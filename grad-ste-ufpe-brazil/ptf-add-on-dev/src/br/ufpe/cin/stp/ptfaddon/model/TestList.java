/*
 * Created on 02/07/2004 11:16:46
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
package br.ufpe.cin.stp.ptfaddon.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import br.ufpe.cin.stp.global.filemanager.FileManager;
import br.ufpe.cin.stp.global.filemanager.JarFileContentsLoader;

/**
 * @created 14/08/2004 12:53:47
 * @author Elias Queiroga
 * Updated by Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class TestList{

  private List testList = null;
  private String pathList;
  private boolean preparado;
  private boolean fromJar;

  public TestList() {
    testList = new LinkedList();
    pathList = "";
  }

  public TestList(String filePath) throws FileNotFoundException{
      this.testList = new LinkedList();
      this.pathList = filePath;
      this.preparado = true;
      this.addFromFile(FileManager.getInstance().getFileLines(this.pathList),this.pathList);
  }

	/**
	 * Creates a new TestList from a JarFile
	 * @created 14/08/2004 12:53:33
	 * @param jarFile
	 */
	public TestList(JarFileContentsLoader jarFile){
	      this.testList = jarFile.getJavaClassEntries();
	      this.pathList = "";
              this.fromJar = true;
	}

        public void addFromJar(JarFileContentsLoader jarFile){
          this.testList = jarFile.getJavaClassEntries();
          this.pathList = "";
          this.fromJar = true;
        }

        /**
         * @return boolean is the list was uploaded from the jar file. If so,
         * must require the user to create the test list file.
         */
        public boolean isFromJar(){
          return this.fromJar;
        }

        public void setFromJar(boolean fromJar){
          this.fromJar = fromJar;
        }

  public void add(Test prmTest){
    testList.add(prmTest);
  }

  public List getTestList(){
    return testList;
  }

  public void addFromFile(List prmFileName, String prmPathTestList){
        testList = prmFileName;
        pathList = prmPathTestList;
  }

  public String getPathList(){
    return pathList;
  }

  public void setPreparado(boolean prmPreparado){
    preparado = prmPreparado;
  }

  public boolean getPreparado(){
    return preparado;
  }

  public void setListPath(String listPath){
    this.pathList = listPath;
  }

  public void setEmptyTestList(){
    testList.clear();
    pathList = "";
  }

  public String[] toArray(){
    String[] list = new String[testList.size()];
    for (int i=0; i < testList.size(); i++)
      list[i] = (String)testList.get(i);
    return list;
  }

  public void setTestList(String[] list){
    testList.clear();
    for (int i=0; i<list.length; i++)
      testList.add( list[i] );
  }

	public String[] getChosenTestList(){
	    String[] tests = this.toArray();
	    Vector a = new Vector();
	    for (int i = 0; i < tests.length; i++) {
              if (tests[i].startsWith("#"))
                continue;
              else a.add(tests[i]);
            }
	    String chosen[] = new String[a.size()];
	    int pos = -1;
	    for (Iterator iter = a.iterator(); iter.hasNext();) {
	        pos++;
	        chosen[pos] = (String) iter.next();
            }
	    return chosen;
	}

	public static boolean isValidTestCasesJarFile(String filePath) throws IOException{
	       List jarClasses = FileManager.getInstance().getJarFileContents(filePath).getEntriesString();
	        boolean isValid = true;
            final String TAF_PACKAGES = "com/motorola/testcase";
            final String PTF_PACKAGES = "synergyLite";
	        Iterator i = jarClasses.iterator();
	        while (i.hasNext()) {
	            String testCaseClass = (String) i.next();
	            if (testCaseClass != null && !testCaseClass.equals("") && testCaseClass.indexOf(".class") != -1){
	                boolean foundPTF = testCaseClass.indexOf(PTF_PACKAGES) != -1;
	                boolean foundTAF = testCaseClass.indexOf(TAF_PACKAGES) != -1; 
	                if (!foundPTF && !foundTAF){
	                    isValid = false;
	                    break;
	                }
	            }
	        }
	        return isValid;
	}

        public static boolean isValidTestCasesListFile(String filePath) throws IOException{
               List jarClasses = FileManager.getInstance().getFileLines(filePath);
                boolean isValid = true;
                final String TAF_PACKAGES = "com.motorola.testcase";
                final String PTF_PACKAGES = "synergyLite.";
                Iterator i = jarClasses.iterator();
                while (i.hasNext()) {
                    String testCaseClass = (String) i.next();
                    if (testCaseClass != null && !testCaseClass.equals("") && testCaseClass.charAt(0) != '#'){
		                boolean foundPTF = testCaseClass.indexOf(PTF_PACKAGES) != -1;
		                boolean foundTAF = testCaseClass.indexOf(TAF_PACKAGES) != -1; 
		                if (!foundPTF && !foundTAF){
		                    isValid = false;
		                    break;
		                }
                    }
                }
                return isValid;
        }
}
