package br.ufpe.cin.stp.global.swingcomponent;

import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.ufpe.cin.stp.global.configuration.ResourceManager;

public final class DialogFactory {

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static DialogFactory singleton;

    private DialogFactory() {
    }

    /**
     * @created 05/09/2004 23:41:46
     * @return The single instance.
     */
    public synchronized static DialogFactory getInstance() {
        if (singleton == null) {
            singleton = new DialogFactory();
        }
        return singleton;
    }
    
    public JFileChooser getFileSingleSelectionChooser() {
        return this.getFileSingleSelectionChooser(".");
    }  
    
    /**
     * Lazy instanciation in order to construct as many file chooser objects
     * as possible. It's a factory of JFileChooser.
     * */
    public JFileChooser getFileSingleSelectionChooser(String relative) {
      JFileChooser fileChooser = new JFileChooser("");
      try {
          // Create a File object containing the canonical path of the
          // desired directory

          relative = (relative == null || relative.charAt(0) != '.') ? "." : relative;
          File f = new File(new File(relative).getCanonicalPath());

          // Set the current directory
          fileChooser.setCurrentDirectory(f);
      } catch (IOException e) {
      }

      fileChooser.setAcceptAllFileFilterUsed(false);
      fileChooser.setMultiSelectionEnabled(false);
      return fileChooser;
    }
    
    public void showErrorMessage(String title, String message){
        JOptionPane.showMessageDialog(null, message,title,JOptionPane.ERROR_MESSAGE,new ImageIcon(ResourceManager.getInstance().getResource("images/error.png")));
    }

    public int showConfirmDialog(String message){
      return JOptionPane.showConfirmDialog(null,message,"Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,new ImageIcon(ResourceManager.getInstance().getResource("images/question.png")));
    }
    
    public int showConfirmDialog(String title, String message, String[] buttonsLabel, String defaultButton){
        return JOptionPane.showOptionDialog(null,message,title,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon(ResourceManager.getInstance().getResource("images/question.png")),buttonsLabel,defaultButton); //default button title
    }
    
    public String showInputDialog(String title,String request, Icon icon){
        return (String)JOptionPane.showInputDialog(
                null,
                request,
                title,
                JOptionPane.PLAIN_MESSAGE,
                icon,
                null,
                "");
    }

    public void showWarningMessage(String title, String message){
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.WARNING_MESSAGE,new ImageIcon(ResourceManager.getInstance().getResource("images/warning.png")));
    }

    public void showInfoMessage(String title, String message){
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(ResourceManager.getInstance().getResource("images/info.png")));
    }
    
    public class PersonalFilter extends javax.swing.filechooser.FileFilter {
        
        private String fileExtention;
        private String description;
        
        public PersonalFilter(String fileExtention, String description){
            this.fileExtention = fileExtention;
            this.description = description;
        }
        
      public boolean accept(java.io.File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(this.fileExtention);
      }

      public String getDescription() {
        return this.description+" ("+this.fileExtention+")";
      }
    }    
    
    public class TestListFilter extends PersonalFilter {
        
        public TestListFilter(){
            super(".txt","Test Cases List File");
        }
     }

     public class ConfigurationFileFilter extends PersonalFilter {

         public ConfigurationFileFilter(){
             super(".cfg","Configuration Properties File");
         }
         
         public boolean accept(java.io.File f) {
          return f.isDirectory() || f.getName().toLowerCase().endsWith(".properties") || f.getName().toLowerCase().endsWith(".cfg");
        }

        public String getDescription() {
           return "Configuration Properties File (*.properties | *.cfg)";
        }
     }

     public class JarFileFilter extends PersonalFilter{
         
         public JarFileFilter(){
             super(".jar","Test Case Suite inside Jar File");
         }
     }

     public class PTFExecutionFilter extends PersonalFilter {

         public PTFExecutionFilter(){
             super(".ptfaddon","PTF Add-on Execution Wizard Configuration File");
         }
     }
     
     public class XLSReportFilter extends PersonalFilter {

        public XLSReportFilter() {
            super(".xls","Microsoft Excel Report");
        }
    }     
}
