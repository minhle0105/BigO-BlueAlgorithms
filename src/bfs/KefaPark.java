package bfs;

import java.util.*;

public class KefaPark {

    private static int solution (List<List<Integer>> graph, int[] cats, int catTolerance) {
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, Boolean> hasRestaurant = new HashMap<>();
        int consecutiveCats = 0;
        for (int i = 1; i < graph.size(); i++) {
            if (graph.get(i).size() == 1) {
                hasRestaurant.put(i, true);
            }
            else {
                hasRestaurant.put(i, false);
            }
        }

        int topVer = 1;
        List<Integer> path = new ArrayList<>();
        List<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            visited.add(false);
            path.add(-1);
        }

        visited.set(topVer, true);
        queue.add(topVer);
        while (!queue.isEmpty()) {
            int removedVer = queue.remove();
            if (cats[removedVer - 1] == 1) {
                consecutiveCats++;
            }
            for (int i = 0; i < graph.get(removedVer).size(); i++) {
                int nextVer = graph.get(removedVer).get(i);
                if (cats[nextVer - 1] == 1) {
                    if (consecutiveCats < catTolerance) {
                        consecutiveCats++;
                    }
                    else {
                        continue;
                    }
                }
                if (cats[nextVer - 1] == 0 && !visited.get(nextVer)) {
                    queue.add(nextVer);
                    visited.set(nextVer, true);
                    path.set(nextVer, removedVer);
                    consecutiveCats = 0;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i) != -1 && hasRestaurant.get(i)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = Integer.parseInt(sc.next());
        int e = v - 1;
        int catTolerance = Integer.parseInt(sc.next());
        List<List<Integer>> graph = new ArrayList<>();
        int[] hasCat = new int[v];
        for (int i = 0; i < hasCat.length; i++) {
            hasCat[i] = Integer.parseInt(sc.next());
        }

        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());
        }



        for (int i = 0; i < e; i++) {
            int v1 = Integer.parseInt(sc.next());
            int v2 = Integer.parseInt(sc.next());
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        System.out.println(solution(graph, hasCat, catTolerance));
        sc.close();
    }
}
