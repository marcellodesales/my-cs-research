/*
 * Created on 02/07/2004 11:16:46
 * <a href=mailto:jeqca@cin.ufpe.br>José Elias Queiroga da Costa Araújo</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */


package br.ufpe.cin.stp.ptfaddon.model;

public class Test {

  private String test;
  private boolean enable;

  public Test() {
    test = "";
    enable = false;
  }

  public void setTest(String prmTest){
    test = prmTest;
  }

  public String getTest(){
    return test;
  }

  public boolean getEnable(){
    return enable;
  }

  public void setEnable(boolean prmEnable){
    enable = prmEnable;
  }

}
