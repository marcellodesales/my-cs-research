/**
 * CategoryAlreadyExistsException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

import br.ufal.graw.exception.CategoryException;

public class CategoryAlreadyExistsException extends CategoryException{
	
	public CategoryAlreadyExistsException(String message){
		super(message);
	}
	
	public CategoryAlreadyExistsException(String message, String ID){
		super(message,ID);
	}
}

