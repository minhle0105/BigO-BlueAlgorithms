package BellmanFord;

import java.util.Arrays;
import java.util.Scanner;

public class Maelstrom {

    private static boolean bellmanFord(Edge[] graph, int src, int[] distance, int[] path, int numberOfVertice) {
        distance[src] = 0;
        for (int i = 1; i <= numberOfVertice - 1; i++) {
            for (Edge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                int w = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    path[v] = u;
                }
            }
        }
        for (Edge edge : graph) {
            int u = edge.source;
            int v = edge.target;
            int w = edge.weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int[][] matrix = new int[n+1][n+1];
        int row = 1;
        int edgeCount = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int r = 0; r < row; r++) {
                String input = sc.next();
                if (input.equals("x")) {
                    matrix[row + 1][r + 1] = -1;
                }
                else {
                    matrix[row + 1][r + 1] = Integer.parseInt(input);
                    edgeCount++;
                }
            }
            row++;
        }

        Edge[] graph = new Edge[edgeCount * 2];
        int count = 0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != -1) {
                    graph[count++] = new Edge(i, j , matrix[i][j]);
                    graph[count++] = new Edge(j, i, matrix[i][j]);
                }
            }
        }

        int[] distance = new int[n + 1];
        int[] path = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        bellmanFord(graph, 1, distance, path, n);
        int result = Integer.MIN_VALUE;
        for (int d : distance) {
            if (d != Integer.MAX_VALUE && d > result) {
                result = d;
            }
        }
        System.out.println(result);
        sc.close();
    }

}
