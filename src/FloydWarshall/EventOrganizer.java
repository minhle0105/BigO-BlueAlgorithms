package FloydWarshall;

import java.util.Scanner;

public class EventOrganizer {
    public static boolean floydWarshall(int[][] graph, int[][] dist, int[][] path, int[] profit) {
        int V = graph.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != Integer.MIN_VALUE & i != j) {
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
                    int distIK = dist[i][k];
                    int distKJ = dist[k][j];
                    int alternativeDistance = distIK + distKJ;
                    int originalDistance = dist[i][j];
                    // Nếu k nằm giữa i với j thì mới tạo ra khoảng thời gian hợp lệ
                    if (alternativeDistance > originalDistance && k>=i && k<=j) {
                        dist[i][j] = alternativeDistance;
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
        int[] results = new int[numberOfTest];

        for (int test = 0; test < numberOfTest; test++) {
            int numberOfEvents = Integer.parseInt(sc.next());
            int[] profitFromEach = new int[numberOfEvents];
            int[][] graph = new int[49][49];

            for (int i = 0; i < numberOfEvents; i++) {
                int start = Integer.parseInt(sc.next());
                int end = Integer.parseInt(sc.next());
                int profit = Integer.parseInt(sc.next());
                profitFromEach[i] = profit;
                if(profit > graph[start][end]) {
                    graph[start][end] = profit;
                }
            }

            // Chỗ nay bỏ đi là do mỗi cái gragh[i][j] nếu không có cạnh nối
            // thì cho nó bằng 0
            // như vậy thì mình mới nối được

            // for (int i = 0; i < graph.length; i++) {
            //     for (int j = 0; j < graph[i].length; j++) {
            //         if (graph[i][j] == 0 && i != j) {
            //             graph[i][j] = Integer.MIN_VALUE;
            //         }
            //     }
            // }

            int[][] distance = new int[49][49];
            int[][] path = new int[49][49];
            floydWarshall(graph, distance, path, profitFromEach);

            // Chỗ này thực ra không cần

            // int individualMax = Integer.MIN_VALUE;
            // for (int i : profitFromEach) {
            //     if (i > individualMax) {
            //         individualMax = i;
            //     }
            // }

            int sumMax = Integer.MIN_VALUE;
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance[i].length; j++) {
                    if (distance[i][j] > sumMax) {
                        sumMax = distance[i][j];
                    }
                }
            }

            results[test] = sumMax;
        }


        for (int result : results) {
            System.out.println(result);
        }


        sc.close();
    }
}
