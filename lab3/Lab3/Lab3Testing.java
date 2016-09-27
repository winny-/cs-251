package Lab3;

import static org.junit.Assert.*;

import org.junit.Test;
import Lab3.BasedNumber;

public class Lab3Testing {
   

    @Test
    public void test1() { 
    	int [] array = new int[]{1,0,0,0,1};
    	int [] array2 = new int[]{1,7};
    	BasedNumber b1 = new BasedNumber(2, array);
    	BasedNumber b2 = new BasedNumber(10, array2);
    	assertTrue(b1.equals(b2));
    	
    }
    @Test
    public void test2() { 
    	int [] array = new int[]{1,0,0,0,1};
    	int [] array2 = new int[]{1,5};
    	BasedNumber b1 = new BasedNumber(2, array);
    	BasedNumber b2 = new BasedNumber(12, array2);
    	assertTrue(b1.equals(b2));
    	
    }
    
    @Test
    public void test3() { 
    	int [] array = new int[]{1,0,1};
    	int [] array2 = new int[]{5};
    	BasedNumber b1 = new BasedNumber(2, array);
    	BasedNumber b2 = new BasedNumber(16, array2);
    	assertTrue(b1.equals(b2));
    	
    }
    
    @Test
    public void test4() { 
    	int [] array = new int[]{1,0,1};
    	int [] array2 = new int[]{5};
    	BasedNumber b1 = new BasedNumber(-2, array);
    	BasedNumber b2 = new BasedNumber(16, array2);
    	assertTrue(b1.equals(b2));
    }
    
}

