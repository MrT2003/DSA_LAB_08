import java.util.*;

class Node {
    String name;
    List<Edge> edges;
    int distance;
    Node previous;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
        this.distance = Integer.MAX_VALUE;
        this.previous = null;
    }
}