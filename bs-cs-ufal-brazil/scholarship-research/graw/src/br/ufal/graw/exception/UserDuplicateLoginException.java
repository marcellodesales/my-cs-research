/**
 * UserDuplicateLoginException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 */

package br.ufal.graw.exception;

import br.ufal.graw.User;
/**
	UserDuplicateLoginException is thrown in an attempt of creating either a new
 	Professor or a Student, with a login that already exists.
*/
public class UserDuplicateLoginException extends UserException{

	/** Constructs a UserDuplicateLoginException with the specified detail message. */
	public UserDuplicateLoginException(String message){
		super(message);
	}
	
	/** Constructs a UserDuplicateLoginException with the specified detail message. */
	public UserDuplicateLoginException(String message, User user){
		super(message, user);
	}
}

