package bfs;

import java.util.*;

public class ValidateMaze {

    static final char path = '.';

    private static List<int[]> hasEnterAndExit(char[][] maze, int numberOfRows, int numberOfColumns) {
        List<int[]> endPoints = new ArrayList<>();
        if (numberOfRows == 1) {
            int r = 0;
            for (int c = 0; c < numberOfColumns; c++) {
                if (maze[r][c] == path) {
                    endPoints.add(new int[]{r,c});
                }
            }
            return endPoints;
        }
        else if (numberOfColumns == 1) {
            int c = 0;
            for (int r = 0; r < numberOfRows; r++) {
                if (maze[r][c] == path) {
                    endPoints.add(new int[]{r,c});
                }
            }
            return endPoints;
        }
        int[][] borders = new int[numberOfRows + numberOfRows + (numberOfColumns - 2) * 2][2];
        int count = 0;
        for (int i = 0; i < numberOfColumns; i++) {
            int[] point1 = {0, i};
            int[] point2 = {numberOfRows - 1, i};
            borders[count++] = point1;
            borders[count++] = point2;
        }

        for (int j = 1; j < numberOfRows - 1; j++) {
            int[] point1 = {j, 0};
            int[] point2 = {j, numberOfColumns - 1};
            borders[count++] = point1;
            borders[count++] = point2;
        }

        for (int[] border : borders) {
            if (maze[border[0]][border[1]] == path) {
                endPoints.add(border);
            }
        }

        return endPoints;
    }

    private static boolean validate(char[][] maze, int numberOfRows, int numberOfColumns) {
        List<int[]> endPoints = hasEnterAndExit(maze, numberOfRows, numberOfColumns);
        int[][] isVisited = new int[numberOfRows][numberOfColumns];

        if (endPoints.size() != 2) {
            return false;
        }

        int[] firstPoint = endPoints.get(0);
        int[] secondPoint = endPoints.get(1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(firstPoint[0]);
        queue.add(firstPoint[1]);
        isVisited[firstPoint[0]][firstPoint[1]] = 1;
        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int thisPointX = queue.remove();
            int thisPointY = queue.remove();

            for (int direction = 0; direction < 4; direction++) {
                int nextPointX = thisPointX + dR[direction];
                int nextPointY = thisPointY + dC[direction];
                boolean XinBound = nextPointX >= 0 && nextPointX < numberOfRows;
                boolean YinBound = nextPointY >= 0 && nextPointY < numberOfColumns;
                if (XinBound && YinBound) {
                    if (nextPointX == secondPoint[0] && nextPointY == secondPoint[1]) {
                        return true;
                    }
                    boolean isNotBlocked = maze[nextPointX][nextPointY] == path;
                    if (isNotBlocked) {
                        boolean isNotVisited = isVisited[nextPointX][nextPointY] == 0;
                        if (isNotVisited) {
                            isVisited[nextPointX][nextPointY] = 1;
                            queue.add(nextPointX);
                            queue.add(nextPointY);
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        String[] results = new String[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int rows = Integer.parseInt(sc.next());
            int columns = Integer.parseInt(sc.next());
            char[][] maze = new char[rows][columns];
            for (int r = 0; r < rows; r++) {
                String thisRow = sc.next();
                maze[r] = thisRow.toCharArray();
            }
            if (validate(maze, rows, columns)) {
                results[test] = "valid";
            }
            else {
                results[test] = "invalid";
            }
        }
        for (String result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
