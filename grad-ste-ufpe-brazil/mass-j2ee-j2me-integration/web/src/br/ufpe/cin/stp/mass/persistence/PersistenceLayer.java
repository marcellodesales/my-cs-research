/*
 * Created on 19/11/2003
 * Updated on 01/08/2004
 *
 * Marcello Junior
 * javaman@moomia.com
 */
package br.ufpe.cin.stp.mass.persistence;

import java.util.Map;

/**
 * The interface defined to represent any Persistence Mechanism.
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 01/08/2004 11:57:24
 */
public interface PersistenceLayer {

	/**
	 * Saves the current state of the the @param persistentObject
	 * in the persistent layer.    
	 * @param persistentObject: a persistent object mapped to a persistence layer
	 * @throws PersistenceLayerException
	 */
	public void saveObject(Object persistentObject) throws PersistenceLayerException;
		
	/**
	 * Removes the reference of the persistenObject from the persistent 
	 * layer.
	 * @param persistentObject
	 * @throws PersistenceLayerException 
	 */
	public boolean deleteObject(Object persistentObject) throws PersistenceLayerException;;
	
	
	/** 
	 * Finds a persistent object instance with the object reference identification.
	 * @param oid: It's the reference of the persistent object.
	 * @param classe: the class the objects is from.
	 * @return An object instance with the correct type class. It must be cast.
	 * @throws PersistentObjectNotFoundException: In case the object is not found.
	 */
	public Object find(String oid, Class classe) throws PersistentObjectNotFoundException;
	
	/**
	 * Finds a persistent object instance with a given attribute=value.
	 * @param attribute: It's a property the required class has. 
	 * @param value: the value the property must have.
	 * @param classe: the class the object is from. 
	 * @return An object instance with the correct class. It must be cast 
	 * @throws PersistentObjectNotFoundException: In case the object is not found. 
	 */
	public Object find(String attribute, Object value, Class classe) throws PersistentObjectNotFoundException;
	
	/**
	 * Finds a persistent object instance with a given attribute=value
	 * set, mapped in a Hashtable.
	 * @param valueSet: a map implementation. It can be a Hashtable.
	 * @param classe: the class the object is from. 
	 * @return An object instance with the correct class. It must be cast 
	 * @throws PersistentObjectNotFoundException: In case the object is not found. 
	 */
	public Object find(Map valueSet, Class classe) throws PersistentObjectNotFoundException;
	
	/**
	 * @created 15/08/2004 11:10:01
	 * @param classe is the class of the instances
	 * @return the collection of all instances of a given class.
	 * @throws PersistentObjectNotFoundException if no instance was found.
	 */
	public Object[] find(Class classe) throws PersistentObjectNotFoundException;
}
