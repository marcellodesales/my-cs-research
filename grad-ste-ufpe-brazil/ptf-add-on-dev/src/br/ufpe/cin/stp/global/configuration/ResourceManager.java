/*
 * Created on 06/06/2004 17:51:38
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 *
 * */

package br.ufpe.cin.stp.global.configuration;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import br.ufpe.cin.stp.global.filemanager.PropertiesLoader;

/**
 * This is the main resource manager of the application. It contains the default
 * configuration properties file, as well as configurations about logging etc.
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 06/06/2004
 */
public class ResourceManager {
    
    /**
     * <code>PTF_HOME_KEY</code> is the key that identifies the PTF root dir
     */
    public static final String PTF_HOME_KEY = "PHONETEST_HOME";  

    private final String CONFIG_FILE = "config/ptfaddon.properties";
    /**
     * <code>properties</code> of the main configuration properties of the
     * application.
     */
    private Properties properties;

    /**
     * <code>logger</code> is the default logger. It's impossible to use other,
     * once it's declared as a final logger.
     */
    //private static final Logger logger = LoggerManager.getInstance().getLogger(ResourceManager.class.getPackage().getName());

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static ResourceManager singleton = null;

    /**
     * Creates a new ResourceManager...
     * 28/06/2004 11:14:28
     * @throws IOException
     */
    private ResourceManager(){
        try {
            this.properties = PropertiesLoader.getInstance().loadPropertiesOutsideJar(this.CONFIG_FILE);
            //logger.info("Initializing the main configuration properties file...");
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       /* if (this.properties == null)
            logger.severe("Configuration properties file not loaded!!!");
        else logger.info("Configuration properties file was successfully loaded...");*/
    }

    /**
     * Get an instance of the LoggerManager. It's the main point access
     * to this class.
     * @return LoggerManager singleton of this class.
     * 07/06/2004 12:33:51
     * Marcello
     * @throws IOException
     */
    public static synchronized ResourceManager getInstance(){
        if (singleton == null){
            singleton = new ResourceManager();
        }
        return singleton;
    }

    /**
     * (07/06/2004 14:43:28)
     * @param key A given key used on the application.
     * @return the property based on the key representation that the given
     * properties given on the configuration is base.
     */
    public String getProperty(String key){
        return this.properties.getProperty(key);
    }

    /**
     * (07/06/2004 15:19:12)
     * @return The properties of the confuguration.
     */
    public Properties getProperties(){
        return this.properties;
    }
    
    /**
     * @created 15/09/2004 19:13:52
     * @return the full path to the PTF root directory.
     */
    public String getPTFRootPath(){
        return this.properties.getProperty(PTF_HOME_KEY);
    }    
        
    /**
     * Sets a value to a given key on the configuration file.
     * @created 12/09/2004 02:45:32
     * @param key
     * @param value
     */
    public void setProperty(String key, String value){
        this.properties.setProperty(key,value);
    }

    /**
     * @return The resource like image, file, etc with the default class loader.
     * @created 29/07/2004 14:08:49
     */
    public URL getResource(String resourcePath){
      return this.getDefaultClassLoader().getResource(resourcePath);
    }

    /**
     * @return The default ClassLoader for autocontained resources.
     * @created 29/07/2004 14:08:49
     */
    public ClassLoader getDefaultClassLoader(){
        return ResourceAnchor.class.getClassLoader();
    }
    
    /**
     * Saves the configuration properties file
     * @created 12/09/2004 02:41:45
     * @throws IOException if an error occor while saving the file
     */
    public void saveProperties() throws IOException{
        PropertiesLoader.getInstance().saveProperties(this.CONFIG_FILE, this.properties);
    }
    
    /**
     * Saves the properties with the specified new values
     * @created 12/09/2004 02:49:40
     * @param newValues new values with key value 
     * @throws IOException
     */
    public void saveProperties(Hashtable newValues) throws IOException{
        
        Enumeration enum = newValues.keys();
        while (enum.hasMoreElements()) {
            String key = (String) enum.nextElement();
            this.properties.setProperty(key,(String)newValues.get(key));
        }
        
        PropertiesLoader.getInstance().saveProperties(this.CONFIG_FILE,newValues);
    }
}
