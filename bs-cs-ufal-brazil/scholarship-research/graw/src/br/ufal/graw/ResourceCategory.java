package br.ufal.graw;

import br.ufal.graw.Category;
import br.ufal.graw.exception.CategoryNotFoundException;
import br.ufal.graw.exception.CategoryAlreadyExistsException;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.CommunityNotFoundException;

import java.util.Hashtable;
import java.util.Date;
import java.util.Vector;

import java.sql.SQLException;

/**
 *
 * @author Marcello de Sales, Rodrigo Paes
 */
public class ResourceCategory extends Category{
	
	private String description;
	
	public ResourceCategory(String ID, DatabaseLayer database) throws CategoryNotFoundException{
		super(ID,database);
		this.result = this.database.query("SELECT * FROM resourcecategory WHERE categoryID='"+ID+"'");
		if (result.size() == 0){
			throw new CategoryNotFoundException("Categoria ("+ID+") não encontrada.",ID);
		}else{
			this.ID = ID;
			this.initObject((Hashtable)this.result.firstElement());
		}
	}
	
	public ResourceCategory(){
	}
	    
    private void initObject(Hashtable data){
		this.description = (String)data.get("description");
    }
	
	public void printAll(){
		System.out.println();
		System.out.println("CategoriaID " +  this.ID);
		System.out.println("title "+ this.title);
		System.out.println("Quantidade "+ this.quantity);
		System.out.println("CommunityID "+this.communityID);
		System.out.println("Tipo "+this.kindOfCategoryID);
		System.out.println("Description "+this.description);
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public static ResourceCategory createNewDocumentCategory(String communityID, String title, String description, DatabaseLayer database) throws CommunityNotFoundException, CategoryAlreadyExistsException{
		ResourceCategory rc = new ResourceCategory();
		try{
			if (Category.alreadyExists(communityID,title,Category.DOCUMENT_CATEGORY,database))
				throw new CategoryAlreadyExistsException("Já existe uma categoria com esse o título "+title+"!");
			else {
				Category category = Category.createNewDocumentCat(communityID,title,database);
				rc = ResourceCategory.createNewResourceCategory(category,description,database);
			}
		} catch (CategoryNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		return rc;
	}
	
	public static ResourceCategory createNewLinkCategory(String communityID, String title, String description, DatabaseLayer database) throws CommunityNotFoundException,CategoryAlreadyExistsException{
		ResourceCategory rc = new ResourceCategory();
		try{
			if (Category.alreadyExists(communityID,title,Category.LINK_CATEGORY,database))
				throw new CategoryAlreadyExistsException("Já existe uma categoria com esse título!");
			else {
				Category category = Category.createNewLinkCat(communityID,title,database);
				rc = ResourceCategory.createNewResourceCategory(category,description,database);
			}
		} catch (CategoryNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		return rc;
	}
	
	/** Creates a new document to a persistent information database. */
    protected static ResourceCategory createNewResourceCategory(Category category, String description, DatabaseLayer database)
		throws CategoryNotFoundException{
			ResourceCategory resourcecategory = new ResourceCategory();
			/* Gets the state of the category Objetc */
			resourcecategory.ID = category.getID();
			resourcecategory.kindOfCategoryID = category.getKindOfCategoryID();
			resourcecategory.communityID = category.getCommunityID();
			resourcecategory.quantity = category.getQuantity();
			resourcecategory.title = category.getTitle();
			/* Creates a new resource category */
			resourcecategory.createInDatabase(category.getID(),description,database);
			return resourcecategory;
	}
	
	/** Creates a new directory in a database. */
	private void createInDatabase(String categoryID, String description, DatabaseLayer database){
		this.database = database;
		try{
			categoryID = Utility.transformToDatabase(categoryID);
			this.description =  Utility.getTextField(description);
			this.database.update("INSERT INTO resourcecategory (categoryID,description) VALUES ('"+categoryID+"','"+description+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
	/** Deletes an existing category from a persistent information database. */
    public static void deleteCategory(String categoryID, DatabaseLayer database)
		throws CategoryNotFoundException{
		try {
			Category category = new Category(categoryID,database);
			
			if (category.getKindOfCategoryID().equals(Category.DOCUMENT_CATEGORY))
				Document.deleteAllDocuments(category,database);
			else
			if (category.getKindOfCategoryID().equals(Category.LINK_CATEGORY))
				Link.deleteAllLinks(category,database);
			
			Category.deleteCategory(categoryID,database);
			database.update("DELETE FROM resourcecategory WHERE categoryID='"+categoryID+"'");
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		} catch (CategoryNotFoundException cnfe){
			throw new CategoryNotFoundException("Categoria não encontrada ('"+categoryID+"')",categoryID);
		} catch (SQLException sqle){
			sqle.getMessage();
		}
	}
	
    public void setData(String title, String description){
		try{
			this.setTitle(title);
			this.description = Utility.getTextField(description);
            this.database.update("UPDATE resourcecategory SET description='"+this.description+"' WHERE categoryID='"+this.ID+"'");
       	} catch (SQLException sqle){
            System.err.println(sqle.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
       	}
    }
	
	public static void main(String[] args){
		
		DatabaseLayer database = new DatabaseLayer();
		
		try{
			Community comm = AbstractCommunity.getRealCommunity("1015439199628",database);
			ResourceCategory ca = ResourceCategory.createNewLinkCategory(comm.getID(),"Departamentos","Todos os links dos departamentos....",database);
			//ResourceCategory ca = ResourceCategory.createNewDocumentCategory(comm.getID(),"Artigos do SBIO2001","Referentes ao 2º bimestre...",database);
			//Category ca = new Category("979250450050",database);
			
			ca.printAll();
			//ResourceCategory.deleteCategory("979665651690",database);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

