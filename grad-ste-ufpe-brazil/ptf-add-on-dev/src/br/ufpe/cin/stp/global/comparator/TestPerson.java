/*
 * Created on 10/06/2004 16:16:32
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 10/06/2004 
 */
class TestPerson {

	private String name;
	private int age;	
	
	public TestPerson(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	/**
	 * @return
	 * 10/06/2004 13:53:43
	 * Marcello
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return
	 * 10/06/2004 13:53:43
	 * Marcello
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param i
	 * 10/06/2004 13:53:43
	 * Marcello
	 */
	public void setAge(int i) {
		age = i;
	}

	/**
	 * @param string
	 * 10/06/2004 13:53:43
	 * Marcello
	 */
	public void setName(String string) {
		name = string;
	}
	
	public static void main(String[] args) {
		TestPerson[] p = new TestPerson[3];
		p[0] = new TestPerson("Marcello",24);
		p[1] = new TestPerson("Carlla",24);
		p[2] = new TestPerson("Kaline",12);
		
		Comparator name = new BeanPropertyComparator("name");
		Comparator age = new BeanPropertyComparator("age");
		
		//order by first age and then name
		Comparator lastFirst = new CompositeComparator(age,name);
		
		List list = Arrays.asList(p);
		Collections.sort(list,lastFirst);
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			TestPerson element = (TestPerson) iter.next();
			System.out.println(element.getName());
			System.out.println(element.getAge());
		}
		
	}

}
