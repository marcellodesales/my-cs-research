import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.io.*;
import javax.microedition.io.*;

import org.ksoap.*;
import org.ksoap.http.*;
import org.kxml.*;
import org.kxml.io.*;
import org.kxml.parser.*;

public class CashConv extends MIDlet implements CommandListener {

    static final String serviceNamespace = "http://www.shinkatech.com/CurrencyConverter/message/";

    Form mainForm = new Form ("Euro Convertor");
    TextField fromField = new TextField ("from :", "DEM", 3, TextField.ANY);
    TextField toField = new TextField ("to :", "FRF", 3, TextField.ANY);
    TextField amountField = new TextField ("amount :", "100", 5, TextField.NUMERIC);
    StringItem resultItem = new StringItem ("", "");
    Command getCommand = new Command ("Get", Command.SCREEN, 1);
    
    
    public CashConv () {
	mainForm.append (fromField);
	mainForm.append (toField);
	mainForm.append (amountField);
	mainForm.append (resultItem);
	mainForm.addCommand (getCommand);
	mainForm.setCommandListener (this);
    }
    
    public void startApp () {
	Display.getDisplay (this).setCurrent (mainForm);
    }

    public void pauseApp () {
    }

    public void destroyApp (boolean unconditional) {
    }  

    public void commandAction (Command c, Displayable d) {

	HttpTransport transport = new HttpTransport 
	    ("http://212.99.131.154:7300/", 
	     "\"http://www.shinkatech.com/CurrencyConverter/action/CurrencyConverter.calculateExchangeRate\"");
	//transport.debug = true;

	
	try {
	    // build request string

	    String from = fromField.getString ();
	    String to = toField.getString ();
	    String amount = amountField.getString ();
	    
	    SoapObject convert = new SoapObject 
		(serviceNamespace, "convert");
	    
	    convert.addProperty ("currency", fromField.getString ());
	    convert.addProperty ("amount", new Float (amountField.getString ()));
	    convert.addProperty ("toCurrency", toField.getString ());
	    
	    SoapObject request = new SoapObject 
		(serviceNamespace, "calculateExchangeRate");

	    request.addProperty ("convert", convert);

	    ClassMap classMap = new ClassMap();
	    classMap.prefixMap = new PrefixMap 
		(classMap.prefixMap, "m", 
		 "http://www.shinkatech.com/CurrencyConverter/message/");

	    //classMap.implicitTypes = true;

		 
	    transport.setClassMap (classMap);

	    String result = transport.call (request).toString ();
	    result = result.substring(0,result.indexOf(".")+3);

	    resultItem.setLabel ("amount :");
	    resultItem.setText (" "+result);
	}
	catch (Exception e) {
	    resultItem.setLabel ("Error:");
	    resultItem.setText (e.toString ());

	    //	    System.out.println (transport.responseDump);
	    //	    throw new RuntimeException (e.toString ());
	}
    }

}


