/**
 * ResourceNotFoundException.java
 *
 * @author Marcello de Sales
 */

package br.ufal.bibliweb.exception;

public class ResourceNotFoundException extends ResourceException{
	
	/**
	 * Exceção gerada quando um recurso do ambiente não for encontrado.
	 * EX: Status, Language
	 **/
	public ResourceNotFoundException(String message){
		super(message);
	}
}

