/**
 * RsaSender.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.rsacripto;
import java.util.Vector;
import java.util.Iterator;

public class RsaSender{
	
	/** The original message to be sent */
	private String originalMessage;
	/** The chripted message as a result of the rsa algorithm */
	private String chriptedMessage;
	/** The rsa structure containing the public and private keys */
	private Rsa rsa;
	
	/** Constructs a new RsaSender with the originalMessage along with the Rsa structure*/
	public RsaSender(String originalMessage, Rsa rsa){
		this.rsa = rsa;
		this.originalMessage = originalMessage;
		this.chriptedMessage = this.getChriptedMessage(this.originalMessage);
	}
	
	/** Gets the original message */
	public String getOriginalMessage(){
		return this.originalMessage;
	}
	
	/** Gets the chripted message */
	public String getChriptedMessage(){
		return this.chriptedMessage;
	}
	
	/** Gets the ascii representation of the original message */
	public String getAsciiMessage(){
		return this.getAsciiString(this.originalMessage);
	}
	
	/** Returns the chripted message */
	private String getChriptedMessage(String originalMessage){
		String asciiMessage = this.getAsciiString(originalMessage);
		Vector asciiBlocks = this.getAsciiBlocks(asciiMessage);
		return this.getChriptedBlocks(asciiBlocks);
	}
	
	/** Returns the ascii representation a string */
	private String getAsciiString(String widestring){
		String ascii = "";
		for (int i=0; i < widestring.length(); i++){
			ascii += (int)widestring.charAt(i)+100;
		}
		return ascii;
	}
	
	/**
	 * Returns the set of blocks of the ascii representation in a random way
	 * 1241355334 => [1241][355][3][34]
	 */
	private Vector getAsciiBlocks(String asciiM){
  		int i,tam;;
		String sAux;
		String asciiMessage = asciiM;
		Vector blocks = new Vector();
		System.out.println("Blocos");
		String stringN = new String(new Double(this.rsa.getPublicKey_N()).toString());
		int blockLength = stringN.length()-3;
		newstring:
    	while (asciiMessage.length() > 0){
			tam = asciiMessage.length();
			
			i = Algebra.getRandom(blockLength); //utiliza n +1

			if (i > tam) i = tam;
			
			if (tam != i)
				while (asciiMessage.charAt(i) == '0') i++;
			
//			if (Double.valueOf(String.valueOf(asciiMessage.charAt(i))).doubleValue() > this.rsa.getPublicKey_N()){
//				i--;
//				while (asciiMessage.charAt(i) == '0') i--;
//			}
					
//
//			String block = "0";
//			boolean nextZero = true;
//			String nextZe = "0";
//			while (block.charAt(0) == '0' || nextZero){
//				i = Algebra.getRandom(blockLength); //utiliza n +1
//    	      	if (i > tam) i = tam;
//				block = asciiMessage.substring(0,i);
//				if (i > asciiMessage.length()){
//					nextZero = false;
//				} else {
//					nextZe = String.valueOf(asciiMessage.charAt(i));
//					nextZero = nextZe.equals("0");
//				}
//			}
			String toBeCopied = asciiMessage.substring(0,i);
			if (Integer.parseInt(toBeCopied) > this.rsa.getPublicKey_N()){ //utiliza n
            	i--;
            	while (asciiMessage.charAt(i) == '0') i--;
			}
			String block = asciiMessage.substring(0,i);
						
			/*if (Algebra.getMDC(this.rsa.getPublicKey_N(),Double.parseDouble(sAux)) != 1){
				asciiMessage = asciiM; // this block may be one of the primes p | q
				break newstring;
			 }*/
			//System.out.print(block + Rsa.DELIMITER);
			asciiMessage = asciiMessage.substring(i,asciiMessage.length());
			blocks.add(new Integer(block));
		}
		System.out.println();
		return blocks;
	}

	/**
	 * Enchripts a block with the Power Module N algorithm
	 * [1241] => 343434
	 */
	private double getChriptedBlock(int x){
		return Algebra.getPowerModuleN(x,this.rsa.getPublicKey_E(),this.rsa.getPublicKey_N()); //e passado como par
	}
	
	/**
	 * Returns the set of chripted blocks of the ascii representation applying
	 * the Power Module N algorithm
	 * [1241][355][3][34] => 343434-43552-43544-3435
	 *
	 */
	private String getChriptedBlocks(Vector codedBlocks){
		String code,wrongC;
		double chriptedBlock;
        Iterator blocks = codedBlocks.iterator();
        code = "";
        while (blocks.hasNext()){
			//code = code + 'C('+inttostr(codedBlocks[i])+') ';
			Integer blockValue = (Integer)blocks.next();
			chriptedBlock = this.getChriptedBlock(blockValue.intValue());
			wrongC = chriptedBlock+""; //take off .0 of the value
			wrongC = wrongC.substring(0,wrongC.length()-2);
            code += wrongC + Rsa.DELIMITER;
		}
		code = code.substring(0,code.length()-1);
		return code;
	}
}

