/**
 * ChromosomePopulation.java
 *
 * @author Marcello Junior e André Correia
 */
package br.ufal.revida;

import java.util.Vector;
import java.util.Random;
import br.ufal.revida.Chromosome;

public class ChromosomePopulation{
	
	/** The probability of having a chromosome mutation in the population. */
	private final float MUTATION_PROBABILITY  = 0.10f;
	/** The probability of having a crossover between two chromosomes in the population. */
	private final float CROSSOVER_PROBABILITY = 0.12f;
	/** The default number of chromosomes in the population. */
	private final int DEFAULT_POPULATION_SIZE = 20;
	/** The population of different chromosomes. */
	private Vector chromosomePopulation;
	
	/** Creates a new chromosome population with the default number of chromosomes. */
	public ChromosomePopulation(){
		this.chromosomePopulation = new Vector();
		this.generateNewPopulation(this.DEFAULT_POPULATION_SIZE);
	}
	
	/** Creates a new chromosome population with a defined initial number of chromosomes. */
	public ChromosomePopulation(int populationSize){
		this.chromosomePopulation = new Vector();
		this.generateNewPopulation(populationSize);
	}

	/** Gets the probability of having a chromosome mutation in the population. */
	public float getMutationProbability(){
		return this.MUTATION_PROBABILITY;
	}
	
	/** Gets the probability of having a crossover between two chromosomes in the population. */
	public float getCrossoverProbability(){
		return this.CROSSOVER_PROBABILITY;
	}
	
	/** Gets the number of chromosomes in the population. */
	public int getSize(){
		return this.chromosomePopulation.size();
	}
	
	/** Verifies if the population contains the given chromosome. */
	private boolean contains(Chromosome chromosome){
		boolean contains = false;
		for (int i=0; i < this.getSize(); i++){
			if (((Chromosome)this.chromosomePopulation.get(i)).getSequence().equals(chromosome.getSequence())){
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	/** Generates a new chromosome population with a given number of chromosomes. */
	public void generateNewPopulation(int populationNumber){
		populationNumber = (populationNumber > Chromosome.getMaxDifferentKind()) ? Chromosome.getMaxDifferentKind() : populationNumber;
		this.chromosomePopulation.clear();
		Chromosome chromosome;
		for(int i=0; i < populationNumber; i++){
			chromosome = new Chromosome();
			while (this.contains(chromosome))
				chromosome = new Chromosome();
			this.chromosomePopulation.add(chromosome);
		}
	}
	
	/** Returns a chromosome in the given index position. */
	public Chromosome getChromosome(int index){
		return (Chromosome)this.chromosomePopulation.get(index);
	}
	
	/** Adds a new specified Chromosome to the population. */
	public void addChromosome(Chromosome newChromosome) throws PopulationChromosomeAlreadyExistsException{
		if (!this.contains(newChromosome)){
			this.chromosomePopulation.add(newChromosome);
		} else throw new PopulationChromosomeAlreadyExistsException();
	}
	
	public void showPopulation(){
		System.out.println();
		System.out.println("A populacao de cromossomos");
		for (int i = 0; i < this.getSize(); i++){
			Chromosome chromosome = this.getChromosome(i);
			int number = i;
			number++;
			System.out.println(number+" = "+chromosome.getSequence());
			for (int j=0; j < this.getSize(); j++){
				if (i == j) continue; else {
					Chromosome chromosome2 = this.getChromosome(j);
					if (chromosome2.getSequence().equals(chromosome.getSequence())){
						System.out.println("Erro: Cromossomo "+i+" = Cromossomo "+j);
						System.out.println(chromosome.getSequence()+" = "+chromosome2.getSequence());
					}
				}
			}
		}
	}
	
	/** Gets the medium adaptation degree of the chromosome population. */
	public int getMediumAdaptationDegree(){
		int medium = 0;
		for (int i=0; i<this.getSize(); i++){
			medium += ((Chromosome)this.chromosomePopulation.get(i)).getAdaptationDegree();
		}
		return (medium / this.getSize());
	}
	
	/** Gets the selected population. */
	public ChromosomePopulation getSelection(){
		int grauMedio = this.getMediumAdaptationDegree();
		ChromosomePopulation selectionPopulation = new ChromosomePopulation(0);
		for(int i=0; i < this.getSize(); i++){
			Chromosome chromosome = (Chromosome)this.chromosomePopulation.get(i);
			//System.out.println(chromosome.getAdaptationDegree()+" >= "+grauMedio);
			try {
				if (chromosome.getAdaptationDegree() >= this.getMediumAdaptationDegree()){
					selectionPopulation.addChromosome(chromosome);
					//System.out.println("Cromosomo adicionado: "+chromosome.getSequence());
				}
			} catch (PopulationChromosomeAlreadyExistsException pcaee){
					pcaee.printStackTrace();
			}
		}
		return selectionPopulation;
	}
	
	public static void main(String args[]){
		ChromosomePopulation population = new ChromosomePopulation(5);
		population.showPopulation();
		System.out.println();
		System.out.println("A nova populacao selecionada");
		ChromosomePopulation selectionPopulation = population.getSelection();
		selectionPopulation.showPopulation();
	}
}

