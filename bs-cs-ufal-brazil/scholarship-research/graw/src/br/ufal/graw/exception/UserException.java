/**
 * UserException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 21/06/2001
 */

package br.ufal.graw.exception;
import br.ufal.graw.User;

/**
	UserException is thrown by either a Professor or a Student.
*/
public class UserException extends GrawException{
	
	/** The user that helped to throw the exception. */
	private User user;
	
	/** Constructs a UserException with the specified detail message. */
	public UserException(String message){
		super(message);
	}

	/** Constructs a UserException a specified detail message and the user. */
	public UserException(String message, User user){
		super(message);
		this.user = user;
	}
	
	/** Returns the used user login. */
	public String getLogin(){
		return this.user.getLogin();
	}
	
	/** Returns the used user password. */
	public String getPassword(){
		return this.user.getPassword();
	}
	
	/** Returns the used user ID. */
	public String getID(){
		return this.user.getID();
	}
}

