/*
 * Created on Jan 27, 2004 11:53:20 AM
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral;

import junit.framework.TestCase;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * Jan 27, 2004 
 */
public class HashCodeTest extends TestCase {

	/**
	 * Constructor for HashCodeTest.
	 * @param arg0
	 */
	public HashCodeTest(String arg0) {
		super(arg0);
	}
	
	public void testStrings() {
		//Hashcode for strings s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
		assertEquals("JAVA".hashCode(),"JAVA".hashCode());
		System.out.println("JAVA".hashCode());
		assertTrue("JAVA" == "JAVA");
		assertEquals("JAVA","JAVA");
		int[] primes = {1,3,5,7};
		System.out.println(primes[5]);
	}
}
