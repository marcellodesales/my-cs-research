package br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import br.ufpe.cin.stp.global.checkchange.NewTestCaseListener;
import br.ufpe.cin.stp.global.configuration.ResourceManager;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;
import br.ufpe.cin.stp.ptfaddon.view.swing.execution.TestCaseDefaultMutableTreeNode;

public class JTreeTestCaseListener extends JTree implements NewTestCaseListener{

    private DefaultMutableTreeNode rootNode;
    private DefaultTreeModel treeModel;
    private DefaultTreeCellRenderer render;

    public JTreeTestCaseListener() {
      super();
      this.render = new DefaultTreeCellRenderer();
      this.render.setOpenIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/test.gif")));
      this.render.setClosedIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/test.gif")));
      this.render.setLeafIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/verify.png")));
      this.setCellRenderer(this.render);
      this.setModel(null);
    }

    private DefaultMutableTreeNode getTestCaseNode(TestCaseInstruction tci){
      return new TestCaseDefaultMutableTreeNode(tci);
    }

    public void newTestCaseAction(TestCaseInstruction tci){
      if (this.getModel() == null){
      	 setModelDefault();
         this.setModel(treeModel);      	 
      }
      rootNode.add(getTestCaseNode(tci));
    }

	/* (non-Javadoc)
	 * @see br.ufpe.cin.stp.global.checkchange.NewTestCaseListener#clear()
	 */
	public void clear() {
	  this.setModel(null);		
	}
	
	private void setModelDefault(){
		rootNode = new DefaultMutableTreeNode("Test Case List");
		treeModel = new DefaultTreeModel(rootNode);
    }



}


