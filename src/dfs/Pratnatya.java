package dfs;

import java.util.*;

public class Pratnatya {

    static List<Integer> visited;
    static int count;

    private static void solution(List<List<Integer>> graph, List<Integer> visited, int startPoint) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startPoint);
        if (visited.get(startPoint) == 1) {
            return;
        }
        else {
            count++;
            visited.set(startPoint, 1);
        }
        while (!stack.isEmpty()) {
            int removedNumber = stack.pop();
            for (int i = 0; i < graph.get(removedNumber).size(); i++) {
                int nextNumber = graph.get(removedNumber).get(i);
                if (visited.get(nextNumber) == 0) {
                    stack.push(nextNumber);
                    visited.set(nextNumber, 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        int[] results = new int[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            visited = new ArrayList<>();
            int numberOfStudents = Integer.parseInt(sc.next());
            int numberOfConnections = Integer.parseInt(sc.next());
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < numberOfStudents; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < numberOfConnections; i++) {
                int x = Integer.parseInt(sc.next());
                int y = Integer.parseInt(sc.next());
                graph.get(x).add(y);
                graph.get(y).add(x);
            }
            for (int i = 0; i < graph.size(); i++) {
                visited.add(0);
            }

            int startPoint;
            count = 0;

            for (int i = 0; i < visited.size(); i++) {
                startPoint = i;
                solution(graph, visited, startPoint);
            }
            results[test] = count;
        }

        for (int result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
