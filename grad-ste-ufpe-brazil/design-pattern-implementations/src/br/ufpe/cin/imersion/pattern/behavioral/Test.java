/*
 * Created on 01/04/2004 19:12:36
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 01/04/2004 
 */
public class Test {

	public static void main(String[] args) {
		
		String x = "Pernambuco";
		String y = x.substring(0,5);
		String z = x.substring(6,10);
		int ind = x.indexOf("na");
		char let = x.charAt(5);
		
		System.out.println(y + ", "+ z + ", "+ ind + ", "+ let+ ", ");
	}
}
