package Prim;

import java.util.*;

class NetworkNode implements Comparable<NetworkNode> {
    int id;
    int dist;

    public NetworkNode(int id, int dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(NetworkNode o) {
        return Integer.compare(this.dist, o.dist);
    }
}

public class SimulateNetwork {

    private static long[] dist;
    private static long[] path;
    private static boolean[] visited;

    private static void prims(int src, List<List<NetworkNode>> graph) {
        PriorityQueue<NetworkNode> heap = new PriorityQueue<>();
        int n = graph.size();
        dist = new long[n];
        path = new long[n];
        visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        heap.add(new NetworkNode(src, 0));
        dist[src] = 0;
        while (!heap.isEmpty()) {
            NetworkNode top = heap.remove();
            int u = top.id;
            visited[u] = true;
            for (int i = 0; i < graph.get(u).size(); i++) {
                NetworkNode neighbor = graph.get(u).get(i);
                int v = neighbor.id;
                int cost = neighbor.dist;
                if (!visited[v] && cost < dist[v]) {
                    dist[v] = cost;
                    heap.add(new NetworkNode(v, cost));
                    path[v] = u;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int m = Integer.parseInt(sc.next());
        List<List<NetworkNode>> graphs = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graphs.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int src = Integer.parseInt(sc.next());
            int dst = Integer.parseInt(sc.next());
            int cost = Integer.parseInt(sc.next());
            graphs.get(src).add(new NetworkNode(dst, cost));
            graphs.get(dst).add(new NetworkNode(src, cost));
        }
        prims(1, graphs);
        int numberOfOptions = Integer.parseInt(sc.next());
        int[] options = new int[numberOfOptions];
        for (int i = 0; i < numberOfOptions; i++) {
            options[i] = Integer.parseInt(sc.next());
        }
        Arrays.sort(options);
        Arrays.sort(dist);
        int firstPointer = 0;
        int secondPointer = dist.length - 1;
        while (firstPointer < options.length && secondPointer >= 0) {
            if (options[firstPointer] < dist[secondPointer] && dist[secondPointer] != Integer.MAX_VALUE) {
                dist[secondPointer--] = options[firstPointer++];
            }
            else {
                secondPointer--;
            }
        }
        int result = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                result += dist[i];
            }

        }
        System.out.println(result);
        sc.close();
    }
}
