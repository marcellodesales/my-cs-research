/*
 * @created 31/07/2004 15:42:16
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.controller;

import br.ufpe.cin.stp.mass.model.SessionFactory;
import br.ufpe.cin.stp.mass.model.session.AbstractSession;
import br.ufpe.cin.stp.mass.model.session.Question;
import br.ufpe.cin.stp.mass.model.session.QuestionItem;
import br.ufpe.cin.stp.mass.model.session.Session;
import br.ufpe.cin.stp.mass.persistence.PersistenceBrokerFactory;
import br.ufpe.cin.stp.mass.persistence.PersistenceLayer;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;

/**
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 31/07/2004 15:42:16
 */
public class CurrentOpenedSessionController {
    
    private PersistenceLayer pl;
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static CurrentOpenedSessionController singleton;

    private CurrentOpenedSessionController() {
        this.pl = PersistenceBrokerFactory.getInstance().getPersistenceLayer();
    }

    /**
     * @created 31/07/2004 15:42:25
     * @return The single instance.
     */
    public synchronized static CurrentOpenedSessionController getInstance() {
        if (singleton == null) {
            singleton = new CurrentOpenedSessionController();
        }
        return singleton;
    }
    
    /**
     * @created 31/07/2004 15:44:18
     * @return a predefined survey for using on tests.
     */
    public Session getDefaultSurvey(){
        
        SessionFactory sf = SessionFactory.getInstance();
        
        AbstractSession session = sf.createNewSurvey("OS War");
        
        Question quest = sf.createNewSingleChoiceQuestion("Best OS Features");     
        QuestionItem item1 = sf.createNewQuestionItem("Windows");        
        QuestionItem item2 = sf.createNewQuestionItem("Linux");        
        QuestionItem item3 = sf.createNewQuestionItem("Mac OS");        
        QuestionItem item4 = sf.createNewQuestionItem("Sun Spark");
        
        quest.addItem(item1);
        quest.addItem(item2);
        quest.addItem(item3);
        quest.addItem(item4);
        
        session.addQuestion(quest); 
        return session;
    }
    
    /**
     * @created 02/07/2004 00:59:30
     * @return the current opened session with the attribute 'opened' enabled.
     * if there's no session opened, a default session will be created.
     */
    public Session getCurrentOpenedSession() throws PersistentObjectNotFoundException{
        Session  session = (Session)this.pl.find("opened",new Boolean(true),AbstractSession.class);
        return session;
    }    
}
