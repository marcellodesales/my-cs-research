/**
 * CategoryNotFoundException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

import br.ufal.graw.exception.CategoryException;

public class CategoryNotFoundException extends CategoryException{
	
	public CategoryNotFoundException(String message){
		super(message);
	}
	
	public CategoryNotFoundException(String message, String ID){
		super(message,ID);
	}
}
