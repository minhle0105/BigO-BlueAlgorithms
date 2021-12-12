package dfs;

import java.util.*;

public class Lakes {

    static int[][] visited;
    static final char land = '*';
    static LinkedList<Integer> lakeInfo;
    static final int[] dR = {-1, 1, 0, 0};
    static final int[] dC = {0, 0, -1, 1};
    static Map<Integer, List<int[]>> lakePosition;

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
        List<int[]> positions = new ArrayList<>();
        stack.push(startPoint[1]);
        stack.push(startPoint[0]);
        positions.add(new int[]{startPoint[0], startPoint[1]});
        int count = 1;
        boolean isOnBound = false;
        while (!stack.isEmpty()) {
            int thisX = stack.pop();
            int thisY = stack.pop();
            if (thisX == 0 | thisX == numberOfRows - 1 | thisY == 0 || thisY == numberOfColumns - 1) {
                isOnBound = true;
                break;
            }
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
                        positions.add(new int[]{nextX,nextY});
                    }
                }
            }
        }
        if (!isOnBound) {
            if (lakePosition.containsKey(count)) {
                for (int[] position : positions) {
                    lakePosition.get(count).add(position);
                }
            }
            else {
                lakePosition.put(count, positions);
            }
            return count;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        lakeInfo = new LinkedList<>();
        lakePosition = new HashMap<>();
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
                if (map[i][j] == land) {
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
            if (lakeSize == 0) {
                continue;
            }
            lakeInfo.add(lakeSize);
        }
        int count = 0;
        Collections.sort(lakeInfo);
        while (lakeInfo.size() != finalLakes) {
            int lakeSizeToFill = lakeInfo.removeFirst();
            count += lakeSizeToFill;
            List<int[]> pointsToFill = lakePosition.get(lakeSizeToFill);
            for (int[] pointToFill : pointsToFill) {
                map[pointToFill[0]][pointToFill[1]] = land;
            }
        }
        System.out.println(count);
        for (char[] row : map) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        sc.close();
    }

}
