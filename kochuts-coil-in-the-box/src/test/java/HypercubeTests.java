import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HypercubeTests {
    private Hypercube hypercube;

    @AfterEach
    public void cleanUp() {
        hypercube = null;
    }

    @Test
    public void checkIfNeighboursCheckedInDim3() {
        hypercube = new Hypercube(2);
        assertEquals(hypercube.searchForLongestCoil(), 2);
    }
}
