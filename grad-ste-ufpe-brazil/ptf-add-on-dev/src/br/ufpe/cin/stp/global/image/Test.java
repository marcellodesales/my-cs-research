package br.ufpe.cin.stp.global.image;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class Test extends Applet { 
      Image image; 
   
      public void init() { 
         image = ImageLoader.getImage("/images/package.png");
      } 
   
   
      public void update(Graphics g) { 
         if (image != null) {
            Dimension d = getSize(); 
            g.drawImage(image, 0, 0, d.width, d.height, Color.white, null); 
         }
      } 
   
   
      public void paint(Graphics g) { 
         update(g);
      } 
}

