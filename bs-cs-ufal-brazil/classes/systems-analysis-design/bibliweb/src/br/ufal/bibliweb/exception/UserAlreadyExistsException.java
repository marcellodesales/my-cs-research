/**
 * UserAlreadyExistsException.java
 *
 * @author Marcello de Sales
 */

package br.ufal.bibliweb.exception;

public class UserAlreadyExistsException extends UserException{
	
	private String registration;
	
	public UserAlreadyExistsException(String message, String registration){
		super(message);
		this.registration = registration;
	}
	
	/** Retorna a matrícula existente. */
	public String getExitedRegistration(){
		return this.registration;
	}
}

