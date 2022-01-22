package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Marble {

    private static int binarySearch(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if ((mid == 0 || numbers[mid - 1] != target) && numbers[mid] == target) {
                return mid;
            }
            else if (target <= numbers[mid]) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseCount = 1;
        while (true) {
            List<Integer> results = new ArrayList<>();
            int n = Integer.parseInt(sc.next());
            int q = Integer.parseInt(sc.next());
            if (n == 0 & q == 0) break;
            int[] marbles = new int[n];
            for (int i = 0; i < n; i++) {
                marbles[i] = Integer.parseInt(sc.next());
            }
            int[] targets = new int[q];
            Arrays.sort(marbles);
            for (int i = 0; i < q; i++) {
                int target = Integer.parseInt(sc.next());
                targets[i] = target;
                int position = binarySearch(marbles, target);
                results.add(position);
            }
            System.out.println("CASE #" + caseCount);
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i) == -1) {
                    System.out.println(targets[i] + " not found");
                }
                else {
                    System.out.println(targets[i] + " found at " + (results.get(i) + 1));
                }
            }
            caseCount++;
        }
        sc.close();
    }
}
