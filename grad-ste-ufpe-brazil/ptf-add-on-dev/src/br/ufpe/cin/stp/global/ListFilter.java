/*
 * @created 09/09/2004 11:22:36
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */

package br.ufpe.cin.stp.global;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @created 09/09/2004 11:22:36
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public class ListFilter {

    /**
     * <code>singleton</code> is the single instance of this class.
     */
    private static ListFilter singleton;

    private ListFilter() {
    }

    /**
     * @created 09/09/2004 11:22:52
     * @return The single instance.
     */
    public synchronized static ListFilter getInstance() {
        if (singleton == null) {
            singleton = new ListFilter();
        }
        return singleton;
    }

    /**
     * @created 09/09/2004 11:26:28
     * @param list
     * @param token
     * @return filtered list based on the specified token
     */
    public String[] getFilteredList(String[] originalList, String token){
        List filteredSet = new Vector();
        String[] filteredList = null;

        for (int i = 0; i < originalList.length; i++) {
            if (this.containsToken(originalList[i],token))
                filteredSet.add(originalList[i]);
        }

        filteredList = new String[filteredSet.size()];
        Iterator i = filteredSet.iterator();
        int ii = -1;
        while (i.hasNext())
            filteredList[++ii] = (String) i.next();

        return filteredList;
    }

    public boolean containsToken(String string, String token){
      return string.toLowerCase().indexOf(token.toLowerCase()) != -1;
    }

    public static void main(String[] args) {
        /*String token = "coM.";
        String[] packages = {".combr","com.motorola","motorola.Com","motorola.cOM.br"};

        String[] filtered = ListFilter.getInstance().getFilteredList(packages,token);

        for (int i = 0; i < filtered.length; i++) {
            System.out.println(filtered[i]);
        }*/
        
        double a = 3.0 / 0;
        
        if (a == Double.POSITIVE_INFINITY)
            System.out.println(a);
        System.out.println(Integer.toBinaryString((byte)a));
        byte b = (byte)a;
        System.out.println(b);
    }
}
