package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;

import java.sql.SQLException;

import br.ufal.bibliweb.exception.ExemplarNotFoundException;
import br.ufal.bibliweb.exception.ResourceNotFoundException;

/**
 * AbstractExemplar.java
 *
 * @author Marcello de Sales
 */
public abstract class AbstractExemplar implements Exemplar{
	
	/** O identificador do exemplar. */
	protected String ID;
	/** O identificador da biblioteca ao qual o exemplar pertence. */
	protected String libraryID;
	/** O identificador do local físico do exemplar. */
	protected String physicalPlaceID;
	/** O identificador da categoria do exemplar. */
	protected String categoryID;
	/** O identificador da linguagem do exemplar. */
	protected String languageID;
	/** O identificador do status do exemplar. */
	protected String statusID;
	/** O identificador do tipo do exemplar. */
	protected String exemplarTypeID;
	/** O título do exemplar. */
	protected String title;
	/** As palavras chaves do exemplar. */
	protected String keywords;
	/** O dia de aquisição do exemplar. */
	protected String acquisitionDate;
	/** A quantidade total de exemplares disponíveis para empréstimo. */
	protected int totalQuantity;
	/** A quantidade de exemplares disponíveis no momento. */
	protected int availableQuantity;
	
	/** Camada de comunicação com o banco de dados. */
	protected DatabaseLayer database;
	/** Todo resultado recuperado do banco de dados. */
	protected Hashtable databaseState;
	
	/** Constroi uma exemplar abstrato */
	public AbstractExemplar(String exemplarID, Vector stateSet) throws ExemplarNotFoundException{
		if (stateSet.size() != 1){
			throw new ExemplarNotFoundException("Exemplar não encontrado com identificador "+exemplarID,exemplarID);
		} else {
			this.databaseState     = (Hashtable)stateSet.firstElement();
			this.ID 			   = exemplarID;
			this.libraryID         = (String)this.databaseState.get("library_id");
			this.physicalPlaceID   = (String)this.databaseState.get("physical_place_id");
			this.categoryID        = (String)this.databaseState.get("category_id");
			this.languageID        = (String)this.databaseState.get("language_id");
			this.statusID          = (String)this.databaseState.get("status_id");
			this.exemplarTypeID    = (String)this.databaseState.get("exemplar_type_id");
			this.title             = (String)this.databaseState.get("title");
			this.keywords          = (String)this.databaseState.get("keywords");
			this.acquisitionDate   = (String)this.databaseState.get("acquisition_date");
			this.totalQuantity     = Integer.parseInt((String)this.databaseState.get("total_quantity"));
			this.availableQuantity = Integer.parseInt((String)this.databaseState.get("available_quantity"));
		}
	}
	
	/** Retorna o valor da identificação do exemplar. */
	public String getID(){
		return this.ID;
	}
	
	/** Retorna o valor da identificação da biblioteca a qual o exemplar pertence. */
	public String getLibraryID(){
		return this.libraryID;
	}

	/** Retorna o valor da identificação do local físico onde o exemplar está localizado. */
	public String getPhysicalPlaceID(){
		return this.physicalPlaceID;
	}

	/** Retorna o valor da identificação da categoria do exemplar. */
	public String getCategoryID(){
		return this.categoryID;
	}

	/** Retorna o valor da identificação do idioma do exemplar. */
	public String getLanguageID(){
		return this.languageID;
	}

	/** Retorna o valor da identificação do status do exemplar. */
	public String getStatusID(){
		return this.statusID;
	}
	
	/** Retorna o valor da identificação do tipo do exemplar. */
	public String getExemplarTypeID(){
		return this.exemplarTypeID;
	}

	/** Retorna o valor do título do exemplar. */
	public String getTitle(){
		return this.title;
	}
		
	/** Retorna as palavras chaves do exemplar. */
	public String getKeywords(){
		return this.keywords;
	}
	
	/** Retorna as palavras chaves do exemplar. */
	public String getAcquisitionDate(){
		return this.acquisitionDate;
	}
	
	/** @return Retorna a quantidade total de exemplares disponíveis. */
	public int getTotalQuantity(){
		return this.totalQuantity;
	}
	
	/** @return Retorna a quantidade de exemplares disponíveis para empréstimo. */
	public int getAvailableQuantity(){
		return this.availableQuantity;
	}
	
	/**
	 * @return Retorna o espaço físico ao qual o exemplar está associado.
	 * @see PhysicalPlace
 	 */
	public PhysicalPlace getPhysicalPlace(){
		PhysicalPlace phy = null;
		try {
			phy = new PhysicalPlace(this.physicalPlaceID,this.database);
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return phy;
	}
		
	/**
	 * @return Retorna o espaço físico ao qual o exemplar está associado.
	 * @see PhysicalPlace
 	 */
	public Category getCategory(){
		Category cat = null;
		try {
			cat = new Category(this.categoryID,this.database);
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return cat;
	}
	
	/**
	 * @return Retorna o curso acadêmico ao qual o exemplar está associado.
	 * @see AcademicCourse
 	 */
	public AcademicCourse getAcademicCourse(){
		AcademicCourse acc = null;
		try{
			acc = (new Category(this.categoryID,this.database)).getAcademicCourse();
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return acc;
	}
	
	/**
	 * @return Retorna o idioma ao qual o exemplar foi escrito.
	 * @see Language
 	 */
	public Language getLanguage(){
		Language language = null;
		try {
			language = new Language(this.languageID,this.database);
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return language;
	}

	/** @return Retorna o tipo do exemplar. */
	public ExemplarType getExemplarType(){
		ExemplarType exT = null;
		try {
			exT = new ExemplarType(this.exemplarTypeID,this.database);
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return exT;
	}
	
	/**
	 * Atualiza a quantidade existente de um livro.
	 * @param exemplarID A identificação do exemplar a ser atualizado.
	 * @param totalQuantity Adiciona mais exemplares.
	 */
	public static void updateQuantity(String exemplarID, int totalQuantity, DatabaseLayer database){
		try{
			database.update("UPDATE exemplar SET total_quantity=total_quantity+"+totalQuantity+" , available_quantity=available_quantity+"+totalQuantity+" WHERE exemplar_id='"+exemplarID+"'");
		} catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
	/** Retorna uma instância correta do tipo de exemplar. */
	public static Exemplar getRealExemplar(String exemplarID, DatabaseLayer database) throws ExemplarNotFoundException, ResourceNotFoundException{
		Exemplar exemplar;
		String exemplarType = AbstractExemplar.getTypeCode(exemplarID,database);
		if (exemplarType.equals(ExemplarType.BOOK_CODE)){
			exemplar = new Book(exemplarID,database);
		} else if (exemplarType.equals(ExemplarType.BOOK_CODE)){
			exemplar = null;
//				user = new Clerk(userID,database);
		} else throw new ResourceNotFoundException("Tipo de exemplar não encontrado ("+exemplarType+") desconhecido!");
		return exemplar;
	}
	
	/** Retorna o código do tipo de exemplar. */
	public static String getTypeCode(String exemplarID, DatabaseLayer database) throws ExemplarNotFoundException{
		Vector result = database.query("SELECT exemplar_type_id FROM \"exemplar\" WHERE exemplar_id='"+exemplarID+"'");
		String exemplarCode = "";
		if (result.size() == 1){
			String groupId = (String)((Hashtable)result.get(0)).get("exemplar_type_id");
			try{
				ExemplarType exemplarType = new ExemplarType(groupId,database);
				exemplarCode = exemplarType.getCode();
			}catch (Exception e){
				e.printStackTrace();
			}
			return exemplarCode;
		} else throw new ExemplarNotFoundException("Exemplar não encontrado com ID="+exemplarID+" !",exemplarID);
	}
	
	/** Retorna o código do tipo de exemplar do livro. */
	public static String getBookTypeID(DatabaseLayer database){
		Vector result = database.query("SELECT exemplar_type_id FROM \"exemplar_type\" WHERE code='"+ExemplarType.BOOK_CODE+"'");
		String exemplarCode = "";
		return (String)((Hashtable)result.get(0)).get("exemplar_type_id");
	}
}

