/*
 * @created 01/08/2004 12:02:06
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.mass.model;

import br.ufpe.cin.stp.mass.model.session.Answer;
import br.ufpe.cin.stp.mass.model.session.Question;
import br.ufpe.cin.stp.mass.model.session.QuestionItem;
import br.ufpe.cin.stp.mass.model.session.Questionary;
import br.ufpe.cin.stp.mass.model.session.Session;
import br.ufpe.cin.stp.mass.model.session.SessionResults;
import br.ufpe.cin.stp.mass.model.session.Survey;

/**
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 01/08/2004 12:02:06
 */
public class SessionFactory {

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static SessionFactory factorySingleton;

    private SessionFactory() {
    }

    /**
     * @created 01/08/2004 12:02:17
     * @return The single instance.
     */
    public synchronized static SessionFactory getInstance() {
        if (factorySingleton == null) {
            factorySingleton = new SessionFactory();
        }
        return factorySingleton;
    }

    /**
     * @param title is the title of the survey.
     * @created 01/08/2004 12:02:17
     * @return a new Survey.
     */
    public Survey createNewSurvey(String title) {
        return new Survey(title);
    }
    
    /**
     * @param title is the title of the questionary.
     * @return a new Questionary.
     * @created 01/08/2004 12:04:25
     */
    public Questionary createNewQuestionary(String title){
        return new Questionary(title);
    }
    
    /**
     * @created 01/08/2004 12:10:28
     * @param title is the title of the QuestionItem.
     * @return a new QuestionItem.
     */
    public QuestionItem createNewQuestionItem(String title){
        return new QuestionItem(title);
    }
    
    /**
     * @created 01/08/2004 12:19:59
     * @param title is the title of the choice.
     * @return a new single choice Question with a give title.
     */
    public Question createNewSingleChoiceQuestion(String title){
        return new Question(title,Question.SINGLECHOICE);
    }
    
    /**
     * @created 01/08/2004 12:19:14
     * @param title is the title of the question.
     * @return a new multi choice question with a given title.
     */
    public Question createNewMultipleChoiceQuestion(String title){
        return new Question(title,Question.MULTICHOICE);
    }    
    
    /**
     * @created 01/08/2004 12:12:11
     * @param sessionID
     * @param senderID
     * @return a new Answer for a give session, created by a sender.
     */
    public Answer createNewAnswer(String sessionID, String senderID){
        return new Answer(sessionID,senderID);
    }
    
    /**
     * @created 01/07/2004 22:20:25
     * @param session
     * @return a new SessionResults for a given session instance.
     */
    public SessionResults createNewSessionResults(Session session){
        return new SessionResults(session);
    }
}
