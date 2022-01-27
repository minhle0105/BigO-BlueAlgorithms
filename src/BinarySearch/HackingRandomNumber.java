package BinarySearch;

import java.util.*;

public class HackingRandomNumber {

    private static int binarySearch(int[] numbers, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] == target) {
                return mid;
            }
            else if (target < numbers[mid]) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int solution(int[] numbers, int k, int n) {
        int count = 0;
        Arrays.sort(numbers);
        for (int i = 0; i < n; i++) {
            int response = binarySearch(numbers, numbers[i] - k, i, n - 1);
            int response2 = binarySearch(numbers, numbers[i] + k, i, n - 1);
            if (response != -1  | response2 != -1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int k = Integer.parseInt(sc.next());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(sc.next());
        }
        System.out.println(solution(numbers, k, n));
        sc.close();
    }
}
