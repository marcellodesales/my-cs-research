/**
 * XMLResourceBundle.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw.tmxtranslator;

import java.util.*;
import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.apache.crimson.parser.Resolver;
import org.apache.crimson.tree.XmlDocument;

import org.xml.sax.SAXException;

public class XMLResourceBundle extends ResourceBundle {

  private Hashtable hashcontents = null;

  //--------------------------------------
  // constructor <- XML file, Lang name
  //--------------------------------------
  public XMLResourceBundle(String tmxfile, String language){
    convertTMXtoHash (tmxfile, language);
  }

  //--------------------------------------
  // TMX to Hashtable conversion
  //--------------------------------------
  public void convertTMXtoHash (String xmlfile, String language){

    File file = null;
    InputSource input = null;
    Document document = null;

    //Create Document with Sun's parser
    try{
		file = new File(xmlfile);
		input = Resolver.createInputSource(file);
	  	//input = Resolver.createInputSource(file.toURL(), false);
	  	document = XmlDocument.createXmlDocument(input, false);
		
	} catch (SAXException e) {
		System.out.println(e.getMessage());
		
	} catch (IOException e){
		System.out.println(e.getMessage());
	}

    //Make a list of Term Units & count the number of itmes
    NodeList listOfTermUnits = document.getElementsByTagName("tu");
    int numberOfTermUnits = listOfTermUnits.getLength();

    // locals
    String temp_key = null;
    String temp_value = null;
    NamedNodeMap temp_list = null;
    Attr temp_attr = null;
    NodeList listOfTUVs = null;
    NodeList listOfSEG = null;
    Element SEGElements = null;
    int numberOfTUVs = 0;

    //set hash size
    hashcontents = new Hashtable(numberOfTermUnits);

    for (int i = 0; i < numberOfTermUnits; i ++){

        //(1) -- set a key
        temp_list = listOfTermUnits.item(i).getAttributes();
        temp_attr = (Attr)temp_list.item(0);
        temp_key = temp_attr.getValue();

        //(2) -- get a value

        //--Make a TUV list => "listOfTUVs"
        Node TUVs = listOfTermUnits.item(i);
        if (TUVs.getNodeType() == Node.ELEMENT_NODE){
          Element TUVElements = (Element)TUVs;

          listOfTUVs = TUVElements.getElementsByTagName("tuv");
          numberOfTUVs = listOfTUVs.getLength();
        }

        //-- Check each TUV. If it's a specified lang, then get a SEG value
        for (int j = 0; j < numberOfTUVs; j++)
        {
          temp_list = listOfTUVs.item(j).getAttributes();
          temp_attr = (Attr)temp_list.item(0);
          if (temp_attr.getValue().equals(language)){
              //-- Get a SEG value
              SEGElements = (Element)listOfTUVs.item(j);
              listOfSEG = SEGElements.getElementsByTagName("seg");
              temp_value = listOfSEG.item(0).getFirstChild().getNodeValue();
          }
        }

        //-- Populate hashtable
        if (temp_key != null && temp_value != null)
          hashcontents.put(temp_key, temp_value);
        
    }//for loop


  }//convert

  //--------------------------------------
  //  handleGetObject
  //--------------------------------------
  public final Object handleGetObject (String key) throws MissingResourceException {
    return hashcontents.get(key);
  }

  //--------------------------------------
  //Define getKeys method
  //--------------------------------------
  public Enumeration getKeys(){
    // Not inplemented here
    // See ListResourceBundle.java
    return null;
  }
	
	public static void main(String[] args){
		XMLResourceBundle xmlrb = new XMLResourceBundle("c:/desenv/graw/translation/sample.tmx","BR");
		String butaotrad = (String)xmlrb.handleGetObject("Button_b");
		System.out.println(butaotrad);
	}
}

