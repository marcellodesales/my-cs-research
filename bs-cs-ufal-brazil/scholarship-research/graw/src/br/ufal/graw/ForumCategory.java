package br.ufal.graw;

import br.ufal.graw.Category;
import br.ufal.graw.exception.CategoryNotFoundException;
import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.MessageNotFoundException;
import br.ufal.graw.exception.CommunityNotFoundException;
import br.ufal.graw.exception.CategoryAlreadyExistsException;

import java.util.Hashtable;
import java.util.Date;
import java.util.Vector;

import java.sql.SQLException;

/**
 *
 * @author Marcello de Sales
 */
public class ForumCategory extends Category{
	
	private String creatorID;
	
	public ForumCategory(String ID, DatabaseLayer database) throws CategoryNotFoundException{
		super(ID,database);
		this.result = this.database.query("SELECT * FROM forumcategory WHERE categoryID='"+ID+"'");
		if (result.size() == 0){
			throw new CategoryNotFoundException("Categoria de fórum ("+ID+") não encontrada.",ID);
		}else{
			this.ID = ID;
			this.initObject((Hashtable)this.result.firstElement());
		}
	}
	
	public ForumCategory(){
	}
	    
    private void initObject(Hashtable data){
		this.creatorID = (String)data.get("creatorID");
    }
	
	public void printAll(){
		System.out.println();
		System.out.println("CategoriaID " +  this.ID);
		System.out.println("title "+ this.title);
		System.out.println("Quantidade "+ this.quantity);
		System.out.println("CommunityID "+this.communityID);
		System.out.println("Tipo "+this.kindOfCategoryID);
		System.out.println("SenderID "+this.creatorID);
	}
	
	public String getCreatorID(){
		return this.creatorID;
	}
	
	public User getCreator(){
		User user = null;
		try{
			user = AbstractUser.getRealUser(this.creatorID,this.database);
		} catch (UserNotFoundException unfe){
			unfe.printStackTrace();
		}
		return user;
	}
	
	public static ForumCategory createNewForumCategory(String communityID, String title, User creator, DatabaseLayer database) throws CommunityNotFoundException,CategoryAlreadyExistsException{
		ForumCategory fc = new ForumCategory();
		try{
			if (Category.alreadyExists(communityID,title,Category.FORUM_CATEGORY,database))
				throw new CategoryAlreadyExistsException("Já existe um tema com o título "+title+"!");
			else {
				Category category = Category.createNewForumCat(communityID,title,database);
				fc = ForumCategory.createNewForumCategory(category, creator, database);
			}
		} catch (CategoryNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return fc;
	}
	
	/** Creates a new document to a persistent information database. */
    private static ForumCategory createNewForumCategory(Category category, User creator, DatabaseLayer database)
		throws CategoryNotFoundException{
			ForumCategory forumCategory = new ForumCategory();
			forumCategory.ID = category.getID();
			forumCategory.kindOfCategoryID = category.getKindOfCategoryID();
			forumCategory.communityID = category.getCommunityID();
			forumCategory.quantity = category.getQuantity();
			forumCategory.createInDatabase(category.getID(),creator,database);
			return forumCategory;
	}
	
	/** Creates a new forumcategory in a database. */
	private void createInDatabase(String categoryID, User creator, DatabaseLayer database){
		this.database = database;
		try{
			categoryID = Utility.transformToDatabase(categoryID);
			this.database.update("INSERT INTO forumcategory (categoryID,creatorID) VALUES ('"+categoryID+"','"+creator.getID()+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
	/** Deletes an existing category from a persistent information database. */
    public static void deleteCategory(String categoryID, DatabaseLayer database)
		throws CategoryNotFoundException{
		try{
			Category forumCategory = new Category(categoryID,database);
			Forum.deleteAllMessages(forumCategory,database);
			Category.deleteCategory(categoryID,database);
			database.update("DELETE FROM forumcategory WHERE categoryID='"+categoryID+"'");
		} catch (SQLException sqle){
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
	}
	
	public Message[] getAllMessages(){
		return this.getMessages(0,0);
	}
	
	public Message[] getMessages(int from, int quant){
		Message[] messages = {};
		String query = "SELECT messageID FROM message WHERE categoryID='"+ID+"' AND ownerMessageID='' ORDER BY messageID";
		if (quant != 0)
			query += " LIMIT "+from+","+quant;
		this.result = this.database.query(query);
		if (this.result.size() > 0){
			messages = new Message[this.result.size()];
			try{
				for (int i=0; i < this.result.size(); i++){
					String messageID = (String)((Hashtable)this.result.get(i)).get("messageID");
					messages[i] = new Message(messageID,database);
				}
			} catch (MessageNotFoundException mnfe){
				mnfe.printStackTrace();
			}
		}
		return messages;
	}
	
    public void setData(String title, String creatorID){
		try{
			this.setTitle(title);
		   	this.creatorID = creatorID;
            this.database.update("UPDATE forumcategory SET creatorID='"+this.creatorID+"' WHERE categoryID='"+this.ID+"'");
       	} catch (SQLException sqle){
            System.err.println(sqle.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
       	}
    }
	
	public static void main(String[] args){
		
		DatabaseLayer database = new DatabaseLayer();
		
		try{
			Community comm = AbstractCommunity.getRealCommunity("1015439199628",database);
			//User user = AbstractUser.getRealUser("1015439644996",database);
			//ForumCategory ca = ForumCategory.createNewForumCategory(comm.getID(),database);
			//ca.setData("Máquina Virtual Java",user.getID());
			ForumCategory ca = new ForumCategory("979993844810",database);
			ca.printAll();
			Message[] messages = ca.getMessages(0,20);
			for (int i=0; i < messages.length; i++){
				messages[i].printData();
		 	}
			//ForumCategory.deleteCategory("979329865390",database);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}


