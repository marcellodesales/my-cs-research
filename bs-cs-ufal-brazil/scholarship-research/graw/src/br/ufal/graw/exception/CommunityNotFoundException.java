/**
 * CommunityNotFoundException.java
 *
 * @author Created by Marcello de Sales
 */

package br.ufal.graw.exception;

public class CommunityNotFoundException extends CommunityException{
	
	public CommunityNotFoundException(String message,String ID){
		super(message,ID);
	}
	
	public CommunityNotFoundException(String message){
		super(message);
	}
}

