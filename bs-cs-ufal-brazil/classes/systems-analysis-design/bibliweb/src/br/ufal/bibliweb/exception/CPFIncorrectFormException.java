/**
 * CPFIncorrectFormException.java
 *
 * @author Marcello de Sales
 */

package br.ufal.bibliweb.exception;

public class CPFIncorrectFormException extends BibliWebException{
	
	/** Criada quando o CPF do usuário está na forma incorreta. */
	public CPFIncorrectFormException(String message){
		super(message);
	}
}

