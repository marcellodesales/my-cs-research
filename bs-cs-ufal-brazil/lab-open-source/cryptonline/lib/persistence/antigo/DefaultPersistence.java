package br.com.aulaweb.persistence.antigo;

import pl.PersistentObject;
import pl.PersistenceManager;
import pl.PlException;

import br.com.aulaweb.persistence.agrok.mechanism.PersistenceMechanismAgrok;

/**
 * $Id: DefaultPersistence.java,v 1.8 2002/07/23 22:02:40 rodrigo Exp $ <br>
 * Essa classe implementa a interface da camada de camada de persistência.
 *
 * @author Rodrigo Paes
 * @version $Revision: 1.8 $ $Date: 2002/07/23 22:02:40 $
 */

public class DefaultPersistence{
	
	private static DefaultPersistence singletonInstance;
	
	public DefaultPersistence(){}
	
	public static synchronized DefaultPersistence getInstance(){
		if (singletonInstance==null){
			singletonInstance = new DefaultPersistence();
		}
		return singletonInstance;
	}
	
	public void saveObject(PersistentObject object) throws PersistenceLayerException{
		try{
			PersistenceManager manager = PersistenceMechanismAgrok.getInstance().getPersistenceManager();
			if (object.getManager()==null){
				object.setManager(manager);
			}
			manager.saveObject(object);
		}catch(PlException e){
			throw new PersistenceLayerException("Problemas ao tentar salvar o objeto",e);
		}
	}
	
	public void deleteObject(PersistentObject object) throws PersistenceLayerException{
		try{
			PersistenceManager manager = PersistenceMechanismAgrok.getInstance().getPersistenceManager();
			if (object.getManager()==null){
				object.setManager(manager);
			}
			manager.deleteObject(object);
		}catch(PlException e){
			throw new PersistenceLayerException("Problemas ao tentar salvar o objeto",e);
		}
	}
	
	
	public PersistenceManager getManager(){
		try{
			return PersistenceMechanismAgrok.getInstance().getPersistenceManager();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Object find(String attributeName, Object value, Class classe) throws PersistenceLayerException{
		try{
			return PersistentObject.find(attributeName, value, classe,getManager());
		}catch(PlException e){
			throw new PersistenceLayerException("Problemas ao tentar encontrar o objeto",e);
		}
	}
	
	public Object[] findAll(String attributeName, Object value, Class classe) throws PersistenceLayerException{
		try{
			return PersistentObject.findAll(attributeName, value, classe,getManager());
		}catch(PlException e){
			throw new PersistenceLayerException("Problemas ao tentar encontrar os objetos",e);
		}
	}
	
	public Object[] findAll(Class classe) throws PersistenceLayerException{
		try{
			return PersistentObject.findAll(classe, getManager());
		}catch(PlException e){
			throw new PersistenceLayerException("Problemas ao tentar encontrar os objetos",e);
		}
	}
	
	
}

