/*
 * @created 02/10/2004 17:03:28
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

import br.ufpe.cin.stp.ptfaddon.model.log.Instruction;

/**
 * @created 02/10/2004 17:03:28
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public abstract class AbstractStepInstructionExpression implements InstructionExpressionIF{

    /**
     * <code>line</code> is the line of a step
     */
    private String line;
    
    /**
     * <code>instruction</code> is the instruction instance representing this step instruction
     */
    private Instruction instruction;    
    
    public AbstractStepInstructionExpression(String line) throws LogTokenNotFoundException{
        this.line = line;
        if (!this.containsToken(line))
            throw new LogTokenNotFoundException("The specified line does not contain the expression token(s): "+this.getInstructionExpressionToken(),this.getInstructionExpressionToken(),line);
        this.instruction = this.interpretInstruction();
    }
    
    /**
     * @created 23/09/2004 12:03:18
     * @return an instruction instance for a given interpretation.
     * @throws LogTokenNotFoundException if the tokens of the interpretation is not found
     */
    protected abstract Instruction interpretInstruction() throws LogTokenNotFoundException;

    /**
     * @created 23/09/2004 13:06:19
     * @return the instruction token used to identify an expression
     */
    public abstract String getInstructionExpressionToken();

	/**
	 * @created 02/10/2004 11:14:32
	 * @param line
	 * @return if the given line string contains the expression token
	 */
	protected boolean containsToken(String line){
	    return line.indexOf(this.getInstructionExpressionToken()) != -1;        
	}
            
    /**
     * @created 02/10/2004 17:25:28
     * @return the line of this instruction
     */
    public String getLine(){
        return this.line;
    }
    
    /**
     * @created 02/10/2004 17:45:37
     * @return the content of this step instruction
     */
    public String getContent(){
        return this.line.substring(this.line.indexOf(" - ")+3);
    }
    
    /**
     * @created 02/10/2004 17:49:21
     * @return the instruction of this step instruction
     */
    public Instruction getInstruction(){
        return this.instruction;
    }
    
    /**
     * Sets the instruction
     * @created 05/04/2004 11:32:00
     * @param instr
     */
    public void setInstruction(Instruction instr){
        this.instruction = instr;
    }
}
