package br.ufpe.cin.stp.ptfaddon.view.swing.execution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.ufpe.cin.stp.global.configuration.ResourceManager;
import br.ufpe.cin.stp.global.swingcomponent.DialogFactory;
import br.ufpe.cin.stp.global.swingcomponent.layout.VerticalFlowLayout;
import br.ufpe.cin.stp.global.swingcomponent.table.ColorCellRender;
import br.ufpe.cin.stp.global.swingcomponent.table.TestCaseSuitTableModel;
import br.ufpe.cin.stp.ptfaddon.model.InfoTest;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite;
import br.ufpe.cin.stp.ptfaddon.model.log.XLSReport;
import br.ufpe.cin.stp.ptfaddon.view.swing.export.ExportingTestCentralFramel;


/**
 * <p>Description: </p>
 * @author Marcello Sales Jr.
 * @version 3.0
 */
public class TestResultPanel extends JPanel {

    private DialogFactory dialogCreator = DialogFactory.getInstance();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout7 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  JPanel jPanel5 = new JPanel();
  JPanel jPanelResultInfo = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  Border border2;
  TitledBorder titledBorder2;
  Border border3;
  JPanel jPanel8 = new JPanel();
  Border border4;
  TitledBorder titledBorder3;
  BorderLayout borderLayout3 = new BorderLayout();
  JTable resultTable = new JTable();
  JScrollPane scrollPane = new JScrollPane(resultTable);
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();

  private TestCaseSuite testCaseSuit;
  private ExportingTestCentralFramel sendTestCentral;
  private InfoTest testInfo;
  JPanel jPanel6 = new JPanel();
  Border border5;
  JLabel elapsedLabel = new JLabel();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  BorderLayout borderLayout6 = new BorderLayout();
  GridLayout gridLayout1 = new GridLayout();
  JButton btnTestCentral = new JButton();
  JButton btnExportToXls = new JButton();

  public TestResultPanel(TestCaseSuite tcs, InfoTest testInfo) throws Exception {
      this.testCaseSuit = tcs;
      this.testInfo = testInfo;
      jbInit();
      this.initTableResult();
      this.sendTestCentral = new ExportingTestCentralFramel();
  }

  private void initTableResult(){
    TestCaseSuitTableModel tcstm = new TestCaseSuitTableModel(this.testCaseSuit);
    ColorCellRender ccr = new ColorCellRender(tcstm);
    jPanel8.add(new JScrollPane(ccr.getColoredTable()), BorderLayout.CENTER);
    this.elapsedLabel.setText(this.elapsedLabel.getText()+""+this.testCaseSuit.getElapsedTime());
  }

  void jbInit() throws Exception {
    border1 = BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(border1,"Result Information");
    border2 = BorderFactory.createEmptyBorder();
    titledBorder2 = new TitledBorder(border2,"Percentage");
    border3 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151)),"Percentage");
    border4 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    titledBorder3 = new TitledBorder(border4,"Test Case Suit Results");
    border5 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    jPanel4.setLayout(borderLayout7);
    this.setLayout(borderLayout1);
    jPanel1.setMaximumSize(new Dimension(107, 117));
    jPanel1.setMinimumSize(new Dimension(10, 10));
    jPanel1.setPreferredSize(new Dimension(107, 117));
    jPanel1.setLayout(verticalFlowLayout1);
    jPanel2.setMinimumSize(new Dimension(380, 10));
    jPanel2.setOpaque(true);
    jPanel2.setPreferredSize(new Dimension(380, 10));
    jPanel2.setLayout(borderLayout2);
    jPanel3.setMinimumSize(new Dimension(10, 90));
    jPanel3.setOpaque(true);
    jPanel3.setPreferredSize(new Dimension(10, 90));
    jPanel3.setLayout(borderLayout5);
    jPanelResultInfo.setBorder(titledBorder1);
    jPanelResultInfo.setMinimumSize(new Dimension(312, 42));
    jPanelResultInfo.setLayout(gridLayout1);
    jPanel5.setLayout(borderLayout6);
    jPanel8.setBorder(titledBorder3);
    jPanel8.setLayout(borderLayout3);
    jLabel1.setBackground(SystemColor.activeCaptionBorder);
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel1.setForeground(new Color(0, 193, 0));
    jLabel1.setText("Passed: "+this.testCaseSuit.getPassed());
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel2.setForeground(Color.red);
    jLabel2.setText("Failed: "+this.testCaseSuit.getFailed());
    jLabel3.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel3.setForeground(SystemColor.textHighlight);
    jLabel3.setRequestFocusEnabled(true);
    jLabel3.setText("Not Configured: "+this.testCaseSuit.getNotConf());
    jLabel4.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel4.setText("Unknown: "+this.testCaseSuit.getUnknown());
    jPanel6.setFont(new java.awt.Font("Dialog", 1, 11));
    jPanel6.setBorder(border5);
    jPanel6.setMinimumSize(new Dimension(10, 20));
    jPanel6.setOpaque(true);
    jPanel6.setPreferredSize(new Dimension(10, 20));
    jPanel6.setLayout(borderLayout4);
    elapsedLabel.setFont(new java.awt.Font("Dialog", 1, 11));
    elapsedLabel.setHorizontalAlignment(SwingConstants.LEFT);
    elapsedLabel.setHorizontalTextPosition(SwingConstants.LEFT);
    elapsedLabel.setText("  Elapsed Test Time: ");
    btnTestCentral.setText("Test Central");
    btnTestCentral.addActionListener(new TestResultPanel_btnTestCentral_actionAdapter(this));
    btnExportToXls.setText("Export");
    btnExportToXls.addActionListener(new TestResultPanel_btnExportToXls_actionAdapter(this));
    this.add(jPanel4, BorderLayout.CENTER);
    jPanel4.add(jPanel1,  BorderLayout.EAST);
    jPanel1.add(btnTestCentral, null);
    jPanel1.add(btnExportToXls, null);
    jPanel4.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jPanel3, BorderLayout.NORTH);
    jPanel3.add(jPanelResultInfo, BorderLayout.CENTER);
    jPanel2.add(jPanel5,  BorderLayout.CENTER);
    jPanel5.add(jPanel8, BorderLayout.CENTER);
    jPanel8.add(jPanel6, BorderLayout.SOUTH);
    jPanel6.add(elapsedLabel, BorderLayout.CENTER);
    // Disable auto resizing to make the table horizontal scrollable
    this.resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    jPanelResultInfo.add(jLabel3, null);
    jPanelResultInfo.add(jLabel1, null);
    jPanelResultInfo.add(jLabel4, null);
    jPanelResultInfo.add(jLabel2, null);

    this.btnTestCentral.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/repository.png")));
    this.btnExportToXls.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/xls-icon.gif")));

  }

  private void saveXLS(String fileDestinationPath){

    try {

      XLSReport report = new XLSReport(fileDestinationPath,this.testCaseSuit);
      report.save();
      try {
        FileReader xlsFile = new FileReader(fileDestinationPath);
        this.dialogCreator.showInfoMessage("XLS Report Created","The new report was successfully created!!!");

        if (report.logWasIncomplete()) {
          String testCaseID = report.getIncompletTestCaseInstruction().getTestCaseID();
          this.dialogCreator.showWarningMessage("XLS Report Created","However, the log was incomplete, and "+
                  "the last instruction being executed was " +
                  testCaseID);
        }

      }
      catch (java.io.FileNotFoundException fnfe) {
          this.dialogCreator.showErrorMessage("Erro Ocorred while creating Report on "+
                                   fileDestinationPath,fnfe.getMessage());
      }

    }
    catch (FileNotFoundException fnfe) {
      JOptionPane.showMessageDialog(null, fnfe.getMessage());
    }
    catch (IOException ioe) {
      JOptionPane.showMessageDialog(null, ioe.getMessage());
    }

  }

  void btnExportToXls_actionPerformed(ActionEvent e) {
    JFileChooser chooser = this.dialogCreator.getFileSingleSelectionChooser();
    chooser.setFileFilter(this.dialogCreator.new XLSReportFilter());
    chooser.setDialogTitle("Save report in XLS file...");
    int status = chooser.showDialog(this,"Save");
    if (status == JFileChooser.APPROVE_OPTION) {
        
        String filePath = chooser.getSelectedFile().getAbsolutePath();
        filePath = filePath.endsWith(".xls") ? filePath : filePath + ".xls";
        
      this.saveXLS(filePath);
    }
  }

  void btnTestCentral_actionPerformed(ActionEvent e) {
       this.sendTestCentral.setTestCaseSuite( this.testCaseSuit, testInfo  );
       sendTestCentral.show();
  }
}

class TestResultPanel_btnExportToXls_actionAdapter implements java.awt.event.ActionListener {
  TestResultPanel adaptee;

  TestResultPanel_btnExportToXls_actionAdapter(TestResultPanel adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnExportToXls_actionPerformed(e);
  }
}

class TestResultPanel_btnTestCentral_actionAdapter implements java.awt.event.ActionListener {
  TestResultPanel adaptee;

  TestResultPanel_btnTestCentral_actionAdapter(TestResultPanel adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnTestCentral_actionPerformed(e);
  }
}
