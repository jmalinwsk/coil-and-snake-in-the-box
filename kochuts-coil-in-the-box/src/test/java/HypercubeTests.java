import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HypercubeTests {
    private Hypercube hypercube;
    private SnakeInTheBox snakeInTheBox;

    @AfterEach
    public void cleanUp() {
        hypercube = null;
        snakeInTheBox = null;
    }

    @Test
    public void checkIfNeighboursCheckedInDim1() {
        hypercube = new Hypercube(1);
        snakeInTheBox = new SnakeInTheBox(hypercube);
        ArrayList<Integer> expected = new ArrayList<>() {{
            add(0);
        }};
        ArrayList<Integer> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkIfNeighboursCheckedInDim2() {
        hypercube = new Hypercube(2);
        snakeInTheBox = new SnakeInTheBox(hypercube);
        ArrayList<Integer> expected = new ArrayList<>() {{
            add(3); add(1); add(0);
        }};
        ArrayList<Integer> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkIfNeighboursCheckedInDim3() {
        hypercube = new Hypercube(3);
        snakeInTheBox = new SnakeInTheBox(hypercube);
        ArrayList<Integer> expected = new ArrayList<>() {{
            add(6); add(7); add(3); add(1); add(0);
        }};
        ArrayList<Integer> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkIfNeighboursCheckedInDim4() {
        hypercube = new Hypercube(4);
        snakeInTheBox = new SnakeInTheBox(hypercube);
        ArrayList<Integer> expected = new ArrayList<>() {{
            add(13); add(12); add(14); add(6); add(7); add(3); add(1); add(0);
        }};
        ArrayList<Integer> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkIfNeighboursCheckedInDim5() {
        hypercube = new Hypercube(5);
        snakeInTheBox = new SnakeInTheBox(hypercube);
        ArrayList<Integer> expected = new ArrayList<>() {{
            add(22); add(18); add(26); add(27); add(25); add(29); add(28);
            add(12); add(14); add(15); add(7); add(3); add(1); add(0);
        }};
        ArrayList<Integer> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkIfNeighboursCheckedInDim6() {
        hypercube = new Hypercube(6);
        snakeInTheBox = new SnakeInTheBox(hypercube);
        ArrayList<Integer> expected = new ArrayList<>() {{
            add(10); add(26); add(18); add(50); add(48); add(56); add(40);
            add(41); add(43); add(59); add(63); add(62); add(46); add(38);
            add(36); add(37); add(53); add(21); add(20); add(28); add(12);
            add(13); add(15); add(7); add(3); add(1); add(0);
        }};
        ArrayList<Integer> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }

    @Test
    public void checkIfNeighboursCheckedInDim7() {
        hypercube = new Hypercube(7);
        snakeInTheBox = new SnakeInTheBox(hypercube);
        ArrayList<Integer> expected = new ArrayList<>() {{
            //TODO
        }};
        ArrayList<Integer> actual = snakeInTheBox.searchForLongestSnake();

        assertEquals(expected, actual);
    }
}
