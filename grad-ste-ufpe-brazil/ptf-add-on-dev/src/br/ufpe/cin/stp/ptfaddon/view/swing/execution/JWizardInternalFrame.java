package br.ufpe.cin.stp.ptfaddon.view.swing.execution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import br.ufpe.cin.stp.global.ListFilter;
import br.ufpe.cin.stp.global.checkchange.ExecutionFinalizationListener;
import br.ufpe.cin.stp.global.configuration.ResourceManager;
import br.ufpe.cin.stp.global.filemanager.FileManager;
import br.ufpe.cin.stp.global.swingcomponent.DialogFactory;
import br.ufpe.cin.stp.global.swingcomponent.layout.VerticalFlowLayout;
import br.ufpe.cin.stp.global.swingcomponent.list.CheckableItem;
import br.ufpe.cin.stp.global.swingcomponent.list.JCheckableList;
import br.ufpe.cin.stp.global.swingcomponent.table.PropertiesTableModel;
import br.ufpe.cin.stp.ptfaddon.controller.WizardFileController;
import br.ufpe.cin.stp.ptfaddon.model.Execution;
import br.ufpe.cin.stp.ptfaddon.model.ExecutionException;
import br.ufpe.cin.stp.ptfaddon.model.PTFAddonFacade;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite;
import br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionWizard;
import br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners.JPanelTestCaseListener;
import br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners.JProgressBarTestCaseListener;
import br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners.JTextAreaChangeble;
import br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners.JTextAreaStepsTestCaseListener;
import br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners.JTreeExceptionTestCaseListener;
import br.ufpe.cin.stp.ptfaddon.view.swing.execution.listeners.JTreeTestCaseListener;

/**
 * @author José Elias Queiroga <BR>
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 18/07/2004 12:41:08
 */
public class JWizardInternalFrame extends JInternalFrame implements TreeSelectionListener, ExecutionFinalizationListener{

    private DialogFactory dialogCreator = DialogFactory.getInstance();
    private PTFAddonFacade ptfAddonFacade = PTFAddonFacade.getInstance();
    private Execution execution;
    private FileManager fileManager;
    /**
     * testListSaved defines if the user has saved the testCaseList settings. The
     * user can only follow to the config file tab if this value is true;
     */
    private boolean testListFileSaved;
    /**
     * configListSaved defines if the config file was saved after opened.
     */
    private boolean configFileSaved;

    JTextAreaChangeble logTextArea = new JTextAreaChangeble();
    JScrollPane logScroll = new JScrollPane(logTextArea);
    BorderLayout borderLayout1 = new BorderLayout();
    JTabbedPane paneTab = new JTabbedPane();
    JPanel ExecutePane = new JPanel();
    JButton btnStop = new JButton();
    JToolBar btnBar = new JToolBar();
    JButton btnStart = new JButton();
    JSplitPane Slip1 = new JSplitPane();
    JProgressBarTestCaseListener ExecutionProgress = new JProgressBarTestCaseListener();
    JLabel statusBar = new JLabel();

    JPanelTestCaseListener panelStatusListener = new JPanelTestCaseListener();
    JTreeTestCaseListener treeTest;
    JTreeExceptionTestCaseListener treeException;

    BorderLayout borderLayout2 = new BorderLayout();
    JPanel paneTestList = new JPanel();
    JCheckableList checkableList = new JCheckableList();
    JButton btnOpenTestList = new JButton();
    JPanel panelBtn = new JPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    CheckableItem[] aux ;
    JPanel paneConfig = new JPanel();
    JTable tableConfig = new JTable( );
    JButton btnOpenCfg = new JButton();
    JButton btnSaveCfg = new JButton();
    JPanel paneGeneralCfg = new JPanel();
    JLabel lblBuild = new JLabel();
    JTextField fieldBuild = new JTextField();
    JLabel lblPlatform = new JLabel();
    JLabel lblPlan = new JLabel();
    JTextField fieldTestPlan = new JTextField();
    JLabel lblCycleGroup = new JLabel();
    JTextField fieldCycleGroup = new JTextField();
    JLabel lblCore = new JLabel();
    JTextField fieldCoreID = new JTextField();
    JButton btnNextGeneral = new JButton();
    BorderLayout borderLayout3 = new BorderLayout();
    JButton btnUploadJar = new JButton();
    JButton btnNextTestList = new JButton();
    JPanel jPanel1 = new JPanel();
    BorderLayout borderLayout4 = new BorderLayout();
    JButton btnNextConfig = new JButton();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();
    BorderLayout borderLayout5 = new BorderLayout();
    JScrollPane scrollConfigList = new JScrollPane();
    JButton btnSaveTestList = new JButton();
    JPanel paneResults = new JPanel();
    BorderLayout borderLayout6 = new BorderLayout();
    JToolBar btnExecutionBar = new JToolBar();
    JButton btnSaveExecution = new JButton();
    JPanel jPanel6 = new JPanel();
    Border border1;
    TitledBorder titledBorder2;
    JPanel jPanel7 = new JPanel();
    Border border2;
    TitledBorder titledBorder3;

    JSplitPane splitExecDetail = new JSplitPane();
    JSplitPane splitStepsException = new JSplitPane();
    JSplitPane jSplitPane3 = new JSplitPane();
    JTextAreaStepsTestCaseListener textAreaSteps = new JTextAreaStepsTestCaseListener();
    JPanel paneDetail = new JPanel();
    VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
    VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
    VerticalFlowLayout verticalFlowLayout3 = new VerticalFlowLayout();
    JPanel panelSteps = new JPanel();
    Border border3;
    TitledBorder titledBorder4;
    BorderLayout borderLayout7 = new BorderLayout();
    Border border4;
    JPanel panelExceptionView = new JPanel();
    Border border5;
    TitledBorder titledBorder5;
    BorderLayout borderLayout8 = new BorderLayout();
    Border border6;
    TitledBorder titledBorder6;
    JPanel panelLog = new JPanel();
    Border border7;
    TitledBorder titledBorder7;
    BorderLayout borderLayout10 = new BorderLayout();
    BorderLayout borderLayout11 = new BorderLayout();

    JPanel panelTestListFilterBox = new JPanel();
    JPanel panelFilter = new JPanel();
    JPanel panelTestListBox = new JPanel();
    BorderLayout borderLayout12 = new BorderLayout();
    Border border8;
    TitledBorder titledBorder8;
    BorderLayout borderLayout13 = new BorderLayout();
    Border border9;
    TitledBorder titledBorder9;
    JCheckBox checkBoxSelectTests = new JCheckBox();
    JTextField textFieldFilter = new JTextField();
    JScrollPane scrollPaneExecutionSteps = new JScrollPane();
    BorderLayout borderLayout9 = new BorderLayout();
    ButtonGroup platformTypeButtonGroup = new ButtonGroup();
    JScrollPane jScrollPane3 = new JScrollPane();
    JScrollPane scrollPaneTreeException = new JScrollPane();
  JPanel jPanel5 = new JPanel();
  JTextField jarTestCasesField = new JTextField();
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton2 = new JRadioButton();
  JTextField fieldTestCaseList = new JTextField();
  JLabel labelJarFile = new JLabel();
  JLabel labelTestList = new JLabel();
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout14 = new BorderLayout();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  BorderLayout borderLayout15 = new BorderLayout();
  Border border10;
  TitledBorder titledBorder10;
  Border border11;
  TitledBorder titledBorder11;
  JTextField fieldMainConfigFile = new JTextField();
  JTextField txtFieldHardware = new JTextField();
  JLabel labelTafEnabled = new JLabel();
  JPanel jPanel10 = new JPanel();
  JButton btnOpenFile = new JButton();
  JButton btnOpenEditor = new JButton();
  GridLayout gridLayout1 = new GridLayout(0,2);
  JLabel labelConfigFile = new JLabel();

    public JWizardInternalFrame(Execution execution, String prmTitle, boolean prm1, boolean prm2,  boolean prm3,  boolean prm4) throws IOException {
        super(prmTitle, prm1, prm2, prm3, prm4);

        System.out.println("Instantiating wizard internal frame...");
        this.execution = execution;
        this.fileManager = FileManager.getInstance();
        System.out.println("file manager set...");
        treeTest = new JTreeTestCaseListener();
        treeException = new JTreeExceptionTestCaseListener();
        System.out.println("tree list listeners set...");

        logTextArea.setEditable(false);
        logTextArea.setColumns(50);
        logTextArea.setAutoscrolls(true);
        this.getContentPane().setLayout( null );
        this.getContentPane().add( logScroll, BorderLayout.CENTER);
        pack();
        setVisible(true);

        System.out.println("will initiate GUI...");
        try {
          jbInit();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println("GUI Instantiated...");
        System.out.println("Verifying if it's the case to open new or created frame");
        if (this.execution.getConfiguration().getPreparado()){
            System.out.println("it's ok... open existing wizard...");
            this.openWizard();
            System.out.println("Wizard opened existing file...");
        } else {
            System.out.println("Will open a new wizard...");
            this.openEmptyWizard();
            System.out.println("Opened new wizard empty ...");
        }
    }

    private void openEmptyWizard(){
        this.paneTab.setEnabledAt(1,false);
        this.paneTab.setEnabledAt(2,false);
        this.paneTab.setEnabledAt(3,false);
        this.paneTab.setEnabledAt(4,false);
    }

    private void openWizard(){
      this.setTitle("Automated Execution for "+this.execution.getInfoTest().getPlatformType()+" :: "+this.execution.getInfoTest().getHardware()+" :: "+this.execution.getInfoTest().getBuild());
      this.txtFieldHardware.setText(this.execution.getInfoTest().getHardware());
      this.fieldBuild.setText(this.execution.getInfoTest().getBuild());
        this.fieldCoreID.setText(this.execution.getInfoTest().getCoreid());
        this.fieldCycleGroup.setText(this.execution.getInfoTest().getCycle());
        this.fieldTestPlan.setText(this.execution.getInfoTest().getTestplan());
        if (this.execution.getInfoTest().getPlatformType().equals("P2K"))
          this.jRadioButton1.setSelected(true);
        else this.jRadioButton2.setSelected(true);

        this.jarTestCasesField.setText(this.execution.getJarTestCasesPath());
        this.fieldTestCaseList.setText(this.execution.getTestList().getPathList());

        this.fieldMainConfigFile.setText(this.execution.getConfiguration().getPathConfig());

        if (!this.ptfAddonFacade.isExecutionWizardLinksOK(this.execution)){
            this.dialogCreator.showErrorMessage("Execution Wizard Needed Files Error","Check if the indicated files exist on the Test List Builder and Configuration Tabs!");
        } else {               
        	this.btnOpenTestList_actionPerformed(null);
        	this.btnOpenCfg_actionPerformed(null);
        }
    	this.paneTab.setEnabledAt(4,false);
    	this.showExecutionTab();
    	this.btnSaveExecution.setEnabled(true);
        
        this.setTAFValue(this.execution.isTAFEnabled());

        this.dialogCreator.showWarningMessage("Automate Tests Pre-requirements","Verify if the configurations on this wizard are correct in order to proceed...");

        this.updateCheckConfigFile(this.execution.getConfiguration().getPathConfig());
        this.updateCheckJarFile(this.execution.getJarTestCasesPath());
        this.updateCheckTestListFile(this.execution.getTestList().getPathList());
    }

    private void updateCheckJarFile(String jarFilePath){
        String jarFileIcon = "configNotOk.gif";
        try {
            if (this.ptfAddonFacade.fileExists(jarFilePath))
                if (this.ptfAddonFacade.isAValidTestCasesJar(jarFilePath)){
                    jarFileIcon = "configOK.gif";
                    this.labelJarFile.setToolTipText("The test cases jar file exists and is valid!");
                } else this.labelJarFile.setToolTipText("The test cases jar file exists, but is not valid!");
            else this.labelJarFile.setToolTipText("The test cases jar file exists, but is not valid!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.labelJarFile.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/"+jarFileIcon)));
    }

    private void updateCheckTestListFile(String testListFilePath){
        String fileIcon = "configNotOk.gif";;
        try {
            if (this.ptfAddonFacade.fileExists(testListFilePath))
                 if (this.ptfAddonFacade.isValidTestCaseList(testListFilePath)){
                     fileIcon = "configOK.gif";
                     this.labelTestList.setToolTipText("The test list file exists and it is valid!");
                 } else this.labelTestList.setToolTipText("The test list file exists, but it is not valid!");

            else this.labelTestList.setToolTipText("The test list file does not exist!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.labelTestList.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/"+fileIcon)));
    }

    private void updateCheckConfigFile(String configFilePath){
        String fileIcon = "configNotOk.gif";
        if (this.ptfAddonFacade.fileExists(configFilePath)){
            fileIcon = "configOK.gif";
            this.labelConfigFile.setToolTipText("The configuration file exists!");
        } else this.labelConfigFile.setToolTipText("The configuration file does not exist!");

        this.labelConfigFile.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/"+fileIcon)));
    }

    private void jbInit() throws Exception {
        //Sets the width and height to the wizard frame
        this.setBounds(20,20,580,400);

        border2 = BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151));
        titledBorder3 = new TitledBorder(border2,"Test Central Information");
        border3 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
        titledBorder4 = new TitledBorder(BorderFactory.createEmptyBorder(),"Execution Steps View");
        border4 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(178, 178, 178));
        border5 = BorderFactory.createEmptyBorder();
        titledBorder5 = new TitledBorder(BorderFactory.createEmptyBorder(),"Exception View");
        border6 = BorderFactory.createEmptyBorder();
        titledBorder6 = new TitledBorder(BorderFactory.createEmptyBorder(),"Online Execution Status View");
        border7 = BorderFactory.createEmptyBorder();
        //titledBorder7 = new TitledBorder(new MatteBorder(new ImageIcon(ResourceManager.getInstance().getResource("images/log.png"))),"Log View");
        titledBorder7 = new TitledBorder(BorderFactory.createEmptyBorder(),"Log View");
        border8 = BorderFactory.createEmptyBorder();
        titledBorder8 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151)),"Test List Files");
        border9 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
        titledBorder9 = new TitledBorder(border9,"Test Cases List");
        border10 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
	    titledBorder10 = new TitledBorder(border10,"Configuration File Key Values");
	    border11 = BorderFactory.createEmptyBorder();
	    titledBorder11 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151)),"Configuration File Path");
	    this.showStatusInfo(0);
        border1 = BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151));
        titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"Platform Information");
        this.getContentPane().setLayout(borderLayout1);
        btnStop.setToolTipText("Stop the execution of the automated tests.");
        btnStop.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/stop.gif")));
        btnStop.setSelected(false);
        btnStop.addActionListener(new JWizardInternalFrame_btnStop_actionAdapter(this));
        btnStop.addActionListener(new JWizardInternalFrame_btnStop_actionAdapter(this));
        btnBar.setOrientation(JToolBar.HORIZONTAL);
        btnBar.setOpaque(true);
//        btnBar.setMargin(new Insets(4, 0, 0, 0));
        btnStart.setToolTipText("Start Executing the selected test cases");
        btnStart.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/run.gif")));
        btnStart.setText("");
        btnStart.addActionListener(new JWizardInternalFrame_btnStart_actionAdapter(this));
        Slip1.setDividerSize(4);
        Slip1.setRightComponent(logTextArea);
        btnSaveCfg.addActionListener(new JWizardInternalFrame_btnSaveCfg_actionAdapter(this));
        ExecutionProgress.setAlignmentY( (float) 0.5);
        ExecutionProgress.setToolTipText("The progress of the automated tests execution");
        ExecutionProgress.setMaximum(10);
        ExecutionProgress.setMinimum(0);
        treeTest.setToolTipText("Executed Test Cases List with the result and elapsed time");
        treeTest.setEditable(true);
        treeTest.addMouseListener(new JWizardInternalFrame_treeTest_mouseAdapter(this));
        ExecutePane.setLayout(borderLayout2);
        ExecutePane.setFont(new java.awt.Font("MS Sans Serif", 0, 11));
        ExecutePane.setDebugGraphicsOptions(0);
        ExecutePane.setOpaque(true);
        ExecutePane.setPreferredSize(new Dimension(170, 124));
        ExecutePane.setToolTipText("The automated tests execution step");
        paneTestList.setEnabled(true);
        paneTestList.setVerifyInputWhenFocusTarget(true);
        paneTestList.setLayout(borderLayout3);
        this.setNormalBounds(new Rectangle(50, 50, 580, 400));
        this.setResizable(true);
        this.setTitle("Automated Test Execution Wizard");
        this.setDebugGraphicsOptions(0);
        this.setMinimumSize(new Dimension(59, 26));
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(220, 200));
        this.setVisible(true);
        this.setSelected(true);
        this.setResizable(true);
        this.setMaximizable(true);


    this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
      public void internalFrameClosing(InternalFrameEvent e) {
        this_internalFrameClosing(e);
      }
      public void internalFrameOpened(InternalFrameEvent e) {
        this_internalFrameOpened(e);
      }
    });
        btnOpenTestList.setEnabled(false);
        btnOpenTestList.setToolTipText("Opens the .txt test cases list file");
        btnOpenTestList.setActionCommand("Open from File");
        btnOpenTestList.setRolloverEnabled(false);
        btnOpenTestList.setText("Open List");
        btnOpenTestList.addActionListener(new JWizardInternalFrame_btnOpenTestList_actionAdapter(this));
        paneConfig.setLayout(borderLayout4);
        tableConfig.setDoubleBuffered(false);
        tableConfig.setIntercellSpacing(new Dimension(1, 1));
        btnOpenCfg.setToolTipText("Open The main PTF-TAF configuration file");
        btnOpenCfg.setText("Open Config File");
        btnOpenCfg.addActionListener(new JWizardInternalFrame_btnOpenCfg_actionAdapter(this));
        btnSaveCfg.setEnabled(false);
        btnSaveCfg.setPreferredSize(new Dimension(59, 25));
        btnSaveCfg.setRequestFocusEnabled(true);
        btnSaveCfg.setToolTipText("Save the key-value table state");
        btnSaveCfg.setText("Save Settings");
        paneGeneralCfg.setLayout(borderLayout5);
        lblBuild.setText("Build:");
        lblBuild.setBounds(new Rectangle(103, 52, 26, 15));
        fieldBuild.setToolTipText("The build of the hardware to be tested");
        fieldBuild.setText("");
        fieldBuild.setBounds(new Rectangle(137, 48, 208, 20));
        lblPlatform.setMinimumSize(new Dimension(54, 15));
        lblPlatform.setText("Hardware:");
        lblPlatform.setBounds(new Rectangle(80, 20, 52, 15));
        lblPlan.setText("Test Plan:");
        lblPlan.setBounds(new Rectangle(62, 79, 45, 15));
        fieldTestPlan.setToolTipText("The description of the Center of Informatics (CIn)-UFPE Test Central test plan");
        fieldTestPlan.setText("");
        fieldTestPlan.setBounds(new Rectangle(110, 76, 242, 21));
        lblCycleGroup.setPreferredSize(new Dimension(98, 15));
    lblCycleGroup.setText("Cycle Group 2/Band:");
    lblCycleGroup.setBounds(new Rectangle(8, 52, 115, 21));
//    lblCycle.setText("Cycle group 2/Band:");
//        lblCycle.setBounds(new Rectangle(9, 54, 103, 15));
//        fieldCycle.setToolTipText("Number of the Cycle");
//        fieldCycle.setCaretColor(Color.black);/
//        fieldCycle.setText("");
//        fieldCycle.setBounds(new Rectangle(110, 51, 66, 21));
        lblCore.setToolTipText("");
        lblCore.setText("Core ID:");
        lblCore.setBounds(new Rectangle(67, 24, 42, 23));
        fieldCoreID.setToolTipText("The Center of Informatics (CIn)-UFPE tester\'s identification");
        fieldCoreID.setText(this.ptfAddonFacade.getRegisteredCoreID());
        fieldCoreID.setBounds(new Rectangle(110, 26, 66, 21));
        btnNextGeneral.setMaximumSize(new Dimension(89, 23));
    btnNextGeneral.setMinimumSize(new Dimension(55, 23));
    btnNextGeneral.setPreferredSize(new Dimension(89, 23));
    btnNextGeneral.setToolTipText("Access the Test List Builder to specify the automated test cases " +
    "for this hardware");
        btnNextGeneral.setActionCommand("Next");
        btnNextGeneral.setText("Next");

        treeTest.addTreeSelectionListener(this);

        btnNextGeneral.addActionListener(new  JWizardInternalFrame_btnNextGeneral_actionAdapter(this));
        panelBtn.setLayout(verticalFlowLayout2);
        btnUploadJar.setToolTipText("Uploads the Test Cases .jar file containing the Test Cases Classes");
    btnUploadJar.setText("Upload JAR");
        btnUploadJar.addActionListener(new JWizardInternalFrame_btnUploadJar_actionAdapter(this));
        btnNextTestList.setEnabled(false);
    btnNextTestList.setToolTipText("Continues the configuration steps, setting the main PTF-TAF configuration " +
    "file");
        btnNextTestList.setText("Next");
        btnNextTestList.addActionListener(new JWizardInternalFrame_btnNextTestList_actionAdapter(this));
        jPanel1.setLayout(verticalFlowLayout3);
        btnNextConfig.setEnabled(false);
        btnNextConfig.setRequestFocusEnabled(true);
    btnNextConfig.setToolTipText("Finalize the configuration process and begin the execution step");
        btnNextConfig.setText("Next");
        btnNextConfig.addActionListener(new JWizardInternalFrame_btnNextConfig_actionAdapter(this));
        jPanel2.setLayout(verticalFlowLayout1);
        jPanel3.setLayout(borderLayout11);
        btnSaveTestList.setEnabled(false);
    btnSaveTestList.setToolTipText("Saves the definition of both .jar and .txt files needed to execute " +
    "the tests");
        btnSaveTestList.setMargin(new Insets(2, 14, 2, 14));
        btnSaveTestList.setText("Save List");
        btnSaveTestList.addActionListener(new
                                          JWizardInternalFrame_btnSaveTestList_actionAdapter(this));
        paneResults.setDebugGraphicsOptions(0);
    paneResults.setToolTipText("The results from the automated tests execution based on the PTF-TAF log output");
        paneResults.setLayout(borderLayout6);
        btnSaveExecution.setEnabled(false);
        btnSaveExecution.setAlignmentX((float) 0.0);
        btnSaveExecution.setToolTipText("Saves this execution wizard process on file.");
        btnSaveExecution.setText("");
        btnSaveExecution.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/execution-wizard-icon-save.png")));
        btnSaveExecution.addActionListener(new JWizardInternalFrame_btnSaveExecution_actionAdapter(this));
        btnExecutionBar.setOrientation(JToolBar.HORIZONTAL);
        jPanel6.setBorder(titledBorder2);
        jPanel6.setPreferredSize(new Dimension(100, 80));
        jPanel6.setLayout(null);
        jPanel7.setBorder(titledBorder3);
        jPanel7.setLayout(null);
        splitExecDetail.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitExecDetail.setBottomComponent(logScroll);
        splitExecDetail.setDividerSize(4);
        splitExecDetail.setLastDividerLocation(130);
        splitExecDetail.setRightComponent(logScroll);
        splitStepsException.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitStepsException.setDividerSize(4);
        splitStepsException.setLastDividerLocation(70);
        textAreaSteps.setBorder(border4);
    textAreaSteps.setEditable(false);
        textAreaSteps.setText("");
        textAreaSteps.setColumns(0);
        jSplitPane3.setDividerSize(4);
        jSplitPane3.setLastDividerLocation(200);
        jSplitPane3.setLeftComponent(panelSteps);
        jSplitPane3.setRightComponent(treeException);
        paneDetail.setBorder(titledBorder6);
        paneDetail.setDebugGraphicsOptions(0);
        paneDetail.setToolTipText("The online execution status of the automated tests");
        paneDetail.setLayout(borderLayout9);
        panelSteps.setBorder(titledBorder4);
    panelSteps.setToolTipText("Shows the steps of a specified test case result chosen from the Test " +
    "Case List View");
        panelSteps.setLayout(borderLayout7);
        panelExceptionView.setBorder(titledBorder5);
    panelExceptionView.setToolTipText("Exceptions from the selected test case from the Test Cases List View");
        panelExceptionView.setLayout(borderLayout8);
        panelLog.setBorder(titledBorder7);
        panelLog.setDebugGraphicsOptions(0);
    panelLog.setToolTipText("The PTF online log output from the execution");
        panelLog.setLayout(borderLayout10);
        panelTestListFilterBox.setLayout(borderLayout12);
        panelFilter.setBorder(titledBorder8);
        panelFilter.setMinimumSize(new Dimension(5, 60));
        panelFilter.setPreferredSize(new Dimension(5, 60));
    panelFilter.setLayout(gridLayout1);
        panelTestListBox.setLayout(borderLayout13);
        panelTestListBox.setBorder(titledBorder9);
        checkBoxSelectTests.setToolTipText("Check and Uncheck all items on the list according to the specified " +
    "filter");
    checkBoxSelectTests.setActionCommand("Select All");
        checkBoxSelectTests.setText("Select All Matching");
        checkBoxSelectTests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
              checkBoxSelectTests_actionPerformed(e);
            }
          });

        textFieldFilter.setToolTipText("The filter which the test cases will be checked or unchecked to");
    textFieldFilter.setText("");
        textFieldFilter.setColumns(20);
    textFieldFilter.setHorizontalAlignment(SwingConstants.LEADING);

        textFieldFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent e) {
               textFieldFilter_keyPressed(e);
            }
            public void keyReleased(KeyEvent e) {
               textFieldFilter_keyReleased(e);
            }
        });

        logScroll.setAutoscrolls(true);
        scrollPaneExecutionSteps.setAutoscrolls(true);
        scrollPaneTreeException.setAutoscrolls(true);
        scrollPaneTreeException.setDebugGraphicsOptions(0);
    jarTestCasesField.setEnabled(true);
    jarTestCasesField.setMinimumSize(new Dimension(11, 20));
    jarTestCasesField.setToolTipText("The full path to the specified test cases in the .jar file");
    jarTestCasesField.setEditable(false);
    jarTestCasesField.setColumns(60);
    panelTestListFilterBox.setMinimumSize(new Dimension(155, 120));
    panelTestListFilterBox.setPreferredSize(new Dimension(315, 250));
    jRadioButton1.setToolTipText("Automated tests for the P2K platform");
    jRadioButton1.setSelected(true);
    jRadioButton1.setText("P2K");
    jRadioButton1.setBounds(new Rectangle(13, 20, 55, 23));
    jRadioButton2.setToolTipText("Automated tests for the JUIX platform");
    jRadioButton2.setText("JUIX");
    jRadioButton2.setBounds(new Rectangle(13, 46, 52, 23));
    jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioButton2_actionPerformed(e);
      }
    });
    labelJarFile.setText("Test Cases Suite in Jar File:");
    labelTestList.setText("Implemented Test Cases List File:");
    fieldTestCaseList.setEnabled(true);
    fieldTestCaseList.setToolTipText("The full path to the specified .txt test cases list file");
    fieldTestCaseList.setEditable(false);
    fieldTestCaseList.setText("");
    fieldTestCaseList.setColumns(60);
    jPanel4.setLayout(borderLayout14);
    jPanel8.setLayout(borderLayout15);
    jPanel8.setBorder(titledBorder10);
    jPanel9.setBorder(titledBorder11);
    fieldMainConfigFile.setToolTipText("The full path to the main configuration file");
    fieldMainConfigFile.setEditable(false);
    fieldMainConfigFile.setText("");
    fieldMainConfigFile.setColumns(40);
    checkableList.setToolTipText("The Test Cases List from the .txt test cases list file");
    scrollConfigList.setToolTipText("The main configuration file key-value table");
    paneConfig.setToolTipText("The main configuration file settings");
    txtFieldHardware.setBounds(new Rectangle(138, 19, 206, 20));
    labelTafEnabled.setFont(new java.awt.Font("Arial Narrow", 1, 11));
    labelTafEnabled.setForeground(Color.red);
    labelTafEnabled.setHorizontalAlignment(SwingConstants.RIGHT);
    labelTafEnabled.setText("    TAF desabled");
    logTextArea.setEditable(false);
    jPanel10.setMinimumSize(new Dimension(87, 30));
    jPanel10.setPreferredSize(new Dimension(87, 30));
    btnOpenFile.setText("Open File");
    btnOpenFile.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnOpenFile_actionPerformed(e);
      }
    });
    btnOpenFile.setToolTipText("Opens a file and sets the value of the absolute path on the value " +
    "of the highlighted line");
    btnOpenFile.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/openFile.png")));
    btnOpenEditor.setToolTipText("Opens an editor for the highlighted configuration key line");
    btnOpenEditor.setBorderPainted(true);
    btnOpenEditor.setText("Open Editor");
    btnOpenEditor.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/openProperties.gif")));
    fieldCycleGroup.setBounds(new Rectangle(110, 51, 67, 21));
    this.getContentPane().add(paneTab, BorderLayout.CENTER);

        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
        btnBar.add(btnStart, null);
        btnBar.add(btnStop, null);
        this.getContentPane().add(btnExecutionBar, BorderLayout.NORTH);
        paneTab.addTab("General Configuration",new ImageIcon(ResourceManager.getInstance().getResource("images/config.png")),paneGeneralCfg,"Test Cycle information");
        ExecutePane.add(Slip1, BorderLayout.CENTER);
        ExecutePane.add(ExecutionProgress, BorderLayout.SOUTH);
        Slip1.add(splitExecDetail, JSplitPane.RIGHT);
        splitExecDetail.add(splitStepsException, JSplitPane.TOP);
        splitStepsException.add(jSplitPane3, JSplitPane.BOTTOM);
        jSplitPane3.add(panelSteps, JSplitPane.LEFT);
        panelSteps.add(scrollPaneExecutionSteps, BorderLayout.CENTER);
        scrollPaneExecutionSteps.getViewport().add(textAreaSteps, null);
        jSplitPane3.add(panelExceptionView, JSplitPane.RIGHT);
        panelExceptionView.add(scrollPaneTreeException, BorderLayout.CENTER);
        scrollPaneTreeException.getViewport().add(treeException, null);
        splitStepsException.add(paneDetail, JSplitPane.TOP);
        paneDetail.add(panelStatusListener, BorderLayout.CENTER);
        Slip1.setDividerLocation(100);
        splitExecDetail.setDividerLocation(150);
        ExecutePane.add(btnBar, BorderLayout.NORTH);
        panelBtn.add(btnUploadJar, null);
        panelBtn.add(btnOpenTestList, null);
        panelBtn.add(btnSaveTestList, null);
        paneTestList.add(panelBtn, BorderLayout.EAST);

        paneTab.setSelectedComponent(paneGeneralCfg);

        paneTab.addTab("Test List Builder",new ImageIcon(ResourceManager.getInstance().getResource("images/testlistbuilder-tab.png")),paneTestList,"Select the test cases to be tested and uploads the TestCases.Jar file");
        paneTab.addTab("Configuration Files",new ImageIcon(ResourceManager.getInstance().getResource("images/ptf-tafconfig-tab.png")),paneConfig,"Sets the main PTF-TAF configuration file keys");
        paneTab.addTab("Execution",new ImageIcon(ResourceManager.getInstance().getResource("images/execution-tab.gif")),ExecutePane,"Executes the selected automated test cases");
        paneTab.addTab("Results",new ImageIcon(ResourceManager.getInstance().getResource("images/results-tab.png")),paneResults,"Show the results of the tested test cases");

         //paneResults.add(new TestResultPanel(null), BorderLayout.CENTER); //0------>>>>>

        paneTab.addChangeListener(new ChangeListener() {
          // This method is called whenever the selected tab changes
          public void stateChanged(ChangeEvent evt) {
            JTabbedPane pane = (JTabbedPane)evt.getSource();
            // Get current tab
            int sel = pane.getSelectedIndex();
            showStatusInfo(sel);
          }
        });

        panelBtn.add(btnNextTestList, null);
        paneTestList.add(panelTestListFilterBox,  BorderLayout.CENTER);
        panelTestListFilterBox.add(panelFilter,  BorderLayout.NORTH);
    panelFilter.add(labelJarFile, null);
    panelFilter.add(jarTestCasesField, null);
    panelFilter.add(labelTestList, null);
    panelFilter.add(fieldTestCaseList, null);
        panelTestListFilterBox.add(panelTestListBox, BorderLayout.CENTER);
        panelTestListBox.add(jScrollPane1,  BorderLayout.CENTER);
        panelTestListBox.add(jPanel5, BorderLayout.NORTH);
        jPanel5.add(checkBoxSelectTests, null);
        jPanel5.add(textFieldFilter, null);
        paneConfig.add(jPanel1, BorderLayout.EAST);
        jPanel1.add(btnOpenCfg, null);
        jPanel1.add(btnSaveCfg, null);
        jPanel1.add(btnNextConfig, null);
    paneConfig.add(jPanel4,  BorderLayout.CENTER);
    jPanel4.add(jPanel8, BorderLayout.CENTER);
    jPanel8.add(scrollConfigList, BorderLayout.CENTER);
    jPanel8.add(jPanel10,  BorderLayout.SOUTH);
    jPanel10.add(btnOpenFile, null);
    jPanel10.add(btnOpenEditor, null);
    scrollConfigList.getViewport().add(tableConfig, null);
    jPanel4.add(jPanel9, BorderLayout.NORTH);
    jPanel9.add(labelConfigFile, null);
    jPanel9.add(fieldMainConfigFile, null);
    paneGeneralCfg.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jPanel6, BorderLayout.NORTH);
    jPanel3.add(jPanel7, BorderLayout.CENTER);
    jPanel7.add(lblCore, null);
    jPanel7.add(fieldCoreID, null);
    jPanel7.add(fieldTestPlan, null);
    jPanel7.add(lblPlan, null);
    jPanel7.add(fieldCycleGroup, null);
    jPanel7.add(lblCycleGroup, null);
    paneGeneralCfg.add(jPanel2, BorderLayout.EAST);
    jPanel2.add(btnNextGeneral, null);

        execution.addListener(logTextArea);
        execution.addTestCaseListener(treeTest);
        execution.addTestCaseListener(ExecutionProgress);
        execution.addStartTestCaseListener(panelStatusListener);
        execution.addTestCaseListener(panelStatusListener);


        tableConfig.addColumn( new TableColumn(10, 10));

        //Evento para marcar e desmarcar o CheckItem
        checkableList.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            int index = checkableList.locationToIndex(e.getPoint());
            checkableList.checkItem(index);
          }
        });

        tableConfig.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            int col = tableConfig.getSelectedColumn();
            int row = tableConfig.getSelectedRow();

            TableModel model = tableConfig.getModel();
            model = tableConfig.getModel();
            execution.getConfiguration().setConfigList( ((PropertiesTableModel)model).getPropertiesLines() );
          }
        });

        paneTab.setSelectedIndex(0);
        btnExecutionBar.add(btnSaveExecution, null);
        btnExecutionBar.add(labelTafEnabled, null);
        splitStepsException.setDividerLocation(70);

        //TreeException configuration
        this.treeException.setBorder(this.border4);
        this.panelExceptionView.add(this.treeException);

        //TreeException configuration
        this.logScroll.setBorder(this.border4);
        this.panelLog.add(this.logScroll,BorderLayout.CENTER);

        splitExecDetail.add(panelLog, JSplitPane.BOTTOM);
        panelLog.add(logScroll, BorderLayout.CENTER);
        Slip1.add(jScrollPane3, JSplitPane.LEFT);
        jScrollPane3.getViewport().add(treeTest, null);
        logScroll.getViewport().add(logTextArea, null);
        jScrollPane1.getViewport().add(checkableList, null);

        jSplitPane3.setDividerLocation(200);
    platformTypeButtonGroup.add(jRadioButton1);
    platformTypeButtonGroup.add(jRadioButton2);
    jPanel6.add(fieldBuild, null);
    jPanel6.add(lblPlatform, null);
    jPanel6.add(txtFieldHardware, null);
    jPanel6.add(lblBuild, null);
    jPanel6.add(jRadioButton1, null);
    jPanel6.add(jRadioButton2, null);

        this.btnNextGeneral.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/next.png")));
        this.btnNextTestList.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/next.png")));
        this.btnNextConfig.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/next.png")));

        this.btnSaveTestList.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/save-button.png")));
        this.btnSaveCfg.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/save-button.png")));

        this.btnUploadJar.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/jarfile.gif")));
        this.btnOpenTestList.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/testlist.gif")));
        this.btnOpenCfg.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/properties.gif")));

        this.checkBoxSelectTests.setIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/filterDesabled.png")));
        this.checkBoxSelectTests.setSelectedIcon(new ImageIcon(ResourceManager.getInstance().getResource("images/filterEnabled.png")));
        this.setToolTipText(this.getTitle());
    }

    public void valueChanged(TreeSelectionEvent event) {
//        ExceptionInstruction[] ex = ((TestCaseDefaultMutableTreeNode)treeTest.getLastSelectedPathComponent()).getTestCaseInstruction().getExceptionInstructions();
    	System.out.println("acionou o tree!!!");
        textAreaSteps.clear();
        textAreaSteps.newTestCaseAction( ((TestCaseDefaultMutableTreeNode)treeTest.getLastSelectedPathComponent()).getTestCaseInstruction() );
        treeException.clear();
        treeException.newTestCaseAction( ((TestCaseDefaultMutableTreeNode)treeTest.getLastSelectedPathComponent()).getTestCaseInstruction() );
    }

    void btnStart_actionPerformed(ActionEvent e) {
        this.textAreaSteps.setText("");
        this.logTextArea.setText("");
        ExecutionProgress.setMaximum(checkableList.getCheckedItems());
        panelStatusListener.setTotalTestCases(checkableList.getCheckedItems());
        try{
        	this.execution.addFinalizationListener(this);
        	System.out.println("Will Execute Tests...");
        	this.dialogCreator.showWarningMessage("PTF Execution Procedures","Assert that you have plugged the phone in for the first time or\ntake the USB cable off the phone and plug it again in order to\navoid the phone being HOLDED ON!");
        	this.execution.ExecTest();
        	this.showStatusInfo(5);
        }catch(ExecutionException ee){
            this.dialogCreator.showErrorMessage("Error while executing testing...",ee.getMessage());
        }
    }

    private boolean isPreparateInfo(){
        boolean preparateStub = false;
        if ( !fieldBuild.getText().equals("") & !fieldCycleGroup.getText().equals("") & !fieldTestPlan.getText().equals("")){
            preparateStub = true;
        }
        return preparateStub;
    }

    void btnStop_actionPerformed(ActionEvent e) {
        this.execution.stopExecution();
    }

    void btnTestlist_actionPerformed(ActionEvent e) {
        this.execution.getTestList().setPreparado(true);
    }

    void btnSave_actionPerformed(ActionEvent e) {
        if (execution.getTestList().getPreparado() && execution.getConfiguration().getPreparado() ){
            try{
                execution.ExecTest();
            }catch (ExecutionException ee) {
                this.dialogCreator.showErrorMessage("Error while executing tests!",ee.getMessage());
            }
        }
    }

  void btnOpenTestList_actionPerformed(ActionEvent e) {

      if (e == null){ //if the call of this method was done from opening a new execution wizard file.

      	this.checkableList.setListData(CheckableItem.getCheckableItems(this.execution.getTestList().getTestList()));
        btnSaveTestList.setEnabled(true);
        btnNextTestList.setEnabled(true);
        this.checkBoxSelectTests.setEnabled(true);
        this.textFieldFilter.setEnabled(true);

      } else { // if the call was done to open a new list. TestListFilter

        JFileChooser chooser = this.dialogCreator.getFileSingleSelectionChooser(this.ptfAddonFacade.getPTFRootPath());
        chooser.setFileFilter(this.dialogCreator.new TestListFilter());
        chooser.setDialogTitle("Open Test List File...");
        int status = chooser.showOpenDialog(this);

	    if(status == JFileChooser.APPROVE_OPTION) {
	      try {
                String path = chooser.getSelectedFile().getPath();
                if (this.ptfAddonFacade.isValidTestCaseList(path)){
                  this.fieldTestCaseList.setText(path);
                  this.execution.getTestList().setFromJar(false);
                  this.checkBoxSelectTests.setEnabled(true);
                  this.checkBoxSelectTests.setEnabled(true);
                  this.textFieldFilter.setEnabled(true);

                  this.execution.getTestList().addFromFile(fileManager.getFileLines(path), path);
                  checkableList.setListData(CheckableItem.getCheckableItems(this.execution.getTestList().getTestList()));
                  btnSaveTestList.setEnabled(true);
                  this.execution.getTestList().setPreparado(true);
                  this.updateCheckTestListFile(chooser.getSelectedFile().getAbsolutePath());
                } else this.dialogCreator.showErrorMessage("Incorrect Test List File","The Test Cases List file must only contain either com.motorola.testcase.* or synergyLite.* based classes!");

	      } catch (IOException fnfe) {
	          this.dialogCreator.showErrorMessage("Error opening test list file!",fnfe.getMessage());
	      }
	    }
      }
  }

  void btnOpenCfg_actionPerformed(ActionEvent e) {

      if (e == null){
          tableConfig.setModel(new PropertiesTableModel(this.execution.getConfiguration().getConfigFileLines()));
          btnSaveCfg.setEnabled(true);
          btnNextConfig.setEnabled(true);

      } else {

        JFileChooser chooser = this.dialogCreator.getFileSingleSelectionChooser();
        chooser.setFileFilter(this.dialogCreator.new ConfigurationFileFilter());
        chooser.setDialogTitle("Open Configuration File...");
        int status = chooser.showOpenDialog(this);

		   if (status == JFileChooser.APPROVE_OPTION) {
		     try {
		       this.execution.getConfiguration().addFromFile( fileManager.getFileLines(chooser.getSelectedFile().getPath()),chooser.getSelectedFile().getPath() );
		       this.tableConfig.setModel(new PropertiesTableModel( execution.getConfiguration().getConfigFileLines() ) );
		       this.execution.getConfiguration().setPreparado(true);
		       btnSaveCfg.setEnabled(true);
               this.fieldMainConfigFile.setText(chooser.getSelectedFile().getPath());
               this.updateCheckConfigFile(chooser.getSelectedFile().getAbsolutePath());
		     }
		     catch(FileNotFoundException fnfe) {
		         this.dialogCreator.showErrorMessage("Error while opening configuration file",fnfe.getMessage());
		     }
		   }
      }
  }

	/**
	 * Validades the general information tab form.
	 * @param e is the event generated
 	* @created 18/07/2004 12:45:47
 	*/
      void btnNextGeneral_actionPerformed(ActionEvent e) {
                if (this.txtFieldHardware.getText().equals("")){
                    this.dialogCreator.showErrorMessage("Platform Information","You must provide the name of the hardware");
                }else
                if (this.fieldBuild.getText().equals("")){
                    this.dialogCreator.showErrorMessage("Platform Information","You must provide the build label");
                }else
		if (this.fieldCoreID.getText().equals(""))
		    this.dialogCreator.showErrorMessage("Test Central Information","You must provide the tester core ID");
		else
	        if (this.fieldCoreID.getText().length() != 6)
	            this.dialogCreator.showErrorMessage("Test Central Information","The tester core ID must be a 6 length code!");
	        else
		if (this.fieldCycleGroup.getText().equals(""))
		    this.dialogCreator.showErrorMessage("Test Central Information","You must provide the cycle information");
		else
//                if (this.fieldCycleGroup.getText().length() < 2 )
//                    this.dialogCreator.showErrorMessage("Test Central Information","The Cycle number must be more than 2 length code!");
//               else
		if (this.fieldTestPlan.getText().equals(""))
		    this.dialogCreator.showErrorMessage("Test Central Information","You must provide the test plan information");
		else {
                  this.setWizardTitle();
                  this.showTestListTab();
                }
      }

      private void setWizardTitle(){
        String platform = (this.jRadioButton1.isSelected()) ? this.jRadioButton1.getText() : this.jRadioButton2.getText();
        this.setTitle("Automated Execution for "+platform+ " :: " +txtFieldHardware.getText()+ " :: " +fieldBuild.getText());
      }

      private void showStatusInfo(int tabIndex){
        switch (tabIndex){
          case 0: this.statusBar.setText(" General Configuration information...");
            break;
          case 1: this.statusBar.setText(" Test list file information...");
                 break;
          case 2: this.statusBar.setText(" Configuration file information...");
            break;
          case 3: this.statusBar.setText(" Automated Test Execution information... Ready to Run Tests...");
            break;
          case 4: this.statusBar.setText(" Final Results of The tests...");
            break;
          case 5: this.statusBar.setText(" Automatic testes in process...");
        }
      }

	private void showGeneralTab(){
	    this.paneTab.setSelectedIndex(0);
	    this.showStatusInfo(0);
	}

	private void showTestListTab(){
           this.paneTab.setEnabledAt(1,true);
	    this.paneTab.setSelectedIndex(1);
	    this.showStatusInfo(1);
	}

	private void showPTFConfigurationFileTab(){
           this.paneTab.setEnabledAt(2,true);
	    paneTab.setSelectedIndex(2);
	    this.showStatusInfo(2);
	}

	private void showExecutionTab(){
           this.paneTab.setEnabledAt(3,true);
	    paneTab.setSelectedIndex(3);
	    this.showStatusInfo(3);
	}

        private void showResultsTab(){
           this.paneTab.setEnabledAt(4,true);
            paneTab.setSelectedIndex(4);
            this.showStatusInfo(4);
            this.enableTabs();
        }

        private void disableTabs(){
            this.paneTab.setEnabledAt(0, false);
            this.paneTab.setEnabledAt(1, false);
            this.paneTab.setEnabledAt(2, false);
        }

        private void enableTabs(){
            this.paneTab.setEnabledAt(0, true);
            this.paneTab.setEnabledAt(1, true);
            this.paneTab.setEnabledAt(2, true);
        }


	void btnUploadJar_actionPerformed(ActionEvent e) {
          JFileChooser chooser = this.dialogCreator.getFileSingleSelectionChooser();
          chooser.setFileFilter(this.dialogCreator.new JarFileFilter());
          chooser.setDialogTitle("Indicate the Test Cases List in Jar File...");
          int status = chooser.showOpenDialog(this);

          if (status == JFileChooser.APPROVE_OPTION) {
              if (chooser.getSelectedFile().getPath().indexOf(".jar") != -1) {
                  String jarListPath = chooser.getSelectedFile().getPath();
                  try {
                    if (this.ptfAddonFacade.isAValidTestCasesJar(jarListPath)){
                          this.jarTestCasesField.setText(jarListPath);
                      	  this.execution.setJarTestCasesPath(chooser.getSelectedFile().getPath());
                      	  this.btnOpenTestList.setEnabled(true);

                      	  if (this.ptfAddonFacade.isTAFComplaintTestCases(jarListPath)){
                      	      this.ptfAddonFacade.enableTAF(this.execution);
                              this.setTAFValue(true);
                              this.updateCheckJarFile(chooser.getSelectedFile().getAbsolutePath());
                      	      this.dialogCreator.showInfoMessage("TAF enabled execution","The execution is TAF enabled, once the taf.jar file was \n identified on the same directory as the Test Cases List jar file!");
                      	  } else {
                      	      this.updateCheckJarFile(chooser.getSelectedFile().getAbsolutePath());
                      	      this.setTAFValue(false);
                      	  }

                    } else this.dialogCreator.showErrorMessage("Incorrect Test Cases Jar File","The specified jar file does not contain a set of valid test cases.\nVerify if the list of the test cases classes is from either com.motorola.testcase.* or synergyLite.* package!");

                } catch (IOException e1) {
                    this.dialogCreator.showErrorMessage("Error while opening the Jar file",e1.getMessage());
                }

              } else this.dialogCreator.showErrorMessage("Incorrect File Extension","The specified file is not in a jar one");
          }
        }

        private void setTAFValue(boolean enabled){
          this.labelTafEnabled.setText(enabled ? "    TAF enabled" : "    TAF desabled");
        }

        void btnNextConfig_actionPerformed(ActionEvent e) {
          if (!this.configFileSaved){
              String buttonsLabel[] = {"yes,no"};

            if (this.dialogCreator.showConfirmDialog("Save this configuration settings on the config file?") == JOptionPane.YES_OPTION) {
              this.btnSaveCfg_actionPerformed(e);
              this.showExecutionTab();
            } else this.dialogCreator.showWarningMessage("PTF Configuration Settings","You have to save the configuration settings in the file in order to proceed.");

          } else {
            this.showExecutionTab();
            this.btnSaveExecution.setEnabled(true);
            this.dialogCreator.showInfoMessage("Save Execution Wizard Information", "From now on you can save this execution wizard on hard disk.\nJust click on the Save Execution Wizard button!");
          }
	}

	void btnNextTestList_actionPerformed(ActionEvent e) {

            if (!this.testListFileSaved){ //if the button has been clicked...

              if (this.dialogCreator.showConfirmDialog("Save the test list settings?") == JOptionPane.YES_OPTION) {

                if (execution.getTestList().getPreparado()){

                  this.btnSaveTestList_actionPerformed(e);
                  this.showPTFConfigurationFileTab();
                } else {
                  this.btnSaveTestList_actionPerformed(e);

              }

              } else this.dialogCreator.showWarningMessage("Test List Builder","You have to save the test list file in order to proceed.");

            } else this.showPTFConfigurationFileTab();

	}

	void btnSaveCfg_actionPerformed(ActionEvent e) {
	    TableModel model = tableConfig.getModel();
	    model = tableConfig.getModel();
	    execution.getConfiguration().setConfigList( ((PropertiesTableModel)model).getPropertiesLines() );

            try {
              fileManager.saveFile(execution.getConfiguration().getPathConfig(),
                                   execution.getConfiguration().
                                   getConfigFileLines());
              this.dialogCreator.showInfoMessage("Configuration File Settings","This configuration information was sucessfully saved!");
              this.configFileSaved = true;
              this.btnNextConfig.setEnabled(true);
              this.paneTab.setEnabledAt(3,true);
            }
            catch (UnsupportedEncodingException ex) {
                this.dialogCreator.showInfoMessage("PTF Configuration ERROR",ex.getMessage());
            }
            catch (IOException ex) {
                this.dialogCreator.showInfoMessage("PTF Configuration ERROR",ex.getMessage());
            }

	}

        private void setTestListPathForJar(){
          JFileChooser chooser = this.dialogCreator.getFileSingleSelectionChooser();
          chooser.setApproveButtonText("Save");
          chooser.setFileFilter(this.dialogCreator.new TestListFilter());
          chooser.setDialogTitle("Save Test Cases List File from the Opened Jar...");
          int status = chooser.showSaveDialog(this);
          if (status == JFileChooser.APPROVE_OPTION) {
            String filePath = chooser.getSelectedFile().getPath();
            this.execution.getTestList().setListPath(filePath);
            this.execution.getTestList().setPreparado(true);
            this.btnSaveTestList_actionPerformed(null);
          } else this.dialogCreator.showErrorMessage("Test Cases List File","This Test List must be saved in order to run the tests...");

        }

	 void btnSaveTestList_actionPerformed(ActionEvent e) {

          //the list was downloaded from the jar file...
          if (this.execution.getTestList().isFromJar() && !this.execution.getTestList().getPreparado()){
            this.setTestListPathForJar();
          } else {
            execution.getTestList().setTestList(checkableList.getCheckedCompleteList());
            execution.setJarTestCasesPath(this.jarTestCasesField.getText());
            try {
              boolean thereIsACheckedTest = false;

              String[] list = this.checkableList.getCheckedCompleteList();
              for (int i = 0; i < list.length; i++){
                if (list[i].charAt(0) != '#'){
                  thereIsACheckedTest = true;
                  break;
                }
              }

              if (thereIsACheckedTest){

                String testListPath = execution.getTestList().getPathList();
                testListPath = testListPath.endsWith(".txt") ? testListPath :
                    testListPath + ".txt";
                fileManager.saveFile(testListPath, execution.getTestList().toArray());

                this.dialogCreator.showInfoMessage("Test List File",
                                          "This test list was sucessfully saved!");
                this.testListFileSaved = true;
                btnNextTestList.setEnabled(true);
                this.paneTab.setEnabledAt(2,true);

              } else this.dialogCreator.showErrorMessage("Test List File",
                                          "The List must contain at least one test case!");
            }
            catch (UnsupportedEncodingException ex) {
                this.dialogCreator.showInfoMessage("PTF Configuration ERROR", ex.getMessage());
            }
            catch (IOException ex) {
                this.dialogCreator.showInfoMessage("PTF Configuration ERROR", ex.getMessage());
            }
          }
	}

  void btnSaveExecution_actionPerformed(ActionEvent e) {
    JFileChooser chooser = this.dialogCreator.getFileSingleSelectionChooser("./executions");
    chooser.setFileFilter(this.dialogCreator.new PTFExecutionFilter());
    chooser.setDialogTitle("Save PTF Execution Wizard Configuration file...");
    int status = chooser.showDialog(this,"Save");
    if (status == JFileChooser.APPROVE_OPTION) {
        String platform = this.jRadioButton1.isSelected() ? this.jRadioButton1.getText() : this.jRadioButton2.getText();
	    String hardware = (String)this.txtFieldHardware.getText();
	    String build    = this.fieldBuild.getText();
	    String coreId   = this.fieldCoreID.getText();
	    String band    = this.fieldCycleGroup.getText();
	    String plan     = this.fieldTestPlan.getText();
        String cycle    = this.fieldTestPlan.getText().substring(this.fieldTestPlan.getText().trim().lastIndexOf(" "), this.fieldTestPlan.getText().length() );
        String jarTestCases = this.jarTestCasesField.getText();

	    try {
	        ExecutionWizard ew = this.execution.getExecutionWizard(platform,hardware,build,coreId,band,plan);
                String filePath = chooser.getSelectedFile().getAbsolutePath();
                filePath = filePath.endsWith(".ptfaddon") ? filePath : filePath + ".ptfaddon";
	        WizardFileController.getInstance().newSaveExecutionFile(filePath,ew);
	        this.dialogCreator.showInfoMessage("Configuration Wizard File","The wizard file was successfully save!");
	    } catch (Exception ee){
	        this.dialogCreator.showErrorMessage("Error saving Wizard file",ee.getMessage());
	    }
	}
  }

  void treeTest_mouseClicked(MouseEvent e) {

  }

  void textFieldFilter_keyReleased(KeyEvent e) {

  }

	/* @created 13/08/2004 14:39:18
	 * (non-Javadoc)
	 * @see br.ufpe.cin.stp.global.checkchange.ExecutionFinalizationListener#processFinalization(br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite)
	 */
	public void processFinalization(TestCaseSuite tcs) {
            try {
              System.out.println("Processing finalization...");
              TestResultPanel resultPanel = new TestResultPanel(tcs, execution.getInfoTest());
              paneResults.add(resultPanel);
              this.dialogCreator.showInfoMessage("Automated Execution Has been finished","The automated tests have been finalized!\nAnalyse the results to be exported...");
              this.showResultsTab();
            }
            catch (Exception ex) {
               this.enableTabs();
              ex.printStackTrace();
            }
	}

        void textFieldFilter_keyPressed(KeyEvent e) {
          this.updateTestList();
        }

        void checkBoxSelectTests_actionPerformed(ActionEvent e) {
          this.updateTestList();
        }

        private void updateTestList() {
          if (this.checkableList.getCheckedCompleteList().length != 0){
            String token = this.textFieldFilter.getText();
            boolean checked = this.checkBoxSelectTests.isSelected();

            String[] list = this.checkableList.getCheckedCompleteList();

              for (int i = 0; i < list.length; i++) {

                if (!checked) {
                  if (token.equals("")){

                    //comments all tests (unckeck all tests to not be tested)
                    if (list[i].charAt(0) != '#') {
                      list[i] = "#" + list[i];
                    } else list[i] = list[i];

                  } else {

                    if (ListFilter.getInstance().containsToken(list[i],token)){

                      if (list[i].charAt(0) != '#') {
                        list[i] = "#" + list[i];
                      } else list[i] = list[i];

                    } else {

                      if (list[i].charAt(0) == '#') {
                         list[i] = list[i].substring(1);
                       } else list[i] = list[i];
                    }

                  }

                } else {

                  //uncomments all tests (selects all tests to be tested)
                  if (token.equals("")){
                      if (list[i].charAt(0) == '#') {
                         list[i] = list[i].substring(1);
                       } else list[i] = list[i];

                  }else {

                    if (ListFilter.getInstance().containsToken(list[i],token)){

                      if (list[i].charAt(0) == '#') {
                         list[i] = list[i].substring(1);
                       } else list[i] = list[i];

                    } else {

                      if (list[i].charAt(0) != '#') {
                        list[i] = "#" + list[i];
                      } else list[i] = list[i];
                    }

                  }
                }
              }

            this.checkableList.setListData(CheckableItem.getCheckableItems(list));
          }

        }

  void this_internalFrameClosing(InternalFrameEvent e) {
      this.ptfAddonFacade.removeExecution( execution );
  }

  void this_internalFrameOpened(InternalFrameEvent e) {
  }

  void btnOpenFile_actionPerformed(ActionEvent e) {
    JFileChooser chooser = this.dialogCreator.getFileSingleSelectionChooser();
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    chooser.setDialogTitle("Choose a file or path to be indicated...");
    int status = chooser.showDialog(this,"Select");
    if (status == JFileChooser.APPROVE_OPTION) {
        int row = this.tableConfig.getSelectedRow();
        PropertiesTableModel model = (PropertiesTableModel)tableConfig.getModel();
        String path = this.ptfAddonFacade.fixPathBackSlashes(chooser.getSelectedFile().getAbsolutePath());

        if (this.ptfAddonFacade.fileExists(path)){
            model.setValueAt(path,row,2);
            this.tableConfig.repaint();
        }
    }
  }

  void jRadioButton2_actionPerformed(ActionEvent e) {

  }
}


class JWizardInternalFrame_btnStart_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnStart_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnStart_actionPerformed(e);
  }
}

class JWizardInternalFrame_btnStop_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnStop_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnStop_actionPerformed(e);
  }
}

class JWizardInternalFrame_btnTestlist_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnTestlist_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnTestlist_actionPerformed(e);
  }
}

class JWizardInternalFrame_btnSaveCfg_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnSaveCfg_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSaveCfg_actionPerformed(e);
  }
}

class JWizardInternalFrame_btnOpenTestList_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnOpenTestList_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOpenTestList_actionPerformed(e);
  }
}

class JWizardInternalFrame_btnNextGeneral_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnNextGeneral_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnNextGeneral_actionPerformed(e);
  }
}

class JWizardInternalFrame_btnUploadJar_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnUploadJar_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnUploadJar_actionPerformed(e);
  }
}

class JWizardInternalFrame_btnNextConfig_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnNextConfig_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnNextConfig_actionPerformed(e);
  }
}

class JWizardInternalFrame_btnNextTestList_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnNextTestList_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnNextTestList_actionPerformed(e);
  }
}

class JWizardInternalFrame_btnOpenCfg_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnOpenCfg_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOpenCfg_actionPerformed(e);
  }
}

class JWizardInternalFrame_btnSaveTestList_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnSaveTestList_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnSaveTestList_actionPerformed(e);
  }
}

class JWizardInternalFrame_btnSaveExecution_actionAdapter implements java.awt.event.ActionListener {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_btnSaveExecution_actionAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSaveExecution_actionPerformed(e);
  }
}

class JWizardInternalFrame_treeTest_mouseAdapter extends java.awt.event.MouseAdapter {
  JWizardInternalFrame adaptee;

  JWizardInternalFrame_treeTest_mouseAdapter(JWizardInternalFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.treeTest_mouseClicked(e);

  }
}

class MyTreeExpansionListener implements TreeExpansionListener {
        public void treeExpanded(TreeExpansionEvent evt) {
        }

        public void treeCollapsed(TreeExpansionEvent evt) {
        }
}





