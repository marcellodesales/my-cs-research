package br.ufal.graw;

import br.ufal.graw.ResourceCategory;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Link;
import br.ufal.graw.exception.CategoryNotFoundException;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.CommunityNotFoundException;

import java.util.Hashtable;

/**
 * LinkCategory.java
 *
 * @author Marcello de Sales
 */
public class LinkCategory extends ResourceCategory{
	
	public LinkCategory(String ID, DatabaseLayer database) throws CategoryNotFoundException{
		super(ID,database);
	}
	
	public LinkCategory(){
	}
		
	/** Gets the Links of this category. */
    public Link[] getLinks(){
		Link[] links = {};
    	this.result  = this.database.query("SELECT linkID FROM link WHERE categoryID='"+this.ID+"'");
		try {
			if (this.result.size() > 0){
				links = new Link[this.result.size()];
	    		for (int i=0; i < this.result.size(); i++){
          			String linkID = (String)((Hashtable)this.result.get(i)).get("linkID");
					links[i]      = new Link(linkID,this.database);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			System.out.println(rnfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,rnfe);
		}
		return links;
    }
	
	/** Gets the Links of the LinkCategory. */
    public Link[] getLinks(int offset, int limit){
		Link[] links = {};
    	this.result  = this.database.query("SELECT linkID FROM link WHERE categoryID='"+this.ID+"' LIMIT "+offset+","+limit);
		try {
			if (this.result.size() > 0){
				links = new Link[this.result.size()];
	    		for (int i=0; i < this.result.size(); i++){
          			String linkID = (String)((Hashtable)this.result.get(i)).get("linkID");
					links[i]      = new Link(linkID,this.database);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			System.out.println(rnfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,rnfe);
		}
		return links;
    }
	
	public static void main(String[] args){
		try{
			LinkCategory linkC = new LinkCategory("979259936340",new DatabaseLayer());
			Link[] links = linkC.getLinks();
			for (int i=0; i<links.length; i++){
				links[i].printData();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

