package br.com.aulaweb.persistence.antigo;

import org.odmg.DList;
import org.odmg.Implementation;
import org.odmg.OQLQuery;
import org.odmg.Transaction;

import br.com.aulaweb.resource.Image;
import br.com.aulaweb.resource.RichText;

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
    public UCListAllProducts(Implementation impl)
    {
        super(impl);
    }

    /** perform this use case*/
    public void apply()
    {
        System.out.println("The list of available RichText:");
        try{
            // 1. open a transaction
            Transaction tx = odmg.newTransaction();
            tx.begin();

            // 2. get an OQLQuery object from the ODMG facade
            OQLQuery query = odmg.newOQLQuery();

            // 3. set the OQL select statement
            query.create("select allrichtexts from " + RichText.class.getName());

            // 4. perform the query and store the result in a persistent Collection
            DList allProducts = (DList) query.execute();
            tx.commit();

            // 5. now iterate over the result to print each product
            java.util.Iterator iter = allProducts.iterator();
            while (iter.hasNext()){
				RichText element = (RichText)iter.next();
                System.out.println((element).getText());
//				java.util.Iterator iter1 = element.getImages().iterator();
//				while (iter1.hasNext()){
//					Image image = (Image)iter.next();
//					System.out.println(image.getTitle());
//				}
            }
        }catch (Throwable t){
			System.out.println(t.getMessage());
        }
    }

    /** get descriptive information on use case*/
    public String getDescription(){
        return "List all RichText entries";
    }
}
