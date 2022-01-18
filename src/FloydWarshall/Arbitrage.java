package FloydWarshall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arbitrage {

    private static boolean floydWarshall(double[][] graph, double[][] dist, int[][] path) {
        int V = graph.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != Double.MIN_VALUE & i != j) {
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
                    if (dist[i][k] * dist[k][j] > dist[i][j]) {
                        dist[i][j] = dist[i][k] * dist[k][j];
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
        int testCount = 1;
        while (true) {
            int n = Integer.parseInt(sc.next());
            if (n == 0) break;
            List<String> currencies = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String currency = sc.next();
                currencies.add(currency);
            }
            double[][] graph = new double[n][n];
            int connections = Integer.parseInt(sc.next());
            for (int c = 0; c < connections; c++) {
                String currency1 = sc.next();
                double rate = Double.parseDouble(sc.next());
                String currency2 = sc.next();
                int i = currencies.indexOf(currency1);
                int j = currencies.indexOf(currency2);
                graph[i][j] = rate;
            }
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (i != j && graph[i][j] == 0) {
                        graph[i][j] = Double.MIN_VALUE;
                    }
                }
            }
            double[][] distance = new double[n][n];
            int[][] path = new int[n][n];
            boolean result = floydWarshall(graph, distance, path);
            boolean isYes = false;
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance[i].length; j++) {
                    if (i == j && distance[i][j] > 1) {
                        isYes = true;
                        break;
                    }
                }
            }
            if (!isYes) {
                System.out.println("Case " + testCount + ": No");
            }
            else {
                System.out.println("Case " + testCount + ": Yes");
            }
            testCount++;
        }
        sc.close();
    }
}
