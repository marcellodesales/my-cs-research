package br.ufal.graw;

import java.sql.SQLException;
import java.util.Vector;
import java.util.Hashtable;

import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.GrawException;
import br.ufal.graw.exception.CommunityNotFoundException;
import br.ufal.graw.exception.ResourceNotFoundException;
import br.ufal.graw.exception.UserNotFoundException;

public abstract class AbstractCommunity implements Community, ResourceManipulator{

	protected DatabaseLayer database;
	protected Vector result;
	
	protected String ID;
	protected String title;
	protected String description;
	protected int visibility;
	protected int associationType;
	protected String kind;
	protected String categoryID;
	protected String subcategoryID;
	protected char status;
	protected String responsibleID;
	/** Integer numer that defines the working tools */
	protected String tools;

	public AbstractCommunity(String communityID, DatabaseLayer database)
		throws CommunityException{
		this.database = database;
		this.result = this.database.query("SELECT * FROM community WHERE community.communityID='"+communityID+"'");
		if (this.result.size() == 0)
			throw new CommunityException("Comunidade nao encontrada em nossos registros. ["+communityID+"]");
		else {
			this.initObject((Hashtable)result.firstElement());
		}
	}
	
	/** Initializes the state of the community */
	private void initObject(Hashtable data){
		this.ID          	= (String)data.get("communityID");
		this.title 			= (String)data.get("title");
		this.description 	= (String)data.get("description");
		this.visibility 	= Integer.parseInt((String)data.get("visibility"));
		this.associationType= Integer.parseInt((String)data.get("association"));
		this.kind 	        = (String)data.get("kind");
		this.categoryID 	= (String)data.get("categoryID");
		this.subcategoryID 	= (String)data.get("subcategoryID");
		this.responsibleID 	= (String)data.get("userID");
		this.status         = ((String)data.get("status")).charAt(0);
		this.tools      	= (String)data.get("tools");
	}
	
	protected static String createACommunityInDatabase(DatabaseLayer database,User responsible, String title, String description,  int visibility, int association, String kind, String categoryID, String subcategoryID){
		String communityID = null;
		try{
			communityID = Utility.getNewID();
			String responsibleUserID = responsible.getID();
			/*Note que essa comunidade eh colocada com o status Waiting, porque o administrador precisa aprovar essa comunidade
			 para ela ficar ativa		 */
			database.update("INSERT INTO community (communityID, title, description, visibility, association, kind, categoryID, subcategoryID, status) "+
								"VALUES ('"+communityID+"','"+title+"','"+description+"','"+visibility+"','"+association+"','"+kind+"','"+categoryID+"','"+subcategoryID+"','"+Community.WAITING+"')");
			database.update("INSERT INTO manages (userID,communityID) VALUES ('"+responsibleUserID+"','"+communityID+"'");
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return communityID;
	}
	
	public User getResponsible() throws UserNotFoundException{
		return AbstractUser.getRealUser(this.responsibleID,this.database);
	}
	
	public String getID(){
		return this.ID;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getKind(){
		return this.kind;
	}
	
	public int getAssociationType(){
		return this.associationType;
	}
	
	public int getVisibility(){
		return this.visibility;
	}
	
	public String getCategoryID(){
		return this.categoryID;
	}
	
	public String getCategoryDescription() throws ResourceNotFoundException{
		return new CommunityCategory(this.categoryID,database).getDescription();
	}
	
	public String getSubcategoryID(){
		return this.subcategoryID;
	}
	
	public String getSubcategoryDescription() throws ResourceNotFoundException{
		return new CommunitySubcategory(this.subcategoryID,database).getDescription();
	}
	
	public boolean isActive(){
		return (this.status == 'A');
	}
	
	public int getQuantUsers(){
		this.result = this.database.query("SELECT count(userID) FROM communityinterests WHERE communityID='"+this.ID+"' AND status='"+Community.ACTIVED+"'");
		return Integer.parseInt((String)((Hashtable)this.result.firstElement()).get("count(userID)"))+1; // O +1 corresponde ao responsável
	}
	
	public int getQuantMessages(){
		int quant = 0;
		try{
			Forum forum = new Forum(this.ID,this.database);
			quant = forum.getNumberOfMessages();
		} catch (CommunityNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		return quant;
	}
	
	public int getQuantDocuments(){
		this.result = this.database.query("SELECT count(documentID) FROM document WHERE communityID='"+this.ID+"'");
		return Integer.parseInt((String)((Hashtable)this.result.firstElement()).get("count(documentID)"));
	}
	
	public int getQuantLinks(){
		this.result = this.database.query("SELECT count(linkID) FROM link WHERE communityID='"+this.ID+"'");
		return Integer.parseInt((String)((Hashtable)this.result.firstElement()).get("count(linkID)"));
	}
	
	/** Verifies if a community with communityID exists. */
	public static boolean exists(String communityID, DatabaseLayer database){
		Vector thisResult = database.query("SELECT communityID FROM community WHERE communityID='"+communityID+"'");
		return (thisResult.size() == 1);
	}
	
	public static Community getRealCommunity(String communityID , DatabaseLayer database) throws CommunityException{
		String kindOfCommunity = AbstractCommunity.getKindOfCommunity(communityID,database);
		Community community;
		if (kindOfCommunity.equals(Community.DISCIPLINE) ){
			community = new Discipline(communityID,database);
		}else if ( kindOfCommunity.equals(Community.EXTRA_COURSE) ){
			community = new ExtraCourse(communityID,database);
		}else if ( kindOfCommunity.equals(Community.GROUP) ){
			community = new Group(communityID,database);
		}else{
			throw new CommunityException("Tipo de comunidade desconhecida.");
		}
		return community;
	}
	
	public void setTitle(String newTitle){
		try{
			newTitle = Utility.transformToDatabase(newTitle);
			this.database.update("UPDATE community SET title='"+newTitle+"' WHERE communityID='"+this.ID+"'");
			this.title = newTitle;
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
	public void setDescription(String newDescription){
		try{
			newDescription = Utility.transformToDatabase(newDescription);
			this.database.update("UPDATE community SET description='"+newDescription+"' WHERE communityID='"+this.ID+"'");
			this.description = newDescription;
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}
	
	public static String getKindOfCommunity(String communityID, DatabaseLayer database) throws CommunityException{
		Vector result;
		result = database.query("SELECT kind FROM community WHERE communityID='"+communityID+"'");
		if (result.size() == 0 ){
			throw new CommunityException("Curso nao encontrado.",communityID);
		}
		Hashtable hash = ((Hashtable)result.firstElement());
		String kinfOfCommunity = (String)hash.get("kind");
		if (kinfOfCommunity == null){
			throw new CommunityException("Tipo de comunidade não especificado.",communityID);
		}else{
			return kinfOfCommunity;
		}
	}
		
	private ResourceCategory[] getResourceCategories(String kindID){
		ResourceCategory[] ress = {};
    	this.result  = this.database.query("SELECT categoryID FROM category WHERE communityID='"+this.ID+"' AND kindOfCategoryID='"+kindID+"' ORDER BY title");
		try {
			if (this.result.size() > 0){
				ress = new ResourceCategory[this.result.size()];
	    		for (int i=0; i < this.result.size(); i++){
          			String categoryID = (String)((Hashtable)this.result.get(i)).get("categoryID");
					ress[i] = new ResourceCategory(categoryID,this.database);
	      		}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return ress;
	}
	
	public ForumCategory[] getForumCategories(){
		ForumCategory[] ress = {};
    	this.result  = this.database.query("SELECT categoryID FROM category WHERE communityID='"+this.ID+"' AND kindOfCategoryID='"+Category.FORUM_CATEGORY+"' ORDER BY title");
		try {
			if (this.result.size() > 0){
				ress = new ForumCategory[this.result.size()];
	    		for (int i=0; i < this.result.size(); i++){
          			String categoryID = (String)((Hashtable)this.result.get(i)).get("categoryID");
					ress[i] = new ForumCategory(categoryID,this.database);
	      		}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return ress;
	}
	
	public ResourceCategory[] getDocumentCategories(){
		return this.getResourceCategories(Category.DOCUMENT_CATEGORY);
	}
	
	public ResourceCategory[] getLinkCategories(){
		return this.getResourceCategories(Category.LINK_CATEGORY);
	}
	
	/** Gets the Links of this community. */
    public Vector getLinks(){
		Vector links = new Vector();
    	this.result  = this.database.query("SELECT linkID FROM link WHERE communityID='"+this.ID+"'");
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String linkID = (String)((Hashtable)this.result.get(i)).get("linkID");
					Link link     = new Link(linkID,this.database);
		  			links.add(link);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			System.out.println(rnfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,rnfe);
		}
		return links;
    }
	
	/** Gets the Links of the community. */
    public Vector getLinks(int offset, int limit){
		Vector links = new Vector();
    	this.result  = this.database.query("SELECT linkID FROM link WHERE communityID='"+this.ID+"' LIMIT "+offset+","+limit);
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String linkID = (String)((Hashtable)this.result.get(i)).get("linkID");
					Link link     = new Link(linkID,this.database);
		  			links.add(link);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			System.out.println(rnfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,rnfe);
		}
		return links;
    }

	/** Gets the Documents of the Course. */
    public Vector getDocuments(){
		Vector documents = new Vector();
    	this.result      = this.database.query("SELECT documentID FROM document WHERE communityID='"+this.ID+"'");
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String documentID = (String)((Hashtable)this.result.get(i)).get("documentID");
					Document document = new Document(documentID,this.database);
		  			documents.add(document);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			System.out.println(rnfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,rnfe);
		}
		return documents;
    }

	/** Gets the Documents of the Course. */
    public Vector getDocuments(int offset, int limit){
		Vector documents = new Vector();
    	this.result      = this.database.query("SELECT documentID FROM document WHERE communityID='"+this.ID+"' LIMIT "+offset+","+limit);
		try {
			if (this.result.size() > 0){
	    		for (int i=0; i < this.result.size(); i++){
          			String documentID = (String)((Hashtable)this.result.get(i)).get("documentID");
					Document document = new Document(documentID,this.database);
		  			documents.add(document);
	      		}
        	}
		} catch (ResourceNotFoundException rnfe){
			System.out.println(rnfe.getMessage());
			Utility.log(Utility.ERROR_FILE_LOG,rnfe);
		}
		return documents;
    }
	
	public Vector getMembers(){
		Vector users = new Vector();
		User user ;
		String userID;
		this.result  = this.database.query("SELECT user.userID FROM communityinterests, user WHERE ((user.userID =communityinterests.userID) AND (communityinterests.communityID='"+this.ID+"') AND (communityinterests.status ='"+Community.MEMBER_ACTIVED+"')) ORDER BY name");
		try {
	    	for (int i=0; i < this.result.size(); i++){
          		userID = (String)((Hashtable)this.result.get(i)).get("userID");
				user = AbstractUser.getRealUser(userID,this.database);
		  		users.add(user);
	      	}
			user = this.getResponsible();
			users.add(user);
		} catch (UserNotFoundException unfe){
			unfe.printStackTrace();
		}
		return users;
	}
	
	/** Checks whether an specified user has full privilegues */
	public boolean userhasFullPrivilegues(User user){
		boolean hasMaster = false;
		if (user.isGuest(this)){
			hasMaster = true;
		}else if (user.isResponsible(this)){
			hasMaster = true;
		}else if (this instanceof Discipline){
			if (user instanceof Professor){
				if (((Professor)user).isTeacher((Discipline)this) ){
					hasMaster = true;
				}
			}else{
				if (user instanceof Student){
					if (((Student )user).isMonitor((Discipline)this)){
						hasMaster = true;
					}
				}
			}
		}else if (this instanceof Group){
			/* Permissão total para qualquer membro do grupo */
			if (user.isMember(this) ){
				hasMaster = true;
			}
		}
		return hasMaster;
	}
	
	
	/**
	@return Todos os usuarios que estao esperando por uma autorizacao para se efetivar em uma comunidade.
	 */
	public Vector getWaitingMembers(){
		Vector users = new Vector();
		User user ;
		String userID;
		
		this.result  = this.database.query("SELECT userID FROM communityinterests WHERE communityID='"+this.ID+"' AND status='"+Community.MEMBER_WAITING+"'");
		try {
	    	for (int i=0; i < this.result.size(); i++){
          		userID = (String)((Hashtable)this.result.get(i)).get("userID");
				user = AbstractUser.getRealUser(userID,this.database);
		  		users.add(user);
	      	}
		} catch (UserNotFoundException unfe){
			unfe.printStackTrace();
		}
		return users;
	}
}
