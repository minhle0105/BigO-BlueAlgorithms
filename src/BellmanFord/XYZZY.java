package BellmanFord;

import java.util.*;

public class XYZZY {

    public static boolean bellmanFord(Edge[] graph, int src, int[] distance, int[] path, int numberOfVertice, Set<Integer> infinite) {
        distance[src] = 100;
        for (int i = 1; i <= numberOfVertice - 1; i++) {
            for (Edge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                int w = edge.weight;
                if (distance[u] != Integer.MIN_VALUE && distance[u] + w > distance[v] && distance[u] > 0) {
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
                if ( distance[u] != Integer.MIN_VALUE && (distance[u] + w > distance[v] | distance[u] == Integer.MAX_VALUE)  && distance[u] > 0) {
                    distance[v] = Integer.MAX_VALUE;
                    distance[u] = Integer.MAX_VALUE;
                    infinite.add(v);
                    infinite.add(u);
                }
            }
        }

        return infinite.size() == 0;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> results = new ArrayList<>();
        while (true) {
            int n = Integer.parseInt(sc.next());
            if (n == -1) break;
            List<List<Integer>> roomGraph = new ArrayList<>();
            List<Edge> graph = new ArrayList<>();
            for (int i = 0; i < n+1; i++) {
                roomGraph.add(new ArrayList<>());
            }
            int[] energies = new int[n+1];
            for (int i = 1; i < n + 1; i++) {
                int energy = Integer.parseInt(sc.next());
                energies[i] = energy;
                int numberOfConnections = Integer.parseInt(sc.next());
                for (int j = 0; j < numberOfConnections; j++) {
                    roomGraph.get(i).add(Integer.parseInt(sc.next()));
                }
            }
            for (int i = 1; i < roomGraph.size(); i++) {
                for (int j = 0; j < roomGraph.get(i).size(); j++) {
                    Edge edge = new Edge(i, roomGraph.get(i).get(j), energies[roomGraph.get(i).get(j)]);
                    graph.add(edge);
                }
            }
            Edge[] graphs = new Edge[graph.size()];
            for (int i = 0; i < graph.size(); i++) {
                graphs[i] = graph.get(i);
            }
            int[] distance = new int[n+1];
            int[] path = new int[n+1];
            Arrays.fill(distance, Integer.MIN_VALUE);
            Arrays.fill(path, -1);
            Set<Integer> infinite = new HashSet<>();
            boolean result = bellmanFord(graphs, 1, distance, path, n, infinite);

            if (infinite.contains(n)) {
                results.add("winnable");
            }
            else {
                if (distance[n] > 0) {
                    results.add("winnable");
                }
                else {
                    results.add("hopeless");
                }
            }

        }
        for (String result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
