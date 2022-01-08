package Dijkstra;

import java.util.*;

class NetworkNode implements Comparable<NetworkNode> {
    public int id;
    public int distance;

    public NetworkNode(int id, int distance) {
        this.id = id;
        this.distance = distance;
    }

    @Override
    public int compareTo(NetworkNode o) {
        return Integer.compare(this.distance, o.distance);
    }
}

public class TrafficNetwork {


    private static void solution(List<List<NetworkNode>> graph, int startPoint, int[] distance, int[] path) {
        PriorityQueue<NetworkNode> heap = new PriorityQueue<>();
        int n = graph.size();
        heap.add(new NetworkNode(startPoint, 0));
        distance[startPoint] = 0;
        while (!heap.isEmpty()) {
            NetworkNode currentNode = heap.remove();
            int currentNodeId = currentNode.id;
            int currentDistance = currentNode.distance;
            if (distance[currentNodeId] != currentDistance) {
                continue;
            }
            for (int i = 0; i < graph.get(currentNodeId).size(); i++) {
                NetworkNode nextNode = graph.get(currentNodeId).get(i);
                if (currentDistance < Integer.MAX_VALUE && currentDistance + nextNode.distance < distance[nextNode.id]) {
                    distance[nextNode.id] = currentDistance + nextNode.distance;
                    heap.add(new NetworkNode(nextNode.id, distance[nextNode.id]));
                    path[nextNode.id] = currentNodeId;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTests = Integer.parseInt(sc.next());
        for (int test = 0; test < numberOfTests; test++) {
            List<List<NetworkNode>> graph;
            List<List<NetworkNode>> reversedGraph;
            graph = new ArrayList<>();
            reversedGraph = new ArrayList<>();
            int[] distance;
            int[] reversedDistance;
            int[] path;
            int numberOfNodes = Integer.parseInt(sc.next());
            for (int node = 0; node < numberOfNodes + 1; node++) {
                graph.add(new ArrayList<>());
                reversedGraph.add(new ArrayList<>());
            }
            distance = new int[graph.size()];
            reversedDistance = new int[reversedGraph.size()];
            for (int i = 0; i < distance.length; i++) {
                distance[i] = Integer.MAX_VALUE;
                reversedDistance[i] = Integer.MAX_VALUE;
            }
            int numberOfOneWayConnections = Integer.parseInt(sc.next());
            int numberOfTwoWayConnections = Integer.parseInt(sc.next());
            int src = Integer.parseInt(sc.next());
            int dst = Integer.parseInt(sc.next());
            // đầu tiên tạo kết nối 1 chiều trước
            for (int c = 0; c < numberOfOneWayConnections; c++) {
                int a = Integer.parseInt(sc.next());
                int b = Integer.parseInt(sc.next());
                int cost = Integer.parseInt(sc.next());
                graph.get(a).add(new NetworkNode(b, cost));
                reversedGraph.get(b).add(new NetworkNode(a, cost));
            }
            path = new int[graph.size()];
            Arrays.fill(path, -1);
            solution(graph, src, distance, path);
            path = new int[reversedGraph.size()];
            Arrays.fill(path, -1);
            solution(reversedGraph, dst, reversedDistance, path);
            int originalDistance = distance[dst];
            for (int c = 0; c < numberOfTwoWayConnections; c++) {
                int a = Integer.parseInt(sc.next());
                int b = Integer.parseInt(sc.next());
                int cost = Integer.parseInt(sc.next());
                int newDistance1 = Integer.MAX_VALUE;
                int newDistance2 = Integer.MAX_VALUE;
                if (distance[a] < Integer.MAX_VALUE && reversedDistance[b] < Integer.MAX_VALUE) {
                    newDistance1 = distance[a] + reversedDistance[b] + cost;
                }
                if (distance[b] < Integer.MAX_VALUE && reversedDistance[a] < Integer.MAX_VALUE) {
                    newDistance2 = distance[b] + reversedDistance[a] + cost;
                }
                int newDistance = Math.min(newDistance1, newDistance2);
                if (originalDistance > newDistance) {
                    originalDistance = newDistance;
                }
            }
            if (originalDistance == Integer.MAX_VALUE) {
                System.out.println(-1);
            }
            else {
                System.out.println(originalDistance);
            }
        }
        sc.close();
    }
}
