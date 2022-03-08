package finalExam;

import java.util.*;

class FreckleNode implements Comparable<FreckleNode> {
    int id;
    double dist;

    public FreckleNode(int id, double dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(FreckleNode o) {
        return Double.compare(this.dist, o.dist);
    }
}

public class Freckles {

    private static double[] dist;
    private static long[] path;
    private static boolean[] visited;

    private static void prims(int src, List<List<FreckleNode>> graph) {
        PriorityQueue<FreckleNode> heap = new PriorityQueue<>();
        int n = graph.size();
        dist = new double[n];
        path = new long[n];
        visited = new boolean[n];
        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        heap.add(new FreckleNode(src, 0));
        dist[src] = 0;
        while (!heap.isEmpty()) {
            FreckleNode top = heap.remove();
            int u = top.id;
            visited[u] = true;
            for (int i = 0; i < graph.get(u).size(); i++) {
                FreckleNode neighbor = graph.get(u).get(i);
                int v = neighbor.id;
                double cost = neighbor.dist;
                if (!visited[v] && cost < dist[v]) {
                    dist[v] = cost;
                    heap.add(new FreckleNode(v, cost));
                    path[v] = u;
                }
            }
        }
    }

    private static double getDistance(double[] point1, double[] point2) {
        double x1 = point1[0];
        double x2 = point2[0];
        double y1 = point1[1];
        double y2 = point2[1];
        return Math.sqrt( Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2) );
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = Integer.parseInt(sc.next());
        sc.nextLine();
        double[] results = new double[test];
        for (int t = 0; t < test; t++) {
            List<List<FreckleNode>> graph = new ArrayList<>();
            int n = Integer.parseInt(sc.next());
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            double[][] points = new double[n][2];
            for (int i = 0; i < n; i++) {
                double a = Double.parseDouble(sc.next());
                double b = Double.parseDouble(sc.next());
                points[i] = new double[]{a, b};
            }
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    double distance = getDistance(points[i], points[j]);
                    graph.get(i).add(new FreckleNode(j, distance));
                    graph.get(j).add(new FreckleNode(i, distance));
                }
            }
            prims(0, graph);
            double sum = 0;
            for (double d : dist) {
                if (d != Double.MAX_VALUE) {
                    sum += d;
                }
            }
            results[t] = sum;
            sc.nextLine();
        }
        for (double result : results) {
            System.out.printf("%.2f", result);
            System.out.println();
        }
    }
}
