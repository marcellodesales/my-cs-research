/*
 * Created on 02/06/2004 09:55:41
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log;

import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import br.ufpe.cin.stp.global.DateUtils;

/**
 * Instruction is the representation of an instruction of the log file.
 * There will be another Instruction types according to each type. However,
 * this class represents ordinary instructions like configuration and 
 * accesses to resources.
 * 
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 02/06/2004 
 */
public class Instruction {

	/**
	 * <code>content</code> is the whole line of the instruction
	 */
	private String content;
	/**
	 * <code>time</code> is the time described on the line.
	 */
	private GregorianCalendar time;
	
	/**
	 * 08/06/2004 02:04:16<BR>
	 * Constructs an Instruction representation.
	 * @param content is the whole line of the log file.
	 */
	public Instruction(String content){
		this.content = content.trim();
	}
	
	public void setTime(GregorianCalendar time){
	    this.time = time;
	}
	
	/**
	 * 02/06/2004 10:11:23<BR>
	 * @return the whole content line.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param string is the content of the Instruction.
	 * 02/06/2004 10:11:23
	 */
	public void setContent(String string) {
		content = string;
	}
	
	/**
	 * Sets the time according to the time and date.
	 * (08/06/2004 02:06:04)
	 * @param date on an appropriate format (dd-mm-yyyy)
	 * @param time on an appropriate format (hh:mm:ss)
	 */
	public void setTime(String date, String time){
		StringTokenizer dateToken = new StringTokenizer(date,"-");
		int day   = Integer.parseInt(dateToken.nextToken());
		int month = Integer.parseInt(dateToken.nextToken());
		int year  = Integer.parseInt(dateToken.nextToken());
		
		StringTokenizer timeToken = new StringTokenizer(time,":");
		int hour = Integer.parseInt(timeToken.nextToken());
		int min  = Integer.parseInt(timeToken.nextToken());
		int sec  = Integer.parseInt(timeToken.nextToken());
		
		this.time = new GregorianCalendar(year,month-1,day,hour,min,sec);		
	}	

	/**
	 * (08/06/2004 02:08:35)
	 * @return a formated representation of the time.
	 */
	public String getFormatedTime(){
		return (this.time != null)? DateUtils.getFormated(this.time): "";
	}
	
	/**
	 * (08/06/2004 02:08:55)
	 * @return The time representation.
	 */
	public GregorianCalendar getTime(){
		return this.time;
	}
	
	public static void main(String[] args) {
	    
	    long start = System.currentTimeMillis();
	    GregorianCalendar time = new GregorianCalendar(2004,9,3,17,47,40);
	    long end = System.currentTimeMillis();
	    System.out.println(end-start+" milliseconds to create a new calendar...");
	    System.out.println(DateUtils.getFormated(time));
	   
	    System.out.println();

	    long start1 = System.currentTimeMillis();
	    GregorianCalendar time1 = new GregorianCalendar(2004,9,3,17,47,40);
	    long end1 = System.currentTimeMillis();
	    System.out.println(end1-start1+" milliseconds to create a new calendar...");
	    System.out.println(DateUtils.getFormated(time1));
	   
	    System.out.println();
	    long start2 = System.currentTimeMillis();
	    GregorianCalendar time2 = new GregorianCalendar(2004,9,3,17,47,40);
	    long end2 = System.currentTimeMillis();
	    System.out.println(end2-start2+" milliseconds to create a new calendar...");
	    System.out.println(DateUtils.getFormated(time2));
	   
	    System.out.println();
	    
	    
	    long startClone = System.currentTimeMillis();
	    GregorianCalendar newTime = (GregorianCalendar)time.clone();
	    long endClone = System.currentTimeMillis();
	    System.out.println(endClone-startClone+" milliseconds to clone calendar...");
	    System.out.println(DateUtils.getFormated(newTime));
    }
}
