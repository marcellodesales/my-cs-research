/*
 * Criado em 20/11/2003
 *
 * Marcello Junior 
 * marcello.desales@gmail.com
 */
package br.ufpe.cin.stp.mass.persistence.hibernate;

import br.ufpe.cin.stp.mass.model.session.AbstractSession;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.MappingException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;
import net.sf.hibernate.cfg.Environment;

/**
 * PatternBox:Adapter+Singleton: "Adaptee" implementation.
 * <ul>
 *   <li>defines an existing interface that needs adapting.</li>
 * </ul>
 * 
 * @author Marcello de Sales
 */
public class HibernatePersistence {

	/** unique instance */
	private static HibernatePersistence sInstance = null;
	
	/** Session Factory to create sessions. */
	private SessionFactory sessions = null;	

	/** 
	 * Private constuctor. Only called by the getInstanceMelhod.
	 */
	private HibernatePersistence() {
	}

	/** 
	 * Get the unique instance of this class. 
	 */
	public static synchronized HibernatePersistence getInstance() {
		if (sInstance == null) {
			sInstance = new HibernatePersistence();
		}
		return sInstance;
	}
	
	/**
	 * Creates a SessionFactory of Hinernate. It's the primary method to 
	 * start hibernate with the descrition files.
	 * @return a SessionFactory instance of hibernate.
	 * @throws HibernateException
	 * @throws MappingException
	 */
	public SessionFactory getSessionFactory() throws HibernateException, MappingException{
		//TODO Create a Property file with all mappings.
		//and use the addFile method to put them there.
		//configuration catches the mappings defs.
		//hibernate.hbm.xml
	    
		Configuration cfg = new Configuration()
		.addClass(AbstractSession.class)
		.setProperty(Environment.HBM2DDL_AUTO, "create");
		cfg.setProperty("hibernate.show_sql", "false");	
			
		this.sessions = cfg.buildSessionFactory();
		return this.sessions;
	}
	
	/**
	 * A main session object instance is retrieve from this method 
	 * in order to persist, retrieve, delete and update objects in 
	 * a persistence layer. 
	 * @see net.sf.hibernate.Session 
	 * @return a Session reference.
	 * @throws HibernateException
	 */
	public Session getSession() throws HibernateException {
		if (this.sessions == null)
			this.getSessionFactory();
		return this.sessions.openSession();		
	}	
}
