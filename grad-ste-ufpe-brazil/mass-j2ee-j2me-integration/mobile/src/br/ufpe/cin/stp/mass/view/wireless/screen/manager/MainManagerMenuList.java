/*
 * @created 04/07/2004 23:03:11
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.screen.manager;

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
public class MainManagerMenuList extends List {

	public static final String CREATE_SESSION = "Create New Session";
	public static final String CLOSE_SESSION_RESULTS = "Close Session";
    
    private final String[] menuItems = {CREATE_SESSION,CLOSE_SESSION_RESULTS};

	private Command selectCommand;
	private Command quitCommand;
	
    /**
     * Create the main application form.
     * @created 04/07/2004 23:12:42
     */
    public MainManagerMenuList(){
        super("[M.A.S.S. :: Main Manager Menu]",List.IMPLICIT);
        final String imageItem = "/qitem.png";
        try {
            for (int i = 0; i < this.menuItems.length; i++)
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
     * @return gets the Quit Command of the main menu.
     * @created 04/07/2004 23:12:11
     */
    public Command getQuitCommand() {
        return this.quitCommand;
    }
    
    public Command getSelectCommand() {
        return this.selectCommand;
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
