/*
 * @created 07/07/2004 20:52:17
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.global.pattern.observer;


/**
 * This is the interface that indicates that any object is observer.
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 07/07/2004 20:52:17
 */
public interface Observer {
    
    /**
     * Checks the value of something.
     * @param x is the value to be checked.
     * @created 07/07/2004 20:54:23
     */
    public void check(int x);
}
