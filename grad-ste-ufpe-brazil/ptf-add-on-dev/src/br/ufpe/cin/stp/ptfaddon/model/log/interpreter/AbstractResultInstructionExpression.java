/*
 * @created 23/09/2004 13:45:59
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

import java.util.StringTokenizer;

/**
 * @created 23/09/2004 13:45:59
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public abstract class AbstractResultInstructionExpression extends AbstractInstructionExpression{

    /**
     * @created 23/09/2004 13:46:39
     * @param line
     */
    public AbstractResultInstructionExpression(String line) throws LogInterpreterException{
        super(line);
        // TODO Auto-generated constructor stub
    }
   
    /* @created 30/09/2004 11:42:33
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractInstructionExpression#setInstructionTokens()
     */
    protected void setInstructionTokens() throws LogTokenNotFoundException {
		StringTokenizer instruction = new StringTokenizer(this.getLine()," ");
		instruction.nextToken();
		this.setInstructionToken(instruction.nextToken());
		this.setDateTimeToken(instruction.nextToken());	
    }    
}
