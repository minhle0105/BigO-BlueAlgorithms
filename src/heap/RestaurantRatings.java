package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RestaurantRatings {

    private static String solution(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        int n = maxHeap.size() + minHeap.size();
        if (n < 3) {
            return "No reviews yet";
        }
        int valueToReturn = minHeap.peek();
        return valueToReturn + "";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfQueries = Integer.parseInt(sc.next());
        List<String> output = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < numberOfQueries; i++) {
            int nextQuery = Integer.parseInt(sc.next());
            if (nextQuery == 1) {
                int value = Integer.parseInt(sc.next());
                if (!minHeap.isEmpty() && value > minHeap.peek()) {
                    minHeap.add(value);
                    if (minHeap.size() > (int) (maxHeap.size() + minHeap.size()) / 3) {
                        maxHeap.add(minHeap.remove());
                    }
                }

                else {
                    maxHeap.add(value);
                    if (maxHeap.size() >= 3 && minHeap.size() < (int) (maxHeap.size() + minHeap.size()) / 3) {
                        minHeap.add(maxHeap.remove());
                    }
                }
            }
            else {
                output.add(solution(maxHeap, minHeap));
            }
        }
        for (String s : output) {
            System.out.println(s);
        }
        sc.close();
    }
}
