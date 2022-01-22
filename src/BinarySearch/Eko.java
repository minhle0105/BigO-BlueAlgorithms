package BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class Eko {

    private static long calculate(long[] numbers, long limit) {
        long left = 0;
        long right = numbers[numbers.length - 1];
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for (long number : numbers) {
                if (number > mid) {
                    sum += number - mid;
                }
            }
            if (sum == limit) {
                return mid;
            }
            else if (sum < limit) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return left - 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int limit = Integer.parseInt(sc.next());
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(sc.next());
        }
        Arrays.sort(numbers);
        long result = calculate(numbers, limit);
        System.out.println(result);
        sc.close();
    }
}
