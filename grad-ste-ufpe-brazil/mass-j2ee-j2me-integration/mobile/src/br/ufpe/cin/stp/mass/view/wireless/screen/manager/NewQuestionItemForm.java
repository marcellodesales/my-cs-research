/*
 * @created 05/07/2004 00:36:01
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.screen.manager;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.ufpe.cin.stp.mass.view.wireless.screen.ScreenComponentFactory;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 05/07/2004 00:36:01
 */
public class NewQuestionItemForm extends Form {
	
	private TextField questionItemField;
	private Command addItemCommand;
	private Command backCommand;
	
    public NewQuestionItemForm(){
        super("[M.A.S.S. :: New Question Item Menu]");
        
        ScreenComponentFactory cf = ScreenComponentFactory.getInstance();
        
        this.questionItemField = new TextField("Question Item","",30,TextField.ANY);
        
        this.addItemCommand = cf.createCommand("ADD");
        this.backCommand = cf.createBackCommand("BACK");
        
        this.append(this.questionItemField);
        this.addCommand(this.addItemCommand);
        this.addCommand(this.backCommand);
    }
    
    /**
     * @return Returns the cancelCommand.
     * @created 05/07/2004 01:13:30
     */
    public Command getBackCommand() {
        return this.backCommand;
    }
    
    /**
     * @return Returns the getAddItemCommand.
     * @created 05/07/2004 01:13:30
     */
    public Command getAddItemCommand() {
        return this.addItemCommand;
    }
    
    /**
     * @return Returns the QuestionItemTitle.
     * @created 05/07/2004 01:13:30
     */
    public String getItemFieldString() {
        return this.questionItemField.getString();
    }
    
    public void setItemFieldValue(String value){
        this.questionItemField.setString(value);
    }
}
