package br.ufal.graw.usermodel;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.AbstractCommunity;
import br.ufal.graw.Community;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.User;
import br.ufal.graw.Utility;

import java.util.Vector;
import java.util.Hashtable;

/**
 * UserCommunityPerfil.java
 *
 * @author Marcello de Sales
 */
public class UserCommunityPerfil{
	
	private String ID;
	private String userID;
	private String communityID;
	private String perfilID;
	/** Palavras chaves escolhidas pelo usuário a respeito de uma preferencia perfilID */
	private String keywords;
	
	private DatabaseLayer database;
	
	public UserCommunityPerfil(String ID, DatabaseLayer database) throws PerfilException{
		this.database = database;
		Vector result = this.database.query("SELECT * FROM perfil_user_community WHERE perfilUserID='"+ID+"'");
		if (result.size() == 0 )
			throw new PerfilException("Perfil de usuário não encontrado com ID="+ID);
		else this.initObject((Hashtable)result.firstElement());
	}
	
	private void initObject(Hashtable data){
		this.ID          = (String)data.get("perfilUserID");
		this.communityID = (String)data.get("communityID");
		this.userID      = (String)data.get("userID");
		this.perfilID    = (String)data.get("perfilID");
		this.keywords    = (String)data.get("keywords");
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return this.ID;
	}
					
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserID() {
		return userID;
	}
			
	public void setCommunityID(String communityID) {
		this.communityID = communityID;
	}
	
	public String getCommunityID(){
		return communityID;
	}
	
	public void setPerfilID(String perfilID) {
		this.perfilID = perfilID;
	}
	
	public String getPerfilID() {
		return this.perfilID;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public String getKeywords() {
		return this.keywords;
	}
	
	public User getUser(){
		User user = null;
		try{
			user = AbstractUser.getRealUser(this.userID,this.database);
		}catch (Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	public static void createUserCommunityPerfil(String communityID, String userID, String perfilID, String keywords, DatabaseLayer database){
		String perfilUserID = Utility.getNewID();
		try{
			database.update("INSERT INTO perfil_user_community (perfilUserID,userID,communityID,perfilID,keywords) VALUES ('"+perfilUserID+"','"+userID+"','"+communityID+"','"+perfilID+"','"+keywords+"')");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean userHasCommunityPerfil(Community community){
		Vector thisResult = database.query("SELECT perfilID FROM perfil_community WHERE communityID='"+community.getID()+"'");
		return (thisResult.size() == 1);
	}
		
	/**
	 *
	 */
	public static void main(String[] args){
		try{
			DatabaseLayer db = new DatabaseLayer();
			Community comm = AbstractCommunity.getRealCommunity("1015439199628",db);
			User marcello = AbstractUser.getRealUser("1015439644996",db);
			//UserCommunityPerfil ucp = new UserCommunityPerfil(marcello.getID(),comm.getID(),db);
			System.out.println("");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

