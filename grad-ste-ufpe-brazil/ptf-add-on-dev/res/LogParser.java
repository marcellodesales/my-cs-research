/*
 * Created on 30/04/2004 20:37:04
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 *
 * */

package br.ufpe.cin.stp.ptfaddon.model.log;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import br.ufpe.cin.stp.global.filemanager.FileLoader;

/**
 * The Log parser class is responsible for parsing a log file in a .txt
 * extension. The log is according to Center of Informatics (CIn)-UFPE's patterns. Basically
 * the parser must build a structure, that will be encapsulated in a
 * TestCaseSuilt class.
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 30/04/2004
 */
public class LogParser {

	/**
	 * <code>TOKEN_START_RESULT</code> is the 'START: ' token that
	 * describes the start of a test case. This will be mapped to a
	 * TestCaseInstruction class.
	 */
    private final String TOKEN_START_RESULT   = "START:";
	/**
	 * <code>TOKEN_END_RESULT</code> is the 'END: ' token that describes
	 * the end of a test case. This token is used to get the final resul
	 * of the test case, as well as the time the test case finished and
	 * also used to calculate the elapsed time of the test case.
	 */
	private final String TOKEN_END_RESULT     = "END:";
	/**
	 * <code>TOKEN_EXCEPTION</code> is the exception token used to identify
	 * the occorrence of an exception. The token will be encapsulated on an
	 * ExceptionInstruction class.
	 */
	private final String TOKEN_EXCEPTION      = "Exception";
	/**
	 * <code>TOKEN_STEP_EXECUTION</code> is the 'DBG1: ' token commonly
	 * used to ordinary descriptions of execution. This token will be encapsulated
	 * on a Instruction class.
	 */
	private final String TOKEN_STEP_EXECUTION = "DBG1: ";
	/**
	 * <code>TOKEN_PASS_RESULT</code> The 'PASS: ' is identified by this
	 * constant in order to identify when a test case had passe. This
	 * will be encapsulated on the TestCaseInstruction class as a property.
	 */
	private final String TOKEN_PASS_RESULT    = "PASS: ";
	/**
	 * <code>TOKEN_FAIL_RESULT</code> is the 'FAIL: ' token used to
	 * describe when a the test case failed. This will be encapsulated
	 * on the TestCaseInstruction class as a property.
	 */
	private final String TOKEN_FAIL_RESULT    = "FAIL: ";
	/**
	 * <code>TOKEN_NOTE</code> is the 'NOTE: ' token that helps to
	 * identify the hardware used on the tests. The description of
	 * the used hardware will be encapsulated on the TestCaseSuit
	 * class as a property.
	 */
	private final String TOKEN_NOTE           = "NOTE: ";
	/**
	 * <code>TOKEN_NOTCONFIGURED_RESULT</code> is the
	 * 'NOT CONFIGURED: ' token used to identify when a test case
	 * was not configured. The description will be encapsulated on the
	 * TestCaseInstruction as a property.
	 */
	private final String TOKEN_NOTCONFIGURED_RESULT = "NOT CONFIGURED:";
	/**
	 * <code>TOKEN_UNKNOWN_RESULT</code> is 'Unknown Result:' token
	 * used to identify when the result of a test case is not known.
	 * The description will be encapsulated on the TestCaseInstruction
	 * as a property.
	 */
	private final String TOKEN_UNKNOWN_RESULT = "Unknown Result:";
	/**
	 * <code>logPath</code> is the relative path of the .txt log file.
	 */
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
	public LogParser(String logPath) throws FileNotFoundException{
		this.logPath = logPath;
		this.setParser(FileLoader.getInstance().getFileLines(this.logPath));
	}

	/**
	 * 08/06/2004 02:00:28<BR>
	 * Creater a parser with a list of lines of the log.
	 * @param logLinesList is the list of lines of the parser. That is
	 * a list of String objects.
	 */
	public LogParser(List logLinesList){
	    this.setParser(logLinesList);
	}

	/**
	 * Sets the default behaviour of the parser.
	 * (08/06/2004 01:57:24)
	 * @param linesList
	 */
	private void setParser(List linesList){
		this.fileLines = linesList;
		this.parse();
		this.finalCheck();
	}

	/**
	 * Finalizes the parse, verifies if the log is complete and correct the list
	 * of complete test cases.
	 * <BR>17/06/2004 21:33:00
	 */
	private void finalCheck(){
		if (!this.tcs.getCurrentInstruction().hasEndInstruction())
			this.tcs.setLastIncompleteTestCase();
	}

	/**
	 * @param resultLine: It's a line that has a token
	 * @return The identification of the result.
	 * <BR>08/06/2004 08:16:12
	 */
	private String processResult(String resultLine){
		String result = "UNDEFINED";
		if (this.stringContains(resultLine,this.TOKEN_PASS_RESULT)){
			result = TestCaseSuite.RESULT_PASSED;
			this.tcs.processResult(TestCaseSuite.RESULT_PASSED);
		} else
		if (this.stringContains(resultLine,this.TOKEN_FAIL_RESULT)){
			result = TestCaseSuite.RESULT_FAILED;
			this.tcs.processResult(TestCaseSuite.RESULT_FAILED);
		} else
		if (this.stringContains(resultLine,this.TOKEN_NOTCONFIGURED_RESULT)){
			result = TestCaseSuite.RESULT_NOTCONFIGURED;
			this.tcs.processResult(TestCaseSuite.RESULT_NOTCONFIGURED);
		} else
		if (this.stringContains(resultLine,this.TOKEN_UNKNOWN_RESULT)){
			result = TestCaseSuite.RESULT_UNKNOWN;
			this.tcs.processResult(TestCaseSuite.RESULT_UNKNOWN);
		}
		return result;
	}

	/**
	 * 08/06/2004 08:15:52
	 * Parse the log file and generates the tree on the TestCaseSuit
	 * object. It will parse each line of the list used.
	 */
	private void parse(){
		Iterator iter = this.fileLines.iterator();
		while (iter.hasNext()) {
			String element = (String) iter.next();
			//System.out.println(element);
			if (element == null || element.equals(" ") || element.equals("")) continue;
			if (this.isResultTestCase(element)) //first process the test case result
				this.tcs.getCurrentInstruction().setResult(this.processResult(element));
			else //	process the test case start, the first time
			if ((this.containsToken(element) && this.isProcessingTestCase()) || this.isFirstTestCase(element) || this.hasProcessedNodeToken(element)){
				this.analizeLine(element);
			}else
			// Process the test case when an exception was caught
			if (this.isProcessingTestCase() && this.isProcessingException()){
				TestCaseInstruction tci = this.tcs.getCurrentInstruction();
                ((ExceptionInstruction)tci.getExceptionInstructions()[0]).add(element);
				//((ExceptionInstruction)tci.getExceptionInstructions()[0]).add(element);
			}else {
				//process other instructions, but only if there already exists a test case
				//on if only the test case doesn't have an end instruction
				if (this.tcs.size() > 0){
					TestCaseInstruction tci = this.tcs.getCurrentInstruction();
					if (!tci.hasEndInstruction())
						tci.addInstruction(new Instruction(element));
				}
			}
		}
	}

	/**
	 * Verifies if on this point of the parser execution the given line
	 * contains a node token to get the hardware used.
	 * (08/06/2004 01:43:10)
	 * @param line is the line with a Note token.
	 * @return true if the line contains a note token and the hardware
	 * was not found yet.
	 */
	private boolean hasProcessedNodeToken(String line){
		return (this.stringContains(line, this.TOKEN_NOTE) && this.tcs.getHardware() == null);
	}

	/**
	 * @param line
	 * @return If the given line is contains the result of the test case.
	 * 08/06/2004 08:17:09
	 */
	private boolean isResultTestCase(String line){
		return (this.stringContains(line,this.TOKEN_PASS_RESULT) || this.stringContains(line,this.TOKEN_FAIL_RESULT) || this.stringContains(line,this.TOKEN_NOTCONFIGURED_RESULT) || this.stringContains(line,this.TOKEN_UNKNOWN_RESULT)) && (this.tcs.size()!= 0);
	}

	/**
	 * @return If there's an exception being processed.
	 * 02/06/2004 16:10:04
	 */
	private boolean isProcessingException(){
       // return (this.tcs.getCurrentInstruction()).getExceptionInstructions()[0] instanceof ExceptionInstruction;
       if (this.tcs.getCurrentInstruction().exceptionsOcorred() ){
         return (this.tcs.getCurrentInstruction().getLastInstruction() instanceof ExceptionInstruction);
       }else return false;               
	}

	/**
	 * @param line
	 * @return If it's the first time a test case appeared.
	 * 02/06/2004 14:21:21
	 */
	private boolean isFirstTestCase(String line){
		return (this.stringContains(line,this.TOKEN_START_RESULT) && this.tcs.size()== 0) || (this.stringContains(line,this.TOKEN_START_RESULT) && this.tcs.size()!= 0);
	}

	/**
	 * @return if there's a test case being processed at the moment.
	 * 02/06/2004 14:23:42
	 * Marcello
	 */
	private boolean isProcessingTestCase(){
		boolean processing = false;
		if ((tcs.size() != 0) && !this.tcs.getCurrentInstruction().hasEndInstruction())
			processing = true;
		return processing;
	}

	/**
	 * Helper method to verify if a string contains another one.
	 * (08/06/2004 01:41:07)
	 * @param source is the string to ve verified.
	 * @param substring is the substring to be looked for in the source.
	 * @return
	 */
	private boolean stringContains(String source, String substring){
		return source.indexOf(substring) != -1;
	}

	/**
	 * 08/06/2004 01:32:05
	 * Verifies if a given line contains a token.
	 * @param line is a given line of the .txt log file.
	 * @return true if the line contains one of the given tokes.
	 */
	private boolean containsToken(String line){
		return this.stringContains(line, this.TOKEN_START_RESULT)   ||
		       this.stringContains(line, this.TOKEN_STEP_EXECUTION) ||
			   this.stringContains(line, this.TOKEN_EXCEPTION)      ||
			   this.stringContains(line, this.TOKEN_END_RESULT)     ||
			   this.stringContains(line, this.TOKEN_NOTE);
	}

	/**
	 * Process a give line according to the tokens.
	 * @param line
	 * 08/06/2004 16:14:38
	 */
	private void analizeLine(String line){
		if (this.stringContains(line, this.TOKEN_START_RESULT))
			this.processStartToken(line);
		else
		if (this.stringContains(line, this.TOKEN_STEP_EXECUTION))
			this.processStepExecToken(line);
		else
		if (this.stringContains(line, this.TOKEN_EXCEPTION) && this.isProcessingTestCase())
			this.processExceptionToken(line);
		else
		if (this.stringContains(line, this.TOKEN_END_RESULT))
			this.processEndToken(line);
		else
		if (this.stringContains(line, this.TOKEN_NOTE))
			this.processNoteToken(line);		
	}

	/**
	 * Gets the hardware tested.
	 * @param noteLine is a line from the log that has the hardware
	 * information.
	 * 17/06/2004 21:25:42
	 */
	private void processNoteToken(String noteLine){
		StringTokenizer instruction = new StringTokenizer(noteLine," ");
		instruction.nextToken();
		instruction.nextToken();
		instruction.nextToken();

		if (instruction.nextToken().equals("Model:")){
			while (instruction.hasMoreTokens()){
				String model = instruction.nextToken();
				if (model.startsWith("(")){
					model = model.substring(1);
					model = model.substring(0,model.length()-1);
					this.tcs.setHardware(model);
					break;
				}
			}
		}
	}

	private Instruction createInstruction(String dateToken, String contentToken){
		StringTokenizer dateTime = new StringTokenizer(dateToken,"/");
		String date = dateTime.nextToken();
		String time = dateTime.nextToken();
		time = time.replace('.',':');
		Instruction instr = new Instruction(contentToken);
		instr.setTime(date,time);
		return instr;
	}

	/**
	 * PreCondition: The TestCaseSuit exists;
	 * Postcondition: A new TestCaseInstruction will be created and
	 * used to be populated with other Instruction objects.
	 * @param startTokenLine - The line that has a start token
	 * 08/06/2004 16:15:12
	 */
	private void processStartToken(String startTokenLine){
		StringTokenizer instruction = new StringTokenizer(startTokenLine," ");
		instruction.nextToken();
		instruction.nextToken();
		String dateTimeToken = instruction.nextToken();

		String content = startTokenLine.substring(startTokenLine.indexOf(" - "));
		instruction.nextToken();
		content = instruction.nextToken();
	
		TestCaseInstruction tci = new TestCaseInstruction(this.createInstruction(dateTimeToken,content));
		this.tcs.add(tci);
	}
	
	/**
	 * PreCondition: The TestCaseSuit exists and is processing a TestCaseInstruction;
	 * Postcondition: The TestCaseInstruction that's currently being processed.
	 * @param stepExecLine - The line that has a step token
	 * will add a new StepInstruction representing this line.
	 * 08/06/2004 16:18:00
	 */
	private void processStepExecToken(String stepExecLine){
		StringTokenizer instruction = new StringTokenizer(stepExecLine," ");
		instruction.nextToken();
		String dateToken = instruction.nextToken();

		String content = stepExecLine.substring(stepExecLine.indexOf(" - "));
		content = content.substring(3);

		this.tcs.getCurrentInstruction().addInstruction(this.createInstruction(dateToken,content));
	}

	/**
	 * <BR>PreCondition: The TestCaseSuit exists and is processing a TestCaseInstruction;
	 * <BR>Postcondition: The TestCaseInstruction that's currently being processed.
	 * @param endTokenLine - The line that has an end toke will set
	 * the end token repreenting this end line.
	 * 08/06/2004 16:21:01
	 */
	private void processEndToken(String endTokenLine){
		StringTokenizer instruction = new StringTokenizer(endTokenLine," ");
		instruction.nextToken();
		instruction.nextToken();
		String dateToken = instruction.nextToken();

		String content = endTokenLine.substring(endTokenLine.indexOf(" - "));
		instruction.nextToken();
		content = instruction.nextToken();

		this.tcs.getCurrentInstruction().setEndInstruction(this.createInstruction(dateToken,content));
	}

	/**
	 * @param finalTokenLineThe - line that has an exception line
	 * <BR>PreCondition: The TestCaseSuit exists and is processing a TestCaseInstruction;
	 * <BR>Postcondition: The TestCaseInstruction that's currently being processed
	 * will add a new ExceptionInstruction.
	 * 08/06/2004 16:22:02
	 */
	private void processExceptionToken(String finalTokenLine){
		ExceptionInstruction ei = new ExceptionInstruction(finalTokenLine);
		this.tcs.getCurrentInstruction().addInstruction(ei);
	}

	/**
	 * @return Gets the TestCaseSuit from the log.
	 * 13/06/2004 13:20:55
	 */
	public TestCaseSuite getTestCases() {
		return this.tcs;
	}

	public static void main(String[] args) {
		LogParser log = null;
		try {

            long start = System.currentTimeMillis();
			log = new LogParser("C:/ptf_log_E1000.txt");
            long end = System.currentTimeMillis();
            System.out.println(end-start+" milliseconds...");
            
            TestCaseSuite suite = log.getTestCases();
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
			e.printStackTrace();
		}

		/*System.out.println(log.tcs.getHardware());
		System.out.println(log.tcs.getDate());

		System.out.println("Tempo total: "+log.tcs.getElapsedTime());

		List testcases = log.tcs.getTestCases();

		//Comparator id = new BeanPropertyComparator("testCaseId");
		//Collections.sort(testcases,id);

		Iterator iter = testcases.iterator();
		while (iter.hasNext()) {
			TestCaseInstruction tci = (TestCaseInstruction)iter.next();
			//if (tci.getResult().equals("PASSED"))
			System.out.println("-->>> "+tci.getContent()+" <<-----");
			
			ExceptionInstruction[] exceptions = tci.getExceptionInstructions();
			
			for (int i = 0; i < exceptions.length; i++) {
                Iterator a = exceptions[i].getExceptionContent();
                while (a.hasNext()) {
                    Instruction instr = (Instruction) a.next();
                    System.out.println(instr.getContent());
                }
            }
			/*Iterator it = tci.iteratorInstructions();
			while (it.hasNext()) {
				Instruction element = (Instruction) it.next();
				System.out.println(element.getContent());
			}
			
			//System.out.println(tci.getLastInstruction().getContent());
		}*/
	}
}
