package br.ufal.graw.web.validation;

import java.util.List;
import java.util.Hashtable;


/**
 * Essa classe � respons�vel por encapsular a comunica��o com a estrat�gia de valida��o.
 * de um novo usu�rio no sistema.
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

