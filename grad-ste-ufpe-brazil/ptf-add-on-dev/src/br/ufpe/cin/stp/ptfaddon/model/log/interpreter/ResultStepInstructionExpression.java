/*
 * @created 23/09/2004 11:54:05
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

import java.util.StringTokenizer;

/**
 * @created 23/09/2004 11:54:05
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class ResultStepInstructionExpression extends AbstractResultInstructionExpression {

	/**
	 * <code>TOKEN_PASS_RESULT</code> The 'PASS: ' is identified by this
	 * constant in order to identify when a test case had passe. This
	 * will be encapsulated on the TestCaseInstruction class as a property.
	 */
	private static final String TOKEN_PASS_RESULT    = "PASS: ";
	/**
	 * <code>TOKEN_FAIL_RESULT</code> is the 'FAIL: ' token used to
	 * describe when a the test case failed. This will be encapsulated
	 * on the TestCaseInstruction class as a property.
	 */
	private static final String TOKEN_FAIL_RESULT    = "FAIL: ";
	/**
	 * <code>TOKEN_NOTCONFIGURED_RESULT</code> is the
	 * 'NOT CONFIGURED: ' token used to identify when a test case
	 * was not configured. The description will be encapsulated on the
	 * TestCaseInstruction as a property.
	 */
	private static final String TOKEN_NOTCONFIGURED_RESULT = "NOT CONFIGURED: ";
	/**
	 * <code>TOKEN_UNKNOWN_RESULT</code> is 'Unknown Result:' token
	 * used to identify when the result of a test case is not known.
	 * The description will be encapsulated on the TestCaseInstruction
	 * as a property.
	 */
	private static final String TOKEN_UNKNOWN_RESULT = "Unknown Result: ";
	
	/** The passed result representation "PASSED" */
	public static final String RESULT_PASSED = "PASSED";
	/** The failed result representation "FAILED" */
	public static final String RESULT_FAILED = "FAILED";
	/** The not configured result representation "NOT CONFIGURED" */
	public static final String RESULT_NOTCONFIGURED = "NOT CONFIGURED";
	/** The passed result representation "UNKNOWN" */
	public static final String RESULT_UNKNOWN = "UNKNOWN";	
	
	private String result;
	
    /**
     * @created 23/09/2004 14:32:08
     * @param line
     * @throws LogTokenNotFoundException
     */
    public ResultStepInstructionExpression(String line) throws LogInterpreterException {
        super(line);
        // TODO Auto-generated constructor stub
    }
    
    public String getResult(){
        return this.result;
    }

    /* @created 23/09/2004 15:29:46
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractInstructionExpression#getInstructionExpressionToken()
     */
    public String getInstructionExpressionToken() {
        return TOKEN_PASS_RESULT + " | " + TOKEN_FAIL_RESULT + " | " + TOKEN_NOTCONFIGURED_RESULT + " | " + TOKEN_UNKNOWN_RESULT;
    }

    /* @created 02/10/2004 11:15:13
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractInstructionExpression#containsToken(java.lang.String)
     */
    protected boolean containsToken(String line){
		return this.contains(line, TOKEN_PASS_RESULT)      ||
	       this.contains(line, TOKEN_FAIL_RESULT)          ||
		   this.contains(line, TOKEN_NOTCONFIGURED_RESULT) ||
		   this.contains(line, TOKEN_UNKNOWN_RESULT);
    }
    
    /**
     * @created 02/10/2004 11:19:48
     * @param line
     * @param token
     * @return if a given line contains a given token
     */
    private boolean contains(String line, String token){
        return line.indexOf(token) != -1;
    }

    /* @created 02/10/2004 11:55:16
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractInstructionExpression#setInstructionTokens()
     */
    protected void setInstructionTokens() throws LogTokenNotFoundException {
		StringTokenizer instruction = new StringTokenizer(this.getLine()," ");
		instruction.nextToken();
	    if (this.contains(this.getLine(),TOKEN_PASS_RESULT)){
	        this.setInstructionToken(RESULT_PASSED);
	        this.result = RESULT_PASSED;
	    } else
	    if (this.contains(this.getLine(),TOKEN_FAIL_RESULT)){
	        this.setInstructionToken(RESULT_FAILED);
	        this.result = RESULT_FAILED;
	    } else
	    if (this.contains(this.getLine(),TOKEN_NOTCONFIGURED_RESULT)){
	        this.setInstructionToken(RESULT_NOTCONFIGURED);
	        this.result = RESULT_NOTCONFIGURED;
	    } else
	    if (this.contains(this.getLine(),TOKEN_UNKNOWN_RESULT)){
	        this.setInstructionToken(RESULT_UNKNOWN);
	        this.result = RESULT_UNKNOWN;
	    } else throw new LogTokenNotFoundException("One of the result tokens was not found: "+this.getInstructionExpressionToken(),this.getInstructionExpressionToken(),this.getLine());
		
	    String value = "";
	    while (instruction.hasMoreTokens()){
	        value = instruction.nextToken();
	        if (value.indexOf("/") != -1) break;
	    }
		this.setDateTimeToken(value);	    
    }    
        
    public static void main(String[] args) {
        try {
            AbstractResultInstructionExpression rsie = new ResultStepInstructionExpression("RSLT:  Unknown Result: 14-06-2004/02:49.49 - com.motorola.testcase.messaging.smsEmsMms.receiveViewMessages.WAP.TC_WAP_RECEIVE_026");
        } catch (LogInterpreterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}
