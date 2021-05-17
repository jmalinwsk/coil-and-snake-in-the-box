import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeInTheBoxTests {
    private Hypercube hypercube;
    private SnakeInTheBox snakeInTheBox;

    @AfterEach
    public void cleanUp() {
        hypercube = null;
        snakeInTheBox = null;
    }

    @Test
    public void checkAllResultsForDim1() throws IOException {
        hypercube = new Hypercube(1);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim1() throws IOException {
        hypercube = new Hypercube(1);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsForDim2() throws IOException {
        hypercube = new Hypercube(2);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim2() throws IOException {
        hypercube = new Hypercube(2);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsForDim3() throws IOException {
        hypercube = new Hypercube(3);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(6); add(7); add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim3() throws IOException {
        hypercube = new Hypercube(3);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(6); add(7); add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsForDim4() throws IOException {
        hypercube = new Hypercube(4);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(13); add(12); add(14); add(6); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(10); add(14); add(6); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(12); add(14); add(15); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(10); add(14); add(15); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(12); add(13); add(15); add(7); add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim4() throws IOException {
        hypercube = new Hypercube(4);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(13); add(12); add(14); add(6); add(7); add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsBestLengthForDim5() throws IOException {
        hypercube = new Hypercube(5);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        ArrayList<ArrayList<Integer>> result = snakeInTheBox.searchForLongestSnake();
        int expected = result.get(0).size();
        int actual = 13;

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim5() throws IOException {
        hypercube = new Hypercube(5);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(22); add(18); add(26); add(27); add(25); add(29); add(28);
            add(12); add(14); add(15); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(25); add(27); add(26); add(18); add(22); add(20); add(28);
            add(12); add(14); add(15); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(22); add(20); add(28); add(29); add(25); add(27); add(26);
            add(10); add(14); add(15); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(22); add(20); add(21); add(29); add(25); add(27); add(26);
            add(10); add(14); add(15); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(22); add(20); add(21); add(29); add(25); add(24); add(26);
            add(10); add(14); add(15); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(25); add(29); add(21); add(20); add(22); add(18); add(26);
            add(10); add(14); add(15); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(25); add(29); add(28); add(20); add(22); add(18); add(26);
            add(10); add(14); add(15); add(7); add(3); add(1); add(0);
        }});
        expected.add(new ArrayList<>() {{
            add(25); add(27); add(26); add(18); add(22); add(20); add(28);
            add(12); add(13); add(15); add(7); add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsBestLengthForDim6() throws IOException {
        hypercube = new Hypercube(6);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        ArrayList<ArrayList<Integer>> result = snakeInTheBox.searchForLongestSnake();
        int expected = result.get(0).size();
        for(int i=0; i<10; i++)
            System.out.println(result.get(0).size());
        int actual = 26;

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim6() throws IOException {
        hypercube = new Hypercube(6);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        expected.add(new ArrayList<>() {{
            add(10); add(26); add(18); add(50); add(48); add(56); add(40);
            add(41); add(43); add(59); add(63); add(62); add(46); add(38);
            add(36); add(37); add(53); add(21); add(20); add(28); add(12);
            add(13); add(15); add(7); add(3); add(1); add(0);
        }});
        ArrayList<ArrayList<Integer>> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    @Disabled("do not start this test if you do not have resources and/or time")
    public void checkBestResultForDim7() throws IOException {
        hypercube = new Hypercube(7);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>() {{
        }};
        ArrayList<ArrayList<Integer>> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }
}
