/*
 * @created 31/07/2004 17:47:32
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.mass.model.session;

import br.ufpe.cin.stp.mass.model.MassException;

/**
 * It's an exception that accour when the user tries to
 * vote on a closed session.
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 31/07/2004 17:47:32
 */
public class SessionClosedException extends MassException {
    
    private Session session;
    
    /**
     * Creates a new SessionClosedException with a given
     * message and the closed session.
     * @param message to the user.
     * @param session that was already closed.
     * @created 31/07/2004 17:57:04
     */
    public SessionClosedException(String message, Session session){
        super(message);
        this.session = session;
    }
    
    /**
     * @return the closed session.
     * @created 31/07/2004 17:56:48
     */
    public Session getClosedSession(){
        return this.session;
    }
}
