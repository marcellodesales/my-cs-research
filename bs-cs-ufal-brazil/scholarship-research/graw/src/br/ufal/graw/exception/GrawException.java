/**
 * NoNamePibicException.java
 *
 * @author Programmed by noNamePibic´s group - UFAL
 * 21/06/2001
 */

package br.ufal.graw.exception;
/**
	NoNamePibicException is thrown by any object of the project.
*/
public class GrawException extends Exception{

	String message;
	/** Constructs a NoNamePibicException with the specified detail message. */
	public GrawException(String message){
		super(message);
	}
	
	public static void main(String args[]){
		try{
			throw new GrawException("testando");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}



