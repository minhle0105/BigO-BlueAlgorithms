package heap;

import java.util.*;

public class AddAll {

    private static long solution(List<Integer> numbers) {
        long cost = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.addAll(numbers);

        while (heap.size() > 1) {
            int a = heap.remove();
            int b = heap.remove();
            cost += a + b;
            heap.add(a+b);
        }
        return cost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Long> results = new ArrayList<>();
        while (true) {
            int n = Integer.parseInt(sc.next());
            if (n == 0) break;
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                numbers.add(Integer.parseInt(sc.next()));
            }
            results.add(solution(numbers));
        }

        for (Long result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
