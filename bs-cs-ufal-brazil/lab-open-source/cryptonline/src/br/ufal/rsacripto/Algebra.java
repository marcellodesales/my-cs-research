/**
 * Algebra.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.rsacripto;

import java.math.BigInteger;

import java.util.Random;

public class Algebra{
	
	public static BigInteger getRSAQuociente(BigInteger a, BigInteger b){
		return (a.divideAndRemainder(b))[0];
	}
	
	public static BigInteger getReminder(BigInteger a, BigInteger b){
		return (a.divideAndRemainder(b))[1];
	}
	
	public static BigInteger getQuociente(BigInteger a, BigInteger b){
		BigInteger q = new BigInteger("1");
		BigInteger unit = new BigInteger("1");
		BigInteger temp;
        while (b.multiply(q).compareTo(a) <= -1){
			temp = new BigInteger(q.add(unit).toString());
			q = temp;
		}
		//temp = new BigInteger(q.subtract(unit).toString());
		//q = temp;
		return q;
	}
	
	public static double getQuotient(double a, double b){
		double q = 1;
		while (b * q <= a)
			q++;
		q--;
		return q;
	}
	
	public static double getReminder(double a, double b){
        double q = 1;
        while (b * q <= a)
        	q++;
        q--;
		return (a - b * q);
	}
	
	public static boolean isPrime(double x){
    	double v = x;
     	double i = 2;

		while ((Algebra.getReminder(v,i) != 0) && ((i*i) <= v))
       		i++;
		return (Algebra.getReminder(v,i) != 0);
	}
	
	public static double getMDC(double p, double q){
		double r = Algebra.getReminder(p,q);
		while (r > 0){
			p = q;
			q = r;
			r = Algebra.getReminder(p,q);
		}
		return q;
	}
	
	public static double getEuler(double p, double q){
		return (p - 1) * (q - 1);
	}
	
	public static double getPowerModuleN(double a, double e, double n){
		double pp = 1;
		while (e != 0){
			if ((e % 2) != 0)
				pp  = a * pp % n;
			e = Algebra.getQuotient(e,2);
			a = a * a % n;
		}
		return pp;
	}
	
	public static int getRandom(int range){
		
//		Random r = new Random();
//		int rand = 0;
//		//rand = r.nextInt() % range;
//		while (rand == 0) rand = r.nextInt(range);
//		//if (rand < 1) rand += range;
		return (int)(Math.random() * --range + 1);
	}

	/**
	 * Method getNewPrime. Retorna um novo número primo de acordo com uma entrada
	 * e uma faixa especificada.
	 *
	 * @param    input               Número aleatório
	 * @param    range               Faixa. Usavel 2000.
	 *
	 * @return   a double O número primo desejado.
	 *
	 */
	public static double getNewPrime(double input, int range){
		double rand, number;
		do {
			number = Algebra.getRandom(Algebra.getRandom(range)) + input;
		} while (!Algebra.isPrime(number));
		return number;
	}
	
	public static void main(String[] args){
//		BigInteger x = BigInteger.probablePrime(10,new Random(344));
//		BigInteger y = BigInteger.probablePrime(10,new Random(508));
		BigInteger x = new BigInteger("150");
		BigInteger y = new BigInteger("3");
		
		System.out.println(x);
		System.out.println(y);
		
		//BigInteger res1 = Algebra.getRSAQuociente(x,y);
		//int res1 = Algebra.getQuotient(x.intValue(),y.intValue());
		//System.out.println(res1);
		
		
		//System.out.println(x.add(y));
		//System.out.println(x.compareTo(y));
	}
}

