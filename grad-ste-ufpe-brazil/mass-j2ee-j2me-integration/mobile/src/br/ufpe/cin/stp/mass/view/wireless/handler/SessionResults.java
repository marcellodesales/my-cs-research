/*
 * @created 06/08/2004 01:14:01
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.handler;

import java.util.Vector;

/**
 * @created 06/08/2004 01:14:01
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class SessionResults {
    
    private Session session;
    
    private Vector voteItems;
    /**
     * @created 08/08/2004 14:27:25
     * @param items the items of the results
     * @param votes
     */
    public SessionResults(Session session){
        this.session = session;
        this.voteItems = new Vector();
    }
    
    public boolean isSessionOpened(){
        return this.session.isOpened();
    }
    
    /**
     * @created 08/08/2004 21:41:44
     * @return the title of the question of the survey.
     */
    public String getQuestionTitle(){
        //TODO : needs to improve it to handle all the questions in case of a questionary.
        return this.session.getQuestions()[0].getTitle();    
    }
    
    /**
     * @created 08/08/2004 21:46:01
     * @return the vote items of the session result.
     */
    public VoteItem[] getVoteItems(){
        VoteItem[] voteItems = new VoteItem[this.voteItems.size()];
        int tam = voteItems.length;
        for (int i = 0; i < tam; i++) {
            voteItems[i] = (VoteItem)this.voteItems.elementAt(i);
        }
        return voteItems;
    }
    
    /**
     * Adds a vote item to the results.
     * @created 08/08/2004 21:28:32
     * @param voteItem 
     */
    public void addVoteItem(VoteItem voteItem){
        this.voteItems.addElement(voteItem);
    }
}
