/**
 * DisciplineNotTaughtException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 25/06/2001
 */

package br.ufal.graw.exception;
/**
	DisciplineNotTaughtException is thrown by a Professor, in an attempt to access a
 	discipline that the object doesn't teach.
 */
public class DisciplineNotTaughtException extends UserException{
	
	/** The discipline code that threw the exception. */
	private String disciplineCode;
	
	/** Constructs a DisciplineNotTaughtException with the specified detail message. */
	public DisciplineNotTaughtException(String message){
		super(message);
	}
}


