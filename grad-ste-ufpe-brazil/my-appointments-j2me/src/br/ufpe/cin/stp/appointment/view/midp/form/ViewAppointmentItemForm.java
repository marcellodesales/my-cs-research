/*
 * @created 05/07/2004 00:36:23
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 *
 * */
 
package br.ufpe.cin.stp.appointment.view.midp.form;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import br.ufpe.cin.stp.appointment.model.AppointmentItem;
import br.ufpe.cin.stp.appointment.view.midp.ComponentFactory;

/**
 * It's the form to view an Item
 * @author Marcello Sales Jr.
 * @version 1.0
 * @updated 05-jul-2004 14:43:26
 */
public class ViewAppointmentItemForm extends Form{

    private StringItem itemDescription;
    private StringItem itemStartHour;
    private StringItem itemDuration;
    private StringItem itemDate;
    
    private Command editCommand;
    private Command backCommand;

    public ViewAppointmentItemForm(AppointmentItem appItem,String date){
        super(appItem.getType());
        
        this.itemDescription = new StringItem("Description",appItem.getDescription());
        this.itemDuration = new StringItem("Duration",String.valueOf(appItem.getDuration()));
        this.itemStartHour = new StringItem("Start Hour",String.valueOf(appItem.getStartHour()));
        this.itemDate = new StringItem("Date",date);
        
        ComponentFactory cf = ComponentFactory.getInstance();
        this.editCommand = cf.createCommand("EDIT");
        this.backCommand = cf.createBackCommand("BACK");
        
        this.append(this.itemDescription);
        this.append(date);
        this.append(this.itemStartHour);
        this.append(this.itemDuration);
        
        this.addCommand(this.editCommand);
        this.addCommand(this.backCommand);
    }    
    
    /**
     * @return Returns the backCommand.
     * @created 05/07/2004 15:22:33
     */
    public Command getBackCommand() {
        return this.backCommand;
    }
    /**
     * @return Returns the editCommand.
     * @created 05/07/2004 15:22:33
     */
    public Command getEditCommand() {
        return this.editCommand;
    }
}
