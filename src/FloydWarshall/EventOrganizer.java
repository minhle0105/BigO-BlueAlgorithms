package FloydWarshall;

import java.util.Scanner;

public class EventOrganizer {
    public static boolean floydWarshall(int[][] graph, int[][] dist, int[][] path, int[] profit) {
        int V = graph.length;
        for (int i = 1; i < V; i++) {
            for (int j = 1; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != Integer.MIN_VALUE & i != j) {
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
                    int distIK = dist[i][k];
                    int distKJ = dist[k][j];
                    int alternativeDistance = distIK + distKJ;
                    int originalDistance = dist[i][j];
                    if (distIK > Integer.MIN_VALUE && distKJ > Integer.MIN_VALUE && alternativeDistance > originalDistance) {
                        dist[i][j] = alternativeDistance;
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
        int numberOfTest = Integer.parseInt(sc.next());
        int[] results = new int[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int numberOfEvents = Integer.parseInt(sc.next());
            int[] profitFromEach = new int[numberOfEvents];
            int[][] graph = new int[49][49];
            for (int i = 0; i < numberOfEvents; i++) {
                int start = Integer.parseInt(sc.next());
                int end = Integer.parseInt(sc.next());
                int profit = Integer.parseInt(sc.next());
                profitFromEach[i] = profit;
                graph[start][end] = profit;
            }

            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (graph[i][j] == 0 && i != j) {
                        graph[i][j] = Integer.MIN_VALUE;
                    }
                }
            }

            int[][] distance = new int[49][49];
            int[][] path = new int[49][49];
            floydWarshall(graph, distance, path, profitFromEach);

            int individualMax = Integer.MIN_VALUE;
            for (int i : profitFromEach) {
                if (i > individualMax) {
                    individualMax = i;
                }
            }
            int sumMax = Integer.MIN_VALUE;
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance[i].length; j++) {
                    if (distance[i][j] > sumMax && distance[i][j] != Integer.MIN_VALUE) {
                        sumMax = distance[i][j];
                    }
                }
            }
            results[test] = Math.max(individualMax, sumMax);
        }
        for (int result : results) {
            System.out.println(result);
        }


        sc.close();
    }
}
