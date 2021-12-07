package bfs;

import java.util.*;

public class Slink {

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

    private static HashMap<Integer, Integer> solution(int[][] visited, int row, int column, int[] startPoint, int count, HashMap<Integer, Integer> hashMap) {
        if (graphIsFull(visited)[0] == -1) {
            return hashMap;
        }
        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startPoint[0]);
        queue.add(startPoint[1]);
        visited[startPoint[0]][startPoint[1]] = 1;
        count++;
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
                        visited[nextX][nextY] = 1;
                    }
                }

            }
        }

        if (hashMap.containsKey(count)) {
            hashMap.put(count, hashMap.get(count) + 1);
        }
        else {
            hashMap.put(count, 1);
        }

        int[] nextStartPoint = graphIsFull(visited);
        count = 0;
        return solution(visited, row, column, nextStartPoint, count, hashMap);

}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int row = Integer.parseInt(sc.next());
            int column = Integer.parseInt(sc.next());
            if (row == 0 && column == 0) {
                break;
            }
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
            int count = 0;
            int[] startPoint = {-1, -1};
            for (int r = 0; r < map.length; r++) {
                for (int c = 0; c < map[r].length; c++) {
                    if (map[r][c] == 1 && visited[r][c] == 0) {
                        startPoint[0] = r;
                        startPoint[1] = c;
                        break;
                    }
                }
                if (startPoint[0] != -1) {
                    break;
                }
            }
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            HashMap<Integer, Integer> result = solution(visited, row, column, startPoint, count, hashMap);
            List<Integer> keySet = new ArrayList<>(result.keySet());
            Collections.sort(keySet);
            int sum = 0;
            List<int[]> finalResult = new ArrayList<>();
            for (Integer key : keySet) {
                sum += result.get(key);
                int[] res = {key, result.get(key)};
                finalResult.add(res);
            }
            System.out.println(sum);
            for (int[] res : finalResult) {
                System.out.println(res[0] + " " + res[1]);
            }
        }

        sc.close();
    }
}
