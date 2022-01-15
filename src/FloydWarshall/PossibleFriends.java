package FloydWarshall;

import java.util.*;

public class PossibleFriends {
    final static int INF = 1000000000;
    private static boolean floydWarshall(int[][] graph, int[][] dist, int[][] path) {
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        List<List<Integer>> results = new ArrayList<>();
        for (int test = 0; test < numberOfTest; test++) {
            List<Integer> result = new ArrayList<>();
            String firstRow = sc.next();
            int m = firstRow.length();
            int[][] graph = new int[m][m];
            for (int i = 0; i < firstRow.length(); i++) {
                if (i == 0) {
                    graph[0][i] = 0;
                }
                else if (firstRow.charAt(i) == 'N') {
                    graph[0][i] = INF;
                }
                else {
                    graph[0][i] = 1;
                }
            }
            for (int i = 1; i < m; i++) {
                String nextRow = sc.next();
                for (int j = 0; j < m; j++) {
                    if (i == j) {
                        graph[i][j] = 0;
                    }
                    else if (nextRow.charAt(j) == 'N') {
                        graph[i][j] = INF;
                    }
                    else {
                        graph[i][j] = 1;
                    }
                }
            }
            int[][] distance = new int[m][m];
            int[][] path = new int[m][m];
            floydWarshall(graph, distance, path);
            Map<Integer, Integer> countFriends = new HashMap<>();
            for (int i = 0; i < m; i++) {
                countFriends.put(i, 0);
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    if (distance[i][j] == 2) {
                        countFriends.put(i, countFriends.get(i) + 1);
                    }
                }
            }
            int maxNumberOfFriends = Integer.MIN_VALUE;
            int idResult = -1;
            for (Integer id : countFriends.keySet()) {
                if (countFriends.get(id) > maxNumberOfFriends) {
                    maxNumberOfFriends = countFriends.get(id);
                    idResult = id;
                }
            }

            for (Integer id : countFriends.keySet()) {
                if (countFriends.get(id) == maxNumberOfFriends && id < idResult) {
                    idResult = id;
                }
            }
            result.add(idResult);
            result.add(maxNumberOfFriends);
            results.add(result);
        }
        for (List<Integer> result : results) {
            System.out.println(result.get(0) + " " + result.get(1));
        }
        sc.close();
    }
}
