/*
 * Created on Jan 27, 2004 2:19:19 PM
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral.command.light;

import br.ufpe.cin.imersion.pattern.behavioral.command.Command;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * Jan 27, 2004 
 */
public class LightOffCommand implements Command {
	private Light myLight;
	
	public LightOffCommand(Light L){
		myLight  =  L;
	}
	
	public void execute(){
		myLight.turnOff();
	}
}
