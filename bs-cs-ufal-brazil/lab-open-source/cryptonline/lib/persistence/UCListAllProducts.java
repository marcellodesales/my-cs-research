package br.com.aulaweb.persistence;

import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;

import br.com.aulaweb.resource.RichText;

import java.util.Collection;

/**
 * Insert the type's description here.
 * Creation date: (04.03.2001 10:34:15)
 * @author: Administrator
 */
public class UCListAllProducts extends AbstractUseCase
{
    /**
     * UCEnterNewProduct constructor comment.
     */
    public UCListAllProducts(org.apache.ojb.broker.PersistenceBroker b)
    {
        super(b);
    }

    /** perform this use case*/
    public void apply()
    {
        System.out.println("The list of available products:");
        // build a query that select all objects of Class Product, without any further criteria
        // according to ODMG the Collection containing all instances of a persistent class is called "Extent"
        Query query = new QueryByCriteria(RichText.class, null);
        try
        {
            // ask the broker to retrieve the Extent collection
            Collection allProducts = broker.getCollectionByQuery(query);
            // now iterate over the result to print each product
            java.util.Iterator iter = allProducts.iterator();
            while (iter.hasNext()){
				RichText element = (RichText)iter.next();
                System.out.println(element.getText());
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }

    /** get descriptive information on use case*/
    public String getDescription()
    {
        return "List all product entries";
    }
}
