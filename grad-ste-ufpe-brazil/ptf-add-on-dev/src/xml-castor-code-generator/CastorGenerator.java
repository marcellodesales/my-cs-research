/*
 * @created 13/07/2004 14:39:19
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE Brazil Design Center
 * Software Test Program
 * CIn / UFPE / Brazil
 * 
 * */
 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.exolab.castor.builder.SourceGenerator;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import br.ufpe.cin.stp.ptfaddon.model.wizardfile.ExecutionWizard;
import br.ufpe.cin.stp.ptfaddon.model.wizardfile.NeededFiles;
import br.ufpe.cin.stp.ptfaddon.model.wizardfile.Platform;
import br.ufpe.cin.stp.ptfaddon.model.wizardfile.TestCentralInfo;
import execution.wizardfile.tags.Execution;

/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 13/07/2004 14:39:19
 */

public class CastorGenerator {

    private static void readUpdate(){
        String id = "1089762256746";
        String file = "bindings/xml/execution-"+id+".ptf";
        
        try {
            FileReader reader = new FileReader(file);
            ExecutionWizard exec = (ExecutionWizard)ExecutionWizard.unmarshal(reader);
            
            System.out.println(exec.getDate());
            System.out.println(exec.getPlatform().getType());
            
            exec.getPlatform().setHardware("NEW TRIPLETS");   
                       
            FileWriter writer = new FileWriter(file);

            exec.marshal(writer);
            
        } catch (MarshalException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
                    
    }
    
    private static void createNew(){
        Date today = new Date();
        String id = String.valueOf(today.getTime());
        
        ExecutionWizard exec = new ExecutionWizard(today);
        
        Platform pf = new Platform("p2k","C650","LIBVFFSOAP344");
        TestCentralInfo tci = new TestCentralInfo("WMJ011","cyle_testcentral","automatic tests");
        NeededFiles nf = new NeededFiles("c:/configuration.cfg","c:/test_auto/lib/testcases.jar","c:/testList.list");
        
        exec.setPlatform(pf);
        exec.setTestCentralInfo(tci);
        exec.setNeededFiles(nf);
                
        try {
            FileWriter writer = new FileWriter("bindings/xml/execution-"+id+".ptf");
            exec.marshal(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {
        SourceGenerator srg = new SourceGenerator();
        //srg.generateSource("bindings/xsd/execution.xsd","br.ufpe.cin.stp.ptfaddon.model.wizardfile");
        createNew();
        //readUpdate();
    }
}
