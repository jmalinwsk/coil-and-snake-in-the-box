import java.util.ArrayList;
import java.util.Stack;

public class Hypercube {
    private int depth; //TODO: change later to dimension
    private ArrayList<Node> nodes;
    private Stack<Integer> nodeStack;
    private Stack<Integer> pivotStack;
    private ArrayList<Integer> currentNeighbours;
    private int currentNode;
    private int currentPivot;

    public Hypercube(int depth) {
        this.depth = depth;
    }

    private void createNodeStack() {
        nodeStack = new Stack<>();
        for(Node node : nodes)
            nodeStack.push(node.getId());
    }

    private void createPivotStack() {
        pivotStack = new Stack<>();
        System.out.println(depth);
        for(int i=depth; i>0; i--)
            pivotStack.push(i);
    }

    private void markCurrentNeighbours() {
        this.currentNeighbours = new ArrayList<>();
        for(int i=0; i<depth; i++) {
            int neighbourId = this.currentNode ^ (1<<i);
            for(Node node : nodes) {
                if(node.getId()==neighbourId) {
                    if (!node.isMarked()) {
                        node.mark();
                        this.currentNeighbours.add(neighbourId);
                    }
                }
            }
        }
        System.out.println(this.currentNeighbours);
    }

    public int searchForLongestCoil() {
        this.nodes = GrayCode.createGrayCode(depth);
        createNodeStack();
        createPivotStack();

        this.currentNode = nodeStack.peek();
        this.currentPivot = pivotStack.peek();
        markCurrentNeighbours();
        System.out.println(nodes);
//        if(this.currentNeighbours.size()==0 || ) //TODO number of possible return paths is zero
//            return 0; //TODO: zmienic to pozniej na odpowiedni format
//        else {
//            for (int i=0; i<currentPivot; i++) {
//                if(currentNeighbours.get(i).isMarked()) {
//                    if() //TODO: can_close_coil
//                        //TODO: save_coil(depth);
//
//                }
//            }
//        }


        return 0;
    }
}
