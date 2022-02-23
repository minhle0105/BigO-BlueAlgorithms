package Prim;

import java.util.*;

class ACMNode implements Comparable<ACMNode> {
    int id;
    int dist;

    public ACMNode(int id, int dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(ACMNode o) {
        return Integer.compare(this.dist, o.dist);
    }
}

public class ACMContest {

    private static long[] dist;
    private static long[] path;
    private static boolean[] visited;

    private static List<List<ACMNode>> prims(int src, List<List<ACMNode>> graph) {
        PriorityQueue<ACMNode> heap = new PriorityQueue<>();
        int n = graph.size();
        dist = new long[n];
        path = new long[n];
        visited = new boolean[n];
        List<List<ACMNode>> minSpanTree = new ArrayList<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        for (int k = 0; k < visited.length; k++) {
            if (!visited[k]) {
                heap.add(new ACMNode(k, 0));
                dist[src] = 0;
                while (!heap.isEmpty()) {
                    ACMNode top = heap.remove();
                    int u = top.id;
                    visited[u] = true;
                    for (int i = 0; i < graph.get((int) u).size(); i++) {
                        ACMNode neighbor = graph.get((int) u).get(i);
                        int v = neighbor.id;
                        int cost = neighbor.dist;
                        if (!visited[v] && cost < dist[v]) {
                            dist[v] = cost;
                            heap.add(new ACMNode(v, cost));
                            path[v] = u;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            minSpanTree.add(new ArrayList<>());
        }
        for (int i = path.length - 1; i > 1; i--) {
            int srcNode = (int) path[i];
            if (srcNode == -1) {
                continue;
            }
            minSpanTree.get(srcNode).add(new ACMNode(i, (int) dist[i]));
            minSpanTree.get(i).add(new ACMNode(srcNode, (int) dist[i]));
        }
        return minSpanTree;
    }

    private static int prims(int src, List<List<ACMNode>> graph, int edgeSrc, int edgeDst, int edgeDistance) {
        PriorityQueue<ACMNode> heap = new PriorityQueue<>();
        int n = graph.size();
        dist = new long[n];
        path = new long[n];
        visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        heap.add(new ACMNode(src, 0));
        dist[src] = 0;
        while (!heap.isEmpty()) {
            ACMNode top = heap.remove();
            int u = top.id;
            visited[u] = true;
            for (int i = 0; i < graph.get(u).size(); i++) {
                ACMNode neighbor = graph.get(u).get(i);
                if (((top.id == edgeSrc && neighbor.id == edgeDst) || (top.id == edgeDst && neighbor.id == edgeSrc)) && neighbor.dist == edgeDistance) {
                    continue;
                }
                int v = neighbor.id;
                int cost = neighbor.dist;
                if (!visited[v] && cost < dist[v]) {
                    dist[v] = cost;
                    heap.add(new ACMNode(v, cost));
                    path[v] = u;
                }
            }
        }

        for (int i = 2; i < visited.length; i++) {
            if (!visited[i]) {
                return Integer.MAX_VALUE;
            }
        }
        int result = 0;
        for (long l : dist) {
            if (l != Integer.MAX_VALUE) {
                result += (int) l;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        List<int[]> results = new ArrayList<>();
        for (int test = 0; test < numberOfTest; test++){
            int[] result = new int[2];
            int n = Integer.parseInt(sc.next());
            int m = Integer.parseInt(sc.next());
            List<List<ACMNode>> graph = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                int a = Integer.parseInt(sc.next());
                int b = Integer.parseInt(sc.next());
                int cost = Integer.parseInt(sc.next());
                graph.get(a).add(new ACMNode(b, cost));
                graph.get(b).add(new ACMNode(a, cost));
            }
            List<List<ACMNode>> minSpanTree1 = prims(1, graph);
            int d1 = 0;
            for (int i = 0; i < dist.length; i++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    d1 += dist[i];
                }
            }
            int d2 = Integer.MAX_VALUE;
            for (int i = 0; i < minSpanTree1.size(); i++) {
                for (int j = 0; j < minSpanTree1.get(i).size(); j++) {
                    int dst = minSpanTree1.get(i).get(j).id;
                    int d2Test = prims(1, graph, i, dst, minSpanTree1.get(i).get(j).dist);
                    if (d2Test < d2 ) {
                        d2 = d2Test;
                    }
                }
            }
            result[0] = d1;
            result[1] = d2;
            results.add(result);
        }

        for (int[] result : results) {
            System.out.println(result[0] + " " + result[1]);
        }
        sc.close();
    }
}
