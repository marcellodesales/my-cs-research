/*
 * Created on Jan 27, 2004 2:18:07 PM
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral.command.fan;

import br.ufpe.cin.imersion.pattern.behavioral.command.Command;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * Jan 27, 2004 
 */
public class FanOffCommand implements Command {

	private Fan myFan;

	public FanOffCommand(Fan F){
		myFan = F;
	}
	public void execute(){
		myFan.stopRotate();
	}
}
