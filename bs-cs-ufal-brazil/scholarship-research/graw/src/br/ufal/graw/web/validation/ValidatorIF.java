package br.ufal.graw.web.validation;

import java.util.Hashtable;
import java.util.List;


/**
 * Interface para estrat�gias de valida��o.
 *
 * @author Rodrigo Paes
 * @version 	%I%, %G%
 */
public interface ValidatorIF{
	
	/** Verifica se um usu�rio � valido mediante uma lista de par�metros que ser�o
	 * passadas em uma Hashtable.
	 * @return Uma List com as mensagens de erro se houverem.
	 */
	public List validate(Hashtable parameters);
	
}

