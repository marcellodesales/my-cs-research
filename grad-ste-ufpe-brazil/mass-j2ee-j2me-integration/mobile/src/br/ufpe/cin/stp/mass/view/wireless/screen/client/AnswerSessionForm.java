package br.ufpe.cin.stp.mass.view.wireless.screen.client;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;

import br.ufpe.cin.stp.mass.view.wireless.handler.Question;
import br.ufpe.cin.stp.mass.view.wireless.handler.QuestionItem;
import br.ufpe.cin.stp.mass.view.wireless.handler.Session;
import br.ufpe.cin.stp.mass.view.wireless.screen.ScreenComponentFactory;

/**
 * @created 03/08/2004 01:38:06
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class AnswerSessionForm extends Form {
    
    private ChoiceGroup questionItems;
	
    private Command viewCommand;
    private Command backCommand;    
    private Session session;
    private byte questionType;
    
    public AnswerSessionForm(Session session){
        super(session.getTitle());
    	this.session = session;    
        ScreenComponentFactory cf = ScreenComponentFactory.getInstance();
    	
        this.initForm();
    	
    	this.append(this.questionItems);
        this.viewCommand = cf.createCommand("VOTE");
        this.backCommand = cf.createBackCommand("BACK");    	
        
        this.addCommand(this.viewCommand);
        this.addCommand(this.backCommand);
    }
    
    private void initForm(){
        Question question = this.session.getQuestions()[0];
        QuestionItem[] questionItems = question.getItems();
        String[] menuItems = new String[questionItems.length];
        for (int i = 0; i < questionItems.length; i++) {
            menuItems[i] = questionItems[i].getDescription();
        }
        if (question.getType() == Question.SINGLECHOICE){
            this.questionItems = ScreenComponentFactory.getInstance().createExclusiveList(question.getTitle(),menuItems);
            this.questionType = Question.SINGLECHOICE;
        } else 
        if (question.getType() == Question.MULTICHOICE){
            this.questionItems = ScreenComponentFactory.getInstance().createMultipleList(question.getTitle(),menuItems);
            this.questionType = Question.MULTICHOICE;
        }
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
    public Command getViewCommand() {
        return this.viewCommand;
    }        
    /**
     * @created 03/08/2004 08:56:45
     * @return Returns the questionItems.
     */
    public ChoiceGroup getSavedSessions() {
        return this.questionItems;
    }
    
    /**
     * @param item is the item of the menu. Use the defined constants.
     * @return verifies which of the the main menu items was chosen.
     * @created 04/07/2004 23:36:36
     */
    public int getSelectedSessionRecordValue(){
        return this.questionItems.getSelectedIndex();
    }    
    
    /**
     * @created 03/08/2004 10:07:59
     * @return the selected question items identifications.
     */
    public String[] getSelectedQuestionItems(){
        String[] itemsID = null;
        if (this.questionType == Question.SINGLECHOICE){
            itemsID = new String[1];
            itemsID[0] = this.session.getQuestions()[0].getItems()[this.questionItems.getSelectedIndex()].getId();
        } else 
        if (this.questionType == Question.MULTICHOICE){
            System.out.println("Needs implementation for multichoice questions");
        }
        return itemsID;
    }
}



