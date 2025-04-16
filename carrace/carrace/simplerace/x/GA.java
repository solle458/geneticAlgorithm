package simplerace.x;

import simplerace.*;
import java.util.*;

public class GA {
    static final int POP_SIZE = 30;
    static final int GENE_SIZE = 7;
    static final int GENERATIONS = 1000;
    static final double MUTATION_RATE = 0.1;

    public static void main(String[] args) {
        Random random = new Random();
        double[][] population = new double[POP_SIZE][GENE_SIZE];

        for (int i = 0; i < POP_SIZE; i++) {
            population[i][0] = 1.0 + random.nextDouble() * 0.4;
            population[i][1] = 1.0 + random.nextDouble() * 5.0;
            population[i][2] = 1.0 + random.nextDouble() * 5.0;
            population[i][3] = 1.0 + random.nextDouble() * 2.0;
            population[i][4] = 1.0 + random.nextDouble() * 2.0;
            population[i][5] = 1.0 + random.nextDouble() * 2.0;
            population[i][6] = 1.0 + random.nextDouble() * 1.0;
        }

        double[] bestGenome = null;
        double bestFitness = -Double.MAX_VALUE;

        for (int gen = 0; gen < GENERATIONS; gen++) {
            double[] fitness = new double[POP_SIZE];
            for (int i = 0; i < POP_SIZE; i++) {
                fitness[i] = GAUtils.evaluate(population[i]);
                if (fitness[i] > bestFitness) {
                    bestFitness = fitness[i];
                    bestGenome = population[i].clone();
                }
            }

            double[][] newPop = new double[POP_SIZE][GENE_SIZE];
            for (int i = 0; i < POP_SIZE; i++) {
                double[] parent1 = tournamentSelect(population, fitness, random);
                double[] parent2 = tournamentSelect(population, fitness, random);
                newPop[i] = crossover(parent1, parent2);
                mutate(newPop[i], random);
            }

            population = newPop;
            // System.out.printf("Generation %d: Best fitness = %.4f%n", gen, bestFitness);
        }

        System.out.println("Best genome: " + Arrays.toString(bestGenome));
        System.out.println("Best fitness: " + bestFitness);
    }

    static double[] tournamentSelect(double[][] pop, double[] fit, Random r) {
        int a = r.nextInt(pop.length), b = r.nextInt(pop.length);
        return fit[a] > fit[b] ? pop[a] : pop[b];
    }

    static double[] crossover(double[] p1, double[] p2) {
        double[] child = new double[p1.length];
        for (int i = 0; i < p1.length; i++)
            child[i] = (p1[i] + p2[i]) / 2.0;
        return child;
    }

    static void mutate(double[] genome, Random r) {
        for (int i = 0; i < genome.length; i++) {
            if (r.nextDouble() < MUTATION_RATE)
                genome[i] += r.nextGaussian() * 0.05;
        }
    }
}
