/**
 * Category.java
 *
 * @author GraW's Development Group
 */

package br.ufal.graw;

import br.ufal.graw.exception.CategoryNotFoundException;
import br.ufal.graw.exception.CommunityNotFoundException;
import br.ufal.graw.AbstractCommunity;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Utility;
import br.ufal.graw.Community;
	
import java.util.Hashtable;
import java.util.Vector;

import java.sql.SQLException;

public class Category{
	
	public static String FORUM_CATEGORY = "F";
	public static String DOCUMENT_CATEGORY = "D";
	public static String LINK_CATEGORY = "L";
	
	protected DatabaseLayer database;
	protected Vector result;
		
	protected String ID;
	protected String title;
	protected String quantity;
	protected String kindOfCategoryID;
	protected String communityID;
	
	public Category(String ID, DatabaseLayer database) throws CategoryNotFoundException{
		
		this.database = database;
		this.result = this.database.query("SELECT * FROM category WHERE categoryID='"+ID+"'");
		if (result.size() == 0){
			throw new CategoryNotFoundException("Categoria ("+ID+") não encontrada.",ID);
		}else{
			this.ID = ID;
			this.initObject((Hashtable)this.result.firstElement());
		}
	}
	
	public Category(){
	}
	    
    private void initObject(Hashtable data){
		this.title            = (String)data.get("title");
		this.communityID      = (String)data.get("communityID");
		this.kindOfCategoryID = (String)data.get("kindOfCategoryID");
		if (kindOfCategoryID.equals(Category.DOCUMENT_CATEGORY))
			this.result = this.database.query("SELECT COUNT(*) FROM document WHERE categoryID='"+this.ID+"'");
		else
		if (kindOfCategoryID.equals(Category.LINK_CATEGORY))
			this.result = this.database.query("SELECT COUNT(*) FROM link WHERE categoryID='"+this.ID+"'");
		else
		if (kindOfCategoryID.equals(Category.FORUM_CATEGORY))
			this.result = this.database.query("SELECT COUNT(*) FROM message WHERE categoryID='"+this.ID+"'");
		this.quantity   = (String)((Hashtable)this.result.firstElement()).get("COUNT(*)");
    }
	
	public void printAll(){
		System.out.println();
		System.out.println("CategoriaID " +  this.ID);
		System.out.println("title "+ this.title);
		System.out.println("Quantidade "+ this.quantity);
		System.out.println("CommunityID "+this.communityID);
		System.out.println("Tipo "+this.kindOfCategoryID);
	}
	
	public String getID(){
		return this.ID;
	}
		
	public String getTitle(){
		return this.title;
	}
	
	public String getQuantity(){
		return (this.quantity == null) ? "0" : this.quantity;
	}
	
	public String getCommunityID(){
		return this.communityID;
	}
	public String getKindOfCategoryID(){
		return this.kindOfCategoryID;
	}
	
	public static String createNewDocumentCat(DatabaseLayer database, String communityID, String title)throws CommunityNotFoundException{
		return Category.createNewCategory(communityID,Category.DOCUMENT_CATEGORY,title,database).getID();
	}
	
	public static Category createNewDocumentCat(String communityID, String title, DatabaseLayer database)throws CommunityNotFoundException{
		return Category.createNewCategory(communityID,Category.DOCUMENT_CATEGORY,title,database);
	}
	
	public static Category createNewLinkCat(String communityID, String title, DatabaseLayer database)throws CommunityNotFoundException{
		return Category.createNewCategory(communityID,Category.LINK_CATEGORY,title,database);
	}
	
	public static String createNewLinkCat(DatabaseLayer database, String communityID, String title)throws CommunityNotFoundException{
		return Category.createNewCategory(communityID,Category.LINK_CATEGORY,title,database).getID();
	}
	
	public static Category createNewForumCat(String communityID, String title, DatabaseLayer database) throws CommunityNotFoundException{
		return Category.createNewCategory(communityID,Category.FORUM_CATEGORY,title,database);
	}
	
	public static String createNewForumCat(DatabaseLayer database, String communityID, String title) throws CommunityNotFoundException{
		return Category.createNewCategory(communityID,Category.FORUM_CATEGORY,title,database).getID();
	}
	
	/** Creates a new document to a persistent information database. */
    private static Category createNewCategory(String communityID, String categoryKind, String title, DatabaseLayer database)
		throws CommunityNotFoundException{
		if (!AbstractCommunity.exists(communityID,database)){
			throw new CommunityNotFoundException("Comunidade não encontrada ID = "+communityID,communityID);
		}else {
			Category category = new Category();
			category.createInDatabase(communityID,categoryKind,title,database);
			return category;
		}
	}
	
	/** Creates a new directory in a database. */
	private void createInDatabase(String communityID, String kindOfCategory, String title, DatabaseLayer database){
		this.database = database;
		try{
			this.ID          = Utility.getNewID();
			this.communityID = Utility.transformToDatabase(communityID);
			this.kindOfCategoryID = kindOfCategory;
			this.title =  Utility.getTextField(title);
			this.database.update("INSERT INTO category (categoryID,title,kindOfCategoryID,communityID) VALUES ('"+this.ID+"','"+title+"','"+kindOfCategory+"','"+communityID+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
    public void setTitle(String title){
       try{
		   	this.title =  Utility.getTextField(title);
            this.database.update("UPDATE category SET title='"+this.title+"' WHERE categoryID='"+this.ID+"'");
       } catch (SQLException sqle){
            System.err.println(sqle.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
       }
    }
	
	/** Deletes an existing category from a persistent information database. */
    public static void deleteCategory(String categoryID, DatabaseLayer database)
		throws CategoryNotFoundException{

		int quant = 0;
		try {
			quant = database.update("DELETE FROM category WHERE categoryID='"+categoryID+"'");
			if (quant == 0){
				throw new CategoryNotFoundException("Impossível excluir; diretorio ("+categoryID+") não encontrado.", categoryID);
			}
		} catch (SQLException sqle){
			sqle.getMessage();
		}
	}
	
	/** Verifies if a category with categoryID exists */
	public static boolean exists(String categoryID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT categoryID FROM category WHERE categoryID='"+categoryID+"'");
		return (thisResult.size() == 1);
	}
	
	/** Verifies if a category with categoryID exists */
	public static boolean alreadyExists(String communityID, String title, String kindOfCategoryID,DatabaseLayer database){
		Vector thisResult = database.query("SELECT categoryID FROM category WHERE title='"+title+"' AND kindOfCategoryID='"+kindOfCategoryID+"' AND communityID='"+communityID+"'");
		return (thisResult.size() == 1);
	}
	
	public static void main(String[] args){
		DatabaseLayer database = new DatabaseLayer();
		try{
			//Community comm = AbstractCommunity.getRealCommunity("1015439199628",database);
			//Category ca = Category.createNewDocumentCategory(comm.getID(),database);
			//ca.setTitle("Transparências");
//			Category ca = new Category("979250450050",database);
	//		ca.printAll();
			
			Discipline discipline = new Discipline("1016044335989",database);
			//Vector links = discipline.getLinks(20,10);
			//discipline.printData();
			
			System.out.println(discipline.getMembers().size());

		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
