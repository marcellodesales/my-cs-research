package br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners;

import javax.swing.JTextArea;

import br.ufpe.cin.stp.global.checkchange.LogLineListener;

/**
 * <p>Title: JTextAreaChangeble</p>
 * <p>Description: This Class extends JTextArea and implements ChangeStringListener. It will be used in Obsover Pattern.</p>
 * @author <a href="mailto:jeqca@cin.ufpe.br>José Elias Queiroga</a>
 * @author <a href="mailto:masj2@cin.ufpe.br>Marcello de Sales</a>
 * @version 1.0
 */
public class JTextAreaChangeble extends JTextArea implements LogLineListener{

  /**
 * Constructor method
 */
  public JTextAreaChangeble() {
    super();
  }

  /* Implementing the method defines in Interface
 * @see br.ufpe.cin.stp.global.checkchange.ChangeStringListener#checkChangeString(java.lang.String)
 */
  public void newLineAction(String line){
    this.append(line);
  }

  /* (non-Javadoc)
   * @see br.ufpe.cin.stp.global.checkchange.LogLineListener#clear()
   */
  public void clear() {
	this.setText("");
  }


}
