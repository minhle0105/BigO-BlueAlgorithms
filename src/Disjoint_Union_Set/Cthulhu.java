package Disjoint_Union_Set;

import java.util.Scanner;

public class Cthulhu {
    static int findSet(int n, int[] parents) {
        if (parents[n] == n) {
            return n;
        }
        return findSet(parents[n], parents);
    }

    static void union(int x, int y, int[] parents) {
        x = findSet(x, parents);
        y = findSet(y, parents);
        if (x != y) {
            parents[x] = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int m = Integer.parseInt(sc.next());
        int[] parents = new int[n + 1];
        for (int i = 1; i < n + 1; i ++) {
            parents[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int x = Integer.parseInt(sc.next());
            int y = Integer.parseInt(sc.next());
            union(x, y, parents);
        }
        boolean result = true;
        for (int i = 2; i <= n; i++) {
            if (findSet(1, parents) != findSet(i, parents)) {
                result = false;
                break;
            }
        }
        if (n != m) {
            result = false;
        }
        if (result) {
            System.out.println("FHTAGN");
        }
        else {
            System.out.println("NO");
        }
        sc.close();
    }
}
