package br.ufpe.cin.stp.ptfaddon.view.swing.export;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import br.ufpe.cin.stp.global.checkchange.NewTestCaseListener;
import br.ufpe.cin.stp.ptfaddon.model.InfoTest;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite;
import br.ufpe.cin.stp.ptfaddon.model.testcentral.ParserTestCaseID;
import br.ufpe.cin.stp.global.swingcomponent.DialogFactory;

public class ExportingTestCentralFramel extends JFrame implements NewTestCaseListener{

  JProgressBar progressBar = new JProgressBar();
  JButton btnOK = new JButton();
  JLabel lblTestCurrentSend = new JLabel();
  int maxValueProgressBar = 0;
  private TestCaseSuite testCaseSuite;
  private InfoTest testInfo;

  /**
   * SendTestCentral
   *
   * @param testCaseSuite TestCaseSuite
   */
  public ExportingTestCentralFramel(TestCaseSuite testCaseSuite, InfoTest testInfo) {
    super();
    this.testCaseSuite = testCaseSuite;
    this.testInfo = testInfo;
    maxValueProgressBar = testCaseSuite.getTestCases().size();
    initComponents();
  }

  public ExportingTestCentralFramel(){
    super();
  }
  
  public void setTestCaseSuite(TestCaseSuite testCaseSuite, InfoTest testInfo) {
    this.testCaseSuite = testCaseSuite;
    this.testInfo = testInfo;
    maxValueProgressBar = testCaseSuite.getTestCases().size();
    this.progressBar.setValue(0);
    initComponents();
  }

  private void initComponents(){
    this.getContentPane().setLayout(null);
    this.setBounds( new Rectangle(19, 37, 400, 180) );
    this.setResizable(false);
    this.setTitle("Sending results to the Center of Informatics (CIn)-UFPE Test Central Repository");
    this.progressBar.setBounds(new Rectangle(19, 37, 348, 41));
    this.progressBar.setMaximum( maxValueProgressBar );
    this.btnOK.setBounds(new Rectangle(135, 92, 108, 38));
    this.btnOK.setText("Close");
    this.btnOK.addActionListener(new SendTestCentral_btnOK_actionAdapter(this));
    this.btnOK.setEnabled(false);
    this.lblTestCurrentSend.setText("Sending");
    this.lblTestCurrentSend.setBounds(new Rectangle(20, 22, 345, 15));
    this.getContentPane().add(progressBar, null);
    this.getContentPane().add(btnOK, null);
    this.getContentPane().add(lblTestCurrentSend, null);
  }

  /**
   * send
   */
  private void send(){
    ParserTestCaseID tc = new ParserTestCaseID(testCaseSuite, this.testInfo);
    tc.addListener(this);
    new Thread(tc).start();
  }

/*
  public static void main(String[] args){
  LogParser logParse = null;
  SendTestCentral sendTestCentral;
    try {
      logParse = new LogParser("c:\\temp\\C650_ptf_log.txt");
    }catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }

    sendTestCentral = new SendTestCentral(logParse.getTestCases());
    sendTestCentral.show();
    }
*/

  /**
   * show
   */
  public void show(){
    String[] buttons = {"Send Results","Cancel"};
    if ( DialogFactory.getInstance().showConfirmDialog("Center of Informatics (CIn)-UFPE Test Central Repository", "Send the results to Center of Informatics (CIn)-UFPE Test Central now?\nNote that you must be connected to the Center of Informatics (CIn)-UFPE VPN in order to send the results.",buttons,buttons[0]) == JOptionPane.YES_OPTION ){
       send();
       super.show();
    }
  }

  /**
   * newTestCaseAction
   *
   * @param tci TestCaseInstruction
   */
  public void newTestCaseAction(TestCaseInstruction tci) {
    if (tci == null){
      this.lblTestCurrentSend.setText("The tests were successfully sent to the Center of Informatics (CIn)-UFPE Test Central Repository");
    }else{
      this.lblTestCurrentSend.setText(tci.getTestCaseID() + " was sent...");
    }
    this.progressBar.setValue( this.progressBar.getValue() + 1 );
    this.btnOK.setEnabled(true);
  }

  void btnOK_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  /* (non-Javadoc)
   *  @see br.ufpe.cin.stp.global.checkchange.NewTestCaseListener#clear()
   */
  public void clear() {
  }
}

class SendTestCentral_btnOK_actionAdapter implements java.awt.event.ActionListener {
  ExportingTestCentralFramel adaptee;

  SendTestCentral_btnOK_actionAdapter(ExportingTestCentralFramel adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOK_actionPerformed(e);
  }
}
