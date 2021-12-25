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

    static List<List<NetworkNode>> graph;
    static int[] distance;
    static int[] path;

    private static void solution(int startPoint) {
        PriorityQueue<NetworkNode> heap = new PriorityQueue<>();
        int n = graph.size();
        distance = new int[n];
        path = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
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
                if (currentDistance + nextNode.distance < distance[nextNode.id]) {
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
            graph = new ArrayList<>();
            int numberOfNodes = Integer.parseInt(sc.next());
            for (int node = 0; node < numberOfNodes + 1; node++) {
                graph.add(new ArrayList<>());
            }
            distance = new int[graph.size()];
            path = new int[graph.size()];
            for (int i = 0; i < distance.length; i++) {
                distance[i] = Integer.MAX_VALUE;
                path[i] = -1;
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
            }

            for (int c = 0; c < numberOfTwoWayConnections; c++) {
                int a = Integer.parseInt(sc.next());
                int b = Integer.parseInt(sc.next());
                int cost = Integer.parseInt(sc.next());
                // check if there exists a connection between a and b
                boolean foundConnection = false;
                // nếu a có đường đi đến b
                for (int j = 0; j < graph.get(a).size(); j++) {
                    if (graph.get(a).get(j).id == b) {
                        graph.get(a).set(j, new NetworkNode(b, cost));
                        graph.get(b).add(new NetworkNode(a, cost));
                        foundConnection = true;
                        break;
                    }
                }
                // nếu b có đường đi đến a
                if (!foundConnection) {
                    for (int j = 0; j < graph.get(b).size(); j++) {
                        if (graph.get(b).get(j).id == a) {
                            graph.get(b).set(j, new NetworkNode(a, cost));
                            graph.get(a).add(new NetworkNode(b, cost));
                            foundConnection = true;
                            break;
                        }
                    }
                }

                // nếu không, tạo kết nối 2 chiều mới
                if (!foundConnection) {
                    graph.get(a).add(new NetworkNode(b, cost));
                    graph.get(b).add(new NetworkNode(a, cost));
                }

            }
            solution(src);
            if (distance[dst] == Integer.MAX_VALUE) {
                System.out.println(-1);
            }
            else {
                System.out.println(distance[dst]);
            }
        }
        sc.close();
    }
}
