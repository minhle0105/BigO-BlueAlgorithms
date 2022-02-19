package Prim;

import java.util.*;

class MinSpanningTreeNode implements Comparable<MinSpanningTreeNode> {
    long id;
    long dist;

    public MinSpanningTreeNode(long id, long dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(MinSpanningTreeNode o) {
        return Long.compare(this.dist, o.dist);
    }
}

public class MinSpanningTree {

    private static long[] dist;
    private static long[] path;
    private static boolean[] visited;


    private static void prims(int src, List<List<MinSpanningTreeNode>> graph) {
        PriorityQueue<MinSpanningTreeNode> heap = new PriorityQueue<>();
        int n = graph.size() + 1;
        dist = new long[n];
        path = new long[n];
        visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        heap.add(new MinSpanningTreeNode(src, 0));
        dist[src] = 0;
        while (!heap.isEmpty()) {
            MinSpanningTreeNode top = heap.remove();
            long u = top.id;
            visited[(int) u] = true;
            for (int i = 0; i < graph.get((int) u).size(); i++) {
                MinSpanningTreeNode neighbor = graph.get((int) u).get(i);
                long v = neighbor.id;
                long cost = neighbor.dist;
//                if (dist[u] != cost) {
//                    continue;
//                }
                if (!visited[(int) v] && cost < dist[(int) v]) {
                    dist[(int) v] = cost;
                    heap.add(new MinSpanningTreeNode(v, cost));
                    path[(int) v] = u;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int m = Integer.parseInt(sc.next());
        List<List<MinSpanningTreeNode>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = Integer.parseInt(sc.next());
            int v = Integer.parseInt(sc.next());
            int dist = Integer.parseInt(sc.next());
            graph.get(u).add(new MinSpanningTreeNode(v, dist));
            graph.get(v).add(new MinSpanningTreeNode(u, dist));
        }
        prims(1, graph);
        long result = 0;
        for (long d : dist) {
            if (d != Integer.MAX_VALUE) {
                result += d;
            }
        }
        System.out.println(result);
        sc.close();
    }
}
