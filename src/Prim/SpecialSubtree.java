package Prim;

import java.util.*;

class SubtreeNode implements Comparable<SubtreeNode> {
    int id;
    int dist;

    public SubtreeNode(int id, int dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(SubtreeNode o) {
        return Integer.compare(this.dist, o.dist);
    }
}

public class SpecialSubtree {

    private static long[] dist;
    private static long[] path;
    private static boolean[] visited;

    private static void prims(int src, List<List<SubtreeNode>> graph) {
        PriorityQueue<SubtreeNode> heap = new PriorityQueue<>();
        int n = graph.size();
        dist = new long[n];
        path = new long[n];
        visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        heap.add(new SubtreeNode(src, 0));
        dist[src] = 0;
        while (!heap.isEmpty()) {
            SubtreeNode top = heap.remove();
            int u = top.id;
            visited[u] = true;
            for (int i = 0; i < graph.get(u).size(); i++) {
                SubtreeNode neighbor = graph.get(u).get(i);
                int v = neighbor.id;
                int cost = neighbor.dist;
                if (!visited[v] && cost < dist[v]) {
                    dist[v] = cost;
                    heap.add(new SubtreeNode(v, cost));
                    path[v] = u;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int m = Integer.parseInt(sc.next());
        List<List<SubtreeNode>> graphs = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graphs.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = Integer.parseInt(sc.next());
            int b = Integer.parseInt(sc.next());
            int cost = Integer.parseInt(sc.next());
            graphs.get(a).add(new SubtreeNode(b, cost));
            graphs.get(b).add(new SubtreeNode(a, cost));
        }
        int src = Integer.parseInt(sc.next());
        prims(src, graphs);
        int result = 0;
        for (long d : dist) {
            if (d != Integer.MAX_VALUE) {
                result += d;
            }

        }
        System.out.println(result);
        sc.close();
    }
}
