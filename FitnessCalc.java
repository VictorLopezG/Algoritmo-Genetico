package com.mycompany.alg_genetico;

import java.lang.Math;

public class FitnessCalc {

    static int[][] datos = {{1, 4, 3, 7, -1, -7, -8, 10, 2, 3}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};//ejemplo entrada

    public static void setDatos(int[][] input) {//incluir entrada
        datos = input;
    }

    // Calculo de Fitness... simplemente la suma.
    static int getFitness(Individual individual) {
        int fitness = individual.size();
        //boolean cero = true;
        for (int i = 1; i < individual.size(); i++) {
            for(int j=0;j<i;j++){
                if (datos[i][j]==1&&i!=j){
                   if(individual.getGene(i)==individual.getGene(j)){
                       fitness--;
                       //System.out.print("-");
                   } 
                }
            }
        }
        return fitness;
    }
}
