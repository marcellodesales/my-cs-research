/**
 * UserNotFoundException.java
 *
 * @author Marcello Junior
 */

package br.ufal.bibliweb.exception;

public class UserNotFoundException extends BibliWebException{
	
	private String ID;
	
	public UserNotFoundException(String message, String userID){
		super(message);
		this.ID = userID;
	}
}

