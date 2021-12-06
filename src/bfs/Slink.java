package bfs;

import java.util.*;

public class Slink {

    private static List<Integer> solution(int[][] map, int row, int column) {
        int[] startPoint = {0, 0};
        List<Integer> width = new ArrayList<>();
        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};
        int[][] visited = new int[row][column];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startPoint[0]);
        queue.add(startPoint[1]);
        visited[0][0] = 1;
        int count = map[startPoint[0]][startPoint[1]] == 1 ? 1 : 0;
        while (!queue.isEmpty()) {
            int x = queue.remove();
            int y = queue.remove();
            if (map[x][y] == 0 && count == 0) {
                count++;
            }
            for (int direction = 0; direction < 4; direction++) {
                int nextX = x + dR[direction];
                int nextY = y + dC[direction];
                if ((nextX >= 0 && nextX < row) && (nextY >= 0 && nextY < column)) {
                    if (visited[nextX][nextY] == 0) {
                        queue.add(nextX);
                        queue.add(nextY);
                        if (map[nextX][nextY] == 1) {
                            count++;
                        } else {
                            width.add(count);
                            count = 0;
                        }
                    }
                    visited[nextX][nextY] = 1;
                }

            }
        }
        return width;
}
//    3 3
//1 1 1
//1 1 0
//1 0 1

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = Integer.parseInt(sc.next());
        int column = Integer.parseInt(sc.next());
        int[][] map = new int[row][column];
        for (int r = 0; r < row; r++) {
            int[] thisRow = new int[column];
            for (int c = 0; c < column; c++) {
                thisRow[c] = Integer.parseInt(sc.next());
            }
            map[r] = thisRow;
        }
        solution(map, row, column);
        sc.close();
    }
}
