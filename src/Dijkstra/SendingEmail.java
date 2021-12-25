package Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Server implements Comparable<Server> {
    public int id;
    public int distance;

    public Server(int id, int distance) {
        this.id = id;
        this.distance = distance;
    }

    @Override
    public int compareTo(Server o) {
        return Integer.compare(this.distance, o.distance);
    }
}


public class SendingEmail {

    static List<List<Server>> graph;
    static int[] distance;
    static int[] path;

    private static void solution(int startPoint) {
        PriorityQueue<Server> heap = new PriorityQueue<>();
        int n = graph.size();
        distance = new int[n];
        path = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
        heap.add(new Server(startPoint, 0));
        distance[startPoint] = 0;
        while (!heap.isEmpty()) {
            Server currentNode = heap.remove();
            int currentNodeId = currentNode.id;
            int currentDistance = currentNode.distance;
            if (distance[currentNodeId] != currentDistance) {
                continue;
            }
            for (int i = 0; i < graph.get(currentNodeId).size(); i++) {
                Server nextNode = graph.get(currentNodeId).get(i);
                if (currentDistance + nextNode.distance < distance[nextNode.id]) {
                    distance[nextNode.id] = currentDistance + nextNode.distance;
                    heap.add(new Server(nextNode.id, distance[nextNode.id]));
                    path[nextNode.id] = currentNodeId;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTests = Integer.parseInt(sc.next());
        String[] results = new String[numberOfTests];
        for (int test = 0; test < numberOfTests; test++) {
            int numberOfServer = Integer.parseInt(sc.next());
            graph = new ArrayList<>();
            for (int i = 0; i < numberOfServer + 1; i++) {
                graph.add(new ArrayList<>());
            }
            distance = new int[graph.size()];
            path = new int[graph.size()];
            for (int i = 0; i < graph.size(); i++) {
                distance[i] = Integer.MAX_VALUE;
                path[i] = -1;
            }
            int numberOfConnections = Integer.parseInt(sc.next());
            int src = Integer.parseInt(sc.next());
            int dst = Integer.parseInt(sc.next());
            for (int c = 0; c < numberOfConnections; c++) {
                int a = Integer.parseInt(sc.next());
                int b = Integer.parseInt(sc.next());
                int cost = Integer.parseInt(sc.next());
                graph.get(a).add(new Server(b, cost));
                graph.get(b).add(new Server(a, cost));
            }
            solution(src);
            if (distance[dst] != Integer.MAX_VALUE) {
                results[test] = "Case #" + (test + 1) + ": " + distance[dst];
            }
            else {
                results[test] = "Case #" + (test + 1) + ": unreachable";
            }
        }
        for (String result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
