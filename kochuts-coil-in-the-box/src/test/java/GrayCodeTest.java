import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class GrayCodeTest {
    ArrayList<Node> actual;
    ArrayList<Integer> actualInteger;
    Iterator<Node> actualIterator;

    @BeforeEach
    public void init() {
        actualInteger = new ArrayList<>();
    }

    @AfterEach
    public void cleanUp() {
        actual = null;
        actualInteger = null;
        actualIterator = null;
    }

    @Test
    public void checkGrayCodeForDim0() {
        actual = GrayCode.createGrayCode(0);
        actualIterator = actual.iterator();
        while(actualIterator.hasNext())
            actualInteger.add(actualIterator.next().getId());
        ArrayList<Integer> expected = new ArrayList<>(Collections.singletonList(0));

        assertArrayEquals(expected.toArray(), actualInteger.toArray());
    }

    @Test
    public void checkGrayCodeForDim1() {
        actual = GrayCode.createGrayCode(1);
        actualIterator = actual.iterator();
        while(actualIterator.hasNext())
            actualInteger.add(actualIterator.next().getId());
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0, 1));

        assertArrayEquals(expected.toArray(), actualInteger.toArray());
    }

    @Test
    public void checkGrayCodeForDim2() {
        actual = GrayCode.createGrayCode(2);
        actualIterator = actual.iterator();
        while(actualIterator.hasNext())
            actualInteger.add(actualIterator.next().getId());
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 3, 2));

        assertArrayEquals(expected.toArray(), actualInteger.toArray());
    }

    @Test
    public void checkGrayCodeForDim3() {
        actual = GrayCode.createGrayCode(3);
        actualIterator = actual.iterator();
        while(actualIterator.hasNext())
            actualInteger.add(actualIterator.next().getId());
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 3, 2, 6, 7, 5, 4));

        assertArrayEquals(expected.toArray(), actualInteger.toArray());
    }

    @Test
    public void checkGrayCodeForDim7() {
        actual = GrayCode.createGrayCode(7);
        actualIterator = actual.iterator();
        while(actualIterator.hasNext())
            actualInteger.add(actualIterator.next().getId());
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(
                0, 1, 3, 2, 6, 7, 5, 4, 12, 13, 15, 14, 10, 11, 9, 8, 24, 25, 27, 26, 30, 31, 29, 28, 20, 21,
                23, 22, 18, 19, 17, 16, 48, 49, 51, 50, 54, 55, 53, 52, 60, 61, 63, 62, 58, 59, 57, 56, 40,
                41, 43, 42, 46, 47, 45, 44, 36, 37, 39, 38, 34, 35, 33, 32, 96, 97, 99, 98, 102, 103, 101,
                100, 108, 109, 111, 110, 106, 107, 105, 104, 120, 121, 123, 122, 126, 127, 125, 124, 116, 117,
                119, 118, 114, 115, 113, 112, 80, 81, 83, 82, 86, 87, 85, 84, 92, 93, 95, 94, 90, 91, 89, 88,
                72, 73, 75, 74, 78, 79, 77, 76, 68, 69, 71, 70, 66, 67, 65, 64));

        assertArrayEquals(expected.toArray(), actualInteger.toArray());
    }
}
