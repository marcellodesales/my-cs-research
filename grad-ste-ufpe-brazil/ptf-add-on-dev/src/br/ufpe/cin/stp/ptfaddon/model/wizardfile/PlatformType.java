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
 * Class PlatformType.
 * 
 * @version $Revision$ $Date$
 */
public class PlatformType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _type
     */
    private java.lang.String _type;

    /**
     * Field _hardware
     */
    private java.lang.String _hardware;

    /**
     * Field _build
     */
    private java.lang.String _build;


      //----------------/
     //- Constructors -/
    //----------------/

    public PlatformType() {
        super();
    } //-- br.ufpe.cin.stp.ptfaddon.model.wizardfile.PlatformType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'build'.
     * 
     * @return the value of field 'build'.
     */
    public java.lang.String getBuild()
    {
        return this._build;
    } //-- java.lang.String getBuild() 

    /**
     * Returns the value of field 'hardware'.
     * 
     * @return the value of field 'hardware'.
     */
    public java.lang.String getHardware()
    {
        return this._hardware;
    } //-- java.lang.String getHardware() 

    /**
     * Returns the value of field 'type'.
     * 
     * @return the value of field 'type'.
     */
    public java.lang.String getType()
    {
        return this._type;
    } //-- java.lang.String getType() 

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
     * Sets the value of field 'build'.
     * 
     * @param build the value of field 'build'.
     */
    public void setBuild(java.lang.String build)
    {
        this._build = build;
    } //-- void setBuild(java.lang.String) 

    /**
     * Sets the value of field 'hardware'.
     * 
     * @param hardware the value of field 'hardware'.
     */
    public void setHardware(java.lang.String hardware)
    {
        this._hardware = hardware;
    } //-- void setHardware(java.lang.String) 

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(java.lang.String type)
    {
        this._type = type;
    } //-- void setType(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (br.ufpe.cin.stp.ptfaddon.model.wizardfile.PlatformType) Unmarshaller.unmarshal(br.ufpe.cin.stp.ptfaddon.model.wizardfile.PlatformType.class, reader);
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
