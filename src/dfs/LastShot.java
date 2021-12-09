package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class LastShot {

    private static int solution (List<List<Integer>> graph, List<Integer> visited, int startPoint) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startPoint);
        visited.set(startPoint, 1);
        while (!stack.isEmpty()) {
            int thisPoint = stack.pop();
            for (int i = 0; i < graph.get(thisPoint).size(); i++) {
                int nextPoint = graph.get(thisPoint).get(i);
                if (visited.get(nextPoint) == 0) {
                    stack.push(nextPoint);
                    visited.set(nextPoint, 1);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < visited.size(); i++) {
            if (visited.get(i) == 1) {
                count++;
            }
        }
        return count;
    }

    private static List<Integer> initVisited (int bound) {
        List<Integer> visited = new ArrayList<>();
        for (int i = 0; i < bound; i++) {
            visited.add(0);
        }
        return visited;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.next());
        int edges = Integer.parseInt(sc.next());
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices + 1; i ++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            int v1 = Integer.parseInt(sc.next());
            int v2 = Integer.parseInt(sc.next());
            graph.get(v1).add(v2);
        }

        List<Integer> visited;
        int currentMax = -1;

        List<Integer> possibleStarts = new ArrayList<>();

        // tất cả những điểm có connect đến điểm khác đều có thể là điểm bắt đầu
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).size() > 0) {
                possibleStarts.add(i);
            }
        }

        // thử xuất phát từ từng điểm một, mỗi lần là đưa vào 1 cái list visited trống
        for (int startPoint : possibleStarts) {
            visited = initVisited(graph.size());
            int result = solution(graph, visited, startPoint);
            // nếu cover được nhiều điểm hơn current max thì lấy currentMax mới
            if (result > currentMax) {
                currentMax = result;
            }

            // nếu đã bằng được số đỉnh tức là duyệt qua hết rồi thì break luôn
            if (currentMax == vertices) {
                break;
            }
        }

        System.out.println(currentMax);

        sc.close();
    }

}
