/*
 * Created on 02/07/2004 11:16:46
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */


package br.ufpe.cin.stp.ptfaddon.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import br.ufpe.cin.stp.global.configuration.ResourceManager;
import br.ufpe.cin.stp.global.configuration.SystemProperties;
import br.ufpe.cin.stp.global.filemanager.FileManager;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite;
import br.ufpe.cin.stp.ptfaddon.model.log.interpreter.LogInterpreterStateMachineParser;
import br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionWizard;

/**
 * This is the main Façade of the application to access all the funcionalities.
 * @author José Elias Queiroga <BR>
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 15/07/2004 19:24:36
 */
public class PTFAddonFacade {

	private static PTFAddonFacade facadeExecution = null;
	public List executionList;
	private Execution execution;

	/**
	 * <code>registeredCoreID</code> is the tester core ID entered on the startup of the application
	 */
	private String registeredCoreID;

        private FileManager fileManager = FileManager.getInstance();
        private boolean toolConfigured = false;
        //private Properties configToolproperties;
        private FileInputStream in;
        private String[] hardwareList;
        private List toolConfigList = new Vector();
        private boolean isconfigToolOpen = false;
        private ResourceManager rm = ResourceManager.getInstance();

  /**
   * PTFAddonFacade
   */
  private PTFAddonFacade() {
	    this.executionList = new Vector();
	    this.execution = new Execution();
	    SystemProperties.getInstance();
	}

  /**
   * getConfigToolOpen
   *
   * @return boolean
   */
  public boolean getConfigToolOpen(){
    return isconfigToolOpen;
  }

  /**
   * setConfigToolOpen
   *
   * @param isOpen boolean
   */
  public void setConfigToolOpen(boolean isOpen){
    isconfigToolOpen = isOpen;
  }

/**
 * @return the singleton instance.
 * @created 15/07/2004 19:25:41
 */
  public synchronized static PTFAddonFacade getInstance(){
	    if (facadeExecution == null)
	        facadeExecution = new PTFAddonFacade();
	    return facadeExecution;
  }

  /**
   * getExecution
   *
   * @return Execution
   */
  public Execution getExecution(){
	    return this.execution;
	}

  /**
   * CreateNewExecution
   *
   * @return Execution
   */
  public Execution CreateNewExecution(){
	    Execution executionAux = new Execution();
	    executionList.add(executionAux);
	    return executionAux;
  }

  /**
   * removeExecution
   *
   * @param execution Execution
   */
  public void removeExecution(Execution execution){
  	      execution.stopExecution();
          executionList.remove( execution );
  }
  
  /**
   * Remove all executions of the execution list.
   */
  public void removeAllExecutions(){
    for (int i=0; i<executionList.size(); i++)
    	((Execution)executionList.get(i)).stopExecution();      	
  }

	/**
	 * @param ew the execution wizard file mapped.
	 * @return a new Execution from an ExecutionWizard object (an execution
	 * state that was previously saved by the tester.
	 * @throws FileNotFoundException if the file could not be opened.
	 * @created 17/07/2004 18:49:07
	 */
  public Execution CreateNewExecution(ExecutionWizard ew) throws FileNotFoundException{
	    Execution executionAux = new Execution(ew);
	    executionList.add(executionAux);
	    return executionAux;
  }

  /**
   * getToolConfigFile
   *
   * @throws ExecutionException
   * @return List
   */
  public Hashtable getToolConfigFile() throws ExecutionException {
      Enumeration enum = this.rm.getProperties().keys();
      Hashtable values = new Hashtable();

      while (enum.hasMoreElements()) {
        String key = (String) enum.nextElement();
        values.put(key,this.rm.getProperties().getProperty(key));
      }
      return values;
  }

  /**
 * @created 12/09/2004 12:37:08
 * @return the root dir to PTF
 */
public String getPTFRootPath(){
      return this.rm.getPTFRootPath();
  }

  /**
   * saveToolConfigFile
   *
   * @param tableModel TableModel
   * @throws ExecutionException
   */
  public void saveToolConfigFile(Hashtable properties) throws ExecutionException {
        try {
            Enumeration enum = properties.keys();

            this.rm.saveProperties(properties);
            //update the environment variables with the new ones...
            SystemProperties.getInstance().setSystemProperties();
        }catch (UnsupportedEncodingException ex) {
           throw new ExecutionException( ex.getMessage() );
        }catch (IOException ex) {
           throw new ExecutionException( ex.getMessage() );
        }
  }

  /**
   * setToolConfigured
   *
   * @param configured boolean
   */
  public void setToolConfigured( boolean configured){
          toolConfigured = configured;
  }

  /**
 * @created 15/09/2004 09:10:48
 * @param pathToPTF
 * @return if the specified pathToPTF is correct, looking for the phonetest.jar file inside the lib sub folder
 */
  public boolean isPTFHomeCorrect(String pathToPTF){
      return (pathToPTF != null && !pathToPTF.equals("") && new File(pathToPTF+"\\lib\\phonetest.jar").exists());
  }

  /**
   * verifyConfigfile
   *
   * @throws ExecutionException
   * @return boolean
   */
  public boolean isConfigurationOk() {
      Properties configToolProperties = this.rm.getProperties();

      String PTFHOME = configToolProperties.getProperty("PHONETEST_HOME");

      this.toolConfigured = (this.isPTFHomeCorrect(PTFHOME));

      return this.toolConfigured;
  }

  /**
   * getToolSettingsList
   *
   * @return List
   */
  public List getToolSettingsList(){
      return toolConfigList;
  }

    /**
     * @created 07/09/2004 10:47:47
     * @return Returns the registeredCoreID.
     */
    public String getRegisteredCoreID() {
        return this.registeredCoreID;
    }
    /**
     * @created 07/09/2004 10:47:48
     * @param registeredCoreID The registeredCoreID to set.
     */
    public void setRegisteredCoreID(String registeredCoreID) {
        this.registeredCoreID = registeredCoreID;
    }

    /**
     * @created 12/09/2004 20:56:07
     * @param logLines the lines of a log file.
     * @return the test case suite containing the results of all test cases.
     */
    public TestCaseSuite getTestCaseSuite(List logLines){
        return new LogInterpreterStateMachineParser(logLines).getTestCaseSuite();
    }

    /**
     * @created 12/09/2004 22:48:46
     * @param filePath
     * @return the log file lines of the specified file path
     * @throws FileNotFoundException if the file was not found
     */
    public List getLogFileLines(String filePath) throws FileNotFoundException {
      return this.fileManager.getFileLines(filePath);
    }

    /**
     * @created 15/09/2004 09:18:06
     * @return the default file manager
     */
    public FileManager getDefaultFileManager(){
        return FileManager.getInstance();
    }

    /**
     * @created 15/09/2004 20:32:41
     * @param testCasesJarFilePath
     * @return
     */
    public boolean isTAFComplaintTestCases(String testCasesJarFilePath){
        String tafFile = this.getJarTAFFilePathFromTestCases(testCasesJarFilePath);
        return new File(tafFile).exists();
    }

    /**
     * @created 15/09/2004 20:36:40
     * @param testCasesJarPath
     * @return the full path to the taf.jar file of the specified testCasesJarPath
     */
    public String getJarTAFFilePathFromTestCases(String testCasesJarPath){
        return (new File(testCasesJarPath)).getParent()+ "\\taf.jar";
    }

    /**
     * Enables the TAF application on the execution instance.
     * @created 15/09/2004 20:43:12
     * @param exec
     */
    public void enableTAF(Execution exec){
        exec.enableTAF();
    }

    /**
     * @created 15/09/2004 20:48:37
     * @param jarFilePath
     * @return if the specified jarFilePath is a valid resource, containing
     * test case implementations. In this case, only com.motorola.testcase
     * package-based classes are possible to be indicated.
     * @throws IOException
     */
    public boolean isAValidTestCasesJar(String jarFilePath) throws IOException{
        return TestList.isValidTestCasesJarFile(jarFilePath);
    }

    /**
     * @created 16/09/2004 20:22:05
     * @param listFilePath
     * @return if the list of the test cases inside the test cases list file is correct.
     * All classes described on that file must be from the com.motorola.testcase package
     * @throws IOException
     */
    public boolean isValidTestCaseList(String listFilePath) throws IOException {
        return TestList.isValidTestCasesListFile(listFilePath);
    }

    /**
     * @created 16/09/2004 20:21:31
     * @param filePath
     * @return replace the back slash \ for \\ without using regular expressions :D
     */
    public String fixPathBackSlashes(String filePath){
        return FileManager.filterBackslashes(filePath);
    }

    /**
     * @created 20/09/2004 10:31:17
     * @param ew
     * @return if all the needed files on the execution wizard instance exist.
     */
    public boolean isExecutionWizardLinksOK(Execution exec){
        return (this.testListFileExists(exec) && this.configFileExists(exec) && this.jaTestCasesrFileExists(exec));
    }

    /**
     * @created 20/09/2004 10:44:00
     * @param exec
     * @return if the test list file exists for a given execution
     */
    public boolean testListFileExists(Execution exec){
        return this.fileManager.fileExists(exec.getTestList().getPathList());
    }

    /**
     * @created 20/09/2004 10:43:42
     * @param exec
     * @return if the configuration file exists for a given execution
     */
    public boolean configFileExists(Execution exec){
        return this.fileManager.fileExists(exec.getConfiguration().getPathConfig());
    }

    /**
     * @created 20/09/2004 10:43:13
     * @param exec
     * @return verifies if the jar file from the execution exists
     */
    public boolean jaTestCasesrFileExists(Execution exec){
        return this.fileManager.fileExists(exec.getJarTestCasesPath());
    }
    
    /**
     * @created 20/09/2004 16:54:57
     * @param filePath
     * @return if the specified filePath exists
     */
    public boolean fileExists(String filePath){
        return this.fileManager.fileExists(filePath);
    }
}
