/*
 * @created 03/04/2004 23:37:12
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.persistence.memory;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * @created 03/04/2004 23:37:12
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class TestXMLPull {

    public static void main (String args[])
    throws XmlPullParserException, IOException
{
    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    factory.setNamespaceAware(true);
    XmlPullParser xpp = factory.newPullParser();

    xpp.setInput( new StringReader ( "<foo>Hello World!</foo>" ) );
    int eventType = xpp.getEventType();
    while (eventType != xpp.END_DOCUMENT) {
     if(eventType == xpp.START_DOCUMENT) {
         System.out.println("Start document");
     } else if(eventType == xpp.END_DOCUMENT) {
         System.out.println("End document");
     } else if(eventType == xpp.START_TAG) {
         System.out.println("Start tag "+xpp.getName());
     } else if(eventType == xpp.END_TAG) {
         System.out.println("End tag "+xpp.getName());
     } else if(eventType == xpp.TEXT) {
         System.out.println("Text "+xpp.getText());
     }
     eventType = xpp.next();
    }
}

}
