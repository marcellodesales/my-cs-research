/*
 * @created 08/08/2004 12:56:03
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.screen.client;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;

import br.ufpe.cin.stp.mass.view.wireless.handler.SessionResults;
import br.ufpe.cin.stp.mass.view.wireless.handler.VoteItem;

/**
 * @created 08/08/2004 12:56:03
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class ViewSessionResultsCanvas extends Canvas {

    /**
     * <code>slicesValues</code> the slices wrapper object to hold values like percentage, value, color etc.
     */
    private PieValue[] slicesValues;
    /**
     * <code>questionTitle</code> the title of the question to be displayed on the graph.
     */
    private String questionTitle;
    private Command backCommand = new Command("Back",Command.BACK,0);
    private Command saveCommand = new Command("SAVE",Command.SCREEN,1);
    
    /**
     * Creates a new ViewSessionResultsCanvas with a given title, each slices' titles, values and percentage, as well as the flag defining if it's to show the save bottom.
     * @created 08/08/2004 20:44:19
     * @param questionTitle is the title of the question to appear on top of the screen.
     * @param slicesTitle are the titles of each slice
     * @param slicesValues are the  values of each slice
     * @param percentages are the percentage values of each slice
     * @param save defines if it's to show the save button
     */
    public ViewSessionResultsCanvas(SessionResults sessionResults){
        this.questionTitle = sessionResults.getQuestionTitle();
        VoteItem[] voteItems = sessionResults.getVoteItems();
        this.slicesValues = new PieValue[voteItems.length];
        for (int i = 0; i < slicesValues.length; i++)
            this.slicesValues[i] = new PieValue(voteItems[i].getItemTitle(),voteItems[i].getPercentage(),voteItems[i].getNumberVotes(),i);
        if (!sessionResults.isSessionOpened()){
            this.addCommand(this.saveCommand);
        }
        this.addCommand(this.backCommand);
    }
    
    /**
     * @created 08/08/2004 20:46:46
     * @param pieValues
     * @return the total shown data of the survey.
     */
    private long getTotal(PieValue[] pieValues){
        long total = 0;
        for (int i = 0; i < pieValues.length; i++) {
            total = total + pieValues[i].value;
        }
        return total;
    }
    
    /**
     * @created 08/08/2004 20:47:12
     * @param pieValue is a pie slice with its value.
     * @param total is the total number of the values of the slices.
     * @return gets an angle of a given slice, for the total number of data.
     */
    private int getAngle(PieValue pieValue, long total){
        return (int)((360*pieValue.value)/total);
    }    
    
    /* @created 08/08/2004 12:56:03
     * (non-Javadoc)
     * @see javax.microedition.lcdui.Displayable#paint(javax.microedition.lcdui.Graphics)
     */
	protected void paint( Graphics g ){
	    g.setColor(0xffffffff);//white
	    g.fillRect(0,0,this.getWidth(),this.getHeight());
	    
	    long total = this.getTotal(slicesValues);
	    int startAn = 0;
	    int angle = 0;
	    g.setColor(0x00000000);//black
	    g.drawString(this.questionTitle, this.getWidth()/2, 0, Graphics.TOP | Graphics.HCENTER);
	    g.drawString("Total: "+total,5,this.getHeight()-5, Graphics.BASELINE | Graphics.LEFT);
	    
	    for (int i = 0; i < slicesValues.length; i++) {    
	        
	        angle = this.getAngle(slicesValues[i],total);
	        
	        g.setColor(slicesValues[i].color);
	        int offset = 10+(10*(i+1)); 
	        g.fillRect(5,offset,10,10);
	        g.setColor(0);
	        g.drawString(slicesValues[i].title + " ["+slicesValues[i].value+" = "+slicesValues[i].percentage+"]",17,offset-3,0);
	        
	        g.setColor(slicesValues[i].color);
	        g.fillArc(65,65,(this.getWidth()/2)+20,(this.getHeight()/2)+20,startAn,angle+2);
	        
	        startAn = startAn + angle;
	    }
	}
	
	public Command getSaveCommand(){
	    return this.saveCommand;
	}
	
	public Command getBackCommand(){
	    return this.backCommand;
	}
}
