package finalExam;

import java.util.*;

public class LearningLanguages {

    static void bfs(List<List<Integer>> graph, int[] visited, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = 1;
        while (!queue.isEmpty()) {
            int thisValue = queue.remove();
            for (int i = 0; i < graph.get(thisValue).size(); i++) {
                int nextValue = graph.get(thisValue).get(i);
                if (visited[nextValue] == 0) {
                    visited[nextValue] = 1;
                    queue.add(nextValue);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int m = Integer.parseInt(sc.next());
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
        }
        for (int i = 0; i < n; i ++) {
            int number = Integer.parseInt(sc.next());
            for (int j = 0; j < number; j++) {
                int lang = Integer.parseInt(sc.next());
                map.get(i).add(lang);
            }
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            Set<Integer> thisValue = map.get(i);
            for (int j = i + 1; j < n; j++) {
                Set<Integer> nextValue = map.get(j);
                for (Integer v : thisValue) {
                    if (nextValue.contains(v)) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }
        }
        boolean allZeroes = true;
        for (Set<Integer> adjacency : map.values()) {
            if (adjacency.size() > 0) {
                allZeroes = false;
                break;
            }
        }
        if (allZeroes) {
            System.out.println(graph.size());
            return;
        }
        int size = graph.size();
        int[] visited = new int[size];
        int count = 0;
        for (int i = 0; i < size; i ++) {
            if (visited[i] == 0) {
                bfs(graph, visited, i);
                count++;
            }
        }

        System.out.println(count - 1);
        sc.close();
    }
}
