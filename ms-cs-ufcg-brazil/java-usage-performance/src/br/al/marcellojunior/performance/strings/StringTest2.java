/*
 * Criado em 31/10/2003
 *
 * Para alterar o gabarito para este arquivo gerado v� para
 * Janela&gt;Prefer�ncias&gt;Java&gt;Gera��o de C�digos&gt;C�digo e Coment�rios
 */
package br.al.marcellojunior.performance.strings;

/**
 * @author Marcello Junior
 *
 * Projeto desenvolvido em J2EE. Utiliza��o do m�todo String.intern()
 * para que c�pias de strings sejam utilizadas como refer�ncias
 * quando se utiliza o construtor new. Os apontadores de mem�ria ser�o
 * os mesmos. Nao funciona da maneira que quer�amos.  
 */
public class StringTest2 {

	public static void main(String[] args) {
		
		// create String references like s1,s2,s3...so on..
		String variables[] = new String[50000];
		for( int i=0; i<variables.length; i++){
			variables[i] = "s"+i;
		}

		// create String literals
		long startTime0 = System.currentTimeMillis();
		for(int i=0; i<variables.length; i++){
			variables[i] = "hello";
		}      
		long endTime0 = System.currentTimeMillis();
		System.out.println("Time taken for creation of String literals : "+ (endTime0 - startTime0) + " milli seconds" );       

		// create String objects using 'new' keyword        
		long startTime1 = System.currentTimeMillis();
		for(int i=0; i<variables.length; i++){
			variables[i] = new String("hello");
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("Time taken for creation of String objects with 'new' key word : "+ (endTime1 - startTime1)+" milli seconds");

		// intern String objects with intern() method          
		long startTime2 = System.currentTimeMillis();
		for(int i=0; i<variables.length; i++){
			variables[i] = new String("hello");
			variables[i] = variables[i].intern();                    
		}
		long endTime2 = System.currentTimeMillis();
		System.out.println("Time taken for creation of String objects with intern(): "+ (endTime2 - startTime2)+" milli seconds");
		
		for (int i = 10; i > 0; i--) {
			System.out.println(variables[i] == variables[i-1]);
		}
		
		System.out.println();
		
		String a = new String("A");
		String aa = a.intern();
		
		System.out.println("As duas strings apontam pro mesmo heap");
		System.out.println("de mem�ria, mesmo utilizando new");
		System.out.println(a == aa);		
	}	
}
