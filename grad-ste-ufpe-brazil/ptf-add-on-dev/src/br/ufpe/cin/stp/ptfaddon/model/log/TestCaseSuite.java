/*
 * Created on 02/06/2004 11:43:45
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import br.ufpe.cin.stp.global.DateUtils;

/**
 * The main component of the log package is the TestCaseSuit, which
 * is responsible for managing the results of each test case. It's also
 * responsible for information like the whole elapsed time between the 
 * first step of the start TestCaseInstruction of the last step instruction
 * of the last TestCaseInstruction, among others.   
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 02/06/2004 
 */
public class TestCaseSuite {

	/** The passed result representation "PASSED" */
	public static final String RESULT_PASSED = "PASSED";
	/** The failed result representation "FAILED" */
	public static final String RESULT_FAILED = "FAILED";
	/** The not configured result representation "NOT CONFIGURED" */
	public static final String RESULT_NOTCONFIGURED = "NOT CONFIGURED";
	/** The passed result representation "UNKNOWN" */
	public static final String RESULT_UNKNOWN = "UNKNOWN";
	/**
	 * <code>testCases</code> is the list of the testcases 
	 * (TestCaseInstruction) instances.
	 */
	private List testCases = new LinkedList();
	/**
	 * <code>lastIncompleteTsi</code> is the last TestCaseInstruction 
	 * in case of incomplete test results.
	 */
	private TestCaseInstruction lastIncompleteTsi;
	/**
	 * <code>date</code> is the date the tests was done.
	 */
	private Date date;
	/**
	 * <code>passed</code> holds the number of test cases that passed.
	 */
	private int passed;
	/**
	 * <code>failed</code> holds the number of test cases that failed.
	 */
	private int failed;
	/**
	 * <code>notConf</code> holds the number of test cases that was not configured.
	 */
	private int notConf;
	/**
	 * <code>unknown</code> holds the number of test cases that has unknown as
	 * a result.
	 */
	private int unknown;
	/**
	 * <code>hardware</code> is the description of the hardware used
	 * on the test.
	 */
	private String hardware;

	/**
	 * Adds a TestCaseInstruction to the TestCaseSuit. It's implemented
	 * via the List methods.
	 * (08/06/2004 01:48:36)
	 * @param testCase
	 */
	public void add(TestCaseInstruction testCase){
		this.testCases.add(testCase);
	}
	
	/**
	 * @param resultConst is the result found mapped on the static constants
	 * 08/06/2004 13:50:19
	 */
	public void processResult(String resultConst){
		if (resultConst.equals(RESULT_PASSED))
			this.passed++;
		else 
		if (resultConst.equals(RESULT_NOTCONFIGURED))
			this.notConf++;
		else 
		if (resultConst.equals(RESULT_FAILED))
			this.failed++;
		else 
		if (resultConst.equals(RESULT_UNKNOWN))
			this.unknown++;
	}
	
	/**
	 * 08/06/2004 13:49:54
	 * @return The number of TestCaseInstructions on the suit
	 */
	public int size(){
		return this.testCases.size();
	}

	/**
	 * 08/06/2004 13:49:02
	 * @return The last instruction on the list of TestCaseInstructions.
	 */
	public TestCaseInstruction getCurrentInstruction(){
		return (TestCaseInstruction)this.testCases.get(this.testCases.size()-1);
	}
	
	/**
	 * (08/06/2004 01:49:31)
	 * @return The Iterator of the test cases.
	 */
	public Iterator getTestCaseIterator(){		
		return this.testCases.iterator();
	}
	
	/**
	 * 17/06/2004 21:31:17
	 * Sets the last incomplete test case instruction if the parser detected
	 * that the log is incomplete.
	 */
	public void setLastIncompleteTestCase(){
		TestCaseInstruction tci = (TestCaseInstruction)this.testCases.get(this.testCases.size()-1);
		this.testCases.remove(this.testCases.size()-1);
		this.lastIncompleteTsi = tci;
	}
	
	/**
	 * 17/06/2004 21:36:31
	 * @return Gets the last incomplete test case instruction.
	 */
	public TestCaseInstruction getLastIncompleteTestCase(){
		return this.lastIncompleteTsi;
	}
	
	/**
	 * 10/06/2004 17:26:58
	 * @return All test cases encapsulated on a list.
	 * @see java.util.List.
	 */
	public List getTestCases(){
		return this.testCases;
	}
	
	/**
	 * 08/06/2004 13:41:50
	 * @return Get the elapsed time between the first and the 
	 * final TestCaseInstruction on this TestCaseSuit. 
	 */
	public String getElapsedTime(){
		GregorianCalendar start = ((TestCaseInstruction)this.testCases.get(0)).getStartInstruction().getTime();
		GregorianCalendar end   = ((TestCaseInstruction)this.testCases.get(this.testCases.size()-1)).getEndInstruction().getTime();
		return DateUtils.getElapsedTime(start,end);
	}	
	
	/**
	 * 12/06/2004 22:33:12
	 * @return the date of the tests.
	 */
	public Date getDate() {
		GregorianCalendar start = ((TestCaseInstruction)this.testCases.get(0)).getStartInstruction().getTime();
		return start.getTime();
	}

	/**
	 * 12/06/2004 22:33:12
	 * Sets the date of the TestCaseInstruction.
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 12/06/2004 23:02:05
	 * @return the number of tests that failed.
	 */
	public int getFailed() {
		return failed;
	}

	/**
	 * 12/06/2004 23:02:05
	 * @return the number of tests that failed.
	 */
	public String getHardware() {
		return hardware;
	}

	/**
	 * 12/06/2004 23:02:05
	 * @return the number of tests that was not configured.
	 */
	public int getNotConf() {
		return notConf;
	}

	/**
	 * 12/06/2004 23:02:05
	 * @return The number of tests that passed.
	 */
	public int getPassed() {
		return passed;
	}

	/**
	 * 12/06/2004 23:02:05
	 * @return The number of test cases that has unknown result.
	 */
	public int getUnknown() {
		return unknown;
	}

	/**
	 * Sets the value of the used hardware.
	 * (08/06/2004 01:54:41)
	 * @param string The new description of the hardware used.
	 */
	public void setHardware(String string) {
		hardware = string;
	}
	
	/**
	 * @created 28/10/2004 11:47:52
	 * Clear the last test case which was not completed.
	 */
	public void finishTestCaseSuite(){
	    if (this.testCases.size() != 0){
			TestCaseInstruction tci = (TestCaseInstruction)this.testCases.get(this.testCases.size()-1);
			if (tci.getEndInstruction() == null){
			    this.testCases.remove(this.testCases.size()-1);
			    this.lastIncompleteTsi = tci;
			}
	    }
	}
}
