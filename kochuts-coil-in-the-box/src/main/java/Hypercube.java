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
        for(int i = dimension; i>0; i--)
            pivotStack.push(i);
    }

    private void saveCurrentNeighbours() {
        this.currentNeighbours = new ArrayList<>();
        for(int i = 0; i< dimension; i++) {
            int neighbourId = this.currentNode ^ (1<<i);
            for(Node node : nodes)
                if(node.getId()==neighbourId)
                    if (!node.isMarked())
                        this.currentNeighbours.add(neighbourId);
        }
    }

    private boolean currentNeighbourThatIsntMarkedExists() {
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

    private boolean currentNeighbourIsMarked(int i) {
        return this.nodes.get(this.currentNeighbours.get(i)).isMarked();
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

    public ArrayList<Integer> searchForLongestCoil() {
        ArrayList<Integer> result = new ArrayList<>();
        this.nodes = GrayCode.createGrayCode(dimension);
        createNodeStack();
        createPivotStack();

        this.currentNode = nodeStack.peek();
        this.currentPivot = pivotStack.peek();
        saveCurrentNeighbours();
        if(currentNeighbourThatIsntMarkedExists()) //TODO number of possible return paths is zero
            return null; //TODO: zmienic to pozniej na odpowiedni format
        else {
            for (int i=0; i<=currentPivot; i++) {
                if(currentNeighbourIsMarked(i)) {
                    if(canCloseCoil(i))
                        saveCoil(); //TODO: zapisywac!
                    this.nodeStack.push(this.currentNeighbours.get(i));
                    this.pivotStack.push(this.currentPivot);
                    //TODO: rekurencja
                    this.nodeStack.pop();
                    this.pivotStack.pop();
                    if(this.nodeStack.isEmpty())
                        return null; //TODO: poprawic na odpowiedni format
                }
                if(this.currentPivot<dimension-1) {
                    this.currentPivot += 1;
                    if(currentNeighbourIsMarked(currentPivot)) {
                        if(canCloseCoil(currentPivot))
                            saveCoil();
                        this.nodeStack.push(this.currentNeighbours.get(currentPivot));
                        this.pivotStack.push(currentPivot);
                        //TODO: rekurencja
                        this.nodeStack.pop();
                        this.pivotStack.pop();
                        if(this.nodeStack.isEmpty())
                            return null; //TODO: poprawic na odpowiedni format
                    }
                }
            }
            unmarkAllNodes();
        }


        return result;
    }
}
