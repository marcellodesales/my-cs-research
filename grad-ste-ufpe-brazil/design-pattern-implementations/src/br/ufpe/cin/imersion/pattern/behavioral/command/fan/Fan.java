/*
 * Created on Jan 27, 2004 2:13:03 PM
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral.command.fan;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * Jan 27, 2004 
 */
public class Fan {
	
	public void startRotate() {
		System.out.println("Fan is rotating");
	}
	
	public void stopRotate() {
		System.out.println("Fan is not rotating");
	}
}
