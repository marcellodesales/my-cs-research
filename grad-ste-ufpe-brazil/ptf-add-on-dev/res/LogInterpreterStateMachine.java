/*
 * @created 05/04/2004 11:48:33
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Vector;

import br.ufpe.cin.stp.global.filemanager.FileLoader;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseSuite;

/**
 * @created 05/04/2004 11:48:33
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class LogInterpreterStateMachine {

    int lineIndex = -1;
    
    private final int PROCESSING_HARDWARE_IDENTIFICATION   = 0;
    private final int PROCESSING_TESTSUITE  = 1;
    private final int PROCESSING_SUMMARY    = 2;
    private final int PROCESSING_A_TESTCASE = 3;

    private final int SUBPROCESSING_EXCEPTION = 4;
    
    private int currentState = 0;
    
    private String logPath;
    
	/**
	 * <code>fileLines</code> is the lines of the log file implemented
	 * using a Vector().
	 */
	private List fileLines = new Vector();
	/**
	 * <code>tcs</code> is the suit of tests that holds all the
	 * TestCaseInstructions.
	 */
	private TestCaseSuite tcs = new TestCaseSuite();

	/**
	 * Creates a LogParser class according to a given log path.
	 * @param logPath The relative path of the log file in the .txt
	 * format.
	 * @throws FileNotFoundException If the given relative log path
	 * does not point to an existing file.
	 */
	public LogInterpreterStateMachine(String logPath) throws FileNotFoundException{
		this.logPath = logPath;
		this.fileLines = FileLoader.getInstance().getFileLines(this.logPath);
		this.interpretLog();
	}

	/**
	 * 08/06/2004 02:00:28<BR>
	 * Creater a parser with a list of lines of the log.
	 * @param logLinesList is the list of lines of the parser. That is
	 * a list of String objects.
	 */
	public LogInterpreterStateMachine(List logLinesList){
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
	 * @created 05/04/2004 12:05:30
	 * Switch to a state where the goal is to finish the processinig
	 */
	private void switchToProcessingSummary(){
	    this.currentState = PROCESSING_SUMMARY;
	}
	
	private void switchToProcessingTestSuite(){
	    this.currentState = PROCESSING_TESTSUITE;
	}
	
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
	
	public boolean isProcessingTestSuite(){
	    return this.currentState == PROCESSING_TESTSUITE;
	}
	
	private void ProcessHardwareIdentification(String logLine) throws LogTokenNotFoundException{
        NoteInstructionExpression note = new NoteInstructionExpression(logLine);
        if (note.hasHardwareName()){
            this.tcs.setHardware(note.getHardwareName());
            this.switchToProcessingTestSuite();
        }	    
	}
	
	private void interpretLog(){
	    
	    String logLine = "";
	    while (this.isProcessingHardware()){
	        int line = ++lineIndex;
	        logLine = (String)this.fileLines.get(line);
	        try {
	            this.ProcessHardwareIdentification(logLine);
            } catch (LogTokenNotFoundException e) {
                continue;
            }
	    }	    
	    testCaseSuite:
	    while (this.isProcessingTestSuite()){
	        int line = ++lineIndex;
	        logLine = (String)this.fileLines.get(line);
	        if (this.willProcessSummaryResult(logLine)){
	            this.switchToProcessingSummary();
	            System.out.println("---->>>> Finish log process!!");
	            break;
	        }else	        
	        if (logLine.indexOf(StartResultInstructionExpression.TOKEN) == -1)
	            continue;
	        else {
	            this.switchToProcessingATestCase();
	            TestCaseInstructionExpression tcie = null;
	            try {
                     tcie = new TestCaseInstructionExpression(new StartResultInstructionExpression(logLine));
                     System.out.println("Processing: "+tcie.getTestCaseInstruction().getTestCaseID());
                     this.tcs.add(tcie.getTestCaseInstruction());
                     this.processATestCase();
                     
                } catch (LogTokenNotFoundException e) {
                    //in the case the test case was not present... or was written wrong (without starting with TC_)
                    //  $TCM::com.motorola.testcase.messaging.iM.IM_SUITE_POSTCONDITION :TCM$
                    continue;
                }
	        }
	    }
	    this.tcs.finishTestCaseSuite();
	}
	
	private boolean willProcessAnException(String logLine){
	    return logLine.indexOf(ExceptionStepInstructionExpression.TOKEN) != -1 && !this.isProcessingException();	    
	}
	
	private boolean willProcessNormalInstruction(String logLine){
	    return logLine.indexOf(StepExecutionInstructionExpression.TOKEN) != -1;
	}
	
	private boolean willProcessEndResult(String logLine){
	    return logLine.indexOf(EndResultInstructionExpression.TOKEN) != -1;
	}
	
	private boolean willProcessSummaryResult(String logLine){
	    return logLine.indexOf("SUMMARY: ") != -1;
	}
	
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
	        if (logLine.equals("") || logLine.equals(" ")){
	            continue;
	        }else
	        if (this.willProcessEndResult(logLine)){
	            logLine = (String)this.fileLines.get(++this.lineIndex);
	            
	            ResultStepInstructionExpression rsie = null;
                try {
                    rsie = new ResultStepInstructionExpression(logLine);                    
                } catch (LogTokenNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Finalizing Test Case: "+rsie.getResult());
                System.out.println("Size: "+this.tcs.getTestCases().size());
                System.out.println();
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
                    System.out.println("\t*Exception Occurred: "+exp.getLine());
                } catch (LogTokenNotFoundException e) {
                    e.printStackTrace();
                }
                while (true){
                    try {
                        int line2 = ++lineIndex;
                        String logl = (String)this.fileLines.get(line2);
                        System.out.println(logl);
                        exp.addExceptionAdditional(logl);
                    } catch (LogTokenNotFoundException e1) {
                        this.tcs.getCurrentInstruction().addInstruction(exp.getInstruction());
                        --this.lineIndex;
                        continue processingATestCase;
                    }
                }
	            
	        } else
		    if (this.willProcessNormalInstruction(logLine)){
		        try {
                    this.tcs.getCurrentInstruction().addInstruction(new StepExecutionInstructionExpression(logLine).getInstruction());
                } catch (LogTokenNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
		    } else {
		        this.tcs.getCurrentInstruction().addInstruction((new SimpleStepInstructionExpression(logLine)).getInstruction());
		    }
	    }
	}
	
	public TestCaseSuite getTestCaseSuite(){
	    return this.tcs;
	}
	
	public static void main(String[] args) {
        try {
            LogInterpreterStateMachine l = new LogInterpreterStateMachine("C:/ptf_log_E1000.txt");
            
            if (l.getTestCaseSuite().getLastIncompleteTestCase() != null){
                System.out.println("incomplete...");
                System.out.println(l.getTestCaseSuite().getLastIncompleteTestCase().getTestCaseID());
            }
          
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
