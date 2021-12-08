package bfs;

import java.util.*;

public class Dhoom {
    private static long solution(long myKey, long answer, int[] nums) {
        final int c = 100000;
        Queue<Long> queue = new LinkedList<>();
        queue.add(myKey);
        List<Long> levels = new ArrayList<>();
        List<Long> visited = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            levels.add(0L);
            visited.add(0L);
        }
        visited.set((int) (myKey % c), 1L);

        while (!queue.isEmpty()) {
            long removedNumber = queue.remove();
            for (int num : nums) {
                long nextNumber = removedNumber * num;
                if (visited.get((int) (nextNumber % c)) == 0) {
                    queue.add(nextNumber % c);
                    levels.set((int) (nextNumber % c), levels.get((int) removedNumber) + 1);
                    visited.set((int) (nextNumber % c), 1L);
                }
                if (nextNumber % c == answer) {
                    return levels.get((int) (nextNumber % c));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int myKey = Integer.parseInt(sc.next());
        int answer = Integer.parseInt(sc.next());
        int n = Integer.parseInt(sc.next());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(sc.next());
        }
        System.out.println(solution(myKey, answer, nums));
        sc.close();
    }
}
