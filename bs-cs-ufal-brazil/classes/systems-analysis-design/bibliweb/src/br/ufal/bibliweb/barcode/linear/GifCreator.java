/**
 * GifCreator.java
 *
 * @author Marcello de Sales
 */

package br.ufal.bibliweb.barcode.linear;

import br.ufal.bibliweb.barcode.linear.*;
import br.ufal.bibliweb.barcode.linear.encoder.*;

public class GifCreator{
   public static void main ( String [] args ) {
      BarCode bc=new BarCode();
      bc.code="1998g55d001t9";
      bc.barType=bc.CODE128;
      bc.setSize(300,95);
	  BarCodeEncoder bce = new BarCodeEncoder(bc, "GIF", "c:/newfile.gif");
   }
	
}

