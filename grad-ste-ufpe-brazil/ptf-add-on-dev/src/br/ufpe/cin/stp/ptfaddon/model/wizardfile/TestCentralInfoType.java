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
 * Class TestCentralInfoType.
 * 
 * @version $Revision$ $Date$
 */
public class TestCentralInfoType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _coreId
     */
    private java.lang.String _coreId;

    /**
     * Field _cycle
     */
    private java.lang.String _cycle;

    /**
     * Field _plan
     */
    private java.lang.String _plan;


      //----------------/
     //- Constructors -/
    //----------------/

    public TestCentralInfoType() {
        super();
    } //-- br.ufpe.cin.stp.ptfaddon.model.wizardfile.TestCentralInfoType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'coreId'.
     * 
     * @return the value of field 'coreId'.
     */
    public java.lang.String getCoreId()
    {
        return this._coreId;
    } //-- java.lang.String getCoreId() 

    /**
     * Returns the value of field 'cycle'.
     * 
     * @return the value of field 'cycle'.
     */
    public java.lang.String getCycle()
    {
        return this._cycle;
    } //-- java.lang.String getCycle() 

    /**
     * Returns the value of field 'plan'.
     * 
     * @return the value of field 'plan'.
     */
    public java.lang.String getPlan()
    {
        return this._plan;
    } //-- java.lang.String getPlan() 

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
     * Sets the value of field 'coreId'.
     * 
     * @param coreId the value of field 'coreId'.
     */
    public void setCoreId(java.lang.String coreId)
    {
        this._coreId = coreId;
    } //-- void setCoreId(java.lang.String) 

    /**
     * Sets the value of field 'cycle'.
     * 
     * @param cycle the value of field 'cycle'.
     */
    public void setCycle(java.lang.String cycle)
    {
        this._cycle = cycle;
    } //-- void setCycle(java.lang.String) 

    /**
     * Sets the value of field 'plan'.
     * 
     * @param plan the value of field 'plan'.
     */
    public void setPlan(java.lang.String plan)
    {
        this._plan = plan;
    } //-- void setPlan(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (br.ufpe.cin.stp.ptfaddon.model.wizardfile.TestCentralInfoType) Unmarshaller.unmarshal(br.ufpe.cin.stp.ptfaddon.model.wizardfile.TestCentralInfoType.class, reader);
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
