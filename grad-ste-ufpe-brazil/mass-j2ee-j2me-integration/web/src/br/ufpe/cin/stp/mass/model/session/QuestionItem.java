package br.ufpe.cin.stp.mass.model.session;

import br.ufpe.cin.stp.mass.persistence.Persistent;

/**
 * @author Marcello Sales Jr. masj2@cin.ufpe.br
 * @version 1.0
 * @created 24/07/2004 19:14:14
 */
public class QuestionItem extends Persistent {

	/**
	 * <code>title</code> is the title of the item.
	 */
	private String title;
	/**
	 * <code>isCorrect</code> defines if the QuestionItem is correct
	 * in the case of a Questionary, other than a Survey. When using
	 * Survey, this value is not used.
	 */
	private boolean isCorrect;
	
	/**
	 * Creates a Question Item with a give title.
	 * @param title
	 * @created 31/07/2004 18:36:23
	 */
	public QuestionItem(String title){
	    this.title = title;
	}
	
	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     * @created 25/07/2004 08:59:27
     */
    public String toString() {
        return this.title;
    }	
    /**
     * @return Returns the isCorrect.
     * @created 31/07/2004 18:36:17
     */
    public boolean isCorrect() {
        return this.isCorrect;
    }
    /**
     * @param isCorrect The isCorrect to set.
     * @created 31/07/2004 18:36:17
     */
    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}