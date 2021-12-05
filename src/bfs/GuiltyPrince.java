package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GuiltyPrince {
    static char prince = '@';
    static char land = '.';
    private static int solution (char[][] map, int[][] visited, Queue<Integer> queue, int numberOfRow, int numberOfColumn) {
        int count = 1;
        int[] dR = {-1,1,0,0};
        int[] dC = {0,0,-1,1};
        for (int i = 0; i < numberOfRow; i++) {
            for (int j = 0; j < numberOfColumn; j++) {
                if (map[i][j] == prince) {
                    queue.add(i);
                    queue.add(j);
                }
            }
        }
        while (!queue.isEmpty()) {
            int i = queue.remove();
            int j = queue.remove();
            for (int direction = 0; direction < 4; direction++) {
                int p = i + dR[direction];
                int q = j + dC[direction];
                if ((p >= 0 && p < numberOfRow) && (q >= 0 && q < numberOfColumn) && map[p][q] == land) {
                    if (visited[p][q] == 0) {
                        visited[p][q] = 1;
                        queue.add(p);
                        queue.add(q);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        String[] results = new String[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            Queue<Integer> queue = new LinkedList<>();
            int column = Integer.parseInt(sc.next());
            int row = Integer.parseInt(sc.next());
            int[][] visited = new int[row][column];
            char[][] map = new char[row][column];
            for (int r = 0; r < row; r++) {
                String thisRowS = sc.next();
                map[r] = thisRowS.toCharArray();
            }
            int steps = solution(map, visited, queue, row, column);
            String result = "Case " + (test + 1) + ": " + steps;
            results[test] = result;
        }
        for (String result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
