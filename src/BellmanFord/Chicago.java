package BellmanFord;

import java.util.*;

class CityEdge {
    int source;
    int target;
    double weight;

    public CityEdge(int source, int target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public void swap() {
        int temp = this.source;
        this.source = this.target;
        this.target = temp;
    }
}

public class Chicago {

    private static boolean bellmanFord(CityEdge[] graph, int src, double[] distance, int[] path, int numberOfVertice) {
        distance[src] = 1;
        for (int i = 1; i <= numberOfVertice - 1; i++) {
            for (CityEdge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                double w = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] * w < distance[v]) {
                    distance[v] = distance[u] * w;
                    path[v] = u;
                }
            }
        }

        for (CityEdge edge : graph) {
            edge.swap();
        }

        for (int i = 1; i <= numberOfVertice - 1; i++) {
            for (CityEdge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                double w = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] * w < distance[v]) {
                    distance[v] = distance[u] * w;
                    path[v] = u;
                }
            }
        }

        for (CityEdge edge : graph) {
            int u = edge.source;
            int v = edge.target;
            double w = edge.weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] * w < distance[v]) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = Integer.parseInt(sc.next());
            if (n == 0) {
                break;
            }
            int m = Integer.parseInt(sc.next());

            CityEdge[] graph = new CityEdge[m];
            double[] distance = new double[n+1];
            int[] path = new int[n+1];
            Arrays.fill(distance, Double.MAX_VALUE);
            Arrays.fill(path, -1);
            for (int i = 0; i < m; i++) {
                int src = Integer.parseInt(sc.next());
                int dst = Integer.parseInt(sc.next());
                double weight = Double.parseDouble(sc.next());
                CityEdge edge = new CityEdge(src, dst, weight/100);
                graph[i] = edge;
            }
            boolean result = bellmanFord(graph, 1, distance, path, n);
            System.out.println(result);
            for (int i = 1; i < distance.length; i++) {
                distance[i] *= 100;
            }
            System.out.println(distance[n]);


        }


        sc.close();
    }
}
