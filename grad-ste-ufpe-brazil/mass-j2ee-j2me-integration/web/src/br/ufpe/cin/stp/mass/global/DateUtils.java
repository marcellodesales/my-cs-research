/*
 * Created on 07/06/2004 20:51:43
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 *
 * */
 
package br.ufpe.cin.stp.mass.global;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 07/06/2004 
 */
public abstract class DateUtils {

	//public static final String DATE_FORMAT = "EEEEEE, dd 'de' MMMMMM yyyy H:mm:ss";
	public static final String DATE_FORMAT_TEST_RESULT = "MM/dd/yyyy HH:mm:ss";

	/**
	 * @param time is a Date or GregorianCalendar instance.
	 * @return The formated given time according to DateUtils.DATE_FORMAT_TEST_RESULT. 
	 * 08/06/2004 12:48:49
	 */
	public static String getFormated(Calendar time){
		return getFormated(time.getTimeInMillis());		
	}
	
	/**
	 * 08/06/2004 16:36:08
	 * @param time The milliseconds numer representing the time.
	 * @return The formatted result using the default format defined on the constants.
	 */
	public static String getFormated(long time){
		Date date = new Date(time);
		return new SimpleDateFormat(DateUtils.DATE_FORMAT_TEST_RESULT).format(date);		
	}	
	
	/**
	 * @param time is an instance of Calendar with the time definition.
	 * @param format Is the format used to parse. EEEEEE, dd 'de' MMMMMM yyyy H:mm:ss 
	 * @return
	 * 08/06/2004 16:37:34
	 */
	public static String getFormated(Calendar time, String format){
		Date date = new Date(time.getTimeInMillis());
		return new SimpleDateFormat(format).format(date);		
	}
	
	/**
	 * @param date is an instance of Date with the time definition.
	 * @param format Is the format used to parse. EEEEEE, dd 'de' MMMMMM yyyy H:mm:ss 
	 * @return
	 * 08/06/2004 16:37:34
	 */
	public static String getFormated(Date date, String format){
		return new SimpleDateFormat(format).format(date);		
	}	
	
	/**
	 * @param start It's the start time
	 * @param end It's the end time
	 * @return The difference between them in the following format: HH:mm:ss according
	 * to the constants of this class.
	 * 08/06/2004 18:06:09
	 */
	public static String getElapsedTime(GregorianCalendar start, GregorianCalendar end){
		GregorianCalendar diff = new GregorianCalendar();
		
		long difference = (end.getTimeInMillis() - start.getTimeInMillis())/1000; //milliseconds->seconds
		long seconds =  difference % 60;
		diff.set(GregorianCalendar.SECOND,(int)seconds);
		
		difference  = (difference - seconds) / 60;
		long minutes =  difference % 60;
		diff.set(GregorianCalendar.MINUTE,(int)minutes);
		
		difference  = (difference - minutes) / 60;
		long hours   =  difference % 24;
		diff.set(GregorianCalendar.HOUR_OF_DAY,(int)hours);
		
		difference  = (difference - hours)   / 24;
		long days    =  difference % 7;
		diff.set(GregorianCalendar.DAY_OF_MONTH,(int)days);

		return getFormated(diff);	 
	}
}
