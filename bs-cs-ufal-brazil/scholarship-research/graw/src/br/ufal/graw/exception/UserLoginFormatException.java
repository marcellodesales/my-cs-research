/**
 * UserLoginFormatException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 */

package br.ufal.graw.exception;

import br.ufal.graw.User;
/**
	UserLoginFormatException is thrown when a specified login is incorrectly passed
    to the constructor of either a Professor or a Student. Need to see the requirements
 	of a valid login.
*/
public class UserLoginFormatException extends UserException{
	
	/** Constructs a LoginFormatException with the specified detail message. */
	public UserLoginFormatException(String message){
		super(message);
	}

	/** Constructs a LoginFormatException with the login, the password and a specified detail message. */
	public UserLoginFormatException(String message, User user){
		super(message, user);
	}
}

