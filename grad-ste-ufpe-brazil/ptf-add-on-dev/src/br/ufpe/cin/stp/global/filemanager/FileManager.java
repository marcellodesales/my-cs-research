/*
 * Created on 02/07/2004 11:45:17
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */

package br.ufpe.cin.stp.global.filemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * 02/07/2004
 */
public class FileManager {

    /**
     * <code>fLoader</code> is the file loader instance.
     */
    FileLoader fLoader = FileLoader.getInstance();

    /**
     * <code>fWriter</code> is the file writer instance.
     */
    FileWriter fWriter = FileWriter.getInstance();

    /**
     * <code>singleton</code> is the singleton of this class.
     */
    private static FileManager singleton;

    private FileManager(){
    }

    /**
     * (02/07/2004 11:51:47)
     * @return an instance of FileManager
     */
    public static synchronized FileManager getInstance(){
        if (singleton == null){
            singleton = new FileManager();
        }
        return singleton;
    }

    /**
     * Saves lines of string on a give file.
     * (02/07/2004 11:51:56)
     * @param filePath is the relative path of the file to be saved.
     * @param lines is the string lines to be saved on the file.
     */
    public synchronized void saveFile(String filePath, String[] lines) throws
      UnsupportedEncodingException, IOException {
        this.fWriter.saveFile(filePath,lines);
    }

    /**
     * Saves the file on the specified filepath
     * @created 14/08/2004 11:56:25
     * @param filePath is the path to save the file.
     * @param lines is the string contents of the file.
     * @throws UnsupportedEncodingException
     * @throws IOException for saving the file.
     */
    public synchronized void saveFile(String filePath, List lines) throws
      UnsupportedEncodingException, IOException {
        this.fWriter.saveFile(filePath,lines);
    }


    /**
     * (02/07/2004 11:52:56)
     * @param filePath is the relative path to save the file.s
     * @return a List of strings of the lines.
     * @throws FileNotFoundException
     */
    public synchronized List getFileLines(String filePath) throws FileNotFoundException{
        return this.fLoader.getFileLines(filePath);
    }

    /**
     * @created 14/08/2004 12:42:47
     * @param jarFilePath is the path to the jar file.
     * @return the wrapper of a jar file.
     * @throws IOException
     */
    public synchronized JarFileContentsLoader getJarFileContents(String jarFilePath) throws IOException{
        return new JarFileContentsLoader(jarFilePath);
    }
    
    /**
     * @created 16/09/2004 20:43:33
     * @param str
     * @return a new string where the single slash is replaced by 2
     */
    public static String filterBackslashes(String str) {        
      	StringTokenizer tokenizer = new StringTokenizer(str, "\\");        
      	StringBuffer buf = new StringBuffer();        
      	while (tokenizer.hasMoreTokens()) {            
      		buf.append(tokenizer.nextToken()+"\\\\");        
      	}        
      	String fixed = buf.toString(); 
      	return fixed.substring(0,fixed.length()-2);    
      }
    
    /**
     * @created 16/09/2004 20:43:33
     * @param str
     * @return a new string where a double slash is replaced by 1
     */
    public static String filterBackBackslashes(String str) {        
      	StringTokenizer tokenizer = new StringTokenizer(str, "\\\\");        
      	StringBuffer buf = new StringBuffer();        
      	while (tokenizer.hasMoreTokens()) {            
      		buf.append(tokenizer.nextToken()+"\\");        
      	}        
      	String fixed = buf.toString(); 
      	return fixed.substring(0,fixed.length()-1);    
      }    
    
    /**
     * @created 20/09/2004 10:39:18
     * @param filePath
     * @return if the file exists
     */
    public boolean fileExists(String filePath){
        return (new File(filePath)).exists();
    }    
    
    /**
     * @created 20/09/2004 10:40:08
     * @param path
     * @return if the path is a directory.
     */
    public boolean isPathADirectory(String path){
        return (new File(path)).isDirectory();
    }    
    
}
