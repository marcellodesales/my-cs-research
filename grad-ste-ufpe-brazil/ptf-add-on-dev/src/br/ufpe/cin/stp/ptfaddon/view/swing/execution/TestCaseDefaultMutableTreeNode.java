package br.ufpe.cin.stp.ptfaddon.view.swing.execution;

import javax.swing.tree.DefaultMutableTreeNode;

import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;

public class TestCaseDefaultMutableTreeNode extends DefaultMutableTreeNode{

  private TestCaseInstruction testCaseInstruction;

  public TestCaseDefaultMutableTreeNode(TestCaseInstruction tci) {
    super();
    testCaseInstruction = tci;
    createNodes();
  }

  private void createNodes(){
    this.setUserObject(testCaseInstruction.getTestCaseID() );
    this.add( new DefaultMutableTreeNode("Elapsed: " + testCaseInstruction.getElapsedTime()) );
    this.add( new DefaultMutableTreeNode("Result: " + testCaseInstruction.getResult()) );
  }

  public TestCaseInstruction getTestCaseInstruction(){
    return testCaseInstruction;
  }

}
