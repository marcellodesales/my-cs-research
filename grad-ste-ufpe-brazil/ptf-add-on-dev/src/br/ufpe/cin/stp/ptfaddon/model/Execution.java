/*/*
 * Created on 02/07/2004 11:16:46
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */


package br.ufpe.cin.stp.ptfaddon.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;

import br.ufpe.cin.stp.global.checkchange.ExecutionFinalizationListener;
import br.ufpe.cin.stp.global.checkchange.LogLineListener;
import br.ufpe.cin.stp.global.checkchange.NewTestCaseListener;
import br.ufpe.cin.stp.global.checkchange.StartTestCaseListener;
import br.ufpe.cin.stp.global.configuration.SystemProperties;
import br.ufpe.cin.stp.global.filemanager.FileManager;
import br.ufpe.cin.stp.global.swingcomponent.DialogFactory;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite;
import br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionWizard;
import br.ufpe.cin.stp.ptfaddon.model.wizardfile.NeededFiles;
import br.ufpe.cin.stp.ptfaddon.model.wizardfile.Platform;
import br.ufpe.cin.stp.ptfaddon.model.wizardfile.TestCentralInfo;
import br.ufpe.cin.stp.ptfaddon.model.wizardfile.WizardFileBeanFactory;
/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 * @author José Elias Queiroga
 * @author Marcello Alves de Sales Junior
 * @version 1.0
 */
public class Execution {

  private Process exectionProcess = null;
  private TestList testList;
  private Configuration configuration;
  private InfoTest infoTest;

  /**
   * <code>jarTestCases</code> is the test cases classes inside the specified jar file.
   */
  private String jarTestCasesPath;

  private FileManager file;

  private LinkedList listeners;
  private LinkedList testCaseListeners;
  private LinkedList startTestCaseListener;
  private LinkedList finalizationListeners;
  
  private boolean stopped = false;
  private final String EXEC_RUNNING = "RUNNING";
  private final String EXEC_STOP = "STOP";  
  private String status = EXEC_STOP;

  private DialogFactory dialogCreator = DialogFactory.getInstance();

  private JTextArea internalJTextArea;
  private Vector logList = new Vector();

  private boolean tafEnabled;
  
  private boolean onSummary;
  private boolean haveReported;
  private boolean exceptionOcorred;  
  
  /**
   * Execution
   * Constructor method.
   */
  public Execution() {
    testList = new TestList();
    configuration = new Configuration();
    infoTest = new InfoTest();

    this.initializeListeners();
  }
  
   /**
    * @created 17/09/2004 09:12:14
    * Enables the TAF framework for this execution
    */
	public void enableTAF(){
	      this.tafEnabled = true;
	}
  
	/**
	 * @created 17/09/2004 09:13:28
	 * Desables the TAF framework for this execution
	 */
	public void desableTAF(){
	    this.tafEnabled = false;
	}

  /**
 * @created 17/09/2004 09:12:50
 * @return verifies if TAF is enabled for this execution.
 */
  public boolean isTAFEnabled(){
      return this.tafEnabled;
  }

  private void initializeListeners() {
    this.listeners = new LinkedList();
    this.testCaseListeners = new LinkedList();
    this.finalizationListeners = new LinkedList();
    this.startTestCaseListener = new LinkedList();
  }

  /**
   * Execution
   *
   * @param ez ExecutionWizard
   * @throws FileNotFoundException
   */
  public Execution(ExecutionWizard ez) throws FileNotFoundException {
    this.testList = new TestList(ez.getNeededFiles().getTestList());
    this.configuration = new Configuration(ez.getNeededFiles().getConfig());
    this.infoTest = new InfoTest();
    this.infoTest.setBuild(ez.getPlatform().getBuild());
    this.infoTest.setCoreid(ez.getTestCentralInfo().getCoreId());
    this.infoTest.setCycle(ez.getTestCentralInfo().getCycle());
    this.infoTest.setDate(ez.getDate().toDate());
    this.infoTest.setPlatformType(ez.getPlatform().getType());
    this.infoTest.setHardware(ez.getPlatform().getHardware());
    this.infoTest.setPreparado(true);
    this.infoTest.setTestplan(ez.getTestCentralInfo().getPlan());
    this.infoTest.setPreparado(true);
    this.jarTestCasesPath = ez.getNeededFiles().getJarTestCases();

    this.initializeListeners();
    this.file = FileManager.getInstance();
    
    this.tafEnabled = PTFAddonFacade.getInstance().isTAFComplaintTestCases(this.jarTestCasesPath);
  }

  /**
   * This method executes the TSM class.
   * @throws ExecutionException
   */
  public void ExecTest() throws ExecutionException {
  	stopped = false;
  	
    for (int i=0; i< testCaseListeners.size(); i++)
    	((NewTestCaseListener)testCaseListeners.get(i)).clear();
    
    try {

      String execution = SystemProperties.getInstance().getExecutionInstruction(
          testList.getPathList(), configuration.getPathConfig(),this.getJarToExecution());
      
      this.exectionProcess = Runtime.getRuntime().exec(execution);

      new ReaderThread(exectionProcess.getInputStream()).start();
      this.status = EXEC_RUNNING;
    }
    catch (Exception prmError) {
      throw new ExecutionException(prmError.getMessage());
    }

  }
  
  /**
   * @return the updated class path string with the addition of the taf.jar file, in the case TAF is enabled
   */  
  private String getJarToExecution(){
      return (this.tafEnabled) ? PTFAddonFacade.getInstance().getJarTAFFilePathFromTestCases(this.jarTestCasesPath)+";"+this.jarTestCasesPath : this.jarTestCasesPath;
  }

  /**
   * setConfig
   *
   * @param prmConfig String
   */
  public void setConfig(String prmConfig) {
//    configuration.AddFromFile(prmConfig);
  }

  /**
   * getTestList
   *
   * @return TestList
   */
  public TestList getTestList() {
    return testList;
  }

  /**
   * getConfiguration
   *
   * @return Configuration
   */
  public Configuration getConfiguration() {
    return configuration;
  }

  /**
   * addListener
   *
   * @param listener LogLineListener
   */
  public void addListener(LogLineListener listener) {
    listeners.add(listener);
  }

  /**
   * addTestCaseListener
   *
   * @param listener NewTestCaseListener
   */
  public void addTestCaseListener(NewTestCaseListener listener) {
    testCaseListeners.add(listener);
  }

  /**
   * addFinalizationListener
   *
   * @param listener ExecutionFinalizationListener
   */
  public void addFinalizationListener(ExecutionFinalizationListener listener) {
    this.finalizationListeners.add(listener);
  }

  /**
   * addStartTestCaseListener
   *
   * @param listener StartTestCaseListener
   */
  public void addStartTestCaseListener(StartTestCaseListener listener) {
    this.startTestCaseListener.add(listener);
  }

  /**
   * getInfoTest
   *
   * @return InfoTest
   */
  public InfoTest getInfoTest() {
    return infoTest;
  }
  
  /**
   * Stops the internal process. 
   */
  public void stopExecution(){
  	 if ( this.status.equals(EXEC_RUNNING)){
  	    this.exectionProcess.destroy();
  	    this.stopped = true;
  	    this.status = EXEC_STOP;
  	 }
  }

  /**
   * getExecutionWizard
   *
   * @param hardware String
   * @param build String
   * @param testerCoreId String
   * @param cycle String
   * @param plan String
   * @throws ParseException
   * @return ExecutionWizard
   */
  public ExecutionWizard getExecutionWizard(String platformType,
                                            String hardware, String build,
                                            String testerCoreId, String cycle,
                                            String plan) throws ParseException {
    WizardFileBeanFactory wfbf = WizardFileBeanFactory.getInstance();
    Platform pf = wfbf.createNewPlatform(platformType, hardware, build);
    NeededFiles nf = wfbf.createNewNeededFiles(this.configuration.getPathConfig(),
                                               this.getJarTestCasesPath(),
                                               this.testList.getPathList());
    TestCentralInfo tci = wfbf.createNewTestCentralInfo(testerCoreId, cycle,
        plan);
    return wfbf.createNewExecutionWizardBean(pf, tci, nf);
  }

  /**
   * <p>Title: </p>
   *
   * <p>Description: </p>
   *
   * <p>Copyright: Copyright (c) 2004</p>
   *
   * <p>Company: </p>
   * @author José Elias Queiroga
   * @author Marcello Alves de Sales Jr.
   * @version 1.0
   */
  private class ReaderThread extends Thread {

    private InputStream pi;
    private String line = new String();
    private boolean control = false;
    private List currentTestCase;
    private List listTestCase;
    private TestCaseSuite suite;
    private List lista;
    int summaryCount;
    
    private final String RSLT_FAIL = "RSLT:  FAIL:";
    private final String RSLT_NOT_CONFIGURED = "RSLT:  NOT CONFIGURED:";
    private final String RSLT_PASS = "RSLT:  PASS:";
    private final String RSLT_START = "RSLT: START: ";
    private final String RSLT_UNKNOWN = "RSLT:  Unknown";
    private final String SUMMARY = "SUMMARY:";
    private final String NOTCONNECTED = "PSTUsbIoControl::open:194";    
    private final String LOST_CONNECTION = "java.lang.RuntimeException: Unable to get response. Phone may have disconnected.";
    private final String NO_PHONE_ON_SPECIFIED_PORT = "CONN property does not specify";
    private final String DEVICE_ON_HOLD = "Device on HOLD";
    
    private final int TC_START = 0;
    private final int TC_END = 1;
    private final int TC_NOTHING = 2;

    private int token = TC_NOTHING;

    /**
     * teste
     *
     * @param p InputStream
     */
    public ReaderThread(InputStream p) {
      this.pi = p;
      this.currentTestCase = new Vector();
      this.listTestCase = new Vector();
      this.summaryCount = 0;
      this.lista = new Vector();
      FileManager file = FileManager.getInstance();
    }

    private void createInternalTextArea() throws BadLocationException {
      internalJTextArea = new JTextArea( ( (JTextArea) listeners.get(0)).getText());

      Element paragraph = internalJTextArea.getDocument().getDefaultRootElement();

      // Get number of content elements
      int contentCount = paragraph.getElementCount();

      // Get index ranges for each content element.
      // Each content element represents one line.
      // Each line includes the terminating newline.
      logList = new Vector();

      for (int i = 0; i < contentCount; i++) {
        Element e = paragraph.getElement(i);
        int rangeStart = e.getStartOffset();
        int rangeEnd = e.getEndOffset();

        logList.add(internalJTextArea.getText(rangeStart, rangeEnd - rangeStart));
      }

    }
    
    private void writeLine(String line){
        //Add the line in listeners
        ( (LogLineListener) listeners.get(0)).newLineAction(line);
        ( (JTextArea) listeners.get(0)).setCaretPosition( ( (JTextArea)listeners.get(0)).getDocument().getLength());    	
    }
    
    /**
     * run
     */
    public void run() {
      final byte[] buf = new byte[1024];
      token = TC_NOTHING;
      
      exceptionOcorred = false;
      stopped = false;
      haveReported = false;
      onSummary = false;
      
      while (!exceptionOcorred && !stopped) {
      	
         int len = 0;
         try {
           len = this.pi.read(buf);
         }catch (IOException ex) {
         	stopExecution();
           	dialogCreator.showErrorMessage("Unknown Execution Error", ex.getMessage());
         }

         if (len > -1 )
           line = new String(buf, 0, len);
         
         if (line.equals(""))
         	continue;
             //error without finishing the tests                               //
         //if ((!(line.indexOf("Closing ESN/IMSI") != -1) && !onSummary && !haveReported) || ((line.indexOf("Closing ESN/IMSI") != -1) && !onSummary) || ((line.indexOf("Closing ESN/IMSI") != -1) && onSummary)){
         if (!haveReported){
         	this.lista.add(line);
         	this.writeLine(line);
         }
         
        //listener for phone desconected
        if( line.indexOf(NOTCONNECTED) > -1  && !exceptionOcorred){
            exceptionOcorred = true;
            dialogCreator.showErrorMessage("Execution Error", "It seems that there's no connection with a Center of Informatics (CIn)-UFPE phone.\nCheck if the phone is powered on or if it's connected to the computer.");
            continue;
        } else 
        if (line.indexOf(NO_PHONE_ON_SPECIFIED_PORT) != -1){
            exceptionOcorred = true;
            dialogCreator.showErrorMessage("Execution Error","There's no phone connected on the specified port!\nCheck the OBJECT_CONN key value on the main configuration file to indicate correct port!");
            continue;
        } else
        if (line.indexOf(DEVICE_ON_HOLD) != -1){
            exceptionOcorred = true;
            dialogCreator.showWarningMessage("Execution Warning","The phone is on HOLD! Please, take the USB cable off the phone and plug it again in order to continue!");
            continue;
        } else
        //listener fot start test case
        if (line.indexOf(RSLT_START) > -1 && !onSummary){
           ((StartTestCaseListener) startTestCaseListener.get(0)).newStartLineAction(line);
           continue;
        }else
        //Listener fot end test case
        if ( (line.indexOf(RSLT_FAIL) > -1 || line.indexOf(RSLT_NOT_CONFIGURED) > -1 || line.indexOf(RSLT_PASS) > -1 || line.indexOf(RSLT_UNKNOWN) > -1) && !onSummary) {
        	        	  
	          try {
	            createInternalTextArea();
	          }catch (BadLocationException ex1) {
	            //dialogCreator.showWarningMessage("Error while getting text", ex1.getMessage());
	              ex1.printStackTrace();
	          }
	          this.suite = PTFAddonFacade.getInstance().getTestCaseSuite(logList);
	
	          ( (NewTestCaseListener) testCaseListeners.get(0)).newTestCaseAction( (TestCaseInstruction)this.suite.getTestCases().get(this.suite.getTestCases().size() - 1));
	          ( (JComponent) testCaseListeners.get(0)).updateUI();
	          ( (NewTestCaseListener) testCaseListeners.get(1)).newTestCaseAction( (TestCaseInstruction)this.suite.getTestCases().get(this.suite.getTestCases().size() - 1));
	          ( (JComponent) testCaseListeners.get(1)).updateUI();
	          ( (NewTestCaseListener) testCaseListeners.get(2)).newTestCaseAction( (TestCaseInstruction)this.suite.getTestCases().get(this.suite.getTestCases().size() - 1));
	          ( (JComponent) testCaseListeners.get(2)).updateUI();
	          continue;
        }else
        //Listener for end Test Suite
        if (line.indexOf("SUMMARY: ") > -1 && !onSummary){
           System.out.println("On summary... tests have been finnalized");
           onSummary = true;
           continue;
        } else //it's bc it has reached the last line 
/*        if (line.indexOf("Closing ESN/IMSI") != -1 && !onSummary){
        	System.out.println("Execution failed...");
        	exceptionOcorred = true;
        	stopExecution();         	
          	break;
        }else
*/       if (line.indexOf("Closing ESN/IMSI") != -1 && onSummary){
        	System.out.println("Execution sucessfully stopped!!!");
        	haveReported = true;
        	stopExecution();
        	((ExecutionFinalizationListener) finalizationListeners.get(0)).processFinalization(PTFAddonFacade.getInstance().getTestCaseSuite(logList));         	
          	break;
        }        
      }
    }
  }

    /**
     * @created 07/07/2004 13:16:51
     * @return Returns the jarTestCasesPath.
     */
    public String getJarTestCasesPath() {
      return this.jarTestCasesPath;
    }

    /**
     * @created 07/07/2004 13:16:51
     * @param jarTestCasesPath The jarTestCasesPath to set.
     */
    public void setJarTestCasesPath(String jarTestCasesPath) {
      this.jarTestCasesPath = jarTestCasesPath;
    }
  }
