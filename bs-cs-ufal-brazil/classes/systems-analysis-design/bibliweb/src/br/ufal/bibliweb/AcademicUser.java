package br.ufal.bibliweb;
///////////////////////////////////////////////////////////
//
//  AcademicUser.java
//  Implementation of the Class AcademicUser
//  Generated by Enterprise Architect
//  Created on:      13-Apr-2002 13:44:13
//  Original author: Marcello Junior
//
///////////////////////////////////////////////////////////
//  Modification history:
///////////////////////////////////////////////////////////
import java.util.Vector;
import java.util.Hashtable;
import java.util.GregorianCalendar;
import java.sql.SQLException;
import java.sql.Date;

import br.ufal.bibliweb.exception.UserNotFoundException;
import br.ufal.bibliweb.exception.AcademicCourseNotFoundException;
import br.ufal.bibliweb.exception.UserAlreadyExistsException;
import br.ufal.bibliweb.exception.CPFIncorrectFormException;
import br.ufal.bibliweb.exception.ResourceNotFoundException;

import br.ufal.bibliweb.DatabaseLayer;

/**
 * Define os usu�rios acad�micos existentes numa biblioteca acad�mica.
 * <ul>
 * 	<li>Professor;
 * 	<li>Aluno;
 * </ul>
 * Possuem todas caracter�sticas de um usu�rio abstrato, al�m dos comportamentos.
 *
 * @author 		Marcello de Sales
 */
public class AcademicUser extends AbstractUser{

	/** A matr�cula do usu�rio acad�mico. */
	private String registration;
	/** A identifica��o do curso acad�mico no qual o usu�rio faz parte. */
	private String academicCourseID;
	
	/**
	 * @param ID 		A identifica��o do usu�rio acad�mico
	 * @param database  Conex�o com banco de dados
	 */
	public AcademicUser(String ID, DatabaseLayer database) throws UserNotFoundException{
		super(ID,database.query("SELECT * FROM academic_user WHERE user_id='"+ID+"'"));
		this.database = database;
		this.registration     = (String)this.databaseState.get("registration");
		this.academicCourseID = (String)this.databaseState.get("academic_course_id");
	}
	
	/** @return A matr�cula do usu�rio acad�mico. */
	public String getRegistration(){
		return this.registration;
	}
	
	/** @return Retorna a identifica��o do curso acad�mico que o usu�rio faz parte. */
	public String getAcademicCourseID(){
		return this.academicCourseID;
	}
	
	/**
	 * @return Retorna o AcademicCourse ao qual o usu�rio possui.
	 * @see AcademicCourse
 	 */
	public AcademicCourse getAcademicCourse(){
		AcademicCourse academicC = null;
		try {
			academicC = new AcademicCourse(this.academicCourseID,this.database);
		} catch (ResourceNotFoundException rnfe){
			rnfe.printStackTrace();
		}
		return academicC;
	}
	
	/** @return Verifica se um usu�rio acad�mico exite com a matr�cula passada. */
	public static boolean registrationExists(DatabaseLayer database, String registration){
		Vector thisResult = database.query("SELECT registration FROM academic_user WHERE registration='"+registration+"'");
		return (thisResult.size() > 0);
	}
	
	/**
	 * Cria um novo usu�rio acad�mico.
	 * @return A identifica��o do novo usu�rio criado!
	 * @throws CPFIncorrectFormException
	 */
	public static String createNewAcademicUser(String groupID, String academicCourseID, String CPF, String registration, String name, String homeAddress, String workAddress, String homePhone, String workPhone, String cellPhone, String email, DatabaseLayer database)
		throws CPFIncorrectFormException, ResourceNotFoundException, UserAlreadyExistsException, AcademicCourseNotFoundException{
		
			CPF   = CPF.trim();
			email = email.trim();
			registration = registration.toUpperCase();
			
			//valida o CPF e levanta exce��o caso esteja errado;
			if(CPF.length() != 11) {
				throw new CPFIncorrectFormException("O CPF � composto de 11 caracteres!");
			} else
				if ((Utility.isCPFValid(CPF)))
					if (!Group.exists(groupID,database))
						throw new ResourceNotFoundException("O grupo n�o existe com ID="+groupID);
					else
					if (AcademicUser.registrationExists(database,registration))
						throw new UserAlreadyExistsException("Usu�rio j� existente com matr�cula = "+registration,registration);
					else
					if (!AcademicCourse.exists(academicCourseID,database))
						throw new AcademicCourseNotFoundException("Curso acad�mico n�o encontrado ID = "+academicCourseID);
					else {
						return AcademicUser.createInDatabase(groupID,academicCourseID,CPF,registration,name,homeAddress,workAddress,homePhone,workPhone,cellPhone,email,database);
						//usuario pode criar sua conta online com o cpf e o ID
					}
				else throw new CPFIncorrectFormException("O n�mero do CPF n�o � v�lido!");
	}
	
	/**
	 * Cria a inst�ncia de usu�rio no banco de dados.
	 * @return O identificador do novo usu�rio acad�mico. */
	private static String createInDatabase(String groupID, String academicCourseID,String CPF, String registration,String name, String homeAddress, String workAddress, String homePhone, String workPhone, String cellPhone, String email, DatabaseLayer database){
		String newUserID = "";
		try{ //Usu�rio impedido porque ainda nao possui imagem.
			newUserID = Utility.getNewOID();
			database.update("INSERT INTO academic_user (user_id,group_id,status_id,cpf,name,home_address,work_address,home_phone,work_phone,cell_phone,email,registration,academic_course_id) "+
								"VALUES ('"+newUserID+"','"+groupID+"','"+Status.USER_OBSTRUCTED+"','"+CPF+
								"','"+name+"','"+homeAddress+"','"+workAddress+"','"+homePhone+
								"','"+workPhone+"','"+cellPhone+"','"+email+"','"+registration+"','"+academicCourseID+"')");
		}catch(SQLException sqle){
			sqle.printStackTrace();
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
		return newUserID;
	}
}

