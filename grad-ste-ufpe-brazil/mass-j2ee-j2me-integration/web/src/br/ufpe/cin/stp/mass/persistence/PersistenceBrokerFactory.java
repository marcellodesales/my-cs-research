/*
 * Criado em 19/11/2003
 *
 * Marcello Junior Marcello Junior
 * javaman@moomia.com
 */
package br.ufpe.cin.stp.mass.persistence;

import br.ufpe.cin.stp.mass.persistence.memory.MemoryRepositoryAdapter;

/**
 * It's the main access to the persistece layer of the application.
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.3
 * @created 31/07/2004 18:19:25
 */
public class PersistenceBrokerFactory {

	/**
	 * <code>targetDefaultPersistence</code> is the default persistence layer of the application.
	 * Just provide a class that implements PersistenceLayer and it's ok.
	 */
	private final PersistenceLayer targetDefaultPersistence;

	private static PersistenceBrokerFactory sInstance = null;

	/**
	 * The private constructor of this class is a Singleton constructor
	 * and a FactoryMethod. If you wanna change the persistence layer,
	 * just switch to another adapter that implements @see PersisteceLayer.
	 */
	private PersistenceBrokerFactory() {
		this.targetDefaultPersistence = MemoryRepositoryAdapter.getInstance();
	}

	/**
	 * @return the singleton instance of this class.
	 * @created 31/07/2004 18:18:28
	 */
	public static synchronized PersistenceBrokerFactory getInstance() {
		if (sInstance == null) {
			sInstance = new PersistenceBrokerFactory();
		}
		return sInstance;
	}

	/**
	 * @return the default persistence layer of this class.
	 * @created 31/07/2004 18:18:50
	 */
	public PersistenceLayer getPersistenceLayer() {
		return this.targetDefaultPersistence;
	}

}
