package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;

import br.ufal.graw.exception.ResourceNotFoundException;

/**
 *
 * @author Marcello de Sales
 */
public class CommunityCategory{
	
	private DatabaseLayer database;
	private Vector result;
	
	private String ID;
	private String description;
	/*Quantidade de comunidades nessa categoria */
	private String quantity;
	
	public CommunityCategory(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.database = database;

		/*If the link exists , return its data (whole line)*/
		this.result = this.database.query("SELECT * FROM communitycategory WHERE categoryID='"+ID+"'");
		if (result.size() == 0){
			throw new ResourceNotFoundException("Categoria ("+ID+") não encontrada.",ID);
		}else{
			this.ID = ID;
			this.initObject((Hashtable)this.result.firstElement());
		}
	}
	
    /** Sets the domain and description. */
    private void initObject(Hashtable data){
		this.description = (String)data.get("description");
		this.result = this.database.query("SELECT COUNT(*) FROM community WHERE categoryID='"+this.ID+"'");
		this.quantity    = (String)((Hashtable)this.result.firstElement()).get("COUNT(*)");
    }
	
	public void printAll(){
		System.out.println();
		System.out.println("Categoria " + this.ID);
		System.out.println(this.description);
		System.out.println("Quantidade: " + this.quantity);
	}
	
	public String getID(){
		return this.ID;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getQuantity(){
		return this.quantity;
	}
	
	/**
	@return Todas as categorias cadastradas
	 */
	public static Vector getCategories(DatabaseLayer database){
		Vector categories = new Vector();
		Vector result ;
		String categoryID;
		Hashtable hash;
		CommunityCategory category;
		
		result = database.query("SELECT categoryID from communitycategory");
		
		for (int i=0 ; i < result.size() ; i++ ){
			try{
				hash = (Hashtable)result.get(i) ;
				categoryID = (String) hash.get("categoryID");
				category = new CommunityCategory( categoryID ,database);
				categories.add( category );
			}catch(ResourceNotFoundException ce){
				ce.printStackTrace();
			}
		}
		return categories;
	}
	
	public CommunitySubcategory[] getSubcategories(){
		CommunitySubcategory[] subcategories = {};
    	Vector result  = this.database.query("SELECT subcategoryID FROM communitysubcategory WHERE categoryID='"+this.ID+"' ORDER BY description");
		try {
			if (result.size() > 0){
				CommunitySubcategory[] sub = new CommunitySubcategory[result.size()];
	    		for (int i=0; i < result.size(); i++){
					String subcategoryID = (String)((Hashtable)result.get(i)).get("subcategoryID");
					sub[i] = new CommunitySubcategory(subcategoryID,database);
				}
				subcategories = sub;
        	}
		} catch (ResourceNotFoundException cnfe){
				cnfe.printStackTrace();
		}
		return subcategories;
	}
		
	public Vector getSubCategories(){
		Vector subcategories = new Vector();
		Vector result ;
		String subcategoryID;
		Hashtable hash;
		CommunitySubcategory subcategory;
		
		result = database.query("SELECT communitysubcategory.subcategoryID from communitycategory,communitysubcategory WHERE "+
							   " communitysubcategory.categoryID = communitycategory.categoryID AND communitycategory.categoryID='"+this.ID+"'");
		
		for (int i=0 ; i < result.size() ; i++ ){
			try{
				hash = (Hashtable)result.get(i) ;
				subcategoryID = (String) hash.get("subcategoryID");
				subcategory = new CommunitySubcategory( subcategoryID ,database);
				subcategories.add( subcategory );
			}catch(ResourceNotFoundException ce){
				ce.printStackTrace();
			}
		}
		return subcategories;
	}
	
	public static void main(String[] args){
		DatabaseLayer db = new DatabaseLayer();
		try {
			CommunityCategory cc = new CommunityCategory("003",db);
			CommunitySubcategory[] ccs = cc.getSubcategories();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

