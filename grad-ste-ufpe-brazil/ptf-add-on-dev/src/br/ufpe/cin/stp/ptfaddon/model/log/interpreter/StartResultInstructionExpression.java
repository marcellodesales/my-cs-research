/*
 * @created 23/09/2004 11:46:30
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

/**
 * @created 23/09/2004 11:46:30
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class StartResultInstructionExpression extends AbstractResultInstructionExpression{

    public static final String TOKEN = "START:";
    
    /**
     * <code>testCaseID</code> is the identification of an instruction
     */
    private String testCaseID;
    
    /**
     * @created 23/09/2004 14:32:34
     * @param line
     */
    public StartResultInstructionExpression(String line) throws LogInterpreterException{
        super(line);
        this.parsePrivateContents();
    }
    
    /**
     * Process private information of this instruction.
     * @created 02/10/2004 09:52:47
     * @throws LogTokenNotFoundException
     */
    public void parsePrivateContents() throws LogTokenNotFoundException{
        this.parseTestCaseID();
    }
    
    /**
     * Extracts the test case ID
     * @created 02/10/2004 09:39:59
     * @throws LogTokenNotFoundException
     */
    private void parseTestCaseID() throws LogTokenNotFoundException{
        String ID = this.getContent().substring(this.getContent().lastIndexOf(".")+1);
        
        if (!ID.startsWith("TC_"))
            throw new LogTokenNotFoundException("The line does not contains a test case identification",ID,this.getContent());
        
        this.testCaseID = ID;
    }

    /* @created 23/09/2004 14:32:28
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractInstructionExpression#getInstructionExpressionToken()
     */
    public String getInstructionExpressionToken() {
        return TOKEN;
    }
    
    /**
     * @created 02/10/2004 09:43:23
     * @return the identification of the test case.
     */
    public String getTestCaseID(){
        return this.testCaseID;
    }
    
    public static void main(String[] args) {
        try {
            AbstractInstructionExpression a = new StartResultInstructionExpression("RSLT: START: 08-06-2004/08:24.55 - com.motorola.testcase.messaging.iM.createAndSendIM.TC_P2PCHAT_START_177 - sdjkjdskjdks dskjdksjds");
            System.out.println("");
        } catch (LogInterpreterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
