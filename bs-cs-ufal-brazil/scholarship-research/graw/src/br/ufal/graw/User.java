package br.ufal.graw;

import java.util.Vector;
import java.util.Hashtable;

import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.GroupException;
import br.ufal.graw.exception.GroupAlreadyExistsException;
import br.ufal.graw.exception.CourseNotFoundException;
import br.ufal.graw.exception.PolicyException;

/**
 * Operações realizadas por todos os Usuários
 * @author Marcello de Sales
 * @author Rodrigo Paes
 */
public interface User{
	public static final String USER            = "U" ;
	public static final String PROFESSOR       = "P" ;
	public static final String STUDENT         = "S" ;
	public static final String ADMINISTRATOR   = "A" ;
	public static final String EXTERN_USER     = "E" ;

/**
	 @return Groups objects wich this belongs.
	 */
	//public Enumeration getGroups();
	
	/**
	@return Todos os grupos que estao subordinados ao curso do parametro e
	 que esse usuario pertence.
	 */
	public Vector getGroups(Course course);
	
	
	/*
	public String inviteUserToGroup(User user, Group group) throws
	PersistentInformationException, GroupException;
	
	public void signOutGroup(Group group) throws
	PersistentInformationException, GroupException;
	
	public void removeUserFromGroup(User user, Group group) throws
	PersistentInformationException, GroupException;
	
	public void createNewGroup(String courseID,  String name, String goal, boolean isVisible)
	throws CourseNotFoundException, GroupAlreadyExistsException , PersistentInformationException;
	 */
	/** Gets the ID of the user. */
	public String getID();
	
	/** Gets the login of the user. */
	public String getLogin();
	
	/** Gets the name of the user. */
	public String getName();
	
	/** Gets the first name of the user. */
	public String getFirstName();
	
	/** Gets the email of the user. */
	public String getEmail();
	
	/** Gets the password of the user. */
	public String getPassword();
	
	/* Compra se o password passado como parametro eh igual ao do usuario */
	public boolean isPassword(String password);
	
	public String getKindOfUser();
	
	public boolean isGuest(Community community);
	public Community[] getCommunityGuest();
		
	
	public DatabaseLayer getDataBaseLayer();
	
	public void setData(String login, String name, String email, String password)
		throws PersistentInformationException;
	
	public void setData(String name, String email)
		throws PersistentInformationException;
	
	/** Sets the name of a user. */
	public void setName(String newName)
		throws PersistentInformationException;
	
	/** Sets the email of a user. */
	public void setEmail(String newEmail)
		throws PersistentInformationException;
	
	/** Sets the password of a user. */
	public void setPassword(String newPassword)
		throws PersistentInformationException;
	
	/** Sets the login of a user. */
	public void setLogin(String newLogin)
		throws PersistentInformationException;
	
	
	/**
	 * @return All courses wich he interests.
	 */
	//public Vector getCourses();
	
	public boolean isMember(Community community);
	public boolean isResponsible(Community community);
	
	/* @return true se esse usario esta esperando por alguma autorizacao para
	 se tornar membro dessa comunidade
	 */
	public boolean isWaitingAuthorization(Community community);
	
	/**
	Pre: isResponsible() == true. Ou seja para executar esse metodo o usuario precisa ser
	responsavel pela communidade.
	 */
	public void acceptMember(Community community , User user) throws PolicyException;
	/**
	Pre: isResponsible() == true. Ou seja para executar esse metodo o usuario precisa ser
	responsavel pela communidade.
	 */
	public void banMember(Community community , User user) throws PolicyException;
	/**
	Pre: isResponsible() == true. Ou seja para executar esse metodo o usuario precisa ser
	responsavel pela communidade.
	 */
	public void inviteUser(Community community , User user) throws PolicyException;
	/**
	Pre: isResponsible() == true. Ou seja para executar esse metodo o usuario precisa ser
	responsavel pela communidade.
	 */
	public void removeCommunity(Community community) throws PolicyException;
	
	//this isn't part of every user... extracourse can be of any user
	//discipline only by a professosr
	//group by any academic user...
//	public void proposeNewExtraCourse(String title, String description,  int visibility, int associationType, String subcategoryID,String program, int hours, String bibliography);
//	public void proposeNewDiscipline(String title, String description,  int visibility, int associationType, String subcategoryID,String program, int hours, String bibliography, String code);
//	public void proposeNewGroup(String name, String description, int visibility, int associationType, String subcategoryID);
	
	/**
	 * @return true if he has interests for courseID
	 */
//	public boolean hasCourseInterests(Course course);
	
	/**
	 * @return true if he has interests for groupID
	 */
//	public boolean hasGroupInterests(Group group);
	
	/**
	 * Set the students's interests.
	 * @param courseIDs Hashtable whose struct is: [CourseID],[{true,false}], where
	 * True means: This Student interests for course.
	 * False means: This Student not interests for course.
	 */
	
	/*	public void setInterests(Hashtable courseIDs)
		throws PersistentInformationException;
	
	public void setInterests(String courseIDs[])
		throws PersistentInformationException;
	
	public void setInterests(Vector courseIDs)
		throws PersistentInformationException;
	 */
	public Vector getPublicDisciplines();
	public Vector getPublicExtraCourses();
	public Vector getPublicGroups();
	
	public void requestAssociation(Community community);
	public void removeAssociation(Community community);
	public Vector getGroups();
	public Vector getExtraCourses();
	
	
}
