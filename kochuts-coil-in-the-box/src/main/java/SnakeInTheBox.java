import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;


public class SnakeInTheBox {
    private Hypercube hypercube;
    private ArrayList<ArrayList<Integer>> coils;
    private Stack<Integer> nodeStack;
    private Stack<Integer> pivotStack;

    public SnakeInTheBox(Hypercube hypercube) {
        this.hypercube = hypercube;
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
        for (Node node : marked)
            if (node.getId() == iThNeighbourId)
                return true;
        return false;
    }

    private ArrayList<Node> markCurrentNeighbours(int currentNode) {
        ArrayList<Node> marked = new ArrayList<>();
        for (int i = 0; i < hypercube.getDimension(); i++) {
            int neighbourId = currentNode ^ (1 << i);
            for (Node node : hypercube.getNodes())
                if (node.getId() == neighbourId && !node.isMarked()) {
                    marked.add(node);
                    node.mark();
                }
        }
        return marked;
    }

    private void saveSnake() {
        Stack<Integer> currentStack = (Stack<Integer>) this.nodeStack.clone();
        ArrayList<Integer> coil = new ArrayList<>();
        while (!currentStack.isEmpty()) {
            coil.add(currentStack.pop());
        }
        this.coils.add(coil);
    }

    private void unmarkAllNodesMarkedAtThisLevel(ArrayList<Node> marked) {
        for (Node node : marked)
            node.unmark();
    }

    private int search(int depth) {
        int currentNode = nodeStack.peek();
        int currentPivot = pivotStack.peek();
        ArrayList<Node> markedAtThisLevel = markCurrentNeighbours(currentNode);

//        for (int j = 0; j < depth; j++)
//            System.out.print("-");
//        System.out.print(currentNode);
//        System.out.print(", ");
//        System.out.println(depth);

        if (allCurrentNeighboursAreMarked(markedAtThisLevel)) {//TODO number of possible return paths is zero
            saveSnake();
            return 0;
        } else {
            for (int i = 0; i <= currentPivot; i++) {
                int iThNeighbourId = currentNode ^ (1 << i);
                if (iThNeighborWasJustMarked(iThNeighbourId, markedAtThisLevel)) {
                    nodeStack.push(iThNeighbourId);
                    pivotStack.push(currentPivot);
                    search(depth + 1);
                    nodeStack.pop();
                    pivotStack.pop();
                    if (nodeStack.isEmpty())
                        return 0;
                }
            }
            if (currentPivot < hypercube.getDimension() - 1) {
                currentPivot += 1;
                int neighbourId = currentNode ^ (1 << currentPivot);
                if (iThNeighborWasJustMarked(neighbourId, markedAtThisLevel)) {
                    nodeStack.push(neighbourId);
                    pivotStack.push(currentPivot);
                    search(depth + 1);
                    nodeStack.pop();
                    pivotStack.pop();
                    if (nodeStack.isEmpty())
                        return 0;
                }
            }
        }
        unmarkAllNodesMarkedAtThisLevel(markedAtThisLevel);
        return 0;
    }

    public ArrayList<Integer> searchForLongestSnake() {
        this.coils = new ArrayList<>();
        hypercube.getNodes().get(0).mark();
        createNodeStack();
        createPivotStack();

        search(0);

        Collections.sort(this.coils, new Comparator<ArrayList>() {
            public int compare(ArrayList coil1, ArrayList coil2) {
                return coil2.size() - coil1.size();
            }
        });
        System.out.println(this.coils.get(0));

        return this.coils.get(0);
    }
}
