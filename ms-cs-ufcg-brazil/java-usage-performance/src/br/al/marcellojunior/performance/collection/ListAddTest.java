/*
 * Criado em 31/10/2003
 *
 * Desenvolvido por Marcello Junior
 */
package br.al.marcellojunior.performance.collection;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;



/**
 * @author Marcello Junior
 *
 * Projeto desenvolvido em J2EE
 */
public class ListAddTest {

	private static final int NUM = 50000;
   	private static String[] objs = null;
	
	public void addLast(List list) {

		long startTime = System.currentTimeMillis();

		for(int i=0; i < NUM; i++){
			list.add(objs[i]);
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for adding Objects at End: "+ (endTime - startTime));
	}	
	
	public void addFirst(List list) {

		long startTime = System.currentTimeMillis();

		for(int i=0; i < NUM; i++){
			list.add(0,objs[i]);

		}

		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for adding Objects at First : "+ (endTime - startTime) );
	}	
	
	public void addMiddle(List list) {

		long startTime = System.currentTimeMillis();

		for(int i=0; i < NUM; i++){
			  list.add(i/2,objs[i]);
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for adding Objects at Middle : "+ (endTime - startTime) );
	}
	
	public void doTest(List list) {
		this.addLast(list);
		this.clear(list);
		this.addMiddle(list);
		this.clear(list);
		this.addFirst(list);
		this.clear(list);
	}

	public void clear(List collection){
		if(!collection.isEmpty())
			collection.clear();
	}
	
	public static void main(String[] args) {
		
		objs = new String[ListAddTest.NUM];

		for(int i=0; i < ListAddTest.NUM; i++){
			objs[i] = "Object "+i;
		}

		ListAddTest col = new ListAddTest();
		
		System.out.println("Teste: Array List without initialization");
		ArrayList collection1 = new ArrayList();
		col.doTest(collection1);
		System.out.println();
		
		System.out.println("Teste: Array List with initialization");
		ArrayList collection1A = new ArrayList(NUM);
		col.doTest(collection1A);
		System.out.println();
	
		System.out.println("Teste: Vector without initialization");
		Vector collection2 = new Vector();
		col.doTest(collection2);
		System.out.println();
		
		System.out.println("Teste: Vector with initialization");
		Vector collection2A = new Vector(NUM);
		col.doTest(collection2A);
		System.out.println();
		
		System.out.println("Teste: LinkedList without initialization");
		LinkedList collection4 = new LinkedList();
		col.doTest(collection4);		
	}
}
