package FloydWarshall;

import java.util.ArrayList;
import java.util.List;

public class SampleCode {
    final static int INF = 1000000000;

    public static void printPath(int s, int t, int[][] path) {
        List<Integer> b = new ArrayList<>();
        while (s != t) {
            b.add(t);
            t = path[s][t];
        }
        b.add(s);
        for (int i = b.size() - 1; i >= 0; i--) {
            System.out.print(b.get(i) + " ");
        }
        System.out.println();
    }

    public static boolean floydWarshall(int[][] graph, int[][] dist, int[][] path) {
        int V = graph.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != INF & i != j) {
                    path[i][j] = i;
                }
                else {
                    path[i][j] = -1;
                }
            }
        }
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                return false;
            }
        }
        return true;
    }
}
