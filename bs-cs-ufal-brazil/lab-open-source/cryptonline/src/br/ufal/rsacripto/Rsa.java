/**
 * Rsa.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.rsacripto;

import br.ufal.rsacripto.Algebra;
import java.util.Vector;
import java.util.Iterator;
public class Rsa{
	
	public static final String DELIMITER = "-";
	public static final String SETA_LOG = "      -> ";
	
	private double p;
	private double q;
	private double e;
	private double n;
	private double d;
	private double fi;
	
	private double t,u;
	
	private Vector log;
	
	public Rsa(){
		this.log = new Vector();
		this.setNewPrimes();
		this.setNewPublicKeys();
		this.setNewPrivateKeys();
	}
	
	public Rsa(double p, double q){
		this.p = p;
		this.q = q;
		this.setNewPublicKeys();
		this.setNewPrivateKeys();
	}
	
	public Rsa(double n, double e, double d){
		this.n = n;
		this.e = e;
		this.d = d;
	}
	
	public double getPublicKey_N(){
		return this.n;
	}
	
	public double getPublicKey_E(){
		return this.e;
	}
	
	public double getPrivateKey_D(){
		return this.d;
	}
	
	private void setNewPrimes(){
		this.p = Algebra.getNewPrime(33,15000);
		this.q = Algebra.getNewPrime(23,15000);
		this.log.add(Rsa.SETA_LOG + "Configurando primos aleatórios");
		this.log.add("        P = "+this.p);
		this.log.add("        Q = "+this.q);
	}
	
	private void setNewPublicKeys(){
		this.n = this.p * this.q;
		this.e = this.getPublicKeyE();
		
		this.log.add(Rsa.SETA_LOG + "Calculando Chaves Públicas");
		this.log.add("          N = P * Q; N = "+this.n);
		this.log.add("          E = "+this.e);
		this.log.add("");
		this.log.add("       Chave Pública (N,E) = ("+this.n+","+this.e+")");
		this.log.add("");
	}
	
	private void setNewPrivateKeys(){
		this.d = this.getDinverseE();
	}
	
	private double getPublicKeyE(){
		//FI = (P-1) * (Q-1);
		this.fi = Algebra.getEuler(this.p,this.q);
		
		this.log.add(Rsa.SETA_LOG + "FI = (P-1) * (Q-1); FI = "+this.fi);
		this.log.add("");
		this.log.add(Rsa.SETA_LOG + "Calculando (E)");
		
		double aux = 2;
		double mdc = Algebra.getMDC(aux,this.fi);
		this.log.add("           Enquanto mdc("+aux+","+fi+") != 1");
		while (mdc != 1) {
			
			this.log.add("           mdc("+aux+","+fi+") = "+mdc);
			
			aux++;
			mdc = Algebra.getMDC(aux,this.fi);
		}
		this.log.add("           mdc("+aux+","+fi+") = "+mdc+" Correto!");
		
		return aux;
	}
	
	public double getDinverseE(){
		double u1 = 1; double u2 = 0; double u3 = this.fi;
		double v1 = 0; double v2 = 1; double v3 = this.e;
		double t1,t2,t3,vv,q;
		
		while (v3 > 0){
			q  = Algebra.getQuotient(u3,v3);
			t1 = u1 - q * v1;
			t2 = u2 - q * v2;
			t3 = u3 - q * v3;
			
			u1 = v1; u2 = v2; u3 = v3;
			v1 = t1; v2 = t2; v3 = t3;
		}
		
		vv = u2;
		return (vv < 0) ? (vv + this.fi):(vv);
	}
	
	public double getExtendedMDC(double p, double q){
		double mdc;
   		double a = p;
   		double b = q;

  		if (b == 0){
      		mdc = a;
			this.t = 1;
			this.u = 0;
		}
		
		double x2 = 1;  double x1 = 0;
  		double y2 = 0;  double y1 = 1;
		double quoc,r;
  		while (b > 0){
    		quoc = Algebra.getQuotient(a,b);
    		r = a - b * quoc;
			this.t = x2 - quoc * x1;
    		this.u = y2 - quoc * y1;
			x2 = x1;
    		x1 = this.t;
    		y2 = y1;
    		y1 = this.u;

			a = b;
    		b = r;
		}
  		mdc = a;
  		this.t = x2;
  		this.u = y2;
		return mdc;
	}
	
	public void printAll(){
		System.out.println("#### All RSA Information ####");
		System.out.println();
		System.out.println("Primo P: "+this.p);
		System.out.println("Primo Q: "+this.q);
		System.out.println();
		System.out.println("Chaves Publica (N, E) = ("+this.n+", "+this.e+")");
		System.out.println("Chaves Privada (N, D) = ("+this.n+", "+this.d+")");
		System.out.println();
		//System.out.println("MDC extendido =>  a * (t) + b * (u) = mdc ");
		//System.out.println(this.p+" * ("+this.t+") + "+this.q+" * ("+this.u+") = "+this.getExtendedMDC(this.p,this.q));
	}
	
	public String getChriptedMessage(String originalMessage){
		RsaSender sender = new RsaSender(originalMessage,this);
		return sender.getChriptedMessage();
	}
	
//	public String getDechriptedMessage(String chriptedMessage){
//		RsaReceiver receiver = new RsaReceiver(chriptedMessage,this);
//		return receiver.getOriginalMessage();
//	}
		
	public static void main(String[] args){
		Rsa rsa = new Rsa(243569,5,48509);
		//Rsa rsa = new Rsa();
		rsa.printAll();
		String origem = "Agora estou satisfeito porque esta funcionando... Agora sei ã agora isso vai ficar muito legal porque agora vai ficar lindo!!!!!!";
		
		RsaSender sender = new RsaSender(origem,rsa);
		//System.out.println("Ascii: "+sender.getAsciiMessage());
		System.out.println("Cripted: "+sender.getChriptedMessage());
		
		RsaReceiver receiver = new RsaReceiver(sender.getChriptedMessage(),rsa);
		//RsaReceiver receiver = new RsaReceiver("5252-1754-5014-1746-2515-5559-6727-515-224-6768-2515-5014-3420-6768-5400",rsa);
		System.out.println("Original: |"+receiver.getOriginalMessage()+"|");
	}
}


