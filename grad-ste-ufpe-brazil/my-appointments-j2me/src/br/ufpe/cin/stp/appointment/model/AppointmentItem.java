package br.ufpe.cin.stp.appointment.model;

/**
 * The appointment item is the wrapper of an appointment item. The item has a type,
 * description, start hour and the duration.
 * @author Marcello de Sales.
 * @version 1.0
 * @created 04-jul-2004 15:57:59
 */
public class AppointmentItem {

    public static final String MEETING = "Meeting";
    public static final String TASK = "Task";
    public static final String CALL = "Call";
    
	/**
	 * The types of the AppointmentItem is on this list.
	 */
	public static final String[] types = {MEETING,TASK,CALL};
	/**
	 * The identification of the type. In this case, the position of the types array.
	 */
	private String type;
	/**
	 * The description of the appointment.
	 */
	private String description;
	/**
	 * The value of the start time of the appointment.
	 */
	private byte startHour;
	/**
	 * The duration of the appointment.
	 */
	private byte duration;

	public AppointmentItem(String type, String description, byte startHour, byte duration){
	    this.type = type; 
	    this.description = description;
	    this.startHour = startHour;
	    this.duration = duration;
	}

	/**
	 * Creates a new Appointment Item
	 * @param type    The identification of the type accessed through constants. 
	 * @param description    The description of the appointment.
	 * @param startHour    The start Hour of the appointment.
	 * @param duration    The duration of the appointment.
	 * 
	 */
	public static AppointmentItem createAppointmentItem(String type, String description, byte startHour, byte duration){
	    if (startHour+duration > 24) throw new IllegalArgumentException("The duration can't overlap the our of the day.");
	    return new AppointmentItem(type,description,startHour,duration);
	}
    /**
     * @return Returns the description.
     * @created 05/07/2004 00:49:01
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * @return Returns the duration.
     * @created 05/07/2004 00:49:01
     */
    public byte getDuration() {
        return this.duration;
    }
    /**
     * @return Returns the startHour.
     * @created 05/07/2004 00:49:01
     */
    public byte getStartHour() {
        return this.startHour;
    }
    /**
     * @return Returns the type.
     * @created 05/07/2004 00:49:01
     */
    public String getType() {
        return this.type;
    }
    /**
     * @param description The description to set.
     * @created 05/07/2004 00:52:34
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @param duration The duration to set.
     * @created 05/07/2004 00:52:34
     */
    public void setDuration(byte duration) {
        this.duration = duration;
    }
    /**
     * @param startHour The startHour to set.
     * @created 05/07/2004 00:52:34
     */
    public void setStartHour(byte startHour) {
        this.startHour = startHour;
    }
    /**
     * @param type The type to set.
     * @created 05/07/2004 00:52:34
     */
    public void setType(String type) {
        this.type = type;
    }
}
