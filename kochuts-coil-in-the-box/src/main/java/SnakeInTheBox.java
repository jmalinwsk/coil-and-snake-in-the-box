import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class SnakeInTheBox {
    private final Hypercube hypercube;
    private ArrayList<ArrayList<Integer>> snakes;
    private Stack<Integer> nodeStack;
    private Stack<Integer> pivotStack;
    private final boolean showOnlyBestResult;

    public SnakeInTheBox(Hypercube hypercube, boolean showOnlyBestResult) {
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

    private void saveBestSnake() {
        Stack<Integer> currentStack = (Stack<Integer>) this.nodeStack.clone();
        ArrayList<Integer> snake = new ArrayList<>();
        while (!currentStack.isEmpty()) {
            snake.add(currentStack.pop());
        }
        if (snakes.isEmpty())
            this.snakes.add(snake);
        else {
            Collections.sort(this.snakes, new Comparator<ArrayList>() {
                public int compare(ArrayList s1, ArrayList s2) {
                    return s2.size() - s1.size();
                }
            });
            if (snake.size() > snakes.get(0).size()) {
                snakes = new ArrayList<ArrayList<Integer>>();
                snakes.add(snake);
            } else if (snake.size() == snakes.get(0).size()) {
                snakes.add(snake);
            }
        }
    }

    private void saveSnake() {
        Stack<Integer> currentStack = (Stack<Integer>) this.nodeStack.clone();
        ArrayList<Integer> snake = new ArrayList<>();
        while (!currentStack.isEmpty()) {
            snake.add(currentStack.pop());
        }
        this.snakes.add(snake);
    }

    private void unmarkAllNodesMarkedAtThisLevel(ArrayList<Node> marked) {
        for (Node node : marked)
            node.unmark();
    }

    private void showResult() {
        for(ArrayList<Integer> s : this.snakes)
            System.out.println(s.size()-1 + " : " + s);
    }

    private int search(int depth) {
        int currentNode = nodeStack.peek();
        int currentPivot = pivotStack.peek();
        ArrayList<Node> markedAtThisLevel = markCurrentNeighbours(currentNode);

        if (allCurrentNeighboursAreMarked(markedAtThisLevel)) {
            if(showOnlyBestResult)
                saveBestSnake();
                else saveSnake();
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

    public ArrayList<ArrayList<Integer>> searchForLongestSnake() {
        this.snakes = new ArrayList<>();
        hypercube.getNodes().get(0).mark();
        createNodeStack();
        createPivotStack();

        search(0);

        showResult();

        return this.snakes; //for unit testing
    }
}
