package br.ufpe.cin.stp.ptfaddon.model.testcentral;

import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import br.ufpe.cin.stp.global.checkchange.NewTestCaseListener;
import br.ufpe.cin.stp.global.filemanager.PropertiesLoader;
import br.ufpe.cin.stp.ptfaddon.model.InfoTest;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite;

/**
 * @author José Elias Queiroga da Costa Araújo
 * @author Marcello de Sales
 */
public class ParserTestCaseID implements Runnable{

  private final String FEATURE_MESSAGING_NEWUI = "feature.messaging.New UI";
  private final String IM = "IM";
  private final String EMAIL = "Email";
  private final String SMS_EMS_MMS = "SMS/EMS/MMS";
  private final String VOICEMAIL = "Voicemail";
  private final String FEATURE = "feature.messaging.New UI";

  private Properties properties;
  private TestCaseSuite testCaseSuite;
  private InfoTest testInfo;
  private Vector testCaseListener;

  private String testCase;

  /**
   * Constructor method
   * @param testCaseSuite - Test Case suite information to send Test central
   * @param testInfo - General information as: Core ID, Planname, Cycle group 2
   */
public ParserTestCaseID(TestCaseSuite testCaseSuite, InfoTest testInfo) {
    this.testCaseListener = new Vector();
    this.testCaseSuite = testCaseSuite;
    this.testInfo = testInfo;
    properties = PropertiesLoader.getInstance().loadProperties("/grouptestcentralid.properties");

  }

  /**
   * Registry listener
   * @param listener - Add listeners 
   */
public void addListener( NewTestCaseListener listener){
    this.testCaseListener.add( listener );
  }

  private String getTestCentralLabelFromPackageLabel(String testCase){
    this.testCase = testCase;
    String testID = null;
    String ID = this.testCase.substring( 32, this.testCase.length()).trim();
    String feature = ID.substring( 0, ID.indexOf(".") );
    ID = ID.substring( ID.indexOf(".")+1, ID.length() );
    String group = ID.substring( 0, ID.indexOf(".") );
    ID = ID.substring( ID.indexOf(".")+1, ID.length() );
    String test =  ID.substring( 0, ID.length() );

    testID = FEATURE + ".";

    if( (feature).equals("smsEmsMms") ){
       testID = testID + SMS_EMS_MMS;
    }else
    if( (feature).equals("iM") ){
       testID = testID + IM;
    }else
    if( (feature).equals("voicemail") ){
        testID = testID + VOICEMAIL;
    }else
    if( (feature).equals("email") ){
        testID = testID + EMAIL;
     }    
    return testID + ":" + searchGroupID(group).trim() + "-" + ID.substring(ID.lastIndexOf("_")+1,ID.length());
  }

  private String searchGroupID(String group){
    String tc = this.properties.getProperty(group);
    return (tc == null) ? "Group Not found!" : tc;
  }

  /**
   * run
   */
  public void run() {
      TestCentralRepositoryConnection tcrc = null;
    try {
        tcrc = TestCentralRepositoryConnection.getInstance();
        tcrc.openConnection();
    } catch (InstantiationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    for (int i=0; i<testCaseSuite.getTestCases().size(); i++){
    	TestCaseInstruction tci = ((TestCaseInstruction)testCaseSuite.getTestCases().get(i));
       String a = tci.getStartInstruction().getContent();
       String tcID = this.getTestCentralLabelFromPackageLabel(a);

       String tcResult =  tci.getResult();
       String elapsedTime = this.elapsedMinute(tci.getElapsedTime());

       String plan = this.testInfo.getTestplan();
       String band = this.testInfo.getCycle();

       try {
           tcrc.reportResult(this.testInfo.getCoreid(),plan,band,tcID,tcResult,elapsedTime);

    	} catch (SQLException e1) {
    	    // TODO Auto-generated catch block
    	    e1.printStackTrace();
    	}

       ((NewTestCaseListener)testCaseListener.get(0)).newTestCaseAction( (TestCaseInstruction)testCaseSuite.getTestCases().get(i) );
    }
    ((NewTestCaseListener)testCaseListener.get(0)).newTestCaseAction( null );
    try {
        tcrc.closeConnection();
    } catch (SQLException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
  }
  
  private String elapsedMinute(String elapsedTime){
  	StringTokenizer time = new StringTokenizer(elapsedTime,":");
  	time.nextToken();
  	String min = time.nextToken();
  		int newMin = (Integer.parseInt(min) == 0) ? 1 : Integer.parseInt(min);
  	return String.valueOf(newMin);
  }
}
