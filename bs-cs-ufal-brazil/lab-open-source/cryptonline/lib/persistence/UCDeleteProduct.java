package br.com.aulaweb.persistence;

import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByExample;

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
    public UCDeleteProduct(org.apache.ojb.broker.PersistenceBroker b)
    {
        super(b);
    }

    /** perform this use case*/
    public void apply()
    {
        String in = readLineWithMessage("Delete Product with id:");
        int id = Integer.parseInt(in);

        // We don't have a reference to the selected Product.
        // So first we have to lookup the object,
        // we do this by a query by example (QBE):
        // 1. build an example object with matching primary key values:
        RichText example = new RichText();
        example.setId(id);
        // 2. build a QueryByExample from this sample instance:
        Query query = new QueryByExample(example);
        try{
            // start broker transaction
            broker.beginTransaction();
            // lookup the product specified by the QBE
            RichText toBeDeleted = (RichText) broker.getObjectByQuery(query);
            // now ask broker to delete the object
            broker.delete(toBeDeleted);
            // commit transaction
            broker.commitTransaction();
        }
        catch (Throwable t){
            // rollback in case of errorsq
            broker.abortTransaction();
            t.printStackTrace();
        }
    }

    /** get descriptive information on use case*/
    public String getDescription()
    {
        return "Delete a product entry";
    }
}
