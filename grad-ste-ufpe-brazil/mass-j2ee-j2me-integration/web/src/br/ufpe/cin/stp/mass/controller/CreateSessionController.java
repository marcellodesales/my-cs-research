/*
 * @created 02/07/2004 00:13:07
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.mass.controller;

import java.util.Map;

import br.ufpe.cin.stp.mass.model.SessionFactory;
import br.ufpe.cin.stp.mass.model.session.AbstractSession;
import br.ufpe.cin.stp.mass.model.session.Question;
import br.ufpe.cin.stp.mass.model.session.QuestionItem;
import br.ufpe.cin.stp.mass.model.session.Session;

/**
 * @created 02/07/2004 00:13:07
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class CreateSessionController {

    private SessionFactory sf;
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static CreateSessionController singleton;

    private CreateSessionController() {
        this.sf = SessionFactory.getInstance();
    }

    /**
     * @created 02/07/2004 00:13:25
     * @return The single instance.
     */
    public synchronized static CreateSessionController getInstance() {
        if (singleton == null) {
            singleton = new CreateSessionController();
        }
        return singleton;
    }
    
    /**
     * @created 02/07/2004 00:23:22
     * @param surveyTitle is the title of the survey.
     * @param questionTitle is the title of the uniq question.
     * @param itemsTitle is the set of titles of each item.
     * @return a new Survey Instance.
     */
    public Session createNewSurvey(String surveyTitle, String questionTitle, String[] itemsTitle){
        AbstractSession session = this.sf.createNewSurvey(surveyTitle);        
        Question question = this.sf.createNewSingleChoiceQuestion(questionTitle);
        
        for (int i = 0; i < itemsTitle.length; i++) {
            QuestionItem qItem = this.sf.createNewQuestionItem(itemsTitle[i]);
            question.addItem(qItem);
        }
        
        session.addQuestion(question);
        return (Session)session;
    }
    
    /**
     * needs implementation!!!!
     * @created 02/07/2004 00:28:30
     * @param title is the title of the questionary.
     * @param questionsAndItems is the map of question titles and a set of item titles. (question:string -> itemsTitle:string[])
     * @return a new Questionary if a given title and a set of questions and theirs respective items.
     */
    public Session createNewQuestionary(String title, Map questionsAndItems){
        // TODO: needs implementation!!!!
        return null;
    }
}
