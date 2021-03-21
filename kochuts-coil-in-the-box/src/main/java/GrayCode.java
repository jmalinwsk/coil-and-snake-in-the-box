import java.util.Stack;

public class GrayCode {

    // https://www.programcreek.com/2014/05/leetcode-gray-code-java/
    public static Stack<Integer> createGrayCode(int dimension) {
        Stack<Integer> codes;

        if (dimension == 0) {
            codes = new Stack<>();
            codes.push(0);
            return codes;
        }

        codes = createGrayCode(dimension - 1);
        int numberToAdd = 1 << (dimension - 1);
        for (int i = codes.size() - 1; i >= 0; i--)
            codes.push(numberToAdd + codes.get(i));

        return codes;
    }
}
