package br.ufpe.cin.stp.mass.persistence;

import br.ufpe.cin.stp.mass.model.MassException;

/**
 * PersistenceLayerException encapsulates the errors on the persistence layer
 * of the application.
 * @author Marcello Sales Jr.
 * @version $Revision: 1.1 $
 */
public class PersistenceLayerException extends MassException{

	/**
	 * Creates a Persistence Layer with the give message.
	 * @param message
	 * @created 31/07/2004 18:08:31
	 */
	public PersistenceLayerException(String message){
		super(message);
	}
}

