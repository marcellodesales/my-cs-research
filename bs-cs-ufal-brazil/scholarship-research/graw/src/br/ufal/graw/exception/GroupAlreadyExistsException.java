/**
 * GroupAlreadyExistsException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

public class GroupAlreadyExistsException extends GroupException{
	
	public GroupAlreadyExistsException(String message){
		super(message);
	}

	public GroupAlreadyExistsException(String message, String ID){
		super(message, ID);
	}

}

