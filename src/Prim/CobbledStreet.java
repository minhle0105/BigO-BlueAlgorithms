package Prim;

import java.util.*;

class CobbledNode implements Comparable<CobbledNode> {
    long id;
    long dist;

    public CobbledNode(long id, long dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(CobbledNode o) {
        return Long.compare(this.dist, o.dist);
    }
}

public class CobbledStreet {

    private static long[] dist;
    private static long[] path;
    private static boolean[] visited;

    private static void prims(int src, List<List<CobbledNode>> graph) {
        PriorityQueue<CobbledNode> heap = new PriorityQueue<>();
        int n = graph.size() + 1;
        dist = new long[n];
        path = new long[n];
        visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        heap.add(new CobbledNode(src, 0));
        dist[src] = 0;
        while (!heap.isEmpty()) {
            CobbledNode top = heap.remove();
            long u = top.id;
            visited[(int) u] = true;
            for (int i = 0; i < graph.get((int) u).size(); i++) {
                CobbledNode neighbor = graph.get((int) u).get(i);
                long v = neighbor.id;
                long cost = neighbor.dist;
                if (!visited[(int) v] && cost < dist[(int) v]) {
                    dist[(int) v] = cost;
                    heap.add(new CobbledNode(v, cost));
                    path[(int) v] = u;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        long[] results = new long[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int p = Integer.parseInt(sc.next());
            int n = Integer.parseInt(sc.next());
            int m = Integer.parseInt(sc.next());
            List<List<CobbledNode>> graph = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                int u = Integer.parseInt(sc.next());
                int v = Integer.parseInt(sc.next());
                int cost = Integer.parseInt(sc.next());
                graph.get(u).add(new CobbledNode(v, cost));
                graph.get(v).add(new CobbledNode(u, cost));
            }
            prims(1, graph);
            long result = 0;
            for (long d : dist) {
                if (d != Integer.MAX_VALUE) {
                    result += d;
                }
            }
            results[test] = result * p;
        }
        for (long result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
