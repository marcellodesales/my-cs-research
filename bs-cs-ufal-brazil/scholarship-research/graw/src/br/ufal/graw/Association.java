package br.ufal.graw;

/**
	Esta classe eh usada para classificar a politica de associacao de novos membros
 	a algum servico.
 */

public class Association{
	/** Qualquer um pode se associar */
	public static final int OPENED = 0;
	/** A associacao so sera permitida mediante a aprovacao */
	public static final int SEMI_OPENED = 1;
	/** Somente o dono do servico pode realizar a nova associacao  */
	public static final int CLOSED = 2;
	
}
