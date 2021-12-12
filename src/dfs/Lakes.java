package dfs;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Lakes {

    static int[][] visited;
    static final char land = '*';
    static Set<Integer> lakeInfo;
    static final int[] dR = {-1, 1, 0, 0};
    static final int[] dC = {0, 0, -1, 1};

    private static int[] graphIsFull(int[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j] == 0) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    private static int findLakeSize(int[] startPoint, int numberOfRows, int numberOfColumns) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startPoint[1]);
        stack.push(startPoint[0]);
        int count = 1;
        while (!stack.isEmpty()) {
            int thisX = stack.pop();
            int thisY = stack.pop();
            for (int direction = 0; direction < 4; direction++) {
                int nextX = thisX + dR[direction];
                int nextY = thisY + dC[direction];
                boolean inBound = nextX >= 0 && nextX < numberOfRows && nextY >= 0 && nextY < numberOfColumns;
                if (inBound) {
                    if (visited[nextX][nextY] == 0) {
                        visited[nextX][nextY] = 1;
                        stack.push(nextY);
                        stack.push(nextX);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        lakeInfo = new HashSet<>();
        int numberOfRows = Integer.parseInt(sc.next());
        int numberOfColumns = Integer.parseInt(sc.next());
        int finalLakes = Integer.parseInt(sc.next());
        char[][] map = new char[numberOfRows][numberOfColumns];
        visited = new int[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            String thisRow = sc.next();
            map[i] = thisRow.toCharArray();
        }

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (map[i][j] == land || i == 0 || j == 0 || i == numberOfRows - 1 || j == numberOfColumns - 1) {
                    visited[i][j] = 1;
                }
            }
        }

        while (true) {
            int[] nextStartPoint = graphIsFull(visited);
            if (nextStartPoint[0] == -1) {
                break;
            }
            visited[nextStartPoint[0]][nextStartPoint[1]] = 1;
            int lakeSize = findLakeSize(nextStartPoint, numberOfRows, numberOfColumns);
            lakeInfo.add(lakeSize);
        }
        sc.close();
    }

}
