/**
 * CouserNotFoundException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

public class CourseNotFoundException extends CourseException{
	
	public CourseNotFoundException(String message){
		super(message);
	}
	
	public CourseNotFoundException(String message, String ID){
		super(message, ID);
	}

	
}

