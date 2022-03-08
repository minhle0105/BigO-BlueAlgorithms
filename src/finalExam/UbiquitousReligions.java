package finalExam;

import java.util.*;

public class UbiquitousReligions {
    private static int numberOfReligion;

    private static void makeSet(int max, int[] parents, int[] ranks) {
        for (int i = 0; i < max; i++) {
            parents[i] = i;
            ranks[i] = 0;
        }
    }

    private static int findSet(int u, int[] parents) {
        if (parents[u] != u) {
            parents[u] = findSet(parents[u], parents);
        }
        return parents[u];
    }

    private static void union(int u, int v, int[] parents, int[] ranks) {
        int uParent = findSet(u, parents);
        int vParent = findSet(v, parents);
        if (uParent == vParent) {
            return;
        }
        if (ranks[uParent] > ranks[vParent]) {
            parents[vParent] = uParent;
            numberOfReligion--;
        }
        else if (ranks[uParent] < ranks[vParent]) {
            parents[uParent] = vParent;
            numberOfReligion--;
        }
        else {
            parents[uParent] = vParent;
            ranks[vParent] ++;
            numberOfReligion--;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> results = new ArrayList<>();
        while (true) {
            int n = Integer.parseInt(sc.next());
            int m = Integer.parseInt(sc.next());
            if (n == 0 & m == 0) break;
            int[] parents = new int[n + 1];
            int[] ranks = new int[n + 1];
            numberOfReligion = n;
            makeSet(parents.length, parents, ranks);
            for (int i = 0; i < m; i++) {
                int a = Integer.parseInt(sc.next());
                int b = Integer.parseInt(sc.next());
                union(a, b, parents, ranks);
            }
            results.add(numberOfReligion);
        }
        for (int i = 0; i < results.size(); i++) {
            System.out.println("Case " + (i + 1) + ": " + results.get(i));
        }
        sc.close();
    }
}
