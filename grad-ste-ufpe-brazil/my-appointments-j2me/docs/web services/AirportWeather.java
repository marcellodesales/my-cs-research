/* 
 * © 2001 Kyle Gabhart -- kyle.gabhart@enhydra.org
 */
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.io.*;
import javax.microedition.io.*;

import org.ksoap.*;
import org.ksoap.transport.*;
import org.kxml.*;
import org.kxml.io.*;
import org.kxml.parser.*;

/*
 * This midlet accesses the Airport Weather web service developed and hosted
 * by Cape Clear (http://www.capeclear.com). The details of the web service
 * can be found at: http://www.capescience.com/webservices/airportweather/index.html
 */
public class AirportWeather extends MIDlet implements CommandListener 
{	
	static final String serviceNamespace = "capeconnect:AirportWeather:com.capeclear.weatherstation.Station";
  static final String serviceUrl = "http://www.capescience.com/ccgw/GWXmlServlet";
  static final String soapAction = "capeconnect:AirportWeather:com.capeclear.weatherstation.Station";

	private List					airportMenu, servicesMenu;
	private String[]			airportCodes, airports, services;
	private int						currentAirport;
	private Display				display;
	private Alert					response;
	private Command				backCommand, exitCommand;
	private HttpTransport	transport;
	private SoapObject		request;
	private ClassMap			classMap;
    
  public AirportWeather() 
  {
 		//Initialize the User Interface
 		airportCodes = new String[] { "HECA", "EGLL", "KLAX", "KJFK", "YSSY", "RJTT" };
 		airports = new String[] { "Cairo", "London, Heathrow", "Los Angeles, Intl", "New York, JFK", "Sydney, Intl", "Tokyo, Intl" };
 		services = new String[] { "All", "Humidity", "Pressure", "Sky", "Temperature", "Visibility", "Wind" };    
    airportMenu = new List( "Select an airport", List.IMPLICIT, airports, null );
    servicesMenu = new List( "Select a service", List.IMPLICIT, services, null );
    response = new Alert( "Weather Status", null, null, AlertType.INFO);
  	response.setTimeout( Alert.FOREVER );
  
    //Add commands        
    backCommand = new Command( "Back", Command.BACK, 2 );
		exitCommand = new Command( "Exit", Command.EXIT, 1 );
		
    airportMenu.addCommand( exitCommand );
    airportMenu.setCommandListener( this );
    servicesMenu.addCommand( backCommand );
    servicesMenu.setCommandListener( this );
        
    //obtain a reference to the device's UI
    display = Display.getDisplay( this );
  }//end AirportWeather()
    
  public void startApp() 
  {
		display.setCurrent( airportMenu ); //display the list of airports
  }//end startApp()

  public void pauseApp () {
  }//end pauseApp()

  public void destroyApp( boolean unconditional ) 
  {
  	//clean up
  	airportMenu = null;
  	display = null;
  	exitCommand = null;
  	response = null;
  	servicesMenu = null;
  }//end destroyApp( boolean )  

	public void commandAction( Command com, Displayable dis ) 
	{	
		if ( dis == airportMenu && com == List.SELECT_COMMAND )
		{
			currentAirport = airportMenu.getSelectedIndex();
			servicesMenu.setTitle( airports[ currentAirport ] + " weather" );
			display.setCurrent( servicesMenu ); //display the list of services
		} 
		else if ( dis == servicesMenu && com == List.SELECT_COMMAND )
		{
			int choice = servicesMenu.getSelectedIndex();
			String result = "Some Error";
			switch( choice )
			{
				case 0:
						SoapObject objResult = (SoapObject) callService( currentAirport, "getSummary" ); //service call
						if ( objResult != null )
						{
							result = "The weather at " + objResult.getProperty(0) + " is " + 
								objResult.getProperty(3) + " with a " + objResult.getProperty(2) + 
								" sky, and wind " + objResult.getProperty(1) + 
								". The humidity is " + objResult.getProperty(4) + 
								", the presssure is " + objResult.getProperty(5) + 
								", and the visibility is " + objResult.getProperty(6) + ".";
						}//end if ( objResult != null )
						break;
				case 1:
						result = (String) callService( currentAirport, "getHumidity" ); //service call
						break;
				case 2:
						result = (String) callService( currentAirport, "getPressure" ); //service call
						break;
				case 3:
						result = (String) callService( currentAirport, "getSkyConditions" ); //service call
						break;
				case 4:
						result = (String) callService( currentAirport, "getTemperature" ); //service call
						break;
				case 5:
						result = (String) callService( currentAirport, "getOb" ); //service call
						break;
				case 6:
						result = (String) callService( currentAirport, "getWind" ); //service call
						break;
			}//end switch( servicesMenu.getSelectedIndex() );  		
			
			//display the result
			response.setString( result );
	    display.setCurrent( response );
		} 
		else if ( com == backCommand )
		{
			display.setCurrent( airportMenu );
		}
		else if ( com == exitCommand ) 
		{
    	destroyApp( true );
      notifyDestroyed();
    }//end if ( dis == airportMenu && com == List.SELECT_COMMAND )
	}//end commandAction( Command, Displayable )		
	
	private Object callService( int choice, String methodName )
	{	
		Object					result = null;
	
		try 
		{
			transport = new HttpTransport( serviceUrl, soapAction + "#" + methodName );
			transport.debug = true;
	    
	    classMap = new ClassMap();
	    classMap.prefixMap = new PrefixMap( classMap.prefixMap, "air", serviceNamespace );
	    transport.setClassMap( classMap );
	    
	    request = new SoapObject( serviceNamespace, methodName );
	    request.addProperty( "arg0", airportCodes[ choice ] );
	    result = transport.call( request );
		}
		catch( Exception e ) 
		{
	    e.printStackTrace();
	    System.out.println( "Request: \n" + transport.requestDump );
	    System.out.println( "Response: \n" + transport.responseDump );
	    result = null;
		}//end try/catch
		
		return result;
	}//end callService()
}//end class AirportWeather