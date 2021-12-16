package heap;

import java.util.*;

public class GuessDataStructure {

    static Queue<Integer> queue;
    static Stack<Integer> stack;
    static PriorityQueue<Integer> heap;
    static String stackToString;
    static String heapToString;
    static String queueToString;

    private static String solution (List<List<Integer>> queryDetails) {
        StringBuilder queryToString = new StringBuilder();

        for (List<Integer> query : queryDetails) {
            if (query.get(0) == 2) {
                int removedNumber = query.get(1);
                queryToString.append(removedNumber);
            }
        }

        if (queryToString.length() < stackToString.length()) {
            stackToString = stackToString.substring(0, queryToString.length());
            queueToString = queueToString.substring(0, queryToString.length());
            heapToString = heapToString.substring(0, queryToString.length());
        }

        if (queryToString.toString().equals(stackToString)) {
            if (queryToString.toString().equals(queueToString) | queryToString.toString().equals(heapToString)) {
                return "not sure";
            }
            else {
                return "stack";
            }
        }

        if (queryToString.toString().equals(heapToString)) {
            if (queryToString.toString().equals(queueToString)) {
                return "not sure";
            }
            else {
                return "priority queue";
            }
        }

        if (queryToString.toString().equals(queueToString)) {
            return "queue";
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
            stackToString = "";
            queueToString = "";
            heapToString = "";
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
                if (q1 == 2) {
                    if (stack.peek() == q2) {
                        stack.pop();
                        stackToString += q2;
                    }
                    if (!queue.isEmpty() && queue.peek() == q2) {
                        queue.remove();
                        queueToString += q2;
                    }
                    if (!heap.isEmpty() && heap.peek() == q2) {
                        heap.remove();
                        heapToString += q2;
                    }
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
