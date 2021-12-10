package dfs;

import java.util.*;

public class DuduServiceMaker {

    private static boolean solution(List<List<Integer>> graph, List<Integer> visited, int startPoint) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startPoint);
        while (!stack.isEmpty()) {
            int removedNumber = stack.pop();
            for (int i = 0; i < graph.get(removedNumber).size(); i++) {
                int nextNumber = graph.get(removedNumber).get(i);
                // nếu điểm này đã được visited rồi tức là đang có cycle quay lại điểm cũ -> true
                if (visited.get(nextNumber) == 1) {
                    return true;
                }
                else if (visited.get(nextNumber) ==  0){
                    stack.push(nextNumber);
                    visited.set(nextNumber, 1);
                }
            }
        }

        // đi hết mà không tìm ra true thì false
        return false;
    }

    private static List<Integer> initNewVisitedList(int bound) {
        List<Integer> visited = new ArrayList<>();
        for (int i = 0; i < bound; i++) {
            visited.add(0);
        }
        return visited;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        boolean[] results = new boolean[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int vertices = Integer.parseInt(sc.next());
            int connections = Integer.parseInt(sc.next());
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < vertices + 1; i++) {
                graph.add(new ArrayList<>());
            }

            List<Integer> visited;

            for (int i = 0; i < connections; i++) {
                int v1 = Integer.parseInt(sc.next());
                int v2 = Integer.parseInt(sc.next());
                graph.get(v1).add(v2);
            }

            boolean hasCycle = false;
            // test thử từ từng điểm xem nếu tìm được cycle thì dừng luôn
            for (int i = 0; i < graph.size(); i++) {
                visited = initNewVisitedList(graph.size());
                visited.set(i, 1);
                if(solution(graph, visited, i)) {
                    hasCycle = true;
                    break;
                }
            }
            results[test] = hasCycle;
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
