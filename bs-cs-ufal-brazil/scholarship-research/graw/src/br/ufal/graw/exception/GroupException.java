package br.ufal.graw.exception;

public class GroupException extends GrawException{
	

	private String ID;
	
	/** Constructs a GroupException with the specified detail message. */
	public GroupException(String message){
		super(message);
	}

	/** Constructs a GroupException with the specified detail message and ID. */
	public GroupException(String message, String ID){
		super(message);
	}
	
	public String getID(){
		return this.ID;
	}
}
