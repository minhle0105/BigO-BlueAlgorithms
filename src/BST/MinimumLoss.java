package BST;

import java.util.Scanner;
import java.util.TreeSet;

public class MinimumLoss {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(sc.next());
        }
        TreeSet<Long> tree = new TreeSet<>();
        long minLoss = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long thisPrice = numbers[i];
            Long prevPrice = tree.ceiling(thisPrice);
            tree.add(thisPrice);
            if (prevPrice != null) {
                long thisLoss = prevPrice - thisPrice;
                if (thisLoss > 0 && thisLoss < minLoss) {
                    minLoss = thisLoss;
                }
            }
        }
        System.out.println(minLoss);
    }
}
