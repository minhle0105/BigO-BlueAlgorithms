package heap;

import java.util.*;

class MaxHeapComparator implements Comparator<Long> {

    @Override
    public int compare(Long o1, Long o2) {
        return o2.compareTo(o1);
    }
}


public class MonkMultiplication {

    // 1 2 3 4 5

    private static List<Long> solution (List<Long> nums) {
        PriorityQueue<Long> heap = new PriorityQueue<Long>(new MaxHeapComparator());
        List<Long> result = new ArrayList<>();
        for (Long num : nums) {
            heap.add(num);
            if (heap.size() < 3) {
                result.add(-1L);
            } else {
                long thisRound = 1L;
                long[] temp = new long[3];
                for (int j = 0; j < 3; j++) {
                    temp[j] = heap.remove();
                    thisRound *= temp[j];
                }
                result.add(thisRound);
                for (long j : temp) {
                    heap.add(j);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        List<Long> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(Long.parseLong(sc.next()));
        }
        List<Long> result = solution(nums);
        for (Long i : result) {
            System.out.println(i);
        }
        sc.close();
    }
}
