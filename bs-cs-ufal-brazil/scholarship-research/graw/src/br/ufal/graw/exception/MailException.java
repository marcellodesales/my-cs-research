/**
 * UserException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 21/06/2001
 */

package br.ufal.graw.exception;

/**
	UserException is thrown by either a Professor or a Student.
*/
public class MailException extends GrawException{

	
	private String usedLogin;
	private String usedPassword;

	/** Constructs a UserException with the specified detail message. */
	public MailException(String message){
		super(message);
	}

}
