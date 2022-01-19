package FloydWarshall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AsterixObelix {

    final static int INF = 1000000000;

    public static boolean floydWarshall(int[][] graph, int[][] dist, int[][] path, int[][] maxCostAtEachPoint) {
        int V = graph.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != INF & i != j) {
                    path[i][j] = i;
                }
                else {
                    path[i][j] = -1;
                }
            }
        }
        for (int k = 1; k < V; k++) {
            for (int i = 1; i < V; i++) {
                for (int j = 1; j < V; j++) {
                    if ((dist[i][k] + dist[k][j]) + Math.max(maxCostAtEachPoint[i][k],maxCostAtEachPoint[k][j]) < dist[i][j] + maxCostAtEachPoint[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[k][j];
                        maxCostAtEachPoint[i][j] = Math.max(maxCostAtEachPoint[i][k],maxCostAtEachPoint[k][j]);
                    }
                }
            }
        }
        for (int k = 1; k < V; k++) {
            for (int i = 1; i < V; i++) {
                for (int j = 1; j < V; j++) {
                    if ((dist[i][k] + dist[k][j]) + Math.max(maxCostAtEachPoint[i][k],maxCostAtEachPoint[k][j]) < dist[i][j] + maxCostAtEachPoint[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[k][j];
                        maxCostAtEachPoint[i][j] = Math.max(maxCostAtEachPoint[i][k],maxCostAtEachPoint[k][j]);
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
        List<List<Integer>> results = new ArrayList<>();

        while (true) {
            List<Integer> result = new ArrayList<>();
            int C = Integer.parseInt(sc.next());
            int R = Integer.parseInt(sc.next());
            int Q = Integer.parseInt(sc.next());
            if (C == 0 && R == 0 && Q == 0) {
                break;
            }
            int[] partyCost = new int[C+1];
            int[][] maxCostAtEachPoint = new int[C+1][C+1];
            for (int i = 0; i < C; i++) {
                int cost = Integer.parseInt(sc.next());
                partyCost[i+1] = cost;
            }
            for (int i = 0; i < C+1; i++) {
                for (int j = 0; j < C+1; j++) {
                    maxCostAtEachPoint[i][j] = Math.max(partyCost[i], partyCost[j]);
                }
            }

            int[][] graph = new int[C+1][C+1];
            for (int i = 1; i < R+1; i++) {
                int a = Integer.parseInt(sc.next());
                int b = Integer.parseInt(sc.next());
                int cost = Integer.parseInt(sc.next());
                graph[a][b] = cost;
                graph[b][a] = cost;
            }
            for (int i = 0; i < C+1; i++) {
                for (int j = 0; j < C+1; j++) {
                    if (graph[i][j] == 0 && i != j) {
                        graph[i][j] = INF;
                    }
                }
            }
            int[][] distance = new int[C+1][C+1];
            int[][] path = new int[C+1][C+1];
            floydWarshall(graph, distance, path, maxCostAtEachPoint);
            for (int i = 0; i < Q; i++) {
                int src = Integer.parseInt(sc.next());
                int dst = Integer.parseInt(sc.next());
                if (distance[src][dst] == INF) {
                    result.add(-1);
                }
                else {
                    result.add(distance[src][dst] + maxCostAtEachPoint[src][dst]);
                }
            }
            results.add(result);
        }

        for (int i = 0; i < results.size(); i++) {
            System.out.println("Case #" + (i+1));
            for (Integer result : results.get(i)) {
                System.out.println(result);
            }
            System.out.println();
        }
        sc.close();
    }
}
