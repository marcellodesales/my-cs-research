package br.ufal.bibliweb;

/**
 * Exemplar.java
 *
 * @author Marcello de Sales
 */
public interface Exemplar{
	
	/** Retorna o valor da identificação do exemplar. */
	public String getID();
	/** Retorna o valor da identificação da biblioteca a qual o exemplar pertence. */
	public String getLibraryID();
	/** Retorna o valor da identificação do local físico onde o exemplar está localizado. */
	public String getPhysicalPlaceID();
	/** Retorna o valor da identificação da categoria do exemplar. */
	public String getCategoryID();
	/** Retorna o valor da identificação do idioma do exemplar. */
	public String getLanguageID();
	/** Retorna o valor da identificação do status do exemplar. */
	public String getStatusID();
	/** Retorna o valor da identificação do tipo do exemplar. */
	public String getExemplarTypeID();
	/** Retorna o valor do título do exemplar. */
	public String getTitle();
	/** Retorna as palavras chaves do exemplar. */
	public String getKeywords();
	/** Retorna a data de aquisição do exemplar. */
	public String getAcquisitionDate();
	/** @return Retorna a quantidade total de exemplares disponíveis. */
	public int getTotalQuantity();
	/** @return Retorna a quantidade de exemplares disponíveis para empréstimo. */
	public int getAvailableQuantity();
	/** @return Retorna o idioma do exemplar. */
	public Language getLanguage();
	/** @return Retorna o espaço físico do exemplar. */
	public PhysicalPlace getPhysicalPlace();
	/** @return Retorna a categoria do exemplar. */
	public Category getCategory();
	/** @return Retorna o curso acadêmico do exemplar. */
	public AcademicCourse getAcademicCourse();
	/** @return Retorna o tipo do exemplar. */
	public ExemplarType getExemplarType();
}

