/**
 * DocumentCategory.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw;

import br.ufal.graw.ResourceCategory;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Document;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.CategoryNotFoundException;

import java.util.Hashtable;

/**
 *
 * @author Marcello de Sales
 */
public class DocumentCategory extends ResourceCategory{
	
	public DocumentCategory(String ID, DatabaseLayer database) throws CategoryNotFoundException{
		super(ID,database);
	}
	
	/** Gets the documents of this category. */
    public Document[] getDocuments(){
		Document[] documents = {};
    	this.result = this.database.query("SELECT documentID FROM document WHERE categoryID='"+this.ID+"'");
		try {
			if (this.result.size() > 0){
				documents = new Document[this.result.size()];
	    		for (int i=0; i < this.result.size(); i++){
          			String docID = (String)((Hashtable)this.result.get(i)).get("documentID");
					documents[i] = new Document(docID,this.database);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return documents;
    }
	
	/** Gets the documents of the LinkCategory. */
    public Document[] getDocuments(int offset, int limit){
		Document[] documents = {};
    	this.result  = this.database.query("SELECT documentID FROM document WHERE categoryID='"+this.ID+"' LIMIT "+offset+","+limit);
		try {
			if (this.result.size() > 0){
				documents = new Document[this.result.size()];
	    		for (int i=0; i < this.result.size(); i++){
          			String docuID = (String)((Hashtable)this.result.get(i)).get("documentID");
					documents[i]  = new Document(docuID,this.database);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return documents;
    }
	
	public static void main(String[] args){
		try{
			DocumentCategory documentC = new DocumentCategory("979259936340",new DatabaseLayer());
			Document[] documents = documentC.getDocuments();
			for (int i=0; i<documents.length; i++){
				documents[i].printData();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

