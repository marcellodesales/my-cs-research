/*
 * Created on Jan 27, 2004 8:12:51 PM
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral.command.FlexManager;

import br.ufpe.cin.imersion.pattern.behavioral.command.Command;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * Jan 27, 2004 
 */
public class PhoneUnableBitCommand implements Command {

	private PhoneConnection connection;

	public PhoneUnableBitCommand(PhoneConnection connection){
		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see br.ufpe.cin.imersion.pattern.behavioral.command.Command#execute()
	 * Jan 27, 2004 8:13:10 PM
	 */
	public void execute() {
		this.connection.unableBit();
	}
}
