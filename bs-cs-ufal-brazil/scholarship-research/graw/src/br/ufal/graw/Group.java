package br.ufal.graw;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;

import java.sql.SQLException;

import br.ufal.graw.exception.GroupAlreadyExistsException;
import br.ufal.graw.exception.GroupNotFoundException;
import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.CourseNotFoundException;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.PersistentInformationException;

public class Group extends AbstractCommunity implements ResourceManipulator{
	
	protected String courseID;
	
	protected String goal;
	
	public Group(String ID,DatabaseLayer database) throws CommunityException {
		super(ID,database);
		
		this.result = this.database.query("SELECT * FROM grouping WHERE grouping.groupingID='"+ID+"'");
		if (this.result.size() == 0 ){
			throw new CommunityException("Grupo nao encontrada com ID= "+ID);
		}else {
			this.ID = ID;
			this.initObject((Hashtable)this.result.firstElement());
		}
	}
	
	private void initObject(Hashtable data){
		this.goal     = (String)data.get("goal");
		this.courseID = (String)data.get("courseID");
	}
	
	public String getGoal(){
		return this.goal;
	}
	
	public void setGoal(String newGoal)
	throws PersistentInformationException{
		newGoal = Utility.transformToDatabase(newGoal);
		
        try{
           	this.database.update("UPDATE grouping SET goal='"+newGoal+"' WHERE groupingID ='"+this.ID+"'");
           	this.goal = newGoal;
        } catch (SQLException sqle){
			sqle.printStackTrace();
          	throw new PersistentInformationException("Não foi possível atualizar o objetivo desse grupo.");
        }
	}
	
	public static String createAGroupInDatabase(DatabaseLayer database, User responsible, String title, String description, int visibility, int association, String kind, String categoryID, String subcategoryID, String goal, Community course){
		String communityID = createACommunityInDatabase(database,responsible,title,description,visibility,association,kind,categoryID,subcategoryID);
		try{
			database.update("INSERT INTO grouping (communityID, goal) "+
								"VALUES ('"+communityID+"','"+goal+"')");
			database.update("INSERT INTO rel_grouping_course (groupingID,courseID) "+
								"VALUES ('"+communityID+"','"+course.getID()+"')");
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return communityID;
	}
	
	/**
	@return Null se esse grupo naum eh subordinado a nenhum grupo.
	@return Um curso se ele e subordinado.
	 */
	public Course getCourse(){
		this.result = this.database.query("SELECT courseID FROM grouping WHERE groupingID='"+this.ID+"' AND courseID IS NOT NULL ");
		if (this.result.size()==1){
			String courseID = (String)((Hashtable)this.result.firstElement()).get("courseID");
			try{
				return (Course)AbstractCommunity.getRealCommunity(courseID,this.database);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/** Gets the administrator of the group -> instance of professor|Student|Guest... */
	/*
	public User getAdmin(){
		User user = null;
		try {
			user = AbstractUser.getRealUser(this.userID, this.database);
		} catch (UserNotFoundException unfe){
			System.err.println(unfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,unfe);
		}
		return user;
	}
	
	/** Gets the Group code. */
	/*
	public String getID(){
		return this.ID;
	}
	
	/** Gets the Group code. */
	/*
	public String getCourseID(){
		return this.courseID;
	}
	
	/** Gets the Group code. */
	/*
	public Course getCourse() throws CourseNotFoundException{
		return AbstractCourse.getRealCourse(this.courseID,this.database);
	}
	
	/** Gets the Group name. */
	/*
	public String getAdminID(){
		return this.userID;
	}
	
	/** Gets the Group name. */
	/*
	public String getName(){
		return this.name;
	}
	
	/** Is the group visible. */
	/*
	public boolean isVisible(){
		return this.visible;
	}
	
	public  boolean isMember(User user){
		result = database.query("SELECT groupingID FROM groupingInterests WHERE groupingID='"+this.ID+"' AND userID='"+user.getID()+"'");
		return ( result.size() != 0 );
	}
	
	public  boolean isAdministrator(User user){
		return user.getID().equals(this.userID);
	}
	
	public static boolean isAdministrator(String userID , String groupID , DatabaseLayer database){
		Vector result = new Vector();
		result = database.query("SELECT groupingID FROM grouping WHERE groupingID='"+groupID+"' AND userID='"+userID+"'");
		return ( result.size() != 0 );
	}
	
	public static boolean isAdministrator(User user , Course group , DatabaseLayer database){
		Vector result = new Vector();
		
		result = database.query("SELECT groupingID FROM grouping WHERE groupingID='"+group.getID()+"' AND userID='"+user.getID()+"'");
		return ( result.size() != 0 );
	}

	/** Gets the Group goal. */
	/*
	public String getGoal(){
		return this.goal;
	}
	
	public Enumeration getMembers()
		throws UserNotFoundException{
		this.result = database.query("SELECT userID FROM groupingInterests WHERE groupingID='"+this.ID+"'");
		if (this.result.size() == 0 ){
			throw new UserNotFoundException("Nenhum usuário encontrado.");
		}
		String userID;
		Vector users = new Vector();
		for (int i=0 ; i< this.result.size() ;i++ ){
			userID = (String)((Hashtable)this.result.get(i)).get("userID");
			users.add(AbstractUser.getRealUser(userID,this.database));
		}
		return users.elements();
	}
	
	
	
	
	
	/** Verifies if a group with groupID exists. */
	
	public static boolean exists(String groupID, DatabaseLayer database){
		Vector thisResult = new Vector();
		thisResult = database.query("SELECT groupingID FROM grouping WHERE groupingID='"+groupID+"'");
		return (thisResult.size() == 1);
	}
	
	/*protected void printData(){
		System.out.println("----------------------------------------------");
		System.out.println("GroupID  = " + this.getID());
		System.out.println("CourseID = " + this.getCourseID());
		System.out.println("UserID   = " + this.getAdminID());
		System.out.println("name     = " + this.getName());
		System.out.println("goal     = " + this.getGoal());
		System.out.println("Is Visible = " + this.isVisible());
		System.out.println("----------------------------------------------");
	 }*/
	/*
	public static void main(String args[]){
		DatabaseLayer data = new DatabaseLayer();
		try{
			Professor prof = new Professor("1",data);
			//Group group = Group.createNewGroup("18726128",prof.getID(),"JavaUFAL","Desmistificar java",true,data);
			Group group = new Group("1001363857500",data);
			User user ;
			Enumeration g = prof.getGroups();
			//Group group = (Group)g.nextElement();
			
			
			Enumeration e = group.getMembers();
			while (e.hasMoreElements()){
				user = (User)e.nextElement();
				System.out.println(user.getName());
			}
			//prof.addUserToGroup("54321",group.getID());
			prof.removeUserFromGroup("54321",group.getID());
			
			e = group.getMembers();
			while (e.hasMoreElements()){
				user = (User)e.nextElement();
				System.out.println(user.getName());
			}
			
			
			/*
			group.printData();
			 */
	/*
		}catch(Exception e){
			System.err.println(e.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,e);
			
		}
    }
	 */
	
}
