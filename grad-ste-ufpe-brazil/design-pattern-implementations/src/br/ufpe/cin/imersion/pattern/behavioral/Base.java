/*
 * Created on 02/04/2004 09:42:05
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 02/04/2004 
 */
import java.io.*;

public class Base{
	
	public void amethod(){
	}
	
	public static void main(String argv[]){
		//Base e = new ExcepDemo();
		Base a;
		String bb = null;
	}
}

interface IF {
	public void amethod();
}

class ExcepDemo extends Base implements IF{

	
	protected ExcepDemo(){
	 try{
		  DataInputStream din = new DataInputStream(System.in);
		  System.out.println("Pausing");
		  din.readChar();
		  System.out.println("Continuing");
		  amethod();
	  }catch(IOException ioe) {
	  }
	}
}

