package com.mycompany.alg_genetico;

public class Algorithm {

    /* Parametros */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final double crossRate = 0.8;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // Evolucionar
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false, pop.getIndividual(0).size(),pop.getIndividual(0).defaultGeneVariety);

        // Al funcionar con elitismo, mantenemos el mejor de la generacion pasada,
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Creamos nuevos elementos a través de cruzamiento
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);//elegimos los padres por torneo
            Individual indiv2 = tournamentSelection(pop);
            //si no se cruzan por crossrate, se deja al mejor padre
            Individual newIndiv;
            if (Math.random() <= crossRate) {
                newIndiv = crossover(indiv1, indiv2);
            } else if (indiv1.getFitness() <= indiv2.getFitness()) {
                newIndiv = indiv1;
            } else {
                newIndiv = indiv2;
            }
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Aplicamos mutacion
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Cruzamiento
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual(indiv1.size(),indiv1.defaultGeneVariety);
        for (int i = 0; i < indiv1.size(); i++) {
            // Cruzamos por cada bit
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutacion por bit
    private static void mutate(Individual indiv) {
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                byte gene = (byte) ((Math.random()*100) % 3);
                indiv.setGene(i, gene);
            }
        }
    }

    // Seleccion
    private static Individual tournamentSelection(Population pop) {
        // Tomamos tournamentSize elementos al azar y pelean entre si
        Population tournament = new Population(tournamentSize, false, pop.getIndividual(0).size(),pop.getIndividual(0).defaultGeneVariety);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Elegimos el mejor de ellos
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}
