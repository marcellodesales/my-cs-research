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
 * Class NeededFilesType.
 * 
 * @version $Revision$ $Date$
 */
public class NeededFilesType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _config
     */
    private java.lang.String _config;

    /**
     * Field _testList
     */
    private java.lang.String _testList;

    /**
     * Field _jarTestCases
     */
    private java.lang.String _jarTestCases;


      //----------------/
     //- Constructors -/
    //----------------/

    public NeededFilesType() {
        super();
    } //-- br.ufpe.cin.stp.ptfaddon.model.wizardfile.NeededFilesType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'config'.
     * 
     * @return the value of field 'config'.
     */
    public java.lang.String getConfig()
    {
        return this._config;
    } //-- java.lang.String getConfig() 

    /**
     * Returns the value of field 'jarTestCases'.
     * 
     * @return the value of field 'jarTestCases'.
     */
    public java.lang.String getJarTestCases()
    {
        return this._jarTestCases;
    } //-- java.lang.String getJarTestCases() 

    /**
     * Returns the value of field 'testList'.
     * 
     * @return the value of field 'testList'.
     */
    public java.lang.String getTestList()
    {
        return this._testList;
    } //-- java.lang.String getTestList() 

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
     * Sets the value of field 'config'.
     * 
     * @param config the value of field 'config'.
     */
    public void setConfig(java.lang.String config)
    {
        this._config = config;
    } //-- void setConfig(java.lang.String) 

    /**
     * Sets the value of field 'jarTestCases'.
     * 
     * @param jarTestCases the value of field 'jarTestCases'.
     */
    public void setJarTestCases(java.lang.String jarTestCases)
    {
        this._jarTestCases = jarTestCases;
    } //-- void setJarTestCases(java.lang.String) 

    /**
     * Sets the value of field 'testList'.
     * 
     * @param testList the value of field 'testList'.
     */
    public void setTestList(java.lang.String testList)
    {
        this._testList = testList;
    } //-- void setTestList(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (br.ufpe.cin.stp.ptfaddon.model.wizardfile.NeededFilesType) Unmarshaller.unmarshal(br.ufpe.cin.stp.ptfaddon.model.wizardfile.NeededFilesType.class, reader);
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
