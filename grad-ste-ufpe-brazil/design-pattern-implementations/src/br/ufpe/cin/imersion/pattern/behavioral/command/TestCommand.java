/*
 * Created on Jan 27, 2004 2:17:20 PM
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.imersion.pattern.behavioral.command;

import br.ufpe.cin.imersion.pattern.behavioral.command.FlexManager.PhoneConnection;
import br.ufpe.cin.imersion.pattern.behavioral.command.FlexManager.PhoneEnableBitCommand;
import br.ufpe.cin.imersion.pattern.behavioral.command.FlexManager.PhoneUnableBitCommand;
import br.ufpe.cin.imersion.pattern.behavioral.command.fan.*;
import br.ufpe.cin.imersion.pattern.behavioral.command.light.*;
import br.ufpe.cin.imersion.pattern.behavioral.command.light.*;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * Jan 27, 2004 
 */
public class TestCommand {

	public static void main(String[] args) {
		Light testLight = new Light();
		LightOnCommand testLOC = new LightOnCommand(testLight);
		LightOffCommand testLFC = new LightOffCommand(testLight);
		SwitchCommand testSwitch = new SwitchCommand(testLOC,testLFC);
		testSwitch.doIt();
		testSwitch.undoIt();
		
		Fan testFan = new Fan();
		FanOnCommand foc = new FanOnCommand(testFan);
		FanOffCommand ffc = new FanOffCommand(testFan);
		SwitchCommand ts = new SwitchCommand(foc,ffc);
		ts.doIt();
		ts.undoIt(); 
		
		PhoneConnection pc = new PhoneConnection();
		PhoneEnableBitCommand pebc = new PhoneEnableBitCommand(pc);
		PhoneUnableBitCommand pubc = new PhoneUnableBitCommand(pc);
		SwitchCommand sc = new SwitchCommand(pebc,pubc);
		sc.doIt();
		sc.undoIt();
	}
}    
