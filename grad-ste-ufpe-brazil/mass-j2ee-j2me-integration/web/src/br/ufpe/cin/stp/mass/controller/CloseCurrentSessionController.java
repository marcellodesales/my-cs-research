/*
 * @created 15/08/2004 11:21:44
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.controller;

import br.ufpe.cin.stp.mass.model.session.Session;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;

/**
 * @created 15/08/2004 11:21:44
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class CloseCurrentSessionController {

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static CloseCurrentSessionController singleton;

    private CloseCurrentSessionController() {
    }

    /**
     * @created 15/08/2004 11:21:55
     * @return The single instance.
     */
    public synchronized static CloseCurrentSessionController getInstance() {
        if (singleton == null) {
            singleton = new CloseCurrentSessionController();
        }
        return singleton;
    }
    
    public boolean closeCurrentSession() throws PersistentObjectNotFoundException {
        Session current = CurrentOpenedSessionController.getInstance().getCurrentOpenedSession();
        current.closeSession();
        return true;
    }
    
/*    public static void main(String[] args) {
        try {
            Session newS = CurrentOpenedSessionController.getInstance().getDefaultSurvey();
            PersistenceLayer pl = PersistenceBrokerFactory.getInstance().getPersistenceLayer();
            CloseCurrentSessionController.getInstance().closeCurrentSession();
            String[] items = {"Item1","Item2"};
            
            
            Session session = MassFacade.getInstance().getSession(newS.getID());
            SessionResults sr = MassFacade.getInstance().getSessionResults(session.getID());

            
            Session newSession = CreateSessionController.getInstance().createNewSurvey("My Survey","U have??",items);
            
            Session current = CurrentOpenedSessionController.getInstance().getCurrentOpenedSession();
            System.out.println(current.getTitle());
            
            Object[] instances = pl.find(Session.class);
            System.out.println(instances.length);
            System.out.println(((Session)instances[0]).isOpened());
            System.out.println(((Session)instances[1]).isOpened());
            
        } catch (PersistentObjectNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
}
