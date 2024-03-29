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

import java.text.ParseException;
import java.util.Calendar;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

import br.ufpe.cin.stp.global.DateUtils;

/**
 * Class ExecutionWizard.
 * 
 * @version $Revision$ $Date$
 */
public class ExecutionWizard extends ExecutionType 
implements java.io.Serializable
{
      //----------------/
     //- Constructors -/
    //----------------/

    public ExecutionWizard() {
        super();
    } //-- br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionWizard()

    public ExecutionWizard(Calendar date) throws ParseException{
        super();
        this.setDate(new org.exolab.castor.types.Date(date.getTime()));
        
        this.setTime(new org.exolab.castor.types.Time(DateUtils.getFormated(date,"HH:mm:ss")));
        this.setId(String.valueOf(date.getTime().getTime()));
    } //-- execution.Execution()       

      //-----------/
     //- Methods -/
    //-----------/

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
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionWizard) Unmarshaller.unmarshal(br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionWizard.class, reader);
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
