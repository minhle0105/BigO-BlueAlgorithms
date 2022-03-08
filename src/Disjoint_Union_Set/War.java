package Disjoint_Union_Set;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class War {

    static int findSet(int u, int[] parents) {
        if (u == parents[u]) {
            return u;
        }
        return findSet(parents[u], parents);
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] parents = new int[n * 2];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        while (true) {
            int query = Integer.parseInt(sc.next());
            int a = Integer.parseInt(sc.next());
            int b = Integer.parseInt(sc.next());
            if (query == 0 & a == 0 & b == 0) {
                break;
            }
            int a1 = findSet(a, parents);
            int a2 = findSet(a + n, parents);
            int b1 = findSet(b, parents);
            int b2 = findSet(b + n, parents);
            if (query == 1) {
                if (a1 == b2) {
                    System.out.println("-1");
                }
                else {
                    parents[a1] = b1;
                    parents[a2] = b2;
                }
            }
            if (query == 2) {
                if (a1 == b1) {
                    System.out.println("-1");
                }
                else {
                    parents[a1] = b2;
                    parents[a2] = b1;
                }
            }
            if (query == 3) {
                System.out.println(a1 == b1 ? 1 : 0);
            }
            if (query == 4) {
                System.out.println(a1 == b2 ? 1 : 0);
            }

        }
        sc.close();
    }
}
