package br.com.aulaweb.persistence.antigo;

import org.odmg.*;

/**
 * Insert the type's description here.
 * Creation date: (04.03.2001 10:34:15)
 * @author: Administrator
 */
public class UCEditProduct extends AbstractUseCase
{
    /**
     * UCEnterNewProduct constructor comment.
     */
    public UCEditProduct(Implementation impl){
        super(impl);
    }

    /** perform this use case*/
    public void apply(){
        String in = readLineWithMessage("Edit Product with id:");
        int id = Integer.parseInt(in);

        // We don't have a reference to the selected Product.
        // So first we have to lookup the object.

        // 1. build oql query to select product by id:
        String oqlQuery = "select del from " + Product.class.getName() + " where id = " + id;

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
            Product toBeEdited = (Product) result.get(0);

            // 4. lock the product for write access
            tx.lock(toBeEdited, tx.WRITE);

            // 5. Edit the product entry
            System.out.println("please edit existing product");
            in = readLineWithMessage("enter name (was " + toBeEdited.getName() + "):");
            toBeEdited.setName(in);
            in = readLineWithMessage("enter price (was " + toBeEdited.getPrice() + "):");
            toBeEdited.setPrice(Double.parseDouble(in));
            in = readLineWithMessage("enter available stock (was " + toBeEdited.getStock() + "):");
            toBeEdited.setStock(Integer.parseInt(in));

            // 6. commit transaction
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
    public String getDescription()
    {
        return "Edit a product entry";
    }
}
