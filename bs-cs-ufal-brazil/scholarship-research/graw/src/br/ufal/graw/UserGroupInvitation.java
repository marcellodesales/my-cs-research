/**
	Class Professor.
	@author Programmed by noNamePibic´s group - UFAL
	15/06/2001
 */
package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

import java.sql.SQLException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Group;
import br.ufal.graw.User;
import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.InvitationException;
import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.GroupNotFoundException;
import br.ufal.graw.exception.PersistentInformationException;
/**
	Professor is an entity composed by various disciplines which
	them itself interests or/and monitors.
*/
public class UserGroupInvitation {
	/* In this class , the login attribute means the professor´s matriculation */

	/** Disciplines taught by a professor. */
	private String ID;
	private User inviter;
	private User invited;
	private Group group;
	
	private DatabaseLayer database;
	private Vector result;
	
	public UserGroupInvitation(String invitationID, DatabaseLayer database)
	throws InvitationException, UserNotFoundException,GroupNotFoundException{
		this.database = database;
		this.result = this.database.query("SELECT * FROM groupingInvitation WHERE groupingInvitationID='"+invitationID+"'");
		if (this.result.size() == 0){
			throw new InvitationException("Este convite não existe ou a confirmação já foi efetuada antes.");
		}
		this.ID = invitationID;
		this.initObject((Hashtable)this.result.firstElement());
		
	}
	 
	private void initObject(Hashtable hash)	throws UserNotFoundException,GroupNotFoundException{
		try{
			this.inviter = AbstractUser.getRealUser((String)hash.get("inviterID"), this.database);
		}catch (UserNotFoundException unfe){
			throw new UserNotFoundException("A pessoa que o convidou nao pertence aos nossos registros.");
		}
		
		try{
			this.invited = AbstractUser.getRealUser((String)hash.get("invitedID"), this.database);
		}catch (UserNotFoundException unfe){
			throw new UserNotFoundException("O convidado nao pertence aos nossos registros.");
		}
		
		try{
			this.group = new Group ((String)hash.get("groupingID"), this.database);
		}catch (CommunityException unfe){
			throw new GroupNotFoundException("O grupo ao qual você foi convidado nao existe mais.");
		}
	}
	
	public void confirm() throws PersistentInformationException{
		try{
			this.database.update("INSERT INTO groupingInterests ( userID, groupingID ) VALUES ('"+this.invited.getID()+"' , '"+this.group.getID()+"' )");
			this.database.update("DELETE from groupingInvitation WHERE groupingInvitationID='"+this.ID+"'");
		}catch(SQLException sqle){
			throw new PersistentInformationException(sqle.getMessage());
		}
	}
	
}

