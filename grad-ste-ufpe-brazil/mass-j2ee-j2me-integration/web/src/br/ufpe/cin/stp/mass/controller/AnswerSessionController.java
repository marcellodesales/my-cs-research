package br.ufpe.cin.stp.mass.controller;

import br.ufpe.cin.stp.mass.model.SessionFactory;
import br.ufpe.cin.stp.mass.model.session.Answer;
import br.ufpe.cin.stp.mass.model.session.QuestionItem;
import br.ufpe.cin.stp.mass.model.session.Session;
import br.ufpe.cin.stp.mass.model.session.SessionClosedException;
import br.ufpe.cin.stp.mass.persistence.PersistenceBrokerFactory;
import br.ufpe.cin.stp.mass.persistence.PersistenceLayer;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;

/**
 * Controller to process answers from users.
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 31/07/2004 17:42:51
 */
public class AnswerSessionController {
    
    private PersistenceLayer pl = PersistenceBrokerFactory.getInstance().getPersistenceLayer();

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static AnswerSessionController singleton;

    private AnswerSessionController() {
    }

    /**
     * @created 31/07/2004 17:42:57
     * @return The single instance.
     */
    public synchronized static AnswerSessionController getInstance() {
        if (singleton == null) {
            singleton = new AnswerSessionController();
        }
        return singleton;
    }
    
    /**
     * Registers an Answer from a user.
     * @created 01/08/2004 11:50:27
     * @param sessionID is the identification of the session.
     * @param itemsID is the set of chosen items of the user.
     * @throws SessionClosedException if the session is already closed.
     * @throws PersistentObjectNotFoundException if any of the identifiers were not valid and no object associated with them was found.
     */
    public void answerSession(String sessionID, String senderID, String[] itemsID) throws SessionClosedException, PersistentObjectNotFoundException {
        Session session = (Session)pl.find(sessionID,Session.class);
        if (!session.isOpened()){
            throw new SessionClosedException("The session "+session.getTitle()+" is already closed and therefore answers are not allowed.",session);
        }
        Answer a = SessionFactory.getInstance().createNewAnswer(sessionID,senderID);
        String[] validItemsID = new String[itemsID.length]; 
        for (int i = 0; i < itemsID.length; i++) {
            QuestionItem qi = (QuestionItem)pl.find(itemsID[i],QuestionItem.class);
            validItemsID[i] = itemsID[i];
        }
        a.addAnswer(validItemsID);
        session.addAnswer(a);
    }
}
