/**
 * ResourceException.java
 *
 * @author Marcello de Sales
 */

package br.ufal.bibliweb.exception;

public class ResourceException extends BibliWebException{
	
	/** Cria uma nova exceção de recurso não encontrado. */
	public ResourceException(String message){
		super(message);
	}
	
}

