/*
 * @created 30/07/2004 20:43:15
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless;

import java.io.IOException;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import br.ufpe.cin.stp.mass.view.wireless.handler.Question;
import br.ufpe.cin.stp.mass.view.wireless.handler.QuestionItem;
import br.ufpe.cin.stp.mass.view.wireless.handler.Session;

/**
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 30/07/2004 20:43:15
 */
public abstract class SessionXMLHandler {
       
    public static Session parseSession(KXmlParser parser) throws XmlPullParserException, IOException{
		parser.nextTag(); //advances one tag
		parser.require(XmlPullParser.START_TAG, null, "session");        
        
        Session session = new Session(parser.getAttributeValue("","id"),parser.getAttributeValue("","title"),parser.getAttributeValue("","date"),parser.getAttributeValue("","type"), parser.getAttributeValue("","opened"));

		parser.nextTag(); //advances one tag
		parser.require(XmlPullParser.START_TAG, null, "questions");
		
		//parse each question
		while (parser.nextTag() != XmlPullParser.END_TAG)
		    session.addQuestion(parseQuestion(parser));
		
		parser.require(XmlPullParser.END_TAG, null, "questions");
		parser.next();

		parser.require(XmlPullParser.END_TAG, null, "session");  
		parser.next();
		parser.require(XmlPullParser.END_DOCUMENT, null, null);
        return session;
    }
    
    private static Question parseQuestion(KXmlParser parser) throws XmlPullParserException, IOException{
		parser.require(XmlPullParser.START_TAG,null,"question");
        Question question = new Question(parser.getAttributeValue("","id"),parser.getAttributeValue("","title"),parser.getAttributeValue("","type"));
        //parse each question
		while (parser.nextTag() != XmlPullParser.END_TAG)
		    question.addItem(parseQuestionItem(parser));     
		
		parser.require(XmlPullParser.END_TAG, null, "question");
		return question;
    }
    
    private static QuestionItem parseQuestionItem(KXmlParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG,null,"question-item");
        QuestionItem item = new QuestionItem(parser.getAttributeValue("","id"),parser.getAttributeValue("","description"));
        parser.nextText();
        return item;
    }
}
