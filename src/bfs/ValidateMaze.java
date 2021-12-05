package bfs;

import java.util.*;

class Point{
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean checkPointsEqual(Point secondPoint) {
        return this.getX() == secondPoint.getX() && this.getY() == secondPoint.getY();
    }
}

public class ValidateMaze {

    private static List<Point> hasEnterAndExit(List<List<Character>> maze, int numberOfRows, int numberOfColumns) {
        List<Point> endPoints = new ArrayList<>();
        List<Character> firstRow = maze.get(0);
        List<Character> lastRow = maze.get(numberOfRows - 1);
        List<Character> firstColumn = new ArrayList<>();
        List<Character> lastColumn = new ArrayList<>();
        for (List<Character> row : maze) {
            firstColumn.add(row.get(0));
            lastColumn.add(row.get(row.size() - 1));
        }

        for (int i = 0; i < firstRow.size(); i++) {
            if (firstRow.get(i) == '.') {
                boolean hasBeenRecognized = false;
                Point point = new Point(0, i);
                for (Point p : endPoints) {
                    if (p.checkPointsEqual(point)) {
                        hasBeenRecognized = true;
                        break;
                    }
                }
                if (!hasBeenRecognized) {
                    endPoints.add(point);
                }
            }
        }

        for (int i = 0; i < lastRow.size(); i++) {
            if (lastRow.get(i) == '.') {
                boolean hasBeenRecognized = false;
                Point point = new Point(numberOfRows - 1, i);
                for (Point p : endPoints) {
                    if (p.checkPointsEqual(point)) {
                        hasBeenRecognized = true;
                        break;
                    }
                }
                if (!hasBeenRecognized) {
                    endPoints.add(point);
                }
            }
        }

        for (int i = 0; i < firstColumn.size(); i++) {
            if (firstColumn.get(i) == '.') {
                boolean hasBeenRecognized = false;
                Point point = new Point(i, 0);
                for (Point p : endPoints) {
                    if (p.checkPointsEqual(point)) {
                        hasBeenRecognized = true;
                        break;
                    }
                }
                if (!hasBeenRecognized) {
                    endPoints.add(point);
                }
            }
        }

        for (int i = 0; i < lastColumn.size(); i++) {
            if (lastColumn.get(i) == '.') {
                boolean hasBeenRecognized = false;
                Point point = new Point(i, numberOfColumns - 1);
                for (Point p : endPoints) {
                    if (p.checkPointsEqual(point)) {
                        hasBeenRecognized = true;
                        break;
                    }
                }
                if (!hasBeenRecognized) {
                    endPoints.add(point);
                }

            }
        }

        return endPoints;
    }

    private static boolean validate(List<List<Character>> maze, int numberOfRows, int numberOfColumns) {
        List<Point> endPoints = hasEnterAndExit(maze, numberOfRows, numberOfColumns);
        if (endPoints.size() != 2) {
            return false;
        }
        Point firstPoint = endPoints.get(0);
        Point secondPoint = endPoints.get(1);
        Queue<Point> queue = new LinkedList<>();
        queue.add(firstPoint);
        while (!queue.isEmpty()) {
            Point thisPoint = queue.remove();
            Point[] adjacentPoints = {new Point(thisPoint.getX() + 1, thisPoint.getY()),
                    new Point(thisPoint.getX(), thisPoint.getY() + 1),
                    new Point(thisPoint.getX() - 1, thisPoint.getY()),
                    new Point(thisPoint.getX(), thisPoint.getY() - 1)};
            for (Point point : adjacentPoints) {
                boolean inBound = point.getX() < numberOfRows && point.getY() < numberOfColumns && point.getX() > -1 && point.getY() > -1;
                if (inBound) {
                    boolean hitWall = maze.get(point.getX()).get(point.getY()) == '#';
                    if ((!hitWall)) {
                        queue.add(point);
                    }
                }
                if (point.checkPointsEqual(secondPoint)) {
                    return true;
                }
            }
        }
        return false;
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
            if (validate(providedMaze, rows, columns)) {
                System.out.println("valid");
            }
            else {
                System.out.println("invalid");
            }
        }

        sc.close();
    }
}
