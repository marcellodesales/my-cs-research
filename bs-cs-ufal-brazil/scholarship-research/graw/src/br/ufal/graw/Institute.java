package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Date;

import java.sql.SQLException;

import br.ufal.graw.Utility;
import br.ufal.graw.exception.InstituteNotFoundException;
import br.ufal.graw.exception.InstituteAlreadyExistsException;

/**
 *
 * @author Marcello de Sales, Rodrigo Paes
 */
public class Institute{
	
	private DatabaseLayer database;
	private Vector result;
	
	protected String ID;
	protected String name;
	protected String abbreviation;
	protected String stateID;
	protected String countryID;
	
	public Institute(){
		
	}
	
	public Institute(String instituteID, DatabaseLayer database) throws InstituteNotFoundException{
		this.database = database;
		this.result = this.database.query("SELECT * FROM institute WHERE instituteID='"+instituteID+"'");
		if (this.result.size() > 0){
			this.initObject((Hashtable)result.firstElement());
		}else{
			throw new InstituteNotFoundException("Institute not found.", ID);
		 }
	}
	
	/** Initialize the state of the department */
	private void initObject(Hashtable data){
		this.ID           = (String)data.get("instituteID");
		this.name         = (String)data.get("name");
		this.abbreviation = (String)data.get("abbreviation");
		this.stateID      = (String)data.get("stateID");
		this.countryID    = (String)data.get("countryID");
	}
	
    public static Institute createNewInstitute(String name, String abbreviation, DatabaseLayer database)
	throws InstituteAlreadyExistsException{
		Vector thisResult = new Vector();
		abbreviation = abbreviation.toUpperCase();
		thisResult = database.query("SELECT abbreviation FROM institute WHERE abbreviation='"+abbreviation+"'");
		if (thisResult.size() > 0){
			throw new InstituteAlreadyExistsException("Institute already exists with the abbreviation "+abbreviation, abbreviation);
		}else {
			Institute institute = new Institute();
			institute.createInDatabase(name, abbreviation, database);
			return institute;
		}
    }
	
    public static Institute createNewInstitute(String name, String abbreviation, int stateID, int countryID, DatabaseLayer database)
	throws InstituteAlreadyExistsException{
		Vector thisResult = new Vector();
		abbreviation = abbreviation.toUpperCase();
		thisResult = database.query("SELECT abbreviation FROM institute WHERE abbreviation='"+abbreviation+"'");
		if (thisResult.size() > 0){
			throw new InstituteAlreadyExistsException("Institute already exists with the abbreviation "+abbreviation, abbreviation);
		}else {
			Institute institute = new Institute();
			institute.createInDatabase(name, abbreviation, database);
			return institute;
		}
    }
	
	/** Creates a new department in a database. */
	private void createInDatabase(String name, String abbreviation, DatabaseLayer database){
		this.database = database;
		try{
			this.ID  = (new Date().getTime())+"";
			this.database.update("INSERT INTO institute VALUES ('"+this.ID+"','"+name+"','"+abbreviation+"','0','0')");
			this.name         = name;
			this.abbreviation = abbreviation;
		}catch(SQLException sqle){
			System.err.println(sqle.getMessage());
		}
	}

	/** Creates a new department in a database. */
	private void createInDatabase(String name, String abbreviation, int stateID, int countryID, DatabaseLayer database){
		this.database = database;
		try{
			this.ID  = (new Date().getTime())+"";
			this.database.update("INSERT INTO institute VALUES ('"+this.ID+"','"+name+"','"+abbreviation+"','"+stateID+"','"+countryID+"')");
			this.name         = name;
			this.abbreviation = abbreviation;
		}catch(SQLException sqle){
			System.err.println(sqle.getMessage());
		}
	}
	
	/** Verifies if an institute with instituteID exists. */
	public static boolean exists(String instituteID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT instituteID FROM institute WHERE instituteID='"+instituteID+"'");
		return (thisResult.size() == 1);
	}
	
	/** @return The department's ID */
	public String getID(){
		return this.ID;
	}
	
	/** @return The department's name */
	public String getName(){
		return this.name;
	}
	
	/** @return The department's name */
	public String getAbbreviation(){
		return this.abbreviation;
	}
	
	/** @return The state's ID */
	public String getstateID(){
		return this.stateID;
	}
	
	/** @return The country's ID */
	public String getCountryID(){
		return this.countryID;
	}
	
	public void printAll(){
		System.out.println("#### Institute - "+this.getID()+" ###");
		System.out.println("Name: "+this.getName());
		System.out.println("Abbreviation: "+this.getAbbreviation());
		System.out.println("State: "+this.getstateID());
		System.out.println("Country: "+this.getCountryID());
	}
	
	public static void main(String args[]){
		
		DatabaseLayer data = new DatabaseLayer();
		try {
			//Institute newInst = Institute.createNewInstitute("Universidade Federal de Alagoas","UFAL",data);
			//Institute exist = new Institute("1005260382750",data);
			System.out.println(Institute.exists("1005260382750",data));
		//	exist.printAll();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

