/*
 * @created 23/09/2004 15:24:30
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

/**
 * @created 23/09/2004 15:24:30
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class LogTokenNotFoundException extends LogInterpreterException{
    
    private String token;
    /**
     * Creates an IllegalTokenException when a specified token is not found on a given line
     * @created 23/09/2004 15:26:09
     * @param message
     * @param token
     */
    public LogTokenNotFoundException(String message, String token, String logLine){
        super(message,logLine);
        this.token = token;
    }
    
    /**
     * @created 23/09/2004 15:26:03
     * @return the token that was not found.
     */
    public String getToken() {
        return this.token;
    }
    
    /* @created 28/10/2004 12:04:02
     * (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    public String getMessage(){
        return "[Token Not Found]: "+super.getMessage()+" [Needed Token]="+this.token;
    }
}
