import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Math.pow;

public class Hypercube {
    private int dimension;
    private int amountOfNodes;
    private ArrayList<Node> nodes;
    private ArrayList<ArrayList<Integer>> coils;
    private Stack<Integer> nodeStack;
    private Stack<Integer> pivotStack;
    private ArrayList<Integer> currentCoil;

    public Hypercube(int dimension) {
        this.dimension = dimension;
    }

    private void createNodes() {
        nodes = new ArrayList<>();
        for (int i=0; i<amountOfNodes; i++)
            nodes.add(new Node(i));
        nodes.get(0).mark();
    }

    private void createNodeStack() {
        nodeStack = new Stack<>();
        nodeStack.push(0);
    }

    private void createPivotStack() {
        pivotStack = new Stack<>();
        pivotStack.push(-1);
    }

    private boolean allCurrentNeighboursAreMarked(ArrayList<Node> markedAtThisLevel) {
        return markedAtThisLevel.size() == 0;
    }

    private boolean iThNeighborWasJustMarked(int iThNeighbourId, ArrayList<Node> marked) {
        for(Node node : marked)
            if(node.getId()==iThNeighbourId)
                return true;
        return false;
    }

    private ArrayList<Node> markCurrentNeighbours(int currentNode) {
        ArrayList<Node> marked = new ArrayList<>();
        for(int i = 0; i<dimension; i++) {
            int neighbourId = currentNode ^ (1<<i);
            for(Node node : nodes)
                if(node.getId()==neighbourId && !node.isMarked()) {
                    marked.add(node);
                    node.mark();
                }
        }
        return marked;
    }

    //TODO: napisac
    private boolean canCloseCoil(int i) {
        return false;//nodes.get(nodes.size()-1).getId() == this.currentNeighbours.get(i);
    }

    //TODO: napisac
    private void saveCoil() {
        coils.add(currentCoil);
        currentCoil = new ArrayList<>();
    }

    private void unmarkAllNodesMarkedAtThisLevel(ArrayList<Node> marked) {
        for(Node node : marked)
            node.unmark();
    }

    public void showResult() {
        int longestCoil = -1;
        for(ArrayList<Integer> coil : coils)
            if(coil.size() > longestCoil)
                longestCoil = coil.size();
        System.out.println(longestCoil);
        System.out.println(coils);
    }

    private int search(int depth) {
        int currentNode = nodeStack.peek();
        int currentPivot = pivotStack.peek();
        ArrayList<Node> markedAtThisLevel = markCurrentNeighbours(currentNode);

        for (int j=0; j<depth; j++)
            System.out.print("-");
        System.out.print(currentNode);
        System.out.print(", ");
        System.out.println(depth);

        if(allCurrentNeighboursAreMarked(markedAtThisLevel)) //TODO number of possible return paths is zero
            return 0;
        else {
            for (int i=0; i<=currentPivot; i++) {
                int iThNeighbourId = currentNode ^ (1<<i);
                if(iThNeighborWasJustMarked(iThNeighbourId, markedAtThisLevel)) {
//                     if(canCloseCoil(i))
//                         saveCoil();
                    nodeStack.push(iThNeighbourId);
                    pivotStack.push(currentPivot);
                    search(depth+1);
                    nodeStack.pop();
                    pivotStack.pop();
                    if(nodeStack.isEmpty())
                        return 0;
                }
            }
            if(currentPivot<dimension-1) {
                currentPivot += 1;
                int neighbourId = currentNode ^ (1<<currentPivot);
                if(iThNeighborWasJustMarked(neighbourId, markedAtThisLevel)) {
//                     if(canCloseCoil(currentPivot))
//                         saveCoil();
                    nodeStack.push(neighbourId);
                    pivotStack.push(currentPivot);
                    search(depth+1);
                    nodeStack.pop();
                    pivotStack.pop();
                    if(nodeStack.isEmpty())
                        return 0;
                }
            }
        }
        unmarkAllNodesMarkedAtThisLevel(markedAtThisLevel);
        return 0;
    }

    public void searchForLongestCoil() {
        this.amountOfNodes = (int)(pow(2, dimension-1)*dimension);
        createNodes();
        this.coils = new ArrayList<>();
        this.currentCoil = new ArrayList<>();

        createNodeStack();
        createPivotStack();
        search(0);
        showResult();
    }
}
