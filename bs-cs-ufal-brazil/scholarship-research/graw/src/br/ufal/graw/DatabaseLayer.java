package br.ufal.graw;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import br.ufal.graw.exception.DatabaseLayerConnectionException;

/**
 * Classe que faz interface com o banco de dados. Realiza operações como: conexão; inserção ,
 * atualização e deleção de dados.
 *
 * @author Marcello de Sales, Rodrigo Paes, Fábio Pinheiro.
 * @version 1.1
 */
public class DatabaseLayer {
	
	private Statement statement;
  	private Connection connection;
  	private Config conf;
	private Vector result;

	public DatabaseLayer(){
		this.conf = new Config();
		try {
			this.connect();
		}catch(Exception e){
			System.err.println("Database Exception: " + e.getMessage());
		}
  	}

	/**Método que realiza a conexão com o banco*/
	public void connect() throws ClassNotFoundException,SQLException,DatabaseLayerConnectionException{
	    
		Class.forName(this.conf.getDatabaseDriver());
		
		this.statement  = null;
		this.connection = null;
		
		try {
			this.connection = DriverManager.getConnection(this.conf.getDatabaseURL(),this.conf.getDatabaseUsername(),this.conf.getDatabasePassword());
		} catch (SQLException sqle){
			sqle.printStackTrace();
			throw new DatabaseLayerConnectionException("There isn't a connection with gr@W' database server!");
		}
		this.statement  = connection.createStatement();
	}

	/**Desfaz a conexão com o banco*/
	public void disconnect(){
		try{
			this.connection.close();
			this.statement.close();
		} catch (Exception e){}
	}


	/**
	 *	Realiza consultas ao banco de dados.
	 *
	 *	@param sql 	SQL da query que se deseja fazer
	 *	@return 	Vector de Hashtables com o resultado da consulta
	 */
	public Vector query(String sql){
		Vector linhas = new Vector();

		try{
			ResultSet result = statement.executeQuery(sql);
			ResultSetMetaData metaData = result.getMetaData();
			Hashtable colunas;
			int columns = metaData.getColumnCount();
			String colNome;
			Object valor;

			while(result.next()){
				colunas = new Hashtable();
				for (int i = 1; i <= columns; i++){
	  				colNome = metaData.getColumnName(i);
	  				//valor   = result.getObject(colNome) == null ? "" : result.getObject(colNome);
					valor   = result.getObject(colNome) == null ? "" : result.getString(colNome);
					colunas.put(colNome, valor);
				}
				linhas.add(colunas);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return linhas;
	}

	/**
		Método para atualizar, inserir ou remover dados do banco.
		@param sql 	Update´s SQL code.

	*/
	public int update(String sql) throws SQLException{
		return statement.executeUpdate(sql);
	}
	
	public Config getConfiguration(){
		return this.conf;
	}

	public static void main (String []args){
		
		DatabaseLayer db = new DatabaseLayer();
		Config conf = db.getConfiguration();
		System.out.println(conf.getDatabasePassword());
		System.out.println(conf.getDatabaseUsername());
		System.out.println(conf.getDatabaseURL());
		
		/*try{
			System.out.println(db.getStudentMatriculation("1000846824160S"));
			System.out.println(db.getStudentName("1000846824160S"));
			System.out.println(db.getStudentEmail("1000846824160S"));
			System.out.println(db.getStudentPassword("1000846824160S"));
			Hashtable hs = db.getAllFieldValues(STUDENT_TABLE,STUDENT_PRIMARY_KEY,"1000846824160S");
			Enumeration e = hs.keys();
			while (e.hasMoreElements()){
				System.out.println(hs.get((String)e.nextElement()));
			}
		}catch(Exception e){
			e.printStackTrace();
		 }*/
  	}
	
	public String getFieldValue(String tableName , String fieldName, String cond){
		this.result = this.query("SELECT "+fieldName+" FROM "+tableName+" WHERE "+cond);
		try{
			return (String)((Hashtable)this.result.firstElement()).get(fieldName);
		}catch(Exception e){
			return null;
		}
	}
	
	/** Área de Teste para abstrair o SQL **********************************/
	
	
	
	/**
	@return  HashTable -> FieldName/Value
	 */
	private Hashtable getAllFieldValues(String tableName , String keyFieldName, String keyValue ){
		this.result = this.query("SELECT * FROM "+tableName+" WHERE "+keyFieldName+"='"+keyValue+"'");
		try{
			return ((Hashtable)this.result.firstElement());
		}catch(Exception e){
			return null;
		}
	}
	
	/** Student ******************************************************************/
	/*
	public final static String STUDENT_TABLE 			= "student";
	public final static String STUDENT_PRIMARY_KEY 		= "studentID";
	public final static String STUDENT_FIELD_MATRICULATION = "studentMatriculation";
	public final static String STUDENT_FIELD_NAME 		= "name";
	public final static String STUDENT_FIELD_EMAIL 		= "email";
	public final static String STUDENT_FIELD_PASSWORD 	= "password";
	
	public void removeStudent(){
	}
	public void addStudent(){
	}
	public String getStudentMatriculation(String studentID){
		return getFieldValue(STUDENT_TABLE,STUDENT_FIELD_MATRICULATION,STUDENT_PRIMARY_KEY,studentID);
	}
	public String getStudentName(String studentID){
		return getFieldValue(STUDENT_TABLE,STUDENT_FIELD_NAME,STUDENT_PRIMARY_KEY,studentID);
	}
	public String getStudentEmail(String studentID){
		return getFieldValue(STUDENT_TABLE,STUDENT_FIELD_EMAIL,STUDENT_PRIMARY_KEY,studentID);
	}
	public String getStudentPassword(String studentID){
		return getFieldValue(STUDENT_TABLE,STUDENT_FIELD_PASSWORD,STUDENT_PRIMARY_KEY,studentID);
	}
	public void setStudentMatriculation(){
	}
	 */
	
}
