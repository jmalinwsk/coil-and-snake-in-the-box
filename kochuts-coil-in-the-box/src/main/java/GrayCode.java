import java.util.ArrayList;

public class GrayCode {
    public static ArrayList<Node> createGrayCode(int dimension) {
        ArrayList<Node> codes;

        if (dimension == 0) {
            codes = new ArrayList<>();
            codes.add(new Node(0));
            return codes;
        }

        codes = createGrayCode(dimension - 1);
        int numberToAdd = 1 << (dimension - 1);
        for (int i = codes.size() - 1; i >= 0; i--)
            codes.add(new Node(numberToAdd + codes.get(i).getId()));

        return codes;
    }
}
