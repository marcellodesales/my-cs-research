/*
 * Criado em 20/11/2003
 *
 * Marcello Junior Marcello Junior
 * javaman@moomia.com
 */
package br.ufpe.cin.stp.mass.persistence.hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.ufpe.cin.stp.mass.persistence.PersistenceLayer;
import br.ufpe.cin.stp.mass.persistence.PersistenceLayerException;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

public class HibernatePersistenceAdapter implements PersistenceLayer {

	private final HibernatePersistence hibernatorPersistenceAdaptee;
	
	private static HibernatePersistenceAdapter sInstance = null;

	private HibernatePersistenceAdapter() {
		hibernatorPersistenceAdaptee = HibernatePersistence.getInstance();
	}

	/** 
	 * Get the unique instance of this class. 
	 */
	public static synchronized HibernatePersistenceAdapter getInstance() {
		if (sInstance == null) {
			sInstance = new HibernatePersistenceAdapter();
		}
		return sInstance;
	}
	
	/* (não-Javadoc)
	 * @see br.ufal.cryptonline.designpattern.PersistenceLayer#saveObject(java.lang.Object)
	 */
	public void saveObject(Object persistentObject) throws PersistenceLayerException {
		Session session = null;
		Transaction tx = null;		
		try{
			session = this.hibernatorPersistenceAdaptee.getSession();			
			tx = session.beginTransaction();
			session.saveOrUpdate(persistentObject);			
			tx.commit();
			
		} catch (HibernateException he) {
		    he.printStackTrace();
			if (tx != null) 
				try {
					tx.rollback();
				} catch (HibernateException ohe) {
					throw new PersistenceLayerException("Duplicate Values");
				}
			
		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}					
	}

	/* (não-Javadoc)
	 * @see br.ufal.cryptonline.designpattern.PersistenceLayer#deleteObject(java.lang.Object)
	 */
	public boolean deleteObject(Object persistentObject) throws PersistenceLayerException{
		try {
			Session ss = this.hibernatorPersistenceAdaptee.getSession();
			ss.delete(persistentObject);
			ss.flush();
			return true;
		} catch (HibernateException he) {
			throw new PersistenceLayerException(he.getMessage());
		}
	}

	/* (não-Javadoc)
	 * @see br.ufal.cryptonline.designpattern.PersistenceLayer#find(java.lang.String, java.lang.Class)
	 */
	public Object find(String oid, Class classe) throws PersistentObjectNotFoundException {
		try{
			Object objFound = null;
			Session session = this.hibernatorPersistenceAdaptee.getSession();
			objFound = session.load(classe,oid);
			return objFound;			
	
		}catch(HibernateException he){
			throw new PersistentObjectNotFoundException("There is no object with the identifier "+oid+", of "+classe);
		}
	}

	/* (não-Javadoc)
	 * @see br.ufal.cryptonline.designpattern.PersistenceLayer#find(java.lang.String, java.lang.Object, java.lang.Class)
	 */
	public Object find(String attribute, Object value, Class classe) throws PersistentObjectNotFoundException {
		try {
			Session session = this.hibernatorPersistenceAdaptee.getSession();
			Criteria creteria = session.createCriteria(classe).add(Expression.eq(attribute,value));			
			List instances = creteria.list();
			Object foundObject = null;
			if (instances.size() == 1){
				foundObject = instances.get(0);
			} 			
			return foundObject;
		} catch (HibernateException he){			
			throw new PersistentObjectNotFoundException(he.getMessage());		
		}	
	}

	/* (não-Javadoc)
	 * @see br.ufal.cryptonline.designpattern.PersistenceLayer#find(java.util.Map, java.lang.Class)
	 */
	public Object find(Map valueSet, Class classe) throws PersistentObjectNotFoundException {
		try{
			Session session = this.hibernatorPersistenceAdaptee.getSession();
			Criteria creteria = session.createCriteria(classe);			
			
			Iterator iter = valueSet.keySet().iterator();
			String key = "";
			String value = ""; 

			while (iter.hasNext()) {
				key   = (String)iter.next();
				value = (String)valueSet.get(key);
				creteria.add(Expression.eq(key,value)); 
			}
			
			List instances = creteria.list();
			Object instance = null;
			if (instances.size() == 1){
				instance = instances.get(0);
			} 			
			return instance;
		} catch (HibernateException he){
			throw new PersistentObjectNotFoundException(he.getMessage());
		}
	}

    /* @created 15/08/2004 11:11:17
     * (non-Javadoc)
     * @see br.ufpe.cin.stp.mass.persistence.PersistenceLayer#find(java.lang.Class)
     */
    public Object[] find(Class classe) throws PersistentObjectNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }
}
