package com.mycompany.alg_genetico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class GA {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input;
        try {
            input = new Scanner(
                    new FileReader("..\\Alg_genetico\\src\\main\\java\\com\\mycompany\\alg_genetico\\mat.txt"));   
        } catch (FileNotFoundException error) {
            System.out.println("No se pudo encontrar el archivo");
            System.out.println("edite la ruta del archivo, debe comenzar con la cantidad de colores,"
                    + "\nluego la cantidad de nodos y la matriz de adyacencia");
            return;
        }
        int colors =input.nextInt();
        int n=input.nextInt();
        int mat[][]= new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                mat[i][j]=input.nextInt();
            }
        }
        {/*Scanner sc = new Scanner(System.in);
        System.out.println("Cantidad de colores");
        int colors=sc.nextInt();
        System.out.println("Cantidad de nodos:");
        int n = sc.nextInt();
        int mat[][] = new int[n][n];
        //ArrayList<Integer> num = new ArrayList<Integer>();
        //while((n=sc.nextInt())!=0) num.add(n);
        //System.out.println("Ingrese matriz de adyacencia");
        for (int i = 1; i < n; i++) {
            //System.out.printf("Conexiones del nodo %d\n",i);
            for (int j = 0; j < i; j++) {
                //System.out.print(i);
                if (j != i) {
                    //System.out.printf("%d: ",j);
                    mat[j][i] = (int)Math.round(Math.random());//sc.nextInt();
                    mat[i][j] = mat[j][i];
                } else {
                    mat[j][i] = 0;
                }
            }
        }*/

 /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println("");
        }*/}
        FitnessCalc.setDatos(mat);

        // Poblacion Inicial
        Population myPop = new Population(n * 100, true, n, colors);

        // Evolución hasta N*1000 iteraciones, uno puede considerar muchas formas de detencion (fitness menor a cierto valor, solución real encontrada)
        int generationCount = 0;
        while (generationCount < n * 1000 && myPop.getFittest().getFitness() != n) {
            generationCount++;
            System.out.print(generationCount % 1000 == 0 ? generationCount + "\n" : "");
            myPop = Algorithm.evolvePopulation(myPop);
        }
        System.out.println(myPop.getFittest().getFitness() == n ? "El problema es resoluble con :" + colors + " colores" : "No es resoluble");
        System.out.println(myPop.getFittest() + " : " + myPop.getFittest().getFitness() + " gen:" + generationCount);
    }
}
