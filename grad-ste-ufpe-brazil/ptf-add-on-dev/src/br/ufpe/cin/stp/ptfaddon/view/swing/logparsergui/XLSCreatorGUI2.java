package br.ufpe.cin.stp.ptfaddon.view.swing.logparsergui;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * <p>Title: Test Case Result Log Parser GUI</p>
 * <p>Description: Temporary LogParser Gui to parse Automatic Test Results</p>
 * @author Marcello Sales Jr.
 * @version 1.0
 */

public class XLSCreatorGUI2 extends JFrame {
  JPanel contentPane;
  JLabel statusBar = new JLabel();
  Border border1;
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JButton generateButton = new JButton();
  JTextField logDestinationPath = new JTextField();
  JPanel jPanel1 = new JPanel();
  JButton searchLogButton = new JButton();
  JPanel jPanel2 = new JPanel();
  JTextField logPathEdit = new JTextField();
  TitledBorder titledBorder3;
  TitledBorder titledBorder4;
  JFileChooser fileChooser = new JFileChooser();

  //Construct the frame
  public XLSCreatorGUI2() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    border1 = BorderFactory.createEmptyBorder();
    titledBorder1 = new TitledBorder(BorderFactory.createEmptyBorder(),"Log File (.txt)");
    titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"tgighg");
    titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"Log File");
    titledBorder4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"Destination File");
    contentPane.setLayout(null);
    this.setResizable(false);
    this.setSize(new Dimension(483, 182));
    this.setState(Frame.NORMAL);
    this.setTitle("PTF Log Parser - Generating \"Well-Formated\" .xls Results");
    statusBar.setText(" ");
    statusBar.setBounds(new Rectangle(0, 285, 400, 15));
    generateButton.setBounds(new Rectangle(171, 117, 120, 23));
    generateButton.setEnabled(false);
    generateButton.setText("Generate XLS!");
    generateButton.addActionListener(new XLSCreatorGUI2_generateButton_actionAdapter(this));
    logDestinationPath.setEditable(false);
    logDestinationPath.setText("");
    logDestinationPath.setBounds(new Rectangle(10, 19, 411, 20));
    jPanel1.setBorder(titledBorder3);
    jPanel1.setBounds(new Rectangle(23, 7, 438, 50));
    jPanel1.setLayout(null);
    searchLogButton.setBounds(new Rectangle(306, 17, 123, 23));
    searchLogButton.setActionCommand("");
    searchLogButton.setText("Search Log File...");
    searchLogButton.addActionListener(new XLSCreatorGUI2_searchLogButton_actionAdapter(this));
    jPanel2.setBorder(titledBorder4);
    jPanel2.setBounds(new Rectangle(26, 61, 434, 50));
    jPanel2.setLayout(null);
    logPathEdit.setEditable(false);
    logPathEdit.setText("");
    logPathEdit.setBounds(new Rectangle(9, 18, 290, 20));
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setBounds(new Rectangle(-6, 151, 1004, 383));
    contentPane.add(statusBar, null);
    jPanel1.add(logPathEdit, null);
    jPanel1.add(searchLogButton, null);
    contentPane.add(fileChooser, null);
    contentPane.add(jPanel2, null);
    jPanel2.add(logDestinationPath, null);
    contentPane.add(generateButton, null);
    contentPane.add(jPanel1, null);
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void searchLogButton_actionPerformed(ActionEvent e) {
    this.showLogFileChooser((byte)0);
  }

  public JFileChooser getFileChooser() {
    if (this.fileChooser == null) {
      fileChooser = new JFileChooser("");

      fileChooser.setAcceptAllFileFilterUsed(false);
      fileChooser.setMultiSelectionEnabled(false);
    }
    return this.fileChooser;
  }

  protected void showLogFileChooser(byte type){

    if (type == 0){
      this.getFileChooser().setFileFilter(new TXTFileFilter());
      this.getFileChooser().setDialogTitle("Open log file...");
      int status = this.getFileChooser().showOpenDialog(this);
      if (status == JFileChooser.APPROVE_OPTION) {
        this.logPathEdit.setText(this.fileChooser.getSelectedFile().getAbsoluteFile().getAbsolutePath());
        this.showLogFileChooser((byte)1);
      }
    }else{
      this.getFileChooser().setFileFilter(new XLSFileFilter());
      this.getFileChooser().setDialogTitle("Set destination directory and file...");
      int status = this.getFileChooser().showOpenDialog(this);
      if (status == JFileChooser.APPROVE_OPTION) {
        this.logDestinationPath.setText(this.fileChooser.getSelectedFile().getAbsoluteFile().getAbsolutePath());
        this.generateButton.setEnabled(true);
      }
    }

  }


  void generateButton_actionPerformed(ActionEvent e) {
  	//try {

		//LogParser log = new LogParser(this.logPathEdit.getText());

		//XLSReport report = new XLSReport(this.logDestinationPath.getText(),log.getTestCases());
		/*report.save();
		try{
			FileReader	xlsFile = new FileReader(this.logDestinationPath.getText());
			JOptionPane.showMessageDialog(null,"The new report was created successfully!!!","XLS Report Created",JOptionPane.INFORMATION_MESSAGE);

			if (report.logWasIncomplete()){
				String testCaseID = report.getIncompletTestCaseInstruction().getTestCaseID();
				JOptionPane.showMessageDialog(null,"However, the log was incomplete, and the last instruction being executed was "+testCaseID,"XLS Report Created",JOptionPane.WARNING_MESSAGE);
			}

		} catch (java.io.FileNotFoundException fnfe){
			JOptionPane.showMessageDialog(null, "Erro Ocorred while creating Report on "+this.logDestinationPath.getText());
		}

	} catch (FileNotFoundException fnfe) {
		JOptionPane.showMessageDialog(null,fnfe.getMessage());
	} catch (IOException ioe){
		JOptionPane.showMessageDialog(null,ioe.getMessage());
	}*/
  }
}

class TXTFileFilter extends javax.swing.filechooser.FileFilter {
    public boolean accept(java.io.File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
    }

    public String getDescription() {
        return "PTF Log Files in .TXT";
    }
}

class XLSFileFilter extends javax.swing.filechooser.FileFilter {
      public boolean accept(java.io.File f) {
          return f.isDirectory() || f.getName().toLowerCase().endsWith(".xls");
      }

      public String getDescription() {
          return "PTF Log Files in .XLS";
      }
}


class XLSCreatorGUI_searchLogButton_actionAdapter implements java.awt.event.ActionListener {
  XLSCreatorGUI2 adaptee;

  XLSCreatorGUI_searchLogButton_actionAdapter(XLSCreatorGUI2 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.searchLogButton_actionPerformed(e);
  }
}

class XLSCreatorGUI2_generateButton_actionAdapter implements java.awt.event.ActionListener {
  XLSCreatorGUI2 adaptee;

  XLSCreatorGUI2_generateButton_actionAdapter(XLSCreatorGUI2 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.generateButton_actionPerformed(e);
  }
}

class XLSCreatorGUI2_searchLogButton_actionAdapter implements java.awt.event.ActionListener {
  XLSCreatorGUI2 adaptee;

  XLSCreatorGUI2_searchLogButton_actionAdapter(XLSCreatorGUI2 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.searchLogButton_actionPerformed(e);
  }
}
