package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

import java.sql.SQLException;

import java.net.URL;
import java.net.URLEncoder;
import java.net.MalformedURLException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Utility;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.CategoryNotFoundException;
import br.ufal.graw.exception.CommunityNotFoundException;
import br.ufal.graw.exception.GroupNotFoundException;
/**
 * Link is a hiperlink that a professor can add to a discipline.
 * 
 * 	Programmed by noNamePibics group - UFAL
 *	17/06/2001
 *
 * @author Marcello de Sales
 */
public class Link{
	/** The database abstraction */
	private DatabaseLayer database;
	/** The query result */
	private Vector result;
	/** The identifier of the object */
	private String ID;
	/** The link's title */
	private String title;
	/** The net address of the link */
	private String address;
	/** Describes the link */
	private String description;
	/** The communityID which this link belongs to */
	private String communityID;
	/** The category ID which this link belongs to */
	private String categoryID;

    /** Creates a new hiperlink object. */
    private Link(){
    }

	/** Creates a new hiperlink object from an existing persistent information database identified by ID. */
    public Link(String ID, DatabaseLayer database)
		throws ResourceNotFoundException{
		this.database = database;
			
		this.result = this.database.query("SELECT * FROM link WHERE linkID='"+ID+"'");
		if (result.size() == 0){
			throw new ResourceNotFoundException("Link não encontrado.",ID);
		}else{
			this.ID = ID;
	    	this.initObject((Hashtable)this.result.firstElement());
		}
    }

    /** Sets the address, description and communityID of a link from a hashtable (only object state). */
    private void initObject(Hashtable data){
		this.title 		  = (String)data.get("title");
        this.address      = (String)data.get("address");
        this.description  = (String)data.get("description");
		this.communityID  = (String)data.get("communityID");
		this.categoryID   = (String)data.get("categoryID");
    }

	/** Creates a new hiperlink to a persistent information database. */
    public static Link createNewLink(String categoryID, DatabaseLayer database)
		throws CategoryNotFoundException{
		try{
			Category linkCategory = new Category(categoryID,database);
			Link link = new Link();
			link.createInDatabase(linkCategory,database);
			return link;
		} catch (CategoryNotFoundException rnfe){
			throw new CategoryNotFoundException("Categoria "+categoryID+" não encontrada",categoryID);
		}
    }
	
	/** Creates a new hiperlink in a database. */
	private void createInDatabase(Category linkCategory, DatabaseLayer database){
		this.database = database;
		try{
			this.ID         = Utility.getNewID();
			this.categoryID = Utility.transformToDatabase(linkCategory.getID());
			this.communityID = linkCategory.getCommunityID();
			this.database.update("INSERT INTO link VALUES ('"+this.ID+"','"+this.communityID+"','','','','"+this.categoryID+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
    public static void deleteAllLinks(Category linksCategory, DatabaseLayer database){
		try{
			int	quant = database.update("DELETE FROM link WHERE categoryID='"+linksCategory.getID()+"'");
		} catch (SQLException sqle){
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
    }
	
	/** Deletes an existing link from a persistent information database. */
    public static void deleteLink(String linkID, DatabaseLayer database)
		throws ResourceNotFoundException{
		int quant = 0;
		try {
			quant = database.update("DELETE FROM link WHERE linkID='"+linkID+"'");
			if (quant == 0){
				throw new ResourceNotFoundException("Impossível excluir, Link ("+linkID+") não encontrado.", linkID);
			}
		} catch (SQLException sqle){
			sqle.getMessage();
		}
    }

	/** Gets the link code. */
	public String getID(){
		return this.ID;
	}
	
	/** Gets the title of the link. */
	public String getTitle(){
		return this.title;
	}

	/** Gets the net address of the link. */
	public String getAddress(){
		return this.address;
	}

	/** Gets the link description. */
	public String getDescription(){
		return this.description;
	}

	/** Gets the code of the community which the link belongs to. */
	public String getCommunityID(){
		return this.communityID;
	}
	
	/** Gets the code of the community which the link belongs to. */
	public String getCategoryID(){
		return this.categoryID;
	}
	
	/** Gets the short-format post date of link. EEEEEEE, dd-MM-yyyy HH:mm */
	public String getPostDate(){
		return Utility.getFormatedDate(this.ID,"EEEEEE, dd-MM-yyyy HH:mm:ss");
	}

    /** Updates the state of a link to a persistent information. */
    public void setData(String title, String address, String description)
		throws MalformedURLException{
       try{
			URL newAddress = new URL(address);
			this.title 			= Utility.transformToDatabase(title);
		   	this.address        = Utility.transformToDatabase(newAddress.toString());
			this.description    = Utility.getTextField(description);
            //this.description    = Utility.strReplace(URLEncoder.encode(Utility.transformToDatabase(description)),Utility.URLLINE,Utility.HTMLLINE);
            this.database.update("UPDATE link SET title='"+this.title+"', address='"+this.address+"', description='"+this.description+"' WHERE linkID='"+this.ID+"'");
	   } catch (MalformedURLException mfurle){
		   	throw new MalformedURLException("O formato do endereço está incorreto!");
       } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
       }
    }

	/** Sets the title of a link. */
	public void setTitle(String newTitle){
		newTitle = Utility.transformToDatabase(newTitle);
		if (!this.title.equals(newTitle)){
			try{
            	this.database.update("UPDATE link SET title='"+newTitle+"' WHERE linkID='"+this.ID+"'");
            	this.title = newTitle;
         	} catch (SQLException sqle){
                System.err.println(sqle.getMessage());
				Utility.log(Utility.ERROR_FILE_LOG,sqle);
         	}
		}
	}

    /** Sets the address of a link. */
    public void setAddress(String newAddress){
    	newAddress= Utility.transformToDatabase(newAddress);
      	if (!this.address.equals(newAddress)){
        	try{
            	this.database.update("UPDATE link SET address='"+newAddress+"' WHERE linkID='"+this.ID+"'");
             	this.address = newAddress;
         	} catch (SQLException sqle){
            	System.err.println(sqle.getMessage());
				Utility.log(Utility.ERROR_FILE_LOG,sqle);
         	}
      	}
    }

    /** Sets the description of a link. */
    public void setDescription(String newDescription){
      	newDescription = Utility.getTextField(newDescription);
      	if (this.getDescription() != newDescription){
        	try{
				//newDescription = Utility.strReplace(URLEncoder.encode(Utility.transformToDatabase(newDescription)), Utility.URLLINE, Utility.HTMLLINE);
				newDescription = Utility.transformToDatabase(newDescription);
             	this.database.update("UPDATE link SET description='"+newDescription+"' WHERE linkID='"+this.ID+"'");
				this.description = newDescription;
         	} catch (SQLException sqle){
            	System.err.println(sqle.getMessage());
				Utility.log(Utility.ERROR_FILE_LOG,sqle);
         	}
      	}
    }

	private void error(String msg){
		System.out.println(msg);
	}

	protected void printData(){
		System.out.println("----------------------------------------------");
		System.out.println("LinkID         = " + this.getID());
		System.out.println("Address        = " + this.getAddress());
		System.out.println("Description    = " + this.getDescription());
		System.out.println("CommunityID    = " + this.getCommunityID());
		System.out.println("CategoryID     = " + this.getCategoryID());
		System.out.println("----------------------------------------------");
	}

	public static void main(String args[]){
		DatabaseLayer data = new DatabaseLayer();
			// Criar um novo link
		/*try{
				Category linkCategory = new ResourceCategory("979259936340",data);
         		Link link = Link.createNewLink(linkCategory.getID(),data);
		 		link.setData("Máquina Virtual Java","http://www.java.sun.com/jvm","Descrição da máquina virtual java como referência...");
		 		link.printData();
			} catch (Exception e){
				System.out.println("Erro: "+e.getMessage());
			}
		 */
			//Cria um novo link existente
		/*try{
				Link link = new Link("979302032120",data);
				link.printData();
			} catch (ResourceNotFoundException rnfe){
				System.out.println("Erro: "+rnfe.getMessage());
			 }
		 */
			//Apaga um documento existente
		/*try {
				Link.deleteLink("979304650690",data);
			} catch (ResourceNotFoundException rnfe){
				rnfe.printStackTrace();
		 }
		 */
    }

}
