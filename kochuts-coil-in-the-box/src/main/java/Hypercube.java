import java.util.ArrayList;

public class Hypercube {
    private int depth; //TODO: change later to dimension
    private ArrayList<Node> nodes;

    public Hypercube(int depth) {
        this.depth = depth;
    }


    private ArrayList<Node> markNeighbours(Node currentNode) {
        ArrayList<Node> neighbours = new ArrayList<>();
        for(int i=0; i<depth; i++) {
            int neighbourId = currentNode.getId() ^ (1<<i);
            if(!nodes.get(neighbourId).isMarked()) {
                nodes.get(neighbourId).mark();
                neighbours.add(new Node(neighbourId, true));
            }
        }
        System.out.println(neighbours);
        return neighbours;
    }

    public int searchForLongestCoil() {
        int result = 0;
        int pivot = 1; //TODO: change later to currentDimension
        Node currentNode;
        ArrayList<Node> currentNeighbours = new ArrayList<>();

        this.nodes = GrayCode.createGrayCode(depth);
        currentNode = nodes.get(0);
        currentNeighbours = markNeighbours(currentNode);



        return result;
    }
}
