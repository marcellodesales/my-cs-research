/*
 * Created on Jan 27, 2004 3:09:02 PM
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral.command.FlexManager;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * Jan 27, 2004 
 */
public class PhoneConnection {

	private boolean bitEnable;

	public void enableBit() {		
		if (!this.bitEnable){
			this.bitEnable = true;
			System.out.println("The Flex Bit was enabled!");
		} else System.out.println("Error: bit already enabled!"); 
	}
	
	public void unableBit() {
		if (this.bitEnable){
			this.bitEnable = false;
			System.out.println("The Flex Bit was unabled!");	
		} else System.out.println("Error: bit already unabled!");
		
		
	}
	
	public boolean getBitStatus(){
		return this.bitEnable;
	}
}
