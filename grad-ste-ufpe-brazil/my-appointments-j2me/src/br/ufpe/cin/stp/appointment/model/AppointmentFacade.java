package br.ufpe.cin.stp.appointment.model;

import java.util.Date;
import java.util.Vector;

/**
 * Manages the list of AppointmentLists instances.
 * @author Marcello de Sales 
 * @version 1.0
 * @created 04-jul-2004 15:57:50
 */
public class AppointmentFacade {
    
    public ApplicationState state;
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static AppointmentFacade singleton; 
    
	/**
	 * The list of the appointment dates available.
	 */
	private Vector appointmentDateList;
	private Vector registeredDate;

	public AppointmentFacade(){
	    this.state = ApplicationState.getInstance();
	    this.appointmentDateList = new Vector();
	    this.registeredDate = new Vector();
	  
	}
	
	/**
	 * (04/07/2004 16:12:51)
	 * @return The single instance.
	 */
	public synchronized static AppointmentFacade getInstance(){
	    if (singleton == null){
	        singleton = new AppointmentFacade();
	    }
	    return singleton;
	}

	/**
	 * Creates a new Appointment List.
	 * @param date    The date of the appointment list.
	 * 
	 */
	public void createAppointmentDate(Date date){
	    
	}

	/**
	 * Registers a date to a controller.
	 * @param date    The date to be registered.
	 * 
	 */
	private void registerDate(Date date){

	}
	
	private AppointmentDate getAppointmentDateFromColl(Date date){
	    int dateNum = this.appointmentDateList.size();
	    AppointmentDate appDate = null;
	    for (int i = 0; i < dateNum; i++) {
	        appDate = (AppointmentDate)this.appointmentDateList.elementAt(i);
            if (date == appDate.getDate()){
                break;
            }
        }	   
        return appDate;
	}

	public AppointmentDate getAppointmentDate(Date date){
	    int dateNum = this.registeredDate.size();
	    AppointmentDate appDate = null;
	    for (int i = 0; i < dateNum; i++) {
            Date da = (Date)this.registeredDate.elementAt(i);
            if (da == date){
                appDate = this.getAppointmentDateFromColl(date);
                break;
            }
        }
	    return appDate;
	}
	
	public AppointmentItem[] getAppointmentItems(){
	    Vector appsItems = new Vector();
	    int n = this.appointmentDateList.size();
	    
	    for (int i = 0; i < n; i++) {
            
	        AppointmentDate appDate = (AppointmentDate)this.appointmentDateList.elementAt(i);
	        Vector appItems = appDate.getAppointmentItems();
	        int j = appItems.size();

	        for (int k = 0; k < j; k++) {
	            AppointmentItem appItem = (AppointmentItem)appItems.elementAt(k);
	            appsItems.addElement(appItem);
	        }
        }
	    AppointmentItem[] itemsToGo = new AppointmentItem[appsItems.size()];
	    int cc = appsItems.size();
	    for (int i = 0; i < cc; i++) {
            itemsToGo[i] = (AppointmentItem)appsItems.elementAt(i);
        }
	    return itemsToGo;
	}
	
	/**
	 * Adds a new Appintment item according to a date.
	 * @param appointmentItem    The appointment item to be added.
	 * @param date
	 * 
	 */
	public void addAppointmentItem(AppointmentItem appointmentItem, Date date){
	    AppointmentDate appDate = this.getAppointmentDate(date);
	    if (appDate == null){
	        appDate = new AppointmentDate(date);
		    this.registeredDate.addElement(date);
	    }
	    appDate.add(appointmentItem);
	    this.appointmentDateList.addElement(appDate);
	}
}
