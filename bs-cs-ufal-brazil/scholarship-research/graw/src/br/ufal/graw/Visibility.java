package br.ufal.graw;

/**
 *	Esta classe eh usada para classificar a visibilidade de algum servico.
 * @author Rodrigo Paes
 */
public class Visibility{
	/** Somente membros de um mesmo curso academico. Ex.: Ciencias da Computacao */
	public static final int PRIVATE = 0;
	/** Membros de um mesmo departamento */
	public static final int INTRA_DEPARTMENT = 1;
	/** Membros de uma mesma instituicao */
	public static final int INTRA_INSTITUTION = 2;
	/** Membros de instituicao diferentes */
	public static final int INTER_INSTITUTION = 3;
	/** Qualquer um pode visualizar*/
	public static final int PUBLIC = 4;
}
