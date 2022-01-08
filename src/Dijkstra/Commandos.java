package Dijkstra;

import java.util.*;

public class Commandos {

    private static List<Integer> bfs(List<List<Integer>> buildings, int startPoint) {
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[buildings.size()];
        List<Integer> levels = new ArrayList<>();
        for (int i = 0; i < buildings.size(); i++) {
            levels.add(0);
        }
        queue.add(startPoint);
        visited[startPoint] = 1;
        while (!queue.isEmpty()) {
            int thisBuilding = queue.remove();
            for (int i = 0; i < buildings.get(thisBuilding).size(); i++) {
                int nextBuilding = buildings.get(thisBuilding).get(i);
                if (visited[nextBuilding] == 0) {
                    queue.add(nextBuilding);
                    visited[nextBuilding] = 1;
                    levels.set(nextBuilding, levels.get(thisBuilding) + 1);
                }
            }
        }
        return levels;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        int[] result = new int[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            List<List<Integer>> buildings = new ArrayList<>();
            int numberOfBuildings = Integer.parseInt(sc.next());
            int numberOfConnections = Integer.parseInt(sc.next());
            for (int i = 0; i < numberOfBuildings; i++) {
                buildings.add(new ArrayList<>());
            }
            for (int i = 0; i < numberOfConnections; i++) {
                int a = Integer.parseInt(sc.next());
                int b = Integer.parseInt(sc.next());
                buildings.get(a).add(b);
                buildings.get(b).add(a);
            }
            int startMission = Integer.parseInt(sc.next());
            int endMission = Integer.parseInt(sc.next());
            List<Integer> bfsFromStart = bfs(buildings, startMission);
            List<Integer> bfsFromDestination = bfs(buildings, endMission);
            List<Integer> sum = new ArrayList<>();
            for (int i = 0; i < bfsFromDestination.size(); i++) {
                sum.add(bfsFromStart.get(i) + bfsFromDestination.get(i));
            }
            int r = sum.get(0);
            for (int i = 1; i < sum.size(); i++) {
                if (sum.get(i) > r) {
                    r = sum.get(i);
                }
            }
            result[test] = r;
        }
        for (int i = 0; i < result.length; i++) {
            System.out.println("Case " + (i+1) + ": " + result[i]);
        }
        sc.close();
    }
}
