/**
 * DepartmentAlreadyExistsException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

public class DepartmentAlreadyExistsException extends DepartmentException {
	
	public DepartmentAlreadyExistsException(String message, String ID){
		super(message, ID);
	}
}

