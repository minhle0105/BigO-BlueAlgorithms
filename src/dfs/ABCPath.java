package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ABCPath {

    static final int[] dR = {0,0,1,-1,-1,-1,1,1};
    static final int[] dC = {1,-1,0,0,-1,1,-1,1};

    private static int solution (char[][] map, int[][] visited, int[] startPoint, int numberOfRows, int numberOfColumns) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startPoint[1]);
        stack.push(startPoint[0]);
        int count = 1;
        while (!stack.isEmpty()) {
            int thisRow = stack.pop();
            int thisColumn = stack.pop();
            for (int direction = 0; direction < 8; direction++) {
                int nextRow = thisRow + dR[direction];
                int nextColumn = thisColumn + dC[direction];
                boolean inBound = nextRow >= 0 && nextRow < numberOfRows && nextColumn >= 0 && nextColumn < numberOfColumns;
                if (inBound) {
                    if ((int) map[nextRow][nextColumn] - (int) map[thisRow][thisColumn] == 1) {
                        if (visited[nextRow][nextColumn] == 0) {
                            visited[nextRow][nextColumn] = 1;
                            stack.push(nextColumn);
                            stack.push(nextRow);
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> testResult = new ArrayList<>();
        while (true) {
            int numberOfRows = Integer.parseInt(sc.next());
            int numberOfColumns = Integer.parseInt(sc.next());
            if (numberOfRows == 0 && numberOfColumns == 0) {
                break;
            }
            char[][] map = new char[numberOfRows][numberOfColumns];
            for (int i = 0; i < numberOfRows; i++) {
                String thisRow = sc.next();
                map[i] = thisRow.toCharArray();
            }

            List<int[]> possibleStartPoint = new ArrayList<>();
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    if (map[i][j] == 'A') {
                        possibleStartPoint.add(new int[]{i,j});
                    }
                }
            }

            int maxVal = 0;
            for (int i = 0; i < possibleStartPoint.size(); i++) {
                int[][] visited = new int[numberOfRows][numberOfColumns];
                visited[possibleStartPoint.get(0)[0]][possibleStartPoint.get(0)[1]] = 1;
                int result = solution(map, visited, possibleStartPoint.get(0), numberOfRows, numberOfColumns);
                if (result > maxVal) {
                    maxVal = result;
                }
            }

            testResult.add(maxVal);
        }

        for (int i = 0; i < testResult.size(); i++) {
            System.out.println("Case " + (i+1) + ": " + testResult.get(i));
        }

        sc.close();
    }
}
