package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class AllIsWell {
    static final String target = "ALLIZZWELL";

    private static boolean solution(char[][] map, int numberOfRows, int numberOfColumns) {
        int[][] visited = new int[map.length][map[0].length];
        int currentPointer = 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(0);
        visited[0][0] = 1;
        int[] dR = {0, 0, 1, -1, -1, -1, 1, 1};
        int[] dC = {1, -1, 0, 0, -1, 1, -1, 1};
        while (!stack.isEmpty()) {
            int thisRow = stack.pop();
            int thisColumn = stack.pop();
            boolean foundNext = false;
            for (int direction = 0; direction < 8; direction++) {
                int nextRow = thisRow + dR[direction];
                int nextColumn = thisColumn + dC[direction];
                boolean isInBound = nextRow >= 0 && nextRow < numberOfRows && nextColumn >= 0 && nextColumn < numberOfColumns;
                if (!isInBound) {
                    continue;
                }
                if (map[nextRow][nextColumn] != target.charAt(currentPointer)) {
                    visited[nextRow][nextColumn] = 1;
                    continue;
                }
                if (map[nextRow][nextColumn] == target.charAt(currentPointer) && visited[nextRow][nextColumn] == 0) {
                    stack.push(nextColumn);
                    stack.push(nextRow);
                    currentPointer++;
                    visited[nextRow][nextColumn] = 1;
                    foundNext = true;
                    break;
                }
            }
            if (foundNext) {
                continue;
            }
        }
        return currentPointer == target.length();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = Integer.parseInt(sc.next());
        int column = Integer.parseInt(sc.next());
        char[][] map = new char[row][column];
        for (int i = 0; i < map.length; i++) {
            String thisRow = sc.next();
            map[i] = thisRow.toCharArray();
        }

        System.out.println(solution(map, row, column));

        sc.close();
    }
}
