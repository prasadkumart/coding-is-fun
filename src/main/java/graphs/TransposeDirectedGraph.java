package graphs;
import java.util.ArrayList;
import java.util.Stack;

public class TransposeDirectedGraph {


    static GraphNode create_transpose(GraphNode node) {
        if (null == node || null != node.neighbors && node.neighbors.size() == 0) {
            return node;
        }

        GraphNode startNode = node;
        Stack<GraphNode> stack = new Stack<>();
        stack.add(node);

        while (node.neighbors.get(0).value != startNode.value) {
            stack.add(node.neighbors.get(0));
            node = node.neighbors.get(0);
        }

        GraphNode currNode = stack.pop();
        GraphNode newStartNode = currNode;
        while(!stack.isEmpty()) {
            GraphNode prevNode = currNode;
            GraphNode newNode = stack.pop();
            prevNode.neighbors.set(0, newNode);
            currNode = newNode;
        }
        currNode.neighbors.set(0, newStartNode);

        return currNode;
    }

    public static void main(String[] args) {
        GraphNode g1 = new GraphNode(1);
        GraphNode g2 = new GraphNode(2);
        GraphNode g3 = new GraphNode(3);
        g1.neighbors.add(g2);
        g2.neighbors.add(g3);
        g3.neighbors.add(g1);

        create_transpose(g1);
    }
}

class GraphNode {
    Integer value;
    ArrayList<GraphNode> neighbors;

    GraphNode(Integer value) {
        this.value = value;
        this.neighbors = new ArrayList(3);
    }
}
