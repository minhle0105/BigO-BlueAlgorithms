package FloydWarshall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Professor {


    public static boolean floydWarshall(int[][] graph, int[][] dist, int[][] path) {
        int V = graph.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != Integer.MAX_VALUE & i != j) {
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


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int numberOfCities = Integer.parseInt(sc.next());
            if (numberOfCities == 0) break;
            int[][] graphYoung = new int[26][26];
            int[][] graphOld = new int[26][26];
            for (int i = 0; i < numberOfCities; i++) {
                String oldOrYoung = sc.next();
                String oneWayOrTwoWay = sc.next();
                String src = sc.next();
                String dst = sc.next();
                int cost = Integer.parseInt(sc.next());
                int srcAscii = (int) (src.charAt(0) - 'A');
                int dstAscii = (int) (dst.charAt(0) - 'A');
                if (oldOrYoung.equals("Y")) {
                    if (oneWayOrTwoWay.equals("U")) {
                        graphYoung[srcAscii][dstAscii] = cost;
                    }
                    else {
                        graphYoung[srcAscii][dstAscii] = cost;
                        graphYoung[dstAscii][srcAscii] = cost;
                    }
                }
                else {
                    if (oneWayOrTwoWay.equals("U")) {
                        graphOld[srcAscii][dstAscii] = cost;
                    }
                    else {
                        graphOld[srcAscii][dstAscii] = cost;
                        graphOld[dstAscii][srcAscii] = cost;
                    }
                }
            }
            int[][] distanceYoung = new int[26][26];
            int[][] distanceOld = new int[26][26];
            int[][] pathYoung = new int[26][26];
            int[][] pathOld = new int[26][26];
            for (int i = 0; i < graphOld.length; i++) {
                for (int j = 0; j < graphOld[i].length; j++) {
                    if (i != j && graphOld[i][j] == 0) {
                        graphOld[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            for (int i = 0; i < graphYoung.length; i++) {
                for (int j = 0; j < graphYoung[i].length; j++) {
                    if (i != j && graphYoung[i][j] == 0) {
                        graphYoung[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            floydWarshall(graphOld, distanceOld, pathOld);
            floydWarshall(graphYoung, distanceYoung, pathYoung);
            int smallestValue = Integer.MAX_VALUE;
            String dst = "";
            String startYoung = sc.next();
            String startOld = sc.next();

            for (int i = 0; i < distanceOld.length; i++) {
                if (distanceOld[(int) (startOld.charAt(0) - 'A')][i] < Integer.MAX_VALUE &&
                        distanceYoung[(int) (startYoung.charAt(0) - 'A')][i] < Integer.MAX_VALUE &&
                        distanceOld[(int) (startOld.charAt(0) - 'A')][i] + distanceYoung[(int) (startYoung.charAt(0) - 'A')][i] < smallestValue) {
                    smallestValue = distanceOld[(int) (startOld.charAt(0) - 'A')][i] + distanceYoung[(int) (startYoung.charAt(0) - 'A')][i];
                    dst = String.valueOf((char) (i + 'A'));
                }
            }
            if (!dst.equals("")) {
                System.out.println(smallestValue + " " + dst);
            }
            else {
                System.out.println("You will never meet.");
            }


        }
        sc.close();
    }
}
