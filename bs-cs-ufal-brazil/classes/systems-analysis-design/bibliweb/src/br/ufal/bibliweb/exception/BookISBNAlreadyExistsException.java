/**
 * BookISBNAlreadyExistsException.java
 *
 * @author Marcello de Sales
 */

package br.ufal.bibliweb.exception;

public class BookISBNAlreadyExistsException extends ExemplarException{
	
	private String ISBN;
	
	public BookISBNAlreadyExistsException(String message, String ISBN){
		super(message);
		this.ISBN = ISBN;
	}
	
	public String getISBN(){
		return this.ISBN;
	}
}

