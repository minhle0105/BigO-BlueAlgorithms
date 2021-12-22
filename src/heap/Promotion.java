package heap;

import java.util.*;

public class Promotion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        int days = Integer.parseInt(sc.next());
        int total = 0;
        // 1 2 3 -> 2
        // 2 1 1 -> 1
        // 1 10 5 5 1 -> 9
        // 5 5 1 -> 4
        // 5 1 2 -> 4
        Map<Integer, Integer> bills = new HashMap<>();
        Map<Integer, Integer> shouldBeDeletedFromMin = new HashMap<>();
        Map<Integer, Integer> shouldBeDeletedFromMax = new HashMap<>();
        for (int i = 0; i < days; i++) {
            int billThisDay = Integer.parseInt(sc.next());
            for (int bill = 0; bill < billThisDay; bill++) {
                int billValue = Integer.parseInt(sc.next());
                if (bills.containsKey(billValue)) {
                    bills.put(billValue, bills.get(billValue) + 1);
                }
                else {
                    bills.put(billValue, 1);
                }
                minHeap.add(billValue);
                maxHeap.add(billValue);
            }
            if (shouldBeDeletedFromMin.containsKey(maxHeap.peek())) {
                shouldBeDeletedFromMin.put(maxHeap.peek(), shouldBeDeletedFromMin.get(maxHeap.peek()) + 1);
            }
            else {
                shouldBeDeletedFromMin.put(maxHeap.peek(), 1);
            }

            if (shouldBeDeletedFromMax.containsKey(minHeap.peek())) {
                shouldBeDeletedFromMax.put(minHeap.peek(), shouldBeDeletedFromMax.get(minHeap.peek()) + 1);
            }
            else {
                shouldBeDeletedFromMax.put(minHeap.peek(), 1);
            }
            while (!maxHeap.isEmpty() & shouldBeDeletedFromMax.containsKey(minHeap.peek())) {
                if (shouldBeDeletedFromMax.get(minHeap.peek()) == 1) {
                    shouldBeDeletedFromMax.remove(minHeap.remove());
                } else {
                    shouldBeDeletedFromMax.put(minHeap.peek(), shouldBeDeletedFromMax.get(minHeap.peek()) - 1);
                }
            }

            while (!minHeap.isEmpty() & shouldBeDeletedFromMin.containsKey(maxHeap.peek())) {
                if (shouldBeDeletedFromMin.get(maxHeap.peek()) == 1) {
                    shouldBeDeletedFromMin.remove(maxHeap.remove());
                } else {
                    shouldBeDeletedFromMin.put(maxHeap.peek(), shouldBeDeletedFromMin.get(maxHeap.peek()) - 1);
                }
            }
            total += (1+1);

        }
        System.out.println(total);
        sc.close();
    }
}
