package br.com.aulaweb.persistence.agrok.mechanism;

import br.com.aulaweb.persistence.PersistenceLayerException;

import pl.PersistenceManagerFactory;
import pl.PersistenceManager;
import pl.XMLConfigLoader;
import pl.PlException;

public class PersistenceMechanismAgrok {
	
	/** Factory da camada de persist�ncia (Agrok), usado para ler os arquivos de configura��o
	 * al�m de criar um pl.PersistenceManager.
	 */
	private PersistenceManagerFactory pmf;
	private PersistenceManager pm;
	/** Arquivo de configura��o do banco de dados */
	private String DATABASE_CONFIG 	= "database.xml" ;
	/** Arquivo de configura��o do schema do banco de dados */
	private String SCHEMA_CONFIG 	= "schema.xml" ;
	
	/** Inst�ncia �nica. Ver: Singleton. [GOF] */
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
	 * @return Uma inst�ncia dessa classe. Singleton [GOF].
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
