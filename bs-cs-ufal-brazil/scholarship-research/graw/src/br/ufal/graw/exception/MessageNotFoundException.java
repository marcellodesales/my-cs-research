/**
 * MessageNotFoundException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 27/06/2001
 */

package br.ufal.graw.exception;
/**
	MessageNotFoundException is thrown when a Message object is not found.
 */
public class MessageNotFoundException extends MessageException{
	
	/** Constructs a MessageNotFoundException with the specified detail message. */
	public MessageNotFoundException(String message, String ID){
		super(message, ID);
	}
}

