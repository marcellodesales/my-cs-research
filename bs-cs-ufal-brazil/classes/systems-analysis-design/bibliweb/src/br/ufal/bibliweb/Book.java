package br.ufal.bibliweb;

import java.util.Vector;
import java.util.Hashtable;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.sql.SQLException;
import java.sql.Date;

import br.ufal.bibliweb.exception.ExemplarNotFoundException;
import br.ufal.bibliweb.exception.IncorrectISBNFormException;
import br.ufal.bibliweb.DatabaseLayer;

/**
 * Book.java
 *
 * @author Marcello de Sales
 */	
public class Book extends AbstractExemplar{
	
	/** o valor da  da categoria do exemplar. */
	private String authors;
	/** Retorna o valor da identificação da categoria do exemplar. */
	private String ISBN;
	/** Retorna o valor da identificação da categoria do exemplar. */
	private String volume;
	/** A edição do exemplar. */
	protected String edition;
	
	/** Cria uma instância de Livro identificado por ID */
	public Book(String ID, DatabaseLayer database) throws ExemplarNotFoundException{
		super(ID,database.query("SELECT * FROM book WHERE exemplar_id='"+ID+"'"));
		this.database = database;
		this.authors  = (String)this.databaseState.get("authors");
		this.ISBN     = (String)this.databaseState.get("isbn");
		this.volume   = (String)this.databaseState.get("volume");
		this.edition  = (String)this.databaseState.get("edition");
	}
	
	/** Retorna o valor do ISBN do livro. */
	public String getISBN(){
		return this.ISBN;
	}
	
	/** Retorna o nome dos autores do livro. */
	public String getAuthors(){
		return this.authors;
	}
	
	/** Retorna o volume do livro. */
	public String getVolume(){
		return this.volume;
	}

	/** Retorna o valor da edição do exemplar. */
	public String getEdition(){
		return this.edition;
	}
	
	/** Imprime o estado do Livro */
	public void printObject(){
		System.out.println("%%%%%%%% INFORMACAO LIVRO %%%%%%%%%%");
		System.out.println("ID: "+this.getID());
		System.out.println("Titulo: "+this.getTitle());
		System.out.println("Autor: "+this.getAuthors());
		System.out.println("Edicao: "+this.getEdition());
		System.out.println("ISBN: "+this.getISBN());
		System.out.println("Palavras Chaves: "+this.getKeywords());
		System.out.println("Status: "+this.getStatusID());
		System.out.println("Quantidade Total: "+this.getTotalQuantity());
		System.out.println("Quantidade Disponível: "+this.getAvailableQuantity());
		System.out.println("Biblioteca: "+this.getLibraryID());
		System.out.println("Localização Física: "+this.getPhysicalPlaceID());
		System.out.println("Categoria: "+this.getCategoryID());
		System.out.println("Língua: "+this.getLanguageID());
		System.out.println("Data de Aquisição: "+this.getAcquisitionDate());
		System.out.println("Volume: "+this.getVolume());
	}
	
	/** Cria a instância do livro no banco de dados. */
	private static String createInDatabase(String exemplarTypeID, String physicalPlaceID, String categoryID, String languageID, String statusID, String title, String edition, String keywords, String acquisitionDate, int totalQuantity, String ISBN, String volume, String authors, DatabaseLayer database){
		String newExemplarID = "";
		try{ //A quantidade disponível é igual a quantidade total! porque estão todos novos!
			newExemplarID = Utility.getNewOID();
			database.update("INSERT INTO book (exemplar_id,exemplar_type_id,physical_place_id,category_id,"+
								"language_id,status_id,title,keywords,acquisition_date,"+
								"total_quantity,available_quantity,isbn,volume,authors,edition) "+
								"VALUES ("+newExemplarID+","+exemplarTypeID+","+physicalPlaceID+","+categoryID+","+
								""+languageID+","+statusID+",'"+title+"','"+keywords+"',"+
								"'"+acquisitionDate+"',"+totalQuantity+","+totalQuantity+","+
								"'"+ISBN+"','"+volume+"','"+authors+"','"+edition+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
		return newExemplarID;
	}
		
	/** Cria um novo livro retornando sua identificação. */
	public static String createNewBook(String physicalPlaceID, String categoryID, String languageID, String ISBN, String title, String edition, String keywords, String volume, String authors, String acquisitionDate, int totalQuantity, DatabaseLayer database)
	throws IncorrectISBNFormException{
		//verificar primeiramente se existe algum. Caso exista, recuperar esse livro e atualizar quantidade disponível;
		ISBN = ISBN.trim();
		Date giveAcquisitionDate = null;
		if (ISBN.length() != 10){
			throw new IncorrectISBNFormException("O ISBN deve conter exatamente 10 caracteres!",ISBN);
		} else {
			String dateParts[] = Utility.getDateParts(acquisitionDate,"/");
			GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(dateParts[2]),Integer.parseInt(dateParts[1]),Integer.parseInt(dateParts[0]));
			giveAcquisitionDate = new Date(gc.getTimeInMillis());
			System.out.println(giveAcquisitionDate);
		}
		String bookID = Book.alreadyExists(ISBN,database);
		if (bookID != null){
			AbstractExemplar.updateQuantity(bookID,totalQuantity,database);
		} else {
			String statusID = Status.BOOK_AVAILABLE;
			String exemplarTypeID = AbstractExemplar.getBookTypeID(database);
			bookID = Book.createInDatabase(exemplarTypeID,physicalPlaceID,categoryID,languageID,statusID,title,edition,keywords,String.valueOf(giveAcquisitionDate),totalQuantity,ISBN,volume,authors,database);
		}
		return bookID;
	}
	
	/** Verifica se um livro existe a partir de seu ISBN */
	public static String alreadyExists(String ISBN, DatabaseLayer database){
		Vector thisResult = database.query("SELECT exemplar_id FROM book WHERE isbn='"+ISBN+"'");
		return (thisResult.size() > 0) ? (String)((Hashtable)thisResult.firstElement()).get("exemplar_id") : null;
	}

	public static void main(String[] args){
		DatabaseLayer db = new DatabaseLayer();
		GregorianCalendar cal = new GregorianCalendar();
		System.out.println(cal.getTimeInMillis());
		Date today = new Date(cal.getTimeInMillis());
		
		try{
			String date = "12/03/2001";
			GregorianCalendar gc = new GregorianCalendar(2002,04,23);
			System.out.println(gc.getTimeInMillis());
			GregorianCalendar gc2 = new GregorianCalendar(2002,05,32);
			System.out.println(gc2.getTimeInMillis());
			Date a = new Date(gc2.getTimeInMillis());
			System.out.println(a);
			
			/*	PhysicalPlace pp = new PhysicalPlace("14",db);
			Category cat = new Category("43",db);
			Language lang = new Language("28",db);
			
			String newBookID = Book.createNewBook(pp.getID(),cat.getID(),lang.getID(),"8594233322","Fundamentals of Database Systems","3ª Edition","Banco de dados|Datamining|Diagrama ER|XML","Único","Elmasri & Navathe",date,1,db);
			
			Book book = new Book(newBookID,new DatabaseLayer());
			 book.printObject();
			 */
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
