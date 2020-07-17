/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

/**
 *
 * @author Vina Kokane
 */
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Kruskal {

    private int count_v;
    private List<Edge> edges;
    public static final int MAX_VALUE = 999;
    private int visited[];
    private double spanning_tree[][];
    private List<Ed> eds;
    HashMap<String, Double> finaltree;

    public Kruskal(int count) {
        count_v = count;
        edges = new LinkedList<Edge>();
        visited = new int[count_v];
        spanning_tree = new double[count_v][count_v];
        eds = new LinkedList<Ed>();
    }

    public HashMap<String, Double> kruskal(double adjacencyMatrix[][]) {
        finaltree = new HashMap<String, Double>();
        boolean finished = false;
        for (int source = 0; source < count_v; source++) {
            for (int destination = 0; destination < count_v; destination++) {
                if (adjacencyMatrix[source][destination] != MAX_VALUE && source != destination) {
                    Edge edge = new Edge();
                    edge.sourcevertex = source;
                    edge.destinationvertex = destination;
                    edge.weight = adjacencyMatrix[source][destination];
                    adjacencyMatrix[destination][source] = MAX_VALUE;
                    edges.add(edge);
                }
            }
        }
        Collections.sort(edges, new EdgeComparator());
        CheckCycle checkCycle = new CheckCycle();
        for (Edge edge : edges) {
//            System.out.println("Source Vertex: "+edge.sourcevertex);
//            System.out.println("Destination Vertex: "+edge.destinationvertex);
//            System.out.println("Weight: "+edge.weight);
            spanning_tree[edge.sourcevertex][edge.destinationvertex] = (double) edge.weight;
            spanning_tree[edge.destinationvertex][edge.sourcevertex] = (double) edge.weight;

            if (checkCycle.checkCycle1(spanning_tree, edge.sourcevertex)) {
                spanning_tree[edge.sourcevertex][edge.destinationvertex] = 0;
                spanning_tree[edge.destinationvertex][edge.sourcevertex] = 0;
                edge.weight = -1;
                continue;
            }
            visited[edge.sourcevertex] = 1;
            visited[edge.destinationvertex] = 1;
            for (int i = 0; i < visited.length - 1; i++) {
                if (visited[i] == 0) {
                    finished = false;
                    break;
                } else {
                    finished = true;
                }
            }
            if (finished) {
                break;
            }
        }
        System.out.println("The spanning tree is ");
        for (int i = 0; i < count_v; i++) {
            System.out.print("\t" + i);
        }
        System.out.println();
        for (int source = 0; source < count_v; source++) {
            System.out.print(source + "\t");
            for (int destination = 0; destination < count_v; destination++) {
                System.out.print(spanning_tree[source][destination] + "\t");
            }
            System.out.println();
        }

        for (int i = 0; i < count_v; i++) {
            for (int j = 0; j < count_v; j++) {
                if (spanning_tree[i][j] != MAX_VALUE && spanning_tree[i][j] != 0.0 && i != j) {
                    Ed hs = new Ed();
                    hs.source = i;
                    hs.destination = j;
                    hs.weight = spanning_tree[i][j];
                    spanning_tree[j][i] = MAX_VALUE;
                    eds.add(hs);
                }
            }
        }
        for (Ed edges : eds) {
            System.out.println("Source Vertex: " + edges.source);
            System.out.println("Destination Vertex: " + edges.destination);
            System.out.println("Weight: " + edges.weight);
            finaltree.put(edges.source + "-" + edges.destination, edges.weight);
        }
        return finaltree;
    }
}

class Edge {

    int sourcevertex;
    int destinationvertex;
    double weight;
}

class EdgeComparator implements Comparator<Edge> {

    @Override
    public int compare(Edge edge1, Edge edge2) {
        if (edge1.weight < edge2.weight) {
            return -1;
        }
        if (edge1.weight > edge2.weight) {
            return 1;
        }
        return 0;
    }
}

class CheckCycle {

    private Stack<Integer> stack;
    private double adjacencyMatrix[][];

    public CheckCycle() {
        stack = new Stack<Integer>();
    }

    public boolean checkCycle1(double adjacency_matrix[][], int source) {
        boolean cyclepresent = false;
        int number_of_nodes = adjacency_matrix[source].length;
//        System.out.println("Source is: "+source);
        adjacencyMatrix = new double[number_of_nodes][number_of_nodes];
        for (int sourcevertex = 0; sourcevertex < number_of_nodes; sourcevertex++) {
            for (int destinationvertex = 0; destinationvertex < number_of_nodes; destinationvertex++) {
//                System.out.print("\t"+adjacency_matrix[sourcevertex][destinationvertex]);
                adjacencyMatrix[sourcevertex][destinationvertex] = adjacency_matrix[sourcevertex][destinationvertex];
            }
//            System.out.println("");
        }
//        System.out.println("");

        int visited[] = new int[number_of_nodes];
        int element = source;
        int i = source;
        visited[source] = 1;
        stack.push(source);

        while (!stack.isEmpty()) {
            element = stack.peek();
            i = element;
            while (i < number_of_nodes) {
                if (adjacencyMatrix[element][i] > 0 && visited[i] == 1) {
                    if (stack.contains(i)) {
                        cyclepresent = true;
                        return cyclepresent;
                    }
                }
                if (adjacencyMatrix[element][i] > 0 && visited[i] == 0) {
                    stack.push(i);
                    visited[i] = 1;
                    adjacencyMatrix[element][i] = 0;// mark as labelled;
                    adjacencyMatrix[i][element] = 0;
                    element = i;
                    i = 1;
                    continue;
                }
                i++;
            }
            stack.pop();
        }
        return cyclepresent;
    }
}

class Ed {

    int source, destination;
    double weight;
}

