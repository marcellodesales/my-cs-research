/**
 * ResourceDuplicateCodeException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 21/06/2001
 */

package br.ufal.graw.exception;
/**
	ResourceDuplicateCodeException is thrown in an attempt of creating either a new
 	document, link or message, with a code that already exists.
*/
public class ResourceDuplicateCodeException extends ResourceException{
	
	/** Constructs a ResourceDuplicateCodeException with the specified detail message. */
	public ResourceDuplicateCodeException(String message, String ID){
		super(message, ID);
	}
}

