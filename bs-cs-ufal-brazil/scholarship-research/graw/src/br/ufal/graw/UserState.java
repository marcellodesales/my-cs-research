package br.ufal.graw;

import java.util.Vector;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * UserState.java
 *
 * @author Marcello de Sales
 * @author Rodrigo Paes
 */
public class UserState{
	
	private User user;
	private String userLastAccessIPAddress;
	private String userLastAccessTime;
	private String accessIPAddress;
	private String accessTime;
	private boolean hasChangesOnCommunities;
	
	private UserCommunityState[] userCommunityState;
	
	private DatabaseLayer database;
	
	public UserState(User user,String iPAddress){
		this.user = user;
		this.database = this.user.getDataBaseLayer();
		this.hasChangesOnCommunities = false;
		if (!this.isNewUser()){
			this.getUserStateValues();
			this.getUserCommunitiesState();
			this.accessIPAddress = iPAddress;
			this.accessTime = Utility.getNewID();
		} else {
			this.userLastAccessIPAddress = iPAddress;
			this.userLastAccessTime = Utility.getNewID();
			this.accessIPAddress = this.userLastAccessIPAddress;
			this.accessTime = this.userLastAccessTime;
			this.getUserCommunitiesState();
		}
		//this.getUserCommunitiesState();
		this.saveUserState();
	}
	
	/**
	 * Method getUserCommunitiesState
	 * Gets the values of the communities the user is interested in and populates the
	 * userCommunityState array.
	 */
	private void getUserCommunitiesState(){
		Vector result = this.database.query("SELECT communityID FROM communityinterests WHERE userID='"+this.user.getID()+"' AND status='A'");
		this.userCommunityState = new UserCommunityState[result.size()];
		this.clearOldInterests(result);
		for (int i = 0; i < result.size(); i++){
			String communityID = (String)((Hashtable)result.get(i)).get("communityID");
			try{
				this.userCommunityState[i] = new UserCommunityState(this.user,AbstractCommunity.getRealCommunity(communityID,this.database));
				if (!this.hasChangesOnCommunities){
					if (this.userCommunityState[i].communityHasChanged())
						this.hasChangesOnCommunities = true;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Method clearOldInterests. Apaga todos os estados de comunidades de usuário
	 * que não são de interesse do usuário.
	 *
	 * @param    interestedCommunitiesa  Vector
	 *
	 */
	private void clearOldInterests(Vector interestedCommunities){
		Vector communitiesState = this.database.query("SELECT communityID FROM usercommunitystate WHERE userID='"+this.user.getID()+"'");
		for (int i = 0; i < communitiesState.size(); i++){
			boolean contains = false;
			String communityID = (String)((Hashtable)communitiesState.get(i)).get("communityID");
			
			for (int j = 0; j < interestedCommunities.size(); j++){
				String interestedCommunityID = (String)((Hashtable)interestedCommunities.get(j)).get("communityID");
				if (interestedCommunityID.equals(communityID)){
					contains = true;
					break;
				}
			}
			
			if (!contains){
				this.deleteAllUserCommunityStateInfo(communityID);
			}
		}
	}
	
	/**
	 * Method deleteAllUserCommunityInformation. Deletes all information of the user.
	 *
	 */
	public void deleteAllUserCommunityStateInfo(String communityID){
		try	{
			this.database.update("DELETE FROM usercommunitycategorystate WHERE userID='"+this.user.getID()+"' AND communityID='"+communityID+"'");
			this.database.update("DELETE FROM usercommunitystate WHERE userID='"+this.user.getID()+"' AND communityID='"+communityID+"'");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method hasChangesOnCommunities
	 *
	 * @return   Se a comunidade mudou...
	 *
	 */
	public boolean hasChangesOnCommunities(){
		return this.hasChangesOnCommunities;
	}
	
	/**
	 * Method getUserStateValues
	 * Gets the values of the user state values.
	 */
	private void getUserStateValues(){
		Vector result = this.database.query("SELECT lastAccessIP,lastAccessTime FROM userstate WHERE userID='"+this.user.getID()+"'");
		this.userLastAccessIPAddress = (String)((Hashtable)result.firstElement()).get("lastAccessIP");
		this.userLastAccessTime = (String)((Hashtable)result.firstElement()).get("lastAccessTime");
	}
		
	/**
	 * Method getUserLastAccessIPAddress
	 *
	 * @return   Gets the last access IP address of the user.
	 *
	 */
	public String getUserLastAccessIPAddress() {
		return this.userLastAccessIPAddress;
	}
	
	
	public boolean isNewUser(){
		Vector thisResult = database.query("SELECT userID FROM userstate WHERE userID='"+this.user.getID()+"'");
		return (thisResult.size() == 0);
	}
	
	
	/**
	 * Method getUserLastAccessTime
	 * Gets the last access time description
	 * @return   The date and time of the last access to the environment.
	 *
	 */
	public String getUserLastAccessTime() {
		return Utility.getFormatedDate(this.userLastAccessTime,"EEEEEE, dd 'de' MMMMMM yyyy HH:mm:ss");
	}
	
	private void saveUserState(){
		try{
			this.database.update("DELETE FROM userstate WHERE userID='"+this.user.getID()+"'");
			this.database.update("INSERT INTO userstate  (userID,lastAccessIP,lastAccessTime) VALUES ('"+this.user.getID()+"','"+this.accessIPAddress+"','"+this.accessTime+"')");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
		
	public UserCommunityState[] getUserCommunityStates(){
		return this.userCommunityState;
	}
	
	/**
	 *
	 */
	public static void main(String[] args){
		try{
			DatabaseLayer database = new DatabaseLayer();
			User user = AbstractUser.getRealUser("1027427649591",database);
			
		UserState state = new UserState(user,"127.0.0.1");
		if (state.hasChangesOnCommunities()){
				System.out.println("tem mudança");
			UserCommunityState[] userCommunityState = state.getUserCommunityStates();
			for (int i = 0; i < userCommunityState.length; i++){
				
				if (userCommunityState[i].thereIsNewDocuments()){
					Hashtable changedDocumentCat = userCommunityState[i].getListDocumentCategories();
					Enumeration keys = changedDocumentCat.keys();
					while (keys.hasMoreElements()){
								Category category = (Category)keys.nextElement();
								String quantity = (String)changedDocumentCat.get(category);
								String news = (quantity.equals("1")) ? "documento novo" : "novos documentos";
							System.out.println(news);
					}
				}

				if (userCommunityState[i].thereIsNewLinks()){
					Hashtable changedLinksCat = userCommunityState[i].getListLinkCategories();
					Enumeration keys = changedLinksCat.keys();
					while (keys.hasMoreElements()){
								Category category = (Category)keys.nextElement();
								String quantity = (String)changedLinksCat.get(category);
								String news = (quantity.equals("1")) ? "link novo" : "novos links";
								System.out.println(news);
					}
  				}

				if (userCommunityState[i].thereIsNewForumMessages()){
					Hashtable changedForumCat = userCommunityState[i].getListForumCategories();
					Enumeration keys = changedForumCat.keys();
					while (keys.hasMoreElements()){
								Category category = (Category)keys.nextElement();
								String quantity = (String)changedForumCat.get(category);
								String news = (quantity.equals("1")) ? "mensagem nova" : "novas mensagens";
							System.out.println(news);
					}
				}
											
				if (userCommunityState[i].thereIsNewUsers()){
					String news = (userCommunityState[i].getQuantityNewUsers() == 1) ? "usuário novo" : "novos usuários";
					System.out.println(news);
				}
			}
				
			} else {
				System.out.println("nada mudou");
			}
			
			/*			Vector a = new Vector();
			String a1 = "eu";
			String a2 = "eu";
			a.add(a1);
			if (a.contains(a2)){
				System.out.println("contem");
			 }*/
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

