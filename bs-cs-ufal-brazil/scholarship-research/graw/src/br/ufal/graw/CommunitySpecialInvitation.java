package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;

import java.sql.SQLException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Utility;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.UserNotFoundException;

/**
 *
 * @author Marcello de Sales
 */
public class CommunitySpecialInvitation{
	
	/** The database abstraction */
	private DatabaseLayer database;
	/** The query result */
	private Vector result;
	/** The identifier of the object */
	private String ID;
	
	private String communityID;
	private String inviterID;
	private String guestID;
	private String email;
	private String message;
	
	
	/** Creates a new hiperlink object from an existing persistent information database identified by ID. */
    public CommunitySpecialInvitation(String ID, DatabaseLayer database)
		throws ResourceNotFoundException{
		this.database = database;
			
		this.result = this.database.query("SELECT * FROM communityguests WHERE communityguestsid ='"+ID+"'");
		if (result.size() == 0){
			throw new ResourceNotFoundException("Convite não encontrado.",ID);
		}else{
			this.ID = ID;
	    	this.initObject((Hashtable)this.result.firstElement());
		}
    }

    /** Sets the address, description and communityID of a link from a hashtable (only object state). */
    private void initObject(Hashtable data){
		this.communityID  	= (String)data.get("communityID");
        this.inviterID     	= (String)data.get("inviterID");
        this.email  		= (String)data.get("email");
		this.message 		= (String)data.get("message");
		this.guestID   		= (String)data.get("guestID");
    }

	/** Creates a new hiperlink to a persistent information database. */
    public static CommunitySpecialInvitation createNew(Community community, User inviter,
													   String email, String message,
													   DatabaseLayer database)
	throws PersistentInformationException{
		String ID = Utility.getNewID();
		message = Utility.transformToDatabase(message);
														   
		try{
			database.update("INSERT INTO communityguests (communityguestsid, communityID, inviterID, email,message,guestID)"+
							" VALUES ('"+ID+"','"+community.getID()+"','"+inviter.getID()+"','"+email+"','"+message+"','NULL')");
			return new CommunitySpecialInvitation(ID,database);
		} catch (SQLException sqle){
			throw new PersistentInformationException("Impossivel criar o convite");
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
			throw new PersistentInformationException("Convite criado porem houve um erro no momento da recuperacao dos dados");
		}
    }
	
	
	public String getID(){
		return this.ID;
	}
	
	public String getEmail(){
		return this.email;
	}
	public String getMessage(){
		return this.message;
	}
	
	public Community getCommunity() throws CommunityException{
		return AbstractCommunity.getRealCommunity(this.communityID,database);
	}
	
	public User getInviter() throws UserNotFoundException{
		return AbstractUser.getRealUser(this.inviterID,database);
	}
	
	public User getGuest() throws UserNotFoundException{
		return AbstractUser.getRealUser(this.guestID,database);
	}
	
	public String getGuestID(){
		return this.guestID;
	}
	
	public void setGuestID(String guestID) throws PersistentInformationException{
		try{
			this.database.update("UPDATE communityguests SET guestID='"+guestID+"' WHERE "+
								"communityguestsid='"+this.ID+"'");
		}catch(Exception e){
			throw new PersistentInformationException("Inpossível setar o guest. "+e.getMessage());
		}
	}
	
	
	public static void main(String args[]){
		try{
			DatabaseLayer database = new DatabaseLayer();
			CommunitySpecialInvitation.createNew(new Discipline("1015439199628",database),
												 new Student("1015331789194",database),
												 "marcello@hotmail.com",
												 "Marcello vem pra ca eh massa",
												database);
												 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}

