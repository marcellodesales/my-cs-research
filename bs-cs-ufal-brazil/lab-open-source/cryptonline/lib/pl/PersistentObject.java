package pl;

import pl.criteria.OrderEntry;
import pl.map.*;

/**
 * This class encapsulates the behavior needed to make
 * single instances persistent and is the class that
 * business/domain classes inherit from to become persistent.
 *
 * @author: Artyom Rudoy
 * @author: Rodrigo Paes
 */
public abstract class PersistentObject
{
	private boolean persistent = false;
    private boolean proxy = false;
    private Object id = null;
    private Object timestamp = null;
    private boolean inOptimisticLock;
    public final static java.lang.String TIMESTAMP_FIELD_NAME = "timestamp";
    private PersistenceManager manager = null;
	
    public final static String ATTRIBUTE_ID = "id";
	
    /**
     * PersistentObject constructor.
     */
    public PersistentObject(PersistenceManager manager)
    {
        super();

        this.manager = manager;
    }

    /**
     * Call this method to permanently delete this object from a database.
     */
    public void delete() throws PlException
    {
        manager.deleteObject(this);
    }

    /**
     * If the object is persistent and Id is used as the key column this
     * method will return Id of this object in a database.
     *
     * @return Object
     */
    public Object getId()
    {
        return id;
    }

    /**
     * Getter method for the timestamp field.
     *
     * @return java.sql.Timestamp
     */
    public Object getTimestamp()
    {
        return timestamp;
    }

    /**
     * Returns true if object is marked for optimistic lock.
     *
     * @return true if object is marked for optimistic lock.
     */
    boolean isInOptimisticLock()
    {
        return inOptimisticLock;
    }

    /**
     * Returns true if this object is in a database.
     *
     * @return boolean
     */
    public boolean isPersistent()
    {
        return persistent;
    }

    public boolean isProxy()
    {
        return proxy;
    }

    /**
     * Marks this object for optimistic lock.
     *
     * @exception pl.OptimisticLockException if this object could not be optimistically locked.
     */
    public void lock() throws PlException
    {
        manager.lockObject(this);
    }

    /**
     * Marks this object for optimistic lock.
     *
     * @exception pl.OptimisticLockException if this object could not be optimistically locked.
     */
    public void lockOptimistic() throws PlException
    {
        manager.lockObjectOptimistic(this);
    }

    /**
     * Call this method to synchronize state of this object with a database state.
     */
    public void retrieve() throws PlException
    {
        manager.retrieveObject(this);
    }

    public void retrieveAsProxy() throws PlException
    {
        manager.retrieveObjectAsProxy(this);
    }

    /**
     * Call this method to retreive associations from a database.
     */
    public void retrieveAssociation(String targetName, OrderEntry[] orderAttributes) throws PlException
    {
        manager.retrieveAssociation(this, targetName, orderAttributes);
    }

    /**
     * Call this method to store this object in a database.
     */
    public void save() throws PlException
    {
        manager.saveObject(this);
    }

    /**
     * Setter method for the id property.
     *
     * @param id
     */
    public void setId(Object id)
    {
        this.id = id;
    }

    /**
     * Setter method for isOptimisticLock field. For internal use only.
     *
     * @param inOptimisticLock
     */
    void setInOptimisticLock(boolean inOptimisticLock)
    {
        this.inOptimisticLock = inOptimisticLock;
    }

    /**
     * Setter method for the persistent property.
     *
     * @param persistent
     */
    public void setPersistent(boolean persistent)
    {
        this.persistent = persistent;
    }

    public void setProxy(boolean proxy)
    {
        this.proxy = proxy;
    }

    /**
     * Setter method for the timestamp field.
     *
     * @param timestamp
     */
    void setTimestamp(Object timestamp)
    {
        this.timestamp = timestamp;
    }
	
	
	
	
	/**
	 * Metodo que procura por um objeto baseado em um dos seus atributos.<br>
	 *
	 * @param    attribute           O Nome do atributo do pelo qual o find procurará.
	 * @param    value               O valor do atributo que se procura. Esse valor deve ser único.
	 * @param    classe              A classe que contem esse atributo.
	 * @param    pm              	 Um PersistenceManager necessário para acessar as informações persistentes.
	 *
	 * @return   Se a busca foi satisfeita então retorna o objeto da classe do parametro. Caso contrário retorna null.
	 * @throws PlException 	É levantada quando existir algum problema com a camada de persistencia.
	 * @author 	 Rodrigo Paes
	 */
	public static Object find(String attributeName, Object value, Class classe, PersistenceManager pm) throws PlException{
		pl.criteria.RetrieveCriteria criteria;
		java.util.Vector parameters = new java.util.Vector();
		Cursor result;
		criteria = pm.getRetrieveCriteria(classe);
		criteria.getWhereCondition().addAndCriteria(criteria.getEqualToCriteria(attributeName));
		parameters.add(value);
		result = criteria.perform(parameters);
		
		if (result.hasMoreElements()){
			return result.nextElement();
		}else{
			return null;
		}
	
	}
	
	/**
	 * Metodo que procura por todos os objetos de uma determinada classe.<br>
	 *
	 * @param    classe              A classe dos objetos que serão retornados.
	 * @param    pm              	 Um PersistenceManager necessário para acessar as informações persistentes.
	 *
	 * @return   Um array com todos os objetos encontrados. Caso nenhum tenha sido encontrado entao o length do array eh 0.
	 * @throws PlException 	É levantada quando existir algum problema com a camada de persistencia.
	 * @author 	 Rodrigo Paes
	 */
	public static Object[] findAll(Class classe, PersistenceManager pm) throws PlException{
		pl.criteria.RetrieveCriteria criteria;
		java.util.Vector parameters = new java.util.Vector();
		java.util.Vector objects = new java.util.Vector();
		Cursor result;
		criteria = pm.getRetrieveCriteria(classe);
		result = criteria.perform(parameters);
		
		while (result.hasMoreElements()){
			objects.add(result.nextElement());
		}
		Object obj[] = new Object[objects.size()];
		objects.toArray(obj);
		return obj;
	}
	
	
	/**
	 * Metodo que procura por todos os objetos de uma determinada classe de acordo com um critério de busca.<br>
	 *
	 * @param    classe              A classe dos objetos que serão retornados.
	 * @param    pm              	 Um PersistenceManager necessário para acessar as informações persistentes.
	 * @param    attribute           O Nome do atributo do pelo qual o find procurará.
	 * @param    value               O valor do atributo que se procura. Esse valor deve ser único.
	 *
	 * @return   Um array com todos os objetos encontrados. Caso nenhum tenha sido encontrado entao o length do array eh 0.
	 * @throws PlException 	É levantada quando existir algum problema com a camada de persistencia.
	 * @author 	 Rodrigo Paes
	 */
	public static Object[] findAll(String attributeName, Object value, Class classe, PersistenceManager pm) throws PlException{
		pl.criteria.RetrieveCriteria criteria;
		java.util.Vector parameters = new java.util.Vector();
		java.util.Vector objects = new java.util.Vector();
		Cursor result;
		criteria = pm.getRetrieveCriteria(classe);
		criteria.getWhereCondition().addAndCriteria(criteria.getEqualToCriteria(attributeName));
		parameters.add(value);
		result = criteria.perform(parameters);

		while (result.hasMoreElements()){
			objects.add(result.nextElement());
		}
		Object obj[] = new Object[objects.size()];
		objects.toArray(obj);
		return obj;
	}
	
	/**
	 * @return O PersistenceManager desse objeto
	 * @author Rodrigo Paes
	 */
	public PersistenceManager getManager(){
		return this.manager;
	}
	
	/**
	 * Seta o PersistenceManager desse objeto.
	 * @author Rodrigo Paes
	 */
	public void setManager(PersistenceManager manager){
		this.manager = manager;
	}
	
	
	/**
	 * Method equals
	 *
	 * @param    persistentObject    an Object
	 *
	 * @return   true Se os dois objetos persistentes tiverem o mesmo Id.
	 *
	 */
	public boolean equals(Object persistentObject){
		try{
			if (persistentObject instanceof PersistentObject){
				if (this.id!=null && ((PersistentObject)persistentObject).getId()!=null){
					return this.getId().equals(((PersistentObject)persistentObject).getId());
				}else{
					return super.equals(persistentObject);
				}
			}else{
				return false;
			}
		}catch (Exception e){
			return false;
		}
	}
}
