package br.ufal.graw;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;

import java.sql.SQLException;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Message;
import br.ufal.graw.exception.CommunityNotFoundException;
import br.ufal.graw.exception.MessageNotFoundException;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.CategoryNotFoundException;

/**
 *
 * @author Marcello de Sales
 */
public class Forum{

	/**	The code which this MessageBoard belongs to. */
	private String communityID;
	///**	A vector which all messages which belongs to this MessageBoard */
	//private Vector messages;
	
	/** Receive abstract results of query */
	private Vector result;
	/** The database abstraction */
	private DatabaseLayer database;
	
	public Hashtable replays;

	/** Retrieve a messageBoard with all its messages.
	 *@param courseID The course's code which this messageBoard belongs.
	 *@param database Tha Database abstraction.
	 */
	public Forum(String communityID, DatabaseLayer database)
	throws CommunityNotFoundException{
		this.database = database;
		//this.messages = new Vector();
		
		if (AbstractCommunity.exists(communityID,database)){
			this.communityID = communityID;
		}else{
			throw new CommunityNotFoundException("Comunidade não encontrada: "+communityID, communityID);
		}
	}

	/**	Pre: The vector result contains the result of a valid SELECT query. */
	/*	private void initObject(){
		Hashtable hash;
		Message message;
		String messageID;
		try{
			for (int i = 0; i < result.size(); i++){
				hash = (Hashtable)this.result.get(i); //initially, the result variable is in the constructor
				messageID = (String)hash.get("messageID");
				message   = new Message(messageID,this.database);
				this.messages.add(message);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,e);
		}
		this.replays = this.getMessageReplays();
	 }*/
	
	/** Returns a combination of ownerMessageID and a vector containing all replays. */
	/*private Hashtable getMessageReplays(){
		Hashtable replays   = new Hashtable();
		Hashtable hashtable = new Hashtable();
		Vector replay;
		Message message,tempMessage;
		try {
			for (int i=0; i<this.messages.size(); i++){
				message = (Message)this.messages.get(i);
				this.result = this.database.query("SELECT messageID FROM message where ownerMessageID='"+message.getID()+"' AND courseID='"+this.courseID+"' ORDER BY messageID");
				replay = new Vector();
				for (int j = 0; j < this.result.size(); j++){
					hashtable        = (Hashtable)this.result.get(j);
					String messageID = (String)hashtable.get("messageID");
					tempMessage = new Message(messageID,this.database);
					replay.add(tempMessage);
				}
				replays.put(message.getID(),replay);
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,e);
				
		}
		return replays;
	 }*/

	public int getNumberOfMessages(){
		this.result = this.database.query("SELECT count(*) FROM message where communityID='"+communityID+"'");
		return Integer.parseInt((String)((Hashtable)this.result.firstElement()).get("count(*)"));
	}
	
	public int getNumberOfTopics(){
		this.result = this.database.query("SELECT count(*) FROM message where communityID='"+communityID+"' AND ownerMessageID=''");
		return Integer.parseInt((String)((Hashtable)this.result.firstElement()).get("count(*)"));
	}
	
	private void createNewMessage(String categoryID, String title, String description, User user, String ownerMessageID)
		throws CategoryNotFoundException {
		
		try {
			Message newMessage = Message.createNewMessage(categoryID,ownerMessageID,this.database);
			newMessage.setData(title,user.getID(),description);
		} catch (MessageNotFoundException mnfe){
			mnfe.printStackTrace();
		}
	}
	
	private Message createNewMessageAndReturn(String ownerMessageID, String title, String description,User user, String categoryID){
		Message newMessage = null;
		try {
			newMessage = Message.createNewMessage(categoryID,ownerMessageID,this.database);
			newMessage.setData(title,user.getID(),description);
			return newMessage;
		} catch (CategoryNotFoundException cnfe){
			cnfe.printStackTrace();
		} catch (MessageNotFoundException mnfe){
			mnfe.printStackTrace();
		}
		return newMessage;
	}
	
	/** Puts a new message topic in the forum */
	public void putNewMessageNotReturning(String title, String description,User user, Category forumCategory){
		try{
			this.createNewMessage(forumCategory.getID(),title,description,user,"");
		} catch (CategoryNotFoundException cnfe){
			cnfe.printStackTrace();
		}
	}
	
	/** Puts a new replay message in the forum */
	public void putNewReplayNotReturning(String ownerMessageID, String title, String description,User user, String communityID, Category forumCategory){
		try{
			this.createNewMessage(forumCategory.getID(),title,description,user,ownerMessageID);
		} catch (CategoryNotFoundException cnfe){
			cnfe.printStackTrace();
		}
	}
	
	/** Puts a new message topic in the forum */
	public Message putNewMessage(String title, String description,User user, Category forumCategory){
		return this.createNewMessageAndReturn("",title,description,user,forumCategory.getID());
	}
	
	/** Puts a new replay message in the forum */
	public Message putNewReplay(String ownerMessageID, String title, String description,User user, Category forumCategory){
		return this.createNewMessageAndReturn(ownerMessageID,title,description,user,forumCategory.getID());
	}
	
	/*
	* Returns a Enumeration the all of messages.
	*/
	/*	public Enumeration getMessages(){
		return this.messages.elements();
	 }*/
	
	/** Gets the messages of the forum. */
    public Vector getMessages(int offset, int limit){
		Vector messages = new Vector();
    	this.result     = this.database.query("SELECT messageID FROM message WHERE communityID='"+this.communityID+"' AND ownerMessageID='' ORDER BY messageID LIMIT "+offset+","+limit);
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String messageID = (String)((Hashtable)this.result.get(i)).get("messageID");
					Message message = new Message(messageID,this.database);
		  			messages.add(message);
	      		}
        	}
		} catch (MessageNotFoundException mnfe){
			System.err.println(mnfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,mnfe);
		}
		return messages;
    }

	/**
	* Returns a Enumeration of the portion of messages between fromIndex, inclusive, and toIndex, exclusive.
	
	public Enumeration getMessages(int from, int to){
		Vector range = new Vector();
		if ((from < to) && (from >= 0) && (to <= this.getNumberOfMessages()) ){
			for (int i=from ; i < to ; i++){
				range.add(this.messages.get(i));
			}
			return range.elements();
		}else{
			return null;
		}
	}
	*/
	public Vector getReplays(String parentMessageID) throws MessageNotFoundException{
		Vector replays = new Vector();
		try {
			if (Message.exists(parentMessageID,database)){
				this.result = this.database.query("SELECT messageID FROM message WHERE ownerMessageID='"+parentMessageID+"' AND communityID='"+this.communityID+"' ORDER BY messageID");
				for (int j = 0; j < this.result.size(); j++){
					String messageID =(String)((Hashtable)this.result.get(j)).get("messageID");
					Message replayMessage = new Message(messageID,this.database);
					replays.add(replayMessage);
				}

			} else throw new MessageNotFoundException("Messagem pai com ID "+parentMessageID+" não encontrado!",parentMessageID);
		} catch (MessageNotFoundException mnfe){
			mnfe.printStackTrace();
		}
		return replays;
	}
	
	public static String getNumberReplays(String parentMessageID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT COUNT(*) FROM message WHERE ownerMessageID = '"+parentMessageID+"'");
		return (String)((Hashtable)(thisResult.firstElement())).get("COUNT(*)");
	}
	
    public static void deleteMessage(String messageID, DatabaseLayer database)
		throws ResourceNotFoundException{
		try{
			int	quant = database.update("DELETE FROM message WHERE messageID='"+messageID+"'");
			if (quant == 0){
				throw new ResourceNotFoundException("Impossível apagar messagem ("+messageID+"), categoria não encontrado",messageID);
			}
		} catch (SQLException sqle){
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
    }
	
    public static void deleteAllMessages(Category forumCategory, DatabaseLayer database){
		try{
			database.update("DELETE FROM message WHERE categoryID='"+forumCategory.getID()+"'");
		} catch (SQLException sqle){
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
	}

	public ForumCategory[] getCategories(){
		ForumCategory[] categories = {};
		this.result = this.database.query("SELECT categoryID FROM category WHERE communityID='"+this.communityID+"' AND kindOfCategoryID='"+Category.FORUM_CATEGORY+"' ORDER BY title");
		if (this.result.size() > 0){
			categories = new ForumCategory[this.result.size()];
			try{
				for (int i=0; i < this.result.size(); i++){
					String categoryID = (String)((Hashtable)this.result.get(i)).get("categoryID");
					categories[i] = new ForumCategory(categoryID,database);
				}
			} catch (CategoryNotFoundException cnfe){
				cnfe.printStackTrace();
			}
		}
		return categories;
	}

	private void error(String errorMessage){
		System.out.println(errorMessage);
	}

	private void printData(){
		try{
			Message msg;
			System.out.println("--------------------------------------------");
			System.out.println("Mensagens: "+this.getNumberOfMessages());
			System.out.println("Tópicos: "+this.getNumberOfTopics());
			Vector replay = new Vector();
			Vector messages = this.getMessages(0,20);
			for (int i=0 ; i< messages.size();i++){
				msg = (Message)messages.get(i);
				System.out.println("Código da mensagem = "+msg.getID());
				System.out.println("Nome do autor      = "+msg.getSenderName());
				System.out.println("Título da mensagem = "+msg.getTitle());
				System.out.println("Descricao          = "+msg.getDescription());
				System.out.println("Comunidade         = "+msg.getCommunityID());
				System.out.println("++++++");
				System.out.println();
				System.out.println("Agora as Réplicas");
				for (Enumeration e = this.getReplays(msg.getID()).elements(); e.hasMoreElements();){
					Message me = (Message)e.nextElement();
					me.printData();
				}
				/*				replay = ((Vector)this.replays.get(msg.getID()));
				for (int j=0; j < replay.size(); j++){
					System.out.println(j);
					Message me = (Message)replay.get(j);
					me.printData();
				 }*/
			}
			System.out.println("--------------------------------------------");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]){
		try{
			DatabaseLayer db = new DatabaseLayer();
			Community discipline = new Discipline("1015439199628",db);
			//User user = new Student("1015439644996",db);
			//Forum forum = new Forum(discipline.getID(),db);
			//Message me = new Message("1004742986560",db);
			//forum.putNewReplay(me.getID(),"Claro que vai funcionar...","E Então... já está funcionando novamente!",user,discipline.getID());
			//forum.printData();
			
			/*Vector tables = db.query("SHOW COLUMNS FROM community");
			
			for(int i=0; i < tables.size(); i++){
				String nomeTable = (String)((Hashtable)tables.get(i)).get("Field");
				System.out.println(nomeTable);
			 }*/
			
			/*ForumCategory[] categories = forum.getCategories();
			for (int i=0; i < categories.length; i++){
				categories[i].printAll();
			 }*/
			
			/*ResourceCategory[] linkCategory = discipline.getLinkCategories();
			for (int i=0; i < linkCategory.length; i++){
				linkCategory[i].printAll();
			 }*/
			
			ResourceCategory[] documentCategory = discipline.getDocumentCategories();
			for (int i=0; i < documentCategory.length; i++){
				documentCategory[i].printAll();
			}

			/*Enumeration e  = mb.getMessages(0,2);
			Message m;
			while (e.hasMoreElements()){
				m = (Message)e.nextElement();
				System.out.println(m.getName());
			 }
			 */
		}catch(Exception e){
			e.printStackTrace();
		 }
	}
}

