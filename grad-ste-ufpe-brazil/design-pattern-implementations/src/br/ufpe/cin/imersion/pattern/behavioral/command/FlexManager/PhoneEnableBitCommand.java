/*
 * Created on Jan 27, 2004 7:51:45 PM
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral.command.FlexManager;

import br.ufpe.cin.imersion.pattern.behavioral.command.Command;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * Jan 27, 2004 
 */
public class PhoneEnableBitCommand implements Command {

	private PhoneConnection connection;

	public static void main(String[] args) {
	}

	public PhoneEnableBitCommand(PhoneConnection connection){
		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see br.ufpe.cin.imersion.pattern.behavioral.command.Command#execute()
	 * Jan 27, 2004 7:52:27 PM
	 */
	public void execute() {
		this.connection.enableBit();				
	}
}
