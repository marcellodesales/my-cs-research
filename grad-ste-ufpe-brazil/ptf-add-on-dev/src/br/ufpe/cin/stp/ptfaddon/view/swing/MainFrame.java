package br.ufpe.cin.stp.ptfaddon.view.swing;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import org.exolab.castor.xml.CastorException;

import br.ufpe.cin.stp.global.configuration.ResourceManager;
import br.ufpe.cin.stp.global.swingcomponent.AnimatedCursor;
import br.ufpe.cin.stp.global.swingcomponent.DialogFactory;
import br.ufpe.cin.stp.ptfaddon.controller.WizardFileController;
import br.ufpe.cin.stp.ptfaddon.model.Execution;
import br.ufpe.cin.stp.ptfaddon.model.PTFAddonFacade;
import br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionWizard;
import br.ufpe.cin.stp.ptfaddon.view.swing.execution.JWizardInternalFrame;
import br.ufpe.cin.stp.ptfaddon.view.swing.export.ExportTestCasesFrame;


/**
 * <p>Title: PTF Add-on V3.0</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Center of Informatics (CIn)-UFPE Inc.</p>
 * @author Elias Queiroga
 * @author Marcello Sales Jr.
 * @version 3.0
 */

public class MainFrame extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    private DialogFactory dialogCreator = DialogFactory.getInstance();
  JPanel contentPane;
  JMenuBar jMenuBar = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuopenConfigItem = new JMenuItem();
  JMenuItem jMenuopenChangeCoreID = new JMenuItem();
  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuNewExec = new JMenuItem();
  JToolBar jToolBar = new JToolBar();
  JButton execWizardOpenButton = new JButton();
  JButton execWizardNewButton = new JButton();
  JButton userGuideButton = new JButton();
  ImageIcon image1;
  ImageIcon image2;
  ImageIcon image3;
  JLabel statusBar = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JMenuItem jMenuMainExit = new JMenuItem();
  JMenu jMenu1 = new JMenu();
  JMenu jMenu2 = new JMenu();
  JMenuItem jMenuUserGuide = new JMenuItem();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  JMenuItem jMenuOpenExec = new JMenuItem();
  JMenuItem jMenuResultsFile = new JMenuItem();

  /**
   * Is the main container to add internal frames.
   **/
  JDesktopPane desktop = new JDesktopPane();
  static final Integer DOCLAYER = new Integer(5);
  static final Integer TOOLLAYER = new Integer(6);
  static final Integer HELPLAYER = new Integer(7);

  private Properties configToolproperties;
  private FileInputStream in;

  /**
   * isConfigured - Verify if the tool is configured correctly
   */
  protected static boolean isConfigured = false;

  /**
   * <code>executionFacade</code> is the main façade of the application.
   **/
  private PTFAddonFacade ptfaddonFacade;

  private static int xExec = 1;
  private static int yExec = 1;

  JButton exportResultsButton = new JButton();
  JButton openConfigButton = new JButton();
  JButton quitAppButton = new JButton();
  JButton buttonChangeCoreID = new JButton();
  JTextField fieldCoreID = new JTextField();

  public MainFrame() {
      System.out.println("Initiating main frame...");
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    this.maximize(this);

    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          quitApplication();
        }
      });

    try {
      this.jbInit();
      this.initPtfAddon();
    } catch(Exception e) {
      e.printStackTrace();
    }

    System.out.println("Main frame loaded...");
  }

  private void initPtfAddon(){
    this.desktop.addMouseListener(this);
    this.desktop.addMouseMotionListener(this);
    this.getContentPane().add(this.desktop);
  }

  //Component initialization
  private void jbInit() throws Exception  {
    image1 = new ImageIcon(ResourceManager.getInstance().getResource("images/execution-wizard-icon-open.png"));
    image2 = new ImageIcon(ResourceManager.getInstance().getResource("images/execution-wizard-icon-new.png"));
    image3 = new ImageIcon(ResourceManager.getInstance().getResource("images/userguide.png"));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    this.setTitle("PTF Add-on V3.0 / A CIn-STP Center of Informatics (CIn)-UFPE Project");
    this.addWindowListener(new MainFrame_this_windowAdapter(this));
    statusBar.setText("PTF Add-on V3.0 Build [CInSTP64] / CIn/UFPE/Center of Informatics (CIn)-UFPE Brazil STP Project - September, 2004");
    jMenuFile.setActionCommand("Main");
    jMenuFile.setText("Main");
    jMenuFile.setMnemonic(KeyEvent.VK_M);
    jMenuopenConfigItem.setText("Open Configuration");
    jMenuopenConfigItem.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/openconfig.png")));
    jMenuopenConfigItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke('C', java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuopenConfigItem.addActionListener(new MainFrame_jMenuopenConfigItem_ActionAdapter(this));

    jMenuopenChangeCoreID.setText("Change Tester Core ID");
    jMenuopenChangeCoreID.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/testerCoreID.png")));
    jMenuopenChangeCoreID.setAccelerator(javax.swing.KeyStroke.getKeyStroke('T', java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuopenChangeCoreID.addActionListener(new MainFrame_buttonChangeCoreID_actionAdapter(this));

    jMenuHelp.setText("Test Execution");
    jMenuHelp.setMnemonic(KeyEvent.VK_T);
    jMenuNewExec.setText("New Execution Wizard");
    jMenuNewExec.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/execution-wizard-icon-new.png")));
    jMenuNewExec.setAccelerator(javax.swing.KeyStroke.getKeyStroke('E', java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuNewExec.addActionListener(new MainFrame_jMenuNewExec_ActionAdapter(this));
    execWizardOpenButton.setIcon(image1);
    execWizardOpenButton.addActionListener(new MainFrame_jButton1_actionAdapter(this));
    execWizardOpenButton.setToolTipText("Open Execution Wizard");
    execWizardNewButton.setIcon(image2);
    execWizardNewButton.addActionListener(new MainFrame_jButton2_actionAdapter(this));
    execWizardNewButton.setToolTipText("New Execution Wizard");
    userGuideButton.setIcon(image3);
    userGuideButton.setToolTipText("User Guide");
    jMenuMainExit.setText("Quit");
    jMenuMainExit.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/exit.png")));
    jMenuMainExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke('Q', java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuMainExit.addActionListener(new MainFrame_jMenuMainExit_actionAdapter(this));
    jMenu1.setText("Results");
    jMenu1.setMnemonic(KeyEvent.VK_R);
    jMenu2.setText("Help");
    jMenu2.setMnemonic(KeyEvent.VK_H);
    jMenuUserGuide.setText("User Guide");
    jMenuUserGuide.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/userguide.png")));
    jMenuUserGuide.setAccelerator(javax.swing.KeyStroke.getKeyStroke('G', java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuHelpAbout.setText("About PTF Add-on");
    jMenuHelpAbout.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/about.gif")));
    jMenuHelpAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke('A', java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuHelpAbout.addActionListener(new MainFrame_jMenuHelpAbout_actionAdapter(this));
    jMenuOpenExec.setText("Open Execution Wizard");
    jMenuOpenExec.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/execution-wizard-icon-open.png")));
    jMenuOpenExec.setAccelerator(javax.swing.KeyStroke.getKeyStroke('D', java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuOpenExec.addActionListener(new MainFrame_jMenuOpenExec_actionAdapter(this));
    jMenuResultsFile.setText("Export Results");
    jMenuResultsFile.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/export-results.png")));
    jMenuResultsFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke('X', java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuResultsFile.addActionListener(new MainFrame_jMenuResultsFile_actionAdapter(this));
    contentPane.setBackground(new Color(159, 201, 255));
    exportResultsButton.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/export-results.png")));
    exportResultsButton.addActionListener(new MainFrame_exportResultsButton_actionAdapter(this));
    exportResultsButton.setToolTipText("Export Existing Log Results");
    openConfigButton.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/openconfig.png")));
    openConfigButton.addActionListener(new MainFrame_openConfigButton_actionAdapter(this));
    openConfigButton.setToolTipText("Open the configuration settings");
    quitAppButton.setToolTipText("Quit from PTF Add-on");
    quitAppButton.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/exit.png")));
    quitAppButton.addActionListener(new MainFrame_quitAppButton_actionAdapter(this));

    buttonChangeCoreID.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/testerCoreID.png")));
    buttonChangeCoreID.addActionListener(new MainFrame_buttonChangeCoreID_actionAdapter(this));
    buttonChangeCoreID.setToolTipText("Set a new Center of Informatics (CIn)-UFPE Core ID value to be used on the environment!");

    fieldCoreID.setToolTipText("The default Center of Informatics (CIn)-UFPE tester Core ID to be used on this add-on");

    fieldCoreID.setEnabled(true);
    fieldCoreID.setMaximumSize(new Dimension(55, 20));
    fieldCoreID.setPreferredSize(new Dimension(55, 20));
    fieldCoreID.setEditable(false);
    fieldCoreID.setText("");
    fieldCoreID.setColumns(7);
    jToolBar.add(openConfigButton);
    jToolBar.add(buttonChangeCoreID, null);
    jToolBar.addSeparator();
    jToolBar.add(fieldCoreID, null);
    jToolBar.addSeparator();
    jToolBar.add(execWizardNewButton, null);
    jToolBar.add(execWizardOpenButton, null);
    jToolBar.addSeparator();
    jToolBar.add(exportResultsButton, null);
    jToolBar.addSeparator();
    jToolBar.add(userGuideButton);
    jToolBar.add(quitAppButton, null);
    jToolBar.addSeparator();
    jMenuFile.add(jMenuopenConfigItem);
    jMenuFile.add(jMenuopenChangeCoreID);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuMainExit);
    jMenuHelp.add(jMenuNewExec);
    jMenuHelp.add(jMenuOpenExec);
    jMenuBar.add(jMenuFile);
    jMenuBar.add(jMenuHelp);
    jMenuBar.add(jMenu1);
    jMenuBar.add(jMenu2);
    jMenuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    this.setJMenuBar(jMenuBar);
    contentPane.add(jToolBar, BorderLayout.NORTH);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    jMenu2.add(jMenuUserGuide);
    jMenu2.addSeparator();
    jMenu2.add(jMenuHelpAbout);
    jMenu1.add(jMenuResultsFile);

    new AnimatedCursor(this.getToolkit(),this);
    this.ptfaddonFacade = PTFAddonFacade.getInstance();

    if (!this.ptfaddonFacade.isConfigurationOk() )
        this.showConfigurationSettingsError();
    else {
        //this.requestTesterCoreID();
    }
  }

  private void requestTesterCoreID(){
	    new Thread(){
	        public void run(){
	            try {
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            String coreID;
	            do{
	                coreID = dialogCreator.showInputDialog("Center of Informatics (CIn)-UFPE CoreID","Enter your Center of Informatics (CIn)-UFPE Core ID",new ImageIcon(ResourceManager.getInstance().getResource("images/testerID.png")));
	                if (coreID == null || coreID.length() != 6)
	                    dialogCreator.showErrorMessage("Invalid Center of Informatics (CIn)-UFPE Core ID: '"+(coreID==null ? "Not Entered" : coreID)+"'","Your Center of Informatics (CIn)-UFPE Core ID must be 6 length wide");
	            } while(coreID == null || coreID.length() != 6);
	            dialogCreator.showInfoMessage("Registered Center of Informatics (CIn)-UFPE CoreID","Center of Informatics (CIn)-UFPE Core ID "+coreID+" will be used on PTF Add-on Executions.\nOther Center of Informatics (CIn)-UFPE Core ID can be optionally entered.");
	            ptfaddonFacade.setRegisteredCoreID(coreID.toLowerCase());
                    fieldCoreID.setText(coreID.toLowerCase());
	        }
	    }.start();
  }

  /**
 * @created 05/09/2004 11:14:29
 * Shows the configuration settings error message as well as the configuration form to the tester.
 */
  private void showConfigurationSettingsError(){
      this.dialogCreator.showErrorMessage("PTF Add-on Main Configuration Settings", "Please set the PTF Add-on configuration properties.");
      this.openConfigurationForm();
  }

  /**
 * @created 05/09/2004 11:17:18
 * Opens the configuration form to the tester.
 */
  	private void openConfigurationForm(){
	      if (!this.ptfaddonFacade.getConfigToolOpen()) {
	          ConfigToolFrame configFrame = new ConfigToolFrame();
	          configFrame.setFrameIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/openconfig.png")));

	          Dimension dlgSize = configFrame.getPreferredSize();
	          Dimension frmSize = this.getSize();
	          Point loc = this.getLocation();
	          configFrame.setLocation(((frmSize.width - dlgSize.width) / 2), (frmSize.height - dlgSize.height) / 2 + loc.y);
	          configFrame.pack();
	          configFrame.show();

	          desktop.add(configFrame);
	          this.ptfaddonFacade.setConfigToolOpen(true);
	      }
  	}

  public void jMenuopenConfigItem_actionPerformed(ActionEvent e) {
      this.openConfigurationForm();
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      //jMenuopenConfigItem_actionPerformed(null);
    }
  }

  private void quitApplication(){
    if (this.dialogCreator.showConfirmDialog("Really quit from PTF Add-on?") == JOptionPane.OK_OPTION) {
    	ptfaddonFacade.removeAllExecutions();
    	System.exit(0);
    }
  }

  void jMenuMainExit_actionPerformed(ActionEvent e) {
    this.quitApplication();
  }

  void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    AboutBoxFrame dlg = new AboutBoxFrame(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }

  // This method minimizes a frame; the iconified bit is not affected
  private void minimize(JFrame frame) {
      int state = frame.getExtendedState();

      // Clear the maximized bits
      state &= ~JFrame.MAXIMIZED_BOTH;

      // Maximize the frame
      frame.setExtendedState(state);
  }

  // This method minimizes a frame; the iconified bit is not affected
  private void maximize(JFrame frame) {
      int state = frame.getExtendedState();

      // Set the maximized bits
      state |= JFrame.MAXIMIZED_BOTH;

      // Maximize the frame
      frame.setExtendedState(state);
  }

  void jMenuNewExec_actionPerformed(ActionEvent e) {
    try {
      if (this.ptfaddonFacade.isConfigurationOk()) {
        this.openExecutionInternalFrame(ptfaddonFacade.CreateNewExecution());

      }else this.showConfigurationSettingsError();

    }catch(Exception ex) {
       this.dialogCreator.showErrorMessage("Configuration File Error", "The configuration file could not be opened!\nCheck if the PTFADD-ON\\config\\ptfaddon.properties file exists!");
    }
  }

  void jMenuOpenExec_actionPerformed(ActionEvent e) {
      try {
        if (this.ptfaddonFacade.isConfigurationOk() ) {
          System.out.println("Opening the execution file...");
          JFileChooser chooser = DialogFactory.getInstance().getFileSingleSelectionChooser("./executions");
          chooser.setFileFilter(this.dialogCreator.new PTFExecutionFilter());
          chooser.setDialogTitle("Open a PTF Execution Wizard file...");
          int status = chooser.showOpenDialog(this);
          if (status == JFileChooser.APPROVE_OPTION) {
            try {
              System.out.println("Approved... now will create the execution...");
              ExecutionWizard ew = WizardFileController.getInstance().getExecutionWizardFile(chooser.getSelectedFile().getAbsolutePath());
              System.out.println(
                  "Execution created... now will open the internal frame...");
              this.openExecutionInternalFrame(PTFAddonFacade.getInstance().CreateNewExecution(ew));
            }
            catch (FileNotFoundException e1) {
                this.dialogCreator.showErrorMessage("Execution Wizard File Error",
                               "Error occurred while opening file: " +
                               e1.getMessage());
            }
            catch (CastorException e1) {
                this.dialogCreator.showErrorMessage("Execution Wizard File Error",
                               "Error occurred while opening file: " +
                               e1.getMessage());
            }
          }
        }
        else this.showConfigurationSettingsError();
      }
      catch (HeadlessException ex) {
      }
      catch (Exception ex) {
          this.dialogCreator.showErrorMessage("Information", "Cannot open the configuration file");
      }

    }

  private void openExecutionInternalFrame(Execution execution){
      System.out.println("Opening the execution internal frame...");
      JWizardInternalFrame executionFrame = null;
      // Create an internal frame
      boolean resizable = true;
      boolean closeable = true;
      boolean maximizable  = true;
      boolean iconifiable = true;

      try{
            executionFrame = new JWizardInternalFrame(execution,"Automated Test Execution Wizard", resizable, closeable, maximizable, iconifiable);
            System.out.println("Internal frame instantiated...");
            Dimension dlgSize = executionFrame.getPreferredSize();
            Dimension frmSize = getSize();
            Point loc = getLocation();
            xExec += 2;
            yExec += 2;
            executionFrame.setLocation((frmSize.width - dlgSize.width) / xExec, (frmSize.height - dlgSize.height) / yExec);
            executionFrame.setFrameIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/execution-wizard-icon.png")));
            desktop.add(executionFrame, HELPLAYER);
         }catch(IOException exc){
             this.dialogCreator.showErrorMessage("Execution Wizard Error","Error occurred while opening wizard: "+exc.getMessage());
         } catch (Exception e){
             this.dialogCreator.showErrorMessage("Internal Application Error","Error occurred while opening wizard: "+e.getMessage());
            e.printStackTrace();
         }
  }

  /**
   * mouseDragged
   *
   * @param e MouseEvent
   */
  public void mouseDragged(MouseEvent e) {
  }

  /**
   * mouseMoved
   *
   * @param e MouseEvent
   */
  public void mouseMoved(MouseEvent e) {
  }

  /**
   * mouseClicked
   *
   * @param e MouseEvent
   */
  public void mouseClicked(MouseEvent e) {
  }

  /**
   * mouseEntered
   *
   * @param e MouseEvent
   */
  public void mouseEntered(MouseEvent e) {
  }

  /**
   * mouseExited
   *
   * @param e MouseEvent
   */
  public void mouseExited(MouseEvent e) {
  }

  /**
   * mousePressed
   *
   * @param e MouseEvent
   */
  public void mousePressed(MouseEvent e) {
  }

  /**
   * mouseReleased
   *
   * @param e MouseEvent
   */
  public void mouseReleased(MouseEvent e) {
  }

  /**
   * actionPerformed
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
  }

  void jButton2_actionPerformed(ActionEvent e) {
    this.jMenuNewExec_actionPerformed(e);
  }

  void jButton1_actionPerformed(ActionEvent e) {
      this.jMenuOpenExec_actionPerformed(e);
  }

  void openConfigButton_actionPerformed(ActionEvent e) {
    this.openConfigurationForm();
  }

  void quitAppButton_actionPerformed(ActionEvent e) {
    this.quitApplication();
  }

  void jMenuResultsFile_actionPerformed(ActionEvent e) {
      System.out.println("Opening the export internal frame...");
      ExportTestCasesFrame exportFrame = null;

      exportFrame = new ExportTestCasesFrame();
	    System.out.println("Export frame instantiated...");
	    Dimension dlgSize = exportFrame.getPreferredSize();
	    Dimension frmSize = getSize();
	    Point loc = getLocation();
	    xExec += 2;
	    yExec += 5;
	    exportFrame.setLocation((frmSize.width - dlgSize.width) / xExec, (frmSize.height - dlgSize.height) / yExec);
	    exportFrame.setFrameIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/export-results.png")));

	    exportFrame.show();
        desktop.add(exportFrame, HELPLAYER);
  }

  void exportResultsButton_actionPerformed(ActionEvent e) {
    this.jMenuResultsFile_actionPerformed(e);
  }

  void buttonChangeCoreID_actionPerformed(ActionEvent e) {
    this.requestTesterCoreID();
  }

  void this_windowClosing(WindowEvent e) {
  }
}


class MainFrame_jMenuopenConfigItem_ActionAdapter implements ActionListener {
  MainFrame adaptee;

  MainFrame_jMenuopenConfigItem_ActionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuopenConfigItem_actionPerformed(e);
  }
}

class MainFrame_jMenuNewExec_ActionAdapter implements ActionListener {
  MainFrame adaptee;

  MainFrame_jMenuNewExec_ActionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuNewExec_actionPerformed(e);
  }
}

class MainFrame_jMenuMainExit_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jMenuMainExit_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuMainExit_actionPerformed(e);
  }
}

class MainFrame_jMenuHelpAbout_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jMenuHelpAbout_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

class MainFrame_jMenuOpenExec_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jMenuOpenExec_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuOpenExec_actionPerformed(e);
  }
}

class MainFrame_jButton2_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButton2_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class MainFrame_jButton1_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jButton1_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class MainFrame_openConfigButton_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_openConfigButton_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.openConfigButton_actionPerformed(e);
  }
}

class MainFrame_quitAppButton_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_quitAppButton_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.quitAppButton_actionPerformed(e);
  }
}

class MainFrame_jMenuResultsFile_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_jMenuResultsFile_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuResultsFile_actionPerformed(e);
  }
}

class MainFrame_exportResultsButton_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_exportResultsButton_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.exportResultsButton_actionPerformed(e);
  }
}

class MainFrame_buttonChangeCoreID_actionAdapter implements java.awt.event.ActionListener {
  MainFrame adaptee;

  MainFrame_buttonChangeCoreID_actionAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.buttonChangeCoreID_actionPerformed(e);
  }
}

class MainFrame_this_windowAdapter extends java.awt.event.WindowAdapter {
  MainFrame adaptee;

  MainFrame_this_windowAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void windowClosing(WindowEvent e) {
    adaptee.this_windowClosing(e);
  }
}
