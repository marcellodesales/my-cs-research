/**
 * ResourceException.java
 *
 * @author Marcello de Sales
 */

package br.ufal.bibliweb.exception;

public class ResourceException extends BibliWebException{
	
	/** Cria uma nova exce��o de recurso n�o encontrado. */
	public ResourceException(String message){
		super(message);
	}
	
}

