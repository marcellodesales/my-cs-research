/*
 * @created 05/07/2004 15:59:06
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.appointment.view.midp.form;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
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
    
    private Display     display;
    private Displayable next;
    private Timer       timer = new Timer();
    private int delay = 5000;
    
    private static Image image;
    private int count;

    public SplashScreenAlert(Display display, Displayable next ){
        this.display = display;
        this.next    = next;

        display.setCurrent(this);
    }

    protected void keyPressed( int keyCode ){
        dismiss();
    }

    protected void paint( Graphics g ){
        //g.drawString("My Appointments...", this.getWidth( )/2, 0,Graphics.TOP | Graphics.HCENTER);

        int width = getWidth( );
        int height = getHeight( );
//         Fill the background using black
        g.setColor(1);
        g.fillRect(0, 0, width, height);
//         Load an image from the MIDlet resources
//      Load an image from the MIDlet resources
        if (image == null) {
	        try {
	            image = Image.createImage("/dhome.png");
	        } catch (IOException ex) {

//	             Create an Image the same size as the Canvas.
	            Image image = Image.createImage(width, height);
	            Graphics imageGraphics = image.getGraphics( );
//	             Fill the background of the image black
	            imageGraphics.fillRect(0, 0, width, height);
//	             Draw a pattern of lines
	            int count = 10;
	            int yIncrement = height/count;
	            int xIncrement = width/count;
	            for (int i = 0, x = xIncrement, y = 0; i < count; i++) {
	            imageGraphics.setColor(0xC0 + ((128 + 10 * i) << 8) +
	            ((128 + 10 * i) << 16));
	            imageGraphics.drawLine(0, y, x, height);
	            y += yIncrement;
	            x += xIncrement;
	            }
//	             Add some text
	            imageGraphics.setFont(Font.getFont(Font.FACE_PROPORTIONAL,
	            Font.STYLE_BOLD, Font.SIZE_LARGE));
	            imageGraphics.setColor(0xffff00);
	            imageGraphics.drawString("My Appointments...", width/2, 0, Graphics.TOP |
	            Graphics.HCENTER);
	            imageGraphics.drawString("by Marcello Sales Jr.", (width/2)+10, height/2, Graphics.TOP |
	    	            Graphics.HCENTER);

//	             Copy the Image to the screen
	            g.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);	            
	            
	            // g.setColor(0xffffff);
	            //g.drawString("My Appointments...", 0, 0, Graphics.TOP | Graphics.LEFT);
	            //g.drawString("by Marcello Sales Jr., 2004", this.getWidth( )/2, this.getHeight()/2, Graphics.TOP | Graphics.LEFT);
	            return;
	        }
        }
	    g.drawImage(image, width/2, height/2, Graphics.VCENTER | Graphics.HCENTER);	        
    }	        

    protected void pointerPressed( int x, int y ){
        dismiss();
    }

    protected void showNotify(){
        timer.schedule( new CountDown(), this.delay);
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
