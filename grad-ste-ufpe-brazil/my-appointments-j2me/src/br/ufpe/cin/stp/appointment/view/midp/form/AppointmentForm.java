/*
 * @created 04/07/2004 23:03:11
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.appointment.view.midp.form;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;

import br.ufpe.cin.stp.appointment.view.midp.ComponentFactory;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 04/07/2004 23:03:11
 */
public class AppointmentForm extends Form {

    /**
     * <code>ADD</code> is the add menu item.
     */
    public static final String ADD = "Add new Appointment";
    /**
     * <code>VIEW</code> is the view menu item.
     */
    public static String VIEW = "View Appointments";
    /**
     * <code>EDIT</code> is the edit menu item.
     */
    public static String EDIT = "Edit an Appointment";
    /**
     * <code>REMOVE</code> is the remove menu item.
     */
    public static String REMOVE = "Remove Appointments";    
    
    private final String[] menuItems = {ADD,VIEW,EDIT,REMOVE};
	/**
	 * This is the choice group component initialized with the typeStrings constant.
	 */
	private ChoiceGroup mainMenu;
	/**
	 * The select Command to cancel. It's the LSK=CANCEL
	 */
	private Command selectCommand;
	
    /**
     * Create the main application form.
     * @created 04/07/2004 23:12:42
     */
    public AppointmentForm(){
        super("My Appointments");
        this.mainMenu = ComponentFactory.getInstance().createExclusiveList("Where do you want to go now?",this.menuItems);        
        this.selectCommand = ComponentFactory.getInstance().createCommand("SELECT");
        
        this.append(this.mainMenu);
        this.addCommand(this.selectCommand);
    }
    
    /**
     * @return gets the Select Command of the main menu.
     * @created 04/07/2004 23:12:11
     */
    public Command getSelectCommand() {
        return this.selectCommand;
    }
    
    /**
     * @param item is the item of the menu. Use the defined constants.
     * @return verifies which of the the main menu items was chosen.
     * @created 04/07/2004 23:36:36
     */
    public boolean selectedItemEquals(String item){
        return this.mainMenu.getString(this.mainMenu.getSelectedIndex()).equals(item);
    }
}
