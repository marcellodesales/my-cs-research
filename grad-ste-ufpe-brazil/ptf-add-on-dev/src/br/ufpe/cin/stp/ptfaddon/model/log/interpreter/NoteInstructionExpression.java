/*
 * @created 23/09/2004 11:58:05
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

import java.util.StringTokenizer;

import br.ufpe.cin.stp.ptfaddon.model.log.Instruction;

/**
 * @created 23/09/2004 11:58:05
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class NoteInstructionExpression extends AbstractStepInstructionExpression {

    public static final String TOKEN = "NOTE: ";
    
    private String hardwareDescription;
    /**
     * @created 23/09/2004 12:03:45
     * @param line
     * @throws LogTokenNotFoundException
     */
    public NoteInstructionExpression(String line) throws LogTokenNotFoundException {
        super(line);
        this.setHardwareName();
    }

    /* @created 30/09/2004 11:19:56
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractInstructionExpression#getInstructionExpressionToken()
     */
    public String getInstructionExpressionToken(){
        return TOKEN;
    }

    /**
     * @created 23/09/2004 13:17:25
     * @return the hardware description of a given token
     */
    public void setHardwareName(){
        StringTokenizer instruction = new StringTokenizer(this.getContent()," ");
        if (instruction.nextToken().equals("Model:")){
			while (instruction.hasMoreTokens()){
				String model = instruction.nextToken();
				if (model.startsWith("(")){
					model = model.substring(1);
					model = model.substring(0,model.length()-1);
					this.hardwareDescription = model;
					break;
				}
			}
		}
    }
    
    /**
     * @created 30/09/2004 11:20:06
     * @return The name of the hardware found on the Note: instruction. null if not found!
     */
    public String getHardwareName(){
        return this.hardwareDescription;
    }
    
    /**
     * @created 23/09/2004 13:18:22
     * @return if the hardware information was found on the note information
     */
    public boolean hasHardwareName(){
        return this.hardwareDescription != null && !this.hardwareDescription.equals("");
    }
    
    public static void main(String[] args) throws LogTokenNotFoundException {
        NoteInstructionExpression a = new NoteInstructionExpression("NOTE: 08-06-2004/08:24.47 - Model: Center of Informatics (CIn)-UFPE Inc. Center of Informatics (CIn)-UFPE Phone (V600) Device(1)");
    }

    /* @created 05/04/2004 16:40:08
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractStepInstructionExpression#interpretInstruction()
     */
    protected Instruction interpretInstruction() throws LogTokenNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }
}
