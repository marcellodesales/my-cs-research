/*
 * @created 05/07/2004 15:59:06
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.screen;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


/**
 * Creates a new SplashScreen for the application.
 * Technical Articles and Tips: Sun Microsystems
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 05/07/2004 15:59:06
 */
public class SplashScreenAlert extends Canvas {
    
    private boolean manager;
    private Display     display;
    private Displayable next;
    private Timer       timer = new Timer();
    
    private Image image;
    private int count;

    public SplashScreenAlert(Display display, Displayable next, boolean manager){
        this.manager = manager;
        this.display = display;
        this.next    = next;

        display.setCurrent(this);
    }

    protected void keyPressed( int keyCode ){
        dismiss();
    }

    protected void paint( Graphics g ){
        int width = getWidth();
        int height = getHeight();
        g.setColor(255,255,255);
        g.fillRect(0, 0, width, height);
//         Load an image from the MIDlet resources
//      Load an image from the MIDlet resources
        if (this.image == null) {
	        try {
	            if(this.manager)
	                image = Image.createImage("/massmanager.png");
	            else image = Image.createImage("/massclient.png");
	            g.setColor(0,0,0);
	            g.drawString("Mobile Audience Survey System",this.getWidth()/2,this.getHeight()-30,Graphics.BASELINE | Graphics.HCENTER);
	            g.drawString("Marcello Sales Jr.",this.getWidth()/2,this.getHeight()-18,Graphics.BASELINE | Graphics.HCENTER);
	            g.drawString("CIn / Motorola / UFPE - August 2004",this.getWidth()/2,this.getHeight()-6,Graphics.BASELINE | Graphics.HCENTER);
	            
	        } catch (IOException ex) {
	        }
        }
	    g.drawImage(image, width/2, height/2, Graphics.VCENTER | Graphics.HCENTER);	        
    }	        

    protected void pointerPressed( int x, int y ){
        dismiss();
    }

    protected void showNotify(){
        timer.schedule( new CountDown(), 5000 );
    }

    private void dismiss(){
        timer.cancel();
        display.setCurrent( next );
    }

    private class CountDown extends TimerTask {
        public void run(){
            dismiss();
        }
    }
}
