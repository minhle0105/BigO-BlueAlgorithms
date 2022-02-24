package Prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class NetworkNode implements Comparable<NetworkNode> {
    int id;
    int dist;

    public NetworkNode(int id, int dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(NetworkNode o) {
        return Integer.compare(this.dist, o.dist);
    }
}

public class SimulateNetwork {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int m = Integer.parseInt(sc.next());
        List<List<NetworkNode>> graphs = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graphs.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int src = Integer.parseInt(sc.next());
            int dst = Integer.parseInt(sc.next());
            int cost = Integer.parseInt(sc.next());
            boolean nodeUpdated = false;
            boolean alreadyHasConnection = false;
            for (int j = 0; j < graphs.get(src).size(); j++) {
                if (graphs.get(src).get(j).id == dst) {
                    if (graphs.get(src).get(j).dist > cost) {
                        graphs.get(src).get(j).dist = cost;
                        try {
                            graphs.get(j).get(src).dist = cost;
                        }
                        catch (Exception e) {
                            graphs.get(j).add(new NetworkNode(src, cost));
                        }
                        nodeUpdated = true;
                    }
                    alreadyHasConnection = true;
                    break;
                }
            }
            if (!nodeUpdated && !alreadyHasConnection) {
                graphs.get(src).add(new NetworkNode(dst, cost));
                graphs.get(dst).add(new NetworkNode(src, cost));
            }
        }
        int numberOfOptions = Integer.parseInt(sc.next());
        int[] options = new int[numberOfOptions];
        for (int i = 0; i < numberOfOptions; i++) {
            options[i] = Integer.parseInt(sc.next());
        }
        Arrays.sort(options);

        sc.close();
    }
}
