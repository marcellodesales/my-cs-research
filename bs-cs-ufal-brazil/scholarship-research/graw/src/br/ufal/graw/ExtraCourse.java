package br.ufal.graw;

/**
	Destinado a qualquer curso extra-curricular.
 */
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.graw.AbstractCourse;
import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.GrawException;


public class ExtraCourse extends AbstractCourse{
	
	
	
	public ExtraCourse(String extraCourseID, DatabaseLayer database)
		throws CommunityException{
		super(extraCourseID,database);
			
		this.result = this.database.query("SELECT * FROM extracourse WHERE communityID='"+extraCourseID+"'");
		if (this.result.size() == 0){
			throw new CommunityException("Curso não encontrado.");
		}else {
			this.ID = (String)((Hashtable)result.firstElement()).get("communityID");
			this.academicCourseID = (String)((Hashtable)result.firstElement()).get("academicCourseID");
			this.departmentID     = (String)((Hashtable)result.firstElement()).get("departmentID");
			this.instituteID      = (String)((Hashtable)result.firstElement()).get("instituteID");
		}
	}
	
	public static String createAPublicExtraCourseInDatabase(DatabaseLayer database,User responsible,String title, String description, int visibility, int association, String kind, String categoryID, String subcategoryID, String program, int hours, String bibliography){
		String communityID = AbstractCourse.createACourseInDatabase(database,responsible,title,description,visibility,association,kind,categoryID,subcategoryID,program,hours,bibliography);
		
		try{
			database.update("INSERT INTO extracourse (communityID) VALUES ('"+communityID+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return communityID;
	}
	
	public static String createAnAcademicExtraCourseInDatabase(DatabaseLayer database,User responsible,String title, String description, int visibility, int association, String kind, String categoryID, String subcategoryID, String program, int hours, String bibliography,AcademicCourse academicCourse){
		String communityID = ExtraCourse.createACourseInDatabase(database,responsible,title,description,visibility,association,kind,categoryID,subcategoryID,program,hours,bibliography);
		
		try{
			String instituteID = academicCourse.getInstitute().getID();
			String departmentID = academicCourse.getDepartment().getID();
			database.update("INSERT INTO rel_extracourse_academic (communityID,instituteID,departmentID,academicCourseID) "+
							"VALUES ('"+communityID+"','"+instituteID+"','"+departmentID+"','"+academicCourse.getID()+"')");
		}catch(Exception e){
			e.printStackTrace();
		}
		return communityID;
	}
	
	public String getAcademicCourseID(){
		return this.academicCourseID;
	}
	
	public boolean isAcademic(){
		return (this.academicCourseID!=null);
	}
}
