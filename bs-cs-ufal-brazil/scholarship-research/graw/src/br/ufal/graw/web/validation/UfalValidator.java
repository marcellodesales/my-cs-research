package br.ufal.graw.web.validation;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import br.ufal.graw.AcademicUser;
import br.ufal.graw.AcademicCourse;
import br.ufal.graw.DatabaseLayer;


/**
 * Validador gen�rico para usu�rios da UFAL
 *
 * @author Rodrigo Paes
 * @version 	%I%, %G%
 */
public class UfalValidator implements ValidatorIF{
	
	private List errors;
	private DefaultValidator defaultValidator;
	
	public UfalValidator(){
		this.errors 			= new ArrayList();
		this.defaultValidator 	= new DefaultValidator();
	}
	
	public List getErrors(){
		return this.errors;
	}
	
	/**
	 * @param UfalUserBean 		O Usu�rio que se deseja validar. Forma na hashtable:["user",UfalUserBean]
	 * @param String 			O c�digo do curso para o qual esse usu�rio ser� validado. Forma: ["courseCode",String]
	 * @param ["login", String] O login que se deseja criar no graW.
	 */
	public List validate(Hashtable parameters){
		this.errors.clear();
		UfalUserBean user 		= null;
		String academicCourseID	= null;
		String courseCode 		= null;
		String courseExtracted 	= null;
		String login 			= null;
		int courseCodeAsInt;
		int courseExtractedAsInt;
		
		try{
			/* Delega a responsabilidade inicial de validacao para o DefaultValidator*/
			this.errors = this.defaultValidator.validate(parameters);
			
			/* L� os parametros necess�rios para realizar a estrat�gia de valida��o
			 * da Ufal.
			 */
			login 				= this.defaultValidator.getLogin(parameters);
			user 				= this.getUser(parameters);
			courseCode 			= this.getCourseCode(parameters);
			academicCourseID 	= this.getAcademicCourseID(parameters);
			courseCodeAsInt 	= Integer.parseInt(courseCode);
			System.out.println("Code: "+courseCode);
			System.out.println("Code(int): "+courseCodeAsInt);
			
			/* Verifica se o curso academico existe */
			Iterator iter = this.verifyAcademicCourse(academicCourseID).iterator();
			while (iter.hasNext()){
				this.errors.add(iter.next());
			}
			
			/* Verifica se a matr�cula j� existe no banco de dados */
			iter = this.verifyAcademicUserAlreadySigned(user,academicCourseID,login).iterator();
			while (iter.hasNext()){
				this.errors.add(iter.next());
			}
			
			
			/* Se for um professor, n�o temos como saber a qual curso ele pertence */
			if (UfalUserBean.PROFESSOR.equalsIgnoreCase(user.getKindOfUser())){
				this.errors.add("De acordo com a base de dados da UFAL voc� � um professor, e n�o � poss�vel saber qual o curso que voc� est� associado, portanto para efetivar o seu cadastro entre em contato conosco pessoalmente, ou atrav�s do nosso email.");
			}else{
				System.out.println(user.getRegistration());
				courseExtracted = this.extractCourseCodeFromRegistration(user.getRegistration());
				System.out.println(courseExtracted);
				courseExtractedAsInt = Integer.parseInt(courseExtracted);
				
				/* Verifica se o aluno realmente pertence ao curso que diz pertencer. */
				if (  courseExtractedAsInt != courseCodeAsInt){
					this.errors.add("Segundo a base de dados da UFAL voc� n�o pertence ao curso que voc� est� tentando se cadastrar");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			this.errors.add("Dados inconsistentes ou faltando.");
		}
		
		return this.errors;
	}
	
	public List verifyAcademicUserAlreadySigned(UfalUserBean user, String academicCourseID, String login){
		List errors = new ArrayList();
		DatabaseLayer grawDatabase = new DatabaseLayer();
		try{
			if (AcademicUser.alreadySigned(user.getRegistration(),academicCourseID,grawDatabase)){
				errors.add("J� existe um usu�rio cadastrado com essa matr�cula! = "+user.getRegistration());
			}
		}catch(Exception e){}
		grawDatabase.disconnect();
		return errors;
		
	}
	
	
	public List verifyAcademicCourse(String academicCourseID){
		List errors = new ArrayList();
		DatabaseLayer grawDatabase = new DatabaseLayer();
		try{
			if (!AcademicCourse.exists(academicCourseID,grawDatabase)){
				errors.add("Curso acad�mico inv�lido! = "+academicCourseID);
			}
		}catch(Exception e){}
		grawDatabase.disconnect();
		return errors;
		
	}
	
	
	public UfalUserBean getUser(Hashtable parameters){
		UfalUserBean user 		= null;
		try{
			user 		= (UfalUserBean)parameters.get("user");
			if (user==null){
				this.errors.add("Usu�rio n�o informado.");
			}
		}catch(Exception e){
			this.errors.add("Usu�rio n�o informado.");
		}
		return user;
	}
	
	public String getCourseCode(Hashtable parameters){
		String courseCode 		= null;
		try{
			courseCode 	= (String)parameters.get("courseCode");
			if (courseCode==null){
				this.errors.add("C�digo do curso n�o informado.");
			}
		}catch(Exception e){
			this.errors.add("C�digo do curso n�o informado.");
		}
		return courseCode;
	}
	
	public String getAcademicCourseID(Hashtable parameters){
		String academicCourseID 		= null;
		try{
			academicCourseID 	= (String)parameters.get("academicCourseID");
			if (academicCourseID==null){
				this.errors.add("C�digo do curso acad�mico n�o informado.");
			}
		}catch(Exception e){
			this.errors.add("C�digo do curso acad�mico n�o informado.");
		}
		return academicCourseID;
	}
	
	
	
	public String extractCourseCodeFromRegistration(String registration){
		String code = null;
		try{
			code = registration.substring(5,7);
		}catch(Exception e){};
		return code;
	}
	
	
	/**
	 *
	 */
	public static void main(String[] args){
		UfalValidator u = new UfalValidator();
		UfalUserBean user = new UfalUserBean();
		Hashtable params = new Hashtable();
		
		user.setName("Rodrigo");
		user.setRegistration("1999g45d041v");
		user.setKindOfUser("Professor");
		
		params.put("user",user);
		params.put("courseCode","55");
		
		List errors = u.validate(params);
		Iterator iter = errors.iterator();
		while (iter.hasNext()){
			Object element = iter.next();
			System.out.println(element);
		}
		
		System.out.println("FIM");
	}
	
}

