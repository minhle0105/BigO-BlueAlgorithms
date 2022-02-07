package BinarySearch;

import java.util.Scanner;

public class EnergyExchange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int k = Integer.parseInt(sc.next());
        int[] energies = new int[n];
        double minVal = 1000;
        for (int i = 0; i < n; i++) {
            energies[i] = Integer.parseInt(sc.next());
            minVal = Math.min(minVal, energies[i]);
        }
        double result = binarySearch(energies, n, k, minVal);
        System.out.println(result);
        sc.close();
    }

    private static double binarySearch(int[] energies, int n, int k, double min) {
        double max = 1000;
        double EPSILON = 1e-8;
        double x = 1.0 - k / 100.0;
        while (max - min >= EPSILON) {
            double mid = (max + min) / 2;
            double a = 0;
            double b = 0;
            for (int i = 0; i < n; i++) {
                if (energies[i] > mid) {
                    a += energies[i] - mid;
                }
                else {
                    b += mid - energies[i];
                }
            }
            if (a * x >= b) {
                min = mid;
            }
            else {
                max = mid;
            }
        }
        return max;
    }
}
