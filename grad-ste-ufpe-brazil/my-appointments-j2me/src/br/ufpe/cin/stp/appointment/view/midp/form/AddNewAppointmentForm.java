/*
 * @created 05/07/2004 00:36:01
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.appointment.view.midp.form;

import java.util.Date;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

import br.ufpe.cin.stp.appointment.model.ApplicationState;
import br.ufpe.cin.stp.appointment.model.AppointmentItem;
import br.ufpe.cin.stp.appointment.view.midp.ComponentFactory;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 05/07/2004 00:36:01
 */
public class AddNewAppointmentForm extends Form {

	/**
	 * This is the choice group component initialized with the typeStrings constant.
	 */
	private ChoiceGroup typeChoice;
	
	private DateField dateField;
	
	private TextField descriptionField; //change to text box on future.
	
	private TextField startHourField;
	
	private TextField durationField;

	private Command saveCommand;
	private Command cancelCommand;
	
    public AddNewAppointmentForm(int numberOfItems){
        super("[M.A.P.S. :: New Session Menu]");
        
        ComponentFactory cf = ComponentFactory.getInstance();
        
        this.typeChoice = cf.createExclusiveList("Type",AppointmentItem.types);
        this.dateField = new DateField("Date", DateField.DATE);
        this.descriptionField = new TextField("Description","",30,TextField.ANY);
        this.startHourField = new TextField("Start Hour","",2,TextField.NUMERIC);
        this.durationField = new TextField("Duration","",2,TextField.NUMERIC);
        
        this.saveCommand = cf.createCommand("SAVE");
        this.cancelCommand = cf.createBackCommand("CANCEL");
        
        this.append(this.typeChoice);
        this.append(this.dateField);
        this.append(this.descriptionField);
        this.append(this.startHourField);
        this.append(this.durationField);
        this.addCommand(this.saveCommand);
        this.addCommand(this.cancelCommand);
    }
    
    /**
     * @return Returns the cancelCommand.
     * @created 05/07/2004 01:13:30
     */
    public Command getCancelCommand() {
        return this.cancelCommand;
    }
    /**
     * @param cancelCommand The cancelCommand to set.
     * @created 05/07/2004 01:13:30
     */
    public void setCancelCommand(Command cancelCommand) {
        this.cancelCommand = cancelCommand;
    }
    /**
     * @return Returns the dateField.
     * @created 05/07/2004 01:13:30
     */
    public Date getDateField(){
        return this.dateField.getDate();
    }
    /**
     * @return Returns the descriptionField.
     * @created 05/07/2004 01:13:30
     */
    public String getDescriptionFieldString() {
        return this.descriptionField.getString();
    }
    /**
     * @return Returns the durationField.
     * @created 05/07/2004 01:13:30
     */
    public String getDurationFieldString() {
        return this.durationField.getString();
    }
    /**
     * @return Returns the saveCommand.
     * @created 05/07/2004 01:13:30
     */
    public Command getSaveCommand() {
        return this.saveCommand;
    }
    /**
     * @param saveCommand The saveCommand to set.
     * @created 05/07/2004 01:13:30
     */
    public void setSaveCommand(Command saveCommand) {
        this.saveCommand = saveCommand;
    }
    /**
     * @return Returns the startHourField.
     * @created 05/07/2004 01:13:30
     */
    public String getStartHourFieldString() {
        return this.startHourField.getString();
    }
    /**
     * @return Returns the typeChoice.
     * @created 05/07/2004 01:13:30
     */
    public ChoiceGroup getTypeChoice() {
        return this.typeChoice;
    }
    /**
     * @param dateField The dateField to set.
     * @created 06/07/2004 13:14:57
     */
    public void setDateField(DateField dateField) {
        this.dateField = dateField;
    }
    /**
     * @param descriptionField The descriptionField to set.
     * @created 06/07/2004 13:14:57
     */
    public void setDescriptionField(String descriptionField) {
        this.descriptionField.setString(descriptionField);
    }
    /**
     * @param durationField The durationField to set.
     * @created 06/07/2004 13:14:57
     */
    public void setDurationField(String durationField) {
        this.durationField.setString(durationField);
    }
    /**
     * @param startHourField The startHourField to set.
     * @created 06/07/2004 13:14:57
     */
    public void setStartHourField(String startHourField) {
        this.startHourField.setString(startHourField);
    }
}
