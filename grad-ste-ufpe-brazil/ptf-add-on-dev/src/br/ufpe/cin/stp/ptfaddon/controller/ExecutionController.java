/*
 * @created 15/07/2004 18:49:38
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.controller;

import java.util.regex.Pattern;



/**
 * @author Marcello Alves de Sales Junior <BR>
 * email: <a href=mailto:masj2@cin.ufpe.br>masj2@cin.ufpe.br</a> <BR>
 * @created 15/07/2004 18:49:38
 */
public class ExecutionController {

    public static void main(String[] args) {//([a-z])*(.([a-z][A-Z][0-9])*)*
        //String patternStr2 = "(#{1}([a-z])*(.([a-z]|[A-Z]|[0-9])*)*)";
        Pattern nonCommented = Pattern.compile("([a-z])*(.(.[a-z][A-Z][0-9])*)*");
        Pattern commented = Pattern.compile("^#(.([a-z][A-Z][0-9])*)*");
        Pattern eachother = Pattern.compile("");
        // Determine if there is an exact match
       
        System.out.println(eachother.matcher("#").matches());
        System.out.println(nonCommented.matcher("#sdsd").matches());
        System.out.println(nonCommented.matcher("##sdsd").matches());
        System.out.println(nonCommented.matcher("...sdsd.com.com").matches());
        System.out.println(nonCommented.matcher("sdsd.com").matches());
        System.out.println();
        System.out.println(commented.matcher("#sdsd").matches());
        System.out.println(commented.matcher("##sdsd").matches());
        System.out.println(commented.matcher("#sdsd.com.com").matches());
        System.out.println(commented.matcher("sdsd").matches());        
    }
}
