/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Prims {

    private boolean unsettled[];
    private boolean settled[];
    private double adjacencyMatrix[][];
    private double key[];
    public static final int INFINITE = 999;
    private int parent[];
    private int index_c;
    HashMap<String, Double> finaltree;

    public Prims(int count) {
        index_c = count;
        unsettled = new boolean[index_c];
        settled = new boolean[index_c];
        adjacencyMatrix = new double[index_c][index_c];
        key = new double[index_c];
        parent = new int[index_c];
    }

    public int getUnsettledCount(boolean unsettled[]) {
        int count = 0;
        for (int index = 0; index < unsettled.length; index++) {
            if (unsettled[index]) {
                count++;
            }
        }
        return count;
    }

    public HashMap<String, Double> primsAlgorithm(double adjacencyMatrix[][]) {
        finaltree=new HashMap<String, Double>();
//        index_c = count;
        int evaluationVertex;
        for (int source = 0; source < index_c; source++) {
            for (int destination = 0; destination < index_c; destination++) {
                System.out.print("\t"+adjacencyMatrix[source][destination]);
                this.adjacencyMatrix[source][destination] = adjacencyMatrix[source][destination];
            }
            System.out.println("");
        }

        for (int index = 0; index < index_c; index++) {
            key[index] = INFINITE;
            System.out.println("Key: "+key[index]);
        }
        key[0] = 0;
        unsettled[0] = true;
        parent[0] = 1;

        while (getUnsettledCount(unsettled) != 0) {
            evaluationVertex = getMimumKeyVertexFromUnsettled(unsettled);
            System.out.println("Evaluation Vertex: "+evaluationVertex);
            unsettled[evaluationVertex] = false;
            settled[evaluationVertex] = true;
            evaluateNeighbours(evaluationVertex);
        }
        printMST();
        return finaltree;
    }

    private int getMimumKeyVertexFromUnsettled(boolean[] unsettled) {
        double min = Integer.MAX_VALUE;
        int node = 0;
        for (int vertex = 0; vertex < index_c; vertex++) {
            if (unsettled[vertex] == true && key[vertex] < min) {
                node = vertex;
                min = key[vertex];
            }
        }
        return node;
    }

    public void evaluateNeighbours(int evaluationVertex) {

        for (int destinationvertex = 0; destinationvertex < index_c; destinationvertex++) {
            if (settled[destinationvertex] == false) {
                if (adjacencyMatrix[evaluationVertex][destinationvertex] != INFINITE) {
                    if (adjacencyMatrix[evaluationVertex][destinationvertex] < key[destinationvertex]) {
                        key[destinationvertex] = (double) adjacencyMatrix[evaluationVertex][destinationvertex];
                        System.out.println("Key in evaluate neigh: "+key[destinationvertex]);
                        parent[destinationvertex] = evaluationVertex;
                        System.out.println("Parent Vertex: "+parent[destinationvertex]);
                    }
                    unsettled[destinationvertex] = true;
                }
            }
        }
    }

    public void printMST() {
        System.out.println("SOURCE  : DESTINATION = WEIGHT");
        for (int vertex = 1; vertex < index_c; vertex++) {
            System.out.println(parent[vertex] + "\t:\t" + vertex + "\t=\t" + adjacencyMatrix[parent[vertex]][vertex]);
            finaltree.put(parent[vertex]+"-"+vertex, adjacencyMatrix[parent[vertex]][vertex]);
        }
    }
}