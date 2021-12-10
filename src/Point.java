import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Point {
    private final int id;
    private final Map<Integer, Integer> adjacencyWeight;

    public Point(int id) {
        this.id = id;
        this.adjacencyWeight = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public boolean hasNeighbors() {
        return this.adjacencyWeight.size() > 0;
    }

    public Map<Integer, Integer> getAdjacencyWeight() {
        return adjacencyWeight;
    }

    public void addNeighbor(int neighborId) {
        this.adjacencyWeight.put(neighborId, 0);
    }

    public void addNeighbor(int neighborId, int weight) {
        this.adjacencyWeight.put(neighborId, weight);
    }

    public int getWeighToANeighbor(int neighborId) {
        return this.adjacencyWeight.get(neighborId);
    }

    public Set<Integer> getAllNeighbors () {
        return this.adjacencyWeight.keySet();
    }

}
