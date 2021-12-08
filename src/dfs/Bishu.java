package dfs;

import java.util.*;

public class Bishu {
    private static int solution(List<List<Integer>> graph, List<Integer> ids) {
        List<Integer> visited = new ArrayList<>();
        List<Integer> levels = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            visited.add(0);
            levels.add(0);
        }

        visited.set(1, 1);
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        while (!stack.isEmpty()) {
            int removedNumber = stack.pop();
            for (int i = 0; i < graph.get(removedNumber).size(); i++) {
                int nextNumber = graph.get(removedNumber).get(i);
                if (visited.get(nextNumber) == 0) {
                    stack.push(nextNumber);
                    visited.set(nextNumber, 1);
                    levels.set(nextNumber, levels.get(removedNumber) + 1);
                }
            }
        }

        int currentMin = Integer.MAX_VALUE;
        int id = -1; // 3 4 3 5
        for (Integer i : ids) {
            if (levels.get(i) < currentMin ) {
                currentMin = levels.get(i);
                id = i;
            }
            else if (levels.get(i) == currentMin) {
                if (i < id) {
                    id = i;
                }
            }
        }
        return id;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = Integer.parseInt(sc.next());
        int E = V - 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            int x = Integer.parseInt(sc.next());
            int y = Integer.parseInt(sc.next());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        int n = Integer.parseInt(sc.next());
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ids.add(Integer.parseInt(sc.next()));
        }
        System.out.println(solution(graph, ids));
        sc.close();
    }
}
