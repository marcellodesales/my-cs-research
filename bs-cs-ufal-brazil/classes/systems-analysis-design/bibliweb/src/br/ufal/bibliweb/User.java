package br.ufal.bibliweb;

/**
 * User.java
 *
 * @author Marcello de Sales
 */
public interface User{
	
	/** Retorna o identificador do usuário. */
	public String getID();
	
	/** Retorna o CPF do usuário. */
	public String getCPF();
	
	/** Retorna o primeiro nome do usuário. */
	public String getFirstName();
	
	/** Retorna o nome completo do usuário. */
	public String getName();
	
	/** Retorna o endereço residencial completo do usuário. */
	public String getHomeAddress();
	
	/** Retorna o endereço de trabalho completo do usuário. */
	public String getWorkAddress();
	
	/** Retorna o telefone residencial do usuário. */
	public String getHomePhone();
	
	/** Retorna o telefone do trabalho do usuário. */
	public String getWorkPhone();
	
	/** Retorna o telefone celular do usuário. */
	public String getCellPhone();
	
	/** Retorna o email do usuário */
	public String getEmail();
	
	/** Retorna o nome de login do usuário. */
	public String getUsername();
	
	/** Retorna o password do usuário. */
	public String getPassword();
	
	/** Retorna o IP do último acesso do usuário. */
	public String getLastAccessIP();
	
	/** Retorna a data do último acesso do usuário. */
	public String getLastAccessDate();
	
	/** Retorna o endereço completo da imagem do usuário. */
	public String getPicture();
	
	/** Retorna a identificação do grupo ao qual o usuário pertence. */
	public String getGroupID();
	
	/** Retorna a identificação do status o qual o usuário possui. */
	public String getStatusID();
	
	/** Retorna o Status ao qual o usuário possui. */
	public Status getStatus();
	
	/** Retorna o Group ao qual o usuário possui. */
	public Group getGroup();
}

