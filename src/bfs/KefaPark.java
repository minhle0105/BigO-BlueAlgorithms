package bfs;

import java.util.*;

public class KefaPark {

    private static int solution (List<List<Integer>> graph, int[] cats, int catTolerance) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> restaurants = new ArrayList<>();
        for (int i = 2; i < graph.size(); i++) {
            if (graph.get(i).size() == 1) {
                restaurants.add(i);
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
            for (int i = 0; i < graph.get(removedVer).size(); i++) {
                int nextVer = graph.get(removedVer).get(i);
                if (!visited.get(nextVer)) {
                    queue.add(nextVer);
                    visited.set(nextVer, true);
                    path.set(nextVer, removedVer);
                }
            }
        }

        int count = 0;

        for (Integer restaurant : restaurants) {
            int consecutiveCats = 0;
            int previousPoint = path.get(restaurant);
            if (cats[restaurant - 1] == 1) {
                consecutiveCats ++;
            }
            if (cats[previousPoint - 1] == 1) {
                consecutiveCats++;
            }
            else {
                consecutiveCats = 0;
            }
            if (consecutiveCats > catTolerance) {
                continue;
            }
            while (path.get(previousPoint) != -1) {
                previousPoint = path.get(previousPoint);
                if (cats[previousPoint - 1] == 1) {
                    consecutiveCats++;
                }
                else {
                    consecutiveCats = 0;
                }
                if (consecutiveCats > catTolerance) {
                    break;
                }
            }
            if (previousPoint == 1 && consecutiveCats <= catTolerance) {
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
