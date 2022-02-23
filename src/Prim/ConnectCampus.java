package Prim;

import java.util.*;

class Building implements Comparable<Building> {
    int id;
    int x;
    int y;
    double dist;

    public Building(int id, int x, int y) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public Building(int id, int x, int y, double dist) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    @Override
    public int compareTo(Building o) {
        return Double.compare(this.dist, o.dist);
    }
}

public class ConnectCampus {

    private static double[] dist;
    private static long[] path;
    private static boolean[] visited;

    private static void prims(int src, List<List<Building>> graph) {
        PriorityQueue<Building> heap = new PriorityQueue<>();
        int n = graph.size();
        dist = new double[n];
        path = new long[n];
        visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        heap.add(new Building(src, 0, 0, 0));
        dist[src] = 0;
        while (!heap.isEmpty()) {
            Building top = heap.remove();
            int u = top.id;
            visited[u] = true;
            for (int i = 0; i < graph.get((int) u).size(); i++) {
                Building neighbor = graph.get((int) u).get(i);
                int v = neighbor.id;
                double cost = neighbor.dist;
                if (!visited[v] && cost < dist[v]) {
                    dist[v] = cost;
                    heap.add(new Building(v, neighbor.x, neighbor.y, cost));
                    path[v] = u;
                }
            }
        }
    }


    private static double getDistance(Building b1, Building b2) {
        double a = Math.pow(b1.x, 2) - Math.pow(b2.x, 2);
        double b = Math.pow(b1.y, 2) - Math.pow(b2.y, 2);
        return Math.sqrt(a + b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Building>> graph = new ArrayList<>();
        List<Building> buildings = new ArrayList<>();
        int n = Integer.parseInt(sc.next());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(sc.next());
            int y = Integer.parseInt(sc.next());
            Building building = new Building(i + 1, x, y);
            buildings.add(building);
        }

        for (int i = 0; i < buildings.size() + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < buildings.size() - 1; i++) {
            for (int j = i + 1; j < buildings.size(); j++) {
                Building b1 = buildings.get(i);
                Building b2 = buildings.get(j);
                double distance = getDistance(b1, b2);
                b1.dist = distance;
                b2.dist = distance;
                graph.get(b1.id).add(b2);
                graph.get(b2.id).add(b1);
            }
        }

        int m = Integer.parseInt(sc.next());
        for (int i = 0; i < m; i++) {
            int a = Integer.parseInt(sc.next());
            int b = Integer.parseInt(sc.next());
            graph.get(a).add(new Building(b, 0, 0, 0));
            graph.get(b).add(new Building(a, 0, 0, 0));
        }
        prims(1, graph);
        double result = 0;
        for (int i = 1; i < dist.length; i++) {
            result += dist[i];
        }
        System.out.println(result);

        sc.close();
    }
}
