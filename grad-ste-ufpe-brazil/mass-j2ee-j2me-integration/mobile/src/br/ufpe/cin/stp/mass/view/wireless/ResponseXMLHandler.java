/*
 * @created 03/08/2004 12:12:55
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.mass.view.wireless;

import java.io.IOException;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * @created 03/08/2004 12:12:55
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class ResponseXMLHandler {

    private boolean actionOk;
    private String message;
    
    public ResponseXMLHandler(KXmlParser parser) throws XmlPullParserException, IOException{
        
/* In any of the two ways work...  
 * int eventType = parser.getEventType();
        while ((eventType = parser.next()) != KXmlParser.END_DOCUMENT) {
	         if(eventType == KXmlParser.START_TAG) {
	            
	             if (parser.getName().equals("response"))
	                 this.actionOk = parser.getAttributeValue("","actionOk").equals("true");
	             else
	             if (parser.getName().equals("message"))
	                 this.message = parser.nextText();
	         }

        }*/
		parser.nextTag(); //advances one tag
		parser.require(XmlPullParser.START_TAG, null, "response");      
		this.actionOk = parser.getAttributeValue("","actionOk").equals("true");
		parser.nextTag(); //advances one tag
		parser.require(XmlPullParser.START_TAG, null, "message");
		this.message = parser.nextText();
		parser.nextTag();
		parser.require(XmlPullParser.END_TAG, null, "response");
		
    }	
    /**
     * @created 03/08/2004 12:16:59
     * @return Returns the actionOk.
     */
    public boolean isActionOk() {
        return this.actionOk;
    }
    /**
     * @created 03/08/2004 12:16:59
     * @return Returns the message.
     */
    public String getMessage() {
        return this.message;
    }
}
