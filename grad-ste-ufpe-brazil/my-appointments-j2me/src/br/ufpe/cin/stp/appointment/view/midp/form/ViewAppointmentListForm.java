/*
 * @created 05/07/2004 17:13:33
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.appointment.view.midp.form;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;

import br.ufpe.cin.stp.appointment.model.AppointmentDate;
import br.ufpe.cin.stp.appointment.model.AppointmentItem;
import br.ufpe.cin.stp.appointment.view.midp.ComponentFactory;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 05/07/2004 17:13:33
 */
public class ViewAppointmentListForm extends Form {
    
    private ChoiceGroup itemsChoice;
    
    private Command cancelCommand;
    private Command viewCommand;
    
    public ViewAppointmentListForm(AppointmentDate appDate){
        super("Appointments from "+appDate.getDate().toString());
        Vector items = appDate.getAppointmentItems();
        int numberOfItems = items.size();
        
        String itemsTitle[] = new String[numberOfItems];

        for (int i = 0; i < numberOfItems; i++) {
            AppointmentItem item = (AppointmentItem)items.elementAt(i);
            itemsTitle[i] = item.getDescription();
        }
        
        this.itemsChoice = ComponentFactory.getInstance().createExclusiveList("Appointments...",itemsTitle);
        
        this.cancelCommand = ComponentFactory.getInstance().createBackCommand("CANCEL");
        this.viewCommand = ComponentFactory.getInstance().createCommand("VIEW");
        
        this.append(this.itemsChoice);
        this.addCommand(this.cancelCommand);
        this.addCommand(this.viewCommand);
    }
    
    public ViewAppointmentListForm(AppointmentItem[] items){
        super("All Appointments");
        int numberOfItems = items.length;
        
        String itemsTitle[] = new String[numberOfItems];

        for (int i = 0; i < numberOfItems; i++) {
            itemsTitle[i] = items[i].getDescription();
        }
        
        this.itemsChoice = ComponentFactory.getInstance().createExclusiveList("Appointments...",itemsTitle);
        
        this.cancelCommand = ComponentFactory.getInstance().createBackCommand("CANCEL");
        this.viewCommand = ComponentFactory.getInstance().createCommand("VIEW");
        
        this.append(this.itemsChoice);
        this.addCommand(this.cancelCommand);
        this.addCommand(this.viewCommand);
    }    
    /**
     * @created 06/07/2004 12:43:22
     * @return Returns the itemsChoice.
     */
    public ChoiceGroup getItemsChoice() {
        return this.itemsChoice;
    }
    /**
     * @param itemsChoice The itemsChoice to set.
     * @created 06/07/2004 12:43:22
     */
    public void setItemsChoice(ChoiceGroup itemsChoice) {
        this.itemsChoice = itemsChoice;
    }
    /**
     * @created 06/07/2004 12:43:22
     * @return Returns the cancelCommand.
     */
    public Command getCancelCommand() {
        return this.cancelCommand;
    }
    /**
     * @created 06/07/2004 12:43:22
     * @return Returns the viewCommand.
     */
    public Command getViewCommand() {
        return this.viewCommand;
    }
}
