/**
 * Critery.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw;

import java.util.Hashtable;
import java.util.Enumeration;

public class Critery{
	
	public static final String LINK_TABLE = "link";
	public static final String DOCUMENT_TABLE = "document";
	public static final String MESSAGE_TABLE = "message";
	
	public static final String ALL_COLUMNS = "*";
	
	private Hashtable like;
	private Hashtable equal;
	private Hashtable notEqual;
	private String goal;
	private String from;

	
	public Critery(String goal , String from){
		this.like = new Hashtable();
		this.equal = new Hashtable();
		this.notEqual = new Hashtable();
		this.goal = goal;
		this.from = from;
	}
	
	public void addLikeCritery(String column , String value){
		this.like.put(column,value);
	}
	
	public void addEqualCritery(String column , String value){
		this.equal.put(column,value);
	}
	
	public void addNotEqualCritery(String column , String value){
		this.notEqual.put(column,value);
	}
	
	public Hashtable getLikeCritery(){
		return this.like;
	}
	
	public Hashtable getEqualCritery(){
		return this.equal;
	}
	
	public Hashtable getNotEqualCritery(){
		return this.notEqual;
	}
	
	/**
	@return Retorna a query em sql baseado nas condicoes que foram cadastradas.
	 */
	public String getMySqlQuery(boolean conjunction){
		String query = "SELECT "+this.goal+" FROM "+this.from+" ";
		String subQuery="", subQuery2;
		String op = (conjunction==true) ? " AND ": " OR ";
		
		subQuery = this.mountSubQuery("=",this.equal,"","",op);
		
		subQuery2 = this.mountSubQuery("!=",this.notEqual,"","",op);
		if (subQuery.length()>0 && subQuery2.length()>0){ subQuery+=op; }
		subQuery+=subQuery2;
		
		subQuery2 = this.mountSubQuery("LIKE",this.like,"%","%",op);
		if (subQuery.length()>0 && subQuery2.length()>0){ subQuery+=op; }
		subQuery+=subQuery2;
		
		if (subQuery.length()>0){
			query += " WHERE " ;
			query += subQuery;
		}
		return query;
		
	}
	
	/**
	Monta o sql baseados nas tabelas hash de criterios.
	Os parametros sao;
	@param cond - operador da condicao. ex: = , != , LIKE
	@param hash - tabela que contem: [coluna - valor]
	@param beforeContent - usado geralmente junto com o like. Geralmente mascara de substring.
	 		Ex: % , __
	@param afterContent - usado geralmente junto com o like. Ex: % , __
	 */
	public String mountSubQuery(String cond , Hashtable hash,String beforeContent, String afterContent, String op){
		String key,content,query="";
		boolean hasMore;
		Enumeration keys = hash.keys();
		hasMore = keys.hasMoreElements();
		
		/* Monta a subQuery de busca */
		if (hasMore){
			do{
				key 	= (String) keys.nextElement();
				content = (String) hash.get(key);
				query += " "+key+" "+cond+" '"+beforeContent+""+content+""+afterContent+"'";
				if ( hasMore=keys.hasMoreElements() ){
					query+=op;
				}
			}while(hasMore);
		}
		return query;
	}
	
	public static void main(String args[]){
		Critery c = new Critery("*","link");
		c.addLikeCritery("linkid","1");
		c.addEqualCritery("bunda","boa");
		c.addNotEqualCritery("mulher","feia");
		System.out.println(c.getMySqlQuery(true));
	}
	
	
}

