/*
 * Created on Jan 27, 2004 2:14:33 PM
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral.command;

/**
 * The SWitch command class.
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * Jan 27, 2004 
 */
class SwitchCommand {
		
	private Command doCommand, undoCommand;
	
	public SwitchCommand(Command dO, Command undo) {
	//concrete Command registers itself with the invoker
		this.doCommand   = dO;  
		this.undoCommand = undo;
	}
	
	void doIt(){ 
	// invoker calls back concrete Command, which executes the Command on the receiver 
		this.doCommand.execute();
	}
	
	void undoIt(){
		this.undoCommand.execute();
	}
}
