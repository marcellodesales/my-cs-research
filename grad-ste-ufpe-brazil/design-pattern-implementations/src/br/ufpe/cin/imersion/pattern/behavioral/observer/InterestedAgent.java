/*
 * @created 07/07/2004 21:04:26
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.global.pattern.observer;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 07/07/2004 21:04:26
 */
public class InterestedAgent implements Observer {

    /* (non-Javadoc)
     * @see br.ufpe.cin.stp.global.pattern.Observer#check(int)
     * @created 07/07/2004 21:04:26
     */
    public void check(int x) {
        System.out.println(this+": I will send the information of "+x+" to the authorities.");
    }
}
