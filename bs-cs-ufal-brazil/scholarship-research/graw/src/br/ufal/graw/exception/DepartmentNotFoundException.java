/**
 * DepartmentNotFoundException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

public class DepartmentNotFoundException extends DepartmentException{
	
	public DepartmentNotFoundException(String message, String ID){
		super(message, ID);
			
	}
}

