/*
 * @created 05/07/2004 00:36:23
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.screen.client;

import java.util.Date;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import br.ufpe.cin.stp.mass.view.wireless.handler.Session;
import br.ufpe.cin.stp.mass.view.wireless.screen.ScreenComponentFactory;

/**
 * It's the form to view an Item
 * @author Marcello Sales Jr.
 * @version 1.0
 * @updated 05-jul-2004 14:43:26
 */
public class ViewCurrenttSessionResultsForm extends Form{

    private StringItem itemSessionTitle;
    private StringItem itemSessionType;
    private DateField itemSessionDate;
    
    private Command saveSessionCommand;
    private Command backCommand;

    public ViewCurrenttSessionResultsForm(Session session){
        super("[M.A.S.S. :: Session Results Menu]");
        
        this.itemSessionTitle = new StringItem("Title",session.getTitle());
        this.itemSessionDate = new DateField("Creation Date: ", DateField.DATE);
        this.itemSessionDate.setDate(new Date(Long.parseLong(session.getDate())));
        this.itemSessionType = new StringItem("Type",session.getTypeString());
        
        ScreenComponentFactory cf = ScreenComponentFactory.getInstance();
        this.saveSessionCommand = cf.createCommand("SAVE");
        this.backCommand = cf.createBackCommand("BACK");
        
        this.append(this.itemSessionType);        
        this.append(this.itemSessionTitle);
        this.append(this.itemSessionDate);
        
        //if (session.isOpened()) //only save if the session is already closed.
        	this.addCommand(this.saveSessionCommand);
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
    public Command getSaveSessionCommand() {
        return this.saveSessionCommand;
    }
}
