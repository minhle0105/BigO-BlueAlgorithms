package Disjoint_Union_Set;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GraphConnectivity {

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


    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input1.txt"));
        int numberOfTest = Integer.parseInt(sc.nextLine());
        sc.nextLine();
        int[] results = new int[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            String start = sc.nextLine();
            int n = start.charAt(0) - 'A' + 1;
            int[] parents = new int[n];
            int[] ranks = new int[n];
            makeSet(n, parents, ranks);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.length() == 0) break;
                int a = line.charAt(0) - 'A';
                int b = line.charAt(1) - 'A';
                unionSet(a, b, parents, ranks);
            }
            for (int i = 0; i < parents.length; i++) {
                parents[i] = findSet(i, parents);
            }
            Set<Integer> set = new HashSet<>();
            for (int i : parents){
                set.add(i);
            }
            results[test] = set.size();
        }
        for (int result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
