/*
 * @created 31/07/2004 17:44:31
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.model;

/**
 * The main Mass exception. Must be used to get every 
 * exception of the application.
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 31/07/2004 17:44:31
 */
public class MassException extends Exception{

    public MassException(String message){
        super(message);
    }
    
    public MassException(String message, Throwable cause){
        super(message,cause);
    }
    
    public MassException(Throwable cause){
        super(cause);
    }
}
