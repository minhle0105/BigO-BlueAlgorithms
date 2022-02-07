package BinarySearch;

import java.util.Scanner;

public class MonkeyOiledBamboo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        int[] results = new int[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int n = Integer.parseInt(sc.next());
            int[] steps = new int[n + 1];
            steps[0] = 0;
            for (int i = 1; i <= n; i++) {
                steps[i] = Integer.parseInt(sc.next());
            }
            results[test] = binarySearch(steps, n);
        }
        for (int i = 0; i < results.length; i++) {
            System.out.println("Case " + (i + 1) + ": " + results[i]);
        }
        sc.close();
    }

    private static int binarySearch(int[] steps, int n) {
        int[] transition = new int[n];
        for (int i = 0; i < n; i++) {
            transition[i] = steps[i + 1] - steps[i];
        }
        int ans = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (ans == transition[i]) {
                ans++;
            }
            ans = Math.max(ans, transition[i]);
        }
        return ans;
    }
}
