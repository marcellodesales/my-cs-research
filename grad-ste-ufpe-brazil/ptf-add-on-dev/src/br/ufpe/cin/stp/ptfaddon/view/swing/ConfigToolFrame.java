package br.ufpe.cin.stp.ptfaddon.view.swing;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.TableColumn;

import br.ufpe.cin.stp.global.configuration.ResourceManager;
import br.ufpe.cin.stp.global.swingcomponent.DialogFactory;
import br.ufpe.cin.stp.global.swingcomponent.layout.VerticalFlowLayout;
import br.ufpe.cin.stp.global.swingcomponent.table.ConfigPropertiesTableModel;
import br.ufpe.cin.stp.ptfaddon.model.ExecutionException;
import br.ufpe.cin.stp.ptfaddon.model.PTFAddonFacade;

/**
 * @author José Elias Queiroga <BR>
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 18/07/2004 12:41:08
 */
public class ConfigToolFrame extends JInternalFrame{

    private DialogFactory dialogCreator = DialogFactory.getInstance();
    JPanel configPane = new JPanel();
  JPanel btnPane = new JPanel();
  VerticalFlowLayout layout = new VerticalFlowLayout();
  JButton btnSave = new JButton();
  JButton btnClose = new JButton();
  JTable tableConfig = new JTable( );
  JFileChooser fileOpenchooser = new JFileChooser();
  BorderLayout borderLayout1 = new BorderLayout();

  private PTFAddonFacade ptfAddonFacade = PTFAddonFacade.getInstance();
  JScrollPane jScrollPane1 = new JScrollPane();
  Border border1;
  TitledBorder titledBorder1;
  JPanel jPanel1 = new JPanel();
  JButton btnOpenFile = new JButton();

    private void jbInit() throws Exception {

        this.setPreferredSize(new Dimension(550,200));
        this.setSize(new Dimension(550,200));

    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    titledBorder1 = new TitledBorder(border1,"PTF Add-on Configuration Keys");
    btnPane.setLayout(layout);
    btnSave.setText("Save");
    btnSave.addActionListener(new ConfigToolFrame_btnSave_actionAdapter(this));
    btnClose.setDoubleBuffered(false);
    btnClose.setRolloverEnabled(false);
    btnClose.setText("    Close   ");
    btnClose.addActionListener(new ConfigToolFrame_btnClose_actionAdapter(this));

    configPane.setLayout(borderLayout1);
    this.setClosable(true);
    this.setClosed(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setMaximizable(true);
    this.setMaximum(false);
    this.setResizable(true);
    this.setTitle("Tool Settings");
    this.setRequestFocusEnabled(true);
    this.setSelected(false);
    this.addInternalFrameListener(new ConfigToolFrame_this_internalFrameAdapter(this));
    configPane.setBorder(titledBorder1);
    jScrollPane1.setAutoscrolls(true);
    btnOpenFile.setText("Open File");
    btnOpenFile.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/openFile.png")));
    btnOpenFile.addActionListener(new ConfigToolFrame_btnOpenFile_actionAdapter(this));
    jPanel1.setPreferredSize(new Dimension(87, 30));
    this.getContentPane().add(btnPane, BorderLayout.EAST);
    btnPane.add(btnSave, null);

    tableConfig.setModel(new ConfigPropertiesTableModel( ptfAddonFacade.getToolConfigFile() ));

    btnPane.add(btnClose, null);
    this.getContentPane().add(configPane, BorderLayout.CENTER);
    configPane.add(jScrollPane1,  BorderLayout.CENTER);
    configPane.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(btnOpenFile, null);
    jScrollPane1.getViewport().add(tableConfig, null);

    tableConfig.setIntercellSpacing(new Dimension(1, 1));
    tableConfig.addColumn( new TableColumn(10, 10));
    }

  void btnSave_actionPerformed(ActionEvent e) {
      try {
          ptfAddonFacade.saveToolConfigFile(((ConfigPropertiesTableModel)this.tableConfig.getModel()).getPropertiesLines());
      }catch (ExecutionException ex1) {
          this.dialogCreator.showErrorMessage("Configuration File Settings Error",ex1.getMessage());
      }
      if (!this.ptfAddonFacade.isConfigurationOk())
          this.dialogCreator.showErrorMessage("Incorrect Configuration Settings","The PTF root path specification is incorrect!");
      else this.dialogCreator.showInfoMessage("Configuration File Settings","This configuration information was sucessfully saved!");
  }

  /**
   * ConfigToolFrame
   */
  public ConfigToolFrame() {
    try {
      jbInit();
    }catch (Exception ex) {
    }
  }

  void btnClose_actionPerformed(ActionEvent e) {
   this.ptfAddonFacade.setConfigToolOpen(false);
   this.dispose();
  }

  void this_internalFrameClosing(InternalFrameEvent e) {
     this.ptfAddonFacade.setConfigToolOpen(false);
  }

  void btnOpenFile_actionPerformed(ActionEvent e) {
    JFileChooser chooser = this.dialogCreator.getFileSingleSelectionChooser();
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    chooser.setDialogTitle("Choose a file or path to be indicated...");
    int status = chooser.showDialog(this,"Select");
    if (status == JFileChooser.APPROVE_OPTION) {
        int row = this.tableConfig.getSelectedRow();
        ConfigPropertiesTableModel model = (ConfigPropertiesTableModel)tableConfig.getModel();
        String key = (String)model.getValueAt(row,0);
        //it's not necessary to change the back slashes because the property object organizes it!
        String path = chooser.getSelectedFile().getAbsolutePath();

        if (key.equals("PHONETEST_HOME"))
            if (!this.ptfAddonFacade.isPTFHomeCorrect(path))
                this.dialogCreator.showErrorMessage("Incorrect PTF root path","The specified path\n"+path+"\ndoes not seem to be the PTF root directory.");
            else this.dialogCreator.showInfoMessage("Correct PTF root path","The specified PTF root directory is correct!");

        model.setValueAt(path,row,1);
	    this.tableConfig.repaint();
    }
  }
}


class ConfigToolFrame_btnSave_actionAdapter implements java.awt.event.ActionListener {
  ConfigToolFrame adaptee;

  ConfigToolFrame_btnSave_actionAdapter(ConfigToolFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSave_actionPerformed(e);
  }
}

class ConfigToolFrame_btnClose_actionAdapter implements java.awt.event.ActionListener {
  ConfigToolFrame adaptee;

  ConfigToolFrame_btnClose_actionAdapter(ConfigToolFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnClose_actionPerformed(e);
  }
}

class ConfigToolFrame_this_internalFrameAdapter extends javax.swing.event.InternalFrameAdapter {
  ConfigToolFrame adaptee;

  ConfigToolFrame_this_internalFrameAdapter(ConfigToolFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void internalFrameClosing(InternalFrameEvent e) {
    adaptee.this_internalFrameClosing(e);
  }
}

class ConfigToolFrame_btnOpenFile_actionAdapter implements java.awt.event.ActionListener {
  ConfigToolFrame adaptee;

  ConfigToolFrame_btnOpenFile_actionAdapter(ConfigToolFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOpenFile_actionPerformed(e);
  }
}



