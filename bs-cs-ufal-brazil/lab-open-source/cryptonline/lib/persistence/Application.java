package br.com.aulaweb.persistence;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.util.ui.AsciiSplash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
/**
 * The tutorial application.
 * @author: thma
 */
public class Application
{
    private Vector useCases;
    private PersistenceBroker broker;
    /**
     * Application constructor comment.
     */
    public Application()
    {
        broker = null;
        try
        {
            broker = PersistenceBrokerFactory.defaultPersistenceBroker();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
        useCases = new Vector();
        useCases.add(new UCListAllProducts(broker));
        useCases.add(new UCEnterNewProduct(broker));
        useCases.add(new UCEditProduct(broker));
        useCases.add(new UCDeleteProduct(broker));
        useCases.add(new UCQuitApplication(broker));
    }
    /**
     * Disply available use cases.
     */
    public void displayUseCases()
    {
        System.out.println();
        for (int i = 0; i < useCases.size(); i++)
        {
            System.out.println("[" + i + "] " + ((UseCase) useCases.get(i)).getDescription());
        }
    }
    /**
     * Insert the method's description here.
     * Creation date: (04.03.2001 10:40:25)
     * @param args java.lang.String[]
     */
    public static void main(String[] args)
    {
        Application app = new Application();
        app.run();
    }
    /**
     * read a single line from stdin and return as String
     */
    private String readLine()
    {
        try
        {
            BufferedReader rin = new BufferedReader(new InputStreamReader(System.in));
            return rin.readLine();
        }
        catch (Exception e)
        {
            return "";
        }
    }
    /**
     * the applications main loop.
     */
    public void run()
    {
    	System.out.println(AsciiSplash.getSplashArt());
        System.out.println("Welcome to the OJB PB tutorial application");
        System.out.println();
        // never stop (there is a special use case to quit the application)
        while (true)
        {
            try
            {
                // select a use case and perform it
                UseCase uc = selectUseCase();
                uc.apply();
            }
            catch (Throwable t)
            {
                broker.close();
                System.out.println(t.getMessage());
            }
        }
    }
    /**
     * select a use case.
     */
    public UseCase selectUseCase()
    {
        displayUseCases();
        System.out.println("type in number to select a use case");
        String in = readLine();
        int index = Integer.parseInt(in);
        return (UseCase) useCases.get(index);
    }
    

}
