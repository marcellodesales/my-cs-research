/**
 * GroupNotFoundException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

public class GroupNotFoundException extends GroupException{
	
	public GroupNotFoundException(String message){
		super(message);
	}

	public GroupNotFoundException(String message, String ID){
		super(message, ID);
	}
}

