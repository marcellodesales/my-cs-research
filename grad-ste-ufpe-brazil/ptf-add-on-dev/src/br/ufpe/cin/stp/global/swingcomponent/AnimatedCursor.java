package br.ufpe.cin.stp.global.swingcomponent;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Cursor;

/**
 * Display an animated cursor in a Java Swing application.
 * @author Marcello de Sales
 */
public class AnimatedCursor extends Thread {

  Component comp;
  Toolkit kit;

  public AnimatedCursor(Toolkit kit, Component comp){
    this.comp = comp;
    this.kit = kit;
    this.start();
  }

  public void run(){

    int i = 0;
    while (true){

      try {
        Thread.sleep(70);
      }
      catch (InterruptedException ex) {
      }

      Image pic = kit.getImage(AnimatedCursor.class.getResource("/images/mouse/Eqnormal"+ (++i)+".gif"));
      Cursor cur = kit.createCustomCursor(pic,(new java.awt.Point(0,0)),"my_cursor");
      comp.setCursor(cur);
      if (i == 14) {
        i = 0;
        try {
          Thread.sleep(4000);
        }
        catch (InterruptedException ex) {
        }

      }
    }

  }
}
