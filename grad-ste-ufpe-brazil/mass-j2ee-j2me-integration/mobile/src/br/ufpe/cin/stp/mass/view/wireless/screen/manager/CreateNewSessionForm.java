/*
 * @created 05/07/2004 00:36:01
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.screen.manager;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.ufpe.cin.stp.mass.view.wireless.screen.ScreenComponentFactory;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 05/07/2004 00:36:01
 */
public class CreateNewSessionForm extends Form {

	/**
	 * This is the choice group component initialized with the typeStrings constant.
	 */
	private ChoiceGroup typeChoice;
	
	private TextField sessionTitleField;
	
	private TextField questionTitleField;

	private Command showQuestionItemsCommand;
	private Command cancelCommand;
	
    public CreateNewSessionForm(){
        super("[M.A.S.S. :: New Session Menu]");
        
        ScreenComponentFactory cf = ScreenComponentFactory.getInstance();
        
        //String[] types = {"Survey","Questionary"};
        String[] types = {"Survey"};
        this.typeChoice = cf.createExclusiveList("Type",types);
        this.sessionTitleField = new TextField("Title","",30,TextField.ANY);
        this.questionTitleField = new TextField("Question Title","",30,TextField.ANY);
        
        this.showQuestionItemsCommand = cf.createCommand("CONTINUE");
        this.cancelCommand = cf.createBackCommand("BACK");
        
        this.append(this.typeChoice);
        this.append(this.sessionTitleField);
        this.append(this.questionTitleField);
        
        this.addCommand(this.showQuestionItemsCommand);
        this.addCommand(this.cancelCommand);
    }
    
    /**
     * @return Returns the cancelCommand.
     * @created 05/07/2004 01:13:30
     */
    public Command getCancelCommand() {
        return this.cancelCommand;
    }
    
    /**
     * @return Returns the getAddQuestionItemsCommand.
     * @created 05/07/2004 01:13:30
     */
    public Command getShowQuestionItemsCommand() {
        return this.showQuestionItemsCommand;
    }
    
    /**
     * @return Returns the sessionTitle.
     * @created 05/07/2004 01:13:30
     */
    public String getSessionTitleFieldString() {
        return this.sessionTitleField.getString();
    }
    
    /**
     * @return Returns the QuestionTitle.
     * @created 05/07/2004 01:13:30
     */
    public String getQuestionTitleFieldString() {
        return this.questionTitleField.getString();
    }
    
    /**
     * @created 04/04/2004 20:14:29
     * @return the type of the session.
     */
    public byte getSessionType(){
        return 0;
        // TODO: Needs improvement if the questionary is to be added.
    }
}
