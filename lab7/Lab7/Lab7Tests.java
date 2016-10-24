package Lab7;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Lab7Tests {

    public PointList w, x, y, z;
    public PointList a, b, c;
    
    @Before
    public void setUp() {
        this.a = new PointList();
        this.b = new PointList();
        this.c = new PointList();
        
        this.w = new PointList();
        this.w.insert(0, -1);

        this.x = new PointList();
        this.x.insert(0, 0);
        this.x.insert(-1, -1);
        this.x.insert(1, 1);


        this.y = new PointList();

        this.z = new PointList();
        this.z.insert(1, 2);
        this.z.insert(1, 4);
    }

    @After
    public void tearDown() {
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;

        this.a = null;
        this.b = null;
        this.c = null;
    }

    @Test
    public void testInsertReturn() {
        assertTrue(this.a.insert(0, 0));
        assertFalse(this.a.insert(0, 0));
        assertTrue(this.a.insert(1, 1));
    }

    @Test
    public void testEmptyToString() {
        assertEquals("", y.toString());
    }

    @Test
    public void testOneToString() {
        assertEquals("(0, -1); distance to origin: 1\n", w.toString());
    }

    @Test
    public void testMultipleToString() {
        assertEquals("(0, 0); distance to origin: 0\n" +
                     "(-1, -1); distance to origin: 1.41\n" +
                     "(1, 1); distance to origin: 1.41\n",
                     x.toString());
    }
}
