/**
 * ResourceNotTaughtException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 25/06/2001
 */

package br.ufal.graw.exception;
/**
	ResourceNotFoundException is thrown in an attempt of creating a new resource, such as
    a document, link or message that doesn't exist.
*/
public class ResourceNotFoundException extends ResourceException{
	
	/** Constructs a ResourceNotFoundException with the specified detail message. */
	public ResourceNotFoundException(String message, String ID){
		super(message, ID);
	}
	public ResourceNotFoundException(String message){
		super(message);
	}
}

