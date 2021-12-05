package bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Point {
    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class ValidateMaze {
    private static int V;
    private static int E;
    private static List<Integer> path;
    private static List<Boolean> visited;

    private static List<List<Integer>> hasEnterAndExit(List<List<Character>> maze, int numberOfRows, int numberOfColumns) {
        List<List<Integer>> endPoints = new ArrayList<>();
        List<Character> firstRow = maze.get(0);
        List<Character> lastRow = maze.get(numberOfRows - 1);
        List<Character> firstColumn = new ArrayList<>();
        List<Character> lastColumn = new ArrayList<>();
        for (List<Character> row : maze) {
            firstColumn.add(row.get(0));
            lastColumn.add(row.get(row.size() - 1));
        }

        for (Character c : firstRow) {
            if (c == '.') {
                List<Integer> thisPoint = new ArrayList<>();
                thisPoint.add(0);
                thisPoint.add(firstRow.indexOf(c));
                endPoints.add(thisPoint);
            }
            if (endPoints.size() == 2) {
                return endPoints;
            }
        }

        for (Character c : lastRow) {
            if (c == '.') {
                List<Integer> thisPoint = new ArrayList<>();
                thisPoint.add(numberOfRows - 1);
                thisPoint.add(lastRow.indexOf(c));
                endPoints.add(thisPoint);
            }
            if (endPoints.size() == 2) {
                return endPoints;
            }
        }

        for (Character c : firstColumn) {
            if (c == '.') {
                List<Integer> thisPoint = new ArrayList<>();
                thisPoint.add(firstColumn.indexOf(c));
                thisPoint.add(numberOfColumns - 1);
                endPoints.add(thisPoint);
            }
            if (endPoints.size() == 2) {
                return endPoints;
            }
        }

        for (Character c : lastColumn) {
            if (c == '.') {
                List<Integer> thisPoint = new ArrayList<>();
                thisPoint.add(lastColumn.indexOf(c));
                thisPoint.add(numberOfColumns - 1);
                endPoints.add(thisPoint);
            }
            if (endPoints.size() == 2) {
                return endPoints;
            }
        }

        return endPoints;
    }

    private static boolean validate(List<List<Character>> maze, int numberOfRows, int numberOfColumns) {
        List<List<Integer>> endPoints = hasEnterAndExit(maze, numberOfRows, numberOfColumns);
        if (endPoints.size() < 2) {
            return false;
        }
        List<Integer> firstPointCoordinates = endPoints.get(0);
        List<Integer> secondPointCoordinates = endPoints.get(1);
        Point firstPoint = new Point(firstPointCoordinates.get(0), firstPointCoordinates.get(1));
        Point secondPoint = new Point(secondPointCoordinates.get(0), secondPointCoordinates.get(1));

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        for (int test = 0; test < numberOfTest; test++) {
            int rows = Integer.parseInt(sc.next());
            int columns = Integer.parseInt(sc.next());
            List<List<Character>> providedMaze = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                List<Character> thisRow = new ArrayList<>();
                String thisLine = sc.next();
                for (char c : thisLine.toCharArray()) {
                    thisRow.add(c);
                }
                providedMaze.add(thisRow);
            }
            List<List<Integer>> endPoints = hasEnterAndExit(providedMaze, rows, columns);
            System.out.println(endPoints);
        }

        sc.close();
    }
}
