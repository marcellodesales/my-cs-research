/*
 * @created 14/07/2004 10:29:27
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.imersion.pattern.structural.decorator;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 14/07/2004 10:29:27
 */
public class StaredString extends StringDecorator {

    /**
     * @param originalString
     * @created 14/07/2004 10:29:33
     */
    public StaredString(String originalString) {
        super(originalString);
    }
    
    
    /* (non-Javadoc)
     * @see br.ufpe.cin.imersion.pattern.structural.decorator.StringDecorator#toString()
     * @created 14/07/2004 10:30:31
     */
    public String toString() {
        // TODO Auto-generated method stub
        return "*** "+super.toString()+" ***";
    }
}
