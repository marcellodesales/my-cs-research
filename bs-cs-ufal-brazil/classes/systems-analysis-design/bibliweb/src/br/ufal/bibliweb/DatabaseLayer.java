package br.ufal.bibliweb;

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

import br.ufal.bibliweb.exception.DatabaseLayerConnectionException;

/**
 * Classe que faz interface com o banco de dados. Realiza operações como: conexão; inserção ,
 * atualização e deleção de dados.
 * 
 *	@author GraW Development Group - Marcello de Sales, Rodrigo Paes
 *	@version 1.1
 */
public class DatabaseLayer{
	
	private Statement statement;
  	private Connection connection;
  	private Config conf;
	private Vector result;

	public DatabaseLayer(){
		this.conf = new Config();
		try {
			this.connect();
		}catch(Exception e){
			System.err.println(e.getMessage());
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
			throw new DatabaseLayerConnectionException("There isn't a connection with BiblioWeb' database server!");
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
	
	public void createMapping(){
		try{
			this.update("");
		} catch(Exception e){
			
		}
	}

	public static void main (String []args){
		
		DatabaseLayer db = new DatabaseLayer();
		Config conf = db.getConfiguration();
		System.out.println(conf.getDatabasePassword());
		System.out.println(conf.getDatabaseUsername());
		System.out.println(conf.getDatabaseURL());
  	}
	
	public String getFieldValue(String tableName , String fieldName, String cond){
		this.result = this.query("SELECT "+fieldName+" FROM "+tableName+" WHERE "+cond);
		try{
			return (String)((Hashtable)this.result.firstElement()).get(fieldName);
		}catch(Exception e){
			return null;
		}
	}
	
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
	
}
