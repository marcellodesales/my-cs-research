/**
 * CPFIncorrectFormException.java
 *
 * @author Marcello de Sales
 */

package br.ufal.bibliweb.exception;

public class CPFIncorrectFormException extends BibliWebException{
	
	/** Criada quando o CPF do usu�rio est� na forma incorreta. */
	public CPFIncorrectFormException(String message){
		super(message);
	}
}

