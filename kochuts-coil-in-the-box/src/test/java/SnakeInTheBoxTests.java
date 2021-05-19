import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
    public void checkAllResultsForDim1() throws Exception {
        hypercube = new Hypercube(1);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        int expected = 1;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim1() throws Exception {
        hypercube = new Hypercube(1);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        int expected = 1;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsForDim2() throws Exception {
        hypercube = new Hypercube(2);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        int expected = 2;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim2() throws Exception {
        hypercube = new Hypercube(2);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        int expected = 2;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsForDim3() throws Exception {
        hypercube = new Hypercube(3);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        int expected = 4;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim3() throws Exception {
        hypercube = new Hypercube(3);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        int expected = 4;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsForDim4() throws Exception {
        hypercube = new Hypercube(4);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        int expected = 7;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim4() throws Exception {
        hypercube = new Hypercube(4);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        int expected = 7;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsBestLengthForDim5() throws Exception {
        hypercube = new Hypercube(5);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        int expected = 13;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim5() throws Exception {
        hypercube = new Hypercube(5);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        int expected = 13;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkAllResultsBestLengthForDim6() throws Exception {
        hypercube = new Hypercube(6);
        snakeInTheBox = new SnakeInTheBox(hypercube, false, true);
        int expected = 26;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkBestResultForDim6() throws Exception {
        hypercube = new Hypercube(6);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        int expected = 26;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    @Disabled("do not start this test if you do not have enough resources and/or time")
    public void checkBestResultForDim7() throws Exception {
        hypercube = new Hypercube(7);
        snakeInTheBox = new SnakeInTheBox(hypercube, true, true);
        int expected = 50;
        int actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }
}
