/*
 * Created on 08/06/2004 07:30:21
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.global.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * FileLoader is a singleton used to get the lines of a given file. Therefore,
 * to access the file lines, first get the instance this class and then access
 * the method geFileLines that returns a List, as an interface of a Vector instance.
 * 
 * @author Marcello Alves de Sales Junior <BR>
 * email: masj2@cin.ufpe.br <BR>
 * 08/06/2004 
 */
public class FileLoader {

	/**
	 * <code>filePath</code> is the relative path of the file.
	 */
	private String filePath;
	/**
	 * <code>file</code> is the reader of the file.
	 */
	private FileReader file;	
	/**
	 * <code>readerOfFile</code> is the output
	 */
	private BufferedReader readerOfFile;
	
	/**
	 * <code>singleton</code> the single instance of this class.
	 */
	private static FileLoader singleton;
	
	/**
	 * Creator only available to the singleton.
	 * 29/06/2004 10:51:45
	 */
	private FileLoader(){}
	
	/**
	 * @return An instance of this file loader.
	 * 08/06/2004 08:05:20
	 * Marcello
	 */
	public static synchronized FileLoader getInstance(){
		if (singleton == null){
			singleton = new FileLoader();
		}
		return singleton;
	}

	/**
	 * @param filePath The full path of the file - C:\file.txt
	 * @return a List of String with the lines.
	 * 08/06/2004 08:04:00
	 */
	public synchronized List getFileLines(String filePath) throws FileNotFoundException{
		this.filePath = filePath;
		List fileLines = new LinkedList(); 				
		try {					
			this.file = new FileReader(this.filePath);	
			this.readerOfFile  = new BufferedReader(file);		
			fileLines = this.readConfigFile(this.readerOfFile);
		} catch (IOException ioe){
			System.err.println(ioe.getMessage());
		} finally {
			try {
				this.readerOfFile.close();
				this.file.close();
			} catch (Exception e){
			    e.printStackTrace();
			}
		}
		return fileLines;
	}

	/**
	 * @return The List of lines of the given file
	 * 08/06/2004 08:03:33
	 */
	private List readConfigFile(BufferedReader reader) throws IOException{
		String line = new String();
		List fileLines = new Vector();
		
		while((line = reader.readLine()) != null )
			fileLines.add(line);
	
		return fileLines;
	}	
	
	/**
	 * 
	 * @created 12/09/2004 02:16:12
	 * @param filePath
	 * @return Gets all lines from a file inside a jar file
	 * @throws IOException
	 */
	public List getFileLinesInsidePackage(String filePath) throws IOException{
	    InputStream stream = br.ufpe.cin.stp.global.filemanager.FileLoader.class.getResourceAsStream(filePath);
	    
	    File file = new File(filePath);
	    
	    FileReader reader = new FileReader(file);	
		BufferedReader readerOfFile  = new BufferedReader(reader);		
		return this.readConfigFile(readerOfFile);	    
	}
}
