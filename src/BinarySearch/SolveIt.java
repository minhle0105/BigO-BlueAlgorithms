package BinarySearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SolveIt {
    private static double isRoot(int p, int q, int r, int s, int t, int u, double x) {
        return p * Math.exp(-x) + q * Math.sin(x) + r * Math.cos(x) + s * Math.tan(x) + t * Math.pow(x, 2) + u;
    }

    private static double solveRoot(int p, int q, int r, int s, int t, int u) {
        double left = 0;
        double right = 1;
        while (left <= right) {
            double mid = (left + right) / 2;
            double root = isRoot(p, q, r, s, t, u, mid);
            if (Math.abs(root - 0) < 0.001) {
                return mid;
            }
            else if (root > 0) {
                left = mid + 0.0001;
            }
            else {
                right = mid - 0.0001;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        List<String> results = new ArrayList<>();
        while (sc.hasNext()) {
            int p = Integer.parseInt(sc.next());
            int q = Integer.parseInt(sc.next());
            int r = Integer.parseInt(sc.next());
            int s = Integer.parseInt(sc.next());
            int t = Integer.parseInt(sc.next());
            int u = Integer.parseInt(sc.next());
            double result = solveRoot(p, q, r, s, t, u);
            if (result == -1) {
                results.add("No solution");
            }
            else {
                results.add(result + "");
            }
        }
        for (String result : results) {
            if (result.equals("No solution")) {
                System.out.println(result);
            }
            else {
                System.out.printf("%.4f", Double.parseDouble(result));
                System.out.println();
            }
        }
        sc.close();
    }
}
