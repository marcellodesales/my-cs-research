/*
 * @created 23/09/2004 11:52:54
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

/**
 * @created 23/09/2004 11:52:54
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class EndResultInstructionExpression extends AbstractResultInstructionExpression {

    public static final String TOKEN = "END:";
    
    /**
     * @created 23/09/2004 14:24:42
     * @param line
     * @throws LogTokenNotFoundException
     */
    public EndResultInstructionExpression(String line) throws LogInterpreterException {
        super(line);
    }

    /* @created 23/09/2004 14:22:22
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractInstructionExpression#getInstructionExpressionToken()
     */
    public String getInstructionExpressionToken() {
        return TOKEN;
    }
    
    public static void main(String[] args) throws LogInterpreterException {
        AbstractInstructionExpression a = new EndResultInstructionExpression("RSLT:   END: 08-06-2004/08:35.49 - com.motorola.testcase.messaging.iM.createAndSendIM.TC_P2PCHAT_END_181 - eslsl  sssss");
    }
}
