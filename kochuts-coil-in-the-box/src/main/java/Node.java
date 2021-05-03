public class Node {
    private int id;
    private boolean marked;

    public Node(int id) {
        this.id = id;
        this.marked = false;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {this.marked = false; }

    public int getId() {
        return id;
    }

    public boolean isMarked() {
        return marked;
    }

    @Override
    public String toString() {
        return "[" + id + ", " + marked + "]";
    }
}
