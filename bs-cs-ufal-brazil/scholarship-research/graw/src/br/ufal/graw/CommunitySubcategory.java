package br.ufal.graw;
import java.util.Hashtable;
import java.util.Vector;

import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.CommunityException;

/**
 *
 * @author Marcello de Sales
 */
public class CommunitySubcategory{
	
	private DatabaseLayer database;
	private Vector result;
	
	private String ID;
	private String categoryID;
	private String description;
	private int quantity;
	
	public CommunitySubcategory(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.database = database;

		/*If the link exists , return its data (whole line)*/
		this.result = this.database.query("SELECT * FROM communitysubcategory WHERE subcategoryID='"+ID+"'");
		if (result.size() == 0){
			throw new ResourceNotFoundException("Subcategoria ("+ID+") não encontrada.",ID);
		}else{
			this.ID = ID;
			this.initObject((Hashtable)this.result.firstElement());
		}
	}
	
    /** Sets the domain and description. */
    private void initObject(Hashtable data){
		this.categoryID  = (String)data.get("categoryID");
		this.description = (String)data.get("description");
		this.result = this.database.query("SELECT COUNT(*) FROM community WHERE subcategoryID='"+this.ID+"' AND status='"+Community.ACTIVED+"'");
		this.quantity    = Integer.parseInt((String)((Hashtable)this.result.firstElement()).get("COUNT(*)"));
    }
	
	public String getID(){
		return this.ID;
	}
	
	public String getCategoryID(){
		return this.categoryID;
	}
	
	public int getPublicQuantity(){
		this.result = this.database.query("SELECT COUNT(*) FROM community WHERE subcategoryID='"+this.ID+"' AND status='"+Community.ACTIVED+"' AND visibility='"+Visibility.PUBLIC+"'");
		return Integer.parseInt((String)((Hashtable)this.result.firstElement()).get("COUNT(*)"));
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public CommunityCategory getCategory(){
		CommunityCategory cc = null;
		try{
			cc = new CommunityCategory(this.categoryID,this.database);
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return cc;
	}
	
	public Vector getCommunities(){
		Vector communities = new Vector();
		String communityID;
		Hashtable hash;
		Community community;
		
		this.result = this.database.query("SELECT communityID from community WHERE "+
										" subcategoryid = '"+this.ID+"' "+
										" AND community.status='"+Community.ACTIVED+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				community = AbstractCommunity.getRealCommunity( communityID ,this.database);
				communities.add( community );
			}catch(CommunityException ce){
				ce.printStackTrace();
			}
		}
		return communities;
	}
	
	public Vector getPublicCommunities(){
		Vector communities = new Vector();
		String communityID;
		Hashtable hash;
		Community community;
		
		this.result = this.database.query("SELECT communityID from community WHERE "+
										" subcategoryid = '"+this.ID+"' "+
										" AND community.status='"+Community.ACTIVED+"' "+
										" AND community.visibility='"+Visibility.PUBLIC+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				community = AbstractCommunity.getRealCommunity( communityID ,this.database);
				communities.add( community );
			}catch(CommunityException ce){
				ce.printStackTrace();
			}
		}
		return communities;
	}
	
	public static boolean exists(String subcategoryID, DatabaseLayer database){
		Vector result = database.query("SELECT subcategoryID FROM communitysubcategory WHERE subcategoryID='"+subcategoryID+"'");
		return (result.size() == 1);
	}
		
	public void printAll(){
		System.out.println();
		System.out.println("Subcategoria " + this.ID);
		System.out.println("Categoria: " + this.categoryID);
		System.out.println(this.description);
		System.out.println("Quantidade: "+this.quantity);
	}
	
	public static void main(String[] args){
		DatabaseLayer db = new DatabaseLayer();
		try {
			if (CommunitySubcategory.exists("222",db)){
				CommunitySubcategory cc = new CommunitySubcategory("1",db);
			} else System.out.println("ddd");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
