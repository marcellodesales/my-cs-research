/**
 * ISBNIncorrectFormException.java
 *
 * @author Marcello Junior
 */

package br.ufal.bibliweb.exception;

public class IncorrectISBNFormException extends ExemplarException{
	
	private String ISBN;
	
	public IncorrectISBNFormException(String message, String ISBN){
		super(message);
		this.ISBN = ISBN;
	}
	
	public String getISBN(){
		return this.ISBN;
	}
}

