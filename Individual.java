package com.mycompany.alg_genetico;

public class Individual {

    static int defaultGeneLength = 10;
    static int defaultGeneVariety=3;
    private byte[] genes = new byte[defaultGeneLength];
    // fitness maximal
    private int fitness = Integer.MAX_VALUE;

    public Individual() {
    }

    public Individual(int length,int colors) {
        defaultGeneLength = length;
        genes = new byte[defaultGeneLength];
        defaultGeneVariety=colors;
        fitness = Integer.MAX_VALUE;
    }

    // Individuo al azar... valor de bits se eligen azarosamente
    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            byte gene = (byte) ((Math.random()*100) % defaultGeneVariety);
            genes[i] = gene;
        }
    }

    // seteadores y obtenedores
    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = Integer.MAX_VALUE;
    }

    /* Metodos publicos */
    public int size() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == Integer.MAX_VALUE) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}
