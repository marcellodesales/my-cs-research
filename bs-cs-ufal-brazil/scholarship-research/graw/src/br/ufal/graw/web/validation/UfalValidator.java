package br.ufal.graw.web.validation;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import br.ufal.graw.AcademicUser;
import br.ufal.graw.AcademicCourse;
import br.ufal.graw.DatabaseLayer;


/**
 * Validador genérico para usuários da UFAL
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
	 * @param UfalUserBean 		O Usuário que se deseja validar. Forma na hashtable:["user",UfalUserBean]
	 * @param String 			O código do curso para o qual esse usuário será validado. Forma: ["courseCode",String]
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
			
			/* Lê os parametros necessários para realizar a estratégia de validação
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
			
			/* Verifica se a matrícula já existe no banco de dados */
			iter = this.verifyAcademicUserAlreadySigned(user,academicCourseID,login).iterator();
			while (iter.hasNext()){
				this.errors.add(iter.next());
			}
			
			
			/* Se for um professor, não temos como saber a qual curso ele pertence */
			if (UfalUserBean.PROFESSOR.equalsIgnoreCase(user.getKindOfUser())){
				this.errors.add("De acordo com a base de dados da UFAL você é um professor, e não é possível saber qual o curso que você está associado, portanto para efetivar o seu cadastro entre em contato conosco pessoalmente, ou através do nosso email.");
			}else{
				System.out.println(user.getRegistration());
				courseExtracted = this.extractCourseCodeFromRegistration(user.getRegistration());
				System.out.println(courseExtracted);
				courseExtractedAsInt = Integer.parseInt(courseExtracted);
				
				/* Verifica se o aluno realmente pertence ao curso que diz pertencer. */
				if (  courseExtractedAsInt != courseCodeAsInt){
					this.errors.add("Segundo a base de dados da UFAL você não pertence ao curso que você está tentando se cadastrar");
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
				errors.add("Já existe um usuário cadastrado com essa matrícula! = "+user.getRegistration());
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
				errors.add("Curso acadêmico inválido! = "+academicCourseID);
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
				this.errors.add("Usuário não informado.");
			}
		}catch(Exception e){
			this.errors.add("Usuário não informado.");
		}
		return user;
	}
	
	public String getCourseCode(Hashtable parameters){
		String courseCode 		= null;
		try{
			courseCode 	= (String)parameters.get("courseCode");
			if (courseCode==null){
				this.errors.add("Código do curso não informado.");
			}
		}catch(Exception e){
			this.errors.add("Código do curso não informado.");
		}
		return courseCode;
	}
	
	public String getAcademicCourseID(Hashtable parameters){
		String academicCourseID 		= null;
		try{
			academicCourseID 	= (String)parameters.get("academicCourseID");
			if (academicCourseID==null){
				this.errors.add("Código do curso acadêmico não informado.");
			}
		}catch(Exception e){
			this.errors.add("Código do curso acadêmico não informado.");
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

