/**
 * RsaReceiver.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.rsacripto;
import java.util.Vector;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Enumeration;

public class RsaReceiver{
	
	/** The original message received */
	private String originalMessage;
	/** The chripted message  as a result of the rsa algorithm */
	private String chriptedMessage;
	/** The rsa structure containing the public and private keys */
	private Rsa rsa;
	
	/** Constructs a new RsaSender with the originalMessage along with the Rsa structure*/
	public RsaReceiver(String chriptedMessage, Rsa rsa){
		this.rsa = rsa;
		this.chriptedMessage = chriptedMessage;
		this.originalMessage = this.getDeschriptedMessage(this.chriptedMessage);
	}
	
	public String getOriginalMessage(){
		return this.originalMessage;
	}
	
	public String getChriptedMessage(){
		return this.chriptedMessage;
	}
	
	private String getDeschriptedMessage(String chriptedMessage){
		Vector chriptedBlocks = this.getChriptedBlocks(chriptedMessage);
		String dechriptedBlocks = this.getDeschriptedBlocksInAscii(chriptedBlocks);
		return this.getORiginalMessage(dechriptedBlocks);
	}
	
	private Vector getChriptedBlocks(String chriptedMessage){
		String aux = "",blocks;
		Vector chriptedBlocks = new Vector();
		StringTokenizer tokenizer = new StringTokenizer(chriptedMessage,Rsa.DELIMITER);
		while (tokenizer.hasMoreTokens()){
			String chriptedBlock = tokenizer.nextToken();
			chriptedBlocks.add(chriptedBlock);
		}
//      	for (int i = 0; i < chriptedMessage.length()+1; i++){
//        	if ((i != chriptedMessage.length()) && (chriptedMessage.charAt(i) != '-'))
//				aux += chriptedMessage.charAt(i);
//            else {
//                //blocks := blocks + 'C('+aux+') ';
//				chriptedBlocks.add(aux);
//                aux = "";
//			}
//	  	}
		return chriptedBlocks;
	}
	
	private double getDechriptedBlock(double chriptedBlock){
		return Algebra.getPowerModuleN(chriptedBlock,this.rsa.getPrivateKey_D(),this.rsa.getPublicKey_N());
	}
	
	private String getDeschriptedBlocksInAscii(Vector chriptedBlocks){
		int dechriptedValeu;
		Iterator blocks = chriptedBlocks.iterator();
		String asciiMessage = "";
		while (blocks.hasNext()){
			Double blockValeu = new Double((String)blocks.next());
			dechriptedValeu = (int)this.getDechriptedBlock(blockValeu.doubleValue());
			asciiMessage += dechriptedValeu+"";
		}
        return asciiMessage;
	}
	
	private String getORiginalMessage(String asciiMessage){
		String origem = "";
		while(asciiMessage.length() > 0){
			String valueStr = asciiMessage.substring(0,3);
			asciiMessage = asciiMessage.substring(3,asciiMessage.length());
			int asciiValue = Integer.parseInt(valueStr)-100;
			String charValue = new Character((char)asciiValue).toString();
			origem += charValue;
		}
		return origem;
	}
}

