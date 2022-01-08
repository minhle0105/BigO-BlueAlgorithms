package Midterm;

import java.util.*;

public class Bomb {

    private static int bfs(char[][] graph, int[] start, int[] destination) {
        int row = graph.length;
        int column = graph[0].length;
        int[][] levels = new int[row][column];
        int[][] visited = new int[row][column];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start[0]);
        queue.add(start[1]);
        visited[start[0]][start[1]] = 1;
        int[] dR = {0, 0, 1, -1};
        int[] dC = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            int thisPointX = queue.remove();
            int thisPointY = queue.remove();
            for (int direction = 0; direction < 4; direction++) {
                int nextPointX = thisPointX + dR[direction];
                int nextPointY = thisPointY + dC[direction];
                boolean inBound = nextPointX >= 0 && nextPointX < row && nextPointY >= 0 && nextPointY < column;
                if (inBound) {
                    boolean notBombed = graph[nextPointX][nextPointY] != '.';
                    if (notBombed) {
                        if (visited[nextPointX][nextPointY] == 0) {
                            queue.add(nextPointX);
                            queue.add(nextPointY);
                            visited[nextPointX][nextPointY] = 1;
                            levels[nextPointX][nextPointY] = levels[thisPointX][thisPointY] + 1;
                        }
                    }
                }
            }
        }
        return levels[destination[0]][destination[1]];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> results = new ArrayList<>();
        while (true) {
            int numberOfRows = Integer.parseInt(sc.next());
            int numberOfColumns = Integer.parseInt(sc.next());
            if (numberOfRows == 0 & numberOfColumns == 0) {
                break;
            }
            char[][] map = new char[numberOfRows][numberOfColumns];
            for (char[] row : map) {
                Arrays.fill(row, ' ');
            }
            int numberOfRowsWithBombs = Integer.parseInt(sc.next());
            for (int i = 0; i < numberOfRowsWithBombs; i++) {
                int rowWithBombs = Integer.parseInt(sc.next());
                int numberOfBombInRow = Integer.parseInt(sc.next());
                for (int j = 0; j < numberOfBombInRow; j++) {
                    int columnWithBombs = Integer.parseInt(sc.next());
                    map[rowWithBombs][columnWithBombs] = '.';
                }
            }
            int[] start = new int[2];
            int[] dst = new int[2];
            start[0] = Integer.parseInt(sc.next());
            start[1] = Integer.parseInt(sc.next());
            dst[0] = Integer.parseInt(sc.next());
            dst[1] = Integer.parseInt(sc.next());
            results.add(bfs(map, start, dst));
        }
        for (Integer result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
