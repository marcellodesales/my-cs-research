package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.bibliweb.exception.ResourceNotFoundException;

/**
 * Direitos.java
 *
 * @author Marcello de Sales
 * criado em 17/04/2002
 */
public class Right{
	
	/** Identificador do direito do grupo. */
	private String ID;
	/** Identificador do grupo ao qual o direito está associado. */
	private String groupID;
	/** Identificador da operação a qual o grupo terá direito. */
	private String operationID;
	
	/** Resultado das buscas no banco de dados. */
	private Vector result;
	/** Conexão com o banco de dados. */
	private DatabaseLayer database;
	
	public Right(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.result = database.query("SELECT * FROM right WHERE right_id='"+ID+"'");
		if (this.result.size() != 1){
			throw new ResourceNotFoundException("Direito não encotrado com ID= "+ID);
		} else {
			this.database = database;
			Hashtable rightState = (Hashtable)result.firstElement();
			this.ID = ID;
			this.groupID = (String)rightState.get("group_id");
			this.operationID = (String)rightState.get("operation_id");
		}
	}
	
	/** Retorna o identificador do direito. */
	public String getID(){
		return this.ID;
	}
	
	/** Retorna a identificação do grupo ao qual o direito está relacionado. */
	public String getGroupID(){
		return this.groupID;
	}
	
	/** Retorna a identificação da operação que está relacionada com o direito. */
	public String getOperationID(){
		return this.operationID;
	}
	
	public static String createNewRight(String groupID, String operationID, DatabaseLayer database){
		String newRightID = null;
		try{
			newRightID = Utility.getNewID(database);
			database.update("INSERT INTO right (right_id,group_id,operation_id) VALUES ('"+newRightID+"','"+groupID+"','"+operationID+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
		return newRightID;
	}
}

