package br.com.aulaweb.persistence.agrok.mechanism;

import br.com.aulaweb.persistence.PersistenceLayerException;

import pl.PersistenceManagerFactory;
import pl.PersistenceManager;
import pl.XMLConfigLoader;
import pl.PlException;

public class PersistenceMechanismAgrok {
	
	/** Factory da camada de persistência (Agrok), usado para ler os arquivos de configuração
	 * além de criar um pl.PersistenceManager.
	 */
	private PersistenceManagerFactory pmf;
	private PersistenceManager pm;
	/** Arquivo de configuração do banco de dados */
	private String DATABASE_CONFIG 	= "database.xml" ;
	/** Arquivo de configuração do schema do banco de dados */
	private String SCHEMA_CONFIG 	= "schema.xml" ;
	
	/** Instância única. Ver: Singleton. [GOF] */
	private static PersistenceMechanismAgrok persistenceMechanismAgrok = null;
	
	private PersistenceMechanismAgrok() throws PersistenceLayerException{
		try{
			this.pmf = new PersistenceManagerFactory();
			
			this.pmf.loadConfig(new XMLConfigLoader(this.getClass().getResource(DATABASE_CONFIG).getFile()));
			XMLConfigLoader xml = new XMLConfigLoader(this.getClass().getResource(SCHEMA_CONFIG).getFile());
			this.pmf.loadConfig(xml);
			this.pm = this.pmf.getPersistenceManager();
		}catch(Exception e){
			throw new PersistenceLayerException("persistence.mechanism",e);
		}
	}
	
	/**
	 * @return Uma instância dessa classe. Singleton [GOF].
	 */
	public static PersistenceMechanismAgrok getInstance() throws PersistenceLayerException{
		if ( persistenceMechanismAgrok==null ){
			persistenceMechanismAgrok = new PersistenceMechanismAgrok();
		}
		return persistenceMechanismAgrok;
	}
	
	public PersistenceManager getPersistenceManager(){
		return this.pm;
	}
	
}
