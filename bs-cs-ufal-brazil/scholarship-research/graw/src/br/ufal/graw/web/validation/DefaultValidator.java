package br.ufal.graw.web.validation;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


import br.ufal.graw.AcademicUser;
import br.ufal.graw.AbstractUser;
import br.ufal.graw.DatabaseLayer;

/**
 * Validador padrão.
 *
 * @author Rodrigo Paes
 * @version 	%I%, %G%
 */
public class DefaultValidator implements ValidatorIF{
	
	private List errors;
	
	public DefaultValidator(){
		this.errors = new ArrayList();
	}
		
	public List validate(Hashtable parameters){
		this.errors.clear();
		Iterator iter = this.verifyDuplicatedLogin(this.getLogin(parameters)).iterator();
		while (iter.hasNext()){
			this.errors.add(iter.next());
		}
		return this.errors;
	}
	
	public List getErrors(){
		return this.errors;
	}
	
	public String getLogin(Hashtable parameters){
		String login 		= null;
		try{
			login 	= (String)parameters.get("login");
			if (login==null){
				this.errors.add("Nome de usuário não informado.");
			}
		}catch(Exception e){
			this.errors.add("Nome do usuário não informado.");
		}
		return login;
	}
	
	
	public List verifyDuplicatedLogin(String login){
		List errors = new ArrayList();
		DatabaseLayer grawDatabase = new DatabaseLayer();
		if (AbstractUser.loginExists(login,grawDatabase)){
			errors.add("Nome de usuário já existente! = "+login);
		}
		grawDatabase.disconnect();
		return errors;
	}
	
	
	
	
}

