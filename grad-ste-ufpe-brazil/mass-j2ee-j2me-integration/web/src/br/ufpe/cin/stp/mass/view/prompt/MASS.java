/*
 * @created 25/07/2004 08:55:06
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.mass.view.prompt;

/**
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 25/07/2004 08:55:06
 */
public class MASS {

    public static void main(String[] args) {
        
        Session session = MassFacade.getInstance().createDefaultSurvey();
        
        Iterator items = ((Question)session.getQuestions().next()).getQuestionItems(); 
        
        while(items.hasNext()){
            QuestionItem item = (QuestionItem)items.next();
            
        }
        
        Answer ans = new Answer(session.getID(),"Marcello");
        String[] items = {item1.getID()};
        ans.addAnswer(items);
        
        Answer ans2 = new Answer(session.getID(),"Leandro");
        String[] items2 = {item2.getID()};
        ans2.addAnswer(items2);
        
        Answer ans3 = new Answer(session.getID(),"Thiago");
        String[] items3 = {item4.getID()};
        ans3.addAnswer(items3);
        
        Answer ans4 = new Answer(session.getID(),"Mainha");
        String[] items4 = {item1.getID()};
        ans4.addAnswer(items4);
        
        Answer ans5 = new Answer(session.getID(),"Painho");
        String[] items5 = {item1.getID()};
        ans5.addAnswer(items5);  
        
        session.addAnswer(ans);
        session.addAnswer(ans2);
        session.addAnswer(ans3);
        session.addAnswer(ans4);
        session.addAnswer(ans5);
        
        System.out.println(session.getTotal());
        System.out.println(session.getPorcentage(item1));
        System.out.println(session.getPorcentage(item2));
        System.out.println(session.getPorcentage(item3));
        System.out.println(session.getPorcentage(item4));
        
        System.out.println(SessionToXML.getInstance().createNewProduct(session).toString());
        
    }
}
