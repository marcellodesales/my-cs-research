/*
 * @created 14/08/2004 11:25:14
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */

package br.ufpe.cin.stp.global.filemanager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @created 14/08/2004 11:25:14
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class JarFileContentsLoader {

    /**
     * <code>jarFilePath</code> is the path to the Jar file.
     */
    private String jarFilePath;
    /**
     * <code>jarFile</code> is the Jar file wrapper class.
     * @see JarFile.
     */
    private JarFile jarFile;

    /**
     * @created 14/08/2004 11:48:30
     * @param jarFilePath
     * @throws IOException
     */
    public JarFileContentsLoader(String jarFilePath) throws IOException{
        this.jarFilePath = jarFilePath;
        this.jarFile = new JarFile(jarFilePath);
    }

    /**
     * @created 14/08/2004 11:44:28
     * @return the entries of the jar file.
     */
    public List getEntriesString(){
        List list = new LinkedList();
        Enumeration enum = this.jarFile.entries();
        while (enum.hasMoreElements()) {
            list.add(((JarEntry)enum.nextElement()).getName());
        }
        return list;
    }

    /**
     * @created 14/08/2004 12:38:13
     * @return the list with java entries.
     */
    public List getJavaClassEntries(){
        List list = new LinkedList();
        Enumeration enum = this.jarFile.entries();
        while (enum.hasMoreElements()) {
          String element = ((JarEntry)enum.nextElement()).getName();
          if (this.isClassEntry(element))
              list.add("#"+this.getJavaRepresentation(element));
        }
        return list;
    }

  	/**
  	 * @created 14/08/2004 12:32:36
  	 * @param entry is on the format com/package/subpackage/myclas.class
  	 * @return the java class representation of a given entry. The
  	 * representation is com.package.subpackage.myclass
  	 */
  	private String getJavaRepresentation(String entry){
  	    entry = entry.substring(0,entry.indexOf('.'));
  	    return entry.replace('/','.');
  	}

	/**
	 * @created 14/08/2004 12:12:39
	 * @param entry is the jar entry string
	 * @return if the given entry is a class file.
	 */
	private boolean isClassEntry(String entry){
	    return entry.indexOf(".class") != -1;
	}

   public static void main(String[] args) throws IOException {
        JarFileContentsLoader loader = new JarFileContentsLoader(args[0]);
        List entries = loader.getJavaClassEntries();
        Iterator a = entries.iterator();
        while (a.hasNext()) {
            String element = (String)a.next();
            System.out.println(element);
        }
    }

}
