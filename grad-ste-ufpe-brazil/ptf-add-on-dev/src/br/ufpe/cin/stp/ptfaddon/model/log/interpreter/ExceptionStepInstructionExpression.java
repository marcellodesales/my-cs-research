/*
 * @created 23/09/2004 11:48:10
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

import br.ufpe.cin.stp.ptfaddon.model.log.ExceptionInstruction;
import br.ufpe.cin.stp.ptfaddon.model.log.Instruction;

/**
 * @created 23/09/2004 11:48:10
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class ExceptionStepInstructionExpression extends AbstractStepInstructionExpression{

    public static final String TOKEN = "Exception";
    
    /**
     * @created 23/09/2004 14:26:29
     * @param line
     * @throws LogTokenNotFoundException
     */
    public ExceptionStepInstructionExpression(String line) throws LogTokenNotFoundException {
        super(line);
    }

    /* @created 02/10/2004 17:30:50
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractStepInstructionExpression#interpretInstruction()
     */
    protected Instruction interpretInstruction() throws LogTokenNotFoundException {
        this.setInstruction(new ExceptionInstruction(this.getLine()));
        return this.getInstruction();
    }

    /* @created 02/10/2004 17:30:50
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractStepInstructionExpression#getInstructionExpressionToken()
     */
    public String getInstructionExpressionToken() {
        return TOKEN;
    }
    
    public String getContent(){
        return this.getInstruction().getContent();
    }
    
    /**
     * Add an additional line to the exception 
     * @created 05/04/2004 14:01:11
     * @param logLine
     */
    public void addExceptionAdditional(String logLine) throws LogTokenNotFoundException{
        if (logLine.indexOf("\tat ") != -1 || logLine.indexOf("... ") != -1)
            ((ExceptionInstruction)this.getInstruction()).add(logLine);
        else throw new LogTokenNotFoundException("Additional Exception Line token not found!","\tat ",logLine);
        
    }
    
    public static void main(String[] args) {
        try {
            AbstractStepInstructionExpression a = new ExceptionStepInstructionExpression("phonetest.TestException: No SoftKey with any of the labels: NEW");
        } catch (LogTokenNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
