import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node node1, Node node2, int weight) {
        Edge edge1 = new Edge(node2, weight);
        Edge edge2 = new Edge(node1, weight);
        node1.edges.add(edge1);
        node2.edges.add(edge2);
    }

    public void printGraph() {
        for (Node node : nodes) {
            System.out.print(node.name + " -> ");
            for (Edge edge : node.edges) {
                System.out.print(edge.node.name + " (" + edge.weight + "), ");
            }
            System.out.println();
        }
    }

    public List<List<Node>> dfs(Node start, Node end, List<Node> path) {
        List<List<Node>> paths = new ArrayList<>();
        path.add(start);
        if (start.name.equals(end.name)) {
            paths.add(new ArrayList<>(path));
        } else {
            for (Edge edge : start.edges) {
                if (!path.contains(edge.node)) {
                    paths.addAll(dfs(edge.node, end, path));
                }
            }
        }
        path.remove(start);
        return paths;
    }

    public void printPaths(List<List<Node>> paths, int minNodes, int maxNodes, int minCost, int maxCost) {
        System.out.println("Number of paths: " + paths.size());
        System.out.println("Paths with smallest number of nodes (" + minNodes + "):");
        for (List<Node> path : paths) {
            if (path.size() == minNodes) {
                int cost = 0;
                for (int i = 0; i < path.size() - 1; i++) {
                    for (Edge edge : path.get(i).edges) {
                        if (edge.node.equals(path.get(i + 1))) {
                            cost += edge.weight;
                            break;
                        }
                    }
                }
                System.out.println(path.stream().map(n -> n.name).collect(Collectors.toList()) + " with cost " + cost);
            }
        }
        System.out.println("Paths with largest number of nodes (" + maxNodes + "):");
        for (List<Node> path : paths) {
            if (path.size() == maxNodes) {
                int cost = 0;
                for (int i = 0; i < path.size() - 1; i++) {
                    for (Edge edge : path.get(i).edges) {
                        if (edge.node.equals(path.get(i + 1))) {
                            cost += edge.weight;
                            break;
                        }
                    }
                }
                System.out.println(path.stream().map(n -> n.name).collect(Collectors.toList()) + " with cost " + cost);
            }
        }
        System.out.println("Path with smallest cost: ");
        List<Node> minPath = null;
        int minPathCost = Integer.MAX_VALUE;
        for (List<Node> path : paths) {
            int cost = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                for (Edge edge : path.get(i).edges) {
                    if (edge.node.equals(path.get(i + 1))) {
                        cost += edge.weight;
                        break;
                    }
                }
            }
            if (cost < minPathCost) {
                minPathCost = cost;
                minPath = path;
            }
        }
        System.out.println(minPath.stream().map(n -> n.name).collect(Collectors.toList()) + " with cost " + minPathCost);
        System.out.println("Path with largest cost: ");
        List<Node> maxPath = null;
        int maxPathCost = Integer.MIN_VALUE;
        for (List<Node> path : paths) {
            int cost = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                for (Edge edge : path.get(i).edges) {
                    if (edge.node.equals(path.get(i + 1))) {
                        cost += edge.weight;
                        break;
                    }
                }
            }
            if (cost > maxPathCost) {
                maxPathCost = cost;
                maxPath = path;
            }
        }
        System.out.println(maxPath.stream().map(n -> n.name).collect(Collectors.toList()) + " with cost " + maxPathCost);
    }

    public void dijkstra(Node start) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new PriorityQueueComparator());
        start.distance = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Edge edge : current.edges) {
                Node neighbor = edge.node;
                int newDistance = current.distance + edge.weight;

                if (newDistance < neighbor.distance) {
                    queue.remove(neighbor);
                    neighbor.distance = newDistance;
                    neighbor.previous = current;
                    queue.add(neighbor);
                }
            }
        }
    }
//    public void printShortestPath(Node end) {
//        if (end.previous == null) {
//            System.out.println("No path found.");
//            return;
//        }
//
//        List<Node> path = new ArrayList<>();
//        for (Node node = end; node != null; node = node.previous) {
//            path.add(node);
//        }
//
//        System.out.print("Shortest path from A to H: ");
//        for (int i = path.size() - 1; i >= 0; i--) {
//            Node node = path.get(i);
//            System.out.print(node.name);
//            if (i != 0) {
//                System.out.print(" -> ");
//            }
//        }
//        System.out.println();
//
//        System.out.print("Shortest path from B to J: ");
//        for (int i = path.size() - 1; i >= 0; i--) {
//            Node node = path.get(i);
//            if (node.name.equals("A") || node.name.equals("B")) {
//                System.out.print(node.name);
//                System.out.print(" -> ");
//            }
//        }
//        System.out.println("H -> J");
//    }

    public List<Node> getShortestPath(Node end) {
        List<Node> path = new ArrayList<>();
        for (Node node = end; node != null; node = node.previous) {
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }

    public void printShortestPath(Node start, Node end) {
        dijkstra(start);
        List<Node> path = getShortestPath(end);
        int cost = end.distance;

        if (path.size() == 1 && !path.get(0).equals(end)) {
            System.out.println("No path found from " + start.name + " to " + end.name);
            return;
        }

        System.out.print("Shortest path from " + start.name + " to " + end.name + ": ");
        for (Node node : path) {
            System.out.print(node.name);
            if (!node.equals(end)) {
                System.out.print(" -> ");
            }
        }
        System.out.println(" with cost " + cost);
    }
}