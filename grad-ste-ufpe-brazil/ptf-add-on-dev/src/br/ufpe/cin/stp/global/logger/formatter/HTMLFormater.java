/*
 * Created on 07/06/2004 02:42:25
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.logger.formatter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * It's the formater to create HTML log files.
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * 07/06/2004 
 * @since JDK1.4
 */
public class HTMLFormater extends Formatter {

	/* (non-Javadoc)
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 * 07/06/2004 14:13:36
	 */
    public String format(LogRecord record) {
        StringBuffer buf = new StringBuffer(1000);
	    // Bold any levels >= WARNING
	    String title = record.getSequenceNumber()+"-"+record.getLevel()+": "+record.getLoggerName();
	    
	    if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
	        buf.append("<b>");
	        buf.append("<font color=red>"+title+"</font>");
	        buf.append("</b>");
	    } else {
	        buf.append("<font color=blue>"+title+"</font>");
	    }
	    
		if (record.getThrown() != null) {
		    try {
		        StringWriter sw = new StringWriter();
		        PrintWriter pw = new PrintWriter(sw);
		        record.getThrown().printStackTrace(pw);
		        pw.close();
		        buf.append("<pre>"+sw.toString()+"</pre>");
		    } catch (Exception ex) {
		    }
		} else {
		    String message = (record.getThrown()!=null)?record.getMessage()+": ("+record.getThrown().getClass().getName()+") "+record.getThrown().getMessage() : record.getMessage();
		    buf.append("<BR><i>"+formatMessage(record)+"</i><BR>");
		}
	
	    String sourceMethod = record.getSourceMethodName().equals("<init>") ? "init" : record.getSourceMethodName();
	    buf.append("SourceClassName: "+record.getSourceClassName());
	    buf.append("<BR>SourceMethodName: "+sourceMethod);
	    buf.append("<BR>Moment: "+record.getMillis());   
	    buf.append("<BR><BR>");
	   
	    return buf.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.util.logging.Formatter#getHead(java.util.logging.Handler)
	 * 07/06/2004 14:13:46
	 */
	public String getHead(Handler h) {
	    return "<HTML><HEAD>"+(new Date())+"</HEAD><BODY><BR><BR>\n";
	}
	
	/* (non-Javadoc)
	 * @see java.util.logging.Formatter#getTail(java.util.logging.Handler)
	 * 07/06/2004 14:14:11
	 */
	public String getTail(Handler h) {
	    return "</BODY></HTML>\n";
	}
	
	public static void main(String[] args) {        
	    // Get the logger
	    Logger logger = Logger.getLogger("br.cin.stp.ptfaddon");
	    try {
	        // Create a file handler that uses the custom formatter
	        FileHandler fh = new FileHandler("log/mylog.html");
	        fh.setFormatter(new HTMLFormater());
	        logger.addHandler(fh);
	    } catch (IOException e) {
	    }
	    
	    // Log some messages
	    logger.setLevel(Level.ALL);
	    logger.severe("my severe message");
	    logger.info("my info message");
	}
}
