/*
 * @created 30/07/2004 20:52:02
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

/**
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 * @created 30/07/2004 20:52:02
 */
public class QuestionItem {
    
    private String id;
    private String description;
    private int index;

    public QuestionItem(String id, String description){
        this.id = id;
        this.description = description;
    }
    /**
     * @return Returns the description.
     * @created 30/07/2004 20:52:46
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * @return Returns the id.
     * @created 30/07/2004 20:52:46
     */
    public String getId() {
        return this.id;
    }
    
    /* @created 03/08/2004 08:46:39
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return this.description;
    }
    
	/**
	 * @return a byte array with the Record data
	 */
	public byte[] getRecordStream() throws IOException{
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(stream);
        
        data.writeUTF(this.id);
        data.writeUTF(this.description);
        return stream.toByteArray();
	}
	
	/**
	 * @param rec is the persistent record in streams
	 * @return the session that was saved on the persistence record.
	 * @throws IOException
	 */
	public static QuestionItem createRecord(byte[] rec) throws IOException {
		ByteArrayInputStream stream = new ByteArrayInputStream(rec);
		DataInputStream data = new DataInputStream(stream);
		return new QuestionItem(data.readUTF(),data.readUTF());
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
