package br.ufal.graw;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Enumeration;

/**
 * CommunityState.java
 *
 * @author GraW Dev. Group.
 * @author Marcello de Sales
 * @author Rodrigo Paes
 */
public class UserCommunityState{
	
	private Community community;
	private User user;
	/**
	 * Quantity of users in the community state.
	 */
	private int quantUsers;
	/**
	 * The set of resource categories with key=categoryID and value=quantity of
	 * community state.
	 */
	private Hashtable linkCategories;
	private Hashtable documentCategories;
	private Hashtable forumCategories;
	
	private int userQuantUsers;
	private Hashtable userLinkCategories;
	private Hashtable userDocumentCategories;
	private Hashtable userForumCategories;
	
	private boolean communityHasChanged;
	private Hashtable changedLinkCategories;
	private Hashtable changedDocumentCategories;
	private Hashtable changedForumCategories;
	private int quantNewUsers;
	
	private DatabaseLayer database;
	
	private boolean isNewUserInCommunity;
	
	public UserCommunityState(User user, Community community){
		this.database  = user.getDataBaseLayer();
		this.community = community;
		this.user = user;
		this.getCommunityState();
		this.isNewUserInCommunity = true;
		if (!this.isNewUserCommunityState()){
			this.isNewUserInCommunity = false;
			this.getUserCommunityState();
			if (this.communityChanged()){
				if (thereIsNewLinks()){
					this.changedLinkCategories = this.getChangedResources(this.userLinkCategories,this.linkCategories);
				}
				if (thereIsNewDocuments()){
					this.changedDocumentCategories = this.getChangedResources(this.userDocumentCategories,this.documentCategories);
				}
				if (thereIsNewForumMessages()){
					this.changedForumCategories = this.getChangedResources(this.userForumCategories,this.forumCategories);
				}
				if (thereIsNewUsers()){
					this.quantNewUsers = this.getQuantityNewUsers();
				}
			}
		}
		this.saveUserCommunityState();
	}
		
	/**
	 * Method getUserCommunityState
	 * Populates the Hashtables userLinkCategories, userDocumentCategories, userForumCategories with the user state values
	 * and the quant of users on the state.
	 */
	private void getUserCommunityState(){
		this.userLinkCategories     = new Hashtable();
		this.userDocumentCategories = new Hashtable();
		this.userForumCategories    = new Hashtable();
		
		Vector result = this.database.query("SELECT quantityUsers FROM usercommunitystate WHERE userID='"+this.user.getID()+"' AND communityID='"+this.community.getID()+"'");
		this.userQuantUsers = Integer.valueOf((String)((Hashtable)result.firstElement()).get("quantityUsers")).intValue();
		
		result = this.database.query("SELECT categoryID,quantity,categoryKind FROM usercommunitycategorystate WHERE userID='"+this.user.getID()+"' AND communityID='"+this.community.getID()+"'");
		for (int i = 0; i < result.size(); i++){
			String categoryID = (String)((Hashtable)result.get(i)).get("categoryID");
			String quant = (String)((Hashtable)result.get(i)).get("quantity");
			String kind  = (String)((Hashtable)result.get(i)).get("categoryKind");
			this.setUserQuantResourcesCategory(categoryID,quant,kind);
		}
	}
	
	/**
	 * Method getCommunityState
	 * Populates the Hashtables linkCategories, documentCategories, forumCategories with the community state values
	 * and the quantity of users on it.
	 */
	private void getCommunityState(){
		this.linkCategories     = new Hashtable();
		this.documentCategories = new Hashtable();
		this.forumCategories    = new Hashtable();
		this.quantUsers = community.getQuantUsers();
		
		Vector result = this.database.query("SELECT categoryID FROM category WHERE communityID='"+this.community.getID()+"'");
		try{
			for	(int i = 0; i < result.size(); i++){
				String categoryID = (String)((Hashtable)result.get(i)).get("categoryID");
				Category category = new Category(categoryID,this.database);
				this.setQuantResourcesCategory(category);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method setQuantResourcesCategory
	 *
	 * @param    category            Puts the category in the correct Hashtable of the community state
	 *
	 */
	private void setQuantResourcesCategory(Category category){
		if (category.getKindOfCategoryID().equals(Category.DOCUMENT_CATEGORY)){
			this.documentCategories.put(category.getID(),new Integer(category.getQuantity()));
		} else
		if (category.getKindOfCategoryID().equals(Category.LINK_CATEGORY)){
			this.linkCategories.put(category.getID(),new Integer(category.getQuantity()));
		} else
		if (category.getKindOfCategoryID().equals(Category.FORUM_CATEGORY)){
			this.forumCategories.put(category.getID(),new Integer(category.getQuantity()));
		}
	}

	/**
	 * Method setUserQuantResourcesCategory
	 * Gives Puts the categoryID in the correct Hashtable of the user state.
	 * @param    categoryID          The identification of the category.
	 * @param    quant               The quantity of the resources in the given category.
	 * @param    kind                The kind of the resource category
	 *
	 */
	private void setUserQuantResourcesCategory(String categoryID, String quant, String kind){
		if (kind.equals(Category.DOCUMENT_CATEGORY)){
			this.userDocumentCategories.put(categoryID,new Integer(quant));
		} else
		if (kind.equals(Category.LINK_CATEGORY)){
			this.userLinkCategories.put(categoryID,new Integer(quant));
		} else
		if (kind.equals(Category.FORUM_CATEGORY)){
			this.userForumCategories.put(categoryID,new Integer(quant));
		}
	}
	
	/**
	 * Method getQuantityResources
	 *
	 * @param    resource            The hashtable containing the resource categories.
	 *
	 * @return   The quantity of resources in a set of categories in the resource.
	 *
	 */
	private int getQuantityResources(Hashtable resource){
		int quant = 0;
		Iterator resourcesValues = resource.values().iterator();
		while (resourcesValues.hasNext()){
			Integer quantt = (Integer)resourcesValues.next();
			quant += quantt.intValue();
		}
		return quant;
	}
	
	/**
	 * Method getQuantLinks
	 *
	 * @return   The quantity of links in the community state.
	 *
	 */
	public int getQuantLinks(){
		return this.getQuantityResources(this.linkCategories);
	}

	/**
	 * Method getQuantForumMessages
	 *
	 * @return   The quantity of messages in the forum in the community state.
	 *
	 */
	public int getQuantForumMessages(){
		return this.getQuantityResources(this.forumCategories);
	}

	/**
	 * Method getQuantDocuments
	 *
	 * @return   The quantity of documents in the community state.
	 *
	 */
	public int getQuantDocuments(){
		return this.getQuantityResources(this.documentCategories);
	}
	
	/**
	 * Method getUserQuantDocuments
	 *
	 * @return   The quantity of documents in the user state.
	 *
	 */
	public int getUserQuantDocuments(){
		return this.getQuantityResources(this.userDocumentCategories);
	}
	
	/**
	 * Method getUserQuantLinks
	 *
	 * @return   The quantity of links in the user state.
	 *
	 */
	public int getUserQuantLinks(){
		return this.getQuantityResources(this.userLinkCategories);
	}

	/**
	 * Method getUserQuantForumMessages
	 *
	 * @return   The quantity of links in the user state.
	 *
	 */
	public int getUserQuantForumMessages(){
		return this.getQuantityResources(this.userForumCategories);
	}
	
	/**
	 * Method thereIsNewDocuments
	 * Verifies if there is new documents in the community.
	 * @return   True if the quantity of documents in the user state is less than the community one.
	 *
	 */
	public boolean thereIsNewDocuments(){
		return (this.getUserQuantDocuments() < this.getQuantDocuments());
	}

	/**
	 * Method thereIsNewLinks
	 * Verifies if there is new links in the community.
	 * @return   True if the quantity of links in the user state is less than the community one.
	 *
	 */
	public boolean thereIsNewLinks(){
		return (this.getUserQuantLinks() < this.getQuantLinks());
	}
	
	/**
	 * Method thereIsNewForumMessages
	 * Verifies if there is new messages in the forum in the community.
	 * @return   True if the quantity of messages of the forum in the user state is less than the community one.
	 *
	 */
	public boolean thereIsNewForumMessages(){
		return (this.getUserQuantForumMessages() < this.getQuantForumMessages());
	}
		
	/**
	 * Method thereIsNewUsers
	 * Verifies if there is new users in the community.
	 * @return   True if the quantity of users in the user state is less than the community one.
	 *
	 */
	public boolean thereIsNewUsers(){
		return this.userQuantUsers < this.quantUsers;
	}
	
	/**
	 * Method getChangedResources
	 * Gets a new Hashtable containing the resources categories whose its number of resources has changed.
	 * @param    from                The source hashtable of the user state
	 * @param    to                  The current state of the community
	 *
	 * @return   A new Hashtable containing the resource categories which have changed.
	 *
	 */
	private Hashtable getChangedResources(Hashtable from, Hashtable to){
		Enumeration froms = from.keys();
		Hashtable changedResources = new Hashtable();
		while (froms.hasMoreElements()){
			String resourceID = (String)froms.nextElement();
			Integer quantFrom = (Integer)from.get(resourceID);
			Integer quantTo   = (Integer)to.get(resourceID);
			if (quantTo != null){
				if (quantFrom.intValue() < quantTo.intValue()){
					changedResources.put(resourceID,new Integer(quantTo.intValue() - quantFrom.intValue()));
				}
			} else {
				this.deleteOldResourceInformation(resourceID);
			}
		}
		return changedResources;
	}
	
	/**
	 * Method deleteOldResourceInformation. Se um resourceID for apagado da comunidade
	 * Precisa apagar sua antiga referencia.
	 *
	 * @param    resourceID          a  String
	 *
	 */
	private void deleteOldResourceInformation(String resourceID){
		try	{
			this.database.update("DELETE FROM usercommunitycategorystate WHERE userID='"+this.user.getID()+"' AND communityID='"+this.community.getID()+"' AND categoryID='"+resourceID+"'");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method getChangedLinkCategories
	 * Gets the changed linkCategories
	 * @return   a Hashtable containing the link categories which have changed.
	 *
	 */
	public Hashtable getChangedLinkCategories(){
		return this.changedLinkCategories;
	}

	/**
	 * Method getChangedDocumentCategories
	 * Gets the changed document categories
	 * @return   a Hashtable containing the document categories which have changed.
	 *
	 */
	public Hashtable getChangedDocumentCategories(){
		return this.changedDocumentCategories;
	}

	/**
	 * Method getChangedForumCategories
	 * Gets the changed forum Categories
	 * @return   a Hashtable containing the forum categories which have changed.
	 *
	 */
	public Hashtable getChangedForumCategories(){
		return this.changedForumCategories;
	}

	/**
	 * Method saveUserCommunityState
	 * Saves the current community state for the user.
	 */
	private void saveUserCommunityState(){
		try{
			if (this.communityHasChanged()){
				if (!this.isNewUserInCommunity){
					if (this.thereIsNewDocuments() || this.thereIsNewLinks() || this.thereIsNewForumMessages()){
						this.database.update("DELETE FROM usercommunitycategorystate WHERE userID='"+this.user.getID()+"' AND communityID='"+this.community.getID()+"'");
						this.updateCategoryState(this.forumCategories,Category.FORUM_CATEGORY);
						this.updateCategoryState(this.documentCategories,Category.DOCUMENT_CATEGORY);
						this.updateCategoryState(this.linkCategories,Category.LINK_CATEGORY);
					}
					if (this.thereIsNewUsers()){
						this.database.update("UPDATE usercommunitystate SET quantityUsers='"+this.quantUsers+"' WHERE userID='"+this.user.getID()+"' AND communityID='"+this.community.getID()+"'");
					}
				}
			} else {
				if (this.isNewUserInCommunity){
					this.database.update("INSERT INTO usercommunitystate (userID,communityID,quantityUsers) VALUES ('"+this.user.getID()+"','"+this.community.getID()+"','"+this.community.getQuantUsers()+"')");
					this.updateCategoryState(this.forumCategories,Category.FORUM_CATEGORY);
					this.updateCategoryState(this.documentCategories,Category.DOCUMENT_CATEGORY);
					this.updateCategoryState(this.linkCategories,Category.LINK_CATEGORY);
				}
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	
	/**
	 * Method updateCategoryState
	 * Updates the category values in the database
	 * @param    resourceCategory    The resource category.
	 * @param    kindResourceCategorya  The kind of the resource category.
	 *
	 */
	private void updateCategoryState(Hashtable resourceCategory, String kindResourceCategory){
		try{
			Enumeration keys = resourceCategory.keys();
			while (keys.hasMoreElements()){
				String categoryID = (String)keys.nextElement();
				Integer quantity = (Integer)resourceCategory.get(categoryID);
				this.database.update("INSERT INTO usercommunitycategorystate (userID,communityID,categoryID,categoryKind,quantity) VALUES ('"+this.user.getID()+"','"+this.community.getID()+"','"+categoryID+"','"+kindResourceCategory+"','"+quantity.intValue()+"')");
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the Hashtable of  key=Category and value=String quantity of new resources
	 */
	private Hashtable getChangedResourceCategories(String kind){
		Hashtable resources = new Hashtable();
		Hashtable categoriesValues = new Hashtable();
		if (kind.equals(Category.DOCUMENT_CATEGORY)){
			resources = this.documentCategories;
		}else
		if(kind.equals(Category.LINK_CATEGORY)){
			resources = this.linkCategories;
		}else
		if (kind.equals(Category.FORUM_CATEGORY)){
			resources = this.forumCategories;
		}
		try{
			Enumeration keys = resources.keys();
			while (keys.hasMoreElements()){
				String categoryID = (String)keys.nextElement();
				Integer quantity = (Integer)resources.get(categoryID);
				categoriesValues.put(new Category(categoryID,this.database),String.valueOf(quantity));
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return categoriesValues;
	}
	
	public Hashtable getListDocumentCategories(){
		return this.getChangedResourceCategories(Category.DOCUMENT_CATEGORY);
	}
	
	public Hashtable getListLinkCategories(){
		return this.getChangedResourceCategories(Category.LINK_CATEGORY);
	}
	
	public Hashtable getListForumCategories(){
		return this.getChangedResourceCategories(Category.FORUM_CATEGORY);
	}
	
	public Community getCommunity(){
		return this.community;
	}
	
	private boolean communityChanged(){
		boolean hasChanged = (this.thereIsNewDocuments() || this.thereIsNewForumMessages() ||
					this.thereIsNewLinks() || this.thereIsNewUsers());
		this.communityHasChanged = hasChanged;
		return this.communityHasChanged;
	}
	
	public boolean communityHasChanged(){
		return this.communityHasChanged;
	}
	
	public int getQuantityNewUsers(){
		return this.quantUsers - this.userQuantUsers;
	}
	
	public boolean isNewUserCommunityState(){
		Vector thisResult = database.query("SELECT userID FROM usercommunitystate WHERE userID='"+this.user.getID()+"' AND communityID='"+this.community.getID()+"'");
		return (thisResult.size() == 0);
	}
		
	/**
	 *
	 */
	public static void main(String[] args){
		DatabaseLayer database = new DatabaseLayer();
		try{
			User user = AbstractUser.getRealUser("1019737602440",database);
			Community community = AbstractCommunity.getRealCommunity("1025280704476",database);
			UserCommunityState ucs = new UserCommunityState(user,community);

		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
