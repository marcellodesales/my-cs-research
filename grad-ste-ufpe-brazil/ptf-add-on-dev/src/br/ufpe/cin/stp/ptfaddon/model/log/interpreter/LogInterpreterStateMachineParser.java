/*
 * @created 05/04/2004 11:48:33
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import br.ufpe.cin.stp.global.filemanager.FileLoader;
import br.ufpe.cin.stp.ptfaddon.model.log.ExceptionInstruction;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite;

/**
 * This class is intended to interpret and parse a log file.
 * @created 05/04/2004 11:48:33
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class LogInterpreterStateMachineParser {

    int lineIndex = -1;
    
    /**
     * <code>PROCESSING_HARDWARE_IDENTIFICATION</code> defines the state of searching for the hardware identification
     */
    private final int PROCESSING_HARDWARE_IDENTIFICATION   = 0;
    /**
     * <code>PROCESSING_TESTSUITE</code> defines the state of processing the test cases suite
     */
    private final int PROCESSING_TESTSUITE  = 1;
    /**
     * <code>PROCESSING_SUMMARY</code> defines the state of processing the summary
     */
    private final int PROCESSING_SUMMARY    = 2;
    /**
     * <code>PROCESSING_A_TESTCASE</code> defines the state of processing a test case
     */
    private final int PROCESSING_A_TESTCASE = 3;
    /**
     * <code>SUBPROCESSING_EXCEPTION</code> defines the state of processing an exception inside the state of processing a test case
     */
    private final int SUBPROCESSING_EXCEPTION = 4;

    private final int PROCESSING_BAD_FORMAT = 5;
    /**
     * <code>currentState</code> defines the current state of the interpreter machine
     */
    private int currentState = 0;
    /**
     * <code>logPath</code> defines the log file path
     */
    private String logPath;
    
	/**
	 * <code>fileLines</code> is the lines of the log file implemented
	 * using a Vector().
	 */
	private List fileLines = new LinkedList();
	/**
	 * <code>tcs</code> is the suit of tests that holds all the
	 * TestCaseInstructions.
	 */
	private TestCaseSuite tcs = new TestCaseSuite();

	private LogInterpreterException badFormatExceptionReason;
	
	/**
	 * Creates a LogParser class according to a given log path.
	 * @param logPath The relative path of the log file in the .txt
	 * format.
	 * @throws FileNotFoundException If the given relative log path
	 * does not point to an existing file.
	 */
	public LogInterpreterStateMachineParser(String logPath) throws FileNotFoundException{
		this.logPath = logPath;
		this.fileLines = FileLoader.getInstance().getFileLines(this.logPath);
		this.interpretLog();
	}
	
	/**
	 * Sets the reason for a bad format of the log
	 * @created 28/10/2004 11:26:10
	 * @param lie
	 */
	private void setBadFormatReason(LogInterpreterException lie){
	    this.badFormatExceptionReason = lie;
	}

	/**
	 * 08/06/2004 02:00:28<BR>
	 * Creater a parser with a list of lines of the log.
	 * @param logLinesList is the list of lines of the parser. That is
	 * a list of String objects.
	 */
	public LogInterpreterStateMachineParser(List logLinesList){
	    this.fileLines = logLinesList;
		this.interpretLog();
	}
	
	/**
	 * @created 05/04/2004 12:04:29
	 * Switch to a state where the goal is to identify the hardware description
	 */
	private void switchToProcessingHardware(){
	    this.currentState = PROCESSING_HARDWARE_IDENTIFICATION;
	}
	
	/**
	 * @created 05/04/2004 12:04:59
	 * Switch to a state where the goal is to identify the test case
	 */
	private void switchToProcessingATestCase(){
	    this.currentState = PROCESSING_A_TESTCASE;
	}
	
	/**
	 * @created 05/04/2004 12:04:59
	 * Switch to a state where the goal is to identify the test case
	 */
	private void switchToProcessingBadFormat(LogInterpreterException lie){
	    this.currentState = PROCESSING_BAD_FORMAT;
	    this.setBadFormatReason(lie);
	}	
	
	/**
	 * @created 05/04/2004 12:05:30
	 * Switches to a state where the goal is to finish the processinig
	 */
	private void switchToProcessingSummary(){
	    this.currentState = PROCESSING_SUMMARY;
	}
	
	/**
	 * @created 05/04/2004 22:33:57
	 * Switches to a state where the goal is to get information of all test cases
	 */
	private void switchToProcessingTestSuite(){
	    this.currentState = PROCESSING_TESTSUITE;
	}
	
	/**
	 * @created 05/04/2004 22:34:33
	 * Switches to a state where the goal is to process an exception
	 */
	private void switchToProcessingException(){
	    this.currentState = SUBPROCESSING_EXCEPTION;
	}
	
	/**
	 * @created 05/04/2004 12:05:55
	 * @return if the state is Processinig the hardware identification
	 */
	public boolean isProcessingHardware(){
	    return this.currentState == PROCESSING_HARDWARE_IDENTIFICATION; 
	}
	
	/**
	 * @created 05/04/2004 22:34:55
	 * @return if the state is processing an exception 
	 */
	public boolean isProcessingException(){
	    return this.currentState == SUBPROCESSING_EXCEPTION;
	}
	
	/**
	 * @created 05/04/2004 12:06:25
	 * @return if the state is processinig the summary
	 */
	public boolean isProcessingSummary(){
	    return this.currentState == PROCESSING_SUMMARY;
	}
	
	/**
	 * @created 05/04/2004 12:06:41
	 * @return if the state is processing a test case
	 */
	public boolean isProcessingATestCase(){
	    return this.currentState == PROCESSING_A_TESTCASE;
	}
	
	/**
	 * @created 05/04/2004 22:35:14
	 * @return if the current state is processing the test cases suite
	 */
	public boolean isProcessingTestSuite(){
	    return this.currentState == PROCESSING_TESTSUITE;
	}
	
	/**
	 * @created 05/04/2004 22:35:14
	 * @return if the current state is processing a bad format
	 */
	public boolean hasBadFormat(){
	    return this.currentState == PROCESSING_BAD_FORMAT;
	}	
	
    /**
     * @created 28/10/2004 11:56:11
     * @return Returns the badFormatExceptionReason.
     */
    public LogInterpreterException getBadFormatExceptionReason() {
        return this.badFormatExceptionReason;
    }
	/**
	 * Process the identification of the hardware for a ginen log line
	 * @created 05/04/2004 22:35:35
	 * @param logLine
	 * @throws LogTokenNotFoundException if the NOTE: token is not found on the line
	 */
	private void ProcessHardwareIdentification(String logLine){
        NoteInstructionExpression note = null;
        try {
            note = new NoteInstructionExpression(logLine);
        } catch (LogTokenNotFoundException e) {
        }
        if (note.hasHardwareName()){
            this.tcs.setHardware(note.getHardwareName());
            this.switchToProcessingTestSuite();
        }	    
	}
	

	/**
	 * @created 05/04/2004 22:36:43
	 * @param logLine
	 * @return if the given log line is for processing an exception
	 */
	private boolean willProcessAnException(String logLine){
	    return logLine.indexOf(ExceptionStepInstructionExpression.TOKEN) != -1 && !this.isProcessingException() && logLine.indexOf(NoteInstructionExpression.TOKEN) == -1;	    
	}
	
	/**
	 * @created 05/04/2004 22:37:18
	 * @param logLine
	 * @return if the given log line is for processing a normal instruction
	 */
	private boolean willProcessNormalInstruction(String logLine){
	    return logLine.indexOf(StepExecutionInstructionExpression.TOKEN) != -1;
	}
	
	/**
	 * @created 05/04/2004 22:37:37
	 * @param logLine
	 * @return if the given log line is for processing an end result
	 */
	private boolean willProcessEndResult(String logLine){
	    return logLine.indexOf(EndResultInstructionExpression.TOKEN) != -1;
	}
	
	/**
	 * @created 05/04/2004 22:37:52
	 * @param logLine
	 * @return if the given log line is for processing a summary
	 */
	private boolean willProcessSummaryResult(String logLine){
	    return logLine.indexOf("SUMMARY: ") != -1;
	}	
	
	/**
	 * @created 28/10/2004 10:07:13
	 * @param logLine
	 * @return if the given log line contains the start log
	 */
	private boolean hasFoundATestCase(String logLine){
	    return logLine.indexOf(StartResultInstructionExpression.TOKEN) != -1;
	}
	
	private boolean hasFoundAHardwareModel(String logLine){
	    return logLine.indexOf(NoteInstructionExpression.TOKEN) != -1 && logLine.indexOf("Model: ") != -1;
	} 
	
	/**
	 * @return verify if the end of the file was reached.
	 */
	private boolean hasFoundEndOfLogFile(){
		return this.fileLines.size()-1 == this.lineIndex;
	}
	
	/**
	 * @created 05/04/2004 22:36:17
	 * The main method where the log is interpreted 
	 */
	private void interpretLog(){
	    
	    String logLine = "";
	    while (this.isProcessingHardware()){
	    	
	        if (hasFoundEndOfLogFile()){
                this.switchToProcessingBadFormat(new LogInterpreterException("There is no test case instruction on the log file!",""));
                break;	         
	        }    	
	    	
	    	logLine = (String)this.fileLines.get(++this.lineIndex);
	        
	        if (this.hasFoundATestCase(logLine)){
	            --this.lineIndex;
	            this.switchToProcessingTestSuite();
	            break;
	        } 
	        
	        if (this.hasFoundAHardwareModel(logLine))
	            this.ProcessHardwareIdentification(logLine);
	    }	    
	    
	    testCaseSuite:
	    while (this.isProcessingTestSuite()){
	        
	    	if (hasFoundEndOfLogFile()){
                this.switchToProcessingBadFormat(new LogInterpreterException("There is no test case instruction on the log file!",""));
                break;	         
	        }    	
	        
	        logLine = (String)this.fileLines.get(++lineIndex);
	        
	        if (this.willProcessSummaryResult(logLine)){
	            this.switchToProcessingSummary();
	            break;
	        }else	        
	        if (this.hasFoundATestCase(logLine)){
	            this.switchToProcessingATestCase();
	            TestCaseInstructionExpression tcie = null;
	            try {
                     tcie = new TestCaseInstructionExpression(new StartResultInstructionExpression(logLine));
                     this.tcs.add(tcie.getTestCaseInstruction());
                     this.processATestCase();
                     
                } catch (LogInterpreterException lie) {
                    this.switchToProcessingBadFormat(lie);
                    break;
                }
	        }
	    }
	    if (!this.hasBadFormat())
	        this.tcs.finishTestCaseSuite();
	}
	
	/**
	 * @created 05/04/2004 22:38:12
	 * Processes a test case 
	 */
	private void processATestCase(){
	    
	    String logLine = "";
	    processingATestCase:
	    while (this.isProcessingATestCase() || this.isProcessingException()){
	        int line = ++lineIndex;
	        if (this.fileLines.size() == line){
	            this.switchToProcessingSummary();
	            break;
	        }
	        logLine = (String)this.fileLines.get(line); 
	        if (logLine.trim().equals("")){
	            continue;
	        }else
	        if (this.willProcessEndResult(logLine)){
	            logLine = (String)this.fileLines.get(++this.lineIndex);
	            
	            ResultStepInstructionExpression rsie = null;
                try {
                    rsie = new ResultStepInstructionExpression(logLine);                    
                } catch (LogInterpreterException lie) {
                	this.switchToProcessingBadFormat(lie);
                    break;                    
                }
                TestCaseInstruction tci = this.tcs.getCurrentInstruction(); 
	            tci.setEndInstruction(rsie.getInstruction());
	            tci.setResult(rsie.getResult());
	            this.tcs.processResult(tci.getResult());
	            this.switchToProcessingTestSuite();
	            
	        } else
	        if (this.willProcessAnException(logLine)){
	            ExceptionStepInstructionExpression exp = null;
	            try {
	                exp = new ExceptionStepInstructionExpression(logLine);
                    this.tcs.getCurrentInstruction().addInstruction(exp.getInstruction());
                } catch (LogTokenNotFoundException lie) {
                	this.switchToProcessingBadFormat(lie);
                    break;
                }
                while (true){
                    try {
                        int line2 = ++lineIndex;
                        String logl = (String)this.fileLines.get(line2);
                        exp.addExceptionAdditional(logl);
                    } catch (LogTokenNotFoundException e1) {
                        --this.lineIndex;
                        continue processingATestCase;
                    }
                }
	            
	        } else
		    if (this.willProcessNormalInstruction(logLine)){
		        try {
                    this.tcs.getCurrentInstruction().addInstruction(new StepExecutionInstructionExpression(logLine).getInstruction());
                } catch (LogTokenNotFoundException lie) {
                	this.switchToProcessingBadFormat(lie);
                    break;
                }
		    } else this.tcs.getCurrentInstruction().addInstruction((new SimpleStepInstructionExpression(logLine)).getInstruction());
	    }
	}
	
	/**
	 * @created 05/04/2004 22:38:28
	 * @return the test cases suite object containing all information about the log
	 */
	public TestCaseSuite getTestCaseSuite(){
	    return this.tcs;
	}
	
	public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            LogInterpreterStateMachineParser l = new LogInterpreterStateMachineParser("C:/ptf_log_NO_TESTS.txt");
            long end = System.currentTimeMillis();
            System.out.println(end-start+" milliseconds...");
            
            if (l.getTestCaseSuite().getLastIncompleteTestCase() != null){
                System.out.println("incomplete...");
                System.out.println(l.getTestCaseSuite().getLastIncompleteTestCase().getTestCaseID());
            }
            
            if (l.hasBadFormat()){
                LogInterpreterException lie = l.getBadFormatExceptionReason();
                System.out.println(lie.getMessage());
                System.out.println(lie.getLogLine());
            }
                
            System.out.println(l.getTestCaseSuite().getHardware());
            
            TestCaseSuite suite = l.getTestCaseSuite();
            Iterator i = suite.getTestCaseIterator();
            while (i.hasNext()){
                TestCaseInstruction tci = (TestCaseInstruction)i.next();
                System.out.println(tci.getTestCaseID()+" - "+tci.getElapsedTime());
                ExceptionInstruction[] ins = tci.getExceptionInstructions();
                for (int j = 0; j < ins.length; j++) {
                    System.out.println("\t"+ins[j]);
                }
            }
          
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
