package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

import java.io.File;
import java.io.FileNotFoundException;

import java.sql.SQLException;

import java.net.URLEncoder;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Utility;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.CategoryNotFoundException;
import br.ufal.graw.exception.CommunityNotFoundException;

/**
 *	Document is something that a professor can add to a discipline as a word,html document
 *
 *	Class Document.
 *	Programmed by Graw group - UFAL
 *      @author Cidorvan
 *      @author Marcello de Sales
 *      @author Rodrigo Paes
 *	18/06/2001
 *
 */
public class Document{
	/** The database abstraction */
	private DatabaseLayer database;
	/** The query result */
	private Vector result;
	/** The identifier of the document object */
	private String ID;
	/** The document's title */
	private String title;
	/** The local address of the document */
	private String fileName;
	/** The size (bytes) of the document */
	private long size;
	/** Describes the document */
	private String description;
	/** The document belongs to a community identified by  communityID*/
	private String communityID;
	/** The category ID which this link belongs to */
	private String categoryID;

    /** Creates a new document object. */
    private Document(){
    }

	/** Creates a new document object from an existing persistent information database identified by documentID. */
    public Document(String documentID, DatabaseLayer database)
		throws ResourceNotFoundException {
		this.database = database;

		this.result = this.database.query("SELECT * FROM document WHERE documentID='"+documentID+"'");
		if (result.size() == 0){
			throw new ResourceNotFoundException("Documento ("+documentID+") não encontrado.",documentID);
		}else{
			this.ID = documentID;
	    	this.initObject((Hashtable)this.result.firstElement());
		}
    }

    /** Sets the address, description and communityID of a document from a hashtable (only object state). */
    private void initObject(Hashtable data){
		this.communityID = (String)data.get("communityID");
		this.title       = (String)data.get("title");
		this.fileName    = (String)data.get("fileName") ;
		this.size 		 = Long.parseLong(((String)data.get("size")));
		this.description = (String)data.get("description");
		this.categoryID  = (String)data.get("categoryID");
    }

	/** Creates a new document to a persistent information database. */
    public static Document createNewDocument(String categoryID, DatabaseLayer database)
		throws CategoryNotFoundException{
		try{
			Category documentCategory = new Category(categoryID,database);
			Document document = new Document();
			document.createInDatabase(documentCategory,database);
			return document;
		} catch (CategoryNotFoundException rnfe){
			throw new CategoryNotFoundException("Categoria "+categoryID+" não encontrada",categoryID);
		}
    }
	
	/** Creates a new document in a database of a community. */
	private void createInDatabase(Category documentCategory, DatabaseLayer database){
		this.database = database;
		try {
			this.ID = Utility.getNewID();
			this.categoryID  = Utility.transformToDatabase(documentCategory.getID());
			this.communityID = Utility.transformToDatabase(documentCategory.getCommunityID());
			this.database.update("INSERT INTO document VALUES ('"+this.ID+"','"+communityID+"','','','','','"+this.categoryID+"')");
		}catch(SQLException sqle){
			this.ID   = null;
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
	}
	
	/** Creates a new document to a persistent information database. */
	/*    public static Document createNewDocument(String courseID, String groupID,  DatabaseLayer database)
		throws GroupNotFoundException{
		Vector thisResult = new Vector();
		thisResult        = database.query("SELECT groupingID FROM grouping WHERE courseID='"+courseID+"' AND groupingID='"+groupID+"'");
		if (thisResult.size() == 0)
			throw new GroupNotFoundException("Grupo não encontrado.");
		else {
			Document document = new Document();
			document.createInDatabase(courseID,groupID, database);
			return document;
		}
    }
	 */
	
	/** Creates a new document in a database. */
	/*	private void createInDatabase(String courseID, String groupID, DatabaseLayer database){
		this.database = database;
		try {
			this.ID = Utility.getNewID();
			courseID = Utility.transformToDatabase(courseID);
			groupID = Utility.transformToDatabase(groupID);
			this.database.update("INSERT INTO document (documentID,title,address,size,description,courseID,groupingID) VALUES ('"+this.ID+"','','','','','"+courseID+"','"+groupID+"')");
			this.courseID = courseID;
		}catch(SQLException sqle){
			this.ID   = null;
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
	 }*/
	
	/** Deletes an existing document from a persistent information database. */
    public static void deleteDocument(String documentID, DatabaseLayer database)
	throws ResourceNotFoundException{
		Vector thisResult = new Vector();
		int quant = 0;
		
		File fileToDelete = null;
		Document document = new Document(documentID,database);
		fileToDelete = new File(document.getAddress());
		fileToDelete.delete();
		try {
			quant = database.update("DELETE FROM document WHERE documentID='"+documentID+"'");
			if (quant == 0){
				throw new ResourceNotFoundException("Impossível apagar, documento não encontrado");
			}
		} catch (SQLException sqle){
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
    }
		
	/** Deletes an existing document from a persistent information database. */
	/*    public static void deleteDocument(Group group, DatabaseLayer database)
		throws ResourceNotFoundException{
		Vector thisResult = new Vector();
		int quant = 0;
		File fileToDelete = null;
		String documentID;
		Document document;
					
		try {
			thisResult = database.query("SELECT documentID FROM document WHERE groupingID='"+group.getID()+"'");
			for (int i=0 ; i < thisResult.size() ; i++ ){
				documentID = (String) ((Hashtable)thisResult.get(i)).get("documentID");
				document = new Document(documentID,database);
				fileToDelete = new File(document.getAddress());
				fileToDelete.delete();
				quant = database.update("DELETE FROM document WHERE documentID='"+documentID+"'");
				if (quant == 0){
					throw new ResourceNotFoundException("Impossível apagar, documento não encontrado");
				}
			}
		} catch (SQLException sqle){
			System.err.println(sqle.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
    }
	
	 */
	/** Deletes an existing document from a persistent information database. */
    public static void deleteDocument(Document document, DatabaseLayer database)
		throws ResourceNotFoundException{
		Vector thisResult = new Vector();
		int quant = 0;
		try {
			String documentID = document.getID();
			
			File fileToDelete = null;
			fileToDelete = new File(document.getAddress());
			fileToDelete.delete();
			
			quant = database.update("DELETE FROM document WHERE documentID='"+documentID+"'");
			if (quant == 0){
				throw new ResourceNotFoundException("Impossível apagar, documento não encontrado");
			}
		} catch (SQLException sqle){
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
	}
		
    public static void deleteAllDocuments(Category documentCategory, DatabaseLayer database)
		throws ResourceNotFoundException{
		Vector thisResult = new Vector();
		int quant = 0;
		try {
			thisResult = database.query("SELECT documentID FROM document WHERE categoryID='"+documentCategory.getID()+"'");
			if (thisResult.size() > 0){
				for (int i=0; i < thisResult.size(); i++){
					String documentID = (String)((Hashtable)thisResult.get(i)).get("documentID");
					Document.deleteDocument(documentID,database);
				}
			}
		} catch (ResourceNotFoundException rnfe){
			throw new ResourceNotFoundException("Erro ao exluir algum documento da categoria especificada "+documentCategory.getID(),documentCategory.getID());
		}
    }
	
	/** Gets the document ID. */
	public String getID(){
		return this.ID;
	}

	/** Gets the document's title */
	public String getTitle(){
		return this.title;
	}

	/** Gets the net address of the document. */
	public String getAddress(){
		return this.database.getConfiguration().getUploadDir()+
			this.communityID+
			File.separator+
			this.fileName;
	}
	
	/**
	@return O endereco base de onde o arquivo sera fisicamente armazenado
	 */
	public static String getAddressBase(String directoryID , DatabaseLayer database){
		return database.getConfiguration().getUploadDir()+
			directoryID+
			File.separator;
	}
	
	public String getFileName(){
		return this.fileName;
		//return this.address.substring(this.address.lastIndexOf(System.getProperty("file.separator"))+1 );
	}
	public long getSize(){
		return this.size;
	}
	public float getSizeAsMegaByte(){
		return (this.size/(1024f*1024f));
	}

	public float getSizeAsKiloByte(){
		return (this.size/1024f);
	}

	/** Gets the document description. */
	public String getDescription(){
		return this.description;
	}

	/** Gets the code of the discipline which the document belongs to. */
	public String getCommunityID(){
		return this.communityID;
	}
	
	public String getCategoryID(){
		return this.categoryID;
	}
	
	/** Gets the short-format post date of the document. dd-MM-yyyy HH:mm */
	public String getPostDate(){
		return Utility.getFormatedDate(this.ID,"EEEEEE, dd-MM-yyyy HH:mm:ss");
	}

	/** Sets the title, address, size and description of a document. */
    public void setData(String title,String fileName,long size,String description){
       try{
       		title       = Utility.transformToDatabase(title);
       		fileName     = Utility.transformToDatabase(fileName);
			//description = Utility.strReplace(URLEncoder.encode(Utility.transformToDatabase(description)),Utility.URLLINE,Utility.HTMLLINE);
		   	description = Utility.getTextField(description);
			this.database.update("UPDATE document SET title='"+title+"', fileName='"+fileName+"',size="+size+", description='"+description+"' WHERE documentID='"+this.ID+"'");
		   	this.size 			= size;
		  	this.title 			= title;
			this.fileName        = fileName;
			this.description    = description;

       } catch (SQLException sqle){
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
       }
    }

    /** Sets the address, description and disciplineCode of a document. */
    public void setData(String title, String description){
       try{
       		title = Utility.transformToDatabase(title);
       		//description = Utility.strReplace(URLEncoder.encode(Utility.transformToDatabase(description)),Utility.URLLINE,Utility.HTMLLINE);
		   	description = Utility.getTextField(description);
			this.database.update("UPDATE document SET title='"+title+"' , description='"+description+"' WHERE documentID='"+this.ID+"'");
		  	this.title 			= title;
			this.description    = description;

       } catch (SQLException sqle){
               Utility.log(Utility.ERROR_FILE_LOG,sqle);
       }
    }

	/** Sets the title of a document. */
	public void setTitle(String newTitle){
		newTitle = Utility.transformToDatabase(newTitle);
		if (!this.title.equals(newTitle)){
        	try{
            	this.database.update("UPDATE document SET title='"+newTitle+"' WHERE documentID='"+this.ID+"'");
             	this.title = newTitle;
         	} catch (SQLException sqle){
				Utility.log(Utility.ERROR_FILE_LOG,sqle);
         	}
      	}
	}

    /** Sets the address of a document. */
    public void setFileName(String newFileName){
     	newFileName = Utility.transformToDatabase(newFileName);
      	if (!this.title.equals(newFileName)){
        	try{
            	this.database.update("UPDATE document SET fileName='"+newFileName+"' WHERE documentID='"+this.ID+"'");
             	this.fileName = newFileName;
         	} catch (SQLException sqle){
				Utility.log(Utility.ERROR_FILE_LOG,sqle);
         	}
      	}
    }

    /** Sets the description of a document. */
    public void setDescription(String newDescription){
    	newDescription = Utility.transformToDatabase(newDescription);
      	if (!this.description.equals(newDescription)){
        	try{
				//newDescription = Utility.strReplace(URLEncoder.encode(Utility.transformToDatabase(newDescription)),Utility.URLLINE,Utility.HTMLLINE);
				newDescription = Utility.transformToDatabase(newDescription);
            	this.database.update("UPDATE document SET description='"+newDescription+"' WHERE documentID='"+this.ID+"'");
             	this.description = newDescription;
         	} catch (SQLException sqle){
             	Utility.log(Utility.ERROR_FILE_LOG,sqle);
         	}
      	}
    }

	protected void printData(){
		System.out.println("----------------------------------------------");
		System.out.println("DocumentCode   = " + this.getID());
		System.out.println("Título         = " + this.getTitle());
		System.out.println("Address        = " + this.getAddress());
		System.out.println("Description    = " + this.getDescription());
		System.out.println("Size           = " + this.getSize());
		System.out.println("CommunityID    = " + this.getCommunityID());
		System.out.println("CategoryID     = " + this.getCategoryID());
		System.out.println("----------------------------------------------");
	}

	public static void main(String args[]){
		DatabaseLayer data = new DatabaseLayer();
		try{
			// Criar um novo documento
			/*try {
				Category ca = new Category("979259603160",data);
				Document doc = Document.createNewDocument(ca.getID(),data);
				doc.setData("Documentação do Pibic 2001","pibic.doc",5634,"Tudo sobre como se comportar no PIBIC2001. Todas as normas estão inseridas neste manual.");
				doc.printData();
			} catch (Exception e){
				System.out.println("Erro: "+e.getMessage());
			 }*/

			//Cria um novo documento existente
			try {
				Document doc = new Document("979304650690",data);
				doc.printData();
			} catch (ResourceNotFoundException rnfe){
				System.out.println("Erro: "+rnfe.getMessage());
			}

			//Apaga um documento existente
			/*try {
				Document.deleteDocument("993492536010",data);
			} catch (Exception e){
				e.printStackTrace();
			 }*/

	}catch(Exception e){
		e.printStackTrace();
	}
    }

}
