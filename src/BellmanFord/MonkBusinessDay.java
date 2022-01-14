package BellmanFord;

import java.util.Arrays;
import java.util.Scanner;

public class MonkBusinessDay {

    public static boolean bellmanFord(Edge[] graph, int src, int[] distance, int[] path, int numberOfVertice) {
        distance[src] = 0;
        for (int i = 1; i <= numberOfVertice - 1; i++) {
            for (Edge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                int w = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    path[v] = u;
                }
            }
        }
        for (Edge edge : graph) {
            int u = edge.source;
            int v = edge.target;
            int w = edge.weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        String[] results = new String[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int n = Integer.parseInt(sc.next());
            int m = Integer.parseInt(sc.next());
            Edge[] graph = new Edge[m];
            int[] distance = new int[n+1];
            int[] path = new int[n+1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            Arrays.fill(path, -1);
            for (int i = 0; i < m; i++) {
                int src = Integer.parseInt(sc.next());
                int dst = Integer.parseInt(sc.next());
                int cost = Integer.parseInt(sc.next());
                Edge edge = new Edge(src, dst, -cost);
                graph[i] = edge;
            }
            boolean result = bellmanFord(graph, 1, distance, path, n);
            if (result) {
                results[test] = "No";
            }
            else {
                results[test] = "Yes";
            }
        }
        for (String result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
