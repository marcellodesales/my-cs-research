package br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners;

import javax.swing.JTextArea;
import br.ufpe.cin.stp.global.checkchange.NewTestCaseListener;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;
import br.ufpe.cin.stp.ptfaddon.model.log.Instruction;

public class JTextAreaStepsTestCaseListener extends JTextArea implements NewTestCaseListener{

  public JTextAreaStepsTestCaseListener() {
    super();
    this.setLineWrap(false);
    setCaretPosition(this.getDocument().getLength());
  }

  /**
   * newTestCaseAction
   *
   * @param tci TestCaseInstruction
   */
  public void newTestCaseAction(TestCaseInstruction tci) {
    this.removeAll();
    int o = tci.listInstructions().size();
    for (int i=0; i < tci.listInstructions().size(); i++){
      this.append( ( (Instruction) tci.listInstructions().get(i)).getContent()+"\r\n");
    //  updateUI();
    }
    }

  /* (non-Javadoc)
   *  @see br.ufpe.cin.stp.global.checkchange.NewTestCaseListener#clear()
   */
  public void clear() {
    this.setText("");
  }
}
