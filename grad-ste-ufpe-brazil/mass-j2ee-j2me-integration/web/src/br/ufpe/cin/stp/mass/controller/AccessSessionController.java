/*
 * @created 15/08/2004 12:07:12
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.controller;

import br.ufpe.cin.stp.mass.model.session.Session;
import br.ufpe.cin.stp.mass.persistence.PersistenceBrokerFactory;
import br.ufpe.cin.stp.mass.persistence.PersistenceLayer;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;

/**
 * @created 15/08/2004 12:07:12
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class AccessSessionController {

    private PersistenceLayer pl = PersistenceBrokerFactory.getInstance().getPersistenceLayer();
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static AccessSessionController singleton;

    private AccessSessionController() {
    }

    /**
     * @created 15/08/2004 12:07:17
     * @return The single instance.
     */
    public synchronized static AccessSessionController getInstance() {
        if (singleton == null) {
            singleton = new AccessSessionController();
        }
        return singleton;
    }
    
    /**
     * @created 15/08/2004 12:11:16
     * @param sessionID
     * @return a session instance of a given sessionID.
     * @throws PersistentObjectNotFoundException if no instance with the given identification was found.
     */
    public Session getSession(String sessionID) throws PersistentObjectNotFoundException{
        return (Session)this.pl.find(sessionID,Session.class);
    }
    
    /**
     * @created 15/08/2004 13:09:59
     * @return the instances of sessions.
     * @throws PersistentObjectNotFoundException if no instance was found.
     */
    public Session[] getSessions() throws PersistentObjectNotFoundException{
        Object[] instances = this.pl.find(Session.class);
        Session[] sessions = new Session[instances.length];
        for (int i = 0; i < instances.length; i++) {
            sessions[i] = (Session)instances[i];
        }
        return sessions; 
    }
}
