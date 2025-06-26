package com.mycompany.alg_genetico;

public class Population {

    Individual[] individuals;

    /*
     * Constructors
     */
    // Crea una poblacion
    public Population(int populationSize, boolean initialise, int length,int colors) {
		individuals = new Individual[populationSize];
        // Inicializa Poblacion
        if (initialise) {
            // Crea individuos
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual(length,colors);
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
    }

    // Obtenedores
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Encuentra el mejor individuo (en este caso, minimizamos
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    // Obtiene tamano de poblacion
    public int size() {
        return individuals.length;
    }

    // Almacena individuos en la poblacion
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
}