/**
 * Cromossomo.java
 *
 * @author Marcello Junior e André Correia
 */
package br.ufal.revida;

import java.util.Vector;
import java.util.Random;

public class Chromosome{
	
	/** The Chromosome sequence of binary digits. */
	private String sequence;
	/** The max length of the Chromosome's sequence */
	private static final int MAX_SEQUENCE_LENGTH = 8;
	
	/** Creates a new Chromosome with a given sequence. */
	public Chromosome(String sequence){
		this.sequence = sequence;
	}
	
	/** Creates a new Chromosome with an auto-genarated sequence. */
	public Chromosome(){
		this.sequence = this.getBinarySequence((int)(Math.random()*this.getMaxDifferentKind()));
	}
	
	/** Gets the maximum number of different Chromosomes with MAX_SEQUENCE_LENGTH numbers. */
	public static int getMaxDifferentKind(){
		String maxBinary = "";
		for (int i=0; i < Chromosome.MAX_SEQUENCE_LENGTH; i++){
			maxBinary += "1";
		}
		return getIntegerNumber(maxBinary);
	}
	
	/** Returns a binary sequence from a given value. */
	public static String getBinarySequence(int value){
		String sequence = Integer.toBinaryString(value);
		String complement = "";
		for (int i=0; i<(Chromosome.MAX_SEQUENCE_LENGTH - sequence.length()); i++){
			complement += "0";
		}
		return complement+sequence;
	}
	
	/** Returns the binary sequence of a Chromosome. */
	public String getSequence(){
		return this.sequence;
	}
	
	/** Sets a new sequence of binary of the Chromosome. */
	public void setSequence(String newSequence){
		this.sequence = newSequence;
	}
	
	/** Returns the binary number representation of a given value. */
	public static int getIntegerNumber(String value){
		int temp = 0;
		for (int i=0; i < value.length(); i++){
			temp += Integer.parseInt(value.substring(i,i+1)) * (Math.pow(2,(value.length()-i-1)));
		}
		return temp;
	}
	
	/** Returns the adaptation degree of the Chromosome. */
	public int getAdaptationDegree(){
		return -(this.getIntegerNumber(this.sequence) * this.getIntegerNumber(this.sequence));
	}
	
	/** Gets 2 (two) sons of the crossover of a father and mother Chromosome. */
	public static Vector getCrossoverSons(Chromosome x, Chromosome y){
		Vector sons = new Vector(2);
		if (x.getSequence().length() == y.getSequence().length()){
			int cutPoint;
			// Garante que o ponto de cruzamento nunca será 0;
			while ((cutPoint = (int)(Math.random()*(x.getSequence().length()-1))) == 0);
			System.out.println("Ponto de Corte do cruzamento = "+cutPoint);
			sons.add(new Chromosome(x.getSequence().substring(0,cutPoint)+y.getSequence().substring(cutPoint,y.getSequence().length())));
			sons.add(new Chromosome(y.getSequence().substring(0,cutPoint)+x.getSequence().substring(cutPoint,x.getSequence().length())));
		}
		return sons;
	}
	
	/** Gets the mutation of a given Chromosome x. */
	public static Chromosome getMutation(Chromosome x){
		int mutationPoint;
		String sequence = x.getSequence();
		// Garante que o ponto de mutação nunca será 0;
		while ((mutationPoint = (int)(Math.random()*(sequence.length()-1))) == 0);
		int point = mutationPoint+1;
		System.out.println("Ponto de mutacao "+point);
		String mutationString = sequence.substring(mutationPoint,mutationPoint+1);
		String first = sequence.substring(0,mutationPoint);
		String last  = sequence.substring(mutationPoint+1,sequence.length());
		if (sequence.charAt(mutationPoint) == '1')
			x.setSequence(first+"0"+last);
		else x.setSequence(first+"1"+last);
		return x;
	}
	
	/** Gets the inverse of a given Chromosome x. */
	public static Chromosome getInverse(Chromosome x){
		int inverseX;
		int inverseY;
		String sequence = x.getSequence();
		// garante que inverseX será sempre maior que inverseY;
		while ((inverseY  = (int)(Math.random()*(sequence.length()-1))) <= (inverseX  = (int)(Math.random()*(sequence.length()-1))));
		String inverse = "";
		int maior = inverseY;
		int menor = inverseX;
		for (int i=maior; i >= menor; i--){
			inverse += ((sequence.charAt(i)) == '1') ? "1" : "0";
		}
		inverse = sequence.substring(0,menor) + inverse + sequence.substring(maior+1,sequence.length());
		inverseX++;
		inverseY++;
		System.out.println(inverseX+"<-X ---- Y->"+inverseY);
		x.setSequence(inverse);
		return x;
	}
		
	public static void main(String[] arg){
		
		Chromosome father = new Chromosome();
		Chromosome mother = new Chromosome();
		System.out.println("Grau de adaptação dos Cromossomos");
		System.out.println("Pai = " + father.getAdaptationDegree());
		System.out.println("Mae = " + mother.getAdaptationDegree());
		System.out.println();
		System.out.println("Cruzamento de Cromossomo");
		System.out.println("Pai = " + father.getSequence());
		System.out.println("Mae = " + mother.getSequence());
		System.out.println();
		Vector sons = Chromosome.getCrossoverSons(father, mother);
		Chromosome son1 = (Chromosome)sons.get(0);
		Chromosome son2 = (Chromosome)sons.get(1);
		System.out.println("Resultado do cruzamento");
		System.out.println("Filho 1: "+son1.getSequence());
		System.out.println("Filho 2: "+son2.getSequence());
		System.out.println();
		System.out.println("Mutacao do Pai");
		father = Chromosome.getMutation(father);
		System.out.println(father.getSequence());
		System.out.println();
		System.out.println("Inverso do Pai, mutado.");
		father = Chromosome.getInverse(father);
		System.out.println(father.getSequence());
	}
}

