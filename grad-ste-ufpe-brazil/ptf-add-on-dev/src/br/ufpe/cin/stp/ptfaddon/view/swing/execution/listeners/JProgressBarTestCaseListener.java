package br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners;

import javax.swing.JProgressBar;
import br.ufpe.cin.stp.global.checkchange.NewTestCaseListener;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;

public class JProgressBarTestCaseListener extends JProgressBar implements NewTestCaseListener{

  public JProgressBarTestCaseListener() {
    super();
  }

  /**
   * newTestCaseAction
   *
   * @param tci TestCaseInstruction
   */
  public void newTestCaseAction(TestCaseInstruction tci){
    this.setValue(this.getValue()+1);
  }

  /* (non-Javadoc)
   * @see br.ufpe.cin.stp.global.checkchange.NewTestCaseListener#clear()
   */
  public void clear() {
  	this.setValue(0);
  }

}
