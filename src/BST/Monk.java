package BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Monk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        List<String> results = new ArrayList<>();
        for (int test = 0; test < numberOfTest; test++) {
            int n = Integer.parseInt(sc.next());
            int m = Integer.parseInt(sc.next());
            long[] numbers = new long[n];
            long[] queries = new long[m];
            for (int i = 0; i < n; i++) {
                numbers[i] = Long.parseLong(sc.next());
            }
            for (int i = 0; i < m; i++) {
                queries[i] = Long.parseLong(sc.next());
            }
            boolean[] outcomes = check(numbers, queries);
            for (boolean outcome : outcomes) {
                if (outcome) {
                    results.add("YES");
                }
                else {
                    results.add("NO");
                }
            }
        }
        for (String result : results) {
            System.out.println(result);
        }
        sc.close();
    }

    private static boolean[] check (long[] numbers, long[] queries) {
        TreeSet<Long> tree = new TreeSet<>();
        for (long number : numbers) {
            tree.add(number);
        }
        boolean[] results = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            results[i] = tree.contains(queries[i]);
            tree.add(queries[i]);
        }
        return results;
    }
}