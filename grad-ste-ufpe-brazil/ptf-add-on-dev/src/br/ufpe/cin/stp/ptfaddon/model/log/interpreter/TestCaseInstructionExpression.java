/*
 * @created 23/09/2004 11:45:55
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

import java.util.List;
import java.util.LinkedList;
import br.ufpe.cin.stp.ptfaddon.model.log.TestCaseInstruction;

/**
 * @created 23/09/2004 11:45:55
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class TestCaseInstructionExpression {

    private StartResultInstructionExpression srie;
    private ResultStepInstructionExpression rsie;
    
    private TestCaseInstruction tci;
    
    private List instructions = new LinkedList();
    
    /**
     * @created 23/09/2004 14:33:23
     * @param line
     * @throws LogTokenNotFoundException
     */
    public TestCaseInstructionExpression(StartResultInstructionExpression srie) {
        this.srie = srie;
        this.tci = new TestCaseInstruction(srie.getInstruction());
    }
    
    /**
     * Add a step instruction expression containing an instruction
     * @created 05/04/2004 11:46:04
     * @param instrExpr
     */
    public void addStepInstruction(InstructionExpressionIF instrExpr){
        this.tci.addInstruction(instrExpr.getInstruction());
    }
    
    /**
     * Add a result instruction expression containing a result expression
     * @created 05/04/2004 11:46:36
     * @param rsie
     */
    public void addResultInstruction(ResultStepInstructionExpression rsie){
        this.tci.setEndInstruction(rsie.getInstruction());
    }
    
    /**
     * @created 05/04/2004 11:47:08
     * @return the test case instruction.
     */
    public TestCaseInstruction getTestCaseInstruction(){
        return this.tci;
    }
}
