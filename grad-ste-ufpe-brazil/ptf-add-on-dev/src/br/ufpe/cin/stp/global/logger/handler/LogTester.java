/*
 * Created on 07/06/2004 03:42:13
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.logger.handler;


import java.util.logging.Level;
import java.util.logging.Logger;


public class LogTester {

  /**
   * Set the following string to whatever JDBC
   * driver you wish to use. I have it set to
   * use the MM driver of MySQL. You can get the
   * MM driver from: 
   * http://mmmysql.sourceforge.net/.
   */
  static public final String driver
  = "org.gjt.mm.mysql.Driver";

  /**
   * Set this to your connection string to access
   * your database. Refer to your database
   * documentation on how to set this. The one
   * I have below logs into a MySQL database.
   */
  static public final String connection
  = "jdbc:mysql://localhost/logging"
    +"?user=root&password=&database=logging";
  
  /**
   * Main function. Performs some basic
   * log testing.
   * 
   * @param argv Arguments are not used.
   */

  public static void main(String argv[])
  {
    // set up the JDBCLogger handler
    JDBCHandler jdbcHandler 
    = new JDBCHandler(driver,connection);
    jdbcHandler.clear();
    
    // setup
    Logger logger = Logger.getLogger("br.cin.stp.ptfaddon");
    logger.addHandler(jdbcHandler);
    logger.setLevel(Level.ALL);

    // try some logging
    
    if (logger.isLoggable(Level.FINER)){
        logger.info("Sample log entry");
        logger.warning("Sample warning");
    }

    try {
      int i=0/0;
    } catch ( Exception e ) {
      logger.log(Level.WARNING, 
        "This is what an exception looks like", e);
    }
  }
}


