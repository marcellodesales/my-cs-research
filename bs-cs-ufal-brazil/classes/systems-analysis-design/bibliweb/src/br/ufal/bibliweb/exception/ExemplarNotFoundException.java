/**
 * ExemplarNotFoundException.java
 *
 * @author Marcello Junior
 */

package br.ufal.bibliweb.exception;

public class ExemplarNotFoundException extends BibliWebException{
	
	private String ID;
	
	public ExemplarNotFoundException(String message, String ID){
		super(message);
		this.ID = ID;
	}
}

