/**
 * DisciplineDuplicateCodeException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 21/06/2001
 */

package br.ufal.graw.exception;
/**
	DisciplineDuplicateCodeException is thrown in an attempt of creating a new
 	discipline with a code that already exists.
*/
public class DisciplineDuplicateCodeException extends DisciplineException{
	
	/** Constructs a DisciplineDuplicateCodeException with the specified detail message. */
	public DisciplineDuplicateCodeException(String message, String code){
		super(message, code);
	}
}

