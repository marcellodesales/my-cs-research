/*
 * @created 31/07/2004 18:50:00
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.persistence;

/**
 * Defines an Exception when the Persistent Object was not found.
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 31/07/2004 18:50:00
 */
public class PersistentObjectNotFoundException extends PersistenceLayerException {

    /**
     * @param message
     * @created 31/07/2004 18:50:51
     */
    public PersistentObjectNotFoundException(String message){
        super(message);
    }
}
