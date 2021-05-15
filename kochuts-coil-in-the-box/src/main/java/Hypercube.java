import java.util.*;

import static java.lang.Math.pow;

public class Hypercube {
    private int dimension;
    private ArrayList<Node> nodes;
    private boolean showOnlyBestResult;

    public Hypercube(int dimension, boolean showOnlyBestResult) {
        this.dimension = dimension;
        this.showOnlyBestResult = showOnlyBestResult;
        createNodes();
    }

    private void createNodes() {
        int amountOfNodes = (int) (pow(2, dimension - 1) * dimension);
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

    public boolean checkIfShowOnlyBestResult() {return showOnlyBestResult; }
}
