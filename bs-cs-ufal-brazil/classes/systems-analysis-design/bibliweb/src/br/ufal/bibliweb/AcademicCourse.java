package br.ufal.bibliweb;

///////////////////////////////////////////////////////////
//
//  AcademicCourse.java
//  Implementação da classe AcademicCourse
//  Criado em:      13-Apr-2002 21:24:27
//  Original author: Ricardo Oliveira
//
///////////////////////////////////////////////////////////

/*
Curso acadêmico presente na instituição.

* Ciência da Computação - Código: G55;
* Medicina -  Código G46;
* Direito - Código G35;
...

*/
import java.util.Vector;
import java.util.Hashtable;
import java.sql.SQLException;

import br.ufal.bibliweb.exception.ResourceNotFoundException;

public class AcademicCourse {


	/** A identificação do curso acadêmico.*/
	private String ID;

	/** A descrição do curso acadêmico.*/
	private String description;

	/** O código do curso acadêmico. */
	private String code;
	
	/** Resultado das buscas no banco de dados. */
	private Vector result;
	/** Conexão com o banco de dados. */
	private DatabaseLayer database;
	
	public AcademicCourse(String ID, DatabaseLayer database) throws ResourceNotFoundException{
		this.result = database.query("SELECT * FROM academic_course WHERE academic_course_id='"+ID+"'");
		if (this.result.size() != 1){
			throw new ResourceNotFoundException("Curso academico não encontrado com ID="+ID+" !");
		} else {
			this.database = database;
			this.ID = ID;
			Hashtable academicCourseState =	(Hashtable)result.firstElement();
			this.description = (String)academicCourseState.get("description");
			this.code = (String)academicCourseState.get("code");
		}
	}
	
	/** Retorna o identificador do Curso Acadêmico. */
	public String getID(){
		return this.ID;
	}

	/** Retorna a descrição do Curso Acadêmico */
	public String getDescription(){
		return this.description;
	}

	/** Retorna o código do Curso Acadêmico. */
	public String getCode(){
		return this.code;
	}
	
	public void printAll(){
		System.out.println("$$$$$$$$$$$$$  Informacoes do Curso Academico  $$$$$$$$$$$$$$");
		System.out.println("ID: "+this.getID());
		System.out.println("Descrição: "+this.getDescription());
		System.out.println("Codigo: "+this.getCode());
	}
	
	/** Cria um novo curso acadêmico*/
	public static void createNewAcademicCourse(String description, String code, DatabaseLayer database){
		try{
			database.update("INSERT INTO academic_course (academic_course_id, description, code) VALUES ('"+Utility.getNewID(database)+"','"+description+"','"+code+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
	}
	
	/** Verifica se um curso acadêmico exite. */
	public static boolean exists(String academicCourseID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT academic_course_id FROM academic_course WHERE academic_course_id="+academicCourseID+"");
		return (thisResult.size() > 0);
	}
	
	public static void main(String[] args){
		DatabaseLayer db = new DatabaseLayer();
		try{
			AcademicCourse.createNewAcademicCourse("Ciência da Computação","G55",db);
			AcademicCourse.createNewAcademicCourse("Medicina","G25",db);
			//AcademicCourse couse = new AcademicCourse("001",db);
			//couse.printAll();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
