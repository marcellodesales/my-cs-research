import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.io.*;
import javax.microedition.io.*;

import org.ksoap.*;
import org.ksoap.http.*;


public class StockQuoteDemo extends MIDlet implements CommandListener {

    Form mainForm = new Form ("StockQuotes");
    TextField symbolField = new TextField ("Symbol", "IBM", 5, TextField.ANY);
    StringItem resultItem = new StringItem ("", "");
    Command getCommand = new Command ("Get", Command.SCREEN, 1);
    
    
    public StockQuoteDemo () {
	mainForm.append (symbolField);
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
	try {
	    // build request string

	    String symbol = symbolField.getString ();
	    resultItem.setLabel (symbol);

	    SoapObject rpc = new SoapObject 
		("urn:xmethods-delayed-quotes", "getQuote");

	    rpc.addProperty ("symbol", symbol);

	    resultItem.setText (""+new HttpTransport 
		("http://services.xmethods.net/soap",
		 "urn:xmethods-delayed-quotes#getQuote").call (rpc));

	}
	catch (Exception e) {
	    e.printStackTrace ();
	    resultItem.setLabel ("Error:");
	    resultItem.setText (e.toString ());
	}
    }

    /** for me4se */

    public static void main (String [] argv) {
	new StockQuoteDemo ().startApp ();
    }
}



