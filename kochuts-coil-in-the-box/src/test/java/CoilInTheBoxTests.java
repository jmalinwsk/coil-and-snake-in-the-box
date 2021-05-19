import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoilInTheBoxTests {
    private Hypercube hypercube;
    private CoilInTheBox coilInTheBox;

    @AfterEach
    public void cleanUp() {
        hypercube = null;
        coilInTheBox = null;
    }

    @Test
    public void checkAllResultsForDim2() throws IOException {
        hypercube = new Hypercube(2);
        coilInTheBox = new CoilInTheBox(hypercube, false, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(2); add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = coilInTheBox.searchForLongestCoil();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim2() throws IOException {
        hypercube = new Hypercube(2);
        coilInTheBox = new CoilInTheBox(hypercube, true, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(2); add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = coilInTheBox.searchForLongestCoil();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsForDim3() throws IOException {
        hypercube = new Hypercube(3);
        coilInTheBox = new CoilInTheBox(hypercube, false, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(4); add(6); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(2); add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = coilInTheBox.searchForLongestCoil();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim3() throws IOException {
        hypercube = new Hypercube(3);
        coilInTheBox = new CoilInTheBox(hypercube, true, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(4); add(6); add(7); add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = coilInTheBox.searchForLongestCoil();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsBestLengthForDim4() throws IOException {
        hypercube = new Hypercube(4);
        coilInTheBox = new CoilInTheBox(hypercube, false, true);
        int expected = 8;
        ArrayList<ArrayList<Integer>> coils = coilInTheBox.searchForLongestCoil();
        int actual = coils.get(0).size();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim4() throws IOException {
        hypercube = new Hypercube(4);
        coilInTheBox = new CoilInTheBox(hypercube, true, true);
        int expected = 8;
        ArrayList<ArrayList<Integer>> coils = coilInTheBox.searchForLongestCoil();
        int actual = coils.get(0).size();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsBestLengthForDim5() throws IOException {
        hypercube = new Hypercube(5);
        coilInTheBox = new CoilInTheBox(hypercube, false, true);
        int expected = 14;
        ArrayList<ArrayList<Integer>> coils = coilInTheBox.searchForLongestCoil();
        int actual = coils.get(0).size();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim5() throws IOException {
        hypercube = new Hypercube(5);
        coilInTheBox = new CoilInTheBox(hypercube, true, true);
        int expected = 14;
        ArrayList<ArrayList<Integer>> coils = coilInTheBox.searchForLongestCoil();
        int actual = coils.get(0).size();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsBestLengthForDim6() throws IOException {
        hypercube = new Hypercube(6);
        coilInTheBox = new CoilInTheBox(hypercube, false, true);
        int expected = 26;
        ArrayList<ArrayList<Integer>> coils = coilInTheBox.searchForLongestCoil();
        int actual = coils.get(0).size();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim6() throws IOException {
        hypercube = new Hypercube(6);
        coilInTheBox = new CoilInTheBox(hypercube, true, true);
        int expected = 26;
        ArrayList<ArrayList<Integer>> coils = coilInTheBox.searchForLongestCoil();
        int actual = coils.get(0).size();

        assertEquals(expected, actual);
    }

    @Test
    @Disabled("do not start this test if you do not have enough resources and/or time")
    public void checkBestResultForDim7() throws IOException {
        hypercube = new Hypercube(7);
        coilInTheBox = new CoilInTheBox(hypercube, true, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>() {{
        }};
        ArrayList<ArrayList<Integer>> actual = coilInTheBox.searchForLongestCoil();

        assertEquals(expected, actual);
    }
}