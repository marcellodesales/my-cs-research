/**
 * CategoryException.java
 *
 * @author GraW's Group
 */

package br.ufal.graw.exception;

import br.ufal.graw.exception.GrawException;

public class CategoryException extends GrawException{
	
	private String ID;

	public CategoryException(String message){
		super(message);
	}
	
	public CategoryException(String message, String ID){
		super(message);
		this.ID = ID;
	}
}

