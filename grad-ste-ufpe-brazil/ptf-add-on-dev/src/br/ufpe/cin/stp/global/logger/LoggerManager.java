/*
 * Created on 07/06/2004 03:02:22
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import br.ufpe.cin.stp.global.filemanager.PropertiesLoader;

/**
 * LoggerManager encapsulates the general java.util.logging.LogManager from Loggin SUN API
 * from JSDK1.4. The default configuration of the logging of the whole
 * application is managed here.
 * It a singleton pattern, and therefore, appropriate 
 * getInstance() method is provided.
 * 
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * 07/06/2004<BR>
 * @see java.util.logging.LogManager 
 * @since JDK1.4
 */
public class LoggerManager {
    
    private final String LOG_PROPERTIES = "br.ufpe.cin.stp.global.logger.logging.properties";
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static LoggerManager singleton = null;
    
    /**
     * <code>log</code> is the default LogManager of the whole application. the
     * used is the JDK1.4 version.
     */
    private LogManager log;
    
    /**
     * Get an instance of the LoggerManager. It's the main point access
     * to this class.
     * @return LoggerManager singleton of this class.
     * 07/06/2004 12:33:51
     * Marcello
     */
    public static synchronized LoggerManager getInstance(){
        if (singleton == null){
            singleton = new LoggerManager();
        }
        return singleton;
    }
    
    private InputStream getDefaultProperties(){
        return PropertiesLoader.getInstance().loadPropertiesInputStream(this.LOG_PROPERTIES);
    }       
    
    /**
     * Initializes the LoggerManager, creating reading the default 
     * configuration properties file. 
     */
    private LoggerManager(){
        this.log = LogManager.getLogManager();   
        try {
            this.log.readConfiguration(this.getDefaultProperties());
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }             
    }
    
    /**
     * @return The default LogManager of this Logger.
     * 07/06/2004 14:19:30
     * Marcello
     */
    public LogManager getLogManager(){
        return this.log;
    }
    
    /**
     * @return Enumeration containing the logger names registered in 
     * the LoggerManager.
     * 07/06/2004 13:43:43
     * Marcello
     */
    public Enumeration getLoggerNames(){
        return this.log.getLoggerNames();
    }
    
    /**
     * 07/06/2004 13:15:31
     * @param loggerName The name of the logger. 
     * @return the logger of the specified name, from global if not yet defined.
     */
    public Logger getLogger(String loggerName){
        
        Logger logger = this.log.getLogger(loggerName);
        if (logger == null){
            logger = Logger.getLogger(loggerName);
            this.log.addLogger(logger);
        }
        return this.log.getLogger(loggerName);
    }
    
    public void printLogManagerInfoConsole(){          
            
          // Get all defined loggers
          Enumeration names = this.log.getLoggerNames();
          
          System.out.println("***Begin Logger Information");
          // For each logger: show name, level, handlers etc. 
          while (names.hasMoreElements()) {
            String loggername = (String)names.nextElement();
            Logger logger = this.log.getLogger(loggername);
            System.out.println("-----------------------");
            System.out.println("Logger name: >" + logger.getName() + "<");
            System.out.println("Logger level: " + logger.getLevel());
            
            // See if a filter is defined
            if (logger.getFilter() != null) 
              System.out.println("Using a filter");
            else 
              System.out.println("No filter used");
            
            // For each handler: show formatter, level, etc. 
            Handler[] h = logger.getHandlers();
            if (h.length == 0) System.out.println("No handlers defined");
            for (int i = 0; i < h.length; i++) {
              if (i == 0) System.out.println("Handlers:");
              Formatter f = h[i].getFormatter();
              System.out.println(h[i].getClass().getName());
              System.out.println("  using formatter: " + f.getClass().getName());
              System.out.println("  using level: " + h[i].getLevel());
              if (h[i].getFilter() != null) 
                System.out.println("  using a filter");
              else 
                System.out.println("  no filter");
            }

            // See if a parent exists 
            Logger parent = logger.getParent();
            if (parent != null) 
              System.out.println("Parent: >" + parent.getName() + "<");
            else 
              System.out.println("No parent");
          }
          System.out.println("*** End Logger Information");        
    }
}
