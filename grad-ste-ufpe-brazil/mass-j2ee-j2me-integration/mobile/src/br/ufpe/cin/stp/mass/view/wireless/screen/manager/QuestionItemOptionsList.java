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
public class QuestionItemOptionsList extends List {

	public static final String ADD_ITEM = "Add New Item";
	public static final String REMOVE_ITEM = "Remove Selected Item";
	public static final String SAVE_ITEMS = "Save Item List";
    
	private String[] menuItems;
	
	/**
	 * The select Command to cancel. It's the LSK=CANCEL
	 */
	private Command selectCommand;
	private Command backCommand;
	
	private String[] items;
	private int itemSelected;
    /**
     * Create the main application form.
     * @created 04/07/2004 23:12:42
     */
    public QuestionItemOptionsList(String[] items, int itemSeleted){
        super("[M.A.S.S. :: Question Item Menu]",List.IMPLICIT);
        this.items = items;
        this.itemSelected = itemSeleted;
        
        if (items.length == 1){ 
            if (items[0].equals(" New...")) //if it's the first time to create an item
                this.menuItems = new String[]{ADD_ITEM};
            else //if there already exists an item on the list created...
                this.menuItems = new String[]{ADD_ITEM,REMOVE_ITEM};
        //if there already exists more than 1 item.
        }else this.menuItems = new String[]{ADD_ITEM,REMOVE_ITEM,SAVE_ITEMS};
        
        final String imageItem = "/qitem.png";
        for (int i = 0; i < this.menuItems.length; i++)
            try {
                this.append(menuItems[i],Image.createImage(imageItem));
            } catch (IOException e) {
                e.printStackTrace();
            }        
                
        this.selectCommand = ScreenComponentFactory.getInstance().createCommand("SELECT");
        this.backCommand = ScreenComponentFactory.getInstance().createBackCommand("BACK");
        
        this.addCommand(this.selectCommand);
        this.addCommand(this.backCommand);
    }
    
    /**
     * @return gets the Select Command of the main menu.
     * @created 04/07/2004 23:12:11
     */
    public Command getSelectCommand() {
        return this.selectCommand;
    }
    
    /**
     * @created 04/04/2004 19:33:41
     * @return Returns the backCommand.
     */
    public Command getBackCommand() {
        return this.backCommand;
    }
    
    /**
     * @created 04/04/2004 21:45:06
     * @param menuItem
     * @return if the menuItem was selected.
     */
    public boolean selectedMenuItemEquals(String menuItem){
        return this.getString(this.getSelectedIndex()).equals(menuItem);
    }    
    /**
     * @created 04/04/2004 22:53:56
     * @return Returns the itemSelected.
     */
    public int getItemSelected() {
        return this.itemSelected;
    }
}
