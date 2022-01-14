package BellmanFord;

import java.util.*;

public class ExtendedTraffic {

    public static boolean bellmanFord(Edge[] graph, int src, int[] distance, int[] path, int numberOfVertice, Set<Integer> infinite) {
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
        for (int i = 1 ; i <= numberOfVertice - 1; i++) {
            for (Edge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                int w = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    infinite.add(v);
                    infinite.add(u);
                }
            }
        }

        return infinite.size() == 0;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        List<List<Integer>> results = new ArrayList<>();
        for (int test = 0; test < numberOfTest; test++) {
            sc.nextLine();
            List<Integer> result = new ArrayList<>();
            int n = Integer.parseInt(sc.next());
            int[] costAtEach = new int[n+1];
            costAtEach[0] = 0;
            for (int i = 1; i < n + 1; i++) {
                costAtEach[i] = Integer.parseInt(sc.next());
            }
            int m = Integer.parseInt(sc.next());
            Edge[] graph = new Edge[m];
            int[] distance = new int[n+1];
            int[] path = new int[n+1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            Arrays.fill(path, -1);
            for (int i = 0; i < m; i++) {
                int src = Integer.parseInt(sc.next());
                int dst = Integer.parseInt(sc.next());
                int cost = (int) Math.pow((costAtEach[dst] - costAtEach[src]), 3);
                graph[i] = new Edge(src, dst, cost);
            }
            Set<Integer> infinite = new HashSet<>();
            bellmanFord(graph, 1, distance, path, n, infinite);
            int q = Integer.parseInt(sc.next());
            for (int i = 0; i < q; i++) {
                int dst = Integer.parseInt(sc.next());
                if (distance[dst] < 3 | distance[dst] == Integer.MAX_VALUE) {
                    result.add(-1);
                }
                else {
                    if (infinite.contains(dst)) {
                        result.add(-1);
                    }
                    else {
                        result.add(distance[dst]);
                    }

                }
            }
            results.add(result);
        }
        int caseCount = 1;
        for (List<Integer> result : results) {
            System.out.printf("Case %s:", caseCount);
            System.out.println();
            for (Integer i : result) {
                if (i == -1) {
                    System.out.println("?");
                }
                else {
                    System.out.println(i);
                }
            }
            caseCount++;
        }
        sc.close();
    }
}
