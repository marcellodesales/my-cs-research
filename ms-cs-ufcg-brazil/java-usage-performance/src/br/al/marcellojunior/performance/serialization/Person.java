/*
 * Criado em 31/10/2003
 *
 * Para alterar o gabarito para este arquivo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
package br.al.marcellojunior.performance.serialization;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author Marcello Junior
 *
 * Projeto desenvolvido em J2EE
 * Use 'transient' key word for unnecessary variables that need 
 * not be read from/written into streams. In this example, the data
 * Vector does not need to be serializable. 
 */
public class Person implements Serializable {

	private String name;
	private transient Vector data;
	private String address;
	
	public Person(String name,String address){
		this.name 	 = name;
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public Vector getData(){
		return data;   
	}

	public String getName(){
		return name;
	}

	public void setData(Vector data){
		this.data = data;
	}
}
