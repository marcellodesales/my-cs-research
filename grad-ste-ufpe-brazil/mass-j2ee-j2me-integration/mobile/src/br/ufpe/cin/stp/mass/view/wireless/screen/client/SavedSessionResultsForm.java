package br.ufpe.cin.stp.mass.view.wireless.screen.client;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;

import br.ufpe.cin.stp.mass.view.wireless.handler.Session;
import br.ufpe.cin.stp.mass.view.wireless.screen.ScreenComponentFactory;

/**
 * @created 03/08/2004 01:38:06
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class SavedSessionResultsForm extends Form {
    
	private ChoiceGroup sessionTitles;
    private Command viewSessionCommand;
    private Command backCommand;    
    
    private Session[] sessions;
    
    public SavedSessionResultsForm(Session[] sessions){
        super("[M.A.S.S. :: Saved Session Results]");
    	this.sessions = sessions;    
        ScreenComponentFactory cf = ScreenComponentFactory.getInstance();
    	
        this.initForm();
    	
    	this.append(this.sessionTitles);
        this.viewSessionCommand = cf.createCommand("VIEW");
        this.backCommand = cf.createBackCommand("BACK");    	
        
        this.addCommand(this.viewSessionCommand);
        this.addCommand(this.backCommand);
    }
    
    private void initForm(){
        String[] sessionTitles = new String[this.sessions.length];
        for (int i = 0; i < this.sessions.length; i++) {
            sessionTitles[i] = this.sessions[i].getTitle();
        }
        this.sessionTitles = ScreenComponentFactory.getInstance().createExclusiveList("Saved Session Results",sessionTitles);
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
    public Command getViewSessionCommand() {
        return this.viewSessionCommand;
    }        
    /**
     * @created 03/08/2004 08:56:45
     * @return Returns the questionItems.
     */
    public ChoiceGroup getSessionTitles() {
        return this.sessionTitles;
    }
    
    /**
     * @param item is the item of the menu. Use the defined constants.
     * @return verifies which of the the main menu items was chosen.
     * @created 04/07/2004 23:36:36
     */
    public int getSelectedRecordIndex(){
        int position = this.sessionTitles.getSelectedIndex();
        return this.sessions[position].getIndex();
    }    
}



