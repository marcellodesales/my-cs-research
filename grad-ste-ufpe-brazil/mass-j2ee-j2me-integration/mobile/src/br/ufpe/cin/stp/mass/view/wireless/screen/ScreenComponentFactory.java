package br.ufpe.cin.stp.mass.view.wireless.screen;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;

/**
 * @created 04-jul-2004 12:36:04 This class is the main factory of the application.
 * 
 * @author Marcello Sales Jr.
 * @version 1.0
 * @updated 04-jul-2004 13:57:38
 */
public class ScreenComponentFactory{
    
	/**
	 * The single instance of this class.
	 */
	private static ScreenComponentFactory singleton;

	private ScreenComponentFactory(){
	}

	/**
	 * It's an exclusive ChoiceGroup where only one option can be chosen.
	 * @param items    The items to be listed.
	 * @param title    This is the title of the list
	 */
	public ChoiceGroup createExclusiveList(String title,String[] items){
	    return new ChoiceGroup(title,ChoiceGroup.EXCLUSIVE,items,null);
	}

	/**
	 * This is the main access to the instance of this class.
	 */
	public synchronized static ScreenComponentFactory getInstance(){
		if (singleton == null){
		    singleton = new ScreenComponentFactory();
		}
		return singleton;
	}

	/**
	 * Creates a new ChoiceGroup that multiple items can be chosen.
	 * @param title    The title of the list.
	 * @param items    The items of the list.
	 * 
	 */
	public ChoiceGroup createMultipleList(String title, String[] items){
		return null;
	}

	/**
	 * Creates a new Info Alert with a specified title, message and delay
	 * @param title    The title of the alert.
	 * @param message    The message of the alert.
	 * @param delay    How many seconds the alert will be shown to the user.
	 * 
	 */
	public Alert createInfoAlert(String title, String message, int delay){
        Alert alert = new Alert(title);
        alert.setTimeout(delay);
        alert.setString(message);
        alert.setType(AlertType.INFO);
        return alert;
	}
	
	/**
	 * Creates a new Error Alert with a specified title, message and delay
	 * @created 03/08/2004 10:28:27
	 * @param title is the title of the alert.
	 * @param message is the message of the alert.
	 * @param delay is how many seconds the alert will be shown to the user (milliseconds).
	 * @return
	 */
	public Alert createErrorAlert(String title, String message, int delay){
        Alert alert = new Alert(title);
        alert.setTimeout(delay);
        alert.setString(message);
        alert.setType(AlertType.ERROR);
        return alert;
	}	

	/**
	 * Creates a command with a given description
	 * @param title    The title of the command.
	 * 
	 */
	public Command createCommand(String title){
        return new Command(title, Command.SCREEN, 1);
	}

	/**
	 * (04/07/2004 13:58:45)
	 * @param title the title of the command.
	 * @return a new default 'BACK' key Command with a given title.
	 */
	public Command createBackCommand(String title){
		return new Command(title,Command.BACK,0);
	}
}