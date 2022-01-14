package BellmanFord;

import java.util.*;

class ShortestPathEdge {
    int source;
    int target;
    int weight;

    public ShortestPathEdge(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }
}

public class ShortestPathNegWeight {

    public static boolean bellmanFord(ShortestPathEdge[] graph, int src, int[] distance, int[] path, int numberOfVertice, Set<Integer> possibleNegative) {
        distance[src] = 0;
        for (int i = 1; i <= numberOfVertice - 1; i++) {
            for (ShortestPathEdge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                int w = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    path[v] = u;
                }
            }
        }
        for (int i = 1; i <= numberOfVertice - 1; i++) {
            for (ShortestPathEdge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                int w = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    possibleNegative.add(u);
                    possibleNegative.add(v);
                }
            }
        }

        return possibleNegative.size() == 0;

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<String>> results = new ArrayList<>();
        while (true) {
            int n = Integer.parseInt(sc.next());
            int m = Integer.parseInt(sc.next());
            int q = Integer.parseInt(sc.next());
            int s = Integer.parseInt(sc.next());
            if (n == 0) {
                break;
            }
            List<String> thisResult = new ArrayList<>();
            ShortestPathEdge[] graph = new ShortestPathEdge[m];
            int[] distance = new int[n];
            int[] path = new int[n];
            Arrays.fill(path, -1);
            Arrays.fill(distance, Integer.MAX_VALUE);
            for (int i = 0; i < m; i++) {
                int src = Integer.parseInt(sc.next());
                int dst = Integer.parseInt(sc.next());
                int weight = Integer.parseInt(sc.next());
                ShortestPathEdge edge = new ShortestPathEdge(src, dst, weight);
                graph[i] = edge;
            }

            Set<Integer> possibleNegative = new HashSet<>();

            bellmanFord(graph, s, distance, path, n, possibleNegative);

            for (int i = 0; i < q; i++) {
                int dst = Integer.parseInt(sc.next());
                if (possibleNegative.contains(dst)) {
                    thisResult.add("-Infinity");
                }
                else {
                    if (distance[dst] == Integer.MAX_VALUE) {
                        thisResult.add("Impossible");
                    }
                    else {
                        thisResult.add(distance[dst] + "");
                    }
                }
            }
            results.add(thisResult);
        }

        for (List<String> result : results) {
            for (String r : result) {
                System.out.println(r);
            }
            System.out.println();
        }
        sc.close();
    }
}
