/*
 * @created 25/07/2004 15:46:54
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.xmlpull;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import br.ufpe.cin.stp.mass.model.MassFacade;
import br.ufpe.cin.stp.mass.model.session.Question;
import br.ufpe.cin.stp.mass.model.session.QuestionItem;
import br.ufpe.cin.stp.mass.model.session.Session;
import br.ufpe.cin.stp.mass.model.session.Survey;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;


/**
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 25/07/2004 15:46:54
 */
public class SessionXMLPull {
    
    private final static String NAMESPACE = "http://www.cin.ufpe.br/stp/mass";

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static SessionXMLPull factorySingleton;

    private SessionXMLPull() {
    }

    /**
     * @created 25/07/2004 15:47:11
     * @return The single instance.
     */
    public synchronized static SessionXMLPull getInstance() {
        if (factorySingleton == null) {
            factorySingleton = new SessionXMLPull();
        }
        return factorySingleton;
    }

    /**
     * @created 25/07/2004 15:47:11
     * @return Object the product of the factory.
     */
    public StringBuffer pullSession(Session session) {
        StringBuffer xmlBuffer = new StringBuffer();
        xmlBuffer.append("<?xml version='1.0' encoding='ISO-8859-1'?>");        
        return xmlBuffer.append(getPulledXMLSession(session).toString());
    }
    public static StringBuffer getPulledXMLSession(Session session){
        StringBuffer xmlBuffer = new StringBuffer();
        xmlBuffer.append("<session ");
        xmlBuffer.append("id='"+session.getID()+"' ");
        int type = (session instanceof Survey) ? 0 : 1; 
        xmlBuffer.append("type='"+type+"' ");
        xmlBuffer.append("opened='"+session.isOpened()+"' ");        
        xmlBuffer.append("title='"+session.getTitle()+"' ");        
        xmlBuffer.append("date='"+session.getTime().getTimeInMillis()+"'>");
        
        xmlBuffer.append("<questions>");
        for (Iterator iter = session.getQuestions(); iter.hasNext();) {
            Question question = (Question) iter.next();
            
            xmlBuffer.append("<question ");
            xmlBuffer.append("id='"+question.getID()+"' ");
            xmlBuffer.append("title='"+question.getTitle()+"' ");
            xmlBuffer.append("type='"+question.getQuestionType()+"'>");
            
            for (Iterator iterator = question.getQuestionItems(); iterator.hasNext();) {
                QuestionItem item = (QuestionItem) iterator.next();
                xmlBuffer.append("<question-item ");
                xmlBuffer.append("id='"+item.getID()+"' ");
                xmlBuffer.append("description='"+item.toString()+"'/>");
            }
            
            xmlBuffer.append("</question>");
        }
        xmlBuffer.append("</questions>");
        xmlBuffer.append("</session>");
         
        return xmlBuffer;
    }
    
    public String getKXml(Session session) throws XmlPullParserException, IllegalArgumentException, IllegalStateException, IOException{
        StringBuffer sb = new StringBuffer();

        // TODO: Download XMLPull Parser examples on the web to fix the problem org.xmlpull.v1.XmlPullParserException: resource not found: /META-INF/services/org.xmlpull.v1.XmlPullParserFactory make sure that parser implementing XmlPull API is available;
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance("org.xmlpull.v1.XmlPullParser", this.getClass());

        XmlSerializer serializer = factory.newSerializer();
        System.out.println("serializer implementation class is "+serializer.getClass());

        boolean addNewLine = false;      
        
        serializer.setOutput(new PrintWriter( System.out ));        
        
        // first write XML declaration
        serializer.startDocument(null, null);
        // add some empty lines before first start tag
        serializer.ignorableWhitespace("\n");
        // if prefix is not set serializer will generate automatically prefix
        // we overwrite this mechanism and manually namespace to be default (empty prefix)
        serializer.setPrefix("", NAMESPACE);
        
        serializer.startTag(NAMESPACE, "session");
        serializer.attribute("id","value","dontknow");
        return serializer.toString();
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(SessionXMLPull.getInstance().getKXml(MassFacade.getInstance().getCurrentOpenedSurvey()));
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PersistentObjectNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
