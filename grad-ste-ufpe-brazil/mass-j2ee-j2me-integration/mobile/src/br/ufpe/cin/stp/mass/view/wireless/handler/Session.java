/*
 * @created 30/07/2004 20:47:23
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.handler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 30/07/2004 20:47:23
 */
public class Session {
    
    private byte type;
    private boolean opened;
    private int index;    
    private String title;
    private String date;
    private String id;  
    private Vector questions;
    
    /**
     * Session is the handler of the session of the J2EE server
     * @created 06/08/2004 13:42:57
     * @param id is the identification
     * @param title is the title of the session
     * @param date is the date in a milliseconds format.
     * @param type defines if it's a Survey or a Questionary
     * @param opened defines if the session is opened.
     */
    public Session(String id, String title, String date, String type, String opened){
        this.id = id;
        this.title = title;
        this.date = date;
        this.type = Byte.parseByte(type);
        this.opened = (opened.equals("true"));
        this.questions = new Vector();
    }    
    
    /**
     * @return Returns the questions.
     * @created 30/07/2004 20:53:29
     */
    public Question[] getQuestions() {
        Question[] questions = new Question[this.questions.size()];
        int questionsNumber = this.questions.size();
        for (int i = 0; i < questionsNumber; i++) {
            questions[i] = (Question)this.questions.elementAt(i);
        }
        return questions;
    }
    
    /**
     * @created 06/08/2004 13:44:36
     * @param question is the question of the session.
     */
    public void addQuestion(Question question){
        this.questions.addElement(question);
    }
    
    /**
     * @return Returns the date.
     * @created 30/07/2004 20:51:05
     */
    public String getDate() {
        return this.date;
    }
    /**
     * @return Returns the id.
     * @created 30/07/2004 20:51:05
     */
    public String getId() {
        return this.id;
    }
    /**
     * @return Returns the title.
     * @created 30/07/2004 20:51:05
     */
    public String getTitle() {
        return this.title;
    }
    /**
     * @created 05/04/2004 08:54:17
     * @return Returns the opened.
     */
    public boolean isOpened() {
        return this.opened;
    }
    /**
     * @created 05/04/2004 08:54:17
     * @return Returns the type.
     */
    public byte getType() {
        return this.type;
    }
    
    /**
     * @created 06/08/2004 13:42:45
     * Closes a session.
     */
    public void closeSession(){
        this.opened = false;
    }
    
    /**
     * @created 05/04/2004 08:55:44
     * @return the description of the session
     */
    public String getTypeString(){
        return (this.type == 0) ? "Survey" : "Questionary";
    }
    
	/**
	 * @return a byte array with the Record data
	 */
	public byte[] getRecordStream() throws IOException{
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(stream);
        
        data.writeByte(this.type);
        data.writeBoolean(this.opened);
        data.writeUTF(this.id);
        data.writeUTF(this.date);
        data.writeUTF(this.title);
        return stream.toByteArray();
	}
	
	/**
	 * @param rec is the persistent record in streams
	 * @return the session that was saved on the persistence record.
	 * @throws IOException
	 */
	public static Session createSession(byte[] rec) throws IOException {
		ByteArrayInputStream stream = new ByteArrayInputStream(rec);
		DataInputStream data = new DataInputStream(stream);
		byte type = data.readByte();
		boolean opened = data.readBoolean();
		String id = data.readUTF();
		String date = data.readUTF();
		String title = data.readUTF();
		return new Session(id, title, date, String.valueOf(type),String.valueOf(opened));
	}    
	
	/**
	 * @return Returns the index of the session.
	 */
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * @param index is the identification of the persistent value.
	 */
	public void setIndex(int index) {
		this.index = index;
	}	
}
