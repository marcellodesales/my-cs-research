/*
 * @created 06/08/2004 13:55:27
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Motorola Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
package br.ufpe.cin.stp.mass.view.wireless.persistence;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

import br.ufpe.cin.stp.mass.view.wireless.handler.Session;

/**
 * PersistenceLayer is the proxy representation of the persistence layer.
 * @created 06/08/2004 13:55:27
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class PersistenceLayer {
    
    /**
     * Saves an object to the given record store.
     * @created 06/08/2004 14:22:53
     * @param objStream is the stream of an object
     * @param recordStore is the record store used.
     * @return the offset of the saved stream.
     * @throws RecordStoreException if an error occur
     */
    public static int saveObject(byte[] objStream, RecordStore recordStore) throws RecordStoreException{
       return recordStore.addRecord(objStream,0,objStream.length); 
    }
    
    /**
     * Deletes an object from the given record store.
     * @created 06/08/2004 14:48:51
     * @param index the offset of the persistent record to be deleted.
     * @param recordStore the record store.
     * @throws RecordStoreException if an error occur after trying to delete the object identified by the index.
     */
    public static void deleteObject(int index, RecordStore recordStore) throws RecordStoreException{
        recordStore.deleteRecord(index);
    }

    /**
     * Gets the stream of an identified object from the record store.
     * @created 06/08/2004 14:48:53
     * @param index is the offset 
     * @param recordStore is the record store.
     * @return the stream of the object to be gotten
     * @throws RecordStoreException if the object is not found to a given index or the index is incorrect
     */
    public static byte[] getObject(int index, RecordStore recordStore) throws RecordStoreException{
        return recordStore.getRecord(index);
    }
    
    /**
     * @created 06/08/2004 15:14:34
     * @return the default record store for the midlet where it was called.
     * @throws RecordStoreException
     */
    public static RecordStore getDefaulRecordStore(String object) throws RecordStoreException{
        return RecordStore.openRecordStore(object,true);
    }
    
    public static Session[] getSavedSessions(RecordStore store){
        Vector sessionIds = new Vector();
        RecordEnumeration records;
        
        try {
            records = store.enumerateRecords(null,null,false);
            while(records.hasNextElement()) {
                int index = records.nextRecordId();
                sessionIds.addElement(new Integer(index));
            }
            final int sessionsSize = sessionIds.size();
            Session[] sessions = new Session[sessionsSize];
            for (int i = 0; i < sessionsSize; i++) {
                sessions[i] = Session.createSession(store.getRecord(((Integer)sessionIds.elementAt(i)).intValue()));
            }
            return sessions;
        } catch (RecordStoreNotOpenException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidRecordIDException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RecordStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
