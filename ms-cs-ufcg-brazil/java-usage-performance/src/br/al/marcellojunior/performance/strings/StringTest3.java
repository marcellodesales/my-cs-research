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
 * Projeto desenvolvido em J2EE
 * You can concatenate multiple strings using either
 * + operator or String.concat()  or   StringBuffer.append(). 
 * Which is the best one interms of performance?
 * 
 * The choice depends on two scenarios. 
 * -> First scenario is compile time resolution versus run time 
 * resolution;
 * -> Second scenario is wether you are using StringBuffer or 
 * String. In general, programmers think that StringBuffer.append() 
 * is better than + operator or String.concat() method. 
 * But this assumption is not true under certain conditions. 
 */
public class StringTest3 {

	public static void main(String[] args) {
		
		System.out.println("Somente string sem buffer");
		//Test the String Concatination
	   	long startTime = System.currentTimeMillis();
		for(int i=0; i<5000; i++){
			String result = "This is"+ "testing the"+ "difference"+ "between"+ "String"+ "and"+ "StringBuffer";
	 	}
	   	long endTime = System.currentTimeMillis();
	   	System.out.println("Time taken for string concatenation using + operator : "+ (endTime - startTime)+ " milli seconds");

		//Test the StringBuffer Concatination
		long startTime1 = System.currentTimeMillis();
		for(int i=0;i<5000;i++){
			StringBuffer result = new StringBuffer();
			result.append("This is");
			result.append("testing the");
			result.append("difference");
			result.append("between");
			result.append("String");
			result.append("and");
			result.append("StringBuffer");
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("Time taken for String concatenation using StringBuffer : "+ (endTime1 - startTime1)+ " milli seconds");
		
		System.out.println();
		
		System.out.println("string com buffer");
		//Test the String Concatenation using + operator
		long startTime2 = System.currentTimeMillis();
		String result = "hello";
		for(int i=0;i<1500;i++){
			result += "hello";
		}
		long endTime2 = System.currentTimeMillis();
		System.out.println("Time taken for string concatenation using + operator : "+ (endTime2 - startTime2)+ " milli seconds");

		//Test the String Concatenation using StringBuffer
		long startTime3 = System.currentTimeMillis();
		StringBuffer result1 = new StringBuffer("hello");
		for(int i=0;i<1500;i++){
			result1.append("hello");
		}
		long endTime3 = System.currentTimeMillis();
		System.out.println("Time taken for string concatenation using StringBuffer :  "+ (endTime3 - startTime3)+ " milli seconds");
		
		System.out.println();	
		System.out.println("Inicialização de 50000 strings dando tamanho a inicializando o stringbuffer");
		long startTime4 = System.currentTimeMillis();
		StringBuffer bufferInited = new StringBuffer(250000);
		for(int i=0; i<50000; i++){
			bufferInited.append("hello");
		}
		long endTime4 = System.currentTimeMillis();
		System.out.println("Time taken for string concatenation using StringBuffer :  "+ (endTime4 - startTime4)+ " milli seconds");

		System.out.println();	
		System.out.println("Inicialização de 50000 strings sem tamanho de inicialização do stringbuffer");
		long startTime5 = System.currentTimeMillis();
		StringBuffer bufferNInited = new StringBuffer();
		for(int i=0; i<50000; i++){
			bufferNInited.append("hello");
		}
		long endTime5 = System.currentTimeMillis();
		System.out.println("Time taken for string concatenation using StringBuffer :  "+ (endTime5 - startTime5)+ " milli seconds");
				
	}
}
