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
 * Always avoid anything that can be done outside of the loop 
 * like method calls, assigning values to variables, or testing 
 * for conditions.
 * 
 * Method calls are very costly and you only make it worse by 
 * putting them in a loop. So as far as possible avoid method 
 * calls in a loop. 
 * 
 * It is better to avoid accessing array elements in a loop the 
 * better option would be to use a temporary variables inside 
 * the loop and modify the array values out of the loop. It is 
 * fast to use a variable in a loop than accessing an array 
 * element.
 * 
 * Try to compare the terminating condition with zero if you 
 * use non-JIT or HotSpot virtual machine, here is an example 
 * to prove the point. JIT or HotSpot virtual machines are 
 * optimized for general loops so you do not have to bother 
 * about the terminating condition.
 * 
 * Avoid using method calls in loops for termination condition 
 * this is costly instead use a temporary variable and test the 
 * loop termination with the temporary variable.
 * 
 * When using short circuit operators to test for loop 
 * termination tests always put the expression that will most 
 * likely evaluate to false at extreme left. This saves all the 
 * following expressions from being tested in case there is an 
 * && operator and if there are only || operators then put the 
 * expression which is most likely to evaluate to true in the 
 * extreme left.
 * 
 * Avoid using try-catch inside the loops instead place the 
 * loops inside the try-catch for better performance
 * 
 * Compare the termination condition in a loop with zero if you 
 * use non-JIT or HotSpot VMs  
 */
public class LoopTest2 {

	public static void main(String[] args) {

		long start,end;
		int[] a = new int[2500000];

		start = System.currentTimeMillis();
		for(int i=0; i<a.length; i++){
			a[i]+=i;
		}
		end = System.currentTimeMillis();
		System.out.println(end-start + " millis with loop check i < a.length ");
		                       
		int[] b = new int[2500000];
		start = System.currentTimeMillis();
		for(int i=b.length; i>0; i--){
			b[i-1]+=i;
		}
		end = System.currentTimeMillis();
		System.out.println(end-start + " millis with loop check i > 0");	
	}
}
