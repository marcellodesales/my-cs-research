package br.com.aulaweb.persistence.antigo;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.singlevm.PersistenceBrokerConfiguration;
import org.apache.ojb.broker.util.configuration.ConfigurationException;
import org.apache.ojb.broker.util.ui.AsciiSplash;
import org.apache.ojb.odmg.OJB;
import org.odmg.Database;
import org.odmg.Implementation;
import org.odmg.ODMGException;

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
    private static String databaseName;

	static{
        
		try{
			databaseName = ((PersistenceBrokerConfiguration)
PersistenceBrokerFactory.getConfigurator().getConfigurationFor(null)).getRepositoryFilename();
        }
        catch (ConfigurationException e){
            databaseName = "repository.xml";
        }
	}
	

    /**
     * Application constructor comment.
     */
    public Application(){
        // get facade instance
        Implementation odmg = OJB.getInstance();
        Database db = odmg.newDatabase();
        //open database
        try{
            db.open(databaseName, Database.OPEN_READ_WRITE);
        }catch (ODMGException ex){
            ex.printStackTrace();
        }

        useCases = new Vector();
        useCases.add(new UCListAllProducts(odmg));
        useCases.add(new UCEnterNewProduct(odmg));
//        useCases.add(new UCEditProduct(odmg));
        useCases.add(new UCDeleteProduct(odmg));
        useCases.add(new UCQuitApplication(odmg));
    }

    /**
     * Disply available use cases.
     */
    public void displayUseCases(){
        System.out.println();
        for (int i = 0; i < useCases.size(); i++){
            System.out.println("[" + i + "] " + ((UseCase) useCases.get(i)).getDescription());
        }
    }


    /**
     * read a single line from stdin and return as String
     */
    private String readLine(){
        try{
            BufferedReader rin = new BufferedReader(new InputStreamReader(System.in));
            return rin.readLine();
        }catch (Exception e){
            return "";
        }
    }

    /**
     * the applications main loop.
     */
    public void run(){
    	System.out.println(AsciiSplash.getSplashArt());
        System.out.println("Welcome to the OJB ODMG tutorial application");
        System.out.println();
        // never stop (there is a special use case to quit the application)
        while (true){
            try{
                // select a use case and perform it
                UseCase uc = selectUseCase();
                uc.apply();
            }catch (Throwable t){
                System.out.println(t.getMessage());
            }
        }
    }

    /**
     * select a use case.
     */
    public UseCase selectUseCase(){
        displayUseCases();
        System.out.println("type in number to select a use case");
        String in = readLine();
        int index = Integer.parseInt(in);
        return (UseCase) useCases.get(index);
    }
	
    /**
     * Insert the method's description here.
     * Creation date: (04.03.2001 10:40:25)
     * @param args java.lang.String[]
     */
    public static void main(String[] args){
        Application app = new Application();
        app.run();
    }
	
}
