/**
 * MessageException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 27/06/2001
 */

package br.ufal.graw.exception;
/**
	MessageException is thrown by a Message object.
 */
public class MessageException extends GrawException{
	
	/** The message ID that threw the exception. */
	private String ID;
	
	/** Constructs a MessageException with the specified detail message. */
	public MessageException(String message, String ID){
		super(message);
		this.ID = ID;
	}
	
	/** Returns the message ID. */
	public String getID(){
		return this.ID;
	}
}

