package br.ufpe.cin.stp.mass.model.session;

/**
 * @author Marcello Sales Jr.
 * @version 1.0
 * @created 24-jul-2004 18:18:44
 */
public class Survey extends AbstractSession {

	public Survey(String title){
	    super(title);
	}
	
	/* 
	 * The  has just only one question. Therefore,
	 * the answer will be just unique.
	 * (non-Javadoc)
     * @see br.ufpe.cin.stp.mass.model.AbstractSession#addAnswer(br.ufpe.cin.stp.mass.model.Answer)
     */
    public void addAnswer(Answer answer) {
        this.answers.add(0,answer);
    }
    
    /* (non-Javadoc)
     * @see br.ufpe.cin.stp.mass.model.AbstractSession#addQuestion(br.ufpe.cin.stp.mass.model.Question)
     * @created 25/07/2004 09:15:05
     */
    public void addQuestion(Question question) {
        this.questions.add(0,question);
    }
}