/*
 * @created 14/07/2004 10:34:36
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
 * @created 14/07/2004 10:34:36
 */
public class MoneyString extends StringDecorator {

    /**
     * @param originalString
     * @created 14/07/2004 10:34:40
     */
    public MoneyString(String originalString) {
        super(originalString);
    }
    
    /* (non-Javadoc)
     * @see br.ufpe.cin.imersion.pattern.structural.decorator.StringDecorator#toString()
     * @created 14/07/2004 10:34:47
     */
    public String toString() {
        return "$$$ "+super.toString()+" $$$";
    }
}
