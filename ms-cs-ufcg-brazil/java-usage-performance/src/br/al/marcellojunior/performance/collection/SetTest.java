/*
 * Criado em 31/10/2003
 *
 * Para alterar o gabarito para este arquivo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
package br.al.marcellojunior.performance.collection;

import java.util.HashSet;

/**
 * @author Marcello Junior
 *
 * Projeto desenvolvido em J2EE
 */
public class SetTest {

	/*
	 * Set is a collection of unique objects, it doesn't allow 
	 * duplicate objects and modification of existing objects. 
	 * Set types also allow basic operations like adding objects, 
	 * removing objects, accessing objects, iterating objects but 
	 * do not allow modification. There are two implementations 
	 * of the Set interface they are HashSet and TreeSet. 
	 * 
	 * HashSet gives better performance than TreeSet because, 
	 * TreeSet is an ordered collection of objects and the objects 
	 * are sorted while they are inserted in the TreeSet where as 
	 * in case of HashSet objects are added in an adhoc manner
	 *
	 * HashSet and TreeSet are backed by HashMap and TreeMap respectively
	 * 
	 * Whenever we use a HashSet we can specify an initial 
	 * capacity and load factor using constructors. The default 
	 * size for a HashSet is 11 and it's load factor is 0.75. 
	 * Load factor determines at which capacity HashSet has to 
	 * be resized. It's internal structure will become double in 
	 * size when it reaches it's maximum capacity based on load 
	 * factor.
	 * 
	 * HashSet scales well when it is initialized with proper 
	 * size and default load factor. When you know the the number 
	 * of objects to be added, it is better to initialize with 
	 * that capacity and put load factor as 1.0f. The objects in 
	 * HashSet are stored and retrieved through hash code which 
	 * provides constant look up time.
	 */
	
	public static void main(String[] args) {
		
		String a = "A";
		String b = "B";
		String c = "C";
		
		try {
			HashSet set = new HashSet(4);
			set.add(a);
			set.add(b);
			set.add(c);
			set.add(a);
			set.add(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
