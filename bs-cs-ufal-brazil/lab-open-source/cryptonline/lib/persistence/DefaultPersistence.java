package br.com.aulaweb.persistence;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByExample;

import java.beans.BeanDescriptor;

import br.com.aulaweb.persistence.ojb.mechanism.broker.PersistenceMechanismOJBBroker;

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
	
	public void saveObject(Object object) throws PersistenceLayerException{
        PersistenceBroker broker = null;
		try{
            broker = PersistenceMechanismOJBBroker.getInstance().getPersistenceBroker();
            // 1. open transaction
			broker.beginTransaction();
            // 2. make the new object persistent
            broker.store(object);
            broker.commitTransaction();
        }catch (PersistenceBrokerException pbe){
            // if something went wrong: rollback
            broker.abortTransaction();
			throw new PersistenceLayerException("Problemas ao tentar salvar o objeto",pbe);
		}
	}
	
	public void deleteObject(Object object) throws PersistenceLayerException{
        PersistenceBroker broker = null;
		try{
            broker = PersistenceMechanismOJBBroker.getInstance().getPersistenceBroker();
            // 1. open transaction
			broker.beginTransaction();
            // now ask broker to delete the object
            broker.delete(object);
            // commit transaction
            broker.commitTransaction();
        }catch (PersistenceBrokerException pbe){
            // if something went wrong: rollback
            broker.abortTransaction();
			throw new PersistenceLayerException("Problemas ao tentar excluir o objeto",pbe);
		}
	}
	
//	public PersistenceManager getManager(){
//		try{
//			return PersistenceMechanismAgrok.getInstance().getPersistenceManager();
//		}catch(Exception e){
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	public Object find(String attributeName, Object value, Class classe) throws PersistenceLayerException{
		
		PersistenceBroker broker = null;
		try{
			Product example = new Product();
			example.setId(id);

////			classe.getMethod(attributeName, )
//			Object example = classe.newInstance();
//			Class parametersTypes[] = {int.class};
//			classe.getMethod(attributeName,
							 
//			example.
//			classe.in
			
			// 2. build a QueryByExample from this sample instance:
			Query query = new QueryByExample(example);
			
            broker = PersistenceMechanismOJBBroker.getInstance().getPersistenceBroker();
            // 4. lookup the product specified by the QBE
			return broker.getObjectByQuery(query);
        }catch (PersistenceBrokerException pbe){
            // rollback in case of errors
			broker.abortTransaction();
			throw new PersistenceLayerException("Problemas ao tentar encontrar o objeto",pbe);
        }catch (Exception e){
			
		}
	}
	
//	public Object[] findAll(String attributeName, Object value, Class classe) throws PersistenceLayerException{
//		try{
//			return PersistentObject.findAll(attributeName, value, classe,getManager());
//		}catch(PlException e){
//			throw new PersistenceLayerException("Problemas ao tentar encontrar os objetos",e);
//		}
//	}
//
//	public Object[] findAll(Class classe) throws PersistenceLayerException{
//		try{
//			return PersistentObject.findAll(classe, getManager());
//		}catch(PlException e){
//			throw new PersistenceLayerException("Problemas ao tentar encontrar os objetos",e);
//		}
//	}
	
	
}

