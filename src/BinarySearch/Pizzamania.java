package BinarySearch;

import java.util.*;

public class Pizzamania {

    private static int getSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }
        int pairCount = 0;
        for (int i = 0; i < numbers.length; i++) {
            int difference = target - numbers[i];
            if (map.containsKey(difference)) {
                pairCount++;
            }
        }
        return pairCount / 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        int[] results = new int[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int n = Integer.parseInt(sc.next());
            int[] numbers = new int[n];
            int price = Integer.parseInt(sc.next());
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(sc.next());
            }
            int pairCount = getSum(numbers, price);
            results[test] = pairCount;
        }
        for (int result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
