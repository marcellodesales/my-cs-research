/**
 * InstituteAlreadyExistsException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

public class InstituteAlreadyExistsException extends InstituteException{
	
	private String abbreviation;
	
	public InstituteAlreadyExistsException(String message, String ID){
		super(message,ID);
	}
}

