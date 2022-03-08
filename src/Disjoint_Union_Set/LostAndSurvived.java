package Disjoint_Union_Set;

import java.util.*;

public class LostAndSurvived {

    private static void makeSet(int max, int[] parents, int[] ranks) {
        for (int i = 1; i < max; i++) {
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

    private static int unionSet(int u, int v, int[] parents, int[] ranks) {
        int uParent = findSet(u, parents);
        int vParent = findSet(v, parents);
        if (uParent == vParent) {
            return -1;
        }
        if (ranks[uParent] > ranks[vParent]) {
            parents[vParent] = uParent;
            return u;
        }
        else if (ranks[uParent] < ranks[vParent]) {
            parents[uParent] = vParent;
            return v;
        }
        else {
            parents[uParent] = vParent;
            ranks[vParent] ++;
            return v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int q = Integer.parseInt(sc.next());
        int[] parents = new int[n + 1];
        int[] ranks = new int[n + 1];
        makeSet(n + 1, parents, ranks);
        int[] results = new int[q];
        int max = 1;
        int[] parentCount = new int[n + 1];
        for (int i = 1; i < parentCount.length; i++) {
            parentCount[i] = 1;
        }

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(1, n);
        for (int i = 2; i <= n; i++) {
            treeMap.put(i, 0);
        }


        for (int i = 0; i < q; i++) {
            int a = Integer.parseInt(sc.next());
            int b = Integer.parseInt(sc.next());
            int valA = parentCount[findSet(a, parents)];
            int valB = parentCount[findSet(b, parents)];
            int repB = findSet(b, parents);
            int repA = findSet(a, parents);
            int par = unionSet(a, b, parents, ranks);
            if (repA == repB) {
                results[i] = max - treeMap.firstKey();
                continue;
            }
            parentCount[par] += par == repB ? valB : valA;
            max = Math.max(max, parentCount[par]);
            if (treeMap.containsKey(parentCount[par])) {
                treeMap.put(parentCount[par], treeMap.get(parentCount[par]) + 1);
            }
            else {
                treeMap.put(parentCount[par], 1);
            }
            if (treeMap.containsKey(valA)) {
                treeMap.put(valA, treeMap.get(valA) - 1);
            }
            if (treeMap.containsKey(valB)) {
                treeMap.put(valB, treeMap.get(valB) - 1);
            }

            if (treeMap.containsKey(valA) && treeMap.get(valA) == 0) {
                treeMap.remove(valA);
            }
            if (treeMap.containsKey(valB) && treeMap.get(valB) == 0) {
                treeMap.remove(valB);
            }
            int min = treeMap.firstKey();
            results[i] = max - min;
        }

        for (int result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
