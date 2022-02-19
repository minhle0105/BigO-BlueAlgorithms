package Prim;

import java.util.*;

class RoadNode implements Comparable<RoadNode> {
    long id;
    long dist;

    public RoadNode(long id, long dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(RoadNode o) {
        return Long.compare(this.dist, o.dist);
    }
}

public class RoadConstruction {

    private static long[] dist;
    private static long[] path;
    private static boolean[] visited;

    private static void prims(int src, List<List<RoadNode>> graph) {
        PriorityQueue<RoadNode> heap = new PriorityQueue<>();
        int n = graph.size();
        dist = new long[n];
        path = new long[n];
        visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        heap.add(new RoadNode(src, 0));
        dist[src] = 0;
        while (!heap.isEmpty()) {
            RoadNode top = heap.remove();
            long u = top.id;
            visited[(int) u] = true;
            for (int i = 0; i < graph.get((int) u).size(); i++) {
                RoadNode neighbor = graph.get((int) u).get(i);
                long v = neighbor.id;
                long cost = neighbor.dist;
                if (!visited[(int) v] && cost < dist[(int) v]) {
                    dist[(int) v] = cost;
                    heap.add(new RoadNode(v, cost));
                    path[(int) v] = u;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.nextLine());
        String[] results = new String[numberOfTest];
        for (int test = 0; test < numberOfTest; test++){
            sc.nextLine();
            int n = Integer.parseInt(sc.nextLine());
            List<List<RoadNode>> graph = new ArrayList<>();
            Map<String, Integer> mapCityName = new HashMap<>();
            int id = 0;
            List<String[]> information = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] thisInfo = sc.nextLine().split(" ");
                information.add(thisInfo);
                if (!mapCityName.containsKey(thisInfo[0])) {
                    mapCityName.put(thisInfo[0], id++);
                }
                if (!mapCityName.containsKey(thisInfo[1])) {
                    mapCityName.put(thisInfo[1], id++);
                }
            }

            int numberOfCities = mapCityName.size();
            for (int i = 0; i < numberOfCities; i++) {
                graph.add(new ArrayList<>());
            }

            for (String[] thisInfo : information) {
                String city1 = thisInfo[0];
                String city2 = thisInfo[1];
                int cost = Integer.parseInt(thisInfo[2]);
                int id1 = mapCityName.get(city1);
                int id2 = mapCityName.get(city2);
                graph.get(id1).add(new RoadNode(id2, cost));
                graph.get(id2).add(new RoadNode(id1, cost));
            }
            prims(0, graph);
            boolean isImpossible = false;
            for (boolean isVisited : visited) {
                if (!isVisited) {
                    isImpossible = true;
                    break;
                }
            }
            if (isImpossible) {
                results[test] = "Impossible";
            }
            else {
                long result = 0;
                for (long d : dist) {
                    if (d != Integer.MAX_VALUE) {
                        result += d;
                    }
                }
                results[test] = result + "";
            }
        }
        for (int i = 0; i < results.length; i++) {
            System.out.println("Case " + (i + 1) + ": " + results[i]);
        }
        sc.close();
    }
}
