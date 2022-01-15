package BellmanFord;

import java.util.*;

public class Alice {

    private static boolean bellmanFord(Edge[] graph, int src, long[] distance, int[] path, int numberOfVertice, Set<Integer> infinite) {
        distance[src] = 0;
        for (int i = 1; i <= numberOfVertice - 1; i++) {
            for (Edge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                int w = edge.weight;
                if (distance[u] != Long.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    path[v] = u;
                }
            }
        }
        for (int i = 1; i <= numberOfVertice - 1; i++) {
            for (Edge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                int w = edge.weight;
                if (distance[u] != Long.MAX_VALUE && distance[u] + w < distance[v]) {
                    infinite.add(u);
                    infinite.add(v);
                    distance[v] = Long.MIN_VALUE;
                    distance[u] = Long.MIN_VALUE;
                }
            }
        }
        return infinite.size() == 0;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = 1;
        while (true) {
            int numberOfCities = Integer.parseInt(sc.next());
            if (numberOfCities == 0) break;
            Map<Integer, String> idCities = new HashMap<>();
            List<Edge> graph = new ArrayList<>();
            for (int i = 0; i < numberOfCities; i++) {
                String cityName = sc.next();
                idCities.put(i + 1, cityName);
                for (int j = 0; j < numberOfCities; j++) {
                    int d = Integer.parseInt(sc.next());
                    if (d == 0 & i != j) {
                        continue;
                    }
                    graph.add(new Edge(i+1, j+1, d));
                }
            }
            Edge[] graph1 = new Edge[graph.size()];
            for (int i = 0; i < graph.size(); i++) {
                graph1[i] = graph.get(i);
            }
            long[] distance = new long[numberOfCities + 1];
            int[] path = new int[numberOfCities + 1];
            Arrays.fill(distance, Long.MAX_VALUE);
            Arrays.fill(path, -1);
            int q = Integer.parseInt(sc.next());
            long[][] results = new long[numberOfCities+1][numberOfCities+1];
            for (int i = 1; i < numberOfCities + 1; i++) {
                Set<Integer> infinite = new HashSet<>();
                Arrays.fill(distance, Long.MAX_VALUE);
                Arrays.fill(path, -1);
                boolean r = bellmanFord(graph1, i, distance, path, numberOfCities, infinite);
                if (r) {
                    System.arraycopy(distance, 0, results[i], 0, numberOfCities + 1);
                }
                else {
                    for (int j = 1; j < numberOfCities + 1; j++) {
                        if (infinite.contains(j)) {
                            results[i][j] = Long.MIN_VALUE;
                        }
                        else {
                            results[i][j] = distance[j];
                        }
                    }
                }

            }
            System.out.println("CASE #" + test + ":");
            for (int i = 0; i < q; i++) {
                int src = Integer.parseInt(sc.next());
                int dst = Integer.parseInt(sc.next());
                src += 1;
                dst += 1;
                if (results[src][dst] == Long.MIN_VALUE) {
                    System.out.println("NEGATIVE CYCLE");
                }
                else {
                    if (results[src][dst] == Long.MAX_VALUE) {
                        System.out.println(idCities.get(src) + "-" + idCities.get(dst) + " " + "NOT REACHABLE");
                    }
                    else {
                        System.out.println(idCities.get(src) + "-" + idCities.get(dst) + " " + results[src][dst]);
                    }

                }
            }
            test++;
        }

        sc.close();
    }
}
