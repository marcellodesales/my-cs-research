/*
 * @created 07/07/2004 21:03:26
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
 * @created 07/07/2004 21:03:26
 */
public class InterestedPerson implements Observer {

    /* (non-Javadoc)
     * @see br.ufpe.cin.stp.global.pattern.Observer#check(int)
     * @created 07/07/2004 21:03:26
     */
    public void check(int x) {
        if (x > 0){
            System.out.println(this+": X is greater than zero!");
        } else System.out.println(this+": X is smaller or iquals to zero!");
    }
}
