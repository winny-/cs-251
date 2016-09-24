package Homework2;

import Homework2.Homework2;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Homework2Test {

    @Test
    public void testFormatFullIfStatement() {
        assertEquals("if (x < 20) {\n\tSystem.out.print(\"This is then part\");\n}\nelse {\n\tSystem.out.print(\"This is else part\");\n}",
                     Homework2.format("if(x < 20){System.out.print(\"This is then part\"); }else{\nSystem.out.print(\"This is else part\");          }"));
    }

    @Test
    public void testFormatIfNoElseStatement() {
        assertEquals("if (x < 20) {\n\tSystem.out.print(\"This is the if part\");\n}",
                     Homework2.format("if(x < 20){System.out.print(\"This is the if part\"); }"));
    }
}
