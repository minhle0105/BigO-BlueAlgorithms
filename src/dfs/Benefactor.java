package dfs;

import java.util.*;

class Point {
    private final int id;
    private final Map<Integer, Integer> adjacencyWeight;

    public Point(int id) {
        this.id = id;
        this.adjacencyWeight = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public boolean hasNeighbors() {
        return this.adjacencyWeight.size() > 0;
    }

    public Map<Integer, Integer> getAdjacencyWeight() {
        return adjacencyWeight;
    }

    public void addNeighbor(int neighborId) {
        this.adjacencyWeight.put(neighborId, 0);
    }

    public void addNeighbor(int neighborId, int weight) {
        this.adjacencyWeight.put(neighborId, weight);
    }

    public int getWeighToANeighbor(int neighborId) {
        return this.adjacencyWeight.get(neighborId);
    }

    public Set<Integer> getAllNeighbors() {
        return this.adjacencyWeight.keySet();
    }
}

public class Benefactor {



    private static List<Integer> initNewVisited(int bound) {
        List<Integer> visited = new ArrayList<>();
        for (int i = 0; i < bound; i++) {
            visited.add(0);
        }
        return visited;
    }

    private static List<Integer> initNewLevels(int bound) {
        List<Integer> levels = new ArrayList<>();
        for (int i = 0; i < bound; i++) {
            levels.add(0);
        }
        return levels;
    }

    private static int[] solution(List<Point> graph, List<Integer> visited, List<Integer> levels, int startPoint) {
        Stack<Point> stack = new Stack<>();
        stack.push(graph.get(startPoint));
        while (!stack.isEmpty()) {
            Point removedPoint = stack.pop();
            Set<Integer> neighborsId = removedPoint.getAllNeighbors();
            for (Integer integer : neighborsId) {
                Point nextPoint = graph.get(integer);
                if (visited.get(nextPoint.getId()) == 0) {
                    stack.push(graph.get(nextPoint.getId()));
                    visited.set(nextPoint.getId(), 1);
                    levels.set(nextPoint.getId(), levels.get(removedPoint.getId()) + removedPoint.getWeighToANeighbor(nextPoint.getId()));
                }
            }

        }
        int maxVal = 0;
        int maxPoint = 0;
        for (int i = 0; i < levels.size(); i++) {
            if (levels.get(i) > maxVal) {
                maxVal = levels.get(i);
                maxPoint = i;
            }
        }
        return new int[]{maxVal, maxPoint};
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTests = Integer.parseInt(sc.next());

        int[] results = new int[numberOfTests];
        for (int test = 0; test < numberOfTests; test++) {
            int numberOfVertices = Integer.parseInt(sc.next());
            List<Point> graph = new ArrayList<>();
            List<Integer> visited;
            List<Integer> levels;

            // khởi tạo graph
            for (int i = 0; i < numberOfVertices + 1; i++) {
                graph.add(new Point(i));
            }

            // tạo connection giữa các điểm
            for (int i = 0; i < numberOfVertices - 1; i++) {
                int v1 = Integer.parseInt(sc.next());
                int v2 = Integer.parseInt(sc.next());
                v1--; v2--;
                int weight = Integer.parseInt(sc.next());
                graph.get(v1).addNeighbor(v2, weight);
                graph.get(v2).addNeighbor(v1, weight);
            }

            int startPoint = 1;

            visited = initNewVisited(graph.size());
            levels = initNewLevels(graph.size());
            int nextStartPoint = solution(graph, visited, levels, startPoint)[1];
            visited = initNewVisited(graph.size());
            levels = initNewLevels(graph.size());
            int maxVal = solution(graph, visited, levels, nextStartPoint)[0];
            results[test] = maxVal;
        }

        for (int result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
