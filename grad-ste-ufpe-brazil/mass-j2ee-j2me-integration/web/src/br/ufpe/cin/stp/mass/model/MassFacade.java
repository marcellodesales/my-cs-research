/*
 * @created 31/07/2004 15:47:04
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.model;

import br.ufpe.cin.stp.mass.controller.AccessSessionController;
import br.ufpe.cin.stp.mass.controller.AnswerSessionController;
import br.ufpe.cin.stp.mass.controller.CloseCurrentSessionController;
import br.ufpe.cin.stp.mass.controller.CreateSessionController;
import br.ufpe.cin.stp.mass.controller.CurrentOpenedSessionController;
import br.ufpe.cin.stp.mass.controller.SessionResultsController;
import br.ufpe.cin.stp.mass.model.session.Session;
import br.ufpe.cin.stp.mass.model.session.SessionClosedException;
import br.ufpe.cin.stp.mass.model.session.SessionResults;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;

/**
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 31/07/2004 15:47:04
 */
public class MassFacade {
    
    private CurrentOpenedSessionController gcsc;
    private AnswerSessionController asc;
    private SessionResultsController src;
    private CreateSessionController csc;
    private AccessSessionController acsc;
    private CloseCurrentSessionController ccsc;
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static MassFacade singleton;

    private MassFacade() {
        this.gcsc = CurrentOpenedSessionController.getInstance();
        this.asc = AnswerSessionController.getInstance();
        this.src = SessionResultsController.getInstance();
        this.csc = CreateSessionController.getInstance();
        this.acsc = AccessSessionController.getInstance();
        this.ccsc = CloseCurrentSessionController.getInstance();
    }

    /**
     * @created 31/07/2004 15:59:03
     * @return The single instance.
     */
    public synchronized static MassFacade getInstance() {
        if (singleton == null) {
            singleton = new MassFacade();
        }
        return singleton;
    }
    
    /**
     * @created 03/04/2004 22:02:55
     * @return the current session of the MASS
     * @throws PersistentObjectNotFoundException if no opened session was found.
     */
    public Session getCurrentOpenedSurvey() throws PersistentObjectNotFoundException{
        return this.gcsc.getCurrentOpenedSession();
    }
    
    /**
     * @created 01/08/2004 13:17:59
     * @param sessionID is the identification of the session.
     * @param senderID is the identification of the sender.
     * @param itemsID is the set of identifications of the chosen items.
     * @throws PersistentObjectNotFoundException if any of the identifiers was not valid and no object was found on the repository.
     * @throws SessionClosedException if the session was already closed.
     */
    public void answerSession(String sessionID,String senderID,String[] itemsID) throws PersistentObjectNotFoundException, SessionClosedException{
        this.asc.answerSession(sessionID,senderID,itemsID);
    }
    
    /**
     * @created 01/07/2004 23:04:21
     * @param sessionID
     * @return the session results of a given session identification.
     * @throws PersistentObjectNotFoundException if the session object is not found on the repository if the given sessionID.
     */
    public SessionResults getSessionResults(String sessionID) throws PersistentObjectNotFoundException{
        return this.src.getSessionResults(sessionID);
    }

    /**
     * @created 02/07/2004 01:18:16
     * @param surveyTitle
     * @param questionTitle
     * @param itemsTitle
     * @return a new session with the given information.
     */
    public Session createNewSurvey(String surveyTitle, String questionTitle, String[] itemsTitle){
        return this.csc.createNewSurvey(surveyTitle,questionTitle,itemsTitle);
    }
    
    /**
     * @created 15/08/2004 12:12:58
     * @param sessionID
     * @return the session instance of a given sessionID.
     * @throws PersistentObjectNotFoundException if no session was found with the given identification.
     */
    public Session getSession(String sessionID) throws PersistentObjectNotFoundException{
        return this.acsc.getSession(sessionID);
    }
    
    /**
     * @created 15/08/2004 12:18:30
     * @return closes the current session notification
     * @throws PersistentObjectNotFoundException if there's no session instance.
     */
    public boolean closeCurrentSession() throws PersistentObjectNotFoundException{
        return this.ccsc.closeCurrentSession();
    }
    
    /**
     * @created 15/08/2004 13:11:04
     * @return the existing sessions
     * @throws PersistentObjectNotFoundException
     */
    public Session[] getSessions() throws PersistentObjectNotFoundException{
        return this.acsc.getSessions();
    }
    
    public static void main(String[] args) throws PersistentObjectNotFoundException {
        Session ss = (Session)MassFacade.getInstance().getCurrentOpenedSurvey();
        String[] items = {"item1","item2","item3"};
        Session sss = (Session)MassFacade.getInstance().createNewSurvey("Title poo","which one",items);
        
        Session s2 = (Session)MassFacade.getInstance().getCurrentOpenedSurvey();
        
        System.out.println(s2 == ss);
    }
}
