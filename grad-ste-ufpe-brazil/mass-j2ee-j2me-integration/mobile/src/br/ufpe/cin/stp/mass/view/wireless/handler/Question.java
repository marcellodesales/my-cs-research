/*
 * @created 30/07/2004 20:48:26
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
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
 * @created 30/07/2004 20:48:26
 */
public class Question {

	/**
	 * <code>MULTICHOICE</code> defines a multi choice question.
	 */
	public static final byte MULTICHOICE = 0;
	/**
	 * <code>SINGLECHOICE</code> defines a single choice question.
	 */
	public static final byte SINGLECHOICE = 1;
	
	private byte type;
	private int index;
    private String id;
    private String title;
    private Vector items;
    
    public Question(String id, String title, String type){
        this.id = id;
        this.title = title;
        this.type = Byte.parseByte(type);
        this.items = new Vector();
    }    
    
    /**
     * @return Returns the items.
     * @created 30/07/2004 20:53:57
     */
    public QuestionItem[] getItems() {
        QuestionItem[] items = new QuestionItem[this.items.size()];
        int itemsNumber = this.items.size();
        for (int i = 0; i < itemsNumber; i++) {
            items[i] = (QuestionItem)this.items.elementAt(i);
        }
        return items;
    }
    
    public void addItem(QuestionItem item){
        this.items.addElement(item);
    }
    
    /**
     * @return Returns the id.
     * @created 30/07/2004 20:50:51
     */
    public String getId() {
        return this.id;
    }
    /**
     * @return Returns the title.
     * @created 30/07/2004 20:50:51
     */
    public String getTitle() {
        return this.title;
    }
    /**
     * @return Returns the type.
     * @created 30/07/2004 20:50:51
     */
    public byte getType() {
        return this.type;
    }
    
    /**
     * @created 08/08/2004 21:13:37
     * @param itemID
     * @return the description of the question item for a given ID.
     */
    public String getQuestionItemString(String itemID){
        int itemsNumber = this.items.size();
        QuestionItem item;
        for (int i = 0; i < itemsNumber; i++) {
            item = (QuestionItem)this.items.elementAt(i);
            if (item.getId().equals(itemID)){
                return item.getDescription();
            }
            
        }
        return "";
    }
    
	/**
	 * @return a byte array with the Record data
	 */
	public byte[] getRecordStream() throws IOException{
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(stream);
        
        data.writeByte(this.type);
        data.writeUTF(this.id);
        data.writeUTF(this.title);
        return stream.toByteArray();
	}
	
	/**
	 * @param rec is the persistent record in streams
	 * @return the session that was saved on the persistence record.
	 * @throws IOException
	 */
	public static Question createRecord(byte[] rec) throws IOException {
		ByteArrayInputStream stream = new ByteArrayInputStream(rec);
		DataInputStream data = new DataInputStream(stream);
		byte type = data.readByte();
		return new Question(data.readUTF(),data.readUTF(),String.valueOf(type));
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
