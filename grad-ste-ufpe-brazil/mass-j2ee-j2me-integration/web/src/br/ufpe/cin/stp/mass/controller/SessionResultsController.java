/*
 * @created 01/07/2004 19:11:40
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.controller;

import br.ufpe.cin.stp.mass.model.SessionFactory;
import br.ufpe.cin.stp.mass.model.session.Session;
import br.ufpe.cin.stp.mass.model.session.SessionResults;
import br.ufpe.cin.stp.mass.persistence.PersistenceBrokerFactory;
import br.ufpe.cin.stp.mass.persistence.PersistenceLayer;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;

/**
 * @created 01/07/2004 19:11:40
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class SessionResultsController {
    
    private PersistenceLayer pl = PersistenceBrokerFactory.getInstance().getPersistenceLayer();
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static SessionResultsController singleton;

    private SessionResultsController() {
    }

    /**
     * @created 01/07/2004 19:11:49
     * @return The single instance.
     */
    public synchronized static SessionResultsController getInstance() {
        if (singleton == null) {
            singleton = new SessionResultsController();
        }
        return singleton;
    }
    
    /**
     * @created 01/07/2004 22:42:06
     * @param sessionID is the identification of the session.
     * @return a new SessionResults wrapper for a given SessionID.
     * @throws PersistentObjectNotFoundException if the sessionID does not represent a session object.
     */
    public SessionResults getSessionResults(String sessionID) throws PersistentObjectNotFoundException{
        Session session = (Session)pl.find(sessionID,Session.class);
        return SessionFactory.getInstance().createNewSessionResults(session);
    }
}
