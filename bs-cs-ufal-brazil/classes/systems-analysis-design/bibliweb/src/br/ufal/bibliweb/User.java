package br.ufal.bibliweb;

/**
 * User.java
 *
 * @author Marcello de Sales
 */
public interface User{
	
	/** Retorna o identificador do usu�rio. */
	public String getID();
	
	/** Retorna o CPF do usu�rio. */
	public String getCPF();
	
	/** Retorna o primeiro nome do usu�rio. */
	public String getFirstName();
	
	/** Retorna o nome completo do usu�rio. */
	public String getName();
	
	/** Retorna o endere�o residencial completo do usu�rio. */
	public String getHomeAddress();
	
	/** Retorna o endere�o de trabalho completo do usu�rio. */
	public String getWorkAddress();
	
	/** Retorna o telefone residencial do usu�rio. */
	public String getHomePhone();
	
	/** Retorna o telefone do trabalho do usu�rio. */
	public String getWorkPhone();
	
	/** Retorna o telefone celular do usu�rio. */
	public String getCellPhone();
	
	/** Retorna o email do usu�rio */
	public String getEmail();
	
	/** Retorna o nome de login do usu�rio. */
	public String getUsername();
	
	/** Retorna o password do usu�rio. */
	public String getPassword();
	
	/** Retorna o IP do �ltimo acesso do usu�rio. */
	public String getLastAccessIP();
	
	/** Retorna a data do �ltimo acesso do usu�rio. */
	public String getLastAccessDate();
	
	/** Retorna o endere�o completo da imagem do usu�rio. */
	public String getPicture();
	
	/** Retorna a identifica��o do grupo ao qual o usu�rio pertence. */
	public String getGroupID();
	
	/** Retorna a identifica��o do status o qual o usu�rio possui. */
	public String getStatusID();
	
	/** Retorna o Status ao qual o usu�rio possui. */
	public Status getStatus();
	
	/** Retorna o Group ao qual o usu�rio possui. */
	public Group getGroup();
}

