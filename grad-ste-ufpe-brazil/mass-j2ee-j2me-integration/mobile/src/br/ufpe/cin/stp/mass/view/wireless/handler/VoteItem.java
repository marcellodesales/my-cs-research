/*
 * @created 08/08/2004 21:24:41
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.handler;

/**
 * It's the wrapper of a given vote item from the xml result representation.
 * @created 08/08/2004 21:24:41
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class VoteItem {
    
    /**
     * <code>itemTitle</code> is the item title
     */
    private String itemTitle;
    /**
     * <code>numberVotes</code> is the number of votes for this item
     */
    private int numberVotes;
    /**
     * <code>percentage</code> is the string representation of the percentage of votes for this item.
     */
    private String percentage;
    
    public VoteItem(String itemTitle, String numberVotes, String percentage){
        this.itemTitle = itemTitle;
        this.numberVotes = Integer.parseInt(numberVotes);
        this.percentage = percentage;
    }
    
    
    /**
     * @created 08/08/2004 21:27:03
     * @return Returns the itemTitle.
     */
    public String getItemTitle() {
        return this.itemTitle;
    }
    /**
     * @created 08/08/2004 21:27:03
     * @return Returns the numberVotes.
     */
    public int getNumberVotes() {
        return this.numberVotes;
    }
    /**
     * @created 08/08/2004 21:27:03
     * @return Returns the percentage.
     */
    public String getPercentage() {
        return this.percentage;
    }
}
