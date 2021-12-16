package heap;

import java.util.*;

public class GuessDataStructure {

    static Queue<Integer> queue;
    static Stack<Integer> stack;
    static PriorityQueue<Integer> heap;

    private static String solution (List<List<Integer>> queryDetails) {
        String stackToString = "";
        String queueToString = "";
        String heapToString = "";
        while (!stack.isEmpty()) {
            stackToString += stack.pop();
        }
        while (!queue.isEmpty()) {
            queueToString += queue.remove();
        }
        while (!heap.isEmpty()) {
            heapToString += heap.remove();
        }

        String queryToString = "";

        for (List<Integer> query : queryDetails) {
            if (query.get(0) == 2) {
                int removedNumber = query.get(1);
                queryToString += removedNumber;
            }
        }

        if (queryToString.length() < stackToString.length()) {
            stackToString = stackToString.substring(0, queryToString.length());
            queueToString = queueToString.substring(0, queryToString.length());
            heapToString = heapToString.substring(0, queryToString.length());
        }

        if (queryToString.equals(stackToString)) {
            if (queryToString.equals(queueToString) | queryToString.equals(heapToString)) {
                return "not sure";
            }
            else {
                return "stack";
            }
        }

        if (queryToString.equals(heapToString)) {
            if (queryToString.equals(stackToString) | queryToString.equals(queueToString)) {
                return "not sure";
            }
            else {
                return "priority queue";
            }
        }

        if (queryToString.equals(queueToString)) {
            if (queryToString.equals(stackToString) | queryToString.equals(heapToString)) {
                return "not sure";
            }
            else {
                return "queue";
            }
        }

        return "impossible";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> output = new ArrayList<>();
        while (sc.hasNextInt()) {
            int numberOfQueries = Integer.parseInt(sc.next());
            List<List<Integer>> queryDetails = new ArrayList<>();
            List<Integer> query;
            queue = new LinkedList<>();
            stack = new Stack<>();
            heap = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
            for (int i = 0; i < numberOfQueries; i++) {
                query = new ArrayList<>();
                int q1 = Integer.parseInt(sc.next());
                int q2 = Integer.parseInt(sc.next());
                query.add(q1);
                query.add(q2);
                if (q1 == 1) {
                    queue.add(q2);
                    stack.add(q2);
                    heap.add(q2);
                }
                queryDetails.add(query);
            }
            output.add(solution(queryDetails));
        }

        for (String s : output) {
            System.out.println(s);
        }
        sc.close();
    }

}
