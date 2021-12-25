package Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TheShortestPath {

    static List<List<Node>> graph;
    static int[] distance;
    static int[] path;

    private static void solution(int startPoint) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        int n = graph.size();
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
        heap.add(new Node(startPoint, 0));
        distance[startPoint] = 0;
        while (!heap.isEmpty()) {
            Node currentNode = heap.remove();
            int currentNodeId = currentNode.id;
            int currentDistance = currentNode.distance;
            if (distance[currentNodeId] != currentDistance) {
                continue;
            }
            for (int i = 0; i < graph.get(currentNodeId).size(); i++) {
                Node nextNode = graph.get(currentNodeId).get(i);
                if (currentDistance + nextNode.distance < distance[nextNode.id]) {
                    distance[nextNode.id] = currentDistance + nextNode.distance;
                    heap.add(new Node(nextNode.id, distance[nextNode.id]));
                    path[nextNode.id] = currentNodeId;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTestCase = Integer.parseInt(sc.next());
        for (int test = 0; test < numberOfTestCase; test++) {
            List<String> cities = new ArrayList<>();
            graph = new ArrayList<>();
            int numberOfCities = Integer.parseInt(sc.next());
            for (int i = 0; i < numberOfCities + 1; i++) {
                graph.add(new ArrayList<>());
            }
            for (int city = 0; city < numberOfCities; city++) {
                String cityName = sc.next();
                cities.add(cityName);
                int connections = Integer.parseInt(sc.next());
                for (int c = 0; c < connections; c++) {
                    int nextCityId = Integer.parseInt(sc.next());
                    int weight = Integer.parseInt(sc.next());
                    graph.get(nextCityId).add(new Node(city + 1, weight));
                    graph.get(city + 1).add(new Node(nextCityId, weight));
                }
            }
            int numberOfPathToFind = Integer.parseInt(sc.next());
            for (int i = 0; i < numberOfPathToFind; i++) {
                String startPoint = sc.next();
                String destination = sc.next();
                distance = new int[graph.size()];
                path = new int[graph.size()];
                solution(cities.indexOf(startPoint) + 1);
                System.out.println(distance[cities.indexOf(destination) + 1]);
            }
        }



        sc.close();
    }
}
