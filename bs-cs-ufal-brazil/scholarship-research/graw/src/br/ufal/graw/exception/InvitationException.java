package br.ufal.graw.exception;

/**
	UserException is thrown by either a Professor or a Student.
*/
public class InvitationException extends GrawException{

	/** Constructs a UserException with the specified detail message. */
	public InvitationException(String message){
		super(message);
	}

}
