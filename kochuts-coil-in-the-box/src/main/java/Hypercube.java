import java.util.ArrayList;
import java.util.Stack;

public class Hypercube {
    private int dimension;
    private ArrayList<Node> nodes;
    private Stack<Integer> nodeStack;
    private Stack<Integer> pivotStack;
    private ArrayList<Integer> currentNeighbours;
    private int currentNode;
    private int currentPivot;

    public Hypercube(int dimension) {
        this.dimension = dimension;
    }

    private void createNodeStack() {
        nodeStack = new Stack<>();
        for(Node node : nodes)
            nodeStack.push(node.getId());
    }

    private void createPivotStack() {
        pivotStack = new Stack<>();
        for(int i = dimension-1; i>=0; i--) {
            pivotStack.push(i);
        }
    }

    private void saveCurrentNeighbours() {
        this.currentNeighbours = new ArrayList<>();
        for(int i = 0; i< dimension; i++) {
            int neighbourId = this.currentNode ^ (1<<i);
            for(Node node : nodes)
                if(node.getId()==neighbourId)
                    this.currentNeighbours.add(neighbourId);
        }
    }

    private boolean allCurrentNeighboursAreMarked() {
        int amount = this.currentNeighbours.size();
        boolean[] results = new boolean[amount];

        for(int i=0; i<amount; i++) {
            for (Node node : nodes)
                if (node.getId() == this.currentNeighbours.get(i))
                    if(node.isMarked())
                        results[i] = true;
                    else results[i] = false;
        }
        for(int i=1; i<amount; i++)
            results[i] = results[i] || results[i-1];

        return results[amount-1];
    }

    private boolean currentNeighbourIsNotMarked(int i) {
        boolean isMarked = this.nodes.get(this.currentNeighbours.get(i)).isMarked();
        return !isMarked;
    }

    private void markCurrentNeighbour(int i) {
        this.nodes.get(this.currentNeighbours.get(i)).mark();
    }

    private boolean canCloseCoil(int i) {
        return nodes.get(nodes.size()-1).getId() == this.nodes.get(this.currentNeighbours.get(i)).getId();
    }

    private void saveCoil() {
        //TODO
    }

    private void unmarkAllNodes() {
        for(Node node : this.nodes)
            node.unmark();
    }

    //TODO: dodac currentCycle i cycles
    private int search(int depth) {
        this.currentNode = nodeStack.peek();
        this.currentPivot = pivotStack.peek();
        saveCurrentNeighbours();
        if(allCurrentNeighboursAreMarked()) //TODO number of possible return paths is zero
            return 0;
        else {
            for (int i=0; i<=currentPivot; i++) {
                if(currentNeighbourIsNotMarked(i)) {
                    markCurrentNeighbour(i);
                    if(canCloseCoil(i)) { //TODO: poprawic!
                        saveCoil(); //TODO: zapisywac!
                    }
                    this.nodeStack.push(this.currentNeighbours.get(i));
                    this.pivotStack.push(this.currentPivot);
                    search(depth+1);
                    this.nodeStack.pop();
                    this.pivotStack.pop();
                    if(this.nodeStack.isEmpty())
                        return 0;
                }
                if(this.currentPivot<dimension-1) {
                    this.currentPivot += 1;
                    if(currentNeighbourIsNotMarked(currentPivot)) {
                        markCurrentNeighbour(currentPivot);
                        if(canCloseCoil(currentPivot))
                            saveCoil();
                        this.nodeStack.push(this.currentNeighbours.get(currentPivot));
                        this.pivotStack.push(currentPivot);
                        search(depth+1);
                        this.nodeStack.pop();
                        this.pivotStack.pop();
                        if(this.nodeStack.isEmpty())
                            return 0;
                    }
                }
            }
            unmarkAllNodes();
        }
        return 0;
    }

    public void searchForLongestCoil() {
        this.nodes = GrayCode.createGrayCode(dimension);
        createNodeStack();
        createPivotStack();
        search(0);
    }

    public void showResult() {
        //TODO
    }
}
