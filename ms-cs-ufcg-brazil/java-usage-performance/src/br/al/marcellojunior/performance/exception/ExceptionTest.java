/*
 * Criado em 01/11/2003
 *
 * Para alterar o gabarito para este arquivo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
package br.al.marcellojunior.performance.exception;

/**
 * @author Marcello Junior
 *
 * Projeto desenvolvido em J2EE
 * Be specific while handling the exception in your catch block.
 * Be specific while throwing exception in your throws clause.
 * Do not use Exception Handling to control programming flow.
 * Very little overhead is imposed by using exception handling 
 * mechanism unless an exception occurs or thrown a new exception 
 * object explicitly.
 * Always use the finally block to release the resources to 
 * prevent resource leaks.
 * Handle exceptions locally wherever possible.
 * Do not use Exception handling in loops. 
 */
public class ExceptionTest {

	public static void main(String[] args) {

		long start,end;
		int i = 0;
		int[] intArray = new int[25000];
		String stringArray[] = new String[25000];
		int size = stringArray.length;

		for(i=0; i<size; i++){
			if(i%50 == 0)
				stringArray[i] = "hello world";
			else
				stringArray[i] = Integer.toString(i);
		}

		start = System.currentTimeMillis();
 		for(i=0; i<size; i++){

			try{
				intArray[i] = Integer.parseInt(stringArray[i]);
			}catch(NumberFormatException e){} 	
		} 
		end=System.currentTimeMillis();
		System.out.println(end-start + " millis with try/catch inside for loop ");

		start=System.currentTimeMillis();
		try{
			for(i=0; i<size; i++){
				intArray[i]=Integer.parseInt(stringArray[i]);
			}
		} catch(NumberFormatException e){}
		end=System.currentTimeMillis();
		System.out.println(end-start + " millis with try/catch outside for loop ");
	}
}
