/*
 * Criado em 31/10/2003
 *
 * Para alterar o gabarito para este arquivo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
package br.al.marcellojunior.performance.serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Vector;

/**
 * @author Marcello Junior
 *
 * Projeto desenvolvido em J2EE
 */
public class SerializationTest {

	static long start,end;
	OutputStream out = null;
	InputStream in   = null;
	OutputStream outBuffer = null;
	InputStream inBuffer   = null;
	ObjectOutputStream objectOut = null;
	ObjectInputStream objectIn   = null;

	public Person getObject(){ 
		
		Person p = new Person("SID","austin");
		Vector v = new Vector();
		for(int i=0;i<7000;i++){
			 v.addElement("StringObject"+i);
		}
		p.setData(v);
		return p;
	}

	public void readObject(){
		
		try{
			in = new FileInputStream("c:/temp/test.txt");
			inBuffer = new BufferedInputStream(in);
			objectIn = new ObjectInputStream(inBuffer);
			objectIn.readObject();
		}catch(Exception e){
			e.printStackTrace();}
		finally{
			if(objectIn != null)
				try{ 
					objectIn.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			 }
	}

	public void writeObject(){
		try{
			out = new FileOutputStream("c:/temp/test.txt");
			outBuffer = new BufferedOutputStream(out);
			objectOut = new ObjectOutputStream(outBuffer);
			objectOut.writeObject(getObject());

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(objectOut != null)
				try{ 
					objectOut.close();
				}catch(IOException e){
					e.printStackTrace();
				}
		}
	}
	
	public static void main(String[] args){

		SerializationTest st = new SerializationTest();
		start = System.currentTimeMillis();
		st.writeObject();
		st.readObject();
		end = System.currentTimeMillis();
		System.out.println("Time taken for writing and reading : "+ (end-start) + " milli seconds");
	}
}
