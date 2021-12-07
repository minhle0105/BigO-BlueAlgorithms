package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class IceCave {

    private static boolean solution(char[][] map, int[] startPoint, int[] endPoint, int row, int column) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startPoint[1]);
        stack.push(startPoint[0]);
        int[][] visited = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (map[i][j] == 'X') {
                    visited[i][j] = 1;
                }
            }
        }
        visited[startPoint[0]][startPoint[1]] = 1;
        int[] dR = {0, 0, 1, -1};
        int[] dC = {1, -1, 0, 0};
        while (!stack.isEmpty()) {
            int thisPointX = stack.pop();
            int thisPointY = stack.pop();

            for (int direction = 0; direction < 4; direction++) {
                int nextPointX = thisPointX + dR[direction];
                int nextPointY = thisPointY + dC[direction];
                boolean XinBound = nextPointX >= 0 && nextPointX < row;
                boolean YinBound = nextPointY >= 0 && nextPointY < column;
                boolean inBound = XinBound && YinBound;
                if (!inBound) {
                    continue;
                }
                if (visited[nextPointX][nextPointY] == 1 && nextPointX == endPoint[0] && nextPointY == endPoint[1]) {
                    return true;
                }
                boolean isNotVisited = visited[nextPointX][nextPointY] == 0;
                if (isNotVisited) {
                    visited[nextPointX][nextPointY] = 1;
                    stack.push(nextPointY);
                    stack.push(nextPointX);
                }
            }
        }
        return false;
}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = Integer.parseInt(sc.next());
        int column = Integer.parseInt(sc.next());
        char[][] map = new char[row][column];
        for (int r = 0; r < row; r++) {
            String thisRow = sc.next();
            map[r] = thisRow.toCharArray();
        }
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];
        startPoint[0] = Integer.parseInt(sc.next()) - 1;
        startPoint[1] = Integer.parseInt(sc.next()) - 1;
        endPoint[0] = Integer.parseInt(sc.next()) - 1;
        endPoint[1] = Integer.parseInt(sc.next()) - 1;
        String result = solution(map, startPoint, endPoint, row, column) ? "YES" : "NO";
        System.out.println(result);
        sc.close();
    }
}
