package br.ufal.bibliweb;

///////////////////////////////////////////////////////////
//
//  AcademicCourse.java
//  Implementa��o da classe AcademicCourse
//  Criado em:      13-Apr-2002 21:24:27
//  Original author: Ricardo Oliveira
//
///////////////////////////////////////////////////////////

/*
Curso acad�mico presente na institui��o.

* Ci�ncia da Computa��o - C�digo: G55;
* Medicina -  C�digo G46;
* Direito - C�digo G35;
...

*/
import java.util.Vector;
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.bibliweb.exception.ResourceNotFoundException;

public class AcademicCourse {


	/** A identifica��o do curso acad�mico.*/
	private String ID;

	/** A descri��o do curso acad�mico.*/
	private String description;

	/** O c�digo do curso acad�mico. */
	private String code;
	
	/** Resultado das buscas no banco de dados. */
	private Vector result;
	/** Conex�o com o banco de dados. */
	private DatabaseLayer database;
	
	public AcademicCourse(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.result = database.query("SELECT * FROM academic_course WHERE academic_course_id='"+ID+"'");
		if (this.result.size() != 1){
			throw new ResourceNotFoundException("Curso academico n�o encontrado com ID="+ID+" !");
		} else {
			this.database = database;
			this.ID = ID;
			Hashtable academicCourseState =	(Hashtable)result.firstElement();
			this.description = (String)academicCourseState.get("description");
			this.code = (String)academicCourseState.get("code");
		}
	}
	
	/** Retorna o identificador do Curso Acad�mico. */
	public String getID(){
		return this.ID;
	}

	/** Retorna a descri��o do Curso Acad�mico */
	public String getDescription(){
		return this.description;
	}

	/** Retorna o c�digo do Curso Acad�mico. */
	public String getCode(){
		return this.code;
	}
	
	public void printAll(){
		System.out.println("$$$$$$$$$$$$$  Informacoes do Curso Academico  $$$$$$$$$$$$$$");
		System.out.println("ID: "+this.getID());
		System.out.println("Descri��o: "+this.getDescription());
		System.out.println("Codigo: "+this.getCode());
	}
	
	/** Cria um novo curso acad�mico*/
	public static void createNewAcademicCourse(String description, String code, DatabaseLayer database){
		try{
			database.update("INSERT INTO academic_course (academic_course_id, description, code) VALUES ('"+Utility.getNewID(database)+"','"+description+"','"+code+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
	}
	
	/** Verifica se um curso acad�mico exite. */
	public static boolean exists(String academicCourseID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT academic_course_id FROM academic_course WHERE academic_course_id="+academicCourseID+"");
		return (thisResult.size() > 0);
	}
	
	public static void main(String[] args){
		DatabaseLayer db = new DatabaseLayer();
		try{
			AcademicCourse.createNewAcademicCourse("Ci�ncia da Computa��o","G55",db);
			AcademicCourse.createNewAcademicCourse("Medicina","G25",db);
			//AcademicCourse couse = new AcademicCourse("001",db);
			//couse.printAll();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
