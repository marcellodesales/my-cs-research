/*
 * Criado em 31/10/2003
 *
 * Para alterar o gabarito para este arquivo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
package br.al.marcellojunior.performance.strings;

/**
 * @author Marcello Junior
 *
 * Projeto desenvolvido em J2EE. Mostra o apontamento de memória
 * de strings diferentes que apontam para heaps de mem diferentes.
 * Para a utilização de mesmos heaps de memória para evitar 
 * duplicatas de strings, utiliza-se o método String.intern(). Ver 
 * no StringTest2.
 */
public class StringTest1 {

	public static void main(String[] args) {
		String s1= "", s2= "",s3 = "",s4 = "";
		// create String literals

		long startTime = System.currentTimeMillis();		
		for(int i=0; i<50000; i++){
			s1 = "hello";
			s2 = "hello"; 
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for creation of String literals : "+ (endTime - startTime) + " milli seconds" );

		// create String objects using 'new' keyword        
		long startTime1 = System.currentTimeMillis();
		for(int i=0; i<50000; i++){
			s3 = new String("hello");
			s4 = new String("hello");
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("Time taken for creation of String objects : "+ (endTime1 - startTime1)+" milli seconds");
		
		System.out.println();
		
		System.out.println("s1 e s2: Literais | s1='string' ");
		System.out.println("s1 e s2 apontam pro mesmo heap de memória");
		System.out.println(s1 == s2);
		
		System.out.println();
		
		System.out.println("s3 e s4: new Objetos | s3=new String('string')");
		System.out.println("s3 e s4 apontam pra heaps de memória diferentes");
		System.out.println(s3 == s4);		
	}
}
