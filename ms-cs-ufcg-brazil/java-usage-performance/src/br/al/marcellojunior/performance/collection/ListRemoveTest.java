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
public class ListRemoveTest {

	private static final int NUM = 20000;
	private static Object[] objs = null;


	public void removeLast(List list) {
		
		long startTime = System.currentTimeMillis();

		for(int i=NUM; i>0; i--){
			list.remove(i-1);
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for removing Objects at End: "+ (endTime - startTime) );
	}

	public void removeFirst(List list) {

		long startTime = System.currentTimeMillis();

		for(int i=0; i<NUM; i++){
			list.remove(0);
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for removing Objects at First : "+ (endTime - startTime) );
	}

	public void removeMiddle(List list) {

		long startTime = System.currentTimeMillis();

		for(int i=0; i<NUM; i++){
			list.remove((NUM-i)/2);
		}

		long endTime = System.currentTimeMillis();

		System.out.println("Time taken for removing Objects at Middle : "+ (endTime - startTime) );
	}

	public List getList(){

		objs = new Object[NUM];
		for(int i=0;i<NUM;i++){
			objs[i] = new Object();

		}
		return Arrays.asList(objs);        
	}

	public void clear(List col){

		if(!col.isEmpty())
			col.clear();
	}

	public void doTest(List collection) {

		collection.addAll(this.getList()); // fill the List
		this.removeLast(collection);
		this.clear(collection);
		
		collection.addAll(getList()); // fill the List
		this.removeMiddle(collection);
		this.clear(collection);

		collection.addAll(getList()); // fill the List
		this.removeFirst(collection);
		this.clear(collection);
	}

	public static void main(String[] args) {
		
		ListRemoveTest col    = new ListRemoveTest();
		
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
	}
}
