/*
 * @created 14/07/2004 10:19:30
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.imersion.pattern.structural.decorator;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 14/07/2004 10:19:30
 */
public abstract class StringDecorator {

    private String originalString;
    
    public StringDecorator(String originalString){
        this.originalString = originalString;
    }
    
    public String getOriginal(){
        return this.originalString;
    }
    
    /* (non-Javadoc)
     * The toString method is the decoration of each new StringDecorator class.
     * @see java.lang.Object#toString()
     * @created 14/07/2004 10:20:49
     */
    public String toString() {
        // TODO Auto-generated method stub
        return this.originalString;
    }
    
    public static void main(String[] args) {
        String original = "Marcello";
        StringDecorator a = new StaredString(original);
        StringDecorator b = new MoneyString(original);
        StringDecorator c = new MoneyStaredString(original);
        System.out.println(a.toString());
        System.out.println(a.getOriginal());
        System.out.println();
        System.out.println(b.toString());
        System.out.println(b.getOriginal());
        System.out.println();
        System.out.println(c.toString());
        System.out.println(c.getOriginal());          
    }
}
