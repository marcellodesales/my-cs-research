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
	
	/** O identificador espaço físico. */
	private String ID;
	/** A descrição do espaço físico. */
	private String description;
	
	/** Resultado das buscas no banco de dados. */
	private Vector result;
	/** Conexão com o banco de dados. */
	private DatabaseLayer database;
	
	public PhysicalPlace(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.result = database.query("SELECT * FROM physical_place WHERE physical_place_id='"+ID+"'");
		if (this.result.size() != 1){
			throw new ResourceNotFoundException("Espaço Físico não encontrado com ID="+ID+" !");
		} else{
			Hashtable physicalPlaceStatus = (Hashtable)result.firstElement();
			this.ID = ID;
			this.database = database;
			this.description = (String)physicalPlaceStatus.get("description");
		}
	}
	
	/** Retorna o identificador do espaço físico.*/
	public String getID(){
		return this.ID;
	}
	
	/** Retorna a descrição do espaço físico.*/
	public String getDescription(){
		return this.description;
	}
	
	public void printAll(){
		System.out.println("############  Informacoes da Local Físico   %%%%%%%%%%");
		System.out.println("ID:"+this.getID());
		System.out.println("Descricao: "+this.getDescription());
	}
	
	/** Cria um novo espaço físico.*/
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


