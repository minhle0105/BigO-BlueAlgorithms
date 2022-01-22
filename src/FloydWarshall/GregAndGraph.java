package FloydWarshall;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GregAndGraph {

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Integer> removedPoints = new HashSet<>();
        int numberOfPoints = Integer.parseInt(sc.next());
        int[][] graph = new int[numberOfPoints][numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            for (int j = 0; j < numberOfPoints; j++) {
                int a = Integer.parseInt(sc.next());
                graph[i][j] = a;
            }
        }
        int[] deletedPoint = new int[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            deletedPoint[i] = Integer.parseInt(sc.next()) - 1;
        }
        int[] results = new int[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            int[][] distance = new int[numberOfPoints][numberOfPoints];
            int[][] path = new int[numberOfPoints][numberOfPoints];
            floydWarshall(graph, distance, path);
            int sum = 0;
            for (int a = 0; a < distance.length; a++) {
                for (int b = 0; b < distance[a].length; b++) {
                    if (!removedPoints.contains(a) && !removedPoints.contains(b)) {
                        sum += distance[a][b];
                    }
                }
            }
            results[i] = sum;
            int deleted = deletedPoint[i];
            removedPoints.add(deleted);
            Arrays.fill(graph[deleted], Integer.MAX_VALUE);
            for (int[] row : graph) {
                row[deleted] = Integer.MAX_VALUE;
            }
            graph[deleted][deleted] = 0;
        }
        for (int result : results) {
            System.out.print(result + " ");
        }
        sc.close();
    }
}
