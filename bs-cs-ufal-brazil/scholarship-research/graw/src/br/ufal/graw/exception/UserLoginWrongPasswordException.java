/**
 * UserLoginFormatException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 */

package br.ufal.graw.exception;

import br.ufal.graw.User;
/**
	UserLoginWrongPasswordException is thrown when a specified password is incorrectly passed
    to the constructor of either a Professor or a Student. Need to see the requirements
 	of a valid login. The password is case-sensitive.
*/
public class UserLoginWrongPasswordException extends UserException{
	
	/** Constructs a UserLoginWrongPasswordException with the specified detail message. */
	public UserLoginWrongPasswordException(String message){
		super(message);
	}
}

