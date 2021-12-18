package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RestaurantRatings {

    private static String solution(PriorityQueue<Integer> heap) {
        int n = heap.size();
        if (n < 3) {
            return "No reviews yet";
        }
        int numberOfValuesToGet = n / 3;
        int[] temp = new int[numberOfValuesToGet];
        for (int i = 0; i < numberOfValuesToGet; i++) {
            temp[i] = heap.remove();
        }
        for (int i = 0; i < numberOfValuesToGet; i++) {
            heap.add(temp[i]);
        }
        int valueToReturn = temp[temp.length - 1];
        return valueToReturn + "";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfQueries = Integer.parseInt(sc.next());
        List<String> output = new ArrayList<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        for (int i = 0; i < numberOfQueries; i++) {
            int nextQuery = Integer.parseInt(sc.next());
            if (nextQuery == 1) {
                int value = Integer.parseInt(sc.next());
                heap.add(value);
            }
            else {
                output.add(solution(heap));
            }
        }
        for (String s : output) {
            System.out.println(s);
        }
        sc.close();
    }
}
