package Prim;

import java.util.*;

class AudioNode implements Comparable<AudioNode> {
    long id;
    long dist;

    public AudioNode(long id, long dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(AudioNode o) {
        return Long.compare(this.dist, o.dist);
    }
}

public class Audiophobia {

    private static long[] dist;
    private static long[] path;
    private static boolean[] visited;

    private static List<List<AudioNode>> prims(int src, List<List<AudioNode>> graph) {
        PriorityQueue<AudioNode> heap = new PriorityQueue<>();
        int n = graph.size();
        dist = new long[n];
        path = new long[n];
        visited = new boolean[n];
        List<List<AudioNode>> minSpanTree = new ArrayList<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        for (int k = 0; k < visited.length; k++) {
            if (!visited[k]) {
                heap.add(new AudioNode(k, 0));
                dist[src] = 0;
                while (!heap.isEmpty()) {
                    AudioNode top = heap.remove();
                    long u = top.id;
                    visited[(int) u] = true;
                    for (int i = 0; i < graph.get((int) u).size(); i++) {
                        AudioNode neighbor = graph.get((int) u).get(i);
                        long v = neighbor.id;
                        long cost = neighbor.dist;
                        if (!visited[(int) v] && cost < dist[(int) v]) {
                            dist[(int) v] = cost;
                            heap.add(new AudioNode(v, cost));
                            path[(int) v] = u;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            minSpanTree.add(new ArrayList<>());
        }
        for (int i = path.length - 1; i > 1; i--) {
            int srcNode = (int) path[i];
            if (srcNode == -1) {
                continue;
            }
            minSpanTree.get(srcNode).add(new AudioNode(i, dist[i]));
            minSpanTree.get(i).add(new AudioNode(srcNode, dist[i]));
        }
        return minSpanTree;
    }

    private static int dfs(List<List<AudioNode>> graph, int src, int dst) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        int[] path = new int[graph.size()];
        Arrays.fill(path, -1);
        stack.push(src);
        visited.add(src);
        int maxVal = Integer.MIN_VALUE;
        while (!stack.isEmpty()) {
            int thisNode = stack.pop();
            if (thisNode == dst) {
                break;
            }
            for (int i = 0; i < graph.get(thisNode).size(); i++) {
                int nextNode = (int) graph.get(thisNode).get(i).id;
                if (!visited.contains(nextNode)) {
                    stack.push(nextNode);
                    visited.add(nextNode);
                    path[nextNode] = thisNode;
                }
            }
        }
        while (path[dst] != -1) {
            int target = -1;
            for (int i = 0; i < graph.get(dst).size(); i++) {
                if (graph.get(dst).get(i).id == path[dst]) {
                    target = i;
                    break;
                }
            }
            if (target == -1) {
                break;
            }
            int d = (int) graph.get(dst).get(target).dist;
            if (d > maxVal) {
                maxVal = d;
            }
            dst = path[dst];
        }
        return maxVal;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> results = new ArrayList<>();
        while (true) {
            int C = Integer.parseInt(sc.next());
            int S = Integer.parseInt(sc.next());
            int Q = Integer.parseInt(sc.next());
            if (C == 0 & S == 0 & Q == 0) {
                break;
            }
            List<List<AudioNode>> graph = new ArrayList<>();
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < C + 1; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < S; i++) {
                int c1 = Integer.parseInt(sc.next()); // 1
                int c2 = Integer.parseInt(sc.next()); // 2
                int cost = Integer.parseInt(sc.next());
                graph.get(c1).add(new AudioNode(c2, cost));
                graph.get(c2).add(new AudioNode(c1, cost));
            }
            List<int[]> queries = new ArrayList<>();
            for (int i = 0; i < Q; i++) {
                int src = Integer.parseInt(sc.next());
                int dst = Integer.parseInt(sc.next());
                queries.add(new int[]{src, dst});
            }
            List<List<AudioNode>> minSpanTree = prims(1, graph);
            for (int[] query : queries) {
                int src = query[0];
                int dst = query[1];
                int maxVal = dfs(minSpanTree, src, dst);
                result.add(maxVal);
            }
            results.add(result);
        }

        for (int i = 0; i < results.size(); i++) {
            System.out.println("Case #" + (i + 1));
            for (Integer result : results.get(i)) {
                if (result == Integer.MIN_VALUE) {
                    System.out.println("no path");
                }
                else {
                    System.out.println(result);
                }
            }
            if (i < results.size() - 1) {
                System.out.println();
            }
        }


        sc.close();
    }
}
