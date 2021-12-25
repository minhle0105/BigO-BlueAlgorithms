package Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node> {
    public int id;
    public int distance;

    public Node(int id, int distance) {
        this.id = id;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.distance, o.distance);
    }
}

public class TravellingCost {

    static List<List<Node>> graph;
    static int[] distance;
    static int[] path;

    private static void solution(int startPoint) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        int n = graph.size();
        distance = new int[n];
        path = new int[n];
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
        int numberOfConnections = Integer.parseInt(sc.next());
        graph = new ArrayList<>();
        for (int i = 0; i < 501; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < numberOfConnections; i++) {
            int a = Integer.parseInt(sc.next());
            int b = Integer.parseInt(sc.next());
            int weight = Integer.parseInt(sc.next());
            // graph 2 chiều
            graph.get(a).add(new Node(b, weight));
            graph.get(b).add(new Node(a, weight));
        }
        int startPoint = Integer.parseInt(sc.next());
        solution(startPoint);
        int numberOfDestination = Integer.parseInt(sc.next());
        List<Integer> destinations = new ArrayList<>();
        for (int i = 0; i < numberOfDestination; i++) {
            destinations.add(Integer.parseInt(sc.next()));
        }
        for (Integer destination : destinations) {
            if (distance[destination] != Integer.MAX_VALUE) {
                System.out.println(distance[destination]);
            } else {
                System.out.println("NO PATH");
            }
        }
        sc.close();
    }
}
