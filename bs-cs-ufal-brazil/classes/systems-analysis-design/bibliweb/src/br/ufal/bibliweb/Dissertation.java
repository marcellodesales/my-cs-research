/**
 * Dissertation.java
 *
 * @autor Ricardo Oliveira
 */

package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;
import java.util.GregorianCalendar;
import java.sql.SQLException;
import java.sql.Date;

import br.ufal.bibliweb.exception.ExemplarNotFoundException;
import br.ufal.bibliweb.DatabaseLayer;

public class Dissertation extends AbstractExemplar{
	
	/** Define o tipo de dissertação. */
	private String kind;
	
	/** Define o autor da dissertação. */
	private String author;
	
	/** Data de publicação da dissertação. */
	private String datePublished;
	
	/** O resumo da dissertação. */
	private String dissertationAbstract;
	
	public Dissertation(String ID, DatabaseLayer database) throws ExemplarNotFoundException{
		super(ID, database.query("SELECT * FROM dissertation WHERE dissertation_id='"+ID+"'"));
		this.database 				= database;
		this.author 				= (String)this.databaseState.get("author");
		this.kind 					= (String)this.databaseState.get("kind");
		this.datePublished 			= (String)this.databaseState.get("date_published");
		this.dissertationAbstract 	= (String)this.databaseState.get("dissertation_abstract");
	}
	
	/** Retorna o tipo da dissertação. */
	public String getKind() {
		return this.kind;
	}
	
	/** Retorna o autor da dissertação. */
	public String getAutor() {
		return this.author;
	}
	
	/** Retorna a data de publicação */
	public String getDatePublished() {
		return this.datePublished;
	}
	
	/** Retorna o resumo da dissertação. */
	public String getDissertationAbstract() {
		return this.dissertationAbstract;
	}
	
	/** Cria a instância da dissertação no banco de dados. */
	private static String createInDatabase(String physicalPlaceID, String categoryID, String languageID, String statusID, String title, String dissertationAbstract, String keywords, String acquisitionDate, int totalQuantity, String kind, String authors, String datePublished, DatabaseLayer database){
		String newExemplarID = "";
		try{ //A quantidade disponível é igual a quantidade total! porque estão todos novos!
			newExemplarID = Utility.getNewOID();
			database.update("INSERT INTO disertation (exemplar_id,physical_place_id,category_id,"+
								"language_id,status_id,title,keywords,acquisition_date,"+
								"total_quantity,available_quantity,kind,authors,date_published,dissertation_abstract) "+
								"VALUES ("+newExemplarID+","+physicalPlaceID+","+categoryID+","+
								""+languageID+","+statusID+",'"+title+"','"+keywords+"',"+
								"'"+acquisitionDate+"',"+totalQuantity+","+totalQuantity+","+
								"'"+kind+"','"+authors+"','"+datePublished+"','"+dissertationAbstract+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
		return newExemplarID;
	}
	
	/** Cria uma nova dissertação retornando sua identificação. */
	public static String createNewDissertation(String physicalPlaceID, String categoryID, String languageID, String statusID, String keywords, String title, String acquisitionDate, int totalQuantity, String kind, String author, String datePublished, String dissertationAbstract, DatabaseLayer database){
		//verificar primeiramente se existe alguma cópia. Caso exista, recuperar essa dissertação e atualizar quantidade disponível;
		String dissertationID = Dissertation.alreadyExists(kind, author, database);
		if (dissertationID != null){
			AbstractExemplar.updateQuantity(dissertationID,totalQuantity,database);
		} else {
			dissertationID = Dissertation.createInDatabase(physicalPlaceID,categoryID,languageID,statusID,title,dissertationAbstract,keywords,acquisitionDate,totalQuantity,kind,author,datePublished,database);
		}
		return dissertationID;
	}
	
	/** Verifica se uma dissertação existe a partir de seu tipo e autor. */
	public static String alreadyExists(String kind, String author, DatabaseLayer database){
		Vector thisResult = database.query("SELECT exemplar_id FROM dissertation WHERE kind='"+kind+"' AND author='"+author+"'");
		return (thisResult.size() > 0) ? (String)((Hashtable)thisResult.firstElement()).get("exemplar_id") : null;
	}
	
}

