package br.ufal.graw.web.validation;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.AcademicUser;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Regra de validacao para campina grande.
 *
 * @author Rodrigo Paes
 * @version 	%I%, %G%
 */
public class CampinaGrandeValidator implements ValidatorIF{
	
	private List errors;
	private DefaultValidator defaultValidator;
	private UfalValidator ufalValidator;
	
	public CampinaGrandeValidator(){
		this.errors = new ArrayList();
		this.defaultValidator = new DefaultValidator();
		this.ufalValidator = new UfalValidator();
	}
	
	private void init(){
		this.errors.clear();
	}
	
	
	/** Algor�tmo:
	 * Recebe como parametro o email e verifica no pr� cadastro, e se o email existir, ent�o
	 * entao � validado.
	 */
	public List validate(Hashtable parameters){
		this.init();
		this.errors = this.defaultValidator.validate(parameters);
		String email = (String)parameters.get("email");
		String academicCourseId = this.ufalValidator.getAcademicCourseID(parameters);
		UfalUserBean user = this.ufalValidator.getUser(parameters);
		
		Iterator iter = this.ufalValidator.getErrors().iterator();
		while (iter.hasNext()){
			this.errors.add(iter.next());
		}
		if (email == null || email.trim().equals("")){
			this.errors.add("Email n�o informado");
		}
		
		iter = this.verifyPreRegistration(user,academicCourseId,email).iterator();
		while (iter.hasNext()){
			this.errors.add(iter.next());
		}
		
		
		return this.errors;
	}
	
	
	
	/**
	 * Verifica se o usu�rio est� previamente cadastrado no graW.
	 * @return Uma lista vazia se o usu�rio est� inserido no pr� cadastro. Se ele n�o
	 * estiver retorna uma mensagem de erro.
	 */
	public List verifyPreRegistration(UfalUserBean user, String academicCourseID, String email){
		List errors = new ArrayList();
		DatabaseLayer grawDatabase = new DatabaseLayer();
		Vector result = AcademicUser.existsEmail(email,academicCourseID,grawDatabase);
		if (result.size()<=0){
			errors.add("Email inexistente para esse curso acad�mico!");
		}else{
			Hashtable hash = (Hashtable)result.firstElement();
			user.setEmail((String)hash.get("email"));
			user.setRegistration((String)hash.get("matriculation"));
		}
		grawDatabase.disconnect();
		return errors;
	}
	
	
	/**
	 *
	 */
	public static void main(String[] args){
		CampinaGrandeValidator campinaGrandeValidator = new CampinaGrandeValidator();
		Hashtable param = new Hashtable();
		param.put("email","teste");
		param.put("login","login");
		param.put("academicCourseID","1025296515353");
		
		List result = campinaGrandeValidator.validate(param);
		
		UfalComputerScienceValidator.printList(result);
		
		
	}
	
}

