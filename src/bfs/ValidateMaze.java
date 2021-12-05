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
        if (numberOfColumns == 1 && numberOfRows == 1) {
            if (maze.get(0).get(0) == '.') {
                Point point = new Point(0, 0);
                endPoints.add(point);
                return endPoints;
            }
        }
        else if (numberOfRows == 1) {
            for (int i = 0; i < numberOfColumns; i++) {
                if (maze.get(0).get(i) == '.') {
                    Point point = new Point(0, i);
                    endPoints.add(point);
                }
            }
            return endPoints;
        }
        else if (numberOfColumns == 1) {
            for (int i = 0; i < numberOfRows; i++) {
                if (maze.get(i).get(0) == '.') {
                    Point point = new Point(i, 0);
                    endPoints.add(point);
                }
            }
            return endPoints;
        }
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
                Point point = new Point(0, i);
                endPoints.add(point);
            }
        }

        for (int i = 0; i < lastRow.size(); i++) {
            if (lastRow.get(i) == '.') {
                Point point = new Point(numberOfRows - 1, i);
                endPoints.add(point);
            }
        }

        for (int i = 0; i < firstColumn.size(); i++) {
            if (firstColumn.get(i) == '.') {
                Point point = new Point(i, 0);
                endPoints.add(point);
            }
        }

        for (int i = 0; i < lastColumn.size(); i++) {
            if (lastColumn.get(i) == '.') {
                Point point = new Point(i, numberOfColumns - 1);
                endPoints.add(point);
            }
        }

        return endPoints;
    }

    private static boolean validate(List<List<Character>> maze, int numberOfRows, int numberOfColumns) {
        List<Point> endPoints = hasEnterAndExit(maze, numberOfRows, numberOfColumns);
        int[][] isVisited = new int[numberOfRows][numberOfColumns];

        // nếu có nhiều hơn hoặc ít hơn 2 điểm có thể là vào ra của matrix tức là sai
        if (endPoints.size() != 2) {
            return false;
        }

        // tìm đường đi từ firstPoint tới secondPoint
        Point firstPoint = endPoints.get(0);
        Point secondPoint = endPoints.get(1);

        Stack<Point> stack = new Stack<>();
        stack.push(firstPoint);
        isVisited[firstPoint.getX()][firstPoint.getY()] = 1;
        while (!stack.isEmpty()) {
            Point thisPoint = stack.pop();
            // xét cả 4 trường hợp khả thi của 4 điểm kề theo 4 hướng
            Point[] adjacentPoints = {new Point(thisPoint.getX() + 1, thisPoint.getY()),
                    new Point(thisPoint.getX(), thisPoint.getY() + 1),
                    new Point(thisPoint.getX() - 1, thisPoint.getY()),
                    new Point(thisPoint.getX(), thisPoint.getY() - 1)};

            for (Point point : adjacentPoints) {
                // trước hết kiểm tra xem từng điểm có nằm trong matrix không
                boolean inBound = point.getX() < numberOfRows && point.getY() < numberOfColumns && point.getX() > -1 && point.getY() > -1;
                if (inBound) {
                    // nếu có trong matrix, kiểm tra xem đã được thăm chưa
                    boolean hasNotBeenVisited = isVisited[point.getX()][point.getY()] == 0;
                    if (hasNotBeenVisited) {
                        // nếu chưa được thăm thì mới xét, nếu chạm tường thì bỏ, nếu không thì là đường đi
                        boolean hitWall = maze.get(point.getX()).get(point.getY()) == '#';
                        if ((!hitWall)) {
                            isVisited[point.getX()][point.getY()] = 1;
                            stack.push(point);
                        }
                    }

                }
                // nếu phát hiện ra đây là điểm thoát khỏi rồi thì đúng, và dừng luôn
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
        String[] results = new String[numberOfTest];
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
