package br.ufpe.cin.stp.ptfaddon.view.swing.execution;

import javax.swing.tree.DefaultMutableTreeNode;

import br.ufpe.cin.stp.ptfaddon.model.log.ExceptionInstruction;

public class ExceptionDefaultMutableTreeNode extends DefaultMutableTreeNode{


  private ExceptionInstruction exceptionInstruction;

  public ExceptionDefaultMutableTreeNode(ExceptionInstruction exi) {
    super(exi.getContent());
    this.exceptionInstruction = exi;
    createNodes();
  }

  private void createNodes(){
    //this.setUserObject(this.exceptionInstruction.toString() );
    for (int i=0; i< exceptionInstruction.getExceptionContentList().size(); i++){
      this.add(new DefaultMutableTreeNode( exceptionInstruction.getExceptionContentList().get(i) ));
    }
  }


}
