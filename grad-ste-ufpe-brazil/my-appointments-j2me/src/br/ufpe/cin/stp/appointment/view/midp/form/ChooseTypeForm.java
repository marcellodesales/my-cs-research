package br.ufpe.cin.stp.appointment.view.midp.form;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;

import br.ufpe.cin.stp.appointment.model.AppointmentItem;
import br.ufpe.cin.stp.appointment.view.midp.ComponentFactory;

/**
 * The form to choose the type of the appointment.
 * @author Marcello de Sales
 * @version 1.0
 * @created 04-jul-2004 12:23:14
 */
public class ChooseTypeForm extends Form {

	/**
	 * This is the choice group component initialized with the typeStrings constant.
	 */
	private ChoiceGroup typeChoice;
	/**
	 * The select Command to cancel. It's the LSK=CANCEL
	 */
	private Command cancelCommand;
	/**
	 * It's the select command to continue the flow of the application. It's the
	 * RSK=SELECT
	 */
	private Command selectCommand;
	/**
	 * Creates a new ChooseTypeForm with an Exclusive ChoiceGroup and commands
	 * to select and cancel the operation.
	 * 04/07/2004 14:07:05
	 */
	public ChooseTypeForm(){
	    super("Choose Appointment Type");
	    ComponentFactory cf = ComponentFactory.getInstance();
	    this.typeChoice = cf.createExclusiveList("Types",AppointmentItem.types);
	    this.selectCommand = cf.createCommand("SELECT");
	    this.cancelCommand = cf.createBackCommand("CANCEL");
	    
	    this.append(this.typeChoice);
	    this.addCommand(this.selectCommand);
	    this.addCommand(this.cancelCommand);
	}
	
	/**
	 * (04/07/2004 14:06:13)
	 * @return Gets the choice group of the types.
	 */
	public ChoiceGroup getTypeChoice(){
	    return this.typeChoice;
	}
    /**
     * @return Returns the cancelCommand.
     * @created 04/07/2004 14:06:52
     */
    public Command getCancelCommand() {
        return this.cancelCommand;
    }
    /**
     * @return Returns the selectCommand.
     * @created 04/07/2004 14:06:52
     */
    public Command getSelectCommand() {
        return this.selectCommand;
    }
}
