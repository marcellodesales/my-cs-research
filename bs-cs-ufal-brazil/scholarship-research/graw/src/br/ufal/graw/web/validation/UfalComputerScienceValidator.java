package br.ufal.graw.web.validation;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Iterator;


import br.ufal.graw.AcademicUser;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.DatabaseLayer;

/**
 * Implementa as regas de validação para o curso de ciência da computação.
 *
 * @author Rodrigo Paes
 * @version 	%I%, %G%
 */
public class UfalComputerScienceValidator implements ValidatorIF{
	
	private UfalValidator ufalValidator;
	private DefaultValidator defaultValidator;
	private List errors;
	
	private static final String  COURSE_CODE = "55";
	
	private boolean debug = false;
	
	
	public UfalComputerScienceValidator(){
		this.init();
	}
	
	private void init(){
		this.ufalValidator 		= new UfalValidator();
		this.defaultValidator 	= new DefaultValidator();
		this.errors 			= new ArrayList();
	}
	
	
	/**
	 * @param ["user",UfalUserBean] - Reprentação desse usuário no banco de dados da Ufal.
	 * @param ["academicCourseID", String ] - O ID do curso academico que se deseja associar (ID do banco do graW)
	 * @param ["login", String] - O login com o qual o usuário deseja se cadastar.
	 */
	public synchronized List validate(Hashtable parameters){
		this.init();
		Iterator iter;
		UfalUserBean user 		= null;
//		String courseCode 		= COURSE_CODE;
		String academicCourseID	= null;
		String login			= null;
		
		parameters.put("courseCode",COURSE_CODE);
		
		
		/* Delega ao DefaultValidator a responsabilidade de recuperar alguns dados
		 * dos parametros e depois verifica se houve algum erro.
		 */
		if (debug){ System.out.println("[CS] - validate()"); }
		if (debug){ System.out.println("	[CS] - Erros: "+this.errors.size()); }
		
		
		login 				= this.defaultValidator.getLogin(parameters);
		iter = this.defaultValidator.getErrors().iterator();
		while (iter.hasNext()){
			this.errors.add(iter.next());
		}
		
		/* Delega ao UfalValidator a responsabilidade de recuperar alguns dados
		 * dos parametros e depois verifica se houve algum erro.
		 */
		user 				= this.ufalValidator.getUser(parameters);
//		courseCode 			= this.ufalValidator.getCourseCode(parameters);
		academicCourseID 	= this.ufalValidator.getAcademicCourseID(parameters);
		iter = this.ufalValidator.getErrors().iterator();
		while (iter.hasNext()){
			this.errors.add(iter.next());
		}
		
		if (debug){ System.out.println("	[CS] - Erros dos parametros: "+this.errors.size()); }
		if (debug){
			if (this.errors.size()>0) printList(errors);
		}
		
		
		/* Se os parametros estiverem todos corretos, entao vamos ao algorítmo*/
		if (this.errors.size() == 0){
			
			/*
		 	* Valida esse usuário no validador Default.
		 	*/
			iter = this.defaultValidator.validate(parameters).iterator();
			while (iter.hasNext()){
				this.errors.add(iter.next());
			}
			
			/* Se tudo correu bem vamos ao algorítmo mais específico: */
			if (this.errors.size() == 0){
				if (debug){ System.out.println("	[CS] - Validador default - ok"); }
		
				/* Procurar no pre cadastro. */
				List preRegistrationErrors = this.verifyPreRegistration(user,academicCourseID);
				
				/** Não existe no pré cadastro */
				if (preRegistrationErrors.size()>0){
					if (debug){ System.out.println("	[CS] - Não existe no pré cadastro"); }
					/* Usa o a estratégia da UFAL para validar o usuário */
					iter = this.ufalValidator.validate(parameters).iterator();
					while (iter.hasNext()){
						this.errors.add(iter.next());
					}
				}else{
					/* Já existe no pré cadastro, e está tudo Ok , para efetivar o cadastro. */
					iter = this.ufalValidator.verifyAcademicUserAlreadySigned(user,academicCourseID,login).iterator();
					while (iter.hasNext()){
						this.errors.add(iter.next());
					}
				}
			}else{
				// Problemas na validação genérica.
				System.out.println("Problemas na validação genérica.");
			}
		}else{
			// Problemas nos parametros
			System.out.println("Problemas nos parametros");
		}
		
		return errors;
	}
	
	/**
	 * Verifica se o usuário está previamente cadastrado no graW.
	 * @return Uma lista vazia se o usuário está inserido no pré cadastro. Se ele não
	 * estiver retorna uma mensagem de erro.
	 */
	public List verifyPreRegistration(UfalUserBean user, String academicCourseID){
		List errors = new ArrayList();
		DatabaseLayer grawDatabase = new DatabaseLayer();
		Vector fields = AcademicUser.getAcademicUserFields(user.getRegistration(),academicCourseID,grawDatabase);
		if (fields.size()<=0){
			errors.add("Matrícula inexistente para esse curso acadêmico! ("+user.getRegistration()+")");
		}else{
			Hashtable hash = (Hashtable)fields.firstElement();
			user.setEmail((String)hash.get("email"));
		}
//		if (AcademicUser.alreadySigned(user.getRegistration(),academicCourseID,grawDatabase)){
//			errors.add("Já existe um usuário cadastrado com essa matrícula! = "+user.getRegistration());
//		}
		grawDatabase.disconnect();
		return errors;
	}
	
	
	/**
	 *
	 */
	public static void main(String[] args){
		ValidatorIF u = new UfalComputerScienceValidator();
		ValidatorIF dv = new DefaultValidator();
		UfalUserBean user = new UfalUserBean();
		Hashtable params = new Hashtable();
		List errors;
		
		user.setName("Rodrigo");
		user.setRegistration("1999g55d041v");
		user.setKindOfUser("Aluno");
		
		/** Testando DefaultValidator validator */

//		Verificar login duplicado
		params.put("login","mosca");
		errors = dv.validate(params);
		if (errors.size()>0){
			printList(errors);
			System.out.println("t1: OK!");
		}else{
			System.out.println("t1: FALHOU!");
		}
		
//		Verificar login Normal
		params.put("login","r0ddkfjhie");
		errors = dv.validate(params);
		if (errors.size()<=0){
			System.out.println("t2: OK!");
		}else{
			printList(errors);
			System.out.println("t2: FALHOU!");
		}
		
		
		
		/** Testando ComputerScience validator */
		
//		Testando estudante com o cadastro já efetuado
		
		params.put("user",user);
		params.put("courseCode","55");
		params.put("academicCourseID","1010426419860");
		params.put("login","r0drigopaes");
		
		errors = u.validate(params);
		if (errors.size()>0){
			printList(errors);
			System.out.println("t3: OK!");
		}else{
			System.out.println("t3: FALHOU!");
		}
		
//		Testando estudante com o pre cadastro Ok
		user.setRegistration("1996G55D029V");
		params.put("user",user);
		errors = u.validate(params);
		if (errors.size()<=0){
			System.out.println("t4: OK!");
		}else{
			printList(errors);
			System.out.println("t4: FALHOU!");
		}
		
//		Testando estudante sem o pre cadastro, mas com dados ok!
		user.setRegistration("1998G55D004R");
		params.put("user",user);
		errors = u.validate(params);
		if (errors.size()<=0){
			System.out.println("t4.1: OK!");
		}else{
			printList(errors);
			System.out.println("t4.1: FALHOU!");
		}
		
//		Testando estudante com o curso errado
		user.setRegistration("1998G35D004R");
		params.put("user",user);
		errors = u.validate(params);
		if (errors.size()>0){
			printList(errors);
			System.out.println("t4.2: OK!");
		}else{
			System.out.println("t4.2: FALHOU!");
		}
		
		
//		testando professor com Pré - cadastro OK
		user.setKindOfUser(UfalUserBean.PROFESSOR);
		System.out.println("Inicio do Teste 5: "+user.getKindOfUser());
		user.setRegistration("2309422");
		params.put("user",user);
		errors = u.validate(params);
		if (errors.size()<=0){
			System.out.println("t5: OK!");
		}else{
			printList(errors);
			System.out.println("t5: FALHOU!");
		}
		System.out.println("FIM do Teste 5: "+user.getKindOfUser());
		
//	  	testando professor com Cadastro já efetuado
		user.setRegistration("1120719");
		params.put("user",user);
		errors = u.validate(params);
		if (errors.size()>0){
			printList(errors);
			System.out.println("t6: OK!");
		}else{
			
			System.out.println("t6: FALHOU!");
		}
		
		
		/** Fim de ComputerScience */
		
				
		
//		testando um estudante de outro curso da ufal tentando se cadastrar em computação
		user.setRegistration("1999G27D001V");
		user.setKindOfUser(UfalUserBean.STUDENT);
		params.put("courseCode","55");
		params.put("academicCourseID","1010426419860");
		params.put("user",user);
		errors = u.validate(params);
		if (errors.size()>0){
			System.out.println("t7: OK!");
		}else{
			printList(errors);
			System.out.println("t7: FALHOU!");
		}
		
//		testando um estudante de outro curso da ufal tentando se cadastrar.
		UfalValidator uv = new UfalValidator();
		user.setRegistration("1999G27D001V");
		user.setKindOfUser(UfalUserBean.STUDENT);
		params.put("courseCode","27");
		params.put("user",user);
		errors = uv.validate(params);
		if (errors.size()<=0){
			System.out.println("t8: OK!");
		}else{
			printList(errors);
			System.out.println("t8: FALHOU!");
		}
		
		
		System.out.println("FIM");
		
	}
	
	
	
	public static void printList(List list){
		Iterator iter = list.iterator();
		while (iter.hasNext()){
			Object element = iter.next();
			System.out.println(element);
		}
	}
	
	
	
}

