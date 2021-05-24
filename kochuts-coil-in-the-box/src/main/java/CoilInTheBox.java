import java.io.IOException;
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
    private final boolean saveToFile;
    private FileParser fileParser;

    public CoilInTheBox(Hypercube hypercube, boolean showOnlyBestResult, boolean saveToFile) throws IOException {
        this.hypercube = hypercube;
        this.showOnlyBestResult = showOnlyBestResult;
        this.saveToFile = saveToFile;
        if(this.saveToFile)
            if(showOnlyBestResult)
                this.fileParser = new FileParser(
                        "output/coil_" +
                                this.hypercube.getDimension() +
                                "_bestOnly.csv");
            else
                this.fileParser = new FileParser(
                        "output/coil_" +
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

    private void sortCoils() {
        Collections.sort(this.coils, new Comparator<ArrayList>() {
            public int compare(ArrayList c1, ArrayList c2) {
                return c2.size() - c1.size();
            }
        });
    }

    private void saveBestCoil() throws IOException {
        Stack<Integer> currentStack = (Stack<Integer>) this.nodeStack.clone();
        ArrayList<Integer> coil = new ArrayList<>();
        while (!currentStack.isEmpty()) {
            coil.add(currentStack.pop());
        }
        if (coils.isEmpty()) {
            this.coils.add(coil);
            if(saveToFile)
                fileParser.addToFile(coil, "coil");
        }
        else {
            sortCoils();
            if (coil.size() > coils.get(0).size()) {
                coils = new ArrayList<ArrayList<Integer>>();
                coils.add(coil);
                if(saveToFile) {
                    fileParser.cleanFile();
                    fileParser.addToFile(coil, "coil");
                }
            } else if (coil.size() == coils.get(0).size()) {
                coils.add(coil);
                if(saveToFile)
                    fileParser.addToFile(coil, "coil");
            }
        }
    }

    private void saveCoil() throws IOException {
        Stack<Integer> currentStack = (Stack<Integer>) this.nodeStack.clone();
        ArrayList<Integer> coil = new ArrayList<>();
        while (!currentStack.isEmpty()) {
            coil.add(currentStack.pop());
        }
        this.coils.add(coil);
        if(saveToFile)
            fileParser.addToFile(coil, "coil");
    }

    private void saveCoilIfCanClose() throws IOException {
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

    private void printCoils() {
        for(ArrayList<Integer> s : this.coils)
            System.out.println(s.size() + " : " + s);
    }

    private void showResult() {
        if(showOnlyBestResult)
            printCoils();
        else {
            sortCoils();
            printCoils();
        }
    }

    private int search(int depth) throws IOException {
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
                }
            }
        }
        unmarkAllNodesMarkedAtThisLevel(markedAtThisLevel, removedReturnPath);
        return 0;
    }

    public ArrayList<ArrayList<Integer>> searchForLongestCoil() throws IOException {
        this.coils = new ArrayList<>();
        hypercube.getNodes().get(0).mark();
        createNodeStack();
        createPivotStack();
        createReturnPaths();
        if(saveToFile)
            fileParser.createFile();

        search(0);

        if(saveToFile)
            fileParser.closeFile();
        else showResult();

        return this.coils; //for unit testing
    }
}
