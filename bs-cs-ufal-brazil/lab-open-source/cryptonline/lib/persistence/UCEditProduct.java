package br.com.aulaweb.persistence;

import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByExample;

/**
 * Insert the type's description here.
 * Creation date: (04.03.2001 10:34:15)
 * @author: Administrator
 */
public class UCEditProduct extends AbstractUseCase
{
    /**
     * UCEditProduct constructor comment.
     */
    public UCEditProduct(org.apache.ojb.broker.PersistenceBroker b)
    {
        super(b);
    }

    /** perform this use case*/
    public void apply()
    {
        String in = readLineWithMessage("Edit Product with id:");
        int id = Integer.parseInt(in);

        // We don't have a reference to the selected Product.
        // So first we have to lookup the object,
        // we do this by a query by example (QBE):
        // 1. build an example object with matching primary key values:
        Product example = new Product();
        example.setId(id);

        // 2. build a QueryByExample from this sample instance:
        Query query = new QueryByExample(example);
        try
        {
            // 3. start broker transaction
            broker.beginTransaction();

            // 4. lookup the product specified by the QBE
            Product toBeEdited = (Product) broker.getObjectByQuery(query);

            // 5. edit the existing entry
            System.out.println("please edit the product entry");
            in = readLineWithMessage("enter name (was " + toBeEdited.getName() + "):");
            toBeEdited.setName(in);
            in = readLineWithMessage("enter price (was " + toBeEdited.getPrice() + "):");
            toBeEdited.setPrice(Double.parseDouble(in));
            in = readLineWithMessage("enter available stock (was " + toBeEdited.getStock() + "):");
            toBeEdited.setStock(Integer.parseInt(in));



            // 6. now ask broker to store the edited object
            broker.store(toBeEdited);
            // 7. commit transaction
            broker.commitTransaction();
        }
        catch (Throwable t)
        {
            // rollback in case of errors
            broker.abortTransaction();
            t.printStackTrace();
        }
    }

    /** get descriptive information on use case*/
    public String getDescription()
    {
        return "Edit a product entry";
    }
}
