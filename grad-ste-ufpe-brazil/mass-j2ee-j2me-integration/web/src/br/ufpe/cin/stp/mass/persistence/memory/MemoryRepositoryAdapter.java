/*
 * @created 31/07/2004 18:12:46
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.persistence.memory;

import java.util.Map;

import br.ufpe.cin.stp.mass.persistence.PersistenceLayer;
import br.ufpe.cin.stp.mass.persistence.PersistenceLayerException;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;

/**
 * This is the adapter of the memory repository to access it.
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 31/07/2004 18:12:46
 */
public class MemoryRepositoryAdapter implements PersistenceLayer{
    
    private MemoryRepository mr;
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static MemoryRepositoryAdapter singleton;

    private MemoryRepositoryAdapter() {
        this.mr = MemoryRepository.getInstance();
    }

    /**
     * @created 31/07/2004 18:13:23
     * @return The single instance.
     */
    public synchronized static MemoryRepositoryAdapter getInstance() {
        if (singleton == null) {
            singleton = new MemoryRepositoryAdapter();
        }
        return singleton;
    }

    /* (non-Javadoc)
     * @see br.ufpe.cin.stp.mass.persistence.PersistenceLayer#saveObject(java.lang.Object)
     * @created 31/07/2004 18:14:46
     */
    public void saveObject(Object persistentObject) throws PersistenceLayerException {
       this.mr.insert(persistentObject);        
    }

    /* (non-Javadoc)
     * @see br.ufpe.cin.stp.mass.persistence.PersistenceLayer#deleteObject(java.lang.Object)
     * @created 31/07/2004 18:14:46
     */
    public boolean deleteObject(Object persistentObject) throws PersistenceLayerException {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see br.ufpe.cin.stp.mass.persistence.PersistenceLayer#find(java.lang.String, java.lang.Class)
     * @created 31/07/2004 18:14:46
     */
    public Object find(String oid, Class klass) throws PersistentObjectNotFoundException {
        try {
            return this.mr.select(oid,klass);
        } catch (MemoryRepositoryException e) {
            throw new PersistentObjectNotFoundException(e.getMessage());
        }
    }

    /* (non-Javadoc)
     * @see br.ufpe.cin.stp.mass.persistence.PersistenceLayer#find(java.lang.String, java.lang.Object, java.lang.Class)
     * @created 31/07/2004 18:14:46
     */
    public Object find(String attribute, Object value, Class classe) throws PersistentObjectNotFoundException {
        try {
            return this.mr.select(attribute,value,classe);
        } catch (MemoryRepositoryException e) {
            throw new PersistentObjectNotFoundException(e.getMessage());
        }
    }

    /* (non-Javadoc)
     * @see br.ufpe.cin.stp.mass.persistence.PersistenceLayer#find(java.util.Map, java.lang.Class)
     * @created 31/07/2004 18:14:46
     */
    public Object find(Map valueSet, Class classe) throws PersistentObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    /* @created 15/08/2004 11:11:33
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.mass.persistence.PersistenceLayer#find(java.lang.Class)
     */
    public Object[] find(Class classe) throws PersistentObjectNotFoundException {
        try {
            return this.mr.select(classe);
        } catch (MemoryRepositoryException e) {
            throw new PersistentObjectNotFoundException(e.getMessage());
        }
    }
}
