/*
 * @created 15/08/2004 13:13:17
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.xmlpull;

import br.ufpe.cin.stp.mass.model.session.Session;

/**
 * @created 15/08/2004 13:13:17
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class ExistingSessionsXMLPull {

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static ExistingSessionsXMLPull singleton;

    private ExistingSessionsXMLPull() {
    }

    /**
     * @created 15/08/2004 13:13:31
     * @return The single instance.
     */
    public synchronized static ExistingSessionsXMLPull getInstance() {
        if (singleton == null) {
            singleton = new ExistingSessionsXMLPull();
        }
        return singleton;
    }
    
    /**
     * @created 15/08/2004 13:13:53
     * @param actionOk
     * @param message
     * @return the existing sessions
     */
    public StringBuffer pullExistingSessions(Session[] sessions){
        StringBuffer xmlBuffer = new StringBuffer();
        xmlBuffer.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
        xmlBuffer.append("<sessions exists='"+ (sessions.length!=0) +"'>");
        for (int i = 0; i < sessions.length; i++)
            xmlBuffer.append("<session id='"+sessions[i].getID()+"' title='"+sessions[i].getTitle()+"' opened='"+sessions[i].isOpened()+"'/>");
        xmlBuffer.append("</sessions>");
 
        return xmlBuffer;
    }    
}
