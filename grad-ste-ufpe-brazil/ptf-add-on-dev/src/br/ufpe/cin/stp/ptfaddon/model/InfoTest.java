package br.ufpe.cin.stp.ptfaddon.model;

import java.util.Date;

public class InfoTest {

  private String build;
  private String platformType;
  private String hardware;
  private String feature;
  private Date date;
  private String cycle;
  private String testplan;
  private String coreid;
  private boolean preparado;

  public InfoTest() {
  }

  /**
   * @return Returns the build.
   */
  public String getBuild() {
    return build;
  }

  /**
   * @return Returns the preparado.
   */

  public boolean getPreparado() {
    return preparado;
  }

  /**
   * @param build The build to set.
   */
  public void setBuild(String build) {
    this.build = build;
  }

  /**
   * @return Returns the coreid.
   */
  public String getCoreid() {
    return coreid;
  }

  /**
   * @param coreid The coreid to set.
   */
  public void setCoreid(String coreid) {
    this.coreid = coreid;
  }

  /**
   * @return Returns the cycle.
   */
  public String getCycle() {
    return cycle;
  }

  /**
   * @param cycle The cycle to set.
   */
  public void setCycle(String cycle) {
    this.cycle = cycle;
  }

  /**
   * @return Returns the date.
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date The date to set.
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @return Returns the feature.
   */
  public String getFeature() {
    return feature;
  }

  /**
   * @param feature The feature to set.
   */
  public void setFeature(String feature) {
    this.feature = feature;
  }

  /**
   * @return Returns the platform.
   */
  public String getPlatformType() {
    return platformType;
  }

  /**
   * @param platform The platform to set.
   */
  public void setPlatformType(String platform) {
    platformType = platform;
  }

  /**
   * @return Returns the testplan.
   */
  public String getTestplan() {
    return testplan;
  }

  /**
   * @param testplan The testplan to set.
   */
  public void setTestplan(String testplan) {
    this.testplan = testplan;
  }

  /**
   * @param preparado The preparado to set.
   */
  public void setPreparado(boolean preparado) {
    this.preparado = preparado;
  }

/**
 * @created 07/07/2004 13:07:04
 * @return Returns the hardware.
 */
public String getHardware() {
    return this.hardware;
}
/**
 * @created 07/07/2004 13:07:04
 * @param hardware The hardware to set.
 */
public void setHardware(String hardware) {
    this.hardware = hardware;
}
}
