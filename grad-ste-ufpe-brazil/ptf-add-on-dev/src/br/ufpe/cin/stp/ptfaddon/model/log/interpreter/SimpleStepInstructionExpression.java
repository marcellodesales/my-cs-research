/*
 * @created 05/04/2004 11:24:00
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

import br.ufpe.cin.stp.ptfaddon.model.log.Instruction;

/**
 * @created 05/04/2004 11:24:00
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class SimpleStepInstructionExpression implements InstructionExpressionIF{

    private Instruction instruction;
    
    public SimpleStepInstructionExpression(String line){
        this.instruction = new Instruction(line);
    }
    
    public Instruction getInstruction(){
        return this.instruction;
    }
}
