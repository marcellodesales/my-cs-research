package br.ufpe.cin.stp.appointment.view.midp;

import java.util.Date;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Screen;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.ufpe.cin.stp.appointment.model.ApplicationState;
import br.ufpe.cin.stp.appointment.model.AppointmentDate;
import br.ufpe.cin.stp.appointment.model.AppointmentFacade;
import br.ufpe.cin.stp.appointment.model.AppointmentItem;
import br.ufpe.cin.stp.appointment.view.midp.form.AddNewAppointmentForm;
import br.ufpe.cin.stp.appointment.view.midp.form.AppointmentForm;
import br.ufpe.cin.stp.appointment.view.midp.form.ChooseTypeForm;
import br.ufpe.cin.stp.appointment.view.midp.form.SplashScreenAlert;
import br.ufpe.cin.stp.appointment.view.midp.form.ViewAppointmentItemForm;
import br.ufpe.cin.stp.appointment.view.midp.form.ViewAppointmentListForm;

/**
 * The main application class
 * @author Marcello de Sales
 * @version 1.0
 * @created 04-jul-2004 14:27:49
 */
public class AppointmentMidlet extends MIDlet implements CommandListener{

    private AppointmentFacade facade;
    private final boolean showSplash = true;
    
    private AppointmentForm appMainForm;    
    private AddNewAppointmentForm addNewItemForm;
    private ViewAppointmentItemForm viewAppItemForm;
    private ViewAppointmentListForm viewAppListForm;
	/**
	 * The form to ask the user about the type of the appointment
	 */
	private ChooseTypeForm chooseTypeForm;

	public AppointmentMidlet(){
	    super();
	    this.facade = AppointmentFacade.getInstance();
	}
	
	private Screen generateAddNewItemForm(){
	    if (this.addNewItemForm == null){
	        this.addNewItemForm = new AddNewAppointmentForm();
	        this.addNewItemForm.setCommandListener(this);
	    }
	    this.addNewItemForm.setDescriptionField("");
	    this.addNewItemForm.getTypeChoice().setSelectedIndex(0,true);
	    this.addNewItemForm.setDurationField("");
	    this.addNewItemForm.setStartHourField("");
	    return this.addNewItemForm;
	}	
	
	/**
	 * (04/07/2004 14:40:03)
	 * @return the new ChooseTypeForm.
	 */
	private Screen generateChooseTypeForm(){
	    if (this.chooseTypeForm == null){
	        this.chooseTypeForm = new ChooseTypeForm();
	        this.chooseTypeForm.setCommandListener(this);
	    }
	    return this.chooseTypeForm;
	}
	
	/**
	 * @return the main form of the application.
	 * @created 04/07/2004 23:22:42
	 */
	private Screen generateMainForm(){
	    if (this.appMainForm == null){
	        this.appMainForm = new AppointmentForm();
	        this.appMainForm.setCommandListener(this);
	    }
	    return this.appMainForm;
	}		
	
	/**
	 * @created 05/07/2004 17:41:06
	 * @param appItem is an appointment item to be displayed.
	 * @param date is the date of the appointment.
	 * @return a new form to view an appointment item.
	 */
	private Screen generateViewAppointmentItemForm(AppointmentItem appItem, String date){
	    //if (this.viewAppItemForm == null){
	        this.viewAppItemForm = new ViewAppointmentItemForm(appItem,date);
	        this.viewAppItemForm.setCommandListener(this);
	    return this.viewAppItemForm;
	}			

	/**
	 * @param appDate
	 * @return a new form with a list of appointments according to a date.
	 * @created 05/07/2004 17:42:41
	 */
	private Screen generateViewAppointmentListForm(AppointmentItem[] appointmentItems){
	    if (this.viewAppListForm == null){
	        this.viewAppListForm = new ViewAppointmentListForm(appointmentItems);
	        this.viewAppListForm.setCommandListener(this);
	    }
	    return this.viewAppListForm;
	}	
	
    /* (non-Javadoc)
     * @see javax.microedition.midlet.MIDlet#startApp()
     * 04/07/2004 14:31:44
     */
    protected void startApp() throws MIDletStateChangeException {
        new SplashScreenAlert(Display.getDisplay(this),this.generateMainForm());    
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
        // TODO Auto-generated method stub
        
    }
    
    /**
     * Controls the flow of information of the viewing of an Appointment Item
     * @created 04/07/2004 21:18:49
     */
    private void processViewing(byte step){
        AppointmentItem[] apps = AppointmentFacade.getInstance().getAppointmentItems();
        switch (step) {
	        case 0:
	        	Display.getDisplay(this).setCurrent(this.generateViewAppointmentListForm(apps)); 
	            break;
	        case 1:
	            //AppointmentItem[] apps = AppointmentFacade.getInstance().getAppointmentItems();
	            Display.getDisplay(this).setCurrent(this.generateViewAppointmentListForm(apps));
	            break;
	        case 2:
	            int appIndex = this.viewAppListForm.getItemsChoice().getSelectedIndex();         
	            Display.getDisplay(this).setCurrent(this.generateViewAppointmentItemForm(apps[appIndex],new Date().toString()));
	        default:
	            break;
        }
          
    }
    
    /**
     * Process the flow of information of the addition of an Appointment Item
     * @created 04/07/2004 21:19:10
     */
    private void processAddition(byte step){
        if (step == 0)
            Display.getDisplay(this).setCurrent(this.generateAddNewItemForm());
        else 
        if (step == 1){
            Date date = this.addNewItemForm.getDateField();
            String type = this.addNewItemForm.getTypeChoice().getString(this.addNewItemForm.getTypeChoice().getSelectedIndex());
            String desc = this.addNewItemForm.getDescriptionFieldString();
            String hour = this.addNewItemForm.getStartHourFieldString();
            String dur = this.addNewItemForm.getDurationFieldString();
            
            AppointmentItem ai = AppointmentItem.createAppointmentItem(type,desc,Byte.parseByte(hour),Byte.parseByte(dur));
            
            this.facade.addAppointmentItem(ai,date);
            
            Display.getDisplay(this).setCurrent(this.generateViewAppointmentItemForm(ai,date.toString()));
        } else {
            Display.getDisplay(this).setCurrent(this.appMainForm);
        }
    }
    
    /**
     * Process the flow of information of Removal of an Appointment Item
     * @created 04/07/2004 21:19:26
     */
    private void processRemoval(){
        Display.getDisplay(this).setCurrent(this.generateChooseTypeForm());        
    }
    
    /**
     * Process the flow of information of Edition of an Appointment Item 
     * @created 04/07/2004 21:19:43
     */
    private void processEdition(){
        Display.getDisplay(this).setCurrent(this.generateChooseTypeForm());        
    }
    
    /* (non-Javadoc)
     * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
     * 04/07/2004 14:33:50
     */
    public void commandAction(Command command, Displayable displayable) {
        ApplicationState state = ApplicationState.getInstance();
        if (displayable == this.appMainForm){               
            if (this.appMainForm.selectedItemEquals(AppointmentForm.ADD)){
                state.processState(ApplicationState.ADDING_STATE);
                this.processAddition((byte)0);
            } else
            if (this.appMainForm.selectedItemEquals(AppointmentForm.VIEW)){
                state.processState(ApplicationState.VIEWING_STATE);
                this.processViewing((byte)0);
            } else
            if (this.appMainForm.selectedItemEquals(AppointmentForm.EDIT)){
                state.processState(ApplicationState.EDITING_STATE);
                this.processEdition();
            } else
            if (this.appMainForm.selectedItemEquals(AppointmentForm.REMOVE)){
                state.processState(ApplicationState.REMOVING_STATE);
                this.processRemoval();
            }                   
        
        }else
        
        if (displayable == this.chooseTypeForm){
            if (command == this.chooseTypeForm.getSelectCommand()){
                
                if (state.getState().equals(ApplicationState.VIEWING_STATE))
                    this.processViewing((byte)1);
                else
                if (state.getState().equals(ApplicationState.EDITING_STATE))
                    this.processEdition();
                else
                if (state.getState().equals(ApplicationState.REMOVING_STATE))
                    this.processRemoval();
                
            }else
            if (command == this.chooseTypeForm.getCancelCommand()){
                Display.getDisplay(this).setCurrent(this.appMainForm);
            }
        } else
            
        if (displayable == this.addNewItemForm){
            if (command == this.addNewItemForm.getSaveCommand()){
               
                this.processAddition((byte)1);
            }else
            if (command == this.addNewItemForm.getCancelCommand())
                this.processAddition((byte)2);
        }
        
        else
        if (displayable == this.viewAppItemForm){
            if (command == this.viewAppItemForm.getBackCommand()){
                if (state.getState().equals(ApplicationState.ADDING_STATE))
                    Display.getDisplay(this).setCurrent(this.appMainForm);
                else 
                if (state.getState().equals(ApplicationState.VIEWING_STATE)){
                    this.processViewing((byte)1);
                }
            } else
            if (command == this.viewAppItemForm.getEditCommand())
                System.out.println("Edit item after view...");
        }
        
        if (displayable == this.viewAppListForm){
            if (command == this.viewAppListForm.getCancelCommand()){
                System.out.println("Depends on the context...");
            }else
            if (command == this.viewAppListForm.getViewCommand()){
                this.processViewing((byte)2);
            }
                
        }
    }

}
