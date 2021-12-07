package bfs;

import java.util.*;

public class Slink {

    private static List<Integer> solution(int[][] map, int[][] visited, int row, int column) {
        int[] startPoint = new int[2];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] == 1) {
                    startPoint[0] = r;
                    startPoint[1] = c;
                    break;
                }
            }
        }
        List<Integer> width = new ArrayList<>();
        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startPoint[0]);
        queue.add(startPoint[1]);
        visited[0][0] = 1;

        // bắt đầu đếm số vết loang, nếu ô đầu tiên bị loang thì bắt đầu từ 1
        int count = 1;
        while (!queue.isEmpty()) {
            int x = queue.remove();
            int y = queue.remove();
            for (int direction = 0; direction < 4; direction++) {
                int nextX = x + dR[direction];
                int nextY = y + dC[direction];

                // trước hết kiểm tra toạ độ có hợp lệ không
                if ((nextX >= 0 && nextX < row) && (nextY >= 0 && nextY < column)) {
                    // nếu hợp lệ và chưa thăm thì thêm vào queue
                    if (visited[nextX][nextY] == 0) {
                        queue.add(nextX);
                        queue.add(nextY);
                        count++;
                    }
                    // miễn là inbound thì dù có loang hay không cũng là thăm rồi nên đổi thành thăm rồi
                    visited[nextX][nextY] = 1;
                }

            }
        }
        return width;
}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = Integer.parseInt(sc.next());
        int column = Integer.parseInt(sc.next());
        int[][] map = new int[row][column];
        int[][] visited = new int[row][column];
        for (int r = 0; r < row; r++) {
            int[] thisRow = new int[column];
            for (int c = 0; c < column; c++) {
                thisRow[c] = Integer.parseInt(sc.next());
            }
            map[r] = thisRow;
        }
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] == 0) {
                    visited[r][c] = 1;
                }
            }
        }
        solution(map, visited, row, column);
        sc.close();
    }
}
