/*
 * Criado em 01/11/2003
 *
 * Para alterar o gabarito para este arquivo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
package br.al.marcellojunior.performance.loops;

/**
 * @author Marcello Junior
 *
 * Projeto desenvolvido em J2EE
 * Always use an int data type as the loop index variable 
 * whenever possible because it is efficient when compared to 
 * using byte or short data types. because when we use byte or 
 * short data type as the loop index variable they involve 
 * implicit type cast to int data type.
 * When using arrays it is always efficient to copy arrays 
 * using System.arraycopy() than using a loop. The following 
 * example shows the difference.
 */
public class LoopTest1 {

	public static void main(String[] args) {
		long start,end;
		int[] a= new int[2500000];
		int[] b= new int[2500000];
		
		for(int i=0; i<a.length; i++){
			a[i]=i;
		} 
		
		start = System.currentTimeMillis();
		for(int j=0; j<a.length; j++){
			b[j] = a[j];
		}
		end = System.currentTimeMillis();
		System.out.println(end-start + " milli seconds for loop copy ");
		
		int[] c = new int[2500000];
		start = System.currentTimeMillis();
		System.arraycopy(a,0,c,0,c.length);
		end = System.currentTimeMillis();
		System.out.println(end-start + " milli seconds for System.arraycopy() ");
	}
}
