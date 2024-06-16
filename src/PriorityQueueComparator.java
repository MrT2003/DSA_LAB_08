import java.util.Comparator;

class PriorityQueueComparator implements Comparator<Node> {
    @Override
    public int compare(Node n1, Node n2) {
        return Integer.compare(n1.distance, n2.distance);
    }
}