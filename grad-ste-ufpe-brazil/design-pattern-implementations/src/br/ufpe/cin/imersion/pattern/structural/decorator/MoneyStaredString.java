/*
 * @created 14/07/2004 10:38:38
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.imersion.pattern.structural.decorator;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 14/07/2004 10:38:38
 */
public class MoneyStaredString extends StaredString {

    /**
     * @param originalString
     * @created 14/07/2004 10:38:43
     */
    public MoneyStaredString(String originalString) {
        super(originalString);
        // TODO Auto-generated constructor stub
    }

    
    /* (non-Javadoc)
     * @see br.ufpe.cin.imersion.pattern.structural.decorator.StaredString#toString()
     * @created 14/07/2004 10:38:51
     */
    public String toString() {
        // TODO Auto-generated method stub
        return "$$$ "+super.toString()+" $$$";
    }
}
