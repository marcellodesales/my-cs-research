package br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners;

import javax.swing.JLabel;
import br.ufpe.cin.stp.global.checkchange.NewTestCaseListener;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;

public class JLabelTestCaseListener extends JLabel implements NewTestCaseListener{

  public JLabelTestCaseListener() {
    super();
  }

  public void newTestCaseAction(TestCaseInstruction tci){
     this.setText(tci.getTestCaseID());
     this.updateUI();
  }

/* (non-Javadoc)
 * @see br.ufpe.cin.stp.global.checkchange.NewTestCaseListener#clear()
 */
  public void clear() {
	 
  }

}
