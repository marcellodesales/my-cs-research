/**
 * ResourceException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 25/06/2001
 */

package br.ufal.graw.exception;
/**
	ResourceException is thrown by either a document, link or message.
*/
public class ResourceException extends GrawException{
	
	/** The resource ID that threw the exception. */
	private String ID;
	
	/** Constructs a ResourceException with the specified detail message. */
	public ResourceException(String message, String ID){
		super(message);
		this.ID = ID;
	}
	
	public ResourceException(String message){
		super(message);
	}
	
	/** Returns the resource ID. */
	public String getID(){
		return this.ID;
	}
}

