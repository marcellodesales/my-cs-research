package br.ufal.graw;

import java.util.Vector;
import java.util.Hashtable;

import java.sql.SQLException;
import java.sql.Time;

import java.text.SimpleDateFormat;

import br.ufal.graw.DatabaseLayer;
import br.ufal.graw.Utility;
import br.ufal.graw.exception.CategoryNotFoundException;
import br.ufal.graw.exception.CommunityNotFoundException;
import br.ufal.graw.exception.MessageNotFoundException;
import br.ufal.graw.exception.UserLoginFormatException;
import br.ufal.graw.exception.UserNotFoundException;

/**
 *
 * @author Marcello de Sales
 */
public class Message{

	/** The database abstraction */
	private DatabaseLayer database;
	/** Receive abstract results of query */
	private Vector result;
	/** The message's author code. */
	private User sender;
	/** The message's author code. */
	private String senderID;
	/** The message's code */
	private String ID;
	/** The owner message's code */
	private String ownerMessageID;
	/** The message's title */
	private String title;
	/** The message's content */
	private String description;
	/** The community's ID which this message belongs to*/
	private String communityID;
	/** The message's categoryID which this message belongs to*/
	private String categoryID;

	/** Attempt retrieve a message with this code.
	 *@param code The message's code
	 *@param database The database abstraction
	 *@throws MessageNotFoundException
	 */
	public Message(String ID, DatabaseLayer database) throws MessageNotFoundException {
		this.database = database;
		this.result = database.query("SELECT * FROM message WHERE messageID='"+ID+"'");
		if (this.result.size() == 1){
			this.initObject((Hashtable)this.result.firstElement());
		}else{
			throw new MessageNotFoundException("Mensagem não encontrada com ID = "+ID, ID);
		}
	}

	/** Initialize the state of the message */
	private void initObject(Hashtable data){
		this.ID       		= (String)data.get("messageID");
		this.ownerMessageID	= (String)data.get("ownerMessageID");
		this.title         	= (String)data.get("title");
		this.senderID      	= (String)data.get("userID");
		this.sender         = this.initSender(this.senderID);
		this.description   	= Utility.getEmotionedText((String)data.get("description"));
		this.communityID 	= (String)data.get("communityID");
		this.categoryID     = (String)data.get("categoryID");
	}
	
	/** Initialize the sender object */
	private User initSender(String senderID){
		User messageSender = null;
		try {
			return AbstractUser.getRealUser(senderID,this.database);
		}catch (UserNotFoundException unfe){
			Utility.log(Utility.ERROR_FILE_LOG,unfe);
		}catch(NullPointerException npe){
			Utility.log(Utility.ERROR_FILE_LOG,npe);
		}
		return null;
	}
	
	private Message(){
	}

	/** Attempt add a message in database, if sucess, hasCreated method returns true.
	 * <b>PRE-Condition: The courseID must exists</b>
	 *@return A message persistent object.
	 */
	public static Message createNewMessage(String categoryID,String ownerMessageID, DatabaseLayer database)
		throws CategoryNotFoundException, MessageNotFoundException{
			Message message = new Message();
			Category forumCategory = new Category(categoryID,database);
			message.createInDatabase(forumCategory,ownerMessageID,database);
			return message;
	}
	
	/**
	 *Add a new message to database.
	 *@param courseID The course's ID which this message belongs to.
	 *@param ownerMessageID The message's owner ID.
	 *@param database The database abstraction where this student will stay.
	 */
	private void createInDatabase(Category category, String ownerMessageID,DatabaseLayer database)
	throws MessageNotFoundException {
		this.database = database;
		try{
			if (ownerMessageID == null){	//Look if the owner message's ID exists
				ownerMessageID = "";
			}else if (!ownerMessageID.equals("")){
				if (!Message.exists(ownerMessageID,database)){
					throw new MessageNotFoundException("Mensagem pai ("+ownerMessageID+") não encontrada.", ownerMessageID);
				}
			}
			this.ID             = Utility.getNewID();
			this.communityID    = Utility.transformToDatabase(category.getCommunityID());
			this.ownerMessageID = Utility.transformToDatabase(ownerMessageID);
			this.categoryID     = Utility.transformToDatabase(category.getID());
			this.database.update("INSERT INTO message VALUES ('"+this.ID+"','"+this.ownerMessageID+"','','','','"+this.communityID+"','"+this.categoryID+"')");
		}catch(SQLException sqle){
			this.ID = null;
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
	}

	/** Deletes an existing message from a persistent information database. */
    public static void deleteMessage(String ID, DatabaseLayer database) throws MessageNotFoundException{
		int quant = 0;
		try {
			quant = database.update("DELETE FROM message WHERE messageID='"+ID+"' OR ownerMessageID='"+ID+"'");
			if (quant == 0){
				throw new MessageNotFoundException("Mensagem não encontrada.", ID);
			}
		} catch (SQLException sqle){
			sqle.getMessage();
		}
    }

	/** @return The message's ID */
	public String getID(){
		return this.ID;
	}

	/** @return The owner message's ID */
	public String getOwnerID(){
		return this.ownerMessageID;
	}

	/** @return The community's ID which this message belongs to*/
	public String getCommunityID(){
		return this.communityID;
	}
	
	/** @return The category's ID which this message belongs to*/
	public String getCategoryID(){
		return this.categoryID;
	}

	/** @return The message's contents. */
	public String getDescription(){
		return this.description;
	}

	/** @return The message's title. */
	public String getTitle(){
		return this.title;
	}

	/** @return The message's author name. */
	public String getSenderID(){
		return (this.senderID == null) ? "Inexistente" : this.senderID;
	}

	/** @return The message's author object as a sender. */
	public User getSender(){
		return this.sender;
	}
	
	/** @return The message's author name. */
	public String getSenderFirstName(){
		return (this.sender == null) ? "Desconhecido" : this.sender.getFirstName();
	}

	/** @return The message's author name. */
	public String getSenderName(){
		return (this.sender == null) ? "Desconhecido" : this.sender.getName();
	}
	
	/** @return The message's author email. */
	public String getSenderEmail(){
		return (this.sender == null) ? "Desconhecido" : this.sender.getEmail();
	}

	/** Set information for this persistent object */
	public void setData(String title, String senderID, String description){
		try{
			this.title       = Utility.transformToDatabase(title);
			this.senderID    = senderID;
			this.sender      = this.initSender(senderID);
			this.description = Utility.getTextField(description);
			this.database.update("UPDATE message SET title='"+this.title+"', userID='"+this.senderID+"', description='"+this.description+"' WHERE messageID='"+this.ID+"'");
			this.description = Utility.getEmotionedText(this.description);
		}catch(SQLException sqle){
			System.out.println(sqle.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,sqle);
		}
	}

	/** Gets the short-format post date of the message. dd-MM-yyyy HH:mm */
	public String getPostDateShort(){
		return Utility.getFormatedDate(this.ID,"dd-MM-yyyy HH:mm:ss");
	}

	/** Gets the full-format post date of the message. EEEEEE, dd 'de' MMMMMM yyyy h:mm a */
	public String getPostDate(){
		return Utility.getFormatedDate(this.ID,"EEEEEE, dd 'de' MMMMMM 'de' yyyy HH:mm:ss");
	}

	/** Gets the full-format post date of the owner of the message */
	public String getOwnerPostDate(){
		return (this.ownerMessageID.equals(""))?"":Utility.getFormatedDate(this.ownerMessageID,"EEEEEE, dd 'de' MMMMMM yyyy HH:mm:ss");
	}

	private void error(String errorMessage){
		System.out.println(errorMessage);
	}
	
	/** Verifies if there's a message with messageID. */
	public static boolean exists(String messageID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT messageID FROM message WHERE messageID='"+messageID+"'");
		return (thisResult.size() == 1);
	}
	
	public void printData(){
		try{
			System.out.println("----------------------------------------------");
			System.out.println("ID da Mensagem = "+this.getID()+", Data de Criação: "+this.getPostDate());
			if (!this.ownerMessageID.equals(""))
				System.out.println("ID do Dono   = "+this.getOwnerID()+", Data de Criação: "+this.getOwnerPostDate());
			System.out.println("Title              = "+ this.getTitle());
			System.out.println("Nome autor         = "+ this.getSenderName());
			System.out.println("Email autor        = "+ this.getSenderEmail());
			System.out.println("Descricao          = "+ this.getDescription());
			System.out.println("ID Comunidade      = "+ this.getCommunityID()+"");
			System.out.println("ID Categoria       = "+ this.getCategoryID()+"");
			System.out.println("----------------------------------------------");
			System.out.println();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]){
		try{
			DatabaseLayer db = new DatabaseLayer();
			Category ca = new Category("979296976120",db);
			//Discipline disc  = new Discipline("12345",db);
			User user = AbstractUser.getRealUser("1015439644996", db);
			//Message me = new Message("979326498190",db);
			//me.printData();
			Message me = Message.createNewMessage(ca.getID(),"979326498190",db);
			me.setData("Sei... mais isso é pra quando?",user.getID(),"coisa lagal...");
			me.printData();

			//Message rep = Message.createNewMessage(disc.getID(),mes.getID(),db);
			//rep.setData("Agora sei q está!!!",user.getID(),"Poxa... tá massa!!!<BR><BR>");
			//rep.printData();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
