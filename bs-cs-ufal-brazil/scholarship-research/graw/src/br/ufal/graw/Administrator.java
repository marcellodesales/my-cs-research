package br.ufal.graw;

import java.util.Vector;
import java.util.Hashtable;
import java.util.Date;

import java.sql.SQLException;

import br.ufal.graw.User;
import br.ufal.graw.Student;
import br.ufal.graw.Professor;
import br.ufal.graw.Discipline;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.exception.UserDuplicateLoginException;
import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.UserLoginFormatException;
import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.DisciplineDuplicateCodeException;
import br.ufal.graw.exception.DisciplineNotFoundException;
import br.ufal.graw.exception.CourseException;
import br.ufal.graw.exception.MailException;
import br.ufal.graw.web.Mail;

public class Administrator extends AbstractUser{
	

	/*	public Administrator(String login, String password,DatabaseLayer database)
	throws UserNotFoundException{

		this.database = database;
		this.result=this.database.query("SELECT * from administrator where administratorLogin='"+login+"' AND password='"+password+"'");
		if (result.size() == 0 ){
			throw new UserNotFoundException("Administrator not found with login= "+login+" and password= "+password);
		}
	 }*/

	/** Attempt retrieve the data from database and create a instance: a Student.
	@param login The student's matriculation.
	@param password The student's password
	@param database The database abstraction.
	 */
	public Administrator(String administratorID, DatabaseLayer database)
		throws UserNotFoundException{
		
		super(administratorID,database);

		this.database = database;
		

		this.result=this.database.query("SELECT * from user where userID='"+administratorID+"' AND kindOfUserID='"+User.ADMINISTRATOR+"'");
		if (result.size() == 0 ){
			throw new UserNotFoundException("Administrador não encontrado.");
		}
	}
		
	/**
		@return new User's ID
	 */
	public String createNewUser(String login, String name, String email)
		throws UserDuplicateLoginException, MailException, PersistentInformationException{
		
		String userID;
		String password =(new Date().getTime())+"";
		
		
		this.result = this.database.query("SELECT userID from user WHERE login='"+login+"'");
		if (result.size() != 0 ){
			throw new UserDuplicateLoginException("Já existe um usuário com esse login: " + login);
		}else{
			try{
				userID = (new java.util.Date().getTime())+"";
				database.update("INSERT INTO user (userID,kindOfUserID, login, name, email, password) VALUES ('"+userID+"' , '"+User.USER+"', '"+login+"','"+name+"','"+email+"','"+password+"' )");
				Mail.send("graw@tci.ufal.br","graW - Graduação WEB",email,name+",Parabéns voce é o mais novo membro do graW",name+",\nPara acessar o sistema utilize o login que você cadastrou e a sua senha temporária é: "+password+"\nVocê pode alterá-la quando quiser utilizando o menu de configurações pessoais do graW.");
				return userID;
			}catch(SQLException sqle){
				System.err.println(sqle.getMessage());
				throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um usuário.");
			}
		}
		
	}
	
	public void deleteUser(String userID)
		throws UserNotFoundException{
		
		int quant = 0;
		login = login.toUpperCase();
		try {
			quant = database.update("DELETE FROM user WHERE userID='"+userID+"'");
			if (quant == 0){
				throw new UserNotFoundException("Impossível excluir, usuário não encontrado ");
			} else {
				/* Relacionamentos */
				database.update("DELETE FROM interests WHERE userID='"+userID+"'");
				this.database.update("DELETE FROM teaches WHERE userID='"+userID+"'");
				/*
				 É mais rapido fazer esses dois deletes do que verificar o tipo de usuario
				 e apagar na tabela certa, mas se o numero de tabelas crescer essa pode ser uma
				 boa sulucao.
				 */
				/* Filhos */
				database.update("DELETE FROM student WHERE userID='"+userID+"'");
				database.update("DELETE FROM professor WHERE userID='"+userID+"'");
			}
		} catch (SQLException sqle){
			sqle.getMessage();
		}
	}
	
	public String createNewStudent(String login, String name, String email, String userName)
					throws UserDuplicateLoginException,MailException,PersistentInformationException{
				
		String studentID;
		
		try{
			studentID = this.createNewUser(login,name,email);
			database.update("UPDATE user SET kindOfUserID='"+User.STUDENT+"' WHERE userID='"+studentID+"'");
			database.update("UPDATE student SET userID='"+studentID+"' WHERE userName='"+userName+"'");
			return studentID;
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um estudante.");
		}
	}
	
	public void deleteStudent(String studentID)
		throws UserNotFoundException{
		
		this.deleteUser(studentID);
	}
	
	public String createNewProfessor(String login, String name, String email, String userName)
					throws UserDuplicateLoginException,MailException,PersistentInformationException{
				
		String professorID;
		
		try{
			professorID = this.createNewUser(login,name,email );
			database.update("UPDATE user SET kindOfUserID='"+User.PROFESSOR+"' WHERE userID='"+professorID+"'");
			database.update("UPDATE professor SET userID='"+professorID+"' WHERE userName='"+userName+"'");
			return professorID;
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um professor.");
		}
	}

	public void deleteProfessor(String professorID)
		throws  UserNotFoundException,PersistentInformationException{
		this.deleteUser(professorID);
	}
	
	/**
	 
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
			return courseID;
		}catch(SQLException sqle){
			this.error(sqle.getMessage());
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar uma disciplina. "+sqle.getMessage());
		}
	 }
	
	
	public String createNewExtraCourse(String name, String description , String program,String references,String hours ) throws PersistentInformationException{
		try{
			String courseID = Utility.getNewID();
			description = Utility.getTextField(description);
			program = Utility.getTextField(program);
			references = Utility.getTextField(references);
			name = Utility.transformToDatabase(name);
			this.database.update("INSERT INTO course (courseID,kindOfCourseID, name,description,program , hours, bibliography) VALUES ('"+courseID+"' ,'"+Course.EXTRA_COURSE+"', '"+name+"','"+description+"', '"+program+"','"+hours+"','"+references+"')");
			return courseID;
		}catch(SQLException sqle){
			this.error(sqle.getMessage());
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um curso extra curricular. "+sqle.getMessage() );
		}
	 }
	
	public void deleteCourse(String courseID) throws PersistentInformationException,CourseException{
		try{
			int quant=0;
			quant= this.database.update("DELETE FROM course WHERE courseID='"+courseID+"'");
			if (quant==0){
				throw new CourseException("Impossível excluir, curso não encontrado ");
			}else{
				/* Relacionamentos
				this.database.update("DELETE FROM interests WHERE courseID='"+courseID+"'");
				this.database.update("DELETE FROM teaches WHERE courseID='"+courseID+"'");
				this.database.update("DELETE FROM document WHERE courseID='"+courseID+"'");
				this.database.update("DELETE FROM link WHERE courseID='"+courseID+"'");
				this.database.update("DELETE FROM message WHERE courseID='"+courseID+"'");
				
				/* Filhos
				this.database.update("DELETE FROM discipline WHERE courseID='"+courseID+"'");
			}
			
		}catch(SQLException sqle){
			this.error(sqle.getMessage());
			throw new PersistentInformationException("Erro no banco de dados ao tentar excluir um curso.");
		}
	}
	

	/*
	public void createNewDiscipline(String disciplineCode, String name, String description)
		throws PersistentInformationException, DisciplineDuplicateCodeException{
		
		disciplineCode= disciplineCode.toUpperCase();
		
		this.result = this.database.query("SELECT courseID FROM discipline WHERE disciplineCode='"+disciplineCode+"'");
		if (result.size()==0){
			throw new DisciplineDuplicateCodeException("Ja existe uma disciplina com esse codigo cadastrado. ["+disciplineCode+"]",disciplineCode);
		}
		try{
			String ID = this.createNewCourse(name,description);
			this.database.update("UPDATE course SET kindOfCourseID='"+Course.DISCIPLINE+"' WHERE courseID='"+ID+"'");
			this.database.update("INSERT INTO discipline (courseID,disciplineCode) VALUES ('"+ID+"','"+disciplineCode+"' )");
		}catch(SQLException sqle){
			this.error(sqle.getMessage());
			sqle.printStackTrace();
		}
		
	}
	 */

	/*
	public void deleteDiscipline(String disciplineID) throws PersistentInformationException,CourseException{
			this.deleteCourse(disciplineID);
	 }

	public void setDisciplinesToProfessor(String disciplinesIDs[] , String professorID)
	throws UserNotFoundException,DisciplineNotFoundException, PersistentInformationException{
		String bigQuery;

		if (disciplinesIDs.length==0){
			throw new DisciplineNotFoundException("Empty discipline's array.", "");
		}
		
		this.result = this.database.query("SELECT userID from professor WHERE userID='"+professorID+"'");
		if (this.result.size()>0){
			
			int quant=0;
			bigQuery="SELECT courseID FROM discipline WHERE ";
				for (int i=0; i<disciplinesIDs.length-1; i++){
					disciplinesIDs[i] = disciplinesIDs[i].toUpperCase();
					bigQuery+="courseID='"+disciplinesIDs[i]+"' OR ";
				}
				disciplinesIDs[disciplinesIDs.length-1] = disciplinesIDs[disciplinesIDs.length-1].toUpperCase();
				bigQuery+="courseID='"+disciplinesIDs[disciplinesIDs.length-1]+"'";
				this.result=this.database.query(bigQuery);

				/* Verify if all disciplines exists
				if (result.size()!=disciplinesIDs.length){
					String message = "Existe/Existem disciplina(s) não encontradas em : ";
					for (int i=0 ; i< disciplinesIDs.length-1 ; i++){
						message+=disciplinesIDs[i]+", ";
					}
					message+=disciplinesIDs[disciplinesIDs.length-1]+".";

					throw new DisciplineNotFoundException(message, "");

				}else{ // Ok! all disciplines exists, now associate professor with his disciplines.
					try{
						this.database.update("DELETE FROM teaches WHERE userID='"+professorID+"'");
						for (int i=0; i<disciplinesIDs.length; i++){
							this.database.update("INSERT INTO teaches (userID, courseID) values ('"+professorID+"','"+disciplinesIDs[i]+"')");
							this.database.update("INSERT INTO interests (userID, courseID) values ('"+professorID+"','"+disciplinesIDs[i]+"')");
						}
					}catch(SQLException sqle){
						this.error(sqle.getMessage());
						throw new PersistentInformationException("Erro no banco de dados ao tentar configurar as disciplinas do professor.");
						//sqle.printStackTrace();
					}
				}
		}else{
			throw new UserNotFoundException("Professor não encontrado.");
		}
	}
	
	public void addProfessorToCourse(Professor professor, Course course)
		throws PersistentInformationException{
		if (professor.teaches(course) ){
			return;
		}
		String professorID = professor.getID();
		String courseID = course.getID();
		try{
		
			this.database.update("INSERT INTO teaches (userID, courseID) values ('"+professorID+"','"+courseID+"')");
			this.database.update("INSERT INTO interests (userID, courseID) values ('"+professorID+"','"+courseID+"')");
			/*Atualiza o estado do objeto professor para que ele reveja as disciplinas
			 que ele leciona
			 
			professor.newCoursesCreated();
		}catch(SQLException sqle){
			throw new PersistentInformationException(sqle.getMessage());
		}
	}
	
	public void removeTeacher(Professor professor, Course course)
		throws PersistentInformationException{
		if (! professor.teaches(course) ){
			return;
		}
		String professorID = professor.getID();
		String courseID = course.getID();
		try{
			this.database.update("DELETE FROM teaches WHERE userID='"+professorID+"' AND courseID='"+courseID+"'");
			this.database.update("DELETE FROM interests WHERE userID='"+professorID+"' AND courseID='"+courseID+"'");
		}catch(SQLException sqle){
			throw new PersistentInformationException(sqle.getMessage());
		}
	}
	
	

	private void error(String errorMessage){
		System.out.println(errorMessage);
	}

	public static void main(String args[]){
		try{
			
			DatabaseLayer db = new DatabaseLayer();
			
			Administrator adm = new Administrator("0",db);
			
			// CRIAR DISCIPLINA
			//String ID =  adm.createNewDiscipline("IA-111","Inteligencia Artificial I", "Inteligência Artificial Básica");
			
			// CRIAR CURSO EXTRA CURRICULAR
			//String ID =  adm.createNewExtraCourse("Curso de Pearl", "Curso da linguagem pearl com duração de uma semana. Foco na flexibilidade dessa liguagem de script.");
			
			//SETAR DISCIPLINAS PARA OS PROFESSORES
			//Professor professor = new Professor("54321",db);
			//String disciplines[] = {"1002296120940"};
			//adm.setDisciplinesToProfessor(disciplines,professor.getID());
			
			// SETAR PROFESSOR PARA UM CURSO
			/*Professor professor = new Professor("1",db);
			Course course = AbstractCourse.getRealCourse("12345",db);
			 adm.addProfessorToCourse(professor,course);*/
			
			//DELETAR CURSO
			//adm.deleteCourse("1005160551380");
	/*

		}catch(Exception e){
			System.err.println(e.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,e);
			
		 }
	 }*/
}

