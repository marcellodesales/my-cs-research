package br.ufal.graw.web.validation;

import java.util.List;
import java.util.Hashtable;


/**
 * Essa classe é responsável por encapsular a comunicação com a estratégia de validação.
 * de um novo usuário no sistema.
 *
 * @author Rodrigo Paes
 * @version 	%I%, %G%
 */

public class Validation{
	
	private ValidatorIF validator;
	
	public Validation(ValidatorIF validator){
		this.validator = validator;
	}
	
	public List validate(Hashtable parameters){
		return this.validator.validate(parameters);
	}
	
}

