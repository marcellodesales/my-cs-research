package br.ufal.graw.exception;
/**
	DisciplineException is thrown by a discipline object.
 */
public class CommunityException extends GrawException{
	
	private String ID;

	public CommunityException(String message){
		super(message);
	}
	
	public CommunityException(String message, String ID){
		super(message);
		this.ID = ID;
	}
	
}

