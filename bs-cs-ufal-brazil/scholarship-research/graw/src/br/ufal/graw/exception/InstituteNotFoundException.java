/**
 * InstituteNotFoundException.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.exception;

import br.ufal.graw.exception.InstituteException;

public class InstituteNotFoundException extends InstituteException{
	
	public InstituteNotFoundException(String message, String ID){
		super(message,ID);
	}
}

