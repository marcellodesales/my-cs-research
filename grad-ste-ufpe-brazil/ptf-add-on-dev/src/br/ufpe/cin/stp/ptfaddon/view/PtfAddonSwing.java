package br.ufpe.cin.stp.ptfaddon.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.UIManager;

import br.ufpe.cin.stp.global.swingcomponent.lookandfeel.IZPackKMetalTheme;
import br.ufpe.cin.stp.ptfaddon.view.swing.MainFrame;

import com.incors.plaf.kunststoff.KunststoffLookAndFeel;


/**
 * <p>Title: PTF Add-on V3.0</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Center of Informatics (CIn)-UFPE Inc.</p>
 * @author Elias Queiroga
 * @author Marcello Sales Jr.
 * @version 3.0
 */

public class PtfAddonSwing {
  boolean packFrame = false;

  //Construct the application
  public PtfAddonSwing() {
    MainFrame frame = new MainFrame();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setSize(screenSize);
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  //Main method
  public static void main(String[] args) {
      System.out.println("Initiating PTF Add-on...");
    try {
      com.incors.plaf.kunststoff.KunststoffLookAndFeel kunststoffLnF
      = new KunststoffLookAndFeel();
      KunststoffLookAndFeel.setCurrentTheme(new IZPackKMetalTheme());
      UIManager.setLookAndFeel(kunststoffLnF);
      System.out.println("Loaded Look and Feel Definitions...");
      System.out.println("Starting application...");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new PtfAddonSwing();
  }
}
