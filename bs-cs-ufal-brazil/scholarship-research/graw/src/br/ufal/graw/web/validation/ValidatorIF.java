package br.ufal.graw.web.validation;

import java.util.Hashtable;
import java.util.List;


/**
 * Interface para estratégias de validação.
 *
 * @author Rodrigo Paes
 * @version 	%I%, %G%
 */
public interface ValidatorIF{
	
	/** Verifica se um usuário é valido mediante uma lista de parâmetros que serão
	 * passadas em uma Hashtable.
	 * @return Uma List com as mensagens de erro se houverem.
	 */
	public List validate(Hashtable parameters);
	
}

