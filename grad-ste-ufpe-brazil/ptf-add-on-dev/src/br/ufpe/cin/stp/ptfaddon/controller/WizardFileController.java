/*
 * @created 14/07/2004 11:15:29
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.exolab.castor.xml.CastorException;

import br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionWizard;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 14/07/2004 11:15:29
 */
public class WizardFileController {

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static WizardFileController singleton;

    private WizardFileController() {
    }

    /**
     * @created 14/07/2004 11:21:02
     * @return The single instance.
     */
    public synchronized static WizardFileController getInstance() {
        if (singleton == null) {
            singleton = new WizardFileController();
        }
        return singleton;
    }
    
  
    /**
     * @param filePath is the complete path to the file.
     * @param exec is the execution bean with the wizard configuration.
     * @throws CastorException if an error occur while transfoming the configuration
     * file.
     * @throws IOException if an error occur while saving the file.
     * @created 14/07/2004 16:07:36
     */
    public void newSaveExecutionFile(String filePath, ExecutionWizard exec) throws IOException, CastorException{
        FileWriter writer = new FileWriter(filePath);
        exec.marshal(writer); 
    }
    
    /**
     * @param filePath is the complete path to the file.
     * @return a new Execution bean from a file
     * @throws FileNotFoundException if the file was not found.
     * @throws CastorException if errors occurred while marshalling the configuration file.
     * @created 14/07/2004 16:03:19
     */
    public ExecutionWizard getExecutionWizardFile(String filePath) throws FileNotFoundException, CastorException{
        FileReader reader = new FileReader(filePath);
        return (ExecutionWizard)ExecutionWizard.unmarshal(reader);
    }
    
   /*public static void main(String[] args) {
        WizardFileBeanFactory wfbf = WizardFileBeanFactory.getInstance();
        Hardware hw = wfbf.createNewHardware("LIBC02_339_RAZOR","RAZOR");
        TestCentralInfo tci = wfbf.createNewTestCentralInfo("wmj011","Cycle New","Automated Test");
        NeededFiles nf = wfbf.createNewNeededFiles("c:\\config.cfg","z:\\tcList.txt");
        String testlist[] = {"com.motorola.testcase.messaging.iM.createAndSendIM.TC_P2PCHAT_START_177","com.motorola.testcase.messaging.smsEmsMms.createAndSendMessage.TC_MSG_SEND_030"};
        TestList tl = wfbf.createNewTestList(testlist);
        
        try {
            ExecutionWizard exec = wfbf.createNewExecutionWizardBean(hw,tci,nf,tl);
            
            String file = "c:\\nexecution-"+exec.getId()+".ptf";            
            WizardFileController.getInstance().newSaveExecutionFile(file,exec);
            
            //ExecutionWizard newExec = WizardFileController.getInstance().getExecutionWizardFile(file);
            
            //newExec.getHardware().setPlatform("V600");
            
            //WizardFileController.getInstance().newSaveExecutionFile(file,newExec);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    */
}
