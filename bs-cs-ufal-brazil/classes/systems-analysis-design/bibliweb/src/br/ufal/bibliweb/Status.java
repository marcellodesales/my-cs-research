package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.bibliweb.exception.ResourceNotFoundException;

/**
 * Status.java
 *
 * @author Marcello de Sales
 */
public class Status {
	
	/** Define o status de livro disponível. valor: "1*/
	public static final String BOOK_AVAILABLE = "1";
	/** Define o status de livro emprestado. valor: "2"*/
	public static final String BOOK_LENT = "2";
	/** Define o status de livro trancado. valor: "3"*/
	public static final String BOOK_LOCKED = "3";
	/** Define o status de usuário ativo. valor: "4"*/
	public static final String USER_ACTIVED = "4";
	/** Define o status de usuário impedido. valor: "5"*/
	public static final String USER_OBSTRUCTED = "5";
	
	/** a identificação do status. */
	private String ID;
	/** a descrição do status. */
	private String description;
	/** define o grupo do status. se de usuário, exemplar. */
	private String group;
	
	/** Resultado das buscas no banco de dados. */
	private Vector result;
	/** Conexão com o banco de dados. */
	private DatabaseLayer database;
	
	public Status(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.result = database.query("SELECT * FROM status WHERE status_id='"+ID+"'");
		if (this.result.size() != 1){
			throw new ResourceNotFoundException("Status não encontrado com ID="+ID+" !");
		} else {
			this.database = database;
			this.ID = ID;
			Hashtable statusState = (Hashtable)result.firstElement();
			this.description = (String)statusState.get("description");
			this.group       = (String)statusState.get("group");
		}
	}
	
	/** Retorna o identificador do status. */
	public String getID(){
		return this.ID;
	}
	
	/** Retorna a descrição do status. */
	public String getDescription(){
		return this.description;
	}
	
	/** Retorna a descrição do grupo do status. */
	public String getGroup(){
		return this.group;
	}
	
	public void printAll(){
		System.out.println("############  Informacoes do Status   %%%%%%%%%%");
		System.out.println("ID:"+this.getID());
		System.out.println("Descricao: "+this.getDescription());
		System.out.println("Grupo: "+this.getGroup());
	}
	
	public static void createNewStatus(String statusID,String description, String group, DatabaseLayer database){
		try{
			database.update("INSERT INTO status (status_id,description,\"group\") VALUES ('"+statusID+"','"+description+"','"+group+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
	}
	
	public static void main(String[] args){
		DatabaseLayer db = new DatabaseLayer();
		try{
			Status.createNewStatus(Status.BOOK_AVAILABLE,"Livro Disponível","Exemplar",db);
		 	//Status status = new Status("BA",db);
			//status.printAll();
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

