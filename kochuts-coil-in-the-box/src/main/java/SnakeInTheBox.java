import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class SnakeInTheBox {
    private final Hypercube hypercube;
    private ArrayList<ArrayList<Integer>> snakes;
    private Stack<Integer> nodeStack;
    private Stack<Integer> pivotStack;
    private final boolean showOnlyBestResult;
    private final boolean saveToFile;
    private FileParser fileParser;
    private int longestSnakeSize;

    public SnakeInTheBox(Hypercube hypercube, boolean showOnlyBestResult, boolean saveToFile) throws IOException {
        this.hypercube = hypercube;
        this.showOnlyBestResult = showOnlyBestResult;
        this.saveToFile = saveToFile;
        if (this.saveToFile)
            if (showOnlyBestResult)
                this.fileParser = new FileParser(
                        "output/snake_" +
                                this.hypercube.getDimension() +
                                "_bestOnly.csv");
            else
                this.fileParser = new FileParser(
                        "output/snake_" +
                                this.hypercube.getDimension() +
                                "_all.csv");
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

    private void sortSnakes() {
        this.snakes.sort((Comparator<ArrayList>) (s1, s2) -> s2.size() - s1.size());
    }

    private void saveBestSnake() throws IOException {
        Stack<Integer> currentStack = (Stack<Integer>) this.nodeStack.clone();
        ArrayList<Integer> snake = new ArrayList<>();
        while (!currentStack.isEmpty()) {
            snake.add(currentStack.pop());
        }
        if (snakes.isEmpty()) {
            this.snakes.add(snake);
            if (saveToFile)
                fileParser.addToFile(snake, "snake");
            longestSnakeSize = snake.size() - 1;
        } else {
            sortSnakes();
            if (snake.size() - 1 > longestSnakeSize) {
                longestSnakeSize = snake.size() - 1;
                snakes = new ArrayList<>();
                snakes.add(snake);
                if (saveToFile) {
                    fileParser.cleanFile();
                    fileParser.addToFile(snake, "snake");
                }
            } else if (snake.size() - 1 == longestSnakeSize) {
                snakes.add(snake);
                if (saveToFile)
                    fileParser.addToFile(snake, "snake");
            }
        }
    }

    private void saveSnake() throws IOException {
        Stack<Integer> currentStack = (Stack<Integer>) this.nodeStack.clone();
        ArrayList<Integer> snake = new ArrayList<>();
        while (!currentStack.isEmpty()) {
            snake.add(currentStack.pop());
        }
        this.snakes.add(snake);
        if (saveToFile)
            fileParser.addToFile(snake, "snake");
        if (snake.size() - 1 > longestSnakeSize)
            longestSnakeSize = snake.size() - 1;
    }

    private void unmarkAllNodesMarkedAtThisLevel(ArrayList<Node> marked) {
        for (Node node : marked)
            node.unmark();
    }

    private void printSnakes() {
        for (ArrayList<Integer> s : this.snakes)
            System.out.println(s.size() - 1 + " : " + s);
    }

    private void showResult() {
        if (!showOnlyBestResult)
            sortSnakes();
        printSnakes();
    }

    private int search(int depth) throws Exception {
        int currentNode = nodeStack.peek();
        int currentPivot = pivotStack.peek();
        ArrayList<Node> markedAtThisLevel = markCurrentNeighbours(currentNode);

        if (allCurrentNeighboursAreMarked(markedAtThisLevel)) {
            if (showOnlyBestResult)
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
                }
            }
        }
        unmarkAllNodesMarkedAtThisLevel(markedAtThisLevel);
        return 0;
    }

    public int searchForLongestSnake() throws Exception {
        this.snakes = new ArrayList<>();
        longestSnakeSize = -1;
        hypercube.getNodes().get(0).mark();
        createNodeStack();
        createPivotStack();
        if (saveToFile)
            fileParser.createFile();

        search(0);

        if (saveToFile)
            fileParser.closeFile();
        else showResult();


        return longestSnakeSize; //for unit testing
    }
}
