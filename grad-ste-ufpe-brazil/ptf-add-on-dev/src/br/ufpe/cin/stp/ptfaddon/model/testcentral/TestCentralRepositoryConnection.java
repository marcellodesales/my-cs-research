/*
 * @created 12/09/2004 14:43:04
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.testcentral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @created 12/09/2004 14:43:04
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class TestCentralRepositoryConnection {
    
    private final String TESTCENTRAL_DATABASE_DRIVE = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    private final String TESTCENTRAL_DATABASE_USER = "tcinsert";
    private final String TESTCENTRAL_DATABASE_PWD = "marshal";
    private final String TESTCENTRAL_DATABASE_IP = "199.5.79.14:1433";
    private final String TESTCENTRAL_DATABASE_URL = "jdbc:microsoft:sqlserver://"+TESTCENTRAL_DATABASE_IP;    

    private Connection connection;
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static TestCentralRepositoryConnection singleton;

    private TestCentralRepositoryConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    }

    /**
     * @created 12/09/2004 14:43:14
     * @return The single instance.
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public synchronized static TestCentralRepositoryConnection getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        if (singleton == null) {
            singleton = new TestCentralRepositoryConnection();
        }
        return singleton;
    }
    
    /**
     * Opens a JDBC connection to the Center of Informatics (CIn)-UFPE Test Central repository
     * @created 12/09/2004 14:57:06
     * @throws InstantiationException if the Driver to access the Test Central Repository is not found
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws SQLException if an error occur while trying to access the Test Central Repository
     */
    public void openConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	Class.forName(this.TESTCENTRAL_DATABASE_DRIVE).newInstance();

        this.connection = DriverManager.getConnection(this.TESTCENTRAL_DATABASE_URL, this.TESTCENTRAL_DATABASE_USER, this.TESTCENTRAL_DATABASE_PWD);
    }
    
    
    /**
     * Closes the JDBC connection with the Center of Informatics (CIn)-UFPE Test Central Repository
     * @created 12/09/2004 14:48:53
     * @throws SQLException if there were an error while trying to close the connection.
     */
    public void closeConnection() throws SQLException{
        this.connection.close();
    }
    
    /**
     * Executes a given sql on Center of Informatics (CIn)-UFPE Test Central repository
     * @created 12/09/2004 15:02:31
     * @param sql the given sql. 
     * @throws SQLException if an error occur 
     */
    private void executeSQLStatement(String sql) throws SQLException{
        this.connection.createStatement().execute(sql);
    }
    
    /**
     * Report the a given result of a testCaseID on the repository
     * @created 12/09/2004 15:12:50
     * @param testerCoreID
     * @param testPlan
     * @param testCaseID
     * @param result the result of the automated test case (PASSED|FAILED|NOT_CONFIGURED) 
     * @throws SQLException
     */
    public void reportResult(String testerCoreID, String testPlan, String band, String testCaseID, String result, String elapsedMinute) throws SQLException{

        String storeProcedure = "";
        if ( result.equals("FAILED")){
        	storeProcedure = "autodb.dbAddAutomatedTestResult_I '" + testCaseID +
            "', '" + testPlan +
            "', 'Automatic', '"+band+"', '0','"+elapsedMinute+"','PTF Environment','','','" +
            testerCoreID.toLowerCase() + "'";     	
          }else
          if ( result.equals("PASSED") ){
          	storeProcedure = "autodb.dbAddAutomatedTestResult_P '" + testCaseID +
            "', '" + testPlan +
            "', 'Automatic', '"+band+"', '0','"+elapsedMinute+"','PTF Environment','','','" +
            testerCoreID.toLowerCase() + "'";
          }else
          if ( result.equals("NOT CONFIGURED") || result.equals("UNKNOWN")){
          	storeProcedure = "autodb.dbAddAutomatedTestResult_B '" + testCaseID +
            "', '" + testPlan +
            "', 'Automatic', '"+band+"', '0','"+elapsedMinute+"','Automated test: need investigation from the execution.','PTF Environment','','','" +
            testerCoreID.toLowerCase() + "'";
          }
          
        System.out.println(storeProcedure);
        this.executeSQLStatement(storeProcedure);
    }
}
