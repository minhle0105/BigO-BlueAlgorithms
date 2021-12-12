package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllIsWell {
    static final String target = "ALLIZZWELL";
    static final int[] dR = {0, 0, 1, -1, -1, -1, 1, 1};
    static final int[] dC = {1, -1, 0, 0, -1, 1, -1, 1};

    private static boolean solution(char[][] map, int numberOfRows, int numberOfColumns, int[] startPoint, int pointer) {
        int[][] visited = new int[numberOfRows][numberOfColumns];
        visited[startPoint[0]][startPoint[1]] = 1;
        return dfs(map, visited, startPoint, 1);
    }

    private static boolean dfs(char[][] map, int[][] visited, int[] startPoint, int pointer) {
        if (pointer == target.length()) {
            return true;
        }
        boolean r = false;
        visited[startPoint[0]][startPoint[1]] = 1;
        for (int i = 0; i < 8; i++) {
            int nextX = startPoint[0] + dR[i];
            int nextY = startPoint[1] + dC[i];
            boolean inBound = nextX >= 0 && nextX < map.length && nextY >= 0 && nextY < map[0].length;
            if (inBound) {
                if (visited[nextX][nextY] == 0 && map[nextX][nextY] == target.charAt(pointer)) {
                    int[] nextStartPoint = new int[]{nextX, nextY};
                    r = r || dfs(map, visited, nextStartPoint, pointer + 1);
                }
            }
        }
        visited[startPoint[0]][startPoint[1]] = 0;
        return r;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        String[] results = new String[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int row = sc.nextInt();
            int column = sc.nextInt();
            char[][] map = new char[row][column];
            for (int i = 0; i < map.length; i++) {
                String thisRow = sc.next();
                map[i] = thisRow.toCharArray();
            }

            int pointer = 0;

            List<int[]> possibleStartPoint = new ArrayList<>();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (map[i][j] == 'A') {
                        possibleStartPoint.add(new int[]{i,j});
                    }
                }
            }

            boolean exists = false;
            for (int[] ints : possibleStartPoint) {
                if (solution(map, row, column, ints, pointer)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                results[test] = "YES";
            }
            else {
                results[test] = "NO";
            }
        }

        for (String result : results) {
            System.out.println(result);
        }


        sc.close();
    }
}
