package br.ufpe.cin.stp.ptfaddon.view.swing.export;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;


import br.ufpe.cin.stp.global.swingcomponent.DialogFactory;
import br.ufpe.cin.stp.ptfaddon.model.InfoTest;
import br.ufpe.cin.stp.ptfaddon.model.PTFAddonFacade;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite;
import br.ufpe.cin.stp.ptfaddon.view.swing.execution.TestResultPanel;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class ExportTestCasesFrame extends JInternalFrame {

  private DialogFactory dialogFactory = DialogFactory.getInstance();

  JTextField txtPathFilename = new JTextField();
  JButton btnChooser = new JButton();
  JButton btnPanelResults = new JButton();

  private PTFAddonFacade ptfaddonFacade = PTFAddonFacade.getInstance();

  private TestResultPanel testResultPanel = null;
  JTextField txtPlanName = new JTextField();
  JTextField txtCoreID = new JTextField();
  JLabel lblPlanName = new JLabel();
  JLabel lblCoreID = new JLabel();
  JPanel logTab = new JPanel();
  JTabbedPane tabPanel = new JTabbedPane();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel paneResults = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JScrollPane jScrollLog = new JScrollPane();
  JTextArea logTextArea = new JTextArea();
  JPanel panelCenter of Informatics (CIn)-UFPE = new JPanel();
  Border border2;
  TitledBorder titledBorder2;

  private List logFileLines;
  JLabel labelBand = new JLabel();
  JTextField txtBand = new JTextField();
  JPanel jPanel2 = new JPanel();
  Border border3;
  TitledBorder titledBorder3;
  JLabel labelTestPlan = new JLabel();
  JLabel labelCoreID = new JLabel();
  JLabel labelrBand = new JLabel();

  public ExportTestCasesFrame(){
    super("Export Test Cases Utility", true, true, true, true);
    //                                resizable, closeable, maximizable, iconable);
      try {
          jbInit();
      }catch(Exception e) {
        e.printStackTrace();
      }
  }

  private void jbInit() throws Exception {
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(border1,"PTF-TAF Log File");
    border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    titledBorder2 = new TitledBorder(border2,"Center of Informatics (CIn)-UFPE Test Central Repository Information");
    border3 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    titledBorder3 = new TitledBorder(border3,"Report Information");
    this.btnPanelResults.setEnabled(false);
    this.txtPathFilename.setText("");
    this.txtPathFilename.setBounds(new Rectangle(8, 25, 341, 23));
    this.getContentPane().setLayout(borderLayout1);
    this.btnChooser.setBounds(new Rectangle(352, 23, 27, 24));
    this.btnChooser.setMaximumSize(new Dimension(43, 25));
    this.btnChooser.setText("...");
    this.btnChooser.addActionListener(new LogSendToTestCentral_btnChooser_actionAdapter(this));

    this.btnPanelResults.setBounds(new Rectangle(397, 12, 109, 32));
    this.btnPanelResults.setText("Show Results");

    this.btnPanelResults.addActionListener(new LogSendToTestCentral_btnPanelResults_actionAdapter(this));
    txtPlanName.setCaretPosition(0);
    this.txtPlanName.setText("");
    txtPlanName.setColumns(20);

    String coreID = this.ptfaddonFacade.getRegisteredCoreID() == null || this.ptfaddonFacade.getRegisteredCoreID().equals("") ? "" : this.ptfaddonFacade.getRegisteredCoreID();

    this.txtCoreID.setText(coreID);
    txtCoreID.setColumns(7);
    this.lblPlanName.setText("Plan Name");
    this.lblCoreID.setText("Tester CoreID");
    this.logTab.setLayout(null);
    this.paneResults.setLayout(borderLayout2);
    jPanel1.setBorder(titledBorder1);
    jPanel1.setBounds(new Rectangle(3, 4, 387, 179));
    jPanel1.setLayout(null);
    jScrollLog.setToolTipText("Log File Lines");
    jScrollLog.setBounds(new Rectangle(8, 57, 371, 110));
    logTextArea.setEnabled(false);
    logTextArea.setText("");
    panelCenter of Informatics (CIn)-UFPE.setBorder(titledBorder2);
    panelCenter of Informatics (CIn)-UFPE.setBounds(new Rectangle(-1, 183, 511, 57));
    labelBand.setText("Band");
    txtBand.setText("");
    txtBand.setColumns(5);
    jPanel2.setBorder(titledBorder3);
    labelTestPlan.setText("Test Plan: ");
    labelCoreID.setText("Tester CoreID: ");
    labelrBand.setText("Band:");
    this.getContentPane().add(tabPanel, BorderLayout.CENTER);
    this.tabPanel.add(logTab,  "PTF Log File");
    tabPanel.add(paneResults,   "Export Results");
    paneResults.add(jPanel2, BorderLayout.NORTH);
    logTab.add(jPanel1, null);
    this.tabPanel.setEnabledAt(1, false);
    this.setBounds(new Rectangle(170, 89, 650, 400));
    this.setResizable(true);

    this.setTitle("PTF Add-on 3.0 - Report generation from PTF log file");
    jPanel1.add(txtPathFilename, null);
    jPanel1.add(jScrollLog, null);
    jPanel1.add(btnChooser, null);
    jScrollLog.getViewport().add(logTextArea, null);
    panelCenter of Informatics (CIn)-UFPE.add(lblCoreID, null);
    panelCenter of Informatics (CIn)-UFPE.add(txtCoreID, null);
    panelCenter of Informatics (CIn)-UFPE.add(lblPlanName, null);
    panelCenter of Informatics (CIn)-UFPE.add(txtPlanName, null);
    panelCenter of Informatics (CIn)-UFPE.add(labelBand, null);
    logTab.add(panelCenter of Informatics (CIn)-UFPE, null);
    panelCenter of Informatics (CIn)-UFPE.add(txtBand, null);
    logTab.add(btnPanelResults, null);
    jPanel2.add(labelCoreID, null);
    jPanel2.add(labelTestPlan, null);
    jPanel2.add(labelrBand, null);
    //paneResults.add(testResultPanel,  BorderLayout.CENTER);

    this.setBounds(50,50,600,400);
  }

  void btnPanelResults_actionPerformed(ActionEvent e) {

    if (this.txtCoreID.getText() == null || this.txtCoreID.getText().equals("")){
      this.dialogFactory.showErrorMessage("Test Report Error","The tester Core ID must be typed!");
    } else
    if (this.txtPlanName.getText() == null || this.txtPlanName.getText().equals("")){
      this.dialogFactory.showErrorMessage("Test Report Error","The plan name, along with the cycle number, must be typed!");
    } else
    if (this.txtBand.getText() == null || this.txtBand.getText().equals("")){
      this.dialogFactory.showErrorMessage("Test Report Error","The Band (Cycle Group 2) value must be typed!");
    } else {

      InfoTest infoTest = new InfoTest();
      infoTest.setCoreid(this.txtCoreID.getText());
      infoTest.setTestplan(this.txtPlanName.getText());
      infoTest.setCycle(this.txtBand.getText());
      try {
          
          TestCaseSuite suite = this.ptfaddonFacade.getTestCaseSuite(this.logFileLines);
          
        this.testResultPanel = new TestResultPanel(suite, infoTest);
        this.paneResults.add(testResultPanel, BorderLayout.CENTER);
        
        this.labelrBand.setText(this.labelrBand.getText()+infoTest.getCycle());
        this.labelCoreID.setText(this.labelCoreID.getText()+infoTest.getCoreid());
        this.labelTestPlan.setText(this.labelTestPlan.getText()+infoTest.getTestplan());
        
        this.tabPanel.setEnabledAt(1, true);
        this.tabPanel.setSelectedIndex(1);
       
        if (suite.getLastIncompleteTestCase() != null)
            this.dialogFactory.showWarningMessage("Incomplete Log Results","Although the results were generated, the log file is incomplete!\nThe last test case being executed was "+suite.getLastIncompleteTestCase().getTestCaseID());
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  void btnChooser_actionPerformed(ActionEvent e) {
    JFileChooser chooser = DialogFactory.getInstance().getFileSingleSelectionChooser();
    chooser.setDialogTitle("Open a PTF-TAF log file...");
    int status = chooser.showOpenDialog(this);

    if (status == JFileChooser.APPROVE_OPTION) {

      try {
        this.logFileLines = this.ptfaddonFacade.getLogFileLines(chooser.getSelectedFile().getPath());

        Iterator i = logFileLines.iterator();

        while (i.hasNext())
          this.logTextArea.append((String)i.next()+"\n");

        this.txtPathFilename.setText(chooser.getSelectedFile().getPath());
        this.btnPanelResults.setEnabled(true);
      }catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }

    }
  }
}

class LogSendToTestCentral_btnPanelResults_actionAdapter implements java.awt.event.ActionListener {
  ExportTestCasesFrame adaptee;

  LogSendToTestCentral_btnPanelResults_actionAdapter(ExportTestCasesFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnPanelResults_actionPerformed(e);
  }
}

class LogSendToTestCentral_btnChooser_actionAdapter implements java.awt.event.ActionListener {
  ExportTestCasesFrame adaptee;

  LogSendToTestCentral_btnChooser_actionAdapter(ExportTestCasesFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnChooser_actionPerformed(e);
  }
}
