/**
 * InstituteException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

public class InstituteException extends GrawException{
	
	private String ID;
	
	public InstituteException(String message, String ID){
		super(message);
		this.ID = ID;
	}
}

