package br.ufal.graw;

import java.util.Vector;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Date;
import java.util.Enumeration;

import java.text.SimpleDateFormat;

import java.net.URLEncoder;
import java.net.URLDecoder;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Discipline;
import br.ufal.graw.Course;
import br.ufal.graw.web.ServletUtility;
import br.ufal.graw.exception.DisciplineNotFoundException;
import br.ufal.graw.exception.CourseException;
import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.UserNotFoundException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.GregorianCalendar;

/**
 *
 * @author Marcello de Sales
 */
public class Utility{

	public static final String ERROR_FILE_LOG = "c:/desenv/graw/logs/error.log";
	public static final String ACESS_FILE_LOG = "c:/desenv/graw/logs/access.log";
	public static final String OPERATION_FILE_LOG = "c:/desenv/graw/logs/operation.log";
	/** It's the string %0D%0A */
	public static final String URLLINE  = "%0D%0A";
	/** It's the tag < br > */
	public static final String HTMLLINE = "<br>";
	public static final String IMAGE_DIR = ServletUtility.COMPLETE_DNS+ServletUtility.CONTEXT_PATH+"sources/images/emotions/";

	private DatabaseLayer database;
	private Vector result;

	public Utility(DatabaseLayer database){
		this.database = database;
	}

	public Utility(){
	}
	
	// Substitui uma substring por outra em uma String
	public static String strReplace(String givenString, String token, String newToken) {
		int pos = 0, ini = 0;
		String string = new String("");
		// Procura todas as ocorrências e substitui
		while ((pos = givenString.indexOf(token, pos)) != -1) {
			string += givenString.substring(ini, pos) + newToken;
			pos += token.length();
			ini = pos;
		}
		// Completa com o final da String
		string += givenString.substring(ini);
		return string;
	}

	// Converte data String para vetor de inteiros
	public static int[] parseData(String data) {
		int dataInt[] = new int[3];
		StringTokenizer st = new StringTokenizer(data);

		// Separa data e hora pegando apenas a data
		st = new StringTokenizer(st.nextToken());

		// Separa ano, mês e dia
		try {
			dataInt[0] = Integer.parseInt(st.nextToken("-"));
			dataInt[1] = Integer.parseInt(st.nextToken("-"));
			dataInt[2] = Integer.parseInt(st.nextToken("-"));
		} catch (Exception e) {
			System.err.println("Util: Erro convertendo data!");
		}

		return dataInt;
	}

	public static String transformToDatabase(String field){
		field = Utility.strReplace(field,"\\","\\\\");
		field = Utility.strReplace(field,"'","\\'");
		field = Utility.strReplace(field,"\"","\\'");
		return field;
	}
	
	public static String getTextField(String text){
		text = Utility.strReplace(text,"<REPLY_LINE>",Utility.HTMLLINE);
		text = Utility.strReplace(URLEncoder.encode(Utility.transformToDatabase(text)),Utility.URLLINE,Utility.HTMLLINE);
		return URLDecoder.decode(text);
	}

	public static void log(String file , String message , Object from){
		try{
			FileWriter writer = new FileWriter(file,true);
			String ls = System.getProperty("line.separator");
			writer.write("--------------------------------------"+ls);
			writer.write("Classe: "+from.getClass().getName()+". ");
			writer.write(new GregorianCalendar().getTime()+ls);
			writer.write(message+ls);
			writer.write("--------------------------------------"+ls);
			writer.flush();
			writer.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public static void log(String file , String message ){
		try{
			FileWriter writer = new FileWriter(file,true);
			String ls = System.getProperty("line.separator");
			writer.write("--------------------------------------"+ls);
			writer.write(new GregorianCalendar().getTime()+ls);
			writer.write(message+ls);
			writer.write("--------------------------------------"+ls);
			writer.flush();
			writer.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

	public static void log(String file , Throwable throwable){
		try{
			FileWriter writer = new FileWriter(file,true);
			String ls = System.getProperty("line.separator");
			writer.write("--------------------------------------"+ls);
			writer.write(new GregorianCalendar().getTime()+ls);
			writer.write(getStackTrace(throwable));
			writer.write("--------------------------------------"+ls);
			writer.flush();
			writer.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

	public static String getStackTrace(Throwable throwable){
  		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  		PrintStream printStream = new PrintStream(outputStream);
  		throwable.printStackTrace(printStream);
  		return outputStream.toString();
  	}
	
	/** Returns a well-formed timestamp defined in format.
	 *  -> format = EEEEEE, dd 'de' MMMMMM yyyy h:mm a
	 *  -> format = dd-MM-yyyy HH:mm @spec SimpleDateFormat
 	 */
	public static String getFormatedDate(String milisecondsID, String format){
		Date date = new Date(Long.parseLong(milisecondsID));
		String dateString = new SimpleDateFormat(format).format(date);
		return dateString;
	}
	
	public static String getMillisecondsNow(){
		return (new Date().getTime())+"";
	}
	
	public static String getNewID(){
		return Utility.getMillisecondsNow();
	}
	
	public static String getTime(){
		return Utility.getFormatedDate(Utility.getNewID(),"EEEEEE, dd 'de' MMMMMM yyyy");
	}
	
	public static String getEmotionedText(String description){
		Hashtable emotions = new Hashtable();
		emotions.put("(Y)","thumbs_up.gif");               emotions.put("(y)","thumbs_up.gif");
		emotions.put("(N)","thumbs_down.gif");		 	   emotions.put("(n)","thumbs_down.gif");
		emotions.put("(B)","beer_yum.gif");    		 	   emotions.put("(b)","beer_yum.gif");
		emotions.put("(D)","martini_shaken.gif");          emotions.put("(d)","martini_shaken.gif");
		emotions.put("(X)","girl_handsacrossamerica.gif"); emotions.put("(x)","girl_handsacrossamerica.gif");
		emotions.put("(Z)","guy_handsacrossamerica.gif");  emotions.put("(z)","guy_handsacrossamerica.gif");
		emotions.put(":-[","bat.gif");     				   emotions.put(":[","bat.gif");
		emotions.put("(})","girl_hug.gif");
		emotions.put("({)","dude_hug.gif");
		emotions.put(":-)","regular_smile.gif");             emotions.put(":)","regular_smile.gif");
		emotions.put(":-D","teeth_smile.gif");    			 emotions.put(":d","teeth_smile.gif"); emotions.put(":D","teeth_smile.gif");
		emotions.put(":-O","omg_smile.gif");      			 emotions.put(":o","omg_smile.gif"); emotions.put(":O","omg_smile.gif");
		emotions.put(":-P","tounge_smile.gif");   			 emotions.put(":p","tounge_smile.gif"); emotions.put(":P","tounge_smile.gif");
		emotions.put(";-)","wink_smile.gif");     			 emotions.put(";)","wink_smile.gif");
		emotions.put(":-(","sad_smile.gif");      		 	 emotions.put(":(","sad_smile.gif");
		emotions.put(":-S","confused_smile.gif"); 			 emotions.put(":s","confused_smile.gif");
		emotions.put(":-|","whatchutalkingabout_smile.gif"); emotions.put(":|","whatchutalkingabout_smile.gif");
		emotions.put(":'(","cry_smile.gif");
		emotions.put(":-$(","embaressed_smile.gif"); emotions.put(":$","embaressed_smile.gif");
		emotions.put("(H)","shades_smile.gif");   	 emotions.put("(h)","shades_smile.gif");
		emotions.put(":-@","angry_smile.gif");   	 emotions.put(":@","angry_smile.gif");
		emotions.put("(A)","angel_smile.gif"); 		 emotions.put("(a)","angel_smile.gif");
		emotions.put("(6)","devil_smile.gif");
		emotions.put("(L)","heart.gif");        emotions.put("(l)","heart.gif");
		emotions.put("(U)","broken_heart.gif"); emotions.put("(u)","broken_heart.gif");
		emotions.put("(K)","kiss.gif");  		emotions.put("(k)","kiss.gif");
		emotions.put("(G)","present.gif");		emotions.put("(g)","present.gif");
		emotions.put("(F)","rose.gif");  		emotions.put("(f)","rose.gif");
		emotions.put("(W)","wilted_rose.gif");	emotions.put("(w)","wilted_rose.gif");
		emotions.put("(P)","camera.gif");		emotions.put("(p)","camera.gif");
		emotions.put("(~)","film.gif");
		emotions.put("(T)","phone.gif");		emotions.put("(t)","phone.gif");
		emotions.put("(@)","kittykay.gif");
		emotions.put("(&)","bowwow.gif");
		emotions.put("(C)","coffee.gif");		emotions.put("(c)","coffee.gif");
		emotions.put("(I)","lightbulb.gif");	emotions.put("(i)","lightbulb.gif");
		emotions.put("(S)","moon.gif");
		emotions.put("(*)","star.gif");
		emotions.put("(8)","musical_note.gif");
		emotions.put("(E)","envelope.gif");  	emotions.put("(e)","envelope.gif");
		emotions.put("(^)","cake.gif");
		emotions.put("(O)","clock.gif");		emotions.put("(o)","clock.gif");
		
		Enumeration codes = emotions.keys();
		while (codes.hasMoreElements()){
			String code = (String)codes.nextElement();
			String img  = "<img src=\""+Utility.IMAGE_DIR+emotions.get(code)+"\">";
			description = Utility.strReplace(description,code,img);
		}
		return description;
	}
	
	public static void main(String args[]){
		//DatabaseLayer db = new DatabaseLayer();
		/*User users[];
		try{
			db.connect();
			Utility u = new Utility(db);
			users = u.getAllUsers();
			for (int i=0 ; i< users.length ; i++){
				System.out.println(users[i].getName());
			}

		}catch(Exception e){
			e.printStackTrace();
		 }*/
		Utility u = new Utility();
		String desc = "Oi ;) Agora quero ver!";
		desc = Utility.getEmotionedText(desc);
		System.out.println(desc);
	}
}
