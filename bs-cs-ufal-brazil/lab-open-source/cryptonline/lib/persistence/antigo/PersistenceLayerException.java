package br.com.aulaweb.persistence.antigo;

import br.com.aulaweb.AulaWebException;

/**
 * $Id: PersistenceLayerException.java,v 1.4 2002/07/15 20:09:01 rodrigo Exp $
 *
 * @author Rodrigo Paes
 * @version $Revision: 1.4 $
 */
public class PersistenceLayerException extends AulaWebException{
	
	public PersistenceLayerException(String userMessage, String systemMessage){
		super(userMessage,systemMessage);
	}
	
	public PersistenceLayerException(String userMessage, Throwable throwable){
		super(userMessage,throwable);
	}
}

