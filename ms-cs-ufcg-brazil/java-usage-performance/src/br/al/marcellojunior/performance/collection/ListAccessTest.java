/*
 * Criado em 31/10/2003
 *
 * Para alterar o gabarito para este arquivo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
package br.al.marcellojunior.performance.collection;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Arrays;

/**
 * @author Marcello Junior
 *
 * Projeto desenvolvido em J2EE
 */
public class ListAccessTest {

	private static final int NUM = 25000;
	private static Object[] objs = null;

	public void getFromLast(List list) {
		
		long startTime = System.currentTimeMillis();
		for(int i=NUM; i>0; i--){
			list.get(i-1);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for getting Objects at Last: "+ (endTime - startTime) );
	}

	public void getFromFirst(List list) {

		long startTime = System.currentTimeMillis();
		for(int i=0; i<NUM; i++){
			list.get(0);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for getting Objects at First : "+ (endTime - startTime) );
	}

	public void getFromMiddle(List list) {
		
		long startTime = System.currentTimeMillis();
		for(int i=0;i<NUM;i++){
			list.get(NUM/2);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for getting Objects at Middle : "+ (endTime - startTime) );
	}

	public void doTest(List list) {

		list.addAll(getList());
		this.getFromLast(list);
		this.getFromMiddle(list);
		this.getFromFirst(list);
	}

	public void clear(List col){
		if(!col.isEmpty()) col.clear();
	}

	public static List getList(){
			
		objs = new Object[NUM];
		for(int i=0;i<NUM;i++) 
			objs[i] = new Object();
		return Arrays.asList(objs);        
	}

	public static void main(String[] args) {
		
		ListAccessTest col = new ListAccessTest();

		System.out.println("Teste: Array List");
		ArrayList collection1 = new ArrayList();
		col.doTest(collection1);
		System.out.println();
		
		System.out.println("Teste: Vector");
		Vector collection2 = new Vector();
		col.doTest(collection2);
		System.out.println();

		System.out.println("Teste: LinkedList");
		LinkedList collection4 = new LinkedList();
		col.doTest(collection4);
		System.out.println();
	}
}
