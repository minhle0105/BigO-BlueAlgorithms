package Disjoint_Union_Set;

import java.util.*;

public class TwoSets {

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
        }
        else if (ranks[uParent] < ranks[vParent]) {
            parents[uParent] = vParent;
        }
        else {
            parents[uParent] = vParent;
            ranks[vParent] ++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.next());
        int a = Integer.parseInt(sc.next());
        int b = Integer.parseInt(sc.next());
        int maxP = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int[] parent = new int[n + 1];
        int[] ranks = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = sc.nextInt();
            map.put(parent[i], i);
        }

        for (Integer key : map.keySet()) {
            if (key > maxP) {
                maxP = key;
            }
        }

//        if (maxP >= Math.max(a, b)) {
//            System.out.println("NO");
//            return;
//        }
        makeSet(n + 1, parent, ranks);
        int A = 0;
        int B = parent.length - 1;

        for (Integer key : map.keySet()) {
            int diff1 = a - key;
            if (map.containsKey(diff1)) {
                union(map.get(key), map.get(diff1), parent, ranks);
            }
            else {
                union(map.get(key), B, parent, ranks);
            }

            int diff2 = b - key;
            if (map.containsKey(diff2)) {
                union(map.get(key), map.get(diff2), parent, ranks);
            }
            else {
                union(map.get(key), A, parent, ranks);
            }
        }
        if (findSet(A, parent) == findSet(B, parent)) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
        for (int i = 1; i <= n; i++) {
            if (findSet(i, parent) == findSet(A, parent)) {
                System.out.print(0 + " ");
            }
            else {
                System.out.print(1 + " ");
            }
        }
        sc.close();
    }
}
