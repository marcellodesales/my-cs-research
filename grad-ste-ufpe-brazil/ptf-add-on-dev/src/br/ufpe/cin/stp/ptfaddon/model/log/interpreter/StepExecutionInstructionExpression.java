/*
 * @created 23/09/2004 11:44:43
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

import br.ufpe.cin.stp.ptfaddon.model.log.Instruction;

/**
 * @created 23/09/2004 11:44:43
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class StepExecutionInstructionExpression extends AbstractStepInstructionExpression {

    public static final String TOKEN = "DBG1: ";
    
    /**
     * @created 23/09/2004 14:33:06
     * @param line
     * @throws LogTokenNotFoundException
     */
    public StepExecutionInstructionExpression(String line) throws LogTokenNotFoundException {
        super(line);
    }

    /* @created 02/10/2004 16:24:26
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractInstructionExpression#interpretInstruction()
     */
    protected Instruction interpretInstruction() throws LogTokenNotFoundException{
        return new Instruction(this.getLine());
    }

    /* @created 23/09/2004 14:32:43
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractInstructionExpression#getInstructionExpressionToken()
     */
    public String getInstructionExpressionToken() {
        return TOKEN;
    }  
    
    public static void main(String[] args) {
        try {
            //AbstractInstructionExpression ba = new StepExecutionInstructionExpression("The maximum SMS message length MAX_MSG_LENGTH is not set.");
            AbstractStepInstructionExpression a = new StepExecutionInstructionExpression("DBG1: 14-06-2004/02:30.46 - delay(0.5)");
            
        } catch (LogTokenNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
