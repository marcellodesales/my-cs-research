/*
 * @created 01/07/2004 17:40:29
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.xmlpull;

/**
 * @created 01/07/2004 17:40:29
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class ResponseXMLPull {
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static ResponseXMLPull singleton;

    private ResponseXMLPull() {
    }

    /**
     * @created 01/07/2004 17:40:44
     * @return The single instance.
     */
    public synchronized static ResponseXMLPull getInstance() {
        if (singleton == null) {
            singleton = new ResponseXMLPull();
        }
        return singleton;
    }
    
    /**
     * @created 01/07/2004 17:48:48
     * @param wasOk is the flag indicating if the action was ok.
     * @param message is the returned message of the response.
     * @return the default xml pulled for a response.
     */
    public StringBuffer pullResponse(boolean actionOk, String message){
        StringBuffer xmlBuffer = new StringBuffer();
        xmlBuffer.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
        xmlBuffer.append("<response actionOk='"+actionOk+"'>");
        xmlBuffer.append("<message>"+message+"</message>");
        xmlBuffer.append("</response>");
        return xmlBuffer;
    }
}
