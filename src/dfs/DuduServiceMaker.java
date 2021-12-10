package dfs;

import java.util.*;

public class DuduServiceMaker {

    private static boolean solution(int[][] graph, int numberOfCourse) {
        Map<Integer, List<Integer>> courseMap = new HashMap<>();
        for (int[] connection : graph) {
            if (courseMap.containsKey(connection[1])) {
                courseMap.get(connection[1]).add(connection[0]);
            }
            else {
                List<Integer> nextCourses = new LinkedList<>();
                nextCourses.add(connection[0]);
                courseMap.put(connection[1], nextCourses);
            }
        }

        boolean[] checked = new boolean[numberOfCourse + 1];
        boolean[] inPath = new boolean[numberOfCourse + 1];

        for (int i = 0; i < numberOfCourse; i++) {
            if (hasCycle(i, courseMap, checked, inPath)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCycle(int course, Map<Integer, List<Integer>> courseMap, boolean[] checked, boolean[] inPath) {
        if (checked[course]) {
            return false;
        }
        if (inPath[course]) {
            return true;
        }

        if(!courseMap.containsKey(course)) {
            return false;
        }

        inPath[course] = true;
        boolean isCyclic = false;

        for (Integer prerequisite : courseMap.get(course)) {
            isCyclic = hasCycle(prerequisite, courseMap, checked, inPath);
            if (isCyclic) {
                break;
            }
        }

        inPath[course] = false;
        checked[course] = true;
        return isCyclic;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        boolean[] results = new boolean[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int vertices = Integer.parseInt(sc.next());
            int connections = Integer.parseInt(sc.next());
            int[][] graph = new int[connections][2];

            for (int i = 0; i < connections; i++) {
                int[] connection = new int[2];
                connection[0] = Integer.parseInt(sc.next());
                connection[1] = Integer.parseInt(sc.next());
                graph[i] = connection;
            }


            boolean result = solution(graph, vertices);

            results[test] = result;
        }

        for (boolean result : results) {
            if (result) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
        sc.close();
    }
}
