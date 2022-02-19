package Prim;

import java.util.*;

class Building {
    int id;
    int x;
    int y;
    List<Integer> neighbors;

    public Building(int id, int x, int y) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.neighbors = new ArrayList<>();
    }
}

public class ConnectCampus {

    private static long[] dist;
    private static long[] path;
    private static boolean[] visited;


    private static double getDistance(Building b1, Building b2) {
        double a = Math.pow(b1.x, 2) - Math.pow(b2.x, 2);
        double b = Math.pow(b1.y, 2) - Math.pow(b2.y, 2);
        return Math.sqrt(a + b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Building> buildings = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        int n = Integer.parseInt(sc.next());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(sc.next());
            int y = Integer.parseInt(sc.next());
            Building building = new Building(i + 1, x, y);
            buildings.add(building);
        }
        int numberOfConnection = Integer.parseInt(sc.next());
        for (int i = 0; i < n; i ++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < numberOfConnection; i++) {
            int src = Integer.parseInt(sc.next()); // 4
            int dst = Integer.parseInt(sc.next()); // 2
            graph.get(src).add(dst);
            graph.get(dst).add(src);
        }
        List<List<Building>> allConnectedGraph = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                allConnectedGraph.get(i).add(new Building(j, get))
//            }
//        }
        sc.close();
    }
}
