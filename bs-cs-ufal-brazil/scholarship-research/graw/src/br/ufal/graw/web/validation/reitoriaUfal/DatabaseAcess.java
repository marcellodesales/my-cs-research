/**
Classe que faz interface com o banco de dados. Realiza operações como: conexão; inserção ,
 atualização e deleção de dados.

	@author Marcello de Sales
	@version 1.0
 */
package br.ufal.graw.web.validation.reitoriaUfal;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Iterator;
import java.util.StringTokenizer;

public class DatabaseAcess{
	
	private Statement statement;
  	private Connection connection;
	private final String SERVER   = "athenas.ufal.br";
  	private final String USERNAME = "usrPibic";
	private final String PASSWORD = "(pbc2i!)";
	private final String DATABASE = "Graw";
   	private final String URL = "jdbc:JSQLConnect://"+this.SERVER+"/database="+this.DATABASE+"/user="+this.USERNAME+"/password="+this.PASSWORD;
	
	public DatabaseAcess() throws ClassNotFoundException,SQLException{
		this.connect();
	}
	
	/**Método que realiza a conexão com o banco*/
	public void connect() throws ClassNotFoundException,SQLException{

	    Class.forName("com.jnetdirect.jsql.JSQLDriver");	//Le o Driver

		this.statement=null;
		this.connection=null;

		this.connection = DriverManager.getConnection(URL, new Properties());
		this.statement = connection.createStatement();

	}//fim do metodo conectarBanco

	/**Desfaz a conexão com o banco*/
	public void disconnect(){
		try{
			this.connection.close();
			this.statement.close();
		} catch (Exception e){}

	}//fim do metodo desconectarBanco


	/**
	 *	Realiza consultas ao banco de dados.
	 *
	 *	@param sql 	SQL da query que se deseja fazer
	 *	@return 	Vector de Hashtables com o resultado da consulta
	 */

	public Vector query(String sql)	{
		Vector linhas = new Vector();

		try {
			ResultSet result = statement.executeQuery(sql);
			ResultSetMetaData metaData = result.getMetaData();
			Hashtable colunas;
			int columns = metaData.getColumnCount();
			String colNome;
			Object valor;

			while(result.next()){
				colunas = new Hashtable();
				for (int i=1; i<=columns; i++){
	  				colNome = metaData.getColumnName(i);
					valor   = result.getString(colNome) == null ? "" : result.getString(colNome);
					colunas.put(colNome, valor);
				}
				linhas.add(colunas);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println("Query: "+sql);
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
	
	/**
	 * Method getUsername
	 *
	 * @param    fullName            O nome completo do usuário.
	 *
	 * @return   O nome de usuário a partir do nome completo.
	 *
	 */
	public static String getUsername(String fullName){
		StringTokenizer partsOfFullName = new StringTokenizer(fullName," ");
		String username = "";
		while(partsOfFullName.hasMoreTokens()){
			String partName = partsOfFullName.nextToken();
			if (!partName.equals("DO") && !partName.equals("DA") && !partName.equals("DE") && !partName.equals("DOS")  && !partName.equals("DAS"))
				username += partName.charAt(0);
		}
		return username.toLowerCase();
	}

	public static void main (String []args){
		
		Vector result;
		try{
			DatabaseAcess db = new DatabaseAcess();

//			result = db.query("select * from UsuariosUfal WHERE matricula LIKE '%G55%' AND status=2 ORDER BY nome ASC");
			result = db.query("select * from UsuariosUfal WHERE matricula LIKE '2001%G55%' ORDER BY nome ASC");
//			result = db.query("select * from UsuariosUfal ORDER BY nome ASC");
//			result = db.query("select * from situacaoAluno");
			Enumeration alunos = result.elements();
			String nome,matricula,cpf,tipoUsuario,userName,status;
			System.out.println("View [UsuariosUfal] Quantidade:"+result.size());
//			Hashtable alunoss = (Hashtable)result.get(343);
//			Enumeration chaves = alunoss.keys();
//			while (chaves.hasMoreElements()){
//				String chave = (String)chaves.nextElement();
//				System.out.println(chave);
//			 }
			while (alunos.hasMoreElements()){
				Hashtable aluno = (Hashtable)alunos.nextElement();
				nome        =(String)aluno.get("nome");
				matricula   =(String)aluno.get("matricula");
				cpf         =(String)aluno.get("cpf");
				tipoUsuario =(String)aluno.get("tipoUsuario");
				status =(String)aluno.get("status");

//				Enumeration cols = aluno.keys();
//								while (cols.hasMoreElements()){
//					String key = (String)cols.nextElement();
//					String valor = (String)aluno.get(key);
//					System.out.println(key+" : "+valor);
//				 }
//				String codigo = (String)aluno.get("Codigo");
//				String statusAluno = (String)aluno.get("StatusAluno");
//				System.out.println(codigo+" : "+statusAluno);
//				userName = DatabaseLayer.getUsername(nome);
//				System.out.println("Cod:"+codigo);
//				System.out.println("Status: "+statusaluno);

				System.out.print(matricula+", ");
				System.out.print(nome+", ");
				System.out.print(tipoUsuario+", ");
				System.out.print(cpf+", ");
				System.out.println(status);


			 }
			result = db.query("select * from CursosGraduacaoUfal");
			Enumeration cursos = result.elements();
			String nomecurso,codigo;
			while (cursos.hasMoreElements()){
				Hashtable curso = (Hashtable)cursos.nextElement();
				nomecurso = (String)curso.get("nome");
				codigo    = (String)curso.get("codigo");
				System.out.println("#########################################");
				System.out.println("Nome:"+nomecurso);
				System.out.println("Codigo:"+codigo);
			 }

		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println(db.consultarBanco("select substring(arquivo,1,50) from blobs"));
  	}
}
