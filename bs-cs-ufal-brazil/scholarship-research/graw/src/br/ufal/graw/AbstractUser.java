/**
 User is an absctract entity. It can be a user, professor...

 */
package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

import java.sql.SQLException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Department;

import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.GroupNotFoundException;
import br.ufal.graw.exception.GroupAlreadyExistsException;
import br.ufal.graw.exception.CourseNotFoundException;
import br.ufal.graw.exception.DepartmentNotFoundException;
import br.ufal.graw.exception.GroupException;
import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.UserLoginFormatException;
import br.ufal.graw.exception.UserLoginWrongPasswordException;
import br.ufal.graw.exception.UserDuplicateLoginException;
import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.PolicyException;
import br.ufal.graw.exception.CommunityException;

/** Classe abstrata que implementa todas as operações em comum aos usuários.
 Operações que tiverem implementações diferentes são deixadas para as classes que
 extenderem de user.
 */
public class AbstractUser implements User{
	
	/** The user's ID while in database */
	protected String ID;
	/** The login of the user. */
	protected String login;
	/** The name of the user. */
	protected String name;
	/** The email of the user. */
	protected String email;
	/** The password of the user. */
	protected String password;
	/** The kind of user. */
	protected String kindOfUserID;
	
	/**
	IDs dos cursos extra curriculares do qual esse usuario eh membro.
	 */
	
	//protected Vector extraCourses;
	
	/**
	 Group's ID. which thi user itself interests.
	 */
	//protected Vector groups;
	
	protected DatabaseLayer database;
	protected Vector result;
	
	public AbstractUser(String ID, DatabaseLayer database) throws UserNotFoundException{
		
		this.database = database;
		this.result   = this.database.query("SELECT * FROM user WHERE userID='"+ID+"'");
		if (result.size() == 0 )
			throw new UserNotFoundException("User not Found with "+ID);
		else {
			this.initObject((Hashtable)this.result.firstElement());
		}
	}
	
	/** Sets the name, email, password of a user in a hashtable (only object state). */
	private void initObject(Hashtable data){
		this.ID       = (String)data.get("userID");
		this.login    = (String)data.get("login");
		this.name     = (String)data.get("name");
		this.email 	  = (String)data.get("email");
		this.password = (String)data.get("password");
		this.kindOfUserID = (String)data.get("kindOfUserID");
		
		/*courses = this.loadInterestedCourses();
		groups 	= this.loadInterestedGroups();
		
		
		String kindOfUser = (String)data.get("kindOfUserID");
		
		if (kindOfUser.equals("P")){
			this.result = database.query("SELECT matriculation,departmentID from professor WHERE userID='"+this.ID+"'");
			
		} else if (kindOfUser.equals("S")){
			this.result = database.query("SELECT matriculation,departmentID from student WHERE userID='"+this.ID+"'");
		} else this.department = null; //the user is an admin
		
		this.matriculation = (String)((Hashtable)this.result.firstElement()).get("matriculation");
		
		try {
			this.department = new Department((String)((Hashtable)this.result.firstElement()).get("departmentID"),this.database);
		} catch (DepartmentNotFoundException dnfe){
			dnfe.printStackTrace();
		}
		 */
	}
	
	/*
	
	private Vector loadInterestedGroups() {
		Vector groups = new Vector();
		
		this.result = this.database.query("SELECT communityInterests.communityID FROM communityInterests,community WHERE communityInterests.communityID=community.communityID AND communityInterests.status='A' AND community.kind='G' userID='"+this.ID+"'");
		if (this.result.size() > 0 ){
			String groupingID;
			
			for (int i=0; i< this.result.size(); i++){
				groupingID = (String)((Hashtable)this.result.get(i)).get("communityID");
				groups.add(groupingID);
			}
		}
		return groups;
	}
	 */
	/**
	 @return Groups objects wich this belongs.
	 */
	/*
	public Vector getGroups(){
		String groupID;
		Vector groupsV = new Vector();;
		Vector groups = this.loadInterestedGroups();
		try{
			for (int i=0 ; i< groups.size() ; i++){
				groupID = (String)groups.get(i);
				groupsV.add(new Group(groupID,this.database));
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,e);
		}
		return groupsV;
	}
	 */
	
	
	
	
	/**
	Metodo da interface User.
	 */
	public Vector getGroups(Course course){
		String groupID;
		Vector groupsV = new Vector();
		
		this.result = this.database.query("SELECT communityinterests.communityID FROM grouping, communityinterests, community WHERE "+
										" grouping.groupingID=communityinterests.communityID AND "+
										" grouping.groupingID=community.communityID AND "+
										" community.status='"+Community.ACTIVED+"' AND "+
										" communityinterests.status='"+Community.MEMBER_ACTIVED+"' AND "+
										" communityinterests.userID='"+this.ID+"' " );
										
		if (this.result.size() > 0 ){
			String groupingID;
			try{
				for (int i=0 ; i< this.result.size() ;i++ ){
					groupingID = (String)((Hashtable)this.result.get(i)).get("communityID");
					groupsV.add( new Group(groupingID,this.database));
				}
			}catch(Exception e){
				System.err.println(e.getMessage());
				Utility.log(Utility.ERROR_FILE_LOG,e);
			}
		}
		return groupsV;
	}
	
	
	/**
	public String inviteUserToGroup(String userID, String groupID) throws
		PersistentInformationException, GroupException{
		try{
			if ( Group.isAdministrator(this.ID,groupID,this.database) ){
				String invitationID = Utility.getNewID();
				this.database.update("INSERT INTO groupingInvitation (groupingInvitationID, groupingID, invitedID, inviterID) VALUES ('"+invitationID+"','"+groupID+"','"+userID+"','"+this.ID+"')");
				return invitationID;
			}else{
				throw new GroupException("Voce não administra esse grupo.");
			}
		}catch(SQLException sqle){
			throw new PersistentInformationException("Esse usuário já é membro desse grupo.");
		}
	}
	 
	
	public String inviteUserToGroup(User user, Group group) throws
		PersistentInformationException, GroupException{
		return this.inviteUserToGroup(user.getID(),group.getID());
	}
	 */
	
	
	/**
	 Note que se o usuraio for o administrador do grupo,
	 será excluído, alem da sua assinatura, o grupo, todas as assinaturas
	 dos outros membros e todos os outros relacionamentos do qual
	 o grupo participa.
	 
	 */
	/*
	public void signOutGroup(Group group)
		throws PersistentInformationException, GroupException{
		if ( ! group.isMember(this) ){
			throw new GroupException("Você não é membro desse grupo");
		}
		
		String groupID = group.getID();
		try{
			
			/* Remove a própria assinatura */
	/*	this.database.update("DELETE FROM groupingInterests WHERE userID='"+this.ID+"' AND groupingID='"+groupID+"'");
			
			if ( group.isAdministrator(this) ){
				/* Remove o grupo , e consequentemente todos os
				 relacionamentos do qual esse grupo faz parte.
				 */
				/* Remove todos os interesses por ele */
	/*		this.database.update("DELETE FROM groupingInterests WHERE groupingID='"+groupID+"'");
				
				/* Remove o grupo */
	/*				this.database.update("DELETE FROM grouping WHERE groupingID='"+groupID+"'");
				
				/**/
	/*				Document.deleteDocument(group,this.database);
				
				/* Atualiza o atributo groups. */
	/*			}
			this.groups.remove(group.getID());
			
		}catch(Exception e){
			throw new PersistentInformationException(e.getMessage());
		}
		
	}
	
	 */
    /**
	 @param courseID - Course wich this group is part.
	 @param name - The Group's name.
	 @param goal - The Group's goal.
	 @param visible - True if the Group's is public for this course.
	 */
	/*	public void createNewGroup(String courseID,  String name, String goal, boolean isVisible)
		throws CourseNotFoundException, GroupAlreadyExistsException , PersistentInformationException{
		Vector thisResult = new Vector();
		name = name.trim();
		thisResult = this.database.query("SELECT groupingID FROM grouping WHERE name='"+name+"'");
		if (thisResult.size() > 0 ){
			throw new GroupAlreadyExistsException("Já existe um grupo com esse nome: "+name);
		}
		if (!AbstractCourse.exists(courseID,database)){
			throw new CourseNotFoundException("Curso não encontrado. Impossível criar um grupo sem associa-lo a um curso.", courseID);
		}else {
			try{
				String visibility = (isVisible) ? "Y" : "N";
				String groupID = Utility.getNewID();
				name = Utility.transformToDatabase(name);
				goal = Utility.getTextField(goal);
				
				this.database.update("INSERT INTO grouping (groupingID, courseID, userID, name, goal, visible ) VALUES ('"+groupID+"','"+courseID+"','"+this.ID+"','"+name+"','"+goal+"','"+visibility+"')");
				this.database.update("INSERT INTO groupingInterests (userID,groupingID) VALUES ('"+this.ID+"','"+groupID+"')");
				/*Adiciona o novo grupo ao seus interesess */
	/*				this.groups.add(groupID);
			}catch(SQLException sqle){
				throw new PersistentInformationException("Erro ao criar grupo: "+sqle.getMessage());
			}
		}
    }
	
	 */
	/**
	 
	public void removeUserFromGroup(String userID, String groupID) throws
		PersistentInformationException, GroupException{
		try{
			if ( Group.isAdministrator(this.ID,groupID,this.database) ){
				this.database.update("DELETE FROM groupingInterests WHERE userID='"+userID+"' AND groupingID='"+groupID+"'");
			}else{
				throw new GroupException("Voce não administra esse grupo.");
			}
		}catch(SQLException sqle){
			throw new PersistentInformationException(sqle.getMessage());
		}
	}
	
	public void removeUserFromGroup(User user, Group group) throws
		PersistentInformationException, GroupException{
		try{
			String userID = user.getID();
			String groupID = group.getID();
			if ( Group.isAdministrator(this.ID,groupID,this.database) ){
				this.database.update("DELETE FROM groupingInterests WHERE userID='"+userID+"' AND groupingID='"+groupID+"'");
			}else{
				throw new GroupException("Voce não administra esse grupo.");
			}
		}catch(SQLException sqle){
			throw new PersistentInformationException(sqle.getMessage());
		}
	}
	 */
	
	/** Gets the ID of the user. */
	public String getID(){
		return this.ID;
	}
	
	/** Gets the login of the user. */
	public String getLogin(){
		return this.login;
	}
	
	/** Gets the name of the user. */
	public String getName(){
		return this.name;
	}
	
	/** Gets the first name of the user. */
	public String getFirstName(){
		int spacePosition = this.name.indexOf(" ");
		return (spacePosition != -1) ? this.name.substring(0,spacePosition) : this.name;
	}
	
	/** Gets the email of the user. */
	public String getEmail(){
		return this.email;
	}
	
	/** Gets the password of the user. */
	public String getPassword(){
		return this.password;
	}
	
	public boolean isPassword(String password){
		this.result = this.database.query("SELECT userid from user WHERE userid='"+this.ID+"' AND password=PASSWORD('"+password+"')");
		return (this.result.size() ==1);
	}
	
	
	/* Retorna todas as comunidades para o qual esse usuario foi convidado e efetivou o seu convite*/
	public Community[] getCommunityGuest(){
		this.result = this.database.query("SELECT communityID from communityguests WHERE guestID='"+this.ID+"'");
		int size = this.result.size();
		Community[] com = new Community[size];
		for (int i=0 ; i< size ; i++){
			try{
				com[i] = AbstractCommunity.getRealCommunity((String)((Hashtable)this.result.get(i)).get("communityID"),this.database);
			}catch(CommunityException ce){
				ce.printStackTrace();
			}
		}
		return com;
	}
	
	public boolean isGuest(Community community){
		this.result = this.database.query("SELECT * from communityguests WHERE guestID='"+this.ID+"' AND communityID='"+community.getID()+"'");
		return (result.size()>0);
	}
	
	/** Gets the kind of user in text. */
	public String getKindOfUser(){
		if (this.kindOfUserID.equals(User.PROFESSOR)){
			return "Professor Acadêmico";
		} else
		if (this.kindOfUserID.equals(User.STUDENT)){
			return "Estudante Acadêmico";
		} else
		if (this.kindOfUserID.equals(User.EXTERN_USER)){
			return "Usuário Web";
		} else
		if (this.kindOfUserID.equals(User.ADMINISTRATOR)){
			return "Administrador";
		} else return "Usuário Web";
	}
	
	public DatabaseLayer getDataBaseLayer(){
		return this.database;
	}
	
	/** Gets the kind of user. null if not found */
	public static String getKindOfUser(String userID, DatabaseLayer database) throws UserNotFoundException{
		Vector result = database.query("SELECT kindOfUserID FROM user WHERE userID='"+userID+"'");
		if (result.size() == 1)
			return (String)((Hashtable)result.get(0)).get("kindOfUserID");
		else throw new UserNotFoundException("User not found: "+userID);
	}
	
	/** Gets a user that can be a professor, student... */
	public static User getRealUser(String userID, DatabaseLayer database) throws UserNotFoundException{
		try {
			User user;
			String kindOfUser = AbstractUser.getKindOfUser(userID,database);
			if (kindOfUser.equals(User.STUDENT)){
				user = new Student(userID,database);
			} else if (kindOfUser.equals(User.PROFESSOR)){
				user = new Professor(userID,database);
			} else if (kindOfUser.equals(User.EXTERN_USER)){
				user = new ExternUser(userID,database);
			} else throw new UserNotFoundException("Tipo de usuario ("+kindOfUser+") desconhecido: "+userID);
			return user;
		} catch (UserNotFoundException nnfe){
			throw new UserNotFoundException("User not found: "+userID);
		}
	}
	
	/** Verifies if a user with userID exists */
	public static boolean exists(String userID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT userID FROM user WHERE userID='"+userID+"'");
		return (thisResult.size() == 1);
	}
	
	/** Verifies if a user exists with a given username and email.
	 * @return The userID or "".
	 */
	public static String exists(String username, String email, DatabaseLayer database){
		Vector thisResult = database.query("SELECT userID FROM user WHERE login='"+username+"' AND email='"+email+"'");
		return (thisResult.size() == 1) ? (String)((Hashtable)thisResult.get(0)).get("userID") : "";
	}
		
	/** Verifies if a there exists a username */
	public static boolean loginExists(String username, DatabaseLayer database){
		Vector thisResult = database.query("SELECT userID FROM user WHERE login='"+username+"'");
		return (thisResult.size() == 1);
	}
	
	/** Verifies if a there exists a username */
	public static String loginExists(DatabaseLayer database, String username){
		Vector thisResult = database.query("SELECT userID FROM user WHERE login='"+username+"'");
		return (thisResult.size() == 1) ? (String)((Hashtable)(thisResult.firstElement())).get("userID") : "";
	}
		
	/** Verifies if a there exists a username */
	public static boolean loginIsCorrect(String login, String password, DatabaseLayer database){
		Vector thisResult = database.query("SELECT userID,kindOfUserID FROM user WHERE login='"+login+"' AND password=PASSWORD('"+password+"')");
		return (thisResult.size() == 1);
	}
	
	/** Verifies if a there exists a username */
	public static String getEmail(String username, DatabaseLayer database){
		Vector thisResult = database.query("SELECT email FROM user WHERE login='"+username+"'");
		return (thisResult.size() == 1) ? (String)((Hashtable)(thisResult.firstElement())).get("email") : "";
	}
	
	public void setData(String login, String name, String email, String password)
		throws PersistentInformationException{
		try{
			login    = Utility.transformToDatabase(login);
			name     = Utility.transformToDatabase(name);
			email    = Utility.transformToDatabase(email);
			password = Utility.transformToDatabase(password);
			this.database.update("UPDATE user SET login='"+login+"' ,name='"+name+"', email='"+email+"', password=PASSWORD('"+password+"') WHERE userID='"+this.ID+"'");
			this.login    = login;
			this.name     = name;
			this.email    = email;
			
			this.result = this.database.query("SELECT password FROM user WHERE userid='"+this.ID+"'");
			this.password =(String) ((Hashtable)this.result.firstElement()).get("password");
		}catch(SQLException sqle){
			throw new PersistentInformationException("Can't set data");
		}
	}
	
	public void setData(String name, String email, String password)
		throws PersistentInformationException{
		try{
			name = Utility.transformToDatabase(name);
			email = Utility.transformToDatabase(email);
			password = Utility.transformToDatabase(password);
			this.database.update("UPDATE user SET name='"+name+"', email='"+email+"', password=PASSWORD('"+password+"') WHERE userID='"+this.ID+"'");
			this.name     = name;
			this.email    = email;
			this.result = this.database.query("SELECT password FROM user WHERE userid='"+this.ID+"'");
			this.password =(String) ((Hashtable)this.result.firstElement()).get("password");
			
		}catch(SQLException sqle){
			throw new PersistentInformationException("Can't set data");
		}
	}
	
	/**
	 * Set the user's information.
	 * @param name The user's name.
	 * @param email The user's email
	 */
	public void setData(String name, String email)
		throws PersistentInformationException{
		try{
			name = Utility.transformToDatabase(name);
			email = Utility.transformToDatabase(email);
			this.database.update("UPDATE user SET name='"+name+"', email='"+email+"' WHERE userID='"+this.ID+"'");
			this.name     = name;
			this.email    = email;
		}catch(SQLException sqle){
			throw new PersistentInformationException("Can't set data");
		}
	}
	
	/** Sets the name of a user. */
	public void setName(String newName)
		throws PersistentInformationException{
		newName = Utility.transformToDatabase(newName);
		if (this.getName() != newName){
			try{
				this.database.update("UPDATE user SET name='"+newName+"' WHERE userID='"+this.ID+"'");
				this.name = newName;
			}catch (SQLException sqle){
				throw new PersistentInformationException("Can't set user's name.");
			}
		}
	}
	
	/** Sets the email of a user. */
	public void setEmail(String newEmail)
		throws PersistentInformationException{
		newEmail = Utility.transformToDatabase(newEmail);
		if (this.getEmail() != newEmail){
			try{
				this.database.update("UPDATE user SET email='"+newEmail+"' WHERE userID='"+this.ID+"'");
				this.email = newEmail;
			} catch (SQLException sqle){
				throw new PersistentInformationException("Can't set user's email");
			}
		}
	}
	
	/** Sets the password of a user. */
	public void setPassword(String newPassword)
		throws PersistentInformationException{
		newPassword = Utility.transformToDatabase(newPassword);
		
			try{
				this.database.update("UPDATE user SET password=PASSWORD('"+newPassword+"') WHERE userID='"+this.ID+"'");
				this.result = this.database.query("SELECT password FROM user WHERE userid='"+this.ID+"'");
				this.password =(String) ((Hashtable)this.result.firstElement()).get("password");
				 
			} catch (SQLException sqle){
				throw new PersistentInformationException("Can't set user's password");
			}
		
	}
	
	/** Sets the login of a user. */
	public void setLogin(String newLogin)
		throws PersistentInformationException{
		newLogin = Utility.transformToDatabase(newLogin);
		try{
			this.database.update("UPDATE user SET login='"+newLogin+"' WHERE userID='"+this.ID+"'");
			this.login = newLogin;
		} catch (SQLException sqle){
			throw new PersistentInformationException("Can't set user's login");
		}
	}
	
	/*public void coursesChanged(){
		this.courses = this.loadInterestedCourses();
		System.out.println("cursos atualizados");
	 }*/
	/*
	private Vector loadInterestedCourses(){
		Vector interestedCourses = new Vector();
		String courseID;
		Boolean monitors;
		
		/* Searching for courses wich he has interests
		 */
	/*		this.result = this.database.query("SELECT courseIDFROM interests WHERE userID='"+this.ID+"'");
		if (this.result.size() > 0 ){
			for (int i=0 ;  i < result.size();i++ ){
				/* course´s ID */
	/*				courseID = (String)((Hashtable)this.result.get(i)).get("courseID");
				interestedCourses.add(courseID);
			}
		}
		return interestedCourses;
	}
	
	/*public Hashtable getInterestedCourses(){
		return this.courses;
	 }*/
	
	/**
	 * @return Todos os cursos(Course) do qual ele faz parte.
	 */
	/*	public Vector getCourses(){
		Vector courses = this.loadInterestedCourses();
		Vector cs = new Vector();
		Course c;
		String courseID;
		
		try{
			for (int i=0 ; i< courses.size() ; i++){
				courseID = (String)courses.get(i);
				c = AbstractCourse.getRealCourse(courseID,this.database);
				cs.add(c);
			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,ex);
		}
		return cs;
	}
	
	
	
	/**
	 * @return The Course IDs wich he has interests.
	 */
	/*	public Vector getCourseIDs(){
		return this.loadInterestedCourses();
	}
	
	/**
	 * @return true if he has interests for courseID
	 */
	/*
	public boolean hasCourseInterests(Course course){
		return this.hasCourseInterests(course.getID());
	}
	
	public boolean hasCourseInterests(String courseID){
		return courses.containsKey(courseID);
	}
	
	/**
	 * @return true if he has interests for groupID
	 */
	/*
	public boolean hasGroupInterests(String groupID){
		return this.groups.contains(groupID);	//usa o mesmo criterio do metodo equals.
	}
	
	public boolean hasGroupInterests(Group group){
		return this.groups.contains(group.getID());	//usa o mesmo criterio do metodo equals.
	}
	
	/**
	 * Set the students's interests.
	 * @param courseIDs Hashtable whose struct is: [CourseID],[{true,false}], where
	 * True means: This Student interests for course.
	 * False means: This Student not interests for course.
	 */
	/*
	public void setInterests(Hashtable courseIDs)
		throws PersistentInformationException{
		Enumeration keys = courseIDs.keys();
		Vector coursesToRemove = new Vector();
		String courseID;
		try{
			while (keys.hasMoreElements()){
				/* Take the course´s ID */
	/*
				courseID = (String)keys.nextElement();
				courseID = Utility.transformToDatabase(courseID);
				
				if (((Boolean)courseIDs.get(courseID)).booleanValue()){
					/* If the value equals true and it not belong to this.courses,
					 then ADD.
					 */
	/*
					if (this.courses.get(courseID) == null){
						this.database.update("INSERT INTO interests VALUES('"+this.ID+"','"+courseID+"','N')");
						this.courses.put(courseID,new Boolean(false));
					}
				}else{
					/* If the value equals false and it belong to this.courses,
					 then REMOVE.
					 */
	/*
					if (this.courses.get(courseID) != null){
						/* Add to Vector for minimize database acess */
//						coursesToRemove.add(courseID);
	/*
					}
				}
			}
			if (coursesToRemove.size() > 0){
				/* Create only one big query  */
	/*
				String query="DELETE FROM interests WHERE userID=\""+this.ID+"\" AND (";
				for (int i=0; i<coursesToRemove.size()-1; i++){
					query+="courseID=\""+(String)coursesToRemove.get(i)+"\" OR ";
				}
				query+="courseID=\""+(String)coursesToRemove.lastElement()+"\")";
				this.database.update(query);
				/* If the query sucess, then update object state */
	/*
				for (int i=0 ; i < coursesToRemove.size() ; i++){
					this.courses.remove(coursesToRemove.get(i));
				}
			}
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao atualizar interesses: "+sqle.getMessage());
		}
	}
	
	public void setInterests(String courseIDs[])
		throws PersistentInformationException{
		try{
			this.database.update("DELETE FROM interests WHERE userID='"+this.ID+"'");
			this.courses = new Hashtable();
			for (int i=0 ; i<courseIDs.length ; i++){
				if (this.courses.get(courseIDs[i]) == null){
					this.database.update("INSERT INTO interests VALUES('"+this.ID+"','"+Utility.transformToDatabase(courseIDs[i])+"','N')");
					this.courses.put(courseIDs[i],new Boolean(false));
				}
			}
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao atualizar interesses: "+sqle.getMessage());
		}
		
	}
	
	public void setInterests(Vector courseIDs)
		throws PersistentInformationException{
		String ID;
		try{
			this.database.update("DELETE FROM interests WHERE userID='"+this.ID+"'");
			this.courses = new Hashtable();
			for (int i=0 ; i<courseIDs.size() ; i++){
				ID = (String)courseIDs.get(i);
				ID = Utility.transformToDatabase(ID);
				if (this.courses.get(ID) == null){
					this.database.update("INSERT INTO interests VALUES('"+this.ID+"','"+ID+"','N')");
					this.courses.put(ID,new Boolean(false));
				}
			}
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao atualizar interesses: "+sqle.getMessage());
		}
	}
	 */
	
	/*********************************************************************
	 * 				IMPLEMENTACAO DA INTERFACE USER
	 *********************************************************************/
	
	
	/**
	@return Todas as disciplinas que sao publicas.
	 */
	public Vector getPublicDisciplines(){
		Vector disciplines = new Vector();
		String communityID;
		Hashtable hash;
		Discipline discipline;
		
		this.result = this.database.query("SELECT discipline.communityID from discipline,community "+
							"WHERE community.communityID=discipline.communityID "+
							"AND community.visibility='"+Visibility.PUBLIC+"' AND community.status='"+Community.ACTIVED+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				discipline = new Discipline( communityID ,this.database);
				disciplines.add( discipline );
			}catch(CommunityException ce){
				ce.printStackTrace();
			}
		}
		
		return disciplines;
	}
		
	public boolean isMember(Community community){
		
		this.result =  this.database.query("SELECT communityID FROM communityinterests WHERE "+
								" communityID='"+community.getID()+"' AND "+
								" communityinterests.status='"+Community.MEMBER_ACTIVED+"' AND "+
						   		" userID='"+this.ID+"' ");
		if (this.result.size()>0) return true;
		
		this.result =  this.database.query("SELECT communityID FROM community WHERE "+
						   		" userID='"+this.ID+"' AND communityID='"+community.getID()+"' ");
		if (this.result.size()>0) return true;
		
		return false;
	}
	
	public boolean isWaitingAuthorization(Community community){
		this.result =  this.database.query("SELECT communityID FROM communityinterests WHERE "+
								" communityID='"+community.getID()+"' AND "+
								" communityinterests.status='"+Community.WAITING+"' AND "+
						   		" userID='"+this.ID+"' ");
		return (this.result.size()>0);
	}
	
	public boolean isResponsible(Community community){
		try{
			return community.getResponsible().getID().equals(this.ID);
		}catch(UserNotFoundException unfe){
			return false;
		}
	}
	
	public void acceptMember(Community community , User user)
	 throws PolicyException{
		if (isResponsible(community)){
			try{
				this.database.update("UPDATE communityinterests SET status='"+Community.MEMBER_ACTIVED+"' WHERE userID='"+user.getID()+"' AND communityID='"+community.getID()+"'");
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}else{
			throw new PolicyException("Você não tem autorização para realizar essa tarefa.");
		}
	}
	
	public void banMember(Community community , User user)
	throws PolicyException{
		if (isResponsible(community)){
			try{
				this.database.update("DELETE FROM communityinterests WHERE userID='"+user.getID()+"' AND communityID='"+community.getID()+"'");
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}else{
			throw new PolicyException("Você não tem autorização para realizar essa tarefa.");
		}
	}
	
	public void inviteUser(Community community , User user)
	throws PolicyException{
		if (isResponsible(community)){
			try{
				this.database.update("INSERT INTO communityinvitation (communityinvitationID, userID, communityID ) VALUES ('"+Utility.getNewID()+"', '"+user.getID()+"' ,'"+community.getID()+"')");
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}else{
			throw new PolicyException("Você não tem autorização para realizar essa tarefa.");
		}
	}
	
	public void removeCommunity(Community community){
	}
	
	public void proposeNewPublicExtraCourse(String title, String description,  int visibility, int association, String kind, String categoryID, String subcategoryID,String program, int hours, String bibliography){
		ExtraCourse.createAPublicExtraCourseInDatabase(this.database,this,title,description,visibility,association,kind,categoryID,subcategoryID,program,hours,bibliography);
	}
		
	/**
	@return Todas os cursos extra curriculares que sao publicas.
	 */
	public Vector getPublicExtraCourses(){
		Vector extraCourses = new Vector();
		String communityID;
		Hashtable hash;
		ExtraCourse extraCourse;
		
		this.result = this.database.query("SELECT extracourse.communityID from extracourse,community "+
							"WHERE community.communityID=extracourse.communityID "+
							"AND community.visibility='"+Visibility.PUBLIC+"' AND community.status='"+Community.ACTIVED+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				extraCourse = new ExtraCourse( communityID ,this.database);
				extraCourses.add( extraCourse );
			}catch(CommunityException ce){
				ce.printStackTrace();
			}
		}
		return extraCourses;
	}
		
	public Vector getPublicGroups(){
		Vector groups = new Vector();
		String communityID;
		Hashtable hash;
		Group group;
		
		this.result = this.database.query("SELECT grouping.groupingID from grouping,community "+
							"WHERE community.communityID=grouping.groupingID "+
							"AND community.visibility='"+Visibility.PUBLIC+"' AND community.status='"+Community.ACTIVED+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("groupingID");
				group = new Group( communityID ,this.database);
				groups.add( group );
			}catch(CommunityException ce){
				ce.printStackTrace();
			}
		}
		return groups;
	}
	
	public void requestAssociation(Community community){
		int association = community.getAssociationType();
		String userStatus;
		if (association == Association.OPENED){
			userStatus = Community.MEMBER_ACTIVED;
		}else if (association == Association.SEMI_OPENED){
			userStatus = Community.MEMBER_WAITING;
		}else{
			System.err.println("AbstractUser, requestAssociation(Community community)- Associação Impossível");
			return ;
		}
		try{
			this.database.update("INSERT INTO communityinterests (userID,communityID,status) "+
		 				 	"VALUES('"+this.ID+"','"+community.getID()+"','"+userStatus+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
	public void removeAssociation(Community community){
		try{
			this.database.update("DELETE FROM communityinterests WHERE userID='"+this.ID+"' AND communityID='"+community.getID()+"'");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
	/* Retorna todas os grupos que esse usuario esta associado */
	public Vector getGroups(){
		Vector groups = new Vector();
		String communityID;
		Hashtable hash;
		Group group;
		
		this.result = this.database.query("select grouping.groupingID from grouping,communityinterests where "+
 				" communityinterests.communityID = grouping.groupingID AND "+
				" communityinterests.status='"+Community.MEMBER_ACTIVED+"' AND "+
 				" communityinterests.userID='"+this.ID+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("groupingID");
				group = new Group( communityID ,this.database);
				groups.add( group );
			}catch(CommunityException cnfe){
				cnfe.printStackTrace();
			}
		}
		
		this.result = this.database.query("select community.communityID from community,grouping where "+
				" grouping.groupingID = community.communityID AND "+
				" community.status='"+Community.ACTIVED+"' AND "+
 				" community.userID='"+this.ID+"' ");
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				group = new Group( communityID ,this.database);
				groups.add( group );
			}catch(CommunityException cnfe){
				cnfe.printStackTrace();
			}
		}
		return groups;
	}
	
	/* Retorna todas os ExtraCourses que esse usuario esta associado */
	public Vector getExtraCourses(){
		Vector extraCourses = new Vector();
		String communityID;
		Hashtable hash;
		ExtraCourse extraCourse;
		
		this.result = this.database.query("select extracourse.communityID from extracourse,communityinterests where "+
 				" communityinterests.communityID = extracourse.communityID AND "+
				" communityinterests.status='"+Community.MEMBER_ACTIVED+"' AND "+
 				" communityinterests.userID='"+this.ID+"'");
		
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				extraCourse = new ExtraCourse( communityID ,this.database);
				extraCourses.add( extraCourse );
			}catch(CommunityException cnfe){
				cnfe.printStackTrace();
			}
		}
		
		this.result = this.database.query("select community.communityID from community,extracourse where "+
				" extracourse.communityID = community.communityID AND "+
				" community.status='"+Community.ACTIVED+"' AND "+
 				" community.userID='"+this.ID+"' ");
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				extraCourse = new ExtraCourse( communityID ,this.database);
				extraCourses.add( extraCourse );
			}catch(CommunityException cnfe){
				cnfe.printStackTrace();
			}
		}
		
		
		return extraCourses;
	}
	
	public static void main(String[] args){
		String login = AbstractUser.loginExists(new DatabaseLayer(),"mardcellojunior");
		System.out.println();
	}
}
