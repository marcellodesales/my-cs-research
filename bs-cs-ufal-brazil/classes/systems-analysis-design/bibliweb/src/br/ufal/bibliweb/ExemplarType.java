package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.bibliweb.exception.ResourceNotFoundException;

/**
 * ExemplarType.java
 *
 * @autor Marcello de Sales
 */
public class ExemplarType {
	
	/** Código para identificar exemplares do tipo E-BOOK "E". */
	public static final String EBOOK_CODE 			= "E";
	/** Código para identificar exemplares do tipo BOOK "B". */
	public static final String BOOK_CODE 			= "B";
	/** Código para identificar exemplares do tipo DISSERTATION "D". */
	public static final String DISSERTATION_CODE 	= "D";
	/** Código para identificar exemplares do tipo MAGAZINE "M". */
	public static final String MAGAZINE_CODE 		= "M";
	/** Código para identificar exemplares do tipo NEWSPAPER "N". */
	public static final String NEWSPAPER_CODE 		= "N";
	
	/** A identificação do tipo de exemplar. */
	private String ID;
	/** A descrição do tipo de exemplar. */
	private String description;
	/**	O código do tipo de exemplar. */
	private String code;
	
	/** Resultado das buscas no banco de dados. */
	private Vector result;
	/** Conexão com o banco de dados. */
	private DatabaseLayer database;
	
	public ExemplarType(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.result = database.query("SELECT * FROM exemplar_type WHERE exemplar_type_id='"+ID+"'");
		if (this.result.size() != 1){
			throw new ResourceNotFoundException("Tipo de exemplar não encontrado com ID="+ID+" !");
		} else {
			Hashtable exemplarTypeState = (Hashtable)result.firstElement();
			this.database 				= database;
			this.ID 					= ID;
			this.description 			= (String)exemplarTypeState.get("description");
			this.code 					= (String)exemplarTypeState.get("code");
		}
	}
	
	/** Retorna a identificação do tipo de exemplar. */
	public String getID() {
		return this.ID;
	}
	
	/** Retorna a descrição do tipo de exemplar. */
	public String getDescription() {
		return this.description;
	}
	
	/** Retorna o código do tipo de exemplar. */
	public String getCode() {
		return this.code;
	}
	
	/** Verifica se um tipo de exemplar existe a partir da identificação exemplarTypeID. */
	public static boolean exists(String exemplarTypeID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT exemplar_type_id FROM exemplar_type WHERE exemplar_type_id='"+exemplarTypeID+"'");
		return (thisResult.size() > 0);
	}
	
	public void printAll(){
		System.out.println("############  Informacoes da categoria   %%%%%%%%%%");
		System.out.println("ID:"+this.getID());
		System.out.println("Descricao: "+this.getDescription());
		System.out.println("Código: "+this.getCode());
	}
	
	public static String createNewExemplarType(String description, String code, DatabaseLayer database){
		String newTypeID = null;
		try{
			newTypeID = Utility.getNewID(database);
			database.update("INSERT INTO exemplar_type (exemplar_type_id,description,code) VALUES ('"+newTypeID+"','"+description+"','"+code+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
		return newTypeID;
	}
	
	public static void main(String[] args){
		DatabaseLayer db = new DatabaseLayer();
		try{
			ExemplarType.createNewExemplarType("Dissertação",ExemplarType.DISSERTATION_CODE,db);
			//ExemplarType et = new ExemplarType("39",db);
			//et.printAll();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

