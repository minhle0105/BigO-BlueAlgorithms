package Dijkstra;

import java.util.*;

class City implements Comparable<City> {
    public int id;
    public int distance;

    public City(int id, int distance) {
        this.id = id;
        this.distance = distance;
    }

    @Override
    public int compareTo(City o) {
        return Integer.compare(this.distance, o.distance);
    }
}

public class ChocolateJourney {

    private static void solution(List<List<City>> graph, int startPoint, int[] distance, int[] path) {
        PriorityQueue<City> heap = new PriorityQueue<>();
        int n = graph.size();
        heap.add(new City(startPoint, 0));
        distance[startPoint] = 0;
        while (!heap.isEmpty()) {
            City currentNode = heap.remove();
            int currentNodeId = currentNode.id;
            int currentDistance = currentNode.distance;
            if (distance[currentNodeId] != currentDistance) {
                continue;
            }
            for (int i = 0; i < graph.get(currentNodeId).size(); i++) {
                City nextNode = graph.get(currentNodeId).get(i);
                if (currentDistance < Integer.MAX_VALUE && currentDistance + nextNode.distance < distance[nextNode.id]) {
                    distance[nextNode.id] = currentDistance + nextNode.distance;
                    heap.add(new City(nextNode.id, distance[nextNode.id]));
                    path[nextNode.id] = currentNodeId;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfCities = Integer.parseInt(sc.next());
        int numberOfTwoWayConnections = Integer.parseInt(sc.next());
        int numberOfCitiesWithChocolate = Integer.parseInt(sc.next());
        int expired = Integer.parseInt(sc.next());
        List<List<City>> graph;
        List<List<City>> reversedGraph;
        graph = new ArrayList<>();
        reversedGraph = new ArrayList<>();
        int[] distance;
        int[] reversedDistance;
        int[] path;
        for (int point = 0; point < numberOfCities + 1; point++) {
            graph.add(new ArrayList<>());
            reversedGraph.add(new ArrayList<>());
        }
        distance = new int[graph.size()];
        reversedDistance = new int[reversedGraph.size()];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            reversedDistance[i] = Integer.MAX_VALUE;
        }
        List<Integer> citiesWithChocolate = new ArrayList<>();
        for (int i = 0; i < numberOfCitiesWithChocolate; i++) {
            int cityId = Integer.parseInt(sc.next());
            citiesWithChocolate.add(cityId);
        }
        for (int i = 0; i < numberOfTwoWayConnections; i++) {
            int a = Integer.parseInt(sc.next());
            int b = Integer.parseInt(sc.next());
            int cost = Integer.parseInt(sc.next());
            graph.get(a).add(new City(b, cost));
            graph.get(b).add(new City(a, cost));
            reversedGraph.get(b).add(new City(a, cost));
            reversedGraph.get(a).add(new City(b, cost));
        }
        path = new int[graph.size()];
        Arrays.fill(path, -1);
        int startPoint = Integer.parseInt(sc.next());
        int endPoint = Integer.parseInt(sc.next());
        solution(graph, startPoint, distance, path);
        solution(reversedGraph, endPoint, reversedDistance, path);
        int result = Integer.MAX_VALUE;
        List<Integer> d = new ArrayList<>();
        for (Integer cityId : citiesWithChocolate) {
            if (reversedDistance[cityId] <= expired) {
                d.add(reversedDistance[cityId] + distance[cityId]);
            }
        }
        for (Integer integer : d) {
            if (integer < result) {
                result = integer;
            }
        }
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(result);
        }
        sc.close();
    }
}
