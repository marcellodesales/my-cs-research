/*
 * Created on 07/06/2004 15:12:02
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 *
 * */

package tests.br.ufpe.cin.stp.global.configuration;

import junit.framework.TestCase;
import br.ufpe.cin.stp.global.configuration.ResourceManager;


/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * 07/06/2004
 */
public class ResourceManagerTest extends TestCase {

    ResourceManager rm;

    public ResourceManagerTest(String name){
        super(name);
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     * 07/06/2004 15:22:10
     */
    protected void setUp() throws Exception {
        this.rm = ResourceManager.getInstance();
    }

    /**
     * 07/06/2004 15:33:51
     * Tests the loading the of the main configuration properties file.
     * It's encapsulated in the ResourceManager class.
     */
    public void testConstruction(){

        assertNotNull(this.rm);
        assertNotNull(this.rm.getProperties());
    }

    /**
     * 07/06/2004 15:28:24
     * Tests the main ptfaddon keys on the configuration file.
     */
    public void testMainKeys(){
        assertNotNull(this.rm.getProperty("ptfaddon.ptfapiversion"));
        assertNotNull(this.rm.getProperty("ptfaddon.title"));
        assertNotNull(this.rm.getProperty("ptfaddon.version"));
        assertNotNull(this.rm.getProperty("phone.connection.type"));
    }
}
