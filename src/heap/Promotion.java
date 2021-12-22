package heap;

import java.util.*;

public class Promotion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> deletedFromMin = new PriorityQueue<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> deletedFromMax = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        int days = Integer.parseInt(sc.next());
        long total = 0;
        // 1 2 3 -> 2
        // 2 1 1 -> 1
        // 1 10 5 5 1 -> 9
        // 5 5 1 -> 4
        // 5 1 2 -> 4
        Map<Integer, Integer> bills = new HashMap<>();
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

            while (!deletedFromMax.isEmpty() && !maxHeap.isEmpty() && maxHeap.peek().equals(deletedFromMax.peek())) {
                maxHeap.remove();
                deletedFromMax.remove();
            }
            while (!deletedFromMin.isEmpty() && !minHeap.isEmpty() && minHeap.peek().equals(deletedFromMin.peek())) {
                minHeap.remove();
                deletedFromMin.remove();
            }
            int maxBill = maxHeap.remove();
            int minBill = minHeap.remove();
            deletedFromMax.add(minBill);
            deletedFromMin.add(maxBill);

            total += (maxBill - minBill);

        }
        System.out.println(total);
        sc.close();
    }
}
