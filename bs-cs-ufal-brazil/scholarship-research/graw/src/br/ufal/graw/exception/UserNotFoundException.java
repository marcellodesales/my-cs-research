/**
 * UserNotFoundException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 */

package br.ufal.graw.exception;

import br.ufal.graw.User;
/**
	UserNotFoundException is thrown in an attempt of creating either a new Professor or
    Student that doesn't exist.
*/
public class UserNotFoundException extends UserException{
	
	private String ID;
	
	/** Constructs a UserNotFoundException with the specified detail message. */
	public UserNotFoundException(String message){
		super(message);
	}

	/** Constructs a UserNotFoundException with the login, the password used and a specified detail message. */
	public UserNotFoundException(String message, User user){
		super(message, user);
	}
	
	/** Constructs a UserNotFoundException with the login, the password used and a specified detail message. */
	public UserNotFoundException(String message, String ID){
		super(message);
		this.ID = ID;
	}
	
	public String getID(){
		return this.ID;
	}
	
}

