package Prim;

import java.util.*;
import java.lang.*;

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

    public Building(Building b) {
        this.id = b.id;
        this.x = b.x;
        this.y = b.y;
        this.dist = b.dist;
    }

    @Override
    public int compareTo(Building o) {
        return Double.compare(this.dist, o.dist);
    }
}

/* Name of the class has to be "Main" only if the class is public. */
class s
{
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
        // Ở đây hôm bữa dùng nhầm cái khoảng cách
        double a = Math.pow(b1.x - b2.x, 2);
        double b = Math.pow(b1.y - b2.y, 2);
        return Math.sqrt(a + b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
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

            for (int i = 0; i < buildings.size(); i++) {
                for (int j = i + 1; j < buildings.size(); j++) {
                    // Em new luôn một building mới ở đây do nếu không new
                    // thì cái b1 và b2 nó lại chỉ là reference của buildings.get(i)
                    // và buildings.get(j). Khi mình thay đổi b1.dist và b2.dist thì nó
                    // sẽ đổi luôn ở trong cái mảng buildings
                    Building b1 = new Building(buildings.get(i));
                    Building b2 = new Building(buildings.get(j));
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
            System.out.format("%.2f", result);
            System.out.println();
        }
        sc.close();
    }
}
