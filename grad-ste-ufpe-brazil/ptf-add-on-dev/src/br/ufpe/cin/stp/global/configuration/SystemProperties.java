/*
 * @created 06/07/2004 20:01:04
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 *
 * */

package br.ufpe.cin.stp.global.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

import br.ufpe.cin.stp.global.filemanager.FileManager;
/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 06/07/2004 20:01:04
 */
public class SystemProperties {

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static SystemProperties singleton;

    private ResourceManager rm = ResourceManager.getInstance();
    //private static final Logger logger = LoggerManager.getInstance().getLogger(SystemProperties.class.getPackage().getName());

    private SystemProperties() {
        //logger.info("Stating System properties...");
        this.setSystemProperties();
    }

    /**
     * @created 29/07/2004 12:07:09
     * @return The single instance.
     * @throws IOException
     */
    public synchronized static SystemProperties getInstance(){
        if (singleton == null) {
            singleton = new SystemProperties();
        }
        return singleton;
    }
    
    /**
     * Sets system properties for the whole application.
     * @created 06/07/2004 20:31:55
     */
    public void setSystemProperties(){
        String PHONETEST_HOME="PHONETEST_HOME";
       
        String PHONETEST_HOME_value = FileManager.filterBackslashes(this.rm.getProperty(PHONETEST_HOME));      
        
        String classPath = this.rm.getProperty("ptfaddon.java.class.path");
        classPath = classPath.replaceAll("%"+PHONETEST_HOME+"%",PHONETEST_HOME_value);        
        
        String path = System.getProperty("java.library.path");
        path = FileManager.filterBackBackslashes(PHONETEST_HOME_value+"\\lib")+";"+path;
		        
        System.setProperty("java.class.path",classPath);
        System.setProperty("java.library.path",path);        
        System.setProperty("PHONETEST_HOME",PHONETEST_HOME_value); 
        
        //http://forum.java.sun.com/thread.jsp?thread=135560&forum=52&message=362130
        //http://forum.java.sun.com/thread.jsp?forum=52&thread=556564&tstart=15&trange=15
        //the explanation why the PATH cannot be set dynamically
        //So, the indication to the WINMONITOR.EXE that's inside the PTF_HOME\lib will not be possible
        //to be dynamically set. The only way is to add it as usual on windows/linux path system variable.
    }    

    /**
     * @param testListPath is the test case list file path.
     * @param configPath is the path of the configuration file.
     * @return the execution string command to run the tests
     * with the correct needed system variables defined in
     * the configuration file.
     * @created 29/07/2004 12:07:40
     */
    public String getExecutionInstruction(String testListPath, String configPath, String jarTestsPath){
       	String TEST_HOME_value=System.getProperty("TEST_HOME");
       	String PHONETEST_HOME_value = System.getProperty("PHONETEST_HOME");
       	String classpath = System.getProperty("java.class.path")+";"+jarTestsPath;
      	
       	String exec =
	       "java -classpath "+classpath+
	        //http://forum.java.sun.com/thread.jsp?thread=135560&forum=52&message=362130
	        //http://forum.java.sun.com/thread.jsp?forum=52&thread=556564&tstart=15&trange=15
	        //the explanation why the PATH cannot be dynamically set 
		   //" -Djava.library.path=" + FileManager.filterBackBackslashes(PHONETEST_HOME_value+"\\lib")+
	       " phonetest.TSM -l " + testListPath +
	       " -c " + configPath;
       	return exec;
    }

    private void printSystemProperties(){
        Properties pr = System.getProperties(); // get the system properties
        Set allKeys = pr.keySet();              // turn keys into a set
        // Turn the Set into an array of Strings
        String[] keys = (String[])allKeys.toArray(new String[allKeys.size()]);
        Arrays.sort(keys);  // sort the array
        for (int i=0; i<keys.length; i++) {
          // print each key and its value
          System.out.println(i + " " + keys[i] + "=" + pr.get(keys[i]));
        }
    }

  /*  public static void main(String[] args) {
        SystemProperties.getInstance().printSystemProperties();
        ResourceManager.getInstance();

        System.out.println();
        System.out.println("---------------- New Values ---------------");

        SystemProperties.getInstance().printSystemProperties();

        System.out.println(SystemProperties.getInstance().getExecutionInstruction("c:\\test.txt","c:\\config.cfg","c:\\marcel\\elias\\elias.jar"));

    }*/
}
