package Dijkstra;

import java.util.*;

//class Node implements Comparable<Node> {
//    public int id;
//    public int distance;
//
//    public Node(int id, int distance) {
//        this.id = id;
//        this.distance = distance;
//    }
//
//    @Override
//    public int compareTo(Node o) {
//        return Integer.compare(this.distance, o.distance);
//    }
//}

public class AlmostShortestPath {

    private static void solution(List<List<Node>> graph, int startPoint, int[] distance, int[] path) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        int n = graph.size();
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
                if (currentDistance < Integer.MAX_VALUE && currentDistance + nextNode.distance < distance[nextNode.id]) {
                    distance[nextNode.id] = currentDistance + nextNode.distance;
                    heap.add(new Node(nextNode.id, distance[nextNode.id]));
                    path[nextNode.id] = currentNodeId;
                }
            }
        }
    }

    private static int specialDijkstra(List<List<Node>> graph, int startPoint, int endPoint, int[] distance, int[] path, int[] d, int[] revD, int shortestDistance) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        int n = graph.size();
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
                int u = currentNode.id;
                int v = nextNode.id;
                int w = graph.get(u).get(i).distance;
                if (!checkIfOnPath(d, revD, u, v, w, shortestDistance)) {
                    if (currentDistance < Integer.MAX_VALUE && currentDistance + nextNode.distance < distance[nextNode.id]) {
                        distance[nextNode.id] = currentDistance + nextNode.distance;
                        heap.add(new Node(nextNode.id, distance[nextNode.id]));
                        path[nextNode.id] = currentNodeId;
                    }
                }
            }
        }
        return distance[endPoint];
    }

    private static boolean checkIfOnPath(int[] distance, int[] reverseDistance, int u, int v, int w, int shortestDistance) {
        return distance[u] + reverseDistance[v] + w == shortestDistance;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            List<List<Node>> graph;
            List<List<Node>> reversedGraph;
            graph = new ArrayList<>();
            reversedGraph = new ArrayList<>();
            int[] distance;
            int[] reversedDistance;
            int[] path;
            int numberOfPoints = Integer.parseInt(sc.next());
            int numberOfConnections = Integer.parseInt(sc.next());
            if (numberOfConnections == 0 || numberOfPoints == 0) {
                break;
            }
            int startPoint = Integer.parseInt(sc.next());
            int endPoint = Integer.parseInt(sc.next());
            for (int i = 0; i < numberOfPoints + 1; i++) {
                graph.add(new ArrayList<>());
                reversedGraph.add(new ArrayList<>());
            }
            distance = new int[graph.size()];
            reversedDistance = new int[reversedGraph.size()];
            for (int i = 0; i < distance.length; i++) {
                distance[i] = Integer.MAX_VALUE;
                reversedDistance[i] = Integer.MAX_VALUE;
            }
            for (int c = 0; c < numberOfConnections; c++) {
                int a = Integer.parseInt(sc.next());
                int b = Integer.parseInt(sc.next());
                int cost = Integer.parseInt(sc.next());
                graph.get(a).add(new Node(b, cost));
                reversedGraph.get(b).add(new Node(a, cost));
            }
            path = new int[graph.size()];
            Arrays.fill(path, -1);
            solution(graph, startPoint, distance, path);
            // [1,3,5,6] -> s -> d : 6
            // [2,4,6,8]
            path = new int[reversedGraph.size()];
            Arrays.fill(path, -1);
            solution(reversedGraph, endPoint, reversedDistance, path);

            int[] newDistance = new int[graph.size()];
            Arrays.fill(newDistance, Integer.MAX_VALUE);
            Arrays.fill(path, -1);
            int result = specialDijkstra(graph, startPoint, endPoint, newDistance, path, distance, reversedDistance, distance[endPoint]);
            if (result == Integer.MAX_VALUE) {
                System.out.println(-1);
            }
            else {
                System.out.println(result);
            }
        }

        sc.close();
    }
}
