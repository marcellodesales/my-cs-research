/**
 * DisciplineException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 25/06/2001
 */

package br.ufal.graw.exception;
/**
	DisciplineException is thrown by a discipline object.
 */
public class DisciplineException extends CourseException{
	
	/** The discipline code that threw the exception. */
	private String code;
	
	/** Constructs a DisciplineException with the specified detail message. */
	public DisciplineException(String message, String code){
		super(message);
		this.code = code;
	}
	
	public DisciplineException(String message){
		super(message);
	}

	/** Gets the code of the discipline. */
	public String getCode(){
		return this.code;
	}
}

