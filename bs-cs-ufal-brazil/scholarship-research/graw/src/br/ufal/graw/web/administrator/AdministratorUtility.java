/**
 * AdministratorUtility.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.web.administrator;

import br.ufal.graw.Association;
import br.ufal.graw.User;
import br.ufal.graw.Community;
import br.ufal.graw.CommunitySubcategory;
import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Institute;
import br.ufal.graw.Department;
import br.ufal.graw.Discipline;
import br.ufal.graw.AcademicCourse;
import br.ufal.graw.AcademicUser;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.AbstractCourse;
import br.ufal.graw.Student;
import br.ufal.graw.Professor;
import br.ufal.graw.Utility;
import br.ufal.graw.Visibility;
import br.ufal.graw.web.Mail;
import br.ufal.graw.exception.InstituteNotFoundException;
import br.ufal.graw.exception.DepartmentNotFoundException;
import br.ufal.graw.exception.AcademicCourseNotFoundException;
import br.ufal.graw.exception.PersistentInformationException;
import br.ufal.graw.exception.UserDuplicateLoginException;
import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.ResourceNotFoundException;

import java.util.Vector;
import java.util.Date;
import java.util.Hashtable;
import java.sql.SQLException;

public class AdministratorUtility{
	
	DatabaseLayer database;
	Vector result = new Vector();
	public static final String WELCOME_MESSAGE =
	"\n\nINTRUÇÕES DE USO:\n\n"+
	"O graW é sistema que visa, entre outras coisas, fornecer mecanismos para apoiar atividades extra-classe.\n"+
	"Abaixo você encontrará informações para começar a usar o graW.\n\n"+
	"BUSCANDO PELAS COMUNIDADES NO GRAW: \n"+
	"Para ver quais comunidades você poderá ter acesso no graW basta acessar o menu Comunidades e clicar em buscar,\n"+
	"lá você verá as comunidades ou categorias de comunidades que você poderá acessar. Clicando em uma categoria\n"+
	"você verá todas as comunidades que fazem parte da categoria selecionada.\n\n"+
	"POLÍTICAS DE ASSOCIAÇÃO A COMUNIDADES\n"+
	"Cada comunidade tem uma política de associação definida pelo criador da comunidade, podendo ser basicamente\n"+
	"de dois tipos:\n"+
	"ABERTA      - Qualquer usuário pode se associar.\n"+
	"SEMI-ABERTA - O responsável pela comunidade precisa aprovar cada requisição de associação.\n\n"+
	"SE ASSOCIANDO A ALGUMA COMUNIDADE: \n"+
	"Você precisa se associar a alguma comunidade para ter acesso aos recursos que o graW oferece, como Fórum, Chat , Email, etc...\n"+
	"Para se associar é só buscar pela comunidade que você deseja se associar e clicar em: associar-se ou solicitar associação,\n"+
	"essas opções irão variar de acordo com o tipo de associação da comunidade.\n\n"+
	"ACESSANDO UMA COMUNIDADE: \n"+
	"Para acessar as comunidades que você está associado é só clicar em acessar no menu comunidades e escolher a comunidade.\n"+
	"Caso não apareça nenhuma comunidade é porque você ainda não está associado a nenhuma comunidade, então é necessário que \n"+
	"primeiro você se associe a alguma comunidade\n\n"+
	"Qualquer dúvida e/ou sugestoes por favor entre em contato com nosco através do email: graw@tci.ufal.br";
	
	public AdministratorUtility(DatabaseLayer database){
		this.database = database;
	}
	
	public String createInstitute(String name, String abbreviation, int stateID, int countryID)	throws PersistentInformationException{
		String instituteID;
		try{
			instituteID = Utility.getNewID();
			database.update("INSERT INTO institute (instituteID, name, abbreviation, stateID, countryID) VALUES ('"+instituteID+"' , '"+name+"', '"+abbreviation+"','"+stateID+"','"+countryID+"')");
			return instituteID;
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um instituto.");
		}
	}
	
	public String createDepartment(String instituteID, String departmentCode, String name, String webDomain)
		throws InstituteNotFoundException, PersistentInformationException{
		String departmentID;
		
		try{
			if (!Institute.exists(instituteID,this.database)){
				throw new InstituteNotFoundException("Instituto inexistente. ID="+instituteID,instituteID);
			}
			departmentID = Utility.getNewID();
			database.update("INSERT INTO department (instituteID, departmentId, departmentCode, name, webDomain) VALUES ('"+
								instituteID+"' , '"+departmentID+"', '"+departmentCode+"' , '"+name+"','"+webDomain+"')");
			return departmentID;
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um instituto.");
		}
	}
	
	public String createAcademicCourse(String departmentID, String name, String code)
			throws DepartmentNotFoundException, PersistentInformationException{
		String academicCourseID;
		try{
			if (!Department.exists(departmentID,this.database)){
				throw new DepartmentNotFoundException("Departamento inexistente. ID="+departmentID,departmentID);
			}
			String instituteID = Department.getInstituteID(departmentID,database);
			academicCourseID = Utility.getNewID();
			database.update("INSERT INTO academiccourse (instituteID,departmentID,academicCourseID,name,code) VALUES ('"+
							instituteID+"','"+departmentID+"','"+academicCourseID+"','"+name+"', '"+code+"')");
			return academicCourseID;
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um Curso Academico.");
		}
	}
	
	private String createNewUser(String login, String name, String email) throws UserDuplicateLoginException,PersistentInformationException{
		
		String userID = "";
		String password =(new Date().getTime())+"";
		if (AbstractUser.loginExists(login,this.database))
			throw new UserDuplicateLoginException("Já existe um usuário com esse login: " + login);
		else{
			try{
				userID = Utility.getNewID();
				this.database.update("INSERT INTO user (userID,kindOfUserID,login,name,email,password) VALUES ('"+userID+"','"+AbstractUser.USER+"','"+login+"','"+name+"','"+email+"',PASSWORD('"+password+"') )");
				Mail.send("graw@tci.ufal.br","graW - Graduação na WEB",email,name+",Parabéns voce é o mais novo membro do graW",name+",\nPara acessar o sistema utilize as seguintes informações:\n\nLogin: "+login+"\nSenha: "+password+"\n\nLembre-se de alterá-la utilizando o menu de configurações pessoais do graW.\n\nQuaquer dúvida entrar em contato com o administrador do graW\ngraw@tci.ufal.br\nhttp://www.graw.tci.ufal.br"+WELCOME_MESSAGE);
			}catch(SQLException sqle){
				System.err.println(sqle.getMessage());
				throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um usuário.");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println(this.getClass().getName()+" createNewUser - Criado novo usuario com ID="+userID);
		return userID;
	}
	
	public String createNewExternalUser(String login, String name, String email) throws UserDuplicateLoginException{
		String externalUserID = "";
		if (AbstractUser.loginExists(login,this.database)){
			throw new UserDuplicateLoginException("Já existe um usuário com esse login: " + login);
		} else {
			try{
				externalUserID = this.createNewUser(login,name,email);
				this.database.update("INSERT INTO externaluser (userID) VALUES ('"+externalUserID+"') ");
				this.database.update("UPDATE user SET kindOfUserID='"+AbstractUser.EXTERN_USER+"' WHERE userID='"+externalUserID+"'");
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return externalUserID;
	}
		
	private String createAcademicUser(String login, String name, String email, String matriculation, String academicCourseID)
		throws Exception{
		String academicUserID;
		System.out.println(this.getClass().getName()+" createAcademicUser - Entrando");
		try{
			if (!AcademicCourse.exists(academicCourseID,this.database)){
				throw new AcademicCourseNotFoundException("Curso acadêmico inexistente! = "+academicCourseID,academicCourseID);
			} else {
				academicUserID = this.createNewUser(login,name,email);
				System.out.println(this.getClass().getName()+" createAcademicUser - Antes de Atualizar");
//				if (database.update("UPDATE academicuser SET userID='"+academicUserID+"' WHERE (academicCourseID='"+academicCourseID+"' AND email='"+email+"') ") ==0){
				database.update("UPDATE academicuser SET userID='"+academicUserID+"' WHERE (academicCourseID='"+academicCourseID+"' AND matriculation='"+matriculation+"')");
//				}
				System.out.println(this.getClass().getName()+" createAcademicUser - DEPOIS de Atualizar");
				return academicUserID;
			}
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um estudante.");
		}
	}
			
	public String createNewStudent(String login, String name, String email, String matriculation, String academicCourseID)
		throws Exception{
		System.out.println(this.getClass().getName()+" createNewStudent - Entrando");
		String studentID;
		matriculation = matriculation.toUpperCase();
		try{
			if (Student.alreadySigned(matriculation,academicCourseID,this.database))
				throw new UserDuplicateLoginException("Já existe um estudante com matrícula "+matriculation+" pertencente ao curso acadêmico +"+academicCourseID);
			else
				if (AbstractUser.loginExists(login,this.database))
					throw new UserDuplicateLoginException("Já existe um usuário	com login "+login);
				else {
					studentID = this.createAcademicUser(login,name,email,matriculation,academicCourseID);
					this.database.update("UPDATE user SET kindOfUserID='"+AbstractUser.STUDENT+"' WHERE userID='"+studentID+"'");
					this.database.update("INSERT INTO student (userID) VALUES ('"+studentID+"') ");
					return studentID;
				}
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um estudante. ");
		}
	}
	
	public String createNewProfessor(String login, String name, String email, String matriculation, String academicCourseID)
		throws Exception{
			
		String professorID;
		matriculation = matriculation.toUpperCase();
		try{
			if (Professor.alreadySigned(matriculation,academicCourseID,this.database))
				throw new UserDuplicateLoginException("Já existe um professor com matrícula "+matriculation+" pertencente ao curso acadêmico +"+academicCourseID);
			else
			if (AbstractUser.loginExists(login,this.database))
				throw new UserDuplicateLoginException("Já existe um usuário	com login "+login);
			else {
				professorID = this.createAcademicUser(login,name,email,matriculation,academicCourseID);
				database.update("UPDATE user SET kindOfUserID='"+AbstractUser.PROFESSOR+"' WHERE userID='"+professorID+"'");
				database.update("INSERT INTO professor (userID) VALUES ('"+professorID+"') ");
				return professorID;
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um estudante. ");
		}
	}
	
	public String signNewProfessor(String login, String name, String matriculation){
		Vector result = this.database.query("SELECT academicCourseID,userName FROM academicuser WHERE kindOfUserID='"+User.PROFESSOR+"' AND matriculation ='"+matriculation+"'");
		String userName = (String)((Hashtable)result.firstElement()).get("userName");
		String academicCourseID = (String)((Hashtable)result.firstElement()).get("academicCourseID");
		String email = userName;
		String userID = "";
		try {
			AcademicCourse ac = new AcademicCourse(academicCourseID,this.database);
			email += "@"+ac.getDepartment().getWebDomain();
			userID = this.createNewProfessor(login,name,email,matriculation,academicCourseID);
		} catch (Exception e){
			e.printStackTrace();
		}
		return userID;
	}
	
	public String signNewStudent(String login, String name, String matriculation){
		Vector result = this.database.query("SELECT academicCourseID,userName FROM academicuser WHERE kindOfUserID='S' AND matriculation ='"+matriculation+"'");
		String userName = (String)((Hashtable)result.firstElement()).get("userName");
		String academicCourseID = (String)((Hashtable)result.firstElement()).get("academicCourseID");
		String email = userName;
		String userID = "";
		try {
			AcademicCourse ac = new AcademicCourse(academicCourseID,this.database);
			email += "@"+ac.getDepartment().getWebDomain();
			userID = this.createNewStudent(login,name,email,matriculation,academicCourseID);
		} catch (Exception e){}
		return userID;
	}
	
	
//	public String signNewStudent(String login, String name, String matriculation,String email, String academicCourseID){
//		//Vector result = this.database.query("SELECT academicCourseID FROM academicuser WHERE kindOfUserID='S' AND matriculation ='"+matriculation+"'");
//		//String userName = (String)((Hashtable)result.firstElement()).get("userName");
//		//String academicCourseID = (String)((Hashtable)result.firstElement()).get("academicCourseID");
//		System.out.println(this.getClass().getName()+" signNewStudent - Entrando");
//		String userID = "";
//		try {
//			AcademicCourse ac = new AcademicCourse(academicCourseID,this.database);
//			userID = this.createNewStudent(login,name,email,matriculation,academicCourseID);
//		} catch (Exception e){}
//		return userID;
//	}
	
	public String createCommunity(String title,
							      String description,
							      int visibility,
							      int association,
							      String kind,
							      String subcategoryID,
							      String status,
							      String responsibleUserID,
							      int tools
								  ) throws Exception{
		String communityID;
	  	try{
			if (!AbstractUser.exists(responsibleUserID,this.database)){
				throw new UserNotFoundException("Usuário não encontrado com ID = "+responsibleUserID,responsibleUserID);
			} else
			if (!CommunitySubcategory.exists(subcategoryID,this.database)){
				throw new ResourceNotFoundException("Subtópico inexistente ID = "+subcategoryID);
			}
			communityID = Utility.getNewID();
			description = Utility.getTextField(description);
			CommunitySubcategory cs = new CommunitySubcategory(subcategoryID,this.database);
			database.update("INSERT INTO community (communityID,title,description,visibility,association,kind,subcategoryID,categoryID,status,userID,tools) VALUES ('"+
							communityID+"','"+title+"','"+description+"','"+visibility+"','"+association+"','"+kind+"','"+subcategoryID+"','"+cs.getCategory().getID()+"','"+status+"','"+responsibleUserID+"','"+tools+"')");
			return communityID;
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar uma comunidade.");
		}
	}
		
	public String createCourse(String title,
							   String description,
							   int visibility,
							   int association,
							   String kind,
							   String subcategoryID,
							   String status,
							   String responsibleUserID,
							   int tools,
							   String program,
							   String hours,
							   String bibliography
							  ) throws Exception{
		String courseID;
		try{
		    program = Utility.getTextField(program);
			bibliography = Utility.getTextField(bibliography);
			courseID = this.createCommunity(title,description,visibility,association,kind,subcategoryID,status,responsibleUserID,tools);
			database.update("INSERT INTO course (communityID,program,hours,bibliography) VALUES ('"+
								courseID+"','"+program+"','"+hours+"','"+bibliography+"')");
			return courseID;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um curso.");
		}
	}
	
	/* Cria uma disciplina associado a um curso academico*/
	public String createDiscipline(String title,
								   String description,
								   int visibility,
								   int association,
								   String subcategoryID,
								   String responsibleUserID,
							   	   String program,
							   	   String hours,
							   	   String bibliography,
								   String disciplineCode,
								   String academicCourseID
							  ) throws Exception{
		
		try{
			if (!AcademicCourse.exists(academicCourseID,this.database)){
				throw new AcademicCourseNotFoundException("Curso academico Inexistente ID = "+academicCourseID,academicCourseID);
			} else
			if (!AbstractUser.exists(responsibleUserID,this.database)){
				throw new UserNotFoundException("Usuário não encontrado com ID = "+responsibleUserID,responsibleUserID);
			} else
			if (!CommunitySubcategory.exists(subcategoryID,this.database)){
				throw new ResourceNotFoundException("Subcategoria Inexistente ID = "+subcategoryID,subcategoryID);
			}
			AcademicCourse ac = new AcademicCourse(academicCourseID,this.database);
			String disciplineID = this.createCourse(title,description,visibility,association,Community.DISCIPLINE,subcategoryID,Community.WAITING,responsibleUserID,Community.ALL_TOOLS,program,hours,bibliography);
			database.update("INSERT INTO discipline (communityID,disciplineCode,instituteID,departmentID,academicCourseID) VALUES ('"+disciplineID+"','"+disciplineCode+"','"+ac.getInstitute().getID()+"','"+ac.getDepartment().getID()+"','"+academicCourseID+"')");
			return disciplineID;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar uma disciplina.");
		}
	}
	
	/**
	 Cria um grupo subordinado a um curso */
	public String createGroup(String title,
							  String description,
							  int visibility,
							  int association,
							  String subcategoryID,
							  String responsibleUserID,
							  int tools,
							  String goal,
							  String courseID
							  ) throws Exception{
		String groupID;
		
		try{
			if (!AbstractCourse.exists(courseID,this.database)){
				throw new PersistentInformationException("Curso Inexistente ID = "+courseID);
			}else
			if (!AbstractUser.exists(responsibleUserID,this.database)){
				throw new UserNotFoundException("Usuário não encontrado com ID = "+responsibleUserID,responsibleUserID);
			} else
			if (!CommunitySubcategory.exists(subcategoryID,this.database)){
				throw new ResourceNotFoundException("Subcategoria Inexistente ID = "+subcategoryID,subcategoryID);
			}
			groupID = this.createCommunity(title,description,visibility,association,Community.GROUP,subcategoryID,Community.WAITING,responsibleUserID,Community.ALL_TOOLS);
			database.update("INSERT INTO grouping (groupingID,goal,courseID) VALUES ('"+
								groupID+"','"+goal+"','"+courseID+"')");
			return groupID;
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um grupo.");
		}
	}
	
	/**
	 Cria um grupo independente de um curso */
	public String createGroup(String title,
							  String description,
							  int visibility,
							  int association,
							  String subcategoryID,
							  String responsibleUserID,
							  int tools,
							  String goal
							 ) throws Exception{
		String groupID;
		try{
			if (!AbstractUser.exists(responsibleUserID,this.database)){
				throw new UserNotFoundException("Usuário não encontrado com ID = "+responsibleUserID,responsibleUserID);
			} else
			if (!CommunitySubcategory.exists(subcategoryID,this.database)){
				throw new ResourceNotFoundException("Subcategoria Inexistente ID = "+subcategoryID,subcategoryID);
			}
			groupID = this.createCommunity(title,description,visibility,association,Community.GROUP,subcategoryID,Community.WAITING,responsibleUserID,Community.ALL_TOOLS);
			database.update("INSERT INTO grouping (groupingID,goal) VALUES ('"+
								groupID+"','"+goal+"')");
			return groupID;
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um grupo.");
		}
	}
	
	public String createExtraCourse(String title,
								   	String description,
								   	int visibility,
								   	int association,
								   	String subcategoryID,
								   	String responsibleUserID,
							   	   	String program,
							   	   	String hours,
							   	   	String bibliography
							  ) throws Exception{
		
		try{
			if (!AbstractUser.exists(responsibleUserID,this.database)){
				throw new UserNotFoundException("Usuário não encontrado com ID = "+responsibleUserID,responsibleUserID);
			} else
			if (!CommunitySubcategory.exists(subcategoryID,this.database)){
				throw new ResourceNotFoundException("Subcategoria Inexistente ID = "+subcategoryID,subcategoryID);
			}
			String extraCourseID = this.createCourse(title,description,visibility,association,Community.EXTRA_COURSE,subcategoryID,Community.WAITING,responsibleUserID,Community.ALL_TOOLS,program,hours,bibliography);
			database.update("INSERT INTO extracourse (communityID) VALUES ('"+extraCourseID+"') ");
			return extraCourseID;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um estudante. ");
		}
	}
	
	/* Cria um curso extra Curricular associado a um curso academico*/
	public String createExtraCourse(String title,
								   	String description,
								   	int visibility,
								   	int association,
								   	String subcategoryID,
								   	String responsibleUserID,
							   	   	String program,
							   	   	String hours,
							   	   	String bibliography,
									String academicCourseID
							  ) throws Exception{
		try{
			if (!AcademicCourse.exists(academicCourseID,this.database)){
				throw new PersistentInformationException("Curso Inexistente ID = "+academicCourseID);
			}else
			if (!AbstractUser.exists(responsibleUserID,this.database)){
				throw new UserNotFoundException("Usuário não encontrado com ID = "+responsibleUserID,responsibleUserID);
			} else
			if (!CommunitySubcategory.exists(subcategoryID,this.database)){
				throw new ResourceNotFoundException("Subcategoria Inexistente ID = "+subcategoryID,subcategoryID);
			}
			AcademicCourse ac = new AcademicCourse(academicCourseID,this.database);
			String extraCourseID = this.createCourse(title,description,visibility,association,Community.EXTRA_COURSE,subcategoryID,Community.WAITING,responsibleUserID,Community.ALL_TOOLS,program,hours,bibliography);
			database.update("INSERT INTO extracourse (communityID,instituteID,departmentID,academicCourseID) VALUES ('"+extraCourseID+"','"+ac.getInstitute().getID()+"','"+ac.getDepartment().getID()+"','"+academicCourseID+"') ");
			return extraCourseID;
		}catch(SQLException sqle){
			sqle.printStackTrace();
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um estudante. ");
		}
	}
	
	
	public void assignProfessor(Professor professor, Discipline discipline)
		throws PersistentInformationException{
		try{
			database.update("INSERT INTO teaches (userID, communityID) VALUES ('"+professor.getID()+"','"+discipline.getID()+"') ");
		}catch(SQLException sqle){
			throw new PersistentInformationException("Erro no banco de dados ao tentar cadastrar um estudante. ");
		}
	}
	
	public static void main(String[] args){
		/*DatabaseLayer db = new DatabaseLayer();
		AdministratorUtility adm = new AdministratorUtility(db);
		String academicCourseID = "1010426419860";
		 */
		try {
			/*
			CommunitySubcategory cs = new CommunitySubcategory("1",db);
			AbstractUser user = new Professor("1005249617900",db);
			AcademicCourse ac = new AcademicCourse("1010426419860",db);
			Community disc = new Discipline("1004739136560",db);
			 */
			System.out.println(AdministratorUtility.WELCOME_MESSAGE);
			//adm.createNewStudent("marcellojunior","Marcello de Sales","marcellojunior@hotmail.com","marcello","1998G55D001t",academicCourseID);
			//adm.createNewProfessor("tanemb","Adrews Tanembaum","tanembaum@hotmail.com","ast","2002G55",academicCourseID);
			
			/*adm.createDiscipline("Redes de Computadores I","Toda a história de redes, seguindo com arquiteturas de redes, protocolos de comunicações e outros..."
					,Visibility.INTRA_INSTITUTION,Association.OPENED,cs.getID(),user.getID()
					 ,"03/02 - Capitulo 1, 04/02 - Capítulo 2","4","Computer Networks, 3rd Edition"
			 ,"TCICN",ac.getID());
			 */
			
			/*adm.createGroup("The Bit Exterminators","Será estudado todas as novas abordagens de como funciona um computador quântico."
								,Visibility.INTER_INSTITUTION,Association.SEMI_OPENED,cs.getID(),user.getID()
			 ,Community.ALL_TOOLS,"Desvendar os segredos dos bits quânticos");
			 */
			
			/*adm.createGroup("@@@ Genomics @@@","Aborda tudo sobre genoma, desde sequenciamento de DNA e algoritmos genéticos em Redes Distribuídas!"
				,Visibility.INTER_INSTITUTION,Association.SEMI_OPENED,cs.getID(),user.getID()
			 ,Community.ALL_TOOLS,"Descobertas de novos sequenciamentos, algorimos melhores...",disc.getID());
			 */
			
			/*adm.createExtraCourse("Introdução ao Protocolo TCP/IP","Uma descrição das principais características do protocolo TCP sobre IP. Camadas, Frame Relay, faixas e outros temas serão abordados!"
			 ,Visibility.PUBLIC,Association.OPENED,cs.getID(),user.getID(),"03/09-06/09","16","Computer Networks 3rd Edition, http://www.tcpip.org");
			 */
			
			/*adm.createExtraCourse("Introdução ao Protocolo IPv6","Tudo sobre IPv6 que será implantado em breve na Internet."
				  ,Visibility.PRIVATE,Association.CLOSED,cs.getID(),user.getID(),"Ainda em definição","Indefinido"
			 ,"Computer Networks 3rd Edition, http://www.google.com/networks/ipv6/, http://www.tcpip.org",ac.getID());
			 */
		} catch (Exception e){
			e.printStackTrace();
		}	}
}

