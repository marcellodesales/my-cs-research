/*
 * Created on 02/07/2004 11:16:46
 * <a href=mailto:jeqca@cin.ufpe.br>José Elias Queiroga da Costa Araújo</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */

package br.ufpe.cin.stp.ptfaddon.model;

import java.io.FileNotFoundException;
import java.util.List;

import br.ufpe.cin.stp.global.filemanager.FileManager;

public class Configuration {

  private List configList;
  private String fileName;
  private boolean preparado;

  public Configuration() {
  }
  
  public Configuration(String filePath) throws FileNotFoundException{
      this.fileName = filePath;
      this.preparado = true;
      this.addFromFile(FileManager.getInstance().getFileLines(this.fileName),this.fileName);      
  }

  public void addFromFile(List configuration, String prmFileName){
    configList = configuration;
    fileName = prmFileName;

  }

  public void SaveToFile(String prmFileName){

  }

  public String getPathConfig(){
    return fileName;
  }
  
  public void setPath(String path){
      this.fileName = path;
  }

  public String[] getConfigFileLines(){
    String[] list = new String[configList.size()];
    for (int i=0; i<configList.size(); i++)
      list[i] = (String)configList.get(i);
    return list;
  }

  public void setPreparado(boolean prmPreparado){
    preparado = prmPreparado;
  }

  public boolean getPreparado(){
    return preparado;
  }

  public void setConfigList(String[] list){
  configList.clear();
  for (int i=0; i<list.length; i++)
    configList.add( list[i] );
}





}
