import java.util.*;

import static java.lang.Math.pow;

public class Hypercube {
    private int dimension;
    private ArrayList<Node> nodes;

    public Hypercube(int dimension) {
        this.dimension = dimension;
        createNodes();
    }

    private void createNodes() {
        int amountOfNodes = (int) (pow(2, dimension));
        nodes = new ArrayList<>();
        for (int i = 0; i < amountOfNodes; i++)
            nodes.add(new Node(i));
    }

    public int getDimension() {
        return dimension;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}
