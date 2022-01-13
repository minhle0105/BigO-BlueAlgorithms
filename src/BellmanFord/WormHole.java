package BellmanFord;

import java.util.Arrays;
import java.util.Scanner;

class Edge {
    int source;
    int target;
    int weight;

    public Edge(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }
}

public class WormHole {

    private static boolean bellmanFord(Edge[] graph, int src, int[] distance, int[] path, int numberOfVertice) {
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
        for (int test = 0; test < numberOfTest; test++) {
            int numberOfVertice = Integer.parseInt(sc.next());
            int numberOfEdges = Integer.parseInt(sc.next());
            Edge[] graph = new Edge[numberOfEdges];
            int[] distance = new int[numberOfVertice];
            int[] path = new int[numberOfVertice];
            Arrays.fill(distance, Integer.MAX_VALUE);
            Arrays.fill(path, -1);
            for (int i = 0; i < numberOfEdges; i++) {
                int src = Integer.parseInt(sc.next());
                int dst = Integer.parseInt(sc.next());
                int cost = Integer.parseInt(sc.next());
                Edge edge = new Edge(src, dst, cost);
                graph[i] = edge;
            }
            boolean result = bellmanFord(graph, 0, distance, path, numberOfVertice);
            if (result) {
                System.out.println("not possible");
            }
            else {
                System.out.println("possible");
            }

        }
        sc.close();
    }
}
