/*
 * @created 01/07/2004 19:23:43
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.xmlpull;

import java.util.Iterator;

import br.ufpe.cin.stp.mass.model.session.Question;
import br.ufpe.cin.stp.mass.model.session.QuestionItem;
import br.ufpe.cin.stp.mass.model.session.SessionResults;

/**
 * @created 01/07/2004 19:23:43
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class SessionResultsXMLPull {

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static SessionResultsXMLPull singleton;

    private SessionResultsXMLPull() {
    }

    /**
     * @created 01/07/2004 19:23:56
     * @return The single instance.
     */
    public synchronized static SessionResultsXMLPull getInstance() {
        if (singleton == null) {
            singleton = new SessionResultsXMLPull();
        }
        return singleton;
    }
    
    public StringBuffer pullSessionResults(SessionResults results){
        StringBuffer xmlBuffer = new StringBuffer();
        xmlBuffer.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
        xmlBuffer.append("<results sessionId='"+results.getSession().getID()+"' opened='"+results.getSession().isOpened()+"'>");
        //xmlBuffer.append(SessionXMLPull.getPulledXMLSession(results.getSession()));
        xmlBuffer.append("<votes total='"+results.getNumberOfAnswers()+"'>");
        
        Iterator it = results.getSession().getQuestions();
        while (it.hasNext()) {
            Question quest = (Question) it.next();
            xmlBuffer.append("<question id='"+quest.getID()+"'>");

            Iterator iterItems = quest.getQuestionItems();
            while (iterItems.hasNext()) {
                QuestionItem item = (QuestionItem) iterItems.next();
                xmlBuffer.append("<question-item id='"+item.getID()+"' ");
                xmlBuffer.append("number='"+results.getNumberOfOccorrencies(item.getID())+"' ");
                xmlBuffer.append("percentage='"+results.getPercentageString(item.getID())+"' />");
            }
            xmlBuffer.append("</question>");
        }
        
        xmlBuffer.append("</votes>");
        xmlBuffer.append("</results>");        
        return xmlBuffer;
    }
}
