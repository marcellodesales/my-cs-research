package br.ufpe.cin.stp.appointment.model;

import java.util.Date;
import java.util.Vector;

/**
 * The appointment date is responsible to organize the appointments according to a
 * given date.
 * @author Marcello de Sales 
 * @version 1.0
 * @created 04-jul-2004 15:58:12
 */
public class AppointmentDate {

    /**
     * @return Returns the date.
     * @created 05/07/2004 17:15:14
     */
    public Date getDate() {
        return this.date;
    }
	/**
	 * The date of the appointment
	 */
	private Date date;
	/**
	 * The list of appointment Items
	 */
	private Vector appointmentItems;
	public AppointmentFacade m_AppointmentManager;

	public AppointmentDate(Date date){
	    this.date = date;
	    this.appointmentItems = new Vector();
	}

	/**
	 * Adds a new AppointmentItem to the list.
	 * @param appointmentItem    The item to be added to the list.
	 * 
	 */
	public void add(AppointmentItem appointmentItem) throws IllegalArgumentException{
	    if (appointmentItem.getStartHour()+appointmentItem.getDuration() > 24){
	        throw new IllegalArgumentException("Appointment.Duration overlaps to other day!");
	    }
	    this.appointmentItems.addElement(appointmentItem);
	}

    /**
     * @return Returns the appointmentItems.
     * @created 05/07/2004 17:16:38
     */
    public Vector getAppointmentItems() {
        return this.appointmentItems;
    }
}
