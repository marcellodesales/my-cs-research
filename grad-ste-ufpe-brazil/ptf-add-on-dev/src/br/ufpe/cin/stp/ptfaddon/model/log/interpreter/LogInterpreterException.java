/*
 * @created 28/10/2004 11:04:18
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

/**
 * @created 28/10/2004 11:04:18
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class LogInterpreterException extends Exception {

    private String logLine;
    /**
     * The main exception of the interpreter
     * @created 28/10/2004 11:04:41
     * @param message
     */
    public LogInterpreterException(String message, String logLine){
        super(message);
        this.logLine = logLine;
    }
    
    /**
     * @created 28/10/2004 11:32:00
     * @return the log line where the token was not found
     */
    public String getLogLine(){
        return this.logLine;
    }
}
