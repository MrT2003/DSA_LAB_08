import java.util.*;
public class MapApp {
    public static void main(String[] args) {
        Graph graph = new Graph();

        System.out.println();
        System.out.println("Task 3");
        //Create Node
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        Node nodeI = new Node("I");
        Node nodeJ = new Node("J");
        Node nodeK = new Node("K");
        Node nodeL = new Node("L");
        Node node2 = new Node("2");

        //Add node into graph
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        graph.addNode(nodeG);
        graph.addNode(nodeH);
        graph.addNode(nodeI);
        graph.addNode(nodeJ);
        graph.addNode(nodeK);
        graph.addNode(nodeL);
        graph.addNode(node2);

        //Add into graph
        graph.addEdge(nodeA, nodeB, 6);
        graph.addEdge(nodeA, node2, 10);
        /*------------------------------------*/
        graph.addEdge(node2, nodeB, 12);
        graph.addEdge(node2, nodeC, 12);
        graph.addEdge(node2, nodeF, 8);
        graph.addEdge(node2, nodeG, 16);
        /*-----------------------------------*/
        graph.addEdge(nodeB, nodeC, 11);
        graph.addEdge(nodeB, nodeD, 14);
        /*-----------------------------------*/
        graph.addEdge(nodeC, nodeE, 6);
        graph.addEdge(nodeC, nodeF, 3);
        /*-----------------------------------*/
        graph.addEdge(nodeF, nodeH, 16);
        graph.addEdge(nodeF, nodeI, 6);
        /*-----------------------------------*/
        graph.addEdge(nodeI, nodeH, 13);
        graph.addEdge(nodeI, nodeL, 17);
        /*-----------------------------------*/
        graph.addEdge(nodeD, nodeE, 4);
        graph.addEdge(nodeD, nodeK, 15);
        graph.addEdge(nodeD, nodeH, 6);
        /*-----------------------------------*/
        graph.addEdge(nodeG, nodeI, 8);
        /*-----------------------------------*/
        graph.addEdge(nodeE, nodeH, 12);
        /*-----------------------------------*/
        graph.addEdge(nodeC, nodeD, 4);
        /*-----------------------------------*/
        graph.addEdge(nodeH, nodeK, 12);
        graph.addEdge(nodeH, nodeL, 18);
        /*-----------------------------------*/
        graph.addEdge(nodeL, nodeJ, 20);
        /*-----------------------------------*/
        graph.addEdge(nodeK, nodeJ, 9);

        graph.printGraph();

        System.out.println();
        System.out.println("Task 4");
        List<List<Node>> paths = graph.dfs(nodeA, nodeK, new ArrayList<>());
        int minNodes = Integer.MAX_VALUE;
        int maxNodes = Integer.MIN_VALUE;
        int minCost = Integer.MAX_VALUE;
        int maxCost = Integer.MIN_VALUE;
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
            if (path.size() < minNodes) {
                minNodes = path.size();
            }
            if (path.size() > maxNodes) {
                maxNodes = path.size();
            }
            if (cost < minCost) {
                minCost = cost;
            }
            if (cost > maxCost) {
                maxCost = cost;
            }
        }
        graph.printPaths(paths, minNodes, maxNodes, minCost, maxCost);
        System.out.println();
        System.out.println("Task 5");
        // Find the shortest path from A to H
        System.out.println("Finding shortest path from A to H:");
        graph.printShortestPath(nodeA, nodeH);

        // Reset the nodes for the next shortest path calculation
        for (Node node : graph.nodes) {
            node.distance = Integer.MAX_VALUE;
            node.previous = null;
        }

        // Find the shortest path from B to J
        System.out.println("Finding shortest path from B to J:");
        graph.printShortestPath(nodeB, nodeJ);
    }
}