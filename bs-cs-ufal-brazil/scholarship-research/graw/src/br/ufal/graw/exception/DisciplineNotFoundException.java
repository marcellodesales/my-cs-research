/**
 * DisciplineNotTaughtException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 21/06/2001
 */

package br.ufal.graw.exception;
/**
	DisciplineNotFoundException is thrown in an attempt of creating a new discipline
 	that doesn't exist.
*/
public class DisciplineNotFoundException extends DisciplineException{
	
	/** Constructs a DisciplineNotFoundException with the specified detail message. */
	public DisciplineNotFoundException(String message, String code){
		super(message, code);
	}
	
	public DisciplineNotFoundException(String message){
		super(message);
	}
}

