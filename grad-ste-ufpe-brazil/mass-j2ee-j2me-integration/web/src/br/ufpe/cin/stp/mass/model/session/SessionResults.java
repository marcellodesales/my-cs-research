/*
 * @created 01/07/2004 19:53:58
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.model.session;

import java.text.DecimalFormat;
import java.util.Iterator;

import br.ufpe.cin.stp.mass.controller.AnswerSessionController;
import br.ufpe.cin.stp.mass.controller.CurrentOpenedSessionController;
import br.ufpe.cin.stp.mass.controller.SessionResultsController;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;

/**
 * @created 01/07/2004 19:53:58
 * SessionResults is a session wrapper to get the global results of the answers.
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class SessionResults {

    /**
     * <code>session</code> is the session to be used.
     */
    private Session session;
    
    /**
     * Creates a new SessionResults for a given session.
     * @created 01/07/2004 22:22:47
     * @param session
     */
    public SessionResults(Session session){
        this.session = session;
    }
    
    /**
     * @created 01/07/2004 22:23:57
     * @return Returns the session.
     */
    public Session getSession() {
        return this.session;
    }
    
    /**
     * @created 01/07/2004 20:03:40
     * @return the answers of a session.
     */
    public Iterator getAnswers(){
        return this.session.getAnswers();
    }
    
    /**
     * @created 01/07/2004 20:03:55
     * @param answer
     * @return the items answered to a given answer.
     */
    public String[] getItemsID(Answer answer){
        return answer.getItemsAnswered();
    }
    
	/**
	 * @created 01/07/2004 19:43:32
	 * @param itemID
	 * @return the number of occorrencies of a given itemID.
	 */
	public int getNumberOfOccorrencies(String itemID){
	    int number = 0;
	    answers:
	    for (Iterator iter = this.getAnswers(); iter.hasNext();) {
	        String[] itemsAnswered = ((Answer)iter.next()).getItemsAnswered();
	        for (int i = 0; i < itemsAnswered.length; i++) {
                if (itemsAnswered[i].equals(itemID)){
                    number++;
                    continue answers;
                }
            }
        }	    
	    return number;
	} 
    
    /**
     * @created 01/07/2004 20:04:43
     * @return the total number of answers.
     */
    public int getNumberOfAnswers(){
        return this.session.numberOfAnswers();
    }
    
	/**
	 * @created 25/07/2004 10:58:23
	 * @param item 
	 * @return the porcentage of a given item.
	 */
	public double getPorcentage(QuestionItem item){	    
	    return this.getPorcentage(item.getID());
	}
	
	/**
	 * @created 01/07/2004 22:32:00
	 * @param itemID
	 * @return the porcentage of a given item.
	 */
	public double getPorcentage(String itemID){
	    double occorrencies = this.getNumberOfOccorrencies(itemID);
	    double total = this.getNumberOfAnswers();
	    double div = occorrencies / total;
	    return div * 100;	    
	}
	
	public static void main(String[] args) {
        Session ss = CurrentOpenedSessionController.getInstance().getDefaultSurvey();
        
        QuestionItem a = (QuestionItem)((Question)ss.getQuestions().next()).getQuestionItems().next();
        
        String ans[] = {a.getID()};
        try {
            AnswerSessionController.getInstance().answerSession(ss.getID(),"mar",ans);
            
            SessionResults sr = SessionResultsController.getInstance().getSessionResults(ss.getID());
            
            System.out.println(sr.getNumberOfAnswers());
            System.out.println(sr.getNumberOfOccorrencies(a.getID()));
        } catch (PersistentObjectNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SessionClosedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	/**
	 * @created 08/08/2004 14:15:14
	 * @param itemID
	 * @return the percentage representation of a given itemID.
	 */
	public String getPercentageString(String itemID){
        DecimalFormat df = new DecimalFormat(); 
        df.setMaximumFractionDigits(2);  
        df.setMinimumFractionDigits(2); 
        df.setDecimalSeparatorAlwaysShown(true); 
        
        return df.format(this.getPorcentage(itemID))+"%";
	}
}
