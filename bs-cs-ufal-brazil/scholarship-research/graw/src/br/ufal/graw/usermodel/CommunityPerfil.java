package br.ufal.graw.usermodel;

import br.ufal.graw.Utility;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Community;

import java.util.Vector;
import java.util.Hashtable;

/**
 * CommunityPerfil.java
 *
 * @author Marcello de Sales
 */
public class CommunityPerfil{
	
	private String ID;
	private String communityID;
	private String keyword;
	private String description;
	
	private DatabaseLayer database;
	
	public CommunityPerfil(String ID,  DatabaseLayer database) throws PerfilException{
		this.database = database;
		Vector result = this.database.query("SELECT * FROM perfil_community WHERE perfilID='"+ID+"'");
		if (result.size() == 0 )
			throw new PerfilException("Perfil not Found with "+ID);
		else this.initObject((Hashtable)result.firstElement());
	}
	
	private void initObject(Hashtable data){
		this.ID          = (String)data.get("perfilID");
		this.communityID = (String)data.get("communityID");
		this.keyword     = (String)data.get("keyword");
		this.description = (String)data.get("description");
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setCommunityID(String communityID) {
		this.communityID = communityID;
	}
	
	public String getCommunityID() {
		return communityID;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

//	public static boolean exists(String perfilID, DatabaseLayer database){
//		Vector thisResult = database.query("SELECT perfilID FROM perfil_community WHERE perfilID='"+perfilID+"'");
//		return (thisResult.size() == 1);
//	}
	
	public static boolean exists(String communityID, String perfilID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT perfilID FROM perfil_community WHERE perfilID='"+perfilID+"' AND communityID='"+communityID+"'");
		return (thisResult.size() == 1);
	}
	
	public static void createCommunityPerfil(String communityID, String keyword, String description, DatabaseLayer database){
		String perfilID = Utility.getNewID();
		try{
			database.update("INSERT INTO perfil_community (perfilID, communityID, keyword, description) VALUES ('"+perfilID+"','"+communityID+"','"+keyword+"','"+description+"')");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method remove. Remove um perfil.
	 *
	 * @param    perfilID            A identificação do perfil a ser removido.
	 * @param    database            a  DatabaseLayer
	 *
	 */
	public static void remove(String perfilID, DatabaseLayer database){
		try{
			database.update("DELETE FROM perfil_community WHERE perfilID='"+perfilID+"'");
			database.update("DELETE FROM perfil_user_community WHERE perfilID='"+perfilID+"'");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method hasPerfil. Verifica se uma comunidade tem perfil cadastrado.
	 *
	 * @param    community           a  Community
	 * @param    database            a  DatabaseLayer
	 *
	 * @return   a boolean
	 *
	 */
	public static boolean hasPerfil(Community community, DatabaseLayer database){
		Vector thisResult = database.query("SELECT perfilID FROM perfil_community WHERE communityID='"+community.getID()+"'");
		return (thisResult.size() == 1);
	}
		
	/**
	 * Method getCommunityPerfils. Todos os perfils da comunidade.
	 *
	 * @param    community           a  Community
	 * @param    database            a  DatabaseLayer
	 *
	 * @return   a CommunityPerfil[]
	 *
	 */
	public static CommunityPerfil[] getCommunityPerfils(Community community, DatabaseLayer database){
		Vector thisResult = database.query("SELECT perfilID FROM perfil_community WHERE communityID='"+community.getID()+"' ORDER BY keyword ASC");
		int quant = thisResult.size();
		CommunityPerfil[] perfils = new CommunityPerfil[quant];
		for (int i = 0; i < quant; i++){
			String perfilID = (String)((Hashtable)thisResult.get(i)).get("perfilID");
			try{
				perfils[0] = new CommunityPerfil(perfilID,database);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return perfils;
	}
	
	/**
	 *
	 */
	public static void main(String[] args){
		try{
			DatabaseLayer db = new DatabaseLayer();
			CommunityPerfil cp = new CommunityPerfil("1039293794966",db);
			System.out.println("");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

