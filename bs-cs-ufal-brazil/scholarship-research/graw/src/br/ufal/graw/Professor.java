package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

import java.sql.SQLException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Course;
import br.ufal.graw.Department;
import br.ufal.graw.exception.UserLoginFormatException;
import br.ufal.graw.exception.UserLoginWrongPasswordException;
import br.ufal.graw.exception.UserDuplicateLoginException;
import br.ufal.graw.exception.CourseException;
import br.ufal.graw.exception.DepartmentNotFoundException;
import br.ufal.graw.exception.DisciplineDuplicateCodeException;
import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.CommunityException;
/**
 *	Class Professor.
 *	@author Programmed by graw group - UFAL
 *      @author Marcello de Sales, Rodrigo Paess	
 *      15/06/2001
 *
 *	Professor is an entity composed by various disciplines which
 *	them itself interests or/and monitors.
 *
 */
public class Professor extends AcademicUser{
	/* In this class , the login attribute means the professor´s matriculation */

	public Professor(String userID, DatabaseLayer database) throws UserNotFoundException{
		super(userID,database);
	//	this.coursesTaught = this.loadCoursesTaught();
	}
	/*
	public void newCoursesCreated(){
		this.coursesTaught = this.loadCoursesTaught();
		
		System.out.println("Chamado");
	}
	 */
	/** Gets the codes of the disciplines taught by a Professor. */
	/*
    private Vector loadCoursesTaught(){
		Vector courses = new Vector();
		Course course;
		
    	this.result = this.database.query("SELECT courseID FROM teaches WHERE userID='"+this.ID+"'");
		if (this.result.size() > 0){
	    	for (int i=0; i < this.result.size(); i++){
          		String courseID = (String)((Hashtable)this.result.get(i)).get("courseID");
				try {
					course = AbstractCourse.getRealCourse(courseID,this.database);
					courses.add(course);
				} catch (CourseException dnfe){
					System.err.println(dnfe.getMessage());
					Utility.log(Utility.ERROR_FILE_LOG,dnfe);
				}
	      	}
        }
		return courses;
    }
	 */
	
	/** Verifies if a professor with professorID exists */
	public static boolean exists(String professorID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT userID FROM professor WHERE userID='"+professorID+"'");
		return (thisResult.size() == 1);
	}
	
	/** Verifies if a professor has already signed in on graW with the given matriculation */
	public static boolean alreadySigned(String matriculation, String academicCourseID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT userID FROM academicuser WHERE academicCourseID='"+academicCourseID+"' AND matriculation='"+matriculation+"'");
		String userID = (String)((Hashtable)thisResult.firstElement()).get("userID");
		return (!userID.equals(""));
	}
	
	public boolean isTeacher(Discipline discipline){
		String disciplineID = discipline.getID();
		this.result = this.database.query("SELECT communityID FROM teaches WHERE userID='"+this.ID+"' AND communityID='"+disciplineID+"'");
		return this.result.size() >0;
	}
	
	public boolean isTeacher(String  disciplineID){
		this.result = this.database.query("SELECT communityID FROM teaches WHERE userID='"+this.ID+"' AND communityID='"+disciplineID+"'");
		return this.result.size() >0;
	}
	
	public boolean isMember(Community community){
		if (super.isMember(community)==false){
			return isTeacher(community.getID());
		}else {
			return true;
		}
	}
	
	public Vector getDisciplinesTaught(){
		Vector disciplines = new Vector();
		Discipline discipline;
		
    	this.result = this.database.query("SELECT communityID FROM teaches WHERE userID='"+this.ID+"'");
		if (this.result.size() > 0){
	    	for (int i=0; i < this.result.size(); i++){
          		String disciplineID = (String)((Hashtable)this.result.get(i)).get("communityID");
				try {
					discipline = new Discipline(disciplineID,this.database);
					disciplines.add(discipline);
				} catch (CommunityException dnfe){
					dnfe.printStackTrace();
					System.err.println(dnfe.getMessage());
					Utility.log(Utility.ERROR_FILE_LOG,dnfe);
				}
	      	}
        }
		return disciplines;
	}
	
	/* Retorna todas as disciplinas que esse professor esta associado incluindo as que ele leciona */
	public Vector getDisciplines(){
		/* Verifica as associacoes que sao communs aos academicos */
		Vector commonDisciplines = super.getDisciplines();
		Vector disciplines = new Vector();
		String communityID;
		Hashtable hash;
		Discipline discipline;
		
		/*
		Note que essa query exclui os usuarios que ensinam uma disciplina e sao ao mesmo tempo
		responsavel por ela, pois se eles forem responsaveis a disciplina ja esta inserida no
		vector commonDisciplines.
		 */
		
		/* Associacoes de ensino */
		this.result = this.database.query("select discipline.communityID from discipline,teaches,community where "+
				" discipline.communityID = teaches.communityID AND "+
				" discipline.communityID = community.communityID AND "+
				" community.userID != '"+this.ID+"' AND "+
 				" teaches.userID='"+this.ID+"' ");
		for (int i=0 ; i < this.result.size() ; i++ ){
			try{
				hash = (Hashtable)this.result.get(i) ;
				communityID = (String) hash.get("communityID");
				discipline = new Discipline( communityID ,this.database);
				disciplines.add( discipline );
			}catch(CommunityException cnfe){
			}
		}
		
		/* Unindo */
		for (int i=0 ; i< commonDisciplines.size() ; i++ ){
			disciplines.add(commonDisciplines.get(i));
		}
		
		return disciplines;
	}
	
	/*
	public Department getDepartment() throws DepartmentNotFoundException{
		this.result = database.query("SELECT departmentID from professor WHERE userID='"+this.ID+"'");
		return new Department((String)((Hashtable)this.result.firstElement()).get("departmentID") , this.database);
	 }*/
	
	public void electMonitor(String userID, String disciplineID)
			throws UserNotFoundException, CommunityException{
			
		/*Enumeration e = this.coursesTaught.elements();
		boolean courseIsTaught = false;
		userID 		= Utility.transformToDatabase(userID);
		courseID    = Utility.transformToDatabase(courseID);
	
		//verifica se o professor ensina a disciplina passada
		Course course;
		while (e.hasMoreElements()){
			course = (Course)e.nextElement();
			if (course.getID().equals(courseID)){
				courseIsTaught = true;
				break;
			}
		}
		*/
		Discipline discipline = new Discipline(disciplineID,this.database);
				
		/* Verifica se esse professor ensina a disciplina */
		if ( this.isTeacher(discipline) ){
			/* Verifica se o usuario existe */
		   	this.result = this.database.query("SELECT userID FROM student WHERE userID='"+userID+"'");
		   	if (this.result.size() == 0)
				throw new UserNotFoundException("Estudante inexistente.");
		    else {
           		try{
                	this.database.update("INSERT INTO monitors (userid, disciplineid, academiccourseid,departamentid,instituteid ) VALUES ('"+userID+"','"+disciplineID+"','"+discipline.getAcademicCourseID()+"','"+discipline.getDepartmentID()+"','"+discipline.getInstituteID()+"' )");
					this.database.update("DELETE FROM communityinterests WHERE userid='"+userID+"' AND communityid='"+disciplineID+"'");
					
            	} catch (SQLException sqle){
                    System.err.println(sqle.getMessage());
					Utility.log(Utility.ERROR_FILE_LOG,sqle);
				}
		    }
	   	} else throw new CommunityException("Você não ensina esse curso.");
	 }
	
	public void deleteMonitor(String userID, String disciplineID)
			throws UserNotFoundException, CommunityException{
			
				/*Enumeration e = this.coursesTaught.elements();
		boolean courseIsTaught = false;
		userID 		= Utility.transformToDatabase(userID);
		courseID    = Utility.transformToDatabase(courseID);
	
		//verifica se o professor ensina a disciplina passada
		Course course;
		while (e.hasMoreElements()){
			course = (Course)e.nextElement();
			if (course.getID().equals(courseID)){
				courseIsTaught = true;
				break;
			}
		}
				 */
		
		/* Verifica se esse professor ensina a disciplina */
		if ( this.isTeacher(disciplineID) ){
			/* Verifica se o usuario existe */
		   	this.result = this.database.query("SELECT userID FROM user WHERE userID='"+userID+"' AND kindOfUserID='"+User.STUDENT+"'");
		   	if (this.result.size() == 0)
				throw new UserNotFoundException("Estudante inexistente.");
		    else {
           		try{
                	this.database.update("DELETE FROM  monitors WHERE userID='"+userID+"' AND disciplineID='"+disciplineID+"'");
					this.database.update("INSERT INTO communityinterests (userid, communityid, status ) VALUES ('"+userID+"','"+disciplineID+"','"+Community.MEMBER_ACTIVED+"')");
            	} catch (SQLException sqle){
                    System.err.println(sqle.getMessage());
					Utility.log(Utility.ERROR_FILE_LOG,sqle);
				}
		    }
	   	} else throw new CourseException("Você não ensina esse curso.");
				
		
	}
	
	public void proposeNewDiscipline(String title, String description, int visibility, int association, String kind, String categoryID,String subcategoryID,String program, int hours, String bibliography, String code, AcademicCourse academicCouse){
		Discipline.createADisciplineInDatabase(this.database,this,title,description,visibility,association,kind,categoryID,subcategoryID,program,hours,bibliography,code,academicCouse);
	}
	
	
	/*
	public String createNewDiscipline(String code, String name, String description,String program,String references,String hours )
		throws PersistentInformationException,DisciplineDuplicateCodeException{
		try{
			String courseID = Utility.getNewID();
			description = Utility.getTextField(description);
			program = Utility.getTextField(program);
			references = Utility.getTextField(references);
			name = Utility.transformToDatabase(name);
			code = Utility.transformToDatabase(code.toUpperCase());
			
			this.result = this.database.query("SELECT courseID FROM discipline WHERE disciplineCode='"+code+"'");
			if (result.size()!=0){
				throw new DisciplineDuplicateCodeException("Ja existe uma disciplina com esse codigo cadastrado. ["+code+"]",code);
			}
			
			this.database.update("INSERT INTO course (courseID,kindOfCourseID, name,description, program , hours, bibliography) VALUES ('"+courseID+"' ,'"+Course.DISCIPLINE+"', '"+name+"','"+description+"', '"+program+"','"+hours+"','"+references+"')");
			this.database.update("INSERT INTO discipline (courseID,disciplineCode) VALUES ('"+courseID+"' ,'"+code+"')");
			this.database.update("INSERT INTO interests (userID, courseID) values ('"+this.ID+"','"+courseID+"')");
			this.coursesTaught.add(AbstractCourse.getRealCourse(courseID,this.database));
			this.coursesChanged();
			return courseID;
		}catch(SQLException sqle){
			this.error(sqle.getMessage());
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar uma disciplina. "+sqle.getMessage());
		}catch(Exception e){
		}
		return null;
			
	 }
	
	
	public String createNewExtraCourse(String name, String description , String program,String references,String hours ) throws PersistentInformationException{
		try{
			String courseID = Utility.getNewID();
			description = Utility.getTextField(description);
			program = Utility.getTextField(program);
			references = Utility.getTextField(references);
			name = Utility.transformToDatabase(name);
			this.database.update("INSERT INTO course (courseID,kindOfCourseID, name,description,program , hours, bibliography) VALUES ('"+courseID+"' ,'"+Course.EXTRA_COURSE+"', '"+name+"','"+description+"', '"+program+"','"+hours+"','"+references+"')");
			this.database.update("INSERT INTO interests (userID, courseID) values ('"+this.ID+"','"+courseID+"')");
			this.coursesTaught.add(AbstractCourse.getRealCourse(courseID,this.database));
			this.coursesChanged();
			return courseID;
		}catch(SQLException sqle){
			this.error(sqle.getMessage());
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um curso extra curricular. "+sqle.getMessage() );
		}catch(Exception e){
		}
		return null;
	 }
	
	 */
	/*
    protected void printData(){
		System.out.println("----------------------------------------------");
		System.out.println("ID       = "+this.getID());
		System.out.println("Login    = "+this.getLogin());
		System.out.println("Nome     = "+this.getName());
		System.out.println("email    = "+this.getEmail());
		System.out.println("password = "+this.getPassword());
		System.out.println("----------------------------------------------");
		System.out.println("Departamento = "+this.getDepartmentName());
		System.out.println("Código       = "+this.getDepartmentCode());
		System.out.println("Dominio      = "+this.getDepartmentDomain());
		System.out.println("Matricula    = "+this.getMatriculation());
		System.out.println("----------------------------------------------");
		System.out.println("Disciplinas Lecionadas = ");
	 */
		/*
		if (this.coursesTaught.size() > 0){
        	for (int i=0; i < this.coursesTaught.size(); i++){
            	String cod = (String)this.coursesTaught.get(i);
              	System.out.println(i+1+" - "+cod);
        	}
		 }*/
     //}

	/** Prints an error message for debug */
     private void error(String msg){
		System.out.println(msg);
		System.exit(1);
     }

    
	public static void main(String args[]){
		DatabaseLayer data = new DatabaseLayer();
		try{
			AcademicUser acu = new Professor("1005249617900",data);
			System.out.println(acu.getAcademicCourse().getName());
		} catch (Exception e){
			
		}
	 }
}
			/*data.connect();
         	// Criar um professor existente
			
         		Professor prof = new Professor("1",data);
			//prof.printData();
				Enumeration c = prof.getGroups();
				while (c.hasMoreElements()){
					System.out.println("Grupos: "+( (Group) c.nextElement()).getName());
				}
				//prof.electMonitor("1998G55D001T","IA-555");
				/*User user = getAttribute("user");
				  if (user.whoAmI() == Professor.id()){
				  Professor professor = (Professor)user;
				  
				//prof.electMonitor("1998G55D001T","SE-254");
    	     	prof.printData();
			} catch (UserLoginFormatException lfe){
				System.out.println("Redireciona -> Página Inicial: " + lfe.getMessage());
			} catch (UserLoginWrongPasswordException ulwpe){
				System.out.println("Redireciona -> Página Inicial: " + ulwpe.getMessage());
			} catch (UserNotFoundException unfe){
				System.out.println("Redireciona -> Página Inicial: " + unfe.getMessage());
				unfe.printStackTrace();
			} //catch (DisciplineNotTaughtException dnte){
//				System.out.println(dnte.getMessage());
//			}*/
	/*
		}catch(Exception e){
			e.printStackTrace();
		}
			 */

