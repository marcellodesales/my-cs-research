package br.ufal.bibliweb;

/**
 * Exemplar.java
 *
 * @author Marcello de Sales
 */
public interface Exemplar{
	
	/** Retorna o valor da identifica��o do exemplar. */
	public String getID();
	/** Retorna o valor da identifica��o da biblioteca a qual o exemplar pertence. */
	public String getLibraryID();
	/** Retorna o valor da identifica��o do local f�sico onde o exemplar est� localizado. */
	public String getPhysicalPlaceID();
	/** Retorna o valor da identifica��o da categoria do exemplar. */
	public String getCategoryID();
	/** Retorna o valor da identifica��o do idioma do exemplar. */
	public String getLanguageID();
	/** Retorna o valor da identifica��o do status do exemplar. */
	public String getStatusID();
	/** Retorna o valor da identifica��o do tipo do exemplar. */
	public String getExemplarTypeID();
	/** Retorna o valor do t�tulo do exemplar. */
	public String getTitle();
	/** Retorna as palavras chaves do exemplar. */
	public String getKeywords();
	/** Retorna a data de aquisi��o do exemplar. */
	public String getAcquisitionDate();
	/** @return Retorna a quantidade total de exemplares dispon�veis. */
	public int getTotalQuantity();
	/** @return Retorna a quantidade de exemplares dispon�veis para empr�stimo. */
	public int getAvailableQuantity();
	/** @return Retorna o idioma do exemplar. */
	public Language getLanguage();
	/** @return Retorna o espa�o f�sico do exemplar. */
	public PhysicalPlace getPhysicalPlace();
	/** @return Retorna a categoria do exemplar. */
	public Category getCategory();
	/** @return Retorna o curso acad�mico do exemplar. */
	public AcademicCourse getAcademicCourse();
	/** @return Retorna o tipo do exemplar. */
	public ExemplarType getExemplarType();
}

