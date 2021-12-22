package heap;

import java.util.*;


public class Qheap1 {

    private static List<Integer> solution(List<List<Integer>> queries) {
        PriorityQueue<Integer> deletedHeap = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (List<Integer> integers : queries) {
            if (integers.size() > 1) {
                int q1 = integers.get(0);
                int q2 = integers.get(1);
                if (q1 == 1) {
                    heap.add(q2);
                } else if (q1 == 2) {
                    deletedHeap.add(q2);
                }
            } else {
                while (!heap.isEmpty() && heap.peek().equals(deletedHeap.peek())) {
                    heap.remove();
                    deletedHeap.remove();
                }
                result.add(heap.peek());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfQueries = Integer.parseInt(sc.next());
        List<List<Integer>> queryDetails = new ArrayList<>();
        for (int i = 0; i < numberOfQueries; i++) {
            List<Integer> query = new ArrayList<>();
            int queryId = Integer.parseInt(sc.next());
            if (queryId == 3) {
                query.add(queryId);
            } else {
                int queryDetail = Integer.parseInt(sc.next());
                query.add(queryId);
                query.add(queryDetail);
            }
            queryDetails.add(query);
        }
        List<Integer> result = solution(queryDetails);
        for (Integer i : result) {
            System.out.println(i);
        }

        sc.close();
    }
}
