package br.com.aulaweb.persistence;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;

import br.com.aulaweb.resource.Image;
import br.com.aulaweb.resource.RichText;
import br.com.aulaweb.resource.problem.objectiveproblem.ObjectiveProblemManager;
/**
 * Insert the type's description here.
 * Creation date: (04.03.2001 10:34:15)
 * @author: Administrator
 */
public class UCEnterNewProduct extends AbstractUseCase
{
    /**
     * UCEnterNewProduct constructor comment.
     */
    public UCEnterNewProduct(PersistenceBroker broker)
    {
        super(broker);
    }

    /** perform this use case*/
    public void apply()
    {
        try{
		RichText newRichText = new RichText();
		newRichText.setText("Enunciado do Probelma");
			Image im = new Image(newRichText);
			im.setTitle("imagem1");
			im.setDescription("Engenharia do Dominio");
			im.setHeight(100);
			im.setWidth(100);

			String fileName = "badday.txt";
			byte[] bytes = "Apenas um teste.".getBytes();
			ObjectiveProblemManager.getInstance().insertImage(im, fileName, bytes);
			
            // 1. open transaction
            broker.beginTransaction();

            // 2. make the new object persistent
            broker.store(newRichText);
            broker.commitTransaction();
        }catch (PersistenceBrokerException ex){
            // if something went wrong: rollback
            broker.abortTransaction();
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /** get descriptive information on use case*/
    public String getDescription()
    {
        return "Enter a new product";
    }
}
