package br.ufal.graw;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Date;

import java.sql.SQLException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.exception.DisciplineDuplicateCodeException;
//import br.ufal.graw.exception.DisciplineNotFoundException;

import br.ufal.graw.exception.CourseNotFoundException;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.UserException;
import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.UserLoginFormatException;
import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.GrawException;
import br.ufal.graw.exception.UserNotFoundException;

/**
	Discipline is what a professor can teach, or a student is interested to
*/
public class Discipline extends AbstractCourse{
	
	protected String disciplineCode;
	
	/** Attempt to retrieve the data from database and create a instance: a Discipline.
	If sucess , the hasCreated method return true, otherwise return false.
	@param disciplineCode The discipline's code.
	@param database The database abstraction.
	 */
	public Discipline(String disciplineID, DatabaseLayer database)
		throws CommunityException{
		super(disciplineID,database);
		
		this.result = this.database.query("SELECT * FROM discipline WHERE communityID='"+disciplineID+"'");
		if (this.result.size() == 0)
			throw new CourseNotFoundException("Disciplina não encontrada.");
		else {
			this.disciplineCode   = (String)((Hashtable)result.firstElement()).get("disciplineCode");
			this.academicCourseID = (String)((Hashtable)result.firstElement()).get("academicCourseID");
			this.departmentID     = (String)((Hashtable)result.firstElement()).get("departmentID");
			this.instituteID      = (String)((Hashtable)result.firstElement()).get("instituteID");
			
		}
	}

	public String getAcademicCourseID(){
		return this.academicCourseID;
	}
	
	public String getDepartmentID(){
		return this.departmentID;
	}
	
	public String getInstituteID(){
		return this.instituteID;
	}
	
	public static String createADisciplineInDatabase(DatabaseLayer database, User responsible, String title, String description, int visibility, int association, String kind, String categoryID, String subcategoryID, String program, int hours, String bibliography, String code, AcademicCourse academicCourse){
		String communityID = AbstractCourse.createACourseInDatabase(database,responsible,title,description,visibility,association,kind,categoryID,subcategoryID,program,hours,bibliography);
		try{
			String instituteID = academicCourse.getInstitute().getID();
			String departmentID = academicCourse.getDepartment().getID();
			database.update("INSERT INTO discipline (communityID,disciplineCode,instituteID,departmentID,academicCourseID) "+
							"VALUES ('"+communityID+"','"+code+"','"+instituteID+"','"+departmentID+"','"+academicCourse.getID()+"')");
		}catch(Exception e){
			e.printStackTrace();
		}
		return communityID;
	}
	
	public static boolean exists(String disciplineID, DatabaseLayer database){
		Vector result = database.query("SELECT courseID FROM discipline WHERE courseID='"+disciplineID+"'");
		return (result.size() == 1);
	}

	/** Gets the discipline's code. */
	public String getCode(){
		return this.disciplineCode;
	}

	/*public void setData(String code, String name, String description)throws PersistentInformationException{
		super.setData(name,description);
		this.setCode(code);
	}
	 */
	
	
	public int getQuantUsers(){
		int quant = super.getQuantUsers();
		int monitors,teachers ;
		this.result = this.database.query("SELECT count(monitors.userID) FROM monitors,community WHERE monitors.disciplineid=community.communityid AND "+
											  " monitors.disciplineID='"+this.ID+"' AND status='"+Community.ACTIVED+"'");
		monitors = Integer.parseInt((String)((Hashtable)this.result.firstElement()).get("count(monitors.userID)"));
		this.result = this.database.query("SELECT count(teaches.userID) FROM teaches,community WHERE teaches.communityid=community.communityid AND "+
											  " teaches.communityID='"+this.ID+"' AND status='"+Community.ACTIVED+"'");
		teachers = Integer.parseInt((String)((Hashtable)this.result.firstElement()).get("count(teaches.userID)"));
		
		return quant+monitors+teachers;
	}
	
	public void setCode(String code)
	throws PersistentInformationException{
		code = Utility.transformToDatabase(code);
		
        try{
           	this.database.update("UPDATE discipline SET disciplineCode='"+code+"' WHERE communityID='"+this.ID+"'");
           	this.disciplineCode = code;
        } catch (SQLException sqle){
			sqle.printStackTrace();
          	throw new PersistentInformationException("Não foi possível atualizar o código da disciplina");
        }
      	
	}
	
	
	/** Gets the Students that monitor this discipline. */
    public Vector getMonitors(){
		Vector monitors = new Vector();
    	this.result     = this.database.query("SELECT userID FROM monitors WHERE disciplineID ='"+this.ID+"'");
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
			ue.printStackTrace();
		}
		return monitors;
    }
	
	
	/** Gets the Students that monitor this discipline que nao sao responsaveis por ela */
    public Vector getMonitorsNotResponsible(){
		Vector monitors = new Vector();
    	this.result     = this.database.query("SELECT monitors.userID FROM monitors,community WHERE "+
											" community.communityID=monitors.disciplineID AND "+
											" community.userID != monitors.userID AND "+
											" community.communityID ='"+this.ID+"' ");
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
			ue.printStackTrace();
		}
		return monitors;
    }
	
	
	/**
	 @return Todos os professores dessa disciplina. */
    public Vector getTeachers(){
		Vector professors = new Vector();
    	this.result     = this.database.query("SELECT userID FROM teaches WHERE communityID ='"+this.ID+"'");
		try {
			if (this.result.size() > 0){
				String professorID;
				Professor professor;
	    		for (int i=0; i < this.result.size(); i++){
          			professorID = (String)((Hashtable)this.result.get(i)).get("userID");
					professor = new Professor(professorID,this.database);
			  		professors.add(professor);
	    	  	}
        	}
		} catch (UserException ue){
			ue.printStackTrace();
		}
		return professors;
    }
	
	
	/**
	 @return Todos os professores dessa disciplina que nao sao responsaveis por ela */
    public Vector getTeachersNotResponsible(){
		Vector professors = new Vector();
    	this.result     = this.database.query("SELECT teaches.userID FROM teaches,community WHERE "+
											" community.communityID=teaches.communityID AND "+
											" community.userID != teaches.userID AND "+
											" community.communityID ='"+this.ID+"' ");
		try {
			if (this.result.size() > 0){
				String professorID;
				Professor professor;
	    		for (int i=0; i < this.result.size(); i++){
          			professorID = (String)((Hashtable)this.result.get(i)).get("userID");
					professor = new Professor(professorID,this.database);
			  		professors.add(professor);
	    	  	}
        	}
		} catch (UserException ue){
			ue.printStackTrace();
		}
		return professors;
    }
	
	
	
	
	private Vector concat(Vector v1 , Vector v2){
		for (int i=0 ; i < v2.size() ; i++ ){
			v1.add(v2.get(i));
		}
		return v1;
	}
	
	/**
	Sobrescreve esse metodo da classe pai
	 */
	public Vector getMembers(){
		Vector interestedMembers = super.getMembers();
		Vector professors = this.getTeachersNotResponsible();
		Vector monitors = this.getMonitorsNotResponsible();
		
		Vector result = new Vector();
		result = this.concat(result,interestedMembers) ;
		result = this.concat(result,professors) ;
		result = this.concat(result,monitors) ;
		
		return result;
	}
	
	/**
	@return Todos os estudantes dessa disciplina.
	 */
	public Vector getStudents(){
		Vector users = new Vector();
		User user ;
		String userID;
		
		this.result  = this.database.query("SELECT userID FROM communityinterests WHERE communityID='"+this.ID+"' ");
		try {
	    	for (int i=0; i < this.result.size(); i++){
          		userID = (String)((Hashtable)this.result.get(i)).get("userID");
				user = new Student(userID,this.database);
		  		users.add(user);
	      	}
		} catch (UserNotFoundException unfe){
			unfe.printStackTrace();
		}
		return users;
	}
	
	
	
	/** Verifies if a discipline exists and returns its ID. */
	/*
	public static String getID(String disciplineCode, DatabaseLayer database)
		throws DisciplineNotFoundException {
		Vector student = database.query("SELECT courseID from discipline WHERE disciplineCode='"+disciplineCode+"'");
		if (student.size() > 0){
			return (String)((Hashtable)student.firstElement()).get("courseID");
		} else throw new DisciplineNotFoundException("Discipline not found with code "+disciplineCode+".", disciplineCode);
	}
	 */

	/** Verifies if a discipline exists and returns its Code. */
	/*
	public static String getCode(String disciplineID, DatabaseLayer database)
		throws DisciplineNotFoundException {
		Vector student = database.query("SELECT disciplineCode from discipline WHERE courseID='"+disciplineID+"'");
		if (student.size() > 0){
			return (String)((Hashtable)student.firstElement()).get("disciplineCode");
		} else throw new DisciplineNotFoundException("Discipline not found with ID "+disciplineID+".", disciplineID);
	 }*/
	
	public void printData(){
		System.out.println("------------ Disciplina ---------------------");
		System.out.println("ID        = " + this.getID());
		System.out.println("Código    = " + this.getCode());
		//System.out.println("Nome      = " + this.getName());
		System.out.println("Titulo    = " + this.getTitle());
		System.out.println("Descrição = " + this.getDescription());
		/*System.out.print("Monitores = ");
		Vector monitors = this.getMonitors();
		System.out.println(monitors.size());
		if (monitors.size() > 0){
        	for (int i=0; i < monitors.size(); i++){
            	Student monitor = (Student)monitors.get(i);
              	monitor.printData();
        	}
		 }*/
		/*System.out.print("Alunos   = ");
		Vector students = this.getStudents();
		System.out.println(students.size());
		if (students.size() > 0){
        	for (int i=0; i < students.size(); i++){
            	Student student = (Student)students.get(i);
              	student.printData();
        	}
		 }*/
		/*System.out.print("Professores = ");
		Vector professors = this.getProfessors();
		System.out.println(professors.size());
		if (professors.size() > 0){
        	for (int i=0; i < professors.size(); i++){
            	Professor professor = (Professor)professors.get(i);
              	professor.printData();
        	}
		 }*/
		/*System.out.print("Links = ");
		Vector links = this.getLinks();
		System.out.println(links.size());
		if (links.size() > 0){
        	for (int i=0; i < links.size(); i++){
            	Link link = (Link)links.get(i);
              	link.printData();
        	}
		 }*/
		/*System.out.print("Documentos = ");
		Vector documents = this.getDocuments();
		System.out.println(documents.size());
		if (documents.size() > 0){
        	for (int i=0; i < documents.size(); i++){
            	Document document = (Document)documents.get(i);
				document.printData();
        	}
		 }*/
		System.out.println("----------------------------------------------");
	}

	public static void main(String args[]){
		DatabaseLayer db = new DatabaseLayer();
		try{
			// Criar uma nova disciplina existente
			try {
				Discipline discipline = new Discipline("1016044335989",db);
				//Vector links = discipline.getLinks(20,10);
				discipline.printData();
				
				System.out.println(discipline.getMembers().size());
				//ResourceCategory[] categories = discipline.getLinkCategories();
				/*				ResourceCategory[] categories = discipline.getDocumentCategories();
				for (int i=0; i < categories.length; i++){
					categories[i].printAll();
				 }*/
			} catch (Exception e){
				e.printStackTrace();
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	 }
}
