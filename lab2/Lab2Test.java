package Lab2;

import Lab2.Lab2;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Lab2Test {
    @Test
    public void testCountWords() {
        assertEquals(Lab2.countWords("hello world"), 2);
        assertEquals(Lab2.countWords("two    words"), 2);
        assertEquals(Lab2.countWords("ONe two threeeee   "), 3);
        assertEquals(Lab2.countWords(""), 0);
        assertEquals(Lab2.countWords("a"), 1);
    }

    @Test
    public void testSpoonerizeAt() {
        assertEquals(Lab2.spoonerizeAt("no one would say this in real life", 2), "no one sould way this in real life");
        assertEquals(Lab2.spoonerizeAt("what happens now", 1), "what nappens how");
        assertEquals(Lab2.spoonerizeAt("this turns out to be a bad approximation", 0), "this turns out to be a bad approximation");
    }
}
