/*
 * Created on 02/06/2004 10:17:49
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log;


/**
 * Describes an ordinary StepExecutionInstruction. It can be a configuration
 * step or other setting stuff.
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 02/06/2004 
 */
public class StepExecInstruction extends Instruction{

	/**
	 * Creates a StepExecution instruction with the contents.
	 * @param title is the whole line of the step instruction.
	 * 08/06/2004 02:15:31
	 */
	public StepExecInstruction(String title){
		super(title);
	}
}
