package BST;

import java.util.Scanner;
import java.util.TreeSet;

public class DistinctCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        String[] results = new String[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int n = Integer.parseInt(sc.next());
            int x = Integer.parseInt(sc.next());
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(sc.next());
            }
            int numberOfDistinct = countDistinct(nums);
            if (numberOfDistinct == x) {
                results[test] = "Good";
            }
            else if (numberOfDistinct < x) {
                results[test] = "Bad";
            }
            else {
                results[test] = "Average";
            }
        }
        for (String result : results) {
            System.out.println(result);
        }
        sc.close();
    }

    private static int countDistinct(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : nums) {
            set.add(i);
        }
        return set.size();
    }
}
