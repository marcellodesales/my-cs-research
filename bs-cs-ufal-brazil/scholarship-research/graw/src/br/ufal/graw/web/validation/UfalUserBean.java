package br.ufal.graw.web.validation;


/**
 * Uma representação do um usuário segundo a visão da UFAL.
 *
 * @author Rodrigo Paes
 * @version 	%I%, %G%
 */
public class UfalUserBean{
	
	public static final String PROFESSOR 	= "PROFESSOR";
	public static final String STUDENT 		= "ALUNO";
	
	/** Indica se esse usuário ainda está na ufal , se morreu, etc ... */
	private String status;
	/** Diz se é um professor, um aluno, um faxineiro , etc... */
	private String kindOfUser;
	/** Cpf */
	private String cpf;
	/** Nome completo */
	private String name;
	/** Matrícula na universidade */
	private String registration;
	
	/** Email */
	private String email;
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getKindOfUser(){
		return this.kindOfUser;
	}
	public void setKindOfUser(String kindOfUser){
		this.kindOfUser = kindOfUser;
	}
	
	public String getCpf(){
		return this.cpf;
	}
	public void setCpf(String cpf){
		this.cpf = cpf;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String getRegistration(){
		return this.registration;
	}
	public void setRegistration(String registration){
		this.registration = registration;
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("________________________________________"+'\n');
		buffer.append("Matrícula: "+this.getRegistration()+'\n');
		buffer.append("Nome: "+this.getName()+'\n');
		buffer.append("Cpf: "+this.getCpf()+'\n');
		buffer.append("Tipo: "+this.getKindOfUser()+'\n');
		buffer.append("Status: "+this.getStatus()+'\n');
		return buffer.toString();
	}
	
}

