package Prim;

import java.util.*;

class Node implements Comparable<Node> {
    long id;
    long dist;

    public Node(long id, long dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.dist, o.dist);
    }
}

public class MinSpanningTree {

    private static long[] dist;
    private static long[] path;
    private static boolean[] visited;


    private static void prims(int src, List<List<Node>> graph) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        int n = graph.size() + 1;
        dist = new long[n];
        path = new long[n];
        visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        heap.add(new Node(src, 0));
        dist[src] = 0;
        while (!heap.isEmpty()) {
            Node top = heap.remove();
            long u = top.id;
            visited[(int) u] = true;
            for (int i = 0; i < graph.get((int) u).size(); i++) {
                Node neighbor = graph.get((int) u).get(i);
                long v = neighbor.id;
                long cost = neighbor.dist;
//                if (dist[u] != cost) {
//                    continue;
//                }
                if (!visited[(int) v] && cost < dist[(int) v]) {
                    dist[(int) v] = cost;
                    heap.add(new Node(v, cost));
                    path[(int) v] = u;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int m = Integer.parseInt(sc.next());
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = Integer.parseInt(sc.next());
            int v = Integer.parseInt(sc.next());
            int dist = Integer.parseInt(sc.next());
            graph.get(u).add(new Node(v, dist));
            graph.get(v).add(new Node(u, dist));
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
