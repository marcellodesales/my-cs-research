package br.ufpe.cin.stp.mass.view.wireless;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Screen;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParserException;

import br.ufpe.cin.stp.mass.view.wireless.handler.Session;
import br.ufpe.cin.stp.mass.view.wireless.handler.SessionResults;
import br.ufpe.cin.stp.mass.view.wireless.persistence.PersistenceLayer;
import br.ufpe.cin.stp.mass.view.wireless.screen.ScreenComponentFactory;
import br.ufpe.cin.stp.mass.view.wireless.screen.SplashScreenAlert;
import br.ufpe.cin.stp.mass.view.wireless.screen.client.AnswerSessionForm;
import br.ufpe.cin.stp.mass.view.wireless.screen.client.MainMenuForm;
import br.ufpe.cin.stp.mass.view.wireless.screen.client.SavedSessionResultsForm;
import br.ufpe.cin.stp.mass.view.wireless.screen.client.ViewCurrenttSessionResultsForm;
import br.ufpe.cin.stp.mass.view.wireless.screen.client.ViewSessionResultsCanvas;
import br.ufpe.cin.stp.mass.view.wireless.ResponseXMLHandler;

/**
 * The main application class
 * @author Marcello Sales Jr.
 * @version 1.0
 * @created 04-jul-2004 14:27:49
 */
public class MassMidlet extends MIDlet implements CommandListener{

    private final boolean showSplash = true;
    private final String currentSessionURL = "http://"+getAppProperty("mass-url")+":8080/mass/currentSession";
    private final String answerSessionURL = "http://"+getAppProperty("mass-url")+":8080/mass/answerSession";
    private final String currentSessionResultsURL = "http://"+getAppProperty("mass-url")+":8080/mass/sessionResults";
    
    private Session session;
    private MainMenuForm mainMenuForm;
    private AnswerSessionForm answerSessionForm;
    private ViewSessionResultsCanvas viewSessionResultsCanvas;
    private SavedSessionResultsForm savedSessionResultsForm;
    
    private RecordStore storeSession;
//    private RecordStore storeQuestion;
//    private RecordStore storeQuestionItem;
    
	public MassMidlet(){
	    super();
	    System.out.println("Init client");
	    new getCurrentSessionThread();
	}
	
	/**
	 * @created 03/08/2004 01:01:03
	 * @return the main menu of the application.
	 */
	private Screen generateMainMenuForm(){
	    if (this.mainMenuForm == null){
	        this.mainMenuForm = new MainMenuForm();
	        this.mainMenuForm.setCommandListener(this);
	    }
	    return this.mainMenuForm;
	}	
	
	/**
	 * @created 06/08/2004 13:17:16
	 * @return the graph canvas of a given SessionResults instance.
	 */
	private Canvas generateViewResultsCanvas(SessionResults sessionResults){
	    this.viewSessionResultsCanvas = new ViewSessionResultsCanvas(sessionResults);
	    this.viewSessionResultsCanvas.setCommandListener(this);
	    return this.viewSessionResultsCanvas;
	}		
	
	/**
	 * @created 03/08/2004 09:44:28
	 * @return the answer session form.
	 */
	private Screen generateAnswerSessionForm(){
	    if (this.answerSessionForm == null){
	        this.answerSessionForm = new AnswerSessionForm(this.session);
	        this.answerSessionForm.setCommandListener(this);
	    }
	    return this.answerSessionForm;
	}	
	
	/**
	 * @created 06/08/2004 17:08:49
	 * @param sessions
	 * @return the list of saved results.
	 */
	private Screen generateSaveSessionResultsForm(Session[] sessions){
	    this.savedSessionResultsForm = new SavedSessionResultsForm(sessions);
	    return this.savedSessionResultsForm;
	}	
	
	private void startRecordStores(){
        try {
 //           this.storeQuestion = PersistenceLayer.getDefaulRecordStore("question");
 //           this.storeQuestionItem = PersistenceLayer.getDefaulRecordStore("questionItem");
            this.storeSession = PersistenceLayer.getDefaulRecordStore("session");
        } catch (RecordStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	    
	}
	
    /* (non-Javadoc)
     * @see javax.microedition.midlet.MIDlet#startApp()
     * 04/07/2004 14:31:44
     */
    protected void startApp() throws MIDletStateChangeException {
        this.startRecordStores();
        if (this.showSplash)
            new SplashScreenAlert(Display.getDisplay(this),this.generateMainMenuForm(),false);
        else Display.getDisplay(this).setCurrent(this.generateMainMenuForm());
    }

    /* (non-Javadoc)
     * @see javax.microedition.midlet.MIDlet#pauseApp()
     * 04/07/2004 14:31:44
     */
    protected void pauseApp() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
     * 04/07/2004 14:31:44
     */
    protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
        try {
            this.storeSession.closeRecordStore();
        } catch (RecordStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Process the vote to a set of question items of the current session on the midlet.
     * @created 03/04/2004 22:17:26
     * @param itemID is the set of identifiers.
     */
    private void processVoteSession(String[] itemID, ResponseXMLHandler response){
        if (response == null)
            new answerSessionThread(itemID);
        else {
            Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createInfoAlert("Vote Result",response.getMessage(),3000),this.generateAnswerSessionForm());
        }
    }
    
    private void processQuitApp(){
        try {
            this.destroyApp(false);
        } catch (MIDletStateChangeException e) {
            e.printStackTrace();
        }
        this.notifyDestroyed();          
    }    
    
    private void processSavedSessionResults(){
        try {
            PersistenceLayer.saveObject(this.session.getRecordStream(),this.storeSession);
            Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createInfoAlert("Session Saved","The session was successfully saved!",3000),this.generateMainMenuForm());
        } catch (RecordStoreException e) {            
            e.printStackTrace();
            Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createErrorAlert("Session NOT Saved","An error occurred after trying to save the current session!",3000));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void showHttpConnectionFailure(){
        Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createErrorAlert("Internet Access Error","There's no Internet Connection Available! Therefore it's impossible to get needed information from the MASS server!",3000));
        this.processQuitApp();
    }    
    
    private void processSavedResults(){
        Display.getDisplay(this).setCurrent(this.generateSaveSessionResultsForm(PersistenceLayer.getSavedSessions(this.storeSession)));  
    }
    
    private void processViewCurrentSessionResults(SessionResults sessionResults){
        if (sessionResults == null)
            new getCurrentSessionResultsThread();
        else Display.getDisplay(this).setCurrent(this.generateViewResultsCanvas(sessionResults));
    }
    
    /* (non-Javadoc)
     * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
     * 04/07/2004 14:33:50
     */
    public void commandAction(Command command, Displayable displayable) {
        if (displayable == this.mainMenuForm){   
            
            if (command == this.mainMenuForm.getSelectCommand()){
	            if (this.mainMenuForm.selectedMenuItemEquals(MainMenuForm.ANSWER_SESSION)){
	                if (this.session == null)
	                    Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createErrorAlert("Session Unavailable","There's no active sessions by now!",3000),this.generateMainMenuForm());
	                else Display.getDisplay(this).setCurrent(this.generateAnswerSessionForm());
	            } else
	                
	            if (this.mainMenuForm.selectedMenuItemEquals(MainMenuForm.CURRENT_SESSION_RESULTS)){
	                if (this.session == null)
	                    Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createErrorAlert("Session Unavailable","There's no active sessions by now!",3000),this.generateMainMenuForm());
	                else this.processViewCurrentSessionResults(null);             
	            
	                //user goes to the saved results
	            } else this.processSavedResults();         
	                
	            //quit button was clicked.
            } else this.processQuitApp();
	    
        } else
	        
        if (displayable == this.answerSessionForm){
            if (command == this.answerSessionForm.getViewCommand()){
                this.processVoteSession(this.answerSessionForm.getSelectedQuestionItems(),null);
            }else
            if (command == this.answerSessionForm.getBackCommand()){
                Display.getDisplay(this).setCurrent(this.generateMainMenuForm());
            }
        
        } else //
        
	    if (displayable == this.viewSessionResultsCanvas){
	        if (command == this.viewSessionResultsCanvas.getSaveCommand()){
	            this.processSavedSessionResults();
	        }else
	        if (command == this.viewSessionResultsCanvas.getBackCommand()){
	            Display.getDisplay(this).setCurrent(this.generateMainMenuForm());
	        }      
	    
	    } else //
	        
	    if (displayable == this.savedSessionResultsForm){
	        if (command == this.savedSessionResultsForm.getViewSessionCommand()){
	            //show the results on a form
	        }else
	        if (command == this.savedSessionResultsForm.getBackCommand()){
	            Display.getDisplay(this).setCurrent(this.generateMainMenuForm());
	        }
	    } 	    
	}
    
    /**
     * Gets the current XML current session representation
     * @created 08/08/2004 23:08:34
     * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
     * @version 1.0
     */
    class getCurrentSessionThread extends Thread {

        public getCurrentSessionThread(){
            this.start();
        }
        
		public void run() {
		    HttpConnection httpConnection = null;
			try {
			    System.out.println(currentSessionURL);
		        //Open http connection
		        httpConnection = (HttpConnection) Connector.open(currentSessionURL);
		        httpConnection.setRequestProperty("User-Agent","Profile/MIDP-1.0 Configuration/CLDC-1.0");
		
				//Initilialize XML parser
				KXmlParser parser = new KXmlParser();
		
				parser.setInput(new InputStreamReader(httpConnection.openInputStream()));
				session = SessionXMLHandler.parseSession(parser);
				
				// TODO: Needs improvement on how to show error.
			}catch (XmlPullParserException xppe) {
			    System.out.println("There's no Session available...");
			} catch (IOException e) {
			    System.out.println("MASS Http Server at http://"+ getAppProperty("mass-url") +"  is not reachable...");
			    showHttpConnectionFailure();
            }
			try {
			    httpConnection.close();
			} catch (Exception e) {
			    e.printStackTrace();
			}
		}			
	}
    
    /**
     * Answer the current session results sending a get information to the server.
     * @created 08/08/2004 23:08:10
     * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
     * @version 1.0
     */
    class answerSessionThread extends Thread {
        
        private String[] itemsID;
        public answerSessionThread(String[] itemsID){
            this.itemsID = itemsID;
            this.start();
        }
        
        public void run() {
            StringBuffer answerURL = new StringBuffer(answerSessionURL+"?sessionID="+session.getId()+"&senderID=Wireless");
            for (int i = 0; i < this.itemsID.length; i++) {
                answerURL.append("&itemID="+itemsID[i]);
            }
            System.out.println(answerURL.toString());
            HttpConnection httpConnection = null;
            try {
                httpConnection = (HttpConnection) Connector.open(answerURL.toString());
                httpConnection.setRequestProperty("User-Agent","Profile/MIDP-1.0 Configuration/CLDC-1.0");
                int response = httpConnection.getResponseCode();
                if (response != HttpConnection.HTTP_OK) {
                    System.out.println("ERROR: "+response);
                } else System.out.println("VOTE OK");   
                
//              Initilialize XML parser
    			KXmlParser parser = new KXmlParser();
    			parser.setInput(new InputStreamReader(httpConnection.openInputStream()));
                
    			//return to continue process the vote and show the results.
    			processVoteSession(null,new ResponseXMLHandler(parser));
    			
            } catch (Exception ioe) {
                System.out.println("MASS Http Server at http://"+ getAppProperty("mass-url") +" is not reachable...");
            }
            try {
                httpConnection.close();
            } catch (Exception e) {
              	e.printStackTrace();
            }
        }
    }
    
    /**
     * Gets the current Session Results XML result from the server.
     * @created 08/08/2004 23:07:43
     * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
     * @version 1.0
     */
    class getCurrentSessionResultsThread extends Thread {

        public getCurrentSessionResultsThread(){
            this.start();
        }
        
		public void run() {
		    HttpConnection httpConnection = null;
			try {
			    System.out.println(currentSessionResultsURL);
		        //Open http connection
		        httpConnection = (HttpConnection) Connector.open(currentSessionResultsURL);
		        httpConnection.setRequestProperty("User-Agent","Profile/MIDP-1.0 Configuration/CLDC-1.0");
		
				//Initilialize XML parser
				KXmlParser parser = new KXmlParser();
		
				parser.setInput(new InputStreamReader(httpConnection.openInputStream()));
				SessionResults sr = SessionResultsXMLHandler.parseSessionResults(session,parser);
				processViewCurrentSessionResults(sr);
				
				// TODO: Needs improvement on how to show error.
			}catch (XmlPullParserException xppe) {
			    System.out.println("There's no Session available...");
			} catch (IOException e) {
			    System.out.println("MASS Http Server at http://"+ getAppProperty("mass-url") +"  is not reachable...");
			    showHttpConnectionFailure();
            }
			try {
			    httpConnection.close();
			} catch (Exception e) {
			    e.printStackTrace();
			}
		}			
	}    
}    