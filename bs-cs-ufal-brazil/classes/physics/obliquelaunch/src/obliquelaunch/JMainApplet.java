package obliquelaunch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;
import obliquelaunch.JMain;

public class JMainApplet extends JApplet {
  
    public JMainApplet() {
		
    }

    public void init() {
		JMain world = new JMain();
		/*		world.addWindowListener(new WindowListener() {
          public void windowClosing(WindowListener e) {
              System.exit(0);
          }
		 });*/
    }
}
