/**
 * AcademicCourseNotFoundException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

public class AcademicCourseNotFoundException extends AcademicCourseException{
	
	public AcademicCourseNotFoundException(String message, String ID){
		super(message,ID);
	}
}

