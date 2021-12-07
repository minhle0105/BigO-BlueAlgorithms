package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Sheep {

    static final char block = '#';
    static final char sheep = 'k';
    static final char wolves = 'v';

    private static int[] mapIsFullyVisited(int[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j] == 0) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    private static int[] solution(char[][] map, int[][] visited, int[] startPoint,  int numberOfRows, int numberOfColumns, int[] sheepWolves) {
        if (mapIsFullyVisited(visited)[0] == -1) {
            return sheepWolves;
        }
        Queue<Integer> queue = new LinkedList<>();
        int sheepInThisArea = 0;
        int wolvesInThisArea = 0;
        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};
        queue.add(startPoint[0]);
        queue.add(startPoint[1]);
        if (map[startPoint[0]][startPoint[0]] == sheep) {
            sheepInThisArea++;
        }
        if (map[startPoint[0]][startPoint[0]] == wolves) {
            wolvesInThisArea++;
        }
        visited[startPoint[0]][startPoint[1]] = 1;
        while (!queue.isEmpty()) {
            int x = queue.remove();
            int y = queue.remove();
            for (int direction = 0; direction < 4; direction++) {
                int nextX = x + dR[direction];
                int nextY = y + dC[direction];
                if ((nextX >= 0 && nextX < numberOfRows) && (nextY >= 0 && nextY < numberOfColumns)) {
                    if (visited[nextX][nextY] == 0) {
                        if (map[nextX][nextY] == sheep) {
                            sheepInThisArea++;
                        }
                        else if (map[nextX][nextY] == wolves) {
                            wolvesInThisArea++;
                        }
                        queue.add(nextX);
                        queue.add(nextY);
                        visited[nextX][nextY] = 1;
                    }
                }
            }
        }
        if (sheepInThisArea > wolvesInThisArea) {
            sheepWolves[1] -= wolvesInThisArea;
        }
        else {
            sheepWolves[0] -= sheepInThisArea;
        }
        int[] nextStartPoint = mapIsFullyVisited(visited);
        return solution(map, visited, nextStartPoint, numberOfRows, numberOfColumns, sheepWolves);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfRows = Integer.parseInt(sc.next());
        int numberOfColumns = Integer.parseInt(sc.next());
        char[][] map = new char[numberOfRows][numberOfColumns];
        for (int row = 0; row < numberOfRows; row++) {
            String thisRow = sc.next();
            map[row] = thisRow.toCharArray();
        }
        int[][] visited = new int[numberOfRows][numberOfColumns];
        int numberOfSheep = 0;
        int numberOfWolves = 0;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (map[i][j] == block) {
                    visited[i][j] = 1;
                }
                if (map[i][j] == sheep) {
                    numberOfSheep++;
                }
                if (map[i][j] == wolves) {
                    numberOfWolves++;
                }
            }
        }

        int[] startPoint = {-1, -1};
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j] != 1) {
                    startPoint[0] = i;
                    startPoint[1] = j;
                    break;
                }
            }
            if (startPoint[0] != -1) {
                break;
            }
        }
        int[] sheepWolves = {numberOfSheep, numberOfWolves};
        int[] result = solution(map, visited,startPoint, numberOfRows, numberOfColumns, sheepWolves);
        System.out.println(result[0] + " " + result[1]);
        sc.close();
    }
}
