import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class CoilInTheBox {
    private final Hypercube hypercube;
    private ArrayList<ArrayList<Integer>> coils;
    private Stack<Integer> nodeStack;
    private Stack<Integer> pivotStack;
    private ArrayList<Integer> returnPaths;
    private final boolean showOnlyBestResult;

    public CoilInTheBox(Hypercube hypercube, boolean showOnlyBestResult) {
        this.hypercube = hypercube;
        this.showOnlyBestResult = showOnlyBestResult;
    }

    private void createNodeStack() {
        nodeStack = new Stack<>();
        nodeStack.push(0);
    }

    private void createPivotStack() {
        pivotStack = new Stack<>();
        pivotStack.push(-1);
    }

    private void createReturnPaths() {
        returnPaths = new ArrayList<>();
        for(int i=1; i<hypercube.getDimension(); i++) {
            int neighbourId = 0^(1<<i);
            returnPaths.add(neighbourId);
        }
    }

    private boolean allCurrentNeighboursAreMarked(ArrayList<Node> markedAtThisLevel) {
        return markedAtThisLevel.size() == 0;
    }

    private Integer numberOfPossibleReturnPaths() {
        return returnPaths.size();
    }

    private boolean iThNeighborWasJustMarked(int iThNeighbourId, ArrayList<Node> marked) {
        for (Node node : marked)
            if (node.getId() == iThNeighbourId)
                return true;
        return false;
    }

    private ArrayList<ArrayList<Node>> markCurrentNeighbours(int currentNode) {
        ArrayList<Node> marked = new ArrayList<>();
        ArrayList<Node> removedReturnPath = new ArrayList<>();

        for (int i = 0; i < hypercube.getDimension(); i++) {
            int neighbourId = currentNode ^ (1 << i);
            Node node = hypercube.getNodes().get(neighbourId);
            if (currentNode!=0 && returnPaths.contains(node.getId())) {
                returnPaths.remove(Integer.valueOf(node.getId()));
                removedReturnPath.add(node);
            }
            if (!node.isMarked()) {
                marked.add(node);
                node.mark();
            }
        }
        ArrayList<ArrayList<Node>> result = new ArrayList<>();
        result.add(marked);
        result.add(removedReturnPath);
        return result;
    }

    private void saveBestCoil() {
        Stack<Integer> currentStack = (Stack<Integer>) this.nodeStack.clone();
        ArrayList<Integer> coil = new ArrayList<>();
        while (!currentStack.isEmpty()) {
            coil.add(currentStack.pop());
        }
        if (coils.isEmpty())
            this.coils.add(coil);
        else {
            Collections.sort(this.coils, new Comparator<ArrayList>() {
                public int compare(ArrayList c1, ArrayList c2) {
                    return c2.size() - c1.size();
                }
            });
            if (coil.size() > coils.get(0).size()) {
                coils = new ArrayList<ArrayList<Integer>>();
                coils.add(coil);
            } else if (coil.size() == coils.get(0).size()) {
                coils.add(coil);
            }
        }
    }

    private void saveCoil() {
        Stack<Integer> currentStack = (Stack<Integer>) this.nodeStack.clone();
        ArrayList<Integer> coil = new ArrayList<>();
        while (!currentStack.isEmpty()) {
            coil.add(currentStack.pop());
        }
        this.coils.add(coil);
    }

    private void saveCoilIfCanClose() {
        int top = nodeStack.peek();
        for (int i = 0; i < hypercube.getDimension(); i++) {
            int neighbourId = top ^ (1 << i);
            if (returnPaths.contains(neighbourId)) {
                nodeStack.push(neighbourId);
                if(showOnlyBestResult)
                    saveBestCoil();
                else saveCoil();
                nodeStack.pop();
            }
        }
    }

    private void unmarkAllNodesMarkedAtThisLevel(ArrayList<Node> marked,
                                                 ArrayList<Node> removedReturnPath) {
        for(Node node : removedReturnPath)
            returnPaths.add(node.getId());
        for (Node node : marked)
            node.unmark();
    }

    private void showResult() {
        for(ArrayList<Integer> s : this.coils)
            System.out.println(s.size() + " : " + s);
    }

    private int search(int depth) {
        int currentNode = nodeStack.peek();
        int currentPivot = pivotStack.peek();
        ArrayList<ArrayList<Node>> ret = markCurrentNeighbours(currentNode);
        ArrayList<Node> markedAtThisLevel = ret.get(0);
        ArrayList<Node> removedReturnPath = ret.get(1);

        if (allCurrentNeighboursAreMarked(markedAtThisLevel) || numberOfPossibleReturnPaths() == 0) {
            unmarkAllNodesMarkedAtThisLevel(markedAtThisLevel, removedReturnPath);
            return 0;
        } else {
            for (int i = 0; i <= currentPivot; i++) {
                int iThNeighbourId = currentNode ^ (1 << i);
                if (iThNeighborWasJustMarked(iThNeighbourId, markedAtThisLevel)) {
                    nodeStack.push(iThNeighbourId);
                    pivotStack.push(currentPivot);
                    saveCoilIfCanClose();
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
                    saveCoilIfCanClose();
                    search(depth + 1);
                    nodeStack.pop();
                    pivotStack.pop();
                    if (nodeStack.isEmpty())
                        return 0;
                }
            }
        }
        unmarkAllNodesMarkedAtThisLevel(markedAtThisLevel, removedReturnPath);
        return 0;
    }

    public ArrayList<ArrayList<Integer>> searchForLongestCoil() {
        this.coils = new ArrayList<>();
        hypercube.getNodes().get(0).mark();
        createNodeStack();
        createPivotStack();
        createReturnPaths();

        search(0);

        showResult();

        return this.coils; //for unit testing
    }
}
