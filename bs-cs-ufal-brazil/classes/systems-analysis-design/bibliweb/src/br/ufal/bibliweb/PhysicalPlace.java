package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.bibliweb.exception.ResourceNotFoundException;

/**
 * phisicalPlace.java
 *
 * @author Marcello de Sales
 * criado em 16/04/2002
 */
public class PhysicalPlace{
	
	/** O identificador espa�o f�sico. */
	private String ID;
	/** A descri��o do espa�o f�sico. */
	private String description;
	
	/** Resultado das buscas no banco de dados. */
	private Vector result;
	/** Conex�o com o banco de dados. */
	private DatabaseLayer database;
	
	public PhysicalPlace(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.result = database.query("SELECT * FROM physical_place WHERE physical_place_id='"+ID+"'");
		if (this.result.size() != 1){
			throw new ResourceNotFoundException("Espa�o F�sico n�o encontrado com ID="+ID+" !");
		} else{
			Hashtable physicalPlaceStatus = (Hashtable)result.firstElement();
			this.ID = ID;
			this.database = database;
			this.description = (String)physicalPlaceStatus.get("description");
		}
	}
	
	/** Retorna o identificador do espa�o f�sico.*/
	public String getID(){
		return this.ID;
	}
	
	/** Retorna a descri��o do espa�o f�sico.*/
	public String getDescription(){
		return this.description;
	}
	
	public void printAll(){
		System.out.println("############  Informacoes da Local F�sico   %%%%%%%%%%");
		System.out.println("ID:"+this.getID());
		System.out.println("Descricao: "+this.getDescription());
	}
	
	/** Cria um novo espa�o f�sico.*/
	public static void createNewPhysicalPlace(String description, DatabaseLayer database){
		try{
			database.update("INSERT INTO physical_place (physical_place_id,description) VALUES ('"+Utility.getNewID(database)+"','"+description+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		try{
			DatabaseLayer db = new DatabaseLayer();
			PhysicalPlace.createNewPhysicalPlace("Estante L/A3",db);
			PhysicalPlace.createNewPhysicalPlace("Estante L/B4",db);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	 }
}


