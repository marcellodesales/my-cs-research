/**
 * @author UFAL
 * 02/04/2002
 */

package br.ufal.bibliweb.exception;
/**
	BibliWebException is thrown by any object of the project.
*/
public class BibliWebException extends Exception{

	/** Constructs a NoNamePibicException with the specified detail message. */
	public BibliWebException(String message){
		super(message);
	}
	
	public static void main(String args[]){
		try{
			throw new BibliWebException("testando");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}



