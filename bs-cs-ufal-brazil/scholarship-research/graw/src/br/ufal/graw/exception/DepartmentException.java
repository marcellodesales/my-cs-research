/**
 * DepartmentException.java
 *
 * @author graW
 */

package br.ufal.graw.exception;

public class DepartmentException extends GrawException{
	
	private String ID;
	
	public DepartmentException(String message, String ID){
		super(message);
		this.ID = ID;
	}
	
	public String getID(){
		return this.ID;
	}
}

