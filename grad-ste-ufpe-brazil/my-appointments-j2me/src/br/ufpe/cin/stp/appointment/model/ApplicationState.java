/*
 * @created 04/07/2004 22:38:27
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.appointment.model;

import java.util.Hashtable;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 04/07/2004 22:38:27
 */
public class ApplicationState {

    /**
     * <code>ADDING_STATE</code> is the adding state.
     */
    public static final String ADDING_STATE = "ADD";
    /**
     * <code>REMOVING_STATE</code> is the removing state.
     */
    public static final String REMOVING_STATE = "REM";
    /**
     * <code>VIEWING_STATE</code> is the view state.
     */
    public static final String VIEWING_STATE = "VIEW";
    /**
     * <code>EDITING_STATE</code> is the edit state.
     */
    public static final String EDITING_STATE = "EDIT";
    /**
     * <code>state</code> defines the state flow of the appication to determine
     * which activity is being performed. The semantics is determined on the 
     * processState and getState methods. 
     */
    private byte[] state = {0,0,0,0};        
    
    //private Hashtable states;
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static ApplicationState singleton;

    public ApplicationState() {
        /*this.states = new Hashtable(4);
         this.states.put(this.ADDING_STATE,new Boolean(false));
        this.states.put(this.EDITING_STATE,new Boolean(false));
        this.states.put(this.VIEWING_STATE,new Boolean(false));
        this.states.put(this.EDITING_STATE,new Boolean(false));
        */
    }

    /**
     * @created 04/07/2004 22:47:26
     * @return The single instance.
     */
    public synchronized static ApplicationState getInstance() {
        if (singleton == null) {
            singleton = new ApplicationState();
        }
        return singleton;
    }    

	/**
	 * The state processing of the application.
	 * @created 04/07/2004 22:33:16
	 * @param state is the state defined on the constants.
	 */
	public void processState(String state){
	    if (state.equals(ADDING_STATE)){
	        this.initializeStates();
	        this.state[0] = 1;
	    } else 
	    if (state.equals(VIEWING_STATE)){
	        this.initializeStates();
	        this.state[1] = 1;
	    } else
	    if (state.equals(EDITING_STATE)){
	        this.initializeStates();
        	this.state[2] = 1;
	    } else	     
		if (state.equals(REMOVING_STATE)){
		    this.initializeStates();
		    this.state[3] = 1;
		}		        
	}
	
	/**
	 * Initialize the state values.
	 * @created 04/07/2004 22:33:06
	 */
	private void initializeStates(){
	    for (int i = 0; i < this.state.length; i++) {
            this.state[i] = 0;
        }
	}
	
	/**
	 * The state of the application will be returned according to the constants
	 * defined on the class.
	 * @return the state of the application.
	 * @created 04/07/2004 22:29:37
	 */
	public String getState(){
	    String staten = "";
	    if (this.state[0] == 1)
	        staten = ADDING_STATE;
	    else
	    if (this.state[1] == 1)
	        staten = VIEWING_STATE;
	    else
	    if (this.state[2] == 1)
	        staten = EDITING_STATE;
	    else
	    if (this.state[3] == 1)
	        staten = REMOVING_STATE;
	    return staten;
	}	    
}
