/**
 * AcademicCourseException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

public class AcademicCourseException extends GrawException{
	
	private String ID;
	
	public AcademicCourseException(String message, String ID){
		super(message);
		this.ID = ID;
	}
}

