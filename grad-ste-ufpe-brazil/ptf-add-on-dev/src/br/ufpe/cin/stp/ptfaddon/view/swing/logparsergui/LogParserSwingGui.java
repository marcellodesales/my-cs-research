package br.ufpe.cin.stp.ptfaddon.view.swing.logparsergui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.UIManager;

/**
 * <p>Title: Test Case Result Log Parser GUI</p>
 * <p>Description: Temporary LogParser Gui to parse Automatic Test Results</p>
 * @author Marcello Sales Jr.
 * @version 1.0
 */

public class LogParserSwingGui {
  boolean packFrame = false;

  //Construct the application
  public LogParserSwingGui() {
    XLSCreatorGUI2 frame = new XLSCreatorGUI2();
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
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  //Main method
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new LogParserSwingGui();
  }
}
