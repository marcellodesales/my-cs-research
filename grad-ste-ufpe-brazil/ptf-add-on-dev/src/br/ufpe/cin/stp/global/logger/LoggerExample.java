package br.ufpe.cin.stp.global.logger;
    
    import java.util.logging.Logger;

    public class LoggerExample { 
      
      // Send all message types to the logger
      public static void tryLevels(Logger logger) {
        logger.severe("severe message");
        logger.warning("warning message");
        logger.info("info message");
        logger.config("config message");
        logger.fine("fine message");
        logger.finer("finer message");
        logger.finest("finest message");
      }
      
      public static void main(String[] args) {
          
          Logger logger = LoggerManager.getInstance().getLogger(LoggerExample.class.getPackage().getName());
          tryLevels(logger);
    }
}

