/*
 * Created on 30/04/2004 22:24:50
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */

package br.ufpe.cin.stp.ptfaddon.model.log;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import br.ufpe.cin.stp.global.DateUtils;

/**
 * The TestCaseInstruction is the representation of a TestCase. The
 * representation is the same, and therefore, it contains a list of
 * instructions, which can be an ordinary StepExecInstruction, an
 * ExceptionInstruction or an Instruction.
 *
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 30/04/2004
 */
public class TestCaseInstruction extends Instruction {

	/**
	 * <code>startInstruction</code> is the stat instruction of
	 * the test case.
	 */
	private Instruction startInstruction;
	/**
	 * <code>endInstruction</code> is the end instruction of the test case.
	 */
	private Instruction endInstruction;
	/**
	 * <code>result</code> is the main result of the Test Case.
	 * It's described on the TestCaseSuilt constants.
	 */
	private String result;
	/**
	 * <code>instructions</code> is the list of instructions of
	 * the test case. It's a list of Strings.
	 */
	private List instructions = new LinkedList();
	
	private List exceptions = new LinkedList();
	/**
	 * <code>testCaseId</code> is the identification of a Test
	 * Case based on Center of Informatics (CIn)-UFPE's specs.
	 */
	private String testCaseId;

	/**
	 * Constructs a TestCaseInstruction with a given startInstruction.
	 * @param startInstruction The start instruction that has
	 * additional information about the test case.
	 */
	public TestCaseInstruction(Instruction startInstruction){
		super(startInstruction.getContent());
		this.startInstruction = startInstruction;

		String content = this.startInstruction.getContent();
		this.testCaseId = content.substring(content.lastIndexOf('.')+1).trim();
	}

	/**
	 * (08/06/2004 02:18:52)
	 * @return The iterator of the instructions of the TestCase.
	 */
	public Iterator iteratorInstructions(){
		return this.instructions.iterator();
	}

  /**
   * listInstructions
   *
   * @return List with steps of the test case
   */
    public List listInstructions(){
         return this.instructions;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * 08/06/2004 02:19:44
     */
    public String toString() {
        return this.getContent();
    }

	/**
	 * @return The representation of the content. It's similar to the
	 * toString() method.
	 * 30/04/2004 22:40:44
	 * Marcello
	 */
	public String getContent() {
		return this.getTestCaseID()+
		" - (" +this.startInstruction.getFormatedTime()+" - "+
		this.endInstruction.getFormatedTime()+") = "+this.getElapsedTime()+
		" - "+this.getResult();
	}

	/**
	 * 08/06/2004 13:36:41
	 * @return The difference between the start and end instructions
	 * time.
	 */
	public String getElapsedTime(){
		String end = (this.endInstruction.getTime() != null) ? DateUtils.getElapsedTime(this.startInstruction.getTime(),this.endInstruction.getTime()) : "";
		return end;
	}

	/**
	 * Sets the result of the test case.
	 * (08/06/2004 02:20:34)
	 * @param result is the result representation that's defined on the
	 * constants of the TestCaseSuit class.
	 * @see TestCaseSuite
	 */
	public void setResult(String result){
		this.result = result;
	}

	/**
	 * (08/06/2004 02:21:25)
	 * @return The result of the test case instruction.
	 */
	public String getResult(){
		return this.result;
	}

	/**
	 * 02/06/2004 10:52:31
	 * @return The Test Case ID from the content. This is the
	 * identification of the test.
	 */
	public String getTestCaseID(){
		return this.testCaseId;
	}

	/**
	 * 02/06/2004 11:42:37
	 * @param instruction The instruction that has the result of
	 * the instruction.
	 */
	public void setEndInstruction(Instruction instruction) {
		this.endInstruction = instruction;
	}

	/**
	 * 02/06/2004 12:31:03
	 * @return if the test case instruction has already an end
	 * instruction.
	 */
	public boolean hasEndInstruction(){
		return this.endInstruction != null;
	}

	/**
	 * 08/06/2004 16:29:59
	 * @param newInstruction That can be an ordinary StepExecInstruction indicating
	 * an instruction of the test case, an ExceptionInstruction indicating an
	 * Exception that occorred or any other Instruction representing settings made
	 * during the test.
	 */
	public void addInstruction(Instruction newInstruction){
		this.instructions.add(newInstruction);
		if (newInstruction instanceof ExceptionInstruction){
		    this.exceptions.add(newInstruction);
		}
	}

	/**
	 * 08/06/2004 14:06:22
	 * @return The last instruction on the list of the instructions. It's not
	 * necessary to be the end instruction.
	 */
	public Instruction getLastInstruction(){
		return (Instruction)this.instructions.get(this.instructions.size()-1);
	}

	/**
	 * 08/06/2004 14:04:12
	 * @return The end instruction, that finalized the test case and
	 * gave the result.
	 */
	public Instruction getEndInstruction() {
		return endInstruction;
	}

	/**
	 * 08/06/2004 14:04:12
	 * @return The start instruction of the TestCase.
	 */
	public Instruction getStartInstruction() {
		return startInstruction;
	}

	/**
	 * 08/06/2004 16:29:03
	 * @return If an exception has occorred on the TestCase.
	 */
	public ExceptionInstruction[] getExceptionInstructions(){
		ExceptionInstruction[] excps = new ExceptionInstruction[this.exceptions.size()];
		Iterator i = this.exceptions.iterator();
		int ii = -1;
		while(i.hasNext())
		    excps[++ii] = (ExceptionInstruction)i.next();
		return excps;
	}

	/**
	 * 08/06/2004 16:29:03
	 * @return If an exception has occorred on the TestCase.
	 */
	public boolean exceptionsOcorred(){
	    return (this.getExceptionInstructions().length > 0);
	}
}

