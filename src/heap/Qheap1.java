package heap;

import java.util.*;


public class Qheap1 {

    private static List<Integer> solution(List<List<Integer>> queries) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (List<Integer> integers : queries) {
            if (integers.size() > 1) {
                int q1 = integers.get(0);
                int q2 = integers.get(1);
                if (q1 == 1) {
                    heap.add(q2);
                } else if (q1 == 2) {
                    if (hashMap.containsKey(q2)) {
                        hashMap.put(q2, hashMap.get(q2) + 1);
                    }
                    else {
                        hashMap.put(q2, 1);
                    }
                }
            } else {
                while (!heap.isEmpty() && hashMap.containsKey(heap.peek())) {
                    if (hashMap.get(heap.peek()) == 1) {
                        hashMap.remove(heap.remove());
                    }
                    else {
                        hashMap.put(heap.peek(), hashMap.get(heap.peek()) - 1);
                    }
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
