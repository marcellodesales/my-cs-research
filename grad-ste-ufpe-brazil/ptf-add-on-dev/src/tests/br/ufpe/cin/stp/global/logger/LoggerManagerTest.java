/*
 * Created on 07/06/2004 21:25:56
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package tests.br.ufpe.cin.stp.global.logger;

import junit.framework.TestCase;
import br.ufpe.cin.stp.global.logger.LoggerManager;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * 07/06/2004 
 */
public class LoggerManagerTest extends TestCase {

    private LoggerManager lm;
    
    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     * 07/06/2004 21:26:33
     */
    protected void setUp() throws Exception {
        this.lm = LoggerManager.getInstance();
    }
    
    /**
     * Tests if the ResourceManager isn't null.
     * @author Marcello (07/06/2004 21:29:07)
     */
    public void testGetInstance(){
        assertNotNull(this.lm);
    }
    
    /**
     * Tests if it's possible to create a logger.
     * @author Marcello (07/06/2004 21:51:13)
     */
    public void testGetLogger(){
        assertNotNull(this.lm.getLogger(LoggerManagerTest.class.getPackage().getName()));
    }
}
