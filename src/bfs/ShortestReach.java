package bfs;

import java.util.*;

public class ShortestReach {
    private static List<List<Integer>> graph;
    private static int V;
    private static int E;
    private static List<Integer> path;
    private static List<Boolean> visited;

    // N number of nodes in graph
    // V number of vertices
    //

    private static List<Integer> solution(List<List<Integer>> graph, int topVertice, int numberOfVertices) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(topVertice);
        path = new ArrayList<>();
        visited = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            visited.add(false);
            path.add(-1);
        }

        for (int i = 0; i < numberOfVertices + 1; i++) {
            result.add(-1);
        }
        List<Integer> levels = new ArrayList<>();
        for (int i = 0; i < numberOfVertices + 1; i++) {
            levels.add(-1);
        }
        levels.set(topVertice, 0);
        visited.set(topVertice, true);
        while (!queue.isEmpty()) {
            int removedVertice = queue.remove();
            for (int i = 0; i < graph.get(removedVertice).size(); i++) {
                int nextVertice = graph.get(removedVertice).get(i);
                if (!visited.get(nextVertice)) {
                    queue.add(nextVertice);
                    visited.set(nextVertice, true);
                    path.set(nextVertice, removedVertice);
                    levels.set(nextVertice, levels.get(removedVertice) + 1);
                }
                result.set(removedVertice, 6 * levels.get(removedVertice));
            }
        }
        for (int i = 0; i < result.size(); i++) {
            if (i == topVertice) {
                result.remove(i);
            }
        }
        return result.subList(1, result.size());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        for (int test = 0; test < numberOfTest; test++) {
            graph = new ArrayList<>();
            int numberOfVertices = Integer.parseInt(sc.next());
            int numberOfEdges = Integer.parseInt(sc.next());
            for (int j = 0; j < numberOfVertices + 1; j++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < numberOfEdges; i++) {
                V = Integer.parseInt(sc.next());
                E = Integer.parseInt(sc.next());
                graph.get(E).add(V);
                graph.get(V).add(E);
            }
            int topVertice = Integer.parseInt(sc.next());
            List<Integer> result = solution(graph, topVertice, numberOfVertices);
            String s = "";
            for (Integer i : result) {
                s += i + " ";
            }
            s = s.substring(0, s.length() - 1);
            System.out.println(s);
        }

        sc.close();
    }
}
