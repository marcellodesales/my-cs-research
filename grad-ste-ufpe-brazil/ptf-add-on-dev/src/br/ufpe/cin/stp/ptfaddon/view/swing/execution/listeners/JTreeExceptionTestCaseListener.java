package br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import br.ufpe.cin.stp.global.checkchange.NewTestCaseListener;
import br.ufpe.cin.stp.ptfaddon.model.log.ExceptionInstruction;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;
import br.ufpe.cin.stp.ptfaddon.view.swing.execution.ExceptionDefaultMutableTreeNode;

public class JTreeExceptionTestCaseListener extends JTree implements NewTestCaseListener{


  private DefaultMutableTreeNode rootNode;
  private DefaultTreeModel treeModel;

  public JTreeExceptionTestCaseListener() {
    super();
    clear();
  }

  private DefaultMutableTreeNode getTestCaseNode(ExceptionInstruction tci){
    return new ExceptionDefaultMutableTreeNode(tci);
  }

  public void newTestCaseAction(TestCaseInstruction tci){

    if (this.getModel() == null){
    	setModelDefault();
    	this.setModel(treeModel);
    }
    if (tci.getExceptionInstructions().length > 0){
        for (int i = 0; i < tci.getExceptionInstructions().length; i++)
            rootNode.add( getTestCaseNode(tci.getExceptionInstructions()[i]) );
        this.updateUI();
     }

  }

  /* (non-Javadoc)
   *  @see br.ufpe.cin.stp.global.checkchange.NewTestCaseListener#clear()
   */
  public void clear() {
    this.setModel(null);
  }
  
  private void setModelDefault(){
  	rootNode = new DefaultMutableTreeNode("Exceptions");
  	treeModel = new DefaultTreeModel(rootNode);
  }



}
