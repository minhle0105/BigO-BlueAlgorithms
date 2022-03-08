package Disjoint_Union_Set;

import java.util.Scanner;

public class IceSkating {

    static int[] xS;
    static int[] yS;

    static void dfs(int n, int point, int[] visited) {
        visited[point] = 1;
        for (int i = 0; i < n; i++) {
            if  ( (xS[i] == xS[point] || yS[i] == yS[point]) && visited[i] == 0 ) {
                dfs(n, i, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int xIndex = 0;
        int yIndex = 0;
        xS = new int[n];
        yS = new int[n];
        for (int i = 0; i < n; i++) {
            xS[xIndex++] = Integer.parseInt(sc.next());
            yS[yIndex++] = Integer.parseInt(sc.next());
        }
        int count = -1;
        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            if(visited[i] == 0) {
                dfs(n, i, visited);
                count++;
            }
        }
        System.out.println(count);

        sc.close();
    }
}
