package br.ufpe.cin.stp.global.checkchange;

import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;

public interface NewTestCaseListener {

  public void newTestCaseAction(TestCaseInstruction tci);
  
  public void clear();

}
