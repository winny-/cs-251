package CheckHomework8;

import static org.junit.Assert.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import org.junit.*;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CheckHomework8 {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp()
    {
        System.setOut(new PrintStream(outContent));

    }

    @After
    public void tearDown()
    { 
        System.setOut(null);
    }

    @Test(expected=Exception.class)
    public void testA() throws Exception
    {
        this.throwAnException();
        assertEquals(String.format("An error occured%n"), outContent.toString());
    }

    public static void throwAnException() throws Exception
    {
        System.out.println("This is the wrong error message");
        throw new Exception("oops");
    }
}
