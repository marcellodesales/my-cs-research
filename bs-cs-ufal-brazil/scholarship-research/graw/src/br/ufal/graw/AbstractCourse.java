package br.ufal.graw;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Date;

import java.sql.SQLException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Group;

import br.ufal.graw.exception.CourseNotFoundException;
import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.GroupNotFoundException;

import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.UserException;
import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.UserLoginFormatException;
import br.ufal.graw.exception.DepartmentNotFoundException;
import br.ufal.graw.exception.InstituteNotFoundException;
import br.ufal.graw.exception.GrawException;


/**
	Essa eh a classe base para todos os cursos.
 */

public abstract class AbstractCourse extends AbstractCommunity implements Course{
		
	
	protected String program;
	protected String hours;
	protected String bibliography;
	
	protected String academicCourseID;
	
	protected String departmentID;
	
	protected String instituteID;
	
	public AbstractCourse(String courseID, DatabaseLayer database)
		throws CommunityException{
		super(courseID,database);
		
		this.result = this.database.query("SELECT * FROM course WHERE communityID='"+courseID+"'");
		if (this.result.size() == 0)
			throw new CourseNotFoundException("Curso não encontrado");
		else {
			this.initObject((Hashtable)result.firstElement());
		}
	}
		
	/** Initializes the state of the discipline */
	private void initObject(Hashtable data){
		this.program      = (String)data.get("program");
		this.hours        = (String)data.get("hours");
		this.bibliography = (String)data.get("bibliography");
	}
	
	protected static String createACourseInDatabase(DatabaseLayer database, User responsible, String title, String description, int visibility, int association, String kind, String categoryID, String subcategoryID, String program, int hours, String bibliography){
		/*Chama o metodo da classe mae */
		String communityID = AbstractCommunity.createACommunityInDatabase(database,responsible,title,description,visibility,association,kind,categoryID,subcategoryID);
		try{
			database.update("INSERT INTO course (communityID, program, hours, bibliography ) "+
							"VALUES ('"+communityID+"','"+program+"','"+hours+"','"+bibliography+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return communityID;
	}
	
	public String getProgram(){
		return this.program;
	}
	
	public void setProgram(String newProgram)
	throws PersistentInformationException{
		newProgram = Utility.transformToDatabase(newProgram);
		
        try{
           	this.database.update("UPDATE course SET program='"+newProgram+"' WHERE communityID='"+this.ID+"'");
           	this.program = newProgram;
        } catch (SQLException sqle){
          	throw new PersistentInformationException("Não foi possível atualizar o programa desse curso.");
        }
	}
	
	
	public void setHours(String newHours)
	throws PersistentInformationException{
		newHours = Utility.transformToDatabase(newHours);
		
        try{
           	this.database.update("UPDATE course SET hours='"+newHours+"' WHERE communityID='"+this.ID+"'");
           	this.hours = newHours;
        } catch (SQLException sqle){
			sqle.printStackTrace();
          	throw new PersistentInformationException("Não foi possível atualizar a carga horária desse curso.");
        }
	}
	
	public void setBibliography(String newBibliography)
	throws PersistentInformationException{
		newBibliography = Utility.transformToDatabase(newBibliography);
		
        try{
           	this.database.update("UPDATE course SET bibliography='"+newBibliography+"' WHERE communityID='"+this.ID+"'");
           	this.bibliography = newBibliography;
        } catch (SQLException sqle){
			sqle.printStackTrace();
          	throw new PersistentInformationException("Não foi possível atualizar a bibliografia desse curso.");
        }
	}
	
	
	
	public String getHours(){
		return this.hours;
	}
	
	public String getBibliography(){
		return this.bibliography;
	}
	
	/** Verifies if a course with courseID exists. */
	public static boolean exists(String courseID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT communityID FROM course WHERE communityID='"+courseID+"'");
		return (thisResult.size() == 1);
	}
	
	/*
	public int getQuantStudent(){
		this.result = database.query("SELECT count(*) FROM interests WHERE courseID='"+this.ID+"'");
		String res = (String)((Hashtable)this.result.get(0)).get("count(*)");
		return Integer.parseInt(res);
	}
	
	public Enumeration getMembers() throws UserNotFoundException{
		Vector users = new Vector();
		this.result = database.query("SELECT userID FROM interests WHERE courseID='"+this.ID+"'");
		if (this.result.size() == 0 ){
			return users.elements();
		}
		String userID;
		for (int i=0 ; i< this.result.size() ;i++ ){
			userID = (String)((Hashtable)this.result.get(i)).get("userID");
			users.add(AbstractUser.getRealUser(userID,this.database));
		}
		return users.elements();
	}
	 */
	public Vector getGroups() throws GroupNotFoundException{
		Vector groups = new Vector();
		this.result = database.query("SELECT grouping.groupingID FROM grouping,community WHERE "+
									" grouping.groupingID = community.communityID AND "+
									" community.status='"+Community.ACTIVED+"' AND "+
									" grouping.courseID='"+this.ID+"'");
		if (this.result.size() == 0 ){
			return groups;
		}
		String groupID;
		try{
			for (int i=0 ; i< this.result.size() ;i++ ){
				groupID = (String)((Hashtable)this.result.get(i)).get("groupingID");
				groups.add( new Group(groupID,this.database));
			}
		}catch (CommunityException gnfe){
			throw new GroupNotFoundException("AbstractCourse - getGroups() - Grupo não encontrado. Banco de dados inconsistente");
		}
		return groups;
	}
	/*
	public Enumeration getVisibleGroups() throws GroupNotFoundException{
		Vector groups = new Vector();
		this.result = database.query("SELECT groupingID FROM grouping WHERE courseID='"+this.ID+"' AND visible='Y'");
		if (this.result.size() == 0 ){
			return groups.elements();
		}
		String groupID;
		try{
			for (int i=0 ; i< this.result.size() ;i++ ){
				groupID = (String)((Hashtable)this.result.get(i)).get("groupingID");
				groups.add( new Group(groupID,this.database));
			}
		}catch (GroupNotFoundException gnfe){
			throw new GroupNotFoundException("Grupo não encontrado. Banco de dados inconsistente");
		}
		return groups.elements();
	}
	 */
	
	
	/** Gets the Students that monitor this course. */
	/*
    public Vector getMonitors(){
		Vector monitors = new Vector();
    	this.result     = this.database.query("SELECT student.userID FROM interests, student WHERE student.userID =interests.userID AND interests.courseID ='"+this.ID+"' AND monitors='Y'");
		try {
			if (this.result.size() > 0){
				String monitorID;
				Student monitor;
	    		for (int i=0; i < this.result.size(); i++){
          			monitorID = (String)((Hashtable)this.result.get(i)).get("userID");
					monitor = new Student(monitorID,this.database);
			  		monitors.add(monitor);
	    	  	}
        	}
		} catch (UserException ue){
			System.out.println(ue.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,ue);
		}
		return monitors;
    }
	
	/** Gets the Students that are interested in this Course. */
	/*
    public Vector getStudents(){
		Vector students = new Vector();
    	this.result = this.database.query("SELECT student.userID FROM interests, student WHERE student.userID=interests.userID AND interests.courseID ='"+this.ID+"'");
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String studentID = (String)((Hashtable)this.result.get(i)).get("userID");
					Student student    = new Student(studentID,this.database);
			  		students.add(student);
	    	  	}
        	}
		} catch (UserException ue){
			System.out.println(ue.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,ue);
		}
		return students;
    }
	
	/** Gets the Professors that teach the Course. */
	/*
    public Vector getProfessors(){
		Vector professors = new Vector();
    	this.result = this.database.query("SELECT professor.userID FROM teaches, professor WHERE professor.userID=teaches.userID AND teaches.courseID='"+this.ID+"'");
		try {
			if (this.result.size() > 0){
				String professorID;
				Professor professor;
		    	for (int i=0; i < this.result.size(); i++){
        	  		professorID  = (String)((Hashtable)this.result.get(i)).get("userID");
					professor = new Professor(professorID,this.database);
		  			professors.add(professor);
	      		}
        	}
		} catch (UserException ue){
			System.out.println(ue.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,ue);
		}
		return professors;
    }
	
	public int getQuantLinks(){
		this.result = database.query("SELECT count(*) FROM link WHERE courseID='"+this.ID+"'");
		String res = (String)((Hashtable)this.result.get(0)).get("count(*)");
		return Integer.parseInt(res);
	}
	
	/** Gets the Links of the Course. */
	/*
    public Vector getLinks(){
		Vector links = new Vector();
    	this.result  = this.database.query("SELECT linkID FROM link WHERE courseID='"+this.ID+"' AND groupingID=''");
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String linkID = (String)((Hashtable)this.result.get(i)).get("linkID");
					Link link     = new Link(linkID,this.database);
		  			links.add(link);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			System.out.println(rnfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,rnfe);
		}
		return links;
    }
	
	/** Gets the Links of the Course. */
	/*
    public Vector getLinks(int offset, int limit){
		Vector links = new Vector();
    	this.result  = this.database.query("SELECT linkID FROM link WHERE courseID='"+this.ID+"' AND groupingID='' LIMIT "+offset+","+limit);
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String linkID = (String)((Hashtable)this.result.get(i)).get("linkID");
					Link link     = new Link(linkID,this.database);
		  			links.add(link);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			System.out.println(rnfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,rnfe);
		}
		return links;
    }
	
	public int getQuantMessages(){
		this.result = database.query("SELECT count(*) FROM message WHERE courseID='"+this.ID+"' AND ownerMessageID=''");
		String res = (String)((Hashtable)this.result.get(0)).get("count(*)");
		return Integer.parseInt(res);
	}
	
	public int getQuantDocuments(){
		this.result = database.query("SELECT count(*) FROM document WHERE courseID='"+this.ID+"'");
		String res = (String)((Hashtable)this.result.get(0)).get("count(*)");
		return Integer.parseInt(res);
	}

	/** Gets the Documents of the Course. */
	/*
    public Vector getDocuments(){
		Vector documents = new Vector();
    	this.result      = this.database.query("SELECT documentID FROM document WHERE courseID='"+this.ID+"' AND groupingID=''");
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String documentID = (String)((Hashtable)this.result.get(i)).get("documentID");
					Document document = new Document(documentID,this.database);
		  			documents.add(document);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			System.out.println(rnfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,rnfe);
		}
		return documents;
    }

	/** Gets the Documents of the Course. */
	/*
    public Vector getDocuments(int offset, int limit){
		Vector documents = new Vector();
    	this.result      = this.database.query("SELECT documentID FROM document WHERE courseID='"+this.ID+"' AND groupingID='' LIMIT "+offset+","+limit);
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String documentID = (String)((Hashtable)this.result.get(i)).get("documentID");
					Document document = new Document(documentID,this.database);
		  			documents.add(document);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			System.out.println(rnfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,rnfe);
		}
		return documents;
    }
	
	/**
	 @return Uma instancia de algum filho da classe course.
	*/
	/*
	public static Course getRealCourse(String courseID , DatabaseLayer database) throws CourseNotFoundException{
		String kindOfCourse = AbstractCourse.getKindOfCourse(courseID,database);
		Course course;
		if ( kindOfCourse.equals(Course.DISCIPLINE) ){
			course = new Discipline(courseID,database);
		}else if ( kindOfCourse.equals(Course.EXTRA_COURSE) ){
			course = new ExtraCourse(courseID,database);
		}else{
			throw new CourseNotFoundException("Tipo de curso desconhecido.");
		}
		return course;
	}
	public static String getKindOfCourse(String courseID , DatabaseLayer database) throws CourseNotFoundException{
		Vector result;
		result = database.query("SELECT communityID,kindOfCourseID FROM course WHERE courseID='"+courseID+"'");
		if (result.size() == 0 ){
			throw new CourseNotFoundException("Curso nao encontrado.",courseID);
		}
		Hashtable hash = ((Hashtable)result.firstElement());
		String kinfOfCourse = (String)hash.get("kindOfCourseID");
		if (kinfOfCourse==null){
			throw new CourseNotFoundException("Tipo de Curso não especificado.",courseID);
		}else{
			return kinfOfCourse;
		}
	}
	
	/*public static void main(){
		try{
		
		}catch(Exception e){
		}
	 }*/
	
	public AcademicCourse getAcademicCourse() throws GrawException{
		return new AcademicCourse(this.academicCourseID,this.database);
	}
	
	public Department getDepartment() throws DepartmentNotFoundException{
		return new Department(this.departmentID,this.database);
	}
	
	public Institute getInstitute() throws InstituteNotFoundException{
		return new Institute(this.instituteID,this.database);
	}
	
	
}
