/*
 * @created 05/04/2004 04:57:24
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package tests.br.ufpe.cin.stp.model.log.interpreter;

import br.ufpe.cin.stp.ptfaddon.model.log.interpreter.AbstractInstructionExpression;
import br.ufpe.cin.stp.ptfaddon.model.log.interpreter.EndResultInstructionExpression;
import br.ufpe.cin.stp.ptfaddon.model.log.interpreter.LogTokenNotFoundException;
import junit.framework.TestCase;

/**
 * @created 05/04/2004 04:57:24
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class TestEndResultInstructionExpression extends TestCase {

    
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testConstructionSucessful(){
        
        try {
            AbstractInstructionExpression a = new EndResultInstructionExpression("RSLT:   END: 08-06-2004/08:35.49 - com.motorola.testcase.messaging.iM.createAndSendIM.TC_P2PCHAT_END_181 - eslsl  sssss");
        } catch (LogTokenNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public void testConstructionNotSuscessfull() {
        AbstractInstructionExpression a;
        try {
            a = new EndResultInstructionExpression("RSLT:   EdND: 08-06-2004/08:35.49 - com.motorola.testcase.messaging.iM.createAndSendIM.TC_P2PCHAT_END_181 - eslsl  sssss");
        } catch (LogTokenNotFoundException e) {
            // TODO Auto-generated catch block
            assertTrue(true);
        }
        
    }
}
