/*
 * @created 04/07/2004 23:03:11
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.screen.client;

import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

import br.ufpe.cin.stp.mass.view.wireless.screen.ScreenComponentFactory;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 04/07/2004 23:03:11
 */
public class MainMenuForm extends List {

	public static final String ANSWER_SESSION = "Answer Current Session";
	public static final String CURRENT_SESSION_RESULTS = "View Current Session Results";
	public static final String SAVED_SESSION_RESULTS = "View Saved Session Results";
    
    private final String[] menuItems = {ANSWER_SESSION,CURRENT_SESSION_RESULTS,SAVED_SESSION_RESULTS};
	/**
	 * The select Command to cancel. It's the LSK=CANCEL
	 */
	private Command selectCommand;
	private Command quitCommand;
	
    /**
     * Create the main application form.
     * @created 04/07/2004 23:12:42
     */
    public MainMenuForm(){
        super("[M.A.S.S. :: Main Menu]",List.IMPLICIT);
        final String imageItem = "/qitem.png";
        for (int i = 0; i < this.menuItems.length; i++)
            try {
                this.append(menuItems[i],Image.createImage(imageItem));
            } catch (IOException e) {
                e.printStackTrace();
            }        
        this.selectCommand = ScreenComponentFactory.getInstance().createCommand("SELECT");
        this.quitCommand = ScreenComponentFactory.getInstance().createBackCommand("QUIT");
        
        this.addCommand(this.selectCommand);
        this.addCommand(this.quitCommand);
    }
    
    /**
     * @return gets the Select Command of the main menu.
     * @created 04/07/2004 23:12:11
     */
    public Command getSelectCommand() {
        return this.selectCommand;
    }
    
    /**
     * @created 05/04/2004 08:24:25
     * @return Returns the quitCommand.
     */
    public Command getQuitCommand() {
        return this.quitCommand;
    }
    
    /**
     * @created 04/04/2004 21:45:06
     * @param menuItem
     * @return if the menuItem was selected.
     */
    public boolean selectedMenuItemEquals(String menuItem){
        return this.getString(this.getSelectedIndex()).equals(menuItem);
    }    
}
