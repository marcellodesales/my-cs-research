/*
 * @created 01/08/2004 12:42:50
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.mass.persistence.memory;

import br.ufpe.cin.stp.mass.model.MassFacade;
import br.ufpe.cin.stp.mass.model.SessionFactory;
import br.ufpe.cin.stp.mass.model.session.AbstractSession;
import br.ufpe.cin.stp.mass.model.session.Answer;
import br.ufpe.cin.stp.mass.model.session.Question;
import br.ufpe.cin.stp.mass.model.session.QuestionItem;
import br.ufpe.cin.stp.mass.model.session.Session;
import br.ufpe.cin.stp.mass.persistence.memory.MemoryRepository;
import junit.framework.TestCase;

/**
 * @created 01/08/2004 12:42:50
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class TestMemoryRepository extends TestCase {

    private MemoryRepository mr;
    private SessionFactory sf;
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        this.mr = MemoryRepository.getInstance();
        this.sf = SessionFactory.getInstance();
    }
    
    public void testInsertSelect(){
        String sessionTitle = "title of session";
        String questionTitle = "Single Question";
        String questItemTitle = "item of question";
        String senderID = "Sender";
        
        Session s = this.sf.createNewSurvey(sessionTitle);
        Question q = this.sf.createNewSingleChoiceQuestion(questionTitle);
        QuestionItem i = this.sf.createNewQuestionItem(questItemTitle);
        Answer a = this.sf.createNewAnswer(s.getID(),senderID);
        
        try {
            Session ss = (Session)this.mr.select(s.getID(),Session.class);
            QuestionItem ii = (QuestionItem)this.mr.select(i.getID(),QuestionItem.class);
            Question qq = (Question)this.mr.select(q.getID(),Question.class);
            Answer aa = (Answer)this.mr.select(a.getID(),Answer.class);
            
            assertNotNull(ss);
            assertNotNull(ii);
            assertNotNull(qq);
            assertNotNull(aa);
            
            assertEquals(s.getTitle(),sessionTitle);
            assertEquals(q.getTitle(),qq.getTitle());
            assertEquals(i,ii);
            assertEquals(a.getSenderID(),senderID);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
/*    public void testSelection() throws MemoryRepositoryException{
        Session session = MassFacade.getInstance().createDefaultSurvey();
        Session session2 = (Session)this.mr.select("opened",new Boolean(true),AbstractSession.class);
        
        Session session3 = MassFacade.getInstance().createDefaultSurvey();
        Session session4 = (Session)MemoryRepository.getInstance().select("title",session.getTitle(),AbstractSession.class);
         
    }
*/
}
