package br.ufal.graw.exception;
/**
	DisciplineException is thrown by a discipline object.
 */
public class CourseException extends CommunityException{
	
	private String ID;

	public CourseException(String message){
		super(message);
	}
	
	public CourseException(String message, String ID){
		super(message);
		this.ID = ID;
	}
	
}

