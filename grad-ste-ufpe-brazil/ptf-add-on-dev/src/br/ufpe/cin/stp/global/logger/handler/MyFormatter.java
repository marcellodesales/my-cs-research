/*
 * Created on 07/06/2004 02:26:09
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.logger.handler;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * The simplest way to try this formatter is to put its name in the 
 * logging.properties file. So we replace
 * java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter
 * with
 * java.util.logging.ConsoleHandler.formatter = hansen.playground.logging.MyFormatter
 * 
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * 07/06/2004 
 */
public class MyFormatter extends Formatter {
    public String format(LogRecord record) {
      return
        "LogRecord info:\n" +
        "Level: " + record.getLevel() + '\n' +
        "LoggerName: " + record.getLoggerName() + '\n' +
        "Message: " + record.getMessage() + '\n' +
        " " + record.getMillis() + '\n' +
        "Sequence Number: " + record.getSequenceNumber() + '\n' +
        "SourceClassName: " + record.getSourceClassName() + '\n' +
        "SourceMethodName: " + record.getSourceMethodName() + '\n' +
        "ThreadID: " + record.getThreadID() + '\n';
    }
}
