package br.ufal.graw;

import java.sql.SQLException;
import java.util.Vector;
import java.util.Hashtable;

import br.ufal.graw.exception.CommunityException;
import br.ufal.graw.exception.UserNotFoundException;
import br.ufal.graw.exception.ResourceNotFoundException;

public interface Community {
	
	/* Tipos de Comunidades*/
	public static final String NONE = "N";
	public static final String DISCIPLINE = "D";
	public static final String EXTRA_COURSE = "E";
	public static final String GROUP = "G";
	
	public static final int FORUM_TOOL     = 1;
	public static final int CHAT_TOOL      = 2;
	public static final int LINKS_TOOL     = 4;
	public static final int DOCUMENTS_TOOL = 8;
	public static final int SCHEDULER_TOOL = 16;
	
	public static final int ALL_TOOLS = FORUM_TOOL | CHAT_TOOL | LINKS_TOOL | DOCUMENTS_TOOL | SCHEDULER_TOOL;

	/*Status dos membros da comunidade */
	public static final String MEMBER_ACTIVED = "A";
	public static final String MEMBER_WAITING = "W";

	/* Status da comunidade */
	public static final String ACTIVED = "A";
	public static final String WAITING = "W";
	
	public String getID();
	
	public String getTitle();
	
	public String getDescription();
	
	public int getVisibility();
	
	public int getAssociationType();
		
	public String getKind();
	
	public User getResponsible() throws UserNotFoundException;
		
	public String getCategoryID();
	
	public String getCategoryDescription() throws ResourceNotFoundException;
	
	public String getSubcategoryID();
	
	public String getSubcategoryDescription() throws ResourceNotFoundException;
	
	public boolean isActive();
	
	public void setTitle(String newTitle);
	
	public void setDescription(String newDescription);
	
	//public void publishNewDocument(cccccc);
	//public void publishNewLink(String title, String description, String URL.......);
	//public void publishNewMessage(String title, String description......);
	
	//public Vector getMessages();
	
	/** Gets the Links of the Course. */
    public Vector getLinks();
	
	public ResourceCategory[] getDocumentCategories();
	public ResourceCategory[] getLinkCategories();
	public ForumCategory[] getForumCategories();
	
	public Vector getLinks(int offset, int limit);
	
	/** Gets the Documents of the Course. */
    public Vector getDocuments();
	
	public Vector getDocuments(int offset, int limit);
	
	public int getQuantMessages();
	
	public int getQuantDocuments();
	
	public int getQuantLinks();
	
	public int getQuantUsers();
	
	public Vector getMembers();
	
	public Vector getWaitingMembers();
	
	public boolean userhasFullPrivilegues(User user);
}
