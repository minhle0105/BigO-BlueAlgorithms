package Disjoint_Union_Set;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Friends {

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

    private static void unionSet(int u, int v, int[] parents, int[] ranks) {
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
        int numberOfTests = Integer.parseInt(sc.next());
        int[] results = new int[numberOfTests];
        for (int test = 0; test < numberOfTests; test++) {
            int n = Integer.parseInt(sc.next());
            int m = Integer.parseInt(sc.next());
            int max = n + 1;
            int[] parents = new int[max];
            int[] ranks = new int[max];
            makeSet(max, parents, ranks);
            for (int i = 0; i < m; i++) {
                int a = Integer.parseInt(sc.next());
                int b = Integer.parseInt(sc.next());
                unionSet(a, b, parents, ranks);
            }
            for (int i = 1; i < parents.length; i++) {
                parents[i] = findSet(i, parents);
            }
            Map<Integer, Integer> countGroupsOfFriends = new HashMap<>();
            for (int parent : parents) {
                if (countGroupsOfFriends.containsKey(parent)) {
                    countGroupsOfFriends.put(parent, countGroupsOfFriends.get(parent) + 1);
                }
                else {
                    countGroupsOfFriends.put(parent, 1);
                }
            }
            int result = -1;
            for (Integer value : countGroupsOfFriends.values()) {
                if (value > result) {
                    result = value;
                }
            }
            results[test] = result;
        }
        for (int result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
