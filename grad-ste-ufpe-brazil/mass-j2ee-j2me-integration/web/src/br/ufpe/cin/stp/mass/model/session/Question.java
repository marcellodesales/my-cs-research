package br.ufpe.cin.stp.mass.model.session;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import br.ufpe.cin.stp.mass.persistence.Persistent;

/**
 * The question represents the question that may be with single or multiple choices.
 * The question is comprised of a set of QuestionItems.
 * @author Marcello Sales Jr.
 * @version 1.0
 * @created 24-jul-2004 18:18:44
 * @see br.ufpe.cin.stp.mass.model.session.QuestionItem
 */
public class Question extends Persistent{

	/**
	 * <code>MULTICHOICE</code> defines a multi choice question.
	 */
	public static final byte MULTICHOICE = 0;
	/**
	 * <code>SINGLECHOICE</code> defines a single choice question.
	 */
	public static final byte SINGLECHOICE = 1;
	/**
	 * <code>questionType</code> defines if the question is multichoice
	 * or single one through the MULTICHOICE or SINGLECHOICE static values.
	 */
	private byte questionType;
	/**
	 * <code>title</code> is the title of the question.
	 */
	private String title;
	/**
	 * <code>questionItems</code> defines the items of the question.
	 */
	public List questionItems;
	
	/**
	 * Creates a new Question with the give title and the type.
	 * @param title
	 * @param type
	 * @created 31/07/2004 18:33:25
	 */
	public Question(String title, byte type){
	    this.title = title;
	    this.questionItems = new Vector();
	    this.questionType = type;
	}
	
    /**
     * @return Returns the title.
     * @created 25/07/2004 16:27:11
     */
    public String getTitle() {
        return this.title;
    }	
	
    /**
     * @return Returns the questionType.
     * @created 25/07/2004 09:07:49
     */
    public byte getQuestionType() {
        return this.questionType;
    }
	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     * @created 25/07/2004 08:59:27
     */
    public String toString() {
        return this.title;
    }
    
    /**
     * Adds a new QuestionItem on the Question.
     * @param item
     * @created 31/07/2004 18:38:42
     */
    public void addItem(QuestionItem item){
        this.questionItems.add(item);
    }
    
    /**
     * @return the Question Items.
     * @created 31/07/2004 18:39:02
     */
    public Iterator getQuestionItems(){
        return this.questionItems.iterator();
    }
}