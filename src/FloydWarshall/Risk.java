package FloydWarshall;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Risk {

    public static boolean floydWarshall(int[][] graph, int[][] dist, int[][] path) {
        int V = graph.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != Integer.MAX_VALUE & i != j) {
                    path[i][j] = i;
                }
                else {
                    path[i][j] = -1;
                }
            }
        }
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] < Integer.MAX_VALUE && dist[k][j] < Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int test = 1;
        while (sc.hasNext()) {
            int[][] graph = new int[21][21];
            for (int i = 0; i < 19; i++) {
                int numberOfNeighbors = Integer.parseInt(sc.next());
                for (int n = 0; n < numberOfNeighbors; n++) {
                    int neighbor = Integer.parseInt(sc.next());
                    graph[i+1][neighbor] = 1;
                    graph[neighbor][i+1] = 1;
                }
            }
            Arrays.fill(graph[0], Integer.MAX_VALUE);
            for (int[] row : graph) {
                row[0] = Integer.MAX_VALUE;
            }
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (i == j) {
                        graph[i][j] = 0;
                    }
                    else if (graph[i][j] == 0) {
                        graph[i][j] = Integer.MAX_VALUE;
                    }
                }
            }
            int[][] distance = new int[21][21];
            int[][] path = new int[21][21];
            floydWarshall(graph, distance, path);
            int numberOfQuery = Integer.parseInt(sc.next());
            int[] results = new int[numberOfQuery];
            int[][] information = new int[numberOfQuery][2];
            for (int q = 0; q < numberOfQuery; q++) {
                int src = Integer.parseInt(sc.next());
                int dst = Integer.parseInt(sc.next());
                results[q] = distance[src][dst];
                information[q] = new int[] {src, dst};
            }
            System.out.println();
            System.out.println("Test Set #" + test);
            int count = 0;
            for (int result : results) {
                System.out.format("%2d to %2d: %d\n", information[count][0],information[count++][1],result);
                System.out.println();
            }
            test++;
        }

        sc.close();
    }
}
