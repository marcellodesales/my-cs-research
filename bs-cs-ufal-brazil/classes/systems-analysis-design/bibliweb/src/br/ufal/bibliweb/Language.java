package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.bibliweb.exception.ResourceNotFoundException;

/**
 * Language.java
 *
 * @author Marcello de Sales
 * Criado em 15/04/2002
 */
public class Language{
	
	/** A identificação do idioma. */
	private String ID;
	
	/** A descrição do idioma. */
	private String description;
	
	/** Resultado das buscas no banco de dados. */
	private Vector result;
	
	public Language(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.result = database.query("SELECT * FROM language WHERE language_ID='"+ID+"'");
		if (this.result.size() != 1){
			throw new ResourceNotFoundException("Idioma não encontrado com ID="+ID+" !");
		} else {
			Hashtable languageState = (Hashtable)result.firstElement();
			this.ID = ID;
			this.description = (String)languageState.get("description");
		}
	}
	
	/** Retorna o identificador do idioma. */
	public String getID(){
		return this.ID;
	}
	
	/** Retorna a descrição do idioma. */
	public String getDescription(){
		return this.description;
	}
	
	public void printAll(){
		System.out.println("##############  Linguagem  #############");
		System.out.println("ID: "+this.getID());
		System.out.println("Desc: "+this.getDescription());
	}
	
	public static void createNewLanguage(String description, DatabaseLayer database){
		try{
			database.update("INSERT INTO language (language_Id,description) VALUES ('"+Utility.getNewID(database)+"','"+description+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
	public static void main(String[] arg){
		Language.createNewLanguage("Português",new DatabaseLayer());
		try{
			//Language lang = new Language("1",new DatabaseLayer());
			//lang.printAll();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

