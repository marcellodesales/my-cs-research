/*
 * @created 04/04/2004 09:19:03
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.screen.manager;

import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

import br.ufpe.cin.stp.mass.view.wireless.screen.ScreenComponentFactory;

/**
 * @created 04/04/2004 09:19:03
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class QuestionItemsList extends List {
    
	private Command itemsOptionCommand;
	private Command backCommand;
	
    /**
     * Creates a new List with the given Question Items. The 
     * Last one will always be the "New..." option.
     * @created 04/04/2004 11:20:43
     * @param items
     */
    public QuestionItemsList(String[] items){
        super("[M.A.S.S. :: Question Items Menu]",List.IMPLICIT);
        try {
            final String imageItem = "/qitem.png";
	        for (int i = 0; i < items.length; i++) 
	            this.append(items[i],Image.createImage(imageItem));
        
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.itemsOptionCommand = ScreenComponentFactory.getInstance().createCommand("OPTIONS");
        this.backCommand = ScreenComponentFactory.getInstance().createBackCommand("BACK");
        
        this.addCommand(this.itemsOptionCommand);
        this.addCommand(this.backCommand);        
    }
    
    /**
     * @created 04/04/2004 11:20:34
     * @return Returns the quitCommand.
     */
    public Command getBackCommand() {
        return this.backCommand;
    }
    /**
     * @created 04/04/2004 11:20:34
     * @return Returns the selectCommand.
     */
    public Command getItemsOptionCommand() {
        return this.itemsOptionCommand;
    }
    
    /**
     * @created 04/04/2004 11:25:44
     * @return the items of the list without the "New..." item.
     */
    public String[] getItemsList(){
        final int number = this.size();
        String[] items = new String[number-1];
        
        for (int i = 0; i < number-1; i++) {
            items[i] = this.getString(i);
        }
        return items;
    } 
}
