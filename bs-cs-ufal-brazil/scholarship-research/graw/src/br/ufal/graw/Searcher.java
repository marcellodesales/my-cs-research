/**
 * Crawler.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.graw;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;


/**
 * @author Marcello de Sales
 */
public class Searcher{
	
	public static String mountQuery(String select, String from , String communityID, String[] columns,String[] words){
		String query = "SELECT "+select+" FROM "+from+" WHERE "+
				"communityid='"+communityID+"' AND ( ";
		String likePart="";
		for (int i=0 ; i<words.length ; i++){
			for (int j=0 ; j< columns.length ; j++){
				likePart+=""+columns[j]+" LIKE '%"+words[i]+"%'";
				if (j+1<columns.length){
					likePart+=" OR ";
				}
			}
			if (i+1<words.length){
				likePart+=" OR ";
			}
		}
		query+=likePart+")";
		return query;
	}
	
	
	
	public static Object[] getObjects(Vector result,String key , DatabaseLayer database, Class classe ){
		Hashtable hash;
		String value;
		Constructor constructor;
		
		Object[] objects = new Object[result.size()];
		Object obj;
		
		try{
			for (int i=0 ; i < result.size() ; i++){
				hash = (Hashtable)result.get(i);
				value = (String) hash.get(key);
				
				constructor = classe.getConstructor(new Class[] {String.class,DatabaseLayer.class});
				obj = constructor.newInstance(new Object[]{value,database} );
				objects[i] = obj;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return objects;
	}
	
	
	public static void printResult(Vector result){
		Hashtable hash;
		Enumeration columns;
		String key,value;
		System.out.println("----------------------------------------------");
		for (int i=0 ; i < result.size() ; i++){
			hash = (Hashtable)result.get(i);
			columns = hash.keys();
			while (columns.hasMoreElements()){
				key = (String)columns.nextElement();
				value = (String) hash.get(key);
				System.out.print(key+"="+value+" | ");
			}
			System.out.println("----------------------------------------------");
		}
	}
	
	
}

