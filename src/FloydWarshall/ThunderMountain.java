package FloydWarshall;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ThunderMountain {


    public static boolean floydWarshall(double[][] graph, double[][] dist, int[][] path) {
        int V = graph.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != Double.MAX_VALUE & i != j) {
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
                    if (dist[i][k] + dist[k][j] < dist[i][j] && dist[i][k] < Double.MAX_VALUE && dist[k][j] < Double.MAX_VALUE) {
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

    private static double calculateDistance(int[] x, int[] y) {
        int firstSquare = (x[0] - y[0]) * (x[0] - y[0]);
        int secondSquare = (x[1] - y[1]) * (x[1] - y[1]);
        return Math.sqrt(firstSquare + secondSquare);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        for (int test = 0; test < numberOfTest; test ++){
            int numberOfTowns = Integer.parseInt(sc.next());
            double[][] graph = new double[numberOfTowns][numberOfTowns];
            double[][] distance = new double[numberOfTowns][numberOfTowns];
            int[][] path = new int[numberOfTowns][numberOfTowns];
            int[][] points = new int[numberOfTowns][2];
            for (int i = 0; i < numberOfTowns; i++) {
                int src = Integer.parseInt(sc.next());
                int dst = Integer.parseInt(sc.next());
                int[] point = new int[]{src, dst};
                points[i] = point;
            }
            for (int i = 0; i < points.length - 1; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    double d = calculateDistance(points[i], points[j]);
                    if (d <= 10) {
                        graph[i][j] = d;
                        graph[j][i] = d;
                    }
                }
            }
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (i != j && graph[i][j] == 0) {
                        graph[i][j] = Double.MAX_VALUE;
                    }
                }
            }

            floydWarshall(graph, distance, path);
            double maxDistance = 0;
            boolean sendKurdy = false;
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance[i].length; j++) {
                    if (distance[i][j] == Double.MAX_VALUE) {
                        sendKurdy = true;
                        break;
                    }
                    if (distance[i][j] > maxDistance && distance[i][j] < Double.MAX_VALUE) {
                        maxDistance = distance[i][j];
                    }
                }
            }
            System.out.println("Case #" + (test + 1) + ":");
            if (!sendKurdy) {
                System.out.printf("%.6f", maxDistance);
            }
            else {
                System.out.println("Send Kurdy");
            }
            System.out.println();
        }
        sc.close();
    }
}
