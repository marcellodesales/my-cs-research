/*
 * @created 14/07/2004 11:37:21
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 *
 * */

package br.ufpe.cin.stp.ptfaddon.model.wizardfile;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * The main access to the beans of the execution wizard file mapper. The
 * main component is the highest tag definition of the output file (in xml
 * format), which is the Execution bean.
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 14/07/2004 11:37:21
 */
public class WizardFileBeanFactory {

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static WizardFileBeanFactory factorySingleton;

    private WizardFileBeanFactory() {
    }

    /**
     * @created 14/07/2004 11:42:49
     * @return The single instance.
     */
    public synchronized static WizardFileBeanFactory getInstance() {
        if (factorySingleton == null) {
            factorySingleton = new WizardFileBeanFactory();
        }
        return factorySingleton;
    }

    /**
     * @param hardware the information about platform and build
     * @param tci is the information about coreID, plan and cycle
     * @param nf is the information about configuration and test list files.
     * @param tl is the information about the list of test cases to be used.
     * @return a new ExecutionWizardBean with all the information about the
     * configuration process (wizard) read to be saved (marshalled).
     * @created 14/07/2004 12:06:08
     */
    public ExecutionWizard createNewExecutionWizardBean(Platform pf, TestCentralInfo tci, NeededFiles nf) throws ParseException{
        Calendar today = new GregorianCalendar();
        String id = String.valueOf(today.getTime().getTime());

        ExecutionWizard exec = new ExecutionWizard(today);

        exec.setPlatform(pf);
        exec.setTestCentralInfo(tci);
        exec.setNeededFiles(nf);
        return exec;
    }
    
    /**
     * @return a new ExecutionWizard instance with empty fields for a new
     * execution, with the date information.
     * @created 17/07/2004 13:41:56
     */
    public ExecutionWizard createNewExecutionWizardBean() throws ParseException{
        return new ExecutionWizard(new GregorianCalendar());
    }

    /**
     * @param build is the build of the execution.
     * @param platform is the phone brand of the execution.
     * @return a new Hardware bean of the configuration wizard.
     * @created 14/07/2004 11:54:18
     */
    public Platform createNewPlatform(String type, String hardware, String build){
        if (type == null || type.equals(""))
            throw new IllegalArgumentException("Platform Type must not be neither null nor empty");
        else
        if (hardware == null || hardware.equals(""))
            throw new IllegalArgumentException("Hardware must not be neither null nor empty");
        else
        if (build == null || build.equals(""))
            throw new IllegalArgumentException("Build must not be neither null nor empty");
        return new Platform(type,hardware,build);
    }

    /**
     * @param testerCoreID is the coreId of the tester.
     * @param cycle
     * @param plan
     * @return a new TestCentralInfo bean with the information concerning
     * the test central system.
     * @created 14/07/2004 11:55:11
     */
    public TestCentralInfo createNewTestCentralInfo(String testerCoreID, String cycle, String plan) throws IllegalArgumentException{
        if (testerCoreID == null || testerCoreID.equals(""))
            throw new IllegalArgumentException("Tester CoreID must not be neither null nor empty");
        else
        if (cycle == null || cycle.equals(""))
            throw new IllegalArgumentException("Cycle must not be neither null nor empty");
        else
        if (plan == null || plan.equals(""))
            throw new IllegalArgumentException("Plan must not be neither null nor empty");
        return new TestCentralInfo(testerCoreID.toUpperCase(),cycle,plan);
    }

    /**
     * @param configPath is the path of the configuration file.
     * @param testListPath is the path of the test list.
     * @return a new Bean about hte needed files.
     * @created 14/07/2004 12:10:08
     */
    public NeededFiles createNewNeededFiles(String configPath, String jarTestCases, String testListPath){
        if (configPath == null || configPath.equals(""))
            throw new IllegalArgumentException("Config file path must not be neither null nor empty");
        else
        if (jarTestCases == null || jarTestCases.equals(""))
            throw new IllegalArgumentException("Jar Test Cases file path must not be neither null nor empty");
        else
        if (testListPath == null || testListPath.equals(""))
            throw new IllegalArgumentException("Test list path must not be neither null nor empty");
        return new NeededFiles(configPath,jarTestCases,testListPath);
    }
}
