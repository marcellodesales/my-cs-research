package br.ufal.graw.usermodel;

import br.ufal.graw.exception.GrawException;
import br.ufal.graw.User;
import br.ufal.graw.Community;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.AbstractCommunity;

import java.util.Vector;
import java.util.Hashtable;

/**
 * MatchPerfil.java
 *
 * @author Marcello de Sales
 */
public class MatchPerfil{
	
	private User user;
	private Community community;
	private Hashtable matchedUserPreferences;
	
	private DatabaseLayer database;
	
	public MatchPerfil(User user, Community community) throws GrawException{
		this.database = user.getDataBaseLayer();
		if (!user.isMember(community))
			throw new GrawException("Usu�rio "+user.getID()+" n�o � membro da comunidade "+community.getID());
		else {
			this.community = community;
			this.user = user;
		}
	}
		
	/**
	 * Method getMatchUsers. Retorna todos os usu�rios com a preferencia passada no construtor.
	 *
	 * @return   an User[]
	 *
	 */
	public User[] getMatchUsers(CommunityPerfil communityPerfil) throws PerfilException{
		if (!CommunityPerfil.exists(this.community.getID(),communityPerfil.getID(),this.database)){
			throw new PerfilException("Perfil n�o pertence a comunidade "+community.getID());
		}
		Vector result = this.database.query("SELECT perfilUserID FROM perfil_user_community WHERE communityID='"+this.community.getID()+"' AND perfilID='"+communityPerfil.getID()+"' AND userID<>'"+this.user.getID()+"'");
		int quant = result.size();
		Vector matchedUsers = new Vector();
		for (int i = 0; i < quant; i++){
			String perfilUserID = (String)((Hashtable)result.get(i)).get("perfilUserID");
			try{
				UserCommunityPerfil ucp = new UserCommunityPerfil(perfilUserID,this.database);
				matchedUsers.add(ucp.getUser());
			}catch (Exception e){		//Usu�rio n�o encontrado... Ent�o � porque ele n�o existe mais e restaram algum perfil seu.
				// retirar os perfils de usu�rio...
			}
		}
		return vectorToArray(matchedUsers);
	}
	
	private User[] vectorToArray(Vector users){
		User[] allUsers = new User[users.size()];
		int size = users.size();
		for (int i = 0; i < size; i++){
			allUsers[i] = (User)users.get(i);
		}
		return allUsers;
	}
	
	public User[] getMatchUsers(String keyword){ //pega os usu�rios que estao na comun e que possuem perfil contendo a palavra chave
		Vector result = this.database.query("SELECT perfil_user_community.perfilUserID "
											+"FROM perfil_user_community,perfil_community "
											+"WHERE perfil_user_community.userID<>'"+this.user.getID()+"' "
											+"AND perfil_user_community.communityID='"+this.community.getID()+"' "
											+"AND perfil_user_community.perfilID=perfil_community.perfilID "
											+"AND (perfil_community.description LIKE '%"+keyword+"%' OR perfil_user_community.keywords LIKE '%"+keyword+"%')");
		int quant = result.size();
		Vector matchedUsers = new Vector();
		for (int i = 0; i < quant; i++){
			String perfilUserID = (String)((Hashtable)result.get(i)).get("perfilUserID");
			try{
				UserCommunityPerfil ucp = new UserCommunityPerfil(perfilUserID,this.database);
				matchedUsers.add(ucp.getUser());
			}catch (Exception e){		//Usu�rio n�o encontrado... Ent�o � porque ele n�o existe mais e restaram algum perfil seu.
				// retirar os perfils de usu�rio...
			}
		}
		return vectorToArray(matchedUsers);
	}
	
	/**
	 *
	 */
	public static void main(String[] args){
		try{
			DatabaseLayer db = new DatabaseLayer();
			Community comm = AbstractCommunity.getRealCommunity("1016044335989",db);
			User respuser = AbstractUser.getRealUser("1015438291312",db);
			User marcello = AbstractUser.getRealUser("1015439644996",db);
			
			CommunityPerfil cp = new CommunityPerfil("1039546156746",db);
			
			MatchPerfil mp = new MatchPerfil(marcello,comm);
			User[] users = mp.getMatchUsers(cp);
			//User[] users = mp.getMatchUsers("m�veis");
			System.out.println("");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

