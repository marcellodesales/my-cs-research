package br.com.aulaweb.persistence.antigo;

import org.odmg.*;

import br.com.aulaweb.resource.RichText;

/**
 * Insert the type's description here.
 * Creation date: (04.03.2001 10:34:15)
 * @author: Administrator
 */
public class UCDeleteProduct extends AbstractUseCase
{
    /**
     * UCEnterNewProduct constructor comment.
     */
    public UCDeleteProduct(Implementation impl){
        super(impl);
    }

    /** perform this use case*/
    public void apply(){
        String in = readLineWithMessage("Delete RichText with id:");
        int id = Integer.parseInt(in);

        // We don't have a reference to the selected Product.
        // So first we have to lookup the object.

        // 1. build oql query to select product by id:
        String oqlQuery = "select del from " + RichText.class.getName() + " where id = " + id;

        Database db = odmg.getDatabase(null); // the current DB
        Transaction tx = null;
        try
        {
            // 2. start transaction
            tx = odmg.newTransaction();
            tx.begin();

            // 3. lookup the product specified by query
            OQLQuery query = odmg.newOQLQuery();
            query.create(oqlQuery);
            DList result = (DList) query.execute();
            RichText toBeDeleted = (RichText) result.get(0);

            // 4. now mark object for deletion
            db.deletePersistent(toBeDeleted);
            // 5. commit transaction
            tx.commit();
        }
        catch (Throwable t)
        {
            // rollback in case of errors
            tx.abort();
            t.printStackTrace();
        }
    }

    /** get descriptive information on use case*/
    public String getDescription(){
        return "Delete a richtext entry";
    }
}
