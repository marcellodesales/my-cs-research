/*
 * Created on 02/07/2004 11:16:46
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */

package br.ufpe.cin.stp.global.filemanager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * FileWriter is an abstraction of a writer of lines and save them on a specified
 * filePath. It's implemented using the Singleton Design Pattern, so use the
 * getInstance() method to get an instance of it and save the file.
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * 02/07/2004
 */
public class FileWriter {

	/**
	 * <code>singleton</code> is the singleton instance of this class.
	 */
	private static FileWriter singleton;

	/**
	 * (02/07/2004 11:38:27)
	 * @return an instance of the Singleton FileWriter
	 */
	public static synchronized FileWriter getInstance(){
	    if (singleton == null){
	        singleton = new FileWriter();
	    }
	    return singleton;
	}

	/**
	 * Private constructor only used by the singleton instance.
	 * @param filePath
	 * 02/07/2004 11:30:28
	 */
	private FileWriter(){
	}

	/**
	 * Saves the file especified on file path with all lines reflecting the
	 * array of strings.
	 * (02/07/2004 11:36:28)
	 * @param filePath is the relative file path. "c:/file.txt"
	 * @param lines is the lines of the file to be created.
	 * @throws FileNotFoundException
	 */
	public void saveFile(String filePath, String[] lines) throws UnsupportedEncodingException, IOException{
            FileOutputStream fileOut = new FileOutputStream (filePath);
            OutputStreamWriter outWriter = new OutputStreamWriter (fileOut); // throws exception
            PrintWriter out = new PrintWriter (outWriter); // throws exception

            for (int i = 0; i < lines.length; i++) {
                out.println(lines[i]);
            }
            out.close();
            outWriter.close ();
	}

        public void saveFile(String filePath, List lines ) throws  UnsupportedEncodingException, IOException {
          String[] arrayLines = new String[lines.size()];
          for (int i=0; i< lines.size(); i++)
               arrayLines[i] = (String)lines.get(i);
          saveFile(filePath, arrayLines);
        }
}
