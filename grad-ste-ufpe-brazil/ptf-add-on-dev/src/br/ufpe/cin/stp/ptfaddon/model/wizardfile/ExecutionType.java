/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.5.3</a>, using an XML
 * Schema.
 * $Id$
 */

package br.ufpe.cin.stp.ptfaddon.model.wizardfile;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class ExecutionType.
 * 
 * @version $Revision$ $Date$
 */
public class ExecutionType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _date
     */
    private org.exolab.castor.types.Date _date;

    /**
     * Field _time
     */
    private org.exolab.castor.types.Time _time;

    /**
     * Field _platform
     */
    private br.ufpe.cin.stp.ptfaddon.model.wizardfile.Platform _platform;

    /**
     * Field _testCentralInfo
     */
    private br.ufpe.cin.stp.ptfaddon.model.wizardfile.TestCentralInfo _testCentralInfo;

    /**
     * Field _neededFiles
     */
    private br.ufpe.cin.stp.ptfaddon.model.wizardfile.NeededFiles _neededFiles;


      //----------------/
     //- Constructors -/
    //----------------/

    public ExecutionType() {
        super();
    } //-- br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'date'.
     * 
     * @return the value of field 'date'.
     */
    public org.exolab.castor.types.Date getDate()
    {
        return this._date;
    } //-- org.exolab.castor.types.Date getDate() 

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'id'.
     */
    public java.lang.String getId()
    {
        return this._id;
    } //-- java.lang.String getId() 

    /**
     * Returns the value of field 'neededFiles'.
     * 
     * @return the value of field 'neededFiles'.
     */
    public br.ufpe.cin.stp.ptfaddon.model.wizardfile.NeededFiles getNeededFiles()
    {
        return this._neededFiles;
    } //-- br.ufpe.cin.stp.ptfaddon.model.wizardfile.NeededFiles getNeededFiles() 

    /**
     * Returns the value of field 'platform'.
     * 
     * @return the value of field 'platform'.
     */
    public br.ufpe.cin.stp.ptfaddon.model.wizardfile.Platform getPlatform()
    {
        return this._platform;
    } //-- br.ufpe.cin.stp.ptfaddon.model.wizardfile.Platform getPlatform() 

    /**
     * Returns the value of field 'testCentralInfo'.
     * 
     * @return the value of field 'testCentralInfo'.
     */
    public br.ufpe.cin.stp.ptfaddon.model.wizardfile.TestCentralInfo getTestCentralInfo()
    {
        return this._testCentralInfo;
    } //-- br.ufpe.cin.stp.ptfaddon.model.wizardfile.TestCentralInfo getTestCentralInfo() 

    /**
     * Returns the value of field 'time'.
     * 
     * @return the value of field 'time'.
     */
    public org.exolab.castor.types.Time getTime()
    {
        return this._time;
    } //-- org.exolab.castor.types.Time getTime() 

    /**
     * Method isValid
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'date'.
     * 
     * @param date the value of field 'date'.
     */
    public void setDate(org.exolab.castor.types.Date date)
    {
        this._date = date;
    } //-- void setDate(org.exolab.castor.types.Date) 

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(java.lang.String id)
    {
        this._id = id;
    } //-- void setId(java.lang.String) 

    /**
     * Sets the value of field 'neededFiles'.
     * 
     * @param neededFiles the value of field 'neededFiles'.
     */
    public void setNeededFiles(br.ufpe.cin.stp.ptfaddon.model.wizardfile.NeededFiles neededFiles)
    {
        this._neededFiles = neededFiles;
    } //-- void setNeededFiles(br.ufpe.cin.stp.ptfaddon.model.wizardfile.NeededFiles) 

    /**
     * Sets the value of field 'platform'.
     * 
     * @param platform the value of field 'platform'.
     */
    public void setPlatform(br.ufpe.cin.stp.ptfaddon.model.wizardfile.Platform platform)
    {
        this._platform = platform;
    } //-- void setPlatform(br.ufpe.cin.stp.ptfaddon.model.wizardfile.Platform) 

    /**
     * Sets the value of field 'testCentralInfo'.
     * 
     * @param testCentralInfo the value of field 'testCentralInfo'.
     */
    public void setTestCentralInfo(br.ufpe.cin.stp.ptfaddon.model.wizardfile.TestCentralInfo testCentralInfo)
    {
        this._testCentralInfo = testCentralInfo;
    } //-- void setTestCentralInfo(br.ufpe.cin.stp.ptfaddon.model.wizardfile.TestCentralInfo) 

    /**
     * Sets the value of field 'time'.
     * 
     * @param time the value of field 'time'.
     */
    public void setTime(org.exolab.castor.types.Time time)
    {
        this._time = time;
    } //-- void setTime(org.exolab.castor.types.Time) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionType) Unmarshaller.unmarshal(br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionType.class, reader);
    } //-- java.lang.Object unmarshal(java.io.Reader) 

    /**
     * Method validate
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
