/*
 * @created 25/07/2004 09:23:32
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.persistence.memory;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import br.ufpe.cin.stp.mass.model.MassFacade;
import br.ufpe.cin.stp.mass.model.session.AbstractSession;
import br.ufpe.cin.stp.mass.model.session.Answer;
import br.ufpe.cin.stp.mass.model.session.Question;
import br.ufpe.cin.stp.mass.model.session.QuestionItem;
import br.ufpe.cin.stp.mass.model.session.Session;
import br.ufpe.cin.stp.mass.persistence.Persistent;
import br.ufpe.cin.stp.mass.persistence.PersistentObjectNotFoundException;

/**
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 25/07/2004 09:23:32
 */
public class MemoryRepository {
    
    /**
     * <code>questions</code> is the respository for questions.
     */
    private List questionsTable;
    /**
     * <code>questionItems</code> is the repository for question items.
     */
    private List questionItemsTable;
    /**
     * <code>sessions</code> is the repository for sessions.
     */
    private List sessionsTable;
    /**
     * <code>answers</code> is the repository for answers.
     */
    private List answersTable;
    
    /**
     * <code>memoryTables</code> is the tables of a given type.
     * 
     */
    private Hashtable memoryTables;
    //TODO: Create a memory table for the vectors.[class:String,table:vector]
    //TODO: and helping methods like register type...
    
    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static MemoryRepository singleton;

    private MemoryRepository() {
        this.questionsTable = new Vector();
        this.questionItemsTable = new Vector();
        this.sessionsTable = new Vector();
        this.answersTable = new Vector();
        this.memoryTables = new Hashtable();
    }
    
    /**
     * @created 02/07/2004 01:11:51
     * @return a gambiarra to get the current session, because the find(att,value,type) is not working!!
     */
    public Session getDefaultSession(){
        // TODO clear this method in the future.
        return (Session)this.sessionsTable.get(0);
    }

    /**
     * @created 25/07/2004 09:32:20
     * @return The singleton instance of this class.
     */
    public synchronized static MemoryRepository getInstance() {
        if (singleton == null) {
            singleton = new MemoryRepository();
        }
        return singleton;
    }
    
    /**
     * Inserts an object on the memory repository.
     * @param object
     * @created 31/07/2004 18:53:04
     */
    public void insert(Object object){
        if (object instanceof Session)
            this.sessionsTable.add(object);
        else
        if (object instanceof Question)
            this.questionsTable.add(object);
        else 
        if (object instanceof QuestionItem)
            this.questionItemsTable.add(object);
        else 
        if (object instanceof Answer)
            this.answersTable.add(object);
    }
    
    private List getMemoryTable(String type) throws MemoryRepositoryException{
        if (type.indexOf("Session")!=-1 || type.indexOf("Survey")!=-1 || type.indexOf("Questionary")!=-1){
            return this.sessionsTable;
        }else
        if (type.indexOf("QuestionItem") != -1){
            return this.questionItemsTable;  
        }else
        if (type.indexOf("Question") != -1){
            return this.questionsTable;
        }else
        if (type.indexOf("Answer") != -1){
            return this.answersTable;
        } else throw new MemoryRepositoryException("Type "+type+" not registered in memory.");                     
    }
    
    /**
     * @created 02/07/2004 00:38:57
     * @param id
     * @param klass
     * @return the object of the repository of the given type with the identification.
     * @throws MemoryRepositoryException
     */
    public Object select(String id, Class klass) throws MemoryRepositoryException{
        Object object = null;
        String type = klass.getName();
        
        Iterator i = this.getMemoryTable(type).iterator();
        while (i.hasNext()) {
            Persistent element = (Persistent) i.next();
            if (element.getID().equals(id)){
                object = element;
                break;
            }
        }
        if (object == null) throw new MemoryRepositoryException("Persistent Object of Type "+type+" not found with ID='"+id+"'"); 
        return object;
    }
    
    /**
     * @created 15/08/2004 11:12:30
     * @param klass
     * @return the instances of the given klass
     * @throws MemoryRepositoryException if no instance was found.
     */
    public Object[] select(Class klass) throws MemoryRepositoryException{
        String type = klass.getName();
       
        
        List table = this.getMemoryTable(type);
        int size = table.size();
        Object[] instances = new Object[size];
        for (int j = 0; j < size; j++) 
            instances[j] = table.get(j);
        
        if (instances.length == 0) throw new MemoryRepositoryException("There are no instances of Persistent Objects of Type "+type); 
        return instances;
    }    
    
    /**
     * @created 02/07/2004 00:49:04
     * @param attribute
     * @param value
     * @param klass
     * @return an instance of the given class specified by the value of an attribute.
     * @throws MemoryRepositoryException if no instance was found.
     */
    public Object select(String attribute, Object value, Class klass) throws MemoryRepositoryException{
        Object object = null;
        String type = klass.getName();
        Field usedField = null;
        
        try {
    	    final Field fields[] = klass.getDeclaredFields();
    	    for (int i = 0; i < fields.length; ++i) {
    		      if (attribute.equals(fields[i].getName())) {
    		          usedField = fields[i];
    		          usedField.setAccessible(true);
    		          break;
    		      }
    	    }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Iterator i = this.getMemoryTable(type).iterator();
        while (i.hasNext()) {
            Object element = i.next();
            try {
                Object a = usedField.get(element);
                
                if (usedField.get(element).equals(value)){
                    object = element;
                    break;
                }
            } catch (IllegalArgumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        if (object == null) throw new MemoryRepositoryException("Persistent Object of Type "+type+" with "+attribute+"="+value+" was not found."); 
        return object;
    }    
    
    /**
     * Register a class on the memory repository. Needs finishing!
     * @created 01/07/2004 17:31:37
     * @param type
     */
    public void registerType(String type){
        this.registerTypeOnTable(type);
    }
    

    /**
     * Register a class on the memory repository. Needs finishing!
     * @created 01/07/2004 17:31:39
     * @param type
     * @return
     */
    private Vector registerTypeOnTable(String type){
        Vector typeTable = (Vector)this.memoryTables.get(type);
        if (typeTable == null){
            typeTable = new Vector();
            this.memoryTables.put(type,typeTable);
        }
        return typeTable;
    }
    
    /**
     * Register a class on the memory repository. Needs finishing!
     * @created 01/07/2004 17:31:40
     * @param type
     * @param typeIf
     */
    public void registerType(String type, String typeIf){
        Vector typeTable = this.registerTypeOnTable(type);
        this.memoryTables.put(typeIf,typeTable);
    }
    
    public static void main(String[] args) throws MemoryRepositoryException, PersistentObjectNotFoundException {
        Session session = MassFacade.getInstance().getCurrentOpenedSurvey();
        Session session2 = (Session)MemoryRepository.getInstance().select("title",session.getTitle(),AbstractSession.class);
        
        System.out.println(session == session2);
    }
}
