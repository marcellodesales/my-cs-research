//$Id: Persistent.java,v 1.1.2.1 2003/10/28 10:44:51 oneovthafew Exp $
package br.ufpe.cin.stp.mass.persistence;

/**
 * Class to map persistence.
 * @author Marcello Sales Jr. masj2@cin.ufpe.br
 * @version 1.0
 * @created 24/07/2004 19:16:12
 */
public class Persistent {
    
    private PersistenceLayer pl = PersistenceBrokerFactory.getInstance().getPersistenceLayer();
    
    /**
     * <code>id</code> is the identification of the object.
     */
    private String id;
    
    /**
     * Creates an Identity class with the given idenfitication
     * being the current milliseconds time.
     */
    protected Persistent(){
        this.id = String.valueOf(this.hashCode());
        try {
            pl.saveObject(this);
        } catch (PersistenceLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * @param id The id to set.
     */
    public String getID() {
        return this.id;
    }
}
