package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.bibliweb.exception.ResourceNotFoundException;

/**
 * Operation.java
 *
 * @author Marcello de Sales
 */
public class Operation{
	
	/** Identificador da opera��o. */
	private String ID;
	/** A descri��o da opera��o. */
	private String description;
	
	/** Resultado das buscas no banco de dados. */
	private Vector result;
	/** Conex�o com o banco de dados. */
	private DatabaseLayer database;
	
	public Operation(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.result = database.query("SELECT * FROM operation WHERE operation_id='"+ID+"'");
		if (this.result.size() != 1){
			throw new ResourceNotFoundException("Opera��o n�o encontrada com ID= "+ID);
		} else {
			this.database = database;
			Hashtable operationState = (Hashtable)result.firstElement();
			this.ID = ID;
			this.description = (String)operationState.get("description");
		}
	}
	
	/** Retorna o identificador da opera��o. */
	public String getID(){
		return this.ID;
	}
	
	/** Retorna a descri��o da opera��o. */
	public String getDescription(){
		return this.description;
	}
	
	/** Adiciona uma nova opera��o. */
	public static String createNewOperation(String operationID,String description, DatabaseLayer database){
		String newOperationID = null;
		try{
			newOperationID = Utility.getNewID(database);
			database.update("INSERT INTO operation (operation_id,description) VALUES ('"+newOperationID+"','"+description+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
		return newOperationID;
	}
}

