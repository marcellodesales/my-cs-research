/*
 * Created on Jan 27, 2004 2:20:02 PM
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral.command.light;

import br.ufpe.cin.imersion.pattern.behavioral.command.Command;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * Jan 27, 2004 
 */
public class LightOnCommand implements Command {

	private Light myLight;

	public LightOnCommand(Light L){
		myLight = L;
	}

	public void execute(){
		myLight.turnOn();
	}
}
