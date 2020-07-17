/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

/**
 *
 * @author user6
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Kruskal1 {

    static int path[];
    static int i, j;
    double mincost;

    public HashMap<String, Double> kruskal(double adj_matrxi[][], int count) throws InterruptedException {
        HashMap<String, Double> min = new HashMap<String, Double>();
        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);

        // Creating graph of 'n' vertices & 'm' edges

        path = new int[count+1];

        Edge1 e[] = new Edge1[count];
        Edge1 t = new Edge1();

        for (i = 0; i < count; i++) {
            for (j = 0; j < count; j++) {
                e[i] = new Edge1();
//      
                e[i].u = i;
                e[i].v = j;
                e[i].wt = (double) adj_matrxi[i][j];
            }
        }

        // Sorting the edges in ascending order of weights
        for (i = 0; i < count; i++) {
            for (j = 0; j < count - i - 1; j++) {
                if (e[j].wt > e[j + 1].wt) {
                    t = e[j];
                    e[j] = e[j + 1];
                    e[j + 1] = t;
                }
            }
        }

        // Initializing the path array
        for (i = 1; i <= count; i++) {
            path[i] = 0;
        }

        // Counts the number of edges selected in the tree
        i = 0;

        // Counts the number of edges selected or discarded
        j = 0;

        mincost = 0;
        System.out.println();
        while ((i != count - 1) && (j != count)) {
            System.out.print("Edge ("
                    + e[j].u + ", " + e[j].v + ") "
                    + "with weight " + e[j].wt + " ");
            if (checkCycle(e[j])) {
                mincost = mincost + e[j].wt;
                i++;
                String s = e[j].u + "-" + e[j].v;
                double d = e[j].wt;
                min.put(s, d);
                System.out.println("is selected");
            } else {
                System.out.println("is discarded");
            }
            j++;
        }
        if (i != count - 1) {
            System.out.println("Minimum spanning tree cannot be formed ");
        }
        return min;

    }

    public static boolean checkCycle(Edge1 e) {
        int u = e.u, v = e.v;

        while (path[u] > 0) {
            u = path[u];
        }

        while (path[v] > 0) {
            v = path[v];
        }

        if (u != v) {
            path[u] = v;
            return true;
        }
        return false;
    }

    static class Edge1 {

        int u, v;
        double wt;
    }
}