package br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import br.ufpe.cin.stp.global.checkchange.StartTestCaseListener;
import br.ufpe.cin.stp.global.checkchange.NewTestCaseListener;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;

/**
 * @author Marcello de Sales
 * @version 1.0
 */
public class JPanelTestCaseListener extends JPanel implements StartTestCaseListener, NewTestCaseListener{

  private JLabel lblTestCasesPassed;
  private JLabel lblTestCasesFailed;
  private JLabel lblTestCasesNotConfigured;
  private JLabel lblTestCasesExecuted;
  private JLabel lblCurrentTestCase;

  private int countTestCasesPassed = 0;
  private int countTestCasesFailed = 0;
  private int countTestCasesNotConfigured = 0;
  private int countTotalTestCases = 0;
  private int countTestCasesExecuted = 0;


  /**
   * JPanelTestCaseListener
   */
  public JPanelTestCaseListener() {
    lblTestCasesPassed = new JLabel();
    lblTestCasesFailed = new JLabel();
    lblTestCasesNotConfigured = new JLabel();
    lblTestCasesExecuted = new JLabel();
    lblCurrentTestCase = new JLabel();

    add(lblTestCasesPassed, null);
    add(lblTestCasesFailed, null);
    add(lblTestCasesNotConfigured, null);
    add(lblTestCasesExecuted, null);
    add(lblCurrentTestCase, null);

    lblTestCasesPassed.setText("Test Cases Passed:");
    lblTestCasesPassed.setBounds(new Rectangle(2, 0, 120, 15));
    this.setLayout(null);
    lblTestCasesFailed.setText("Test Cases Failed:");
    lblTestCasesFailed.setBounds(new Rectangle(3, 14, 110, 15));
    this.setDebugGraphicsOptions(0);
    lblTestCasesFailed.setRequestFocusEnabled(true);
    lblTestCasesNotConfigured.setRequestFocusEnabled(true);
    lblTestCasesNotConfigured.setDisplayedMnemonic('0');
    lblTestCasesNotConfigured.setText("Test Cases Not Configured:");
    lblTestCasesNotConfigured.setBounds(new Rectangle(3, 28, 160, 15));
    lblTestCasesExecuted.setText("Test Cases Executed: ");
    lblTestCasesExecuted.setBounds(new Rectangle(223, 0, 150, 15));
    lblCurrentTestCase.setRequestFocusEnabled(true);
    lblCurrentTestCase.setToolTipText("");
    lblCurrentTestCase.setVerifyInputWhenFocusTarget(true);
    lblCurrentTestCase.setText("Current Test Case:");
    lblCurrentTestCase.setBounds(new Rectangle(223, 15, 350, 15));


  }

  /**
   * newStartLineAction
   *
   * @param Start test Case Line
   */
  public void newStartLineAction(String line) {
  	    
  	    String lineTemp = line.substring(line.lastIndexOf(".")+1, line.length());
  	    if (lineTemp.indexOf(" ") > 1){
  	        lineTemp = lineTemp.substring(0, line.indexOf(" "));
  	        System.out.println(lineTemp);  	        
  	    }
  	
  	
        this.lblCurrentTestCase.setText("Current Test Case: " + lineTemp);
        this.lblTestCasesExecuted.setText("Test Cases Executed: " + this.countTestCasesExecuted + "/" + this.countTotalTestCases);        
  }

  /**
   * newTestCaseAction
   *
   * @param tci TestCaseInstruction
   */
  public void newTestCaseAction(TestCaseInstruction tci) {
    if ( tci.getResult().equals("PASSED") ){
       this.countTestCasesPassed++;
       this.lblTestCasesPassed.setText("Test Cases Passed: 0" + this.countTestCasesPassed);
    }
    if ( tci.getResult().equals("FAILED") ){
       this.countTestCasesFailed++;
       this.lblTestCasesFailed.setText("Test Cases Failed: 0" + this.countTestCasesFailed);
    }
    if ( tci.getResult().equals("NOT CONFIGURED") ){
       this.countTestCasesNotConfigured++;
       this.lblTestCasesNotConfigured.setText("Test Cases Not Configured: 0" + this.countTestCasesNotConfigured);
    }

    this.countTestCasesExecuted = this.countTestCasesExecuted + 1;
    this.lblTestCasesExecuted.setText("Test Cases Executed: " +  this.countTestCasesExecuted + "/" + this.countTotalTestCases);

  }

  public void setTotalTestCases(int totalTestCases){
    this.countTotalTestCases = totalTestCases;
    this.lblTestCasesExecuted.setText("Test Cases Executed: " + this.countTestCasesExecuted + "/" + this.countTotalTestCases);
  }

/* (non-Javadoc)
 * @see br.ufpe.cin.stp.global.checkchange.StartTestCaseListener#clear()
 */
  public void clear() {
    lblTestCasesPassed.setText("Test Cases Passed: 0");
    lblTestCasesFailed.setText("Test Cases Failed: 0");
    lblTestCasesNotConfigured.setText("Test Cases Not Configured: 0");  
    lblTestCasesExecuted.setText("Test Cases Executed: ");
    this.lblCurrentTestCase.setText("Current Test Case: ");    
  }

}
