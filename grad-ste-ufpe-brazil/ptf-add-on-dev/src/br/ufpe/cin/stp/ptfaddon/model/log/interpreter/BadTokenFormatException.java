/*
 * @created 28/10/2004 10:22:39
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

/**
 * @created 28/10/2004 10:22:39
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class BadTokenFormatException extends LogInterpreterException{

    private String badToken;
    private String expectedToken;
    
    /**
     * Creates an exception indicating that a bad format was found on a given token
     * @created 28/10/2004 11:08:07
     * @param message
     * @param badToken
     * @param expectedToken
     */
    public BadTokenFormatException(String message, String badToken, String expectedToken, String logLine){
        super(message,logLine);
        this.badToken = badToken;
        this.expectedToken = expectedToken;
    }
    /**
     * @created 28/10/2004 11:33:14
     * @return Returns the badToken.
     */
    public String getBadToken() {
        return this.badToken;
    }
    /**
     * @created 28/10/2004 11:33:14
     * @return Returns the expectedToken.
     */
    public String getExpectedToken() {
        return this.expectedToken;
    }
    
    /* @created 28/10/2004 12:03:21
     * (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    public String getMessage(){
        return "[Bad Token]: "+super.getMessage()+" [Found Token]="+this.badToken+" | [Expected Token]="+this.expectedToken;
    }
}
