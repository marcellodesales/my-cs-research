package br.ufal.graw.usermodel;

import br.ufal.graw.exception.GrawException;
import br.ufal.graw.Community;
import br.ufal.graw.User;
import br.ufal.graw.Student;
import br.ufal.graw.Discipline;
import br.ufal.graw.Professor;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.AbstractCommunity;
import br.ufal.graw.DatabaseLayer;

/**
 *
 * @author Marcello de Sales
 */
public class UserModel{
	
	/** Quantidade m�nima de perfis para os usu�rios escolherem. */
	private static int MIN_QUANT_PERFIL = 3;
	/** Singleton de UserModel. */
	private static UserModel userModel = null;
	private DatabaseLayer database;
	
	private UserModel(){
	}
	
	private void setDatabaseLayer(DatabaseLayer database){
		this.database = database;
	}
	
	public static UserModel getInstance(DatabaseLayer databaseLayer){
		if (userModel == null)
			userModel = new UserModel();
		userModel.setDatabaseLayer(databaseLayer);
		return userModel;
	}
	
	/** Cria um novo perfil para a comunidade. Somente para o usu�rio respons�vel pela comunidade por executar essa m�todo. */
	public void createCommunityPerfil(Community community, User user, String keyword, String description)
		throws GrawException{
		if (!user.isResponsible(community)) throw new GrawException("Usu�rio n�o tem permiss�o para criar perfil! "+user.getID());
		else{
			CommunityPerfil.createCommunityPerfil(community.getID(),keyword,description,this.database);
		}
	}
	
	/** Deleta um perfil. Somente para o usu�rio respons�vel pela comunidade. Remove todas as refer�ncias de perfil, desde os usu�rios e comunidades. */
	public void deleteCommunityPerfil(Community community, User communityResponsible, String perfilID) throws GrawException{
		if (!communityResponsible.isResponsible(community)) throw new GrawException("Usu�rio n�o tem permiss�o para criar perfil! "+communityResponsible.getID());
		else{
			CommunityPerfil.remove(perfilID,this.database);
		}
	}
	
	/** Seta um perfil para um usu�rio em uma comunidade. */
	public void setUserCommunityPerfil(Community community, User user, String perfilID, String keywords) throws GrawException{
		if (!user.isMember(community)) throw new GrawException("Usu�rio "+user.getID()+" n�o � membro da comunidade "+community.getID()+" para ter perfil.");
		else
		if (!CommunityPerfil.exists(community.getID(),perfilID,this.database)) throw new PerfilException("Imposs�vel criar perfil para usu�rio: Perfil inexistente com ID "+perfilID);
		else UserCommunityPerfil.createUserCommunityPerfil(community.getID(),user.getID(),perfilID, keywords,this.database);
	}
	
	/** Retorna um matchperfil object onde � poss�vel pesquisar por palavras chaves ou por perfils id�nticos. */
	public MatchPerfil getMatchPerfil(Community community, User user) throws GrawException{
		return new MatchPerfil(user,community);
	}
	
	/**
	 * Method userMayCreatePerfil. Verifica se o usu�rio pode criar um perfil para
	 * uma comunidade. Somente monitores de disciplinas e professores respons�veis
	 * por disciplinas podem faze-lo. Em outros tipos de comunidades somente os
	 * respons�veis podem colocar os perfis.
	 *
	 * @param    user                um usu�rio.
	 * @param    community           uma comunidade.
	 *
	 * @return   a boolean
	 *
	 */
	public boolean userMayCreatePerfil(User user, Community community){
		boolean may = false;
		if ((user instanceof Student) && (community instanceof Discipline))
			may = ((Student)user).isMonitor((Discipline)community);
		else may = user.isResponsible(community);
		return may;
	}
	
	/**
	 * Method communityHasPerfil. Verifica se a comunidade possui algum perfil.
	 *
	 * @param    community           a  Community
	 *
	 * @return   a boolean
	 *
	 */
	public boolean communityHasPerfil(Community community){
		return CommunityPerfil.hasPerfil(community,this.database);
	}
	
	/**
	 * Method usersMayChoosePerfil. Verifica se os usu�rios da comunidade j� podem
	 * atualizar suas preferencias. Para isso, a quantidade de perfis t�m que ser
	 * maior ou igual a MIN_QUANT_PERFIL;
	 *
	 * @param    community           a  Community
	 *
	 * @return   a boolean
	 *
	 */
	public boolean usersMayChoosePerfil(Community community){
		return (CommunityPerfil.getCommunityPerfils(community,this.database).length >= MIN_QUANT_PERFIL);
	}
	
	/**
	 * Method getCommunityPerfils.
	 *
	 * @param    community           a  Community
	 *
	 * @return   a CommunityPerfil[] Um conjunto de todos os perfis do comunidade.
	 *
	 */
	public CommunityPerfil[] getCommunityPerfils(Community community){
		return CommunityPerfil.getCommunityPerfils(community,this.database);
	}
		
	/**
	 *
	 */
	public static void main(String[] args){
		try{
			DatabaseLayer db = new DatabaseLayer();
			Community comm = AbstractCommunity.getRealCommunity("1016044335989",db);
			User respuser = AbstractUser.getRealUser("1015438291312",db);
			//User marcello = AbstractUser.getRealUser("1015439644996",db);
			//UserModel.getInstance().createCommunityPerfil(comm,respuser,"Representa��o do Conhecimento","Todos os algor�timos de busca e representa��o do conhecimento...",db);
			
			boolean mayChoose = UserModel.getInstance(db).usersMayChoosePerfil(comm);
			boolean hasPerfil = UserModel.getInstance(db).communityHasPerfil(comm);
			boolean userMayCreate = UserModel.getInstance(db).userMayCreatePerfil(respuser,comm);
			
//			CommunityPerfil cp = new CommunityPerfil("1039546156746",db);
//			UserModel.getInstance(db).setUserCommunityPerfil(comm,respuser,cp.getID(),"Mathnet, Mathema, Sistemas Multi-Agentes");
//			CommunityPerfil cp = new CommunityPerfil("1039293794966",db);
			System.out.println("");
//
//			UserModel.getInstance().setUserCommunityPerfil(comm,marcello,cp.getID(),"java, soap,",db);
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
