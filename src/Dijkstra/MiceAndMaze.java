package Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MiceAndMaze {

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
        graph = new ArrayList<>();
        int numberOfVertices = Integer.parseInt(sc.next());
        int destination = Integer.parseInt(sc.next());
        int timeLimit = Integer.parseInt(sc.next());
        int numberOfConnections = Integer.parseInt(sc.next());

        for (int i = 0; i < numberOfVertices + 1; i++) {
            graph.add(new ArrayList<>());
        }
        distance = new int[graph.size()];
        path = new int[graph.size()];

        for (int i = 0; i < numberOfConnections; i++) {
            int a = Integer.parseInt(sc.next());
            int b = Integer.parseInt(sc.next());
            int weight = Integer.parseInt(sc.next());
            // đảo ngược graph -> giảm thời gian thay vì phải đi từ mỗi đỉnh tới đích -> đi từ đích tới mỗi đỉnh
            graph.get(b).add(new Node(a, weight));
        }

        solution(destination);

        int count = 0;
        for (int d : distance) {
            if (d <= timeLimit) {
                count++;
            }
        }
        System.out.println(count);
        sc.close();
    }
}
