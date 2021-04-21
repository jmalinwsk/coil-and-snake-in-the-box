import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HypercubeTests {
    private Hypercube hypercube;

    @AfterEach
    public void cleanUp() {
        hypercube = null;
    }

    @Test
    public void checkIfNeighboursCheckedInDim2() {
        hypercube = new Hypercube(3);
        hypercube.searchForLongestCoil();
        assertEquals(0, 0);
    }
}
