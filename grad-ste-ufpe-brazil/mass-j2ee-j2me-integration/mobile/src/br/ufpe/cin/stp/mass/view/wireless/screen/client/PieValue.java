/*
 * @created 08/08/2004 21:49:24
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.screen.client;

/**
 * @created 08/08/2004 21:49:24
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
class PieValue {
    
    /**
     * <code>value</code> is the value data.
     */
    int value;
    /**
     * <code>color</code> is the color value to be chosen in the rgb int format
     */
    int color;
    /**
     * <code>title</code> is the title of the slice
     */
    String title;
    /**
     * <code>percentage</code> is the percentage string representation of the slice
     */
    String percentage;
    /**
     * <code>i</code> defines value for the color chooser.
     */
    
    /**
     * Creates a new PieValue with a given title, the percentage value and the int value.
     * @created 08/08/2004 20:50:06
     * @param title
     * @param percentage
     * @param value
     */
    public PieValue(String title, String percentage, int value, int colorIndex) {
        this.value = value;
        this.color = getColor(colorIndex);
        this.title = title;
        this.percentage = percentage;
    }
    
    /**
     * @created 08/08/2004 23:27:19
     * @param i
     * @return the color hexadecimal value based on an index.
     */
    public static int getColor(int i){
        switch (i) {
        case 0:
            return 0x00ff0000;
        case 1:
            return 0x0000ff00;
        case 2:
            return 0x00ffff00;
        case 3:
            return 0x000000ff;
        case 4:
            return 0x00ff00ff;
        case 5:
            return 0xff00ff00;
        case 6:
            return 0xff00ffff;           
        default:
            return 0x000000ff;
        }       
    }
}
