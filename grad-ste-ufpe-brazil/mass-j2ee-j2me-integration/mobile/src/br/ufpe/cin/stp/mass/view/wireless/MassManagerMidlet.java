/*
 * @created 04/04/2004 00:58:22
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Screen;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParserException;

import br.ufpe.cin.stp.mass.view.wireless.handler.Session;
import br.ufpe.cin.stp.mass.view.wireless.screen.ScreenComponentFactory;
import br.ufpe.cin.stp.mass.view.wireless.screen.SplashScreenAlert;
import br.ufpe.cin.stp.mass.view.wireless.screen.manager.CloseCurrentSessionForm;
import br.ufpe.cin.stp.mass.view.wireless.screen.manager.CreateNewSessionForm;
import br.ufpe.cin.stp.mass.view.wireless.screen.manager.MainManagerMenuList;
import br.ufpe.cin.stp.mass.view.wireless.screen.manager.NewQuestionItemForm;
import br.ufpe.cin.stp.mass.view.wireless.screen.manager.QuestionItemOptionsList;
import br.ufpe.cin.stp.mass.view.wireless.screen.manager.QuestionItemsList;
import br.ufpe.cin.stp.mass.view.wireless.SessionXMLHandler;
/**
 * @created 04/04/2004 00:58:22
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class MassManagerMidlet extends MIDlet implements CommandListener{

    private boolean showSplash = true;
    private Session session;
    private final String createSessionURL = "http://"+getAppProperty("mass-url")+":8080/mass/createNewSession";
    private final String currentSessionURL = "http://"+getAppProperty("mass-url")+":8080/mass/currentSession";
    
/*    private boolean httpOK;
    private HttpConnectionVerifier httpCheck = new HttpConnectionVerifier(this);
    Timer timer = new Timer();
*/    
    private MainManagerMenuList mainMenuList;
    private CreateNewSessionForm createNewSessionForm;
    private QuestionItemsList questionItemsList;
    private QuestionItemOptionsList questionItemOptionsList;
    private NewQuestionItemForm newQuestionItem;
//  private SaveNewSessionForm closeCurrentSessionForm;
    private CloseCurrentSessionForm closeCurrentSessionForm;

    private String[] currentQuestionItems;
    
    public MassManagerMidlet(){
        System.out.println("Init Manager");
        this.currentQuestionItems = new String[]{" New..."};
        new getCurrentSessionThread();
        //this.timer.schedule(this.httpCheck, 1000);
    }
    
	/**
	 * @created 03/08/2004 01:01:03
	 * @return the main menu of the application.
	 */
	private Screen generateMainManagerMenuForm(){
	    if (this.mainMenuList == null){
	        this.mainMenuList = new MainManagerMenuList();
	        this.mainMenuList.setCommandListener(this);
	    }
	    return this.mainMenuList;
	}		

	/**
	 * @created 04/04/2004 08:27:18
	 * @return the createNewSessionForm instance
	 */
	private Screen generateCreateNewSessionForm(){
	    if (this.createNewSessionForm == null){
	        this.createNewSessionForm = new CreateNewSessionForm();
	        this.createNewSessionForm.setCommandListener(this);
	    }
	    return this.createNewSessionForm;
	}	
	
	/**
	 * @created 04/04/2004 11:29:20
	 * @param values is the set of items of the list with the last option as "New...";
	 * @return the QuestionItems form
	 */
	private Screen generateQuestionItemsList(){
        this.questionItemsList = new QuestionItemsList(this.currentQuestionItems);
        this.questionItemsList.setCommandListener(this);
	    return this.questionItemsList;
	}		
	
	/**
	 * @created 08/08/2004 00:51:18
	 * @param selected
	 * @return is the screen to show the options about the items
	 */
	private Screen generateQuestionItemsOptionsList(int selected){
        this.questionItemOptionsList = new QuestionItemOptionsList(this.currentQuestionItems, selected);
        this.questionItemOptionsList.setCommandListener(this);
	    return this.questionItemOptionsList;
	}		
	
	/**
	 * @created 03/08/2004 01:01:03
	 * @return the main menu of the application.
	 */
	private Screen generateNewQuestionItemMenuForm(){
	    if (this.newQuestionItem == null){
	        this.newQuestionItem = new NewQuestionItemForm();
	        this.newQuestionItem.setCommandListener(this);
	    }
	    return this.newQuestionItem;
	}		
    
	/**
	 * @created 05/04/2004 09:04:32
	 * @return the form to close the current session.
	 */
	private Screen generateCloseCurrentSessionForm(){
	    if (this.closeCurrentSessionForm == null){
	        this.closeCurrentSessionForm = new CloseCurrentSessionForm(this.session);
	        this.closeCurrentSessionForm.setCommandListener(this);
	    }
	    return this.closeCurrentSessionForm;
	}		
	
    /* @created 04/04/2004 00:58:58
     * (non-Javadoc)
     * @see javax.microedition.midlet.MIDlet#startApp()
     */
    protected void startApp() throws MIDletStateChangeException {
        if (this.showSplash)
            new SplashScreenAlert(Display.getDisplay(this),this.generateMainManagerMenuForm(),true);
        else Display.getDisplay(this).setCurrent(this.generateMainManagerMenuForm());        
    }
    /* @created 04/04/2004 00:58:58
     * (non-Javadoc)
     * @see javax.microedition.midlet.MIDlet#pauseApp()
     */
    protected void pauseApp() {
        // TODO Auto-generated method stub
        
    }
    /* @created 04/04/2004 00:58:58
     * (non-Javadoc)
     * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
     */
    protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * @created 05/08/2004 23:17:33
     * @param params
     * @return a new parameter values with the white spaces changed to "%20".
     */
    private String getParamsWithURLSpaces(String params){
        final String space = "%20";
        StringBuffer newParams = new StringBuffer();
        for (int i = 0; i < params.length(); i++) {
            if (params.charAt(i) == ' ')
                newParams.append(space);
            else newParams.append(params.charAt(i));
        }
        return newParams.toString();
    }
    
    /**
     * Process the last step. Saves the Session on the server depending on if there exists
     * 2 or more option items...
     * @created 04/04/2004 13:21:07
     * @param items the items to be saved. The last one will be desconsidered. "New..."
     * @param selectedSave
     */
    private void processSaveSession(boolean selectedSave){
        int itemsSize = this.currentQuestionItems.length;
        if (itemsSize >= 2){
            
            if (selectedSave){
                String surveyTitle = this.createNewSessionForm.getSessionTitleFieldString();
                String questionTiTle = this.createNewSessionForm.getQuestionTitleFieldString();
                StringBuffer params = new StringBuffer();
                params.append("?");
                params.append("surveyTitle="+surveyTitle);
                params.append("&questionTitle="+questionTiTle);
                for (int i = 0; i < itemsSize; i++) 
                    params.append("&items="+this.currentQuestionItems[i]);
                
                new createNewSessionThread(this.getParamsWithURLSpaces(params.toString()));
                
            } else Display.getDisplay(this).setCurrent(this.generateQuestionItemsList());                
            
        } else Display.getDisplay(this).setCurrent(this.generateQuestionItemsList());        
    }
    
    private void processCreateNewSessionFinalState(Session session){
        if (session != null){
            Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createInfoAlert("Session Saved!","The session "+session.getTitle()+" was sucessfully created on the server!",4000),this.generateMainManagerMenuForm());
        } else Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createErrorAlert("Session not Saved!","An error occurred and the session "+session.getTitle()+" was not saved!",4000),this.generateMainManagerMenuForm());
    }
    
    private void processQuitApp(){
        try {
            this.destroyApp(false);
        } catch (MIDletStateChangeException e) {
            e.printStackTrace();
        }
        this.notifyDestroyed();          
    }
    
    private void processRemoveItem(int selectedItemPosition){
//      if there's only one item, then the user wants to remove the only created item.
        if (this.currentQuestionItems.length == 1) 
            this.currentQuestionItems[0] = " New...";
        else {
            //there are more than 1 item, so update the list
            String newCurrentItems[] = new String[this.currentQuestionItems.length-1];
            
            int correctIndex = 0;
            for (int i = 0; i < this.currentQuestionItems.length; i++) {
                if (i == selectedItemPosition)
                    continue;
                else {
                    newCurrentItems[correctIndex] = this.currentQuestionItems[i];
                    correctIndex++;
                }
            }
            this.currentQuestionItems = newCurrentItems;
        }
        Display.getDisplay(this).setCurrent(this.generateQuestionItemsList());
    }
    
    public boolean itemExists(String item){
        for (int i = 0; i < this.currentQuestionItems.length; i++) {
            if (currentQuestionItems[i].equals(item)){
                return true;
            }
        }
        return false;
    }
    
    private void processAddNewQuestionItem(String newItem){
        
        if (this.itemExists(newItem)){
            Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createErrorAlert("Question Item Error","The item "+newItem+" already exists!",3000),this.generateNewQuestionItemMenuForm());
        } else {
        // TODO: This method can be performed with the thrid one of this scope. clear and test
	        if (this.currentQuestionItems.length == 1){
	            if (this.currentQuestionItems[0].equals(" New..."))
	                this.currentQuestionItems[0] = newItem;
	            else {
	                String[] newQuestionItems = new String[2];
	                newQuestionItems[0] = this.currentQuestionItems[0];
	                newQuestionItems[1] = newItem;
	                this.currentQuestionItems = newQuestionItems;
	            }
	            
	        } else {
	            String[] newQuestionItems = new String[this.currentQuestionItems.length+1];
	            for (int i = 0; i < this.currentQuestionItems.length; i++) {
	                newQuestionItems[i] = this.currentQuestionItems[i];
	            }
	            newQuestionItems[newQuestionItems.length-1] = newItem;
	            this.currentQuestionItems = newQuestionItems;            
	        }
	        this.newQuestionItem.setItemFieldValue("");
	        Display.getDisplay(this).setCurrent(this.generateQuestionItemsList());
    	}
    }
    
    private void processCloseCurrentSession(){
        // TODO NEEDS TO IMPLEMENT THE CLOSE OF THE RESULT...
        Display.getDisplay(this).setCurrent(this.generateMainManagerMenuForm());
        System.out.println("NEEDS TO IMPLEMENT THE CLOSE OF THE RESULT...");
    }
    
    /* @created 04/04/2004 00:58:58
     * (non-Javadoc)
     * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
     */
    public void commandAction(Command command, Displayable screen) {
        if (screen == this.mainMenuList){ //
            if (command == this.mainMenuList.getSelectCommand()){
                //Create New Session
	            if (this.mainMenuList.selectedMenuItemEquals(this.mainMenuList.CREATE_SESSION))
	                if (this.session != null){
	                    Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createErrorAlert("Session Unavailable","There already exists an opened session!",3000),this.generateMainManagerMenuForm());
	                } else Display.getDisplay(this).setCurrent(this.generateCreateNewSessionForm());
	            
	            else //Close Current Session
	            if (this.mainMenuList.selectedMenuItemEquals(this.mainMenuList.CLOSE_SESSION_RESULTS)){
	                if (this.session == null)
	                    Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createErrorAlert("Session Unavailable","There's no active sessions by now!",3000),this.generateMainManagerMenuForm());
	                else 
	                if (!this.session.isOpened()){
	                    
	                } else Display.getDisplay(this).setCurrent(this.generateCloseCurrentSessionForm());
	            }
//	          Quit Command
            } else this.processQuitApp();
                
        } else 
            
        if (screen == this.createNewSessionForm){
            //show item options screen for the first time
            if (command == this.createNewSessionForm.getShowQuestionItemsCommand()){
                Display.getDisplay(this).setCurrent(this.generateQuestionItemsList());
//              user cancelled and goes to start menu.
            }else Display.getDisplay(this).setCurrent(this.generateMainManagerMenuForm());                 
                      
        } else
            
        if (screen == this.questionItemsList){                       
            //show question items options screen
            if (command == this.questionItemsList.getItemsOptionCommand()){
                Display.getDisplay(this).setCurrent(this.generateQuestionItemsOptionsList(this.questionItemsList.getSelectedIndex()));
//              user goes back to the new session form
            } else Display.getDisplay(this).setCurrent(this.generateCreateNewSessionForm());
                
        } else
            
        if (screen == this.questionItemOptionsList){
            //show options for the selected question item available on the list
            if (command == this.questionItemOptionsList.getSelectCommand()){
                
                //add a new item on the list
                if (this.questionItemOptionsList.selectedMenuItemEquals(this.questionItemOptionsList.ADD_ITEM))
                    Display.getDisplay(this).setCurrent(this.generateNewQuestionItemMenuForm());
                else //remove an item of the list
                if (this.questionItemOptionsList.selectedMenuItemEquals(this.questionItemOptionsList.REMOVE_ITEM)){
                    this.processRemoveItem(this.questionItemOptionsList.getItemSelected());
                } else { 
                    this.processSaveSession(true);
                }
//              user gave up of the options screen
            } else Display.getDisplay(this).setCurrent(this.generateQuestionItemsList()); 
                
        } else 
            
        if (screen == this.newQuestionItem){
            //add a new item to the currentQuestionItems
            if (command == this.newQuestionItem.getAddItemCommand()){
                this.processAddNewQuestionItem(this.newQuestionItem.getItemFieldString());
            } else Display.getDisplay(this).setCurrent(this.generateQuestionItemsList());
                
        } else
            
        if (screen == this.closeCurrentSessionForm){
//          send message to the server to close
            if (command == this.closeCurrentSessionForm.getCloseSessionCommand()){
                this.processCloseCurrentSession();
            } else Display.getDisplay(this).setCurrent(this.generateMainManagerMenuForm());
        }
    }
    
    private void showHttpConnectionFailure(){
        Display.getDisplay(this).setCurrent(ScreenComponentFactory.getInstance().createErrorAlert("Internet Access Error","There's no Internet Connection Available! Therefore it's impossible to get needed information from the MASS server!",3000));
        this.processQuitApp();
    }

    class createNewSessionThread extends Thread {
        
        private String params;
        
        public createNewSessionThread(String parameters){
            this.params = parameters;
            this.start();
        }
        
		public void run() {
		    HttpConnection httpConnection = null;
			try {
			    System.out.println(createSessionURL+this.params);
		        //Open http connection
		        httpConnection = (HttpConnection) Connector.open(createSessionURL+this.params);
		        httpConnection.setRequestProperty("User-Agent","Profile/MIDP-1.0 Configuration/CLDC-1.0");
                int response = httpConnection.getResponseCode();
                
                if (response != HttpConnection.HTTP_OK) {
                    System.out.println("ERROR: "+response);
                } else System.out.println("NEW SESSION CREATED!");  		        
		        
				//Initilialize XML parser
				KXmlParser parser = new KXmlParser();
		
				parser.setInput(new InputStreamReader(httpConnection.openInputStream()));
				session = SessionXMLHandler.parseSession(parser);
				
				processCreateNewSessionFinalState(session);
				// TODO: Needs improvement on how to show error.
				//if the session is not available, the xml generated is a result.
			}catch (XmlPullParserException xppe) {
			    xppe.printStackTrace();
			    System.out.println("There's no Session available...");
			} catch (IOException e) {
			    System.out.println("MASS Http Server is offline...");
			    showHttpConnectionFailure();
            }
			try {
			    httpConnection.close();
			} catch (Exception e) {
			    e.printStackTrace();
			    //showHttpConnectionFailure();
			}
		}	
	}      
    
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
		        //httpOK = true;
		        httpConnection.setRequestProperty("User-Agent","Profile/MIDP-1.0 Configuration/CLDC-1.0");
		        
				//Initilialize XML parser
				KXmlParser parser = new KXmlParser();
		
				parser.setInput(new InputStreamReader(httpConnection.openInputStream()));
				session = SessionXMLHandler.parseSession(parser);
				
				// TODO: Needs improvement on how to show error.
				//if the session is not available, the xml generated is a result.
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
			    //showHttpConnectionFailure();
			}
		}	
	}    
    
/*	class HttpConnectionVerifier extends TimerTask{

	    private MIDlet midlet;
	    
	    public HttpConnectionVerifier(MIDlet midlet){
	        this.midlet = midlet;
	    }
        /* @created 05/04/2004 10:31:15
         * (non-Javadoc)
         * @see java.util.TimerTask#run()
         */
/*        public void run() {
            // TODO Auto-generated method stub
            if(httpOK)
                showHttpConnectionFailure();
        }
	    
	}    
*/
}
