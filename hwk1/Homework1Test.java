package Homework1;

import Homework1.Homework1;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Homework1Test {
    @Test
    public void testCompute() {
        assertEquals(Homework1.compute(10000, 5),
                     0.0271,
                     0.00001);
        assertEquals(Homework1.compute(500000, 18),
                     0.346936,
                     0.0000001);
    }
}
