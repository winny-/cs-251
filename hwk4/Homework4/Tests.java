package Homework4;

import static org.junit.Assert.*;

import org.junit.*;

import Homework4.Gym;
import Homework4.Gate;

public class Tests {
    Gym a;
    Gym b;
    Gym c;

    @Before
    public void setUp()
    {
        this.a = new Gym();
        this.b = new Gym(0);
        this.c = new Gym(5,100);
    }

    @After
    public void tearDown()
    {    	
    	this.a = null;
        this.b = null;
        this.c = null;
    }

    @Test 
    public void TestConstructor1()
    {
    	Gate[] gates = new Gate[3];
    	gates[0]= new Gate("gate_0");
    	gates[1]= new Gate("gate_1");
    	gates[2]= new Gate("gate_2");
    	
    	assertEquals(this.a.getClass().getSimpleName().toString(), "Gym");
        assertEquals(1000,this.a.capacity);
        assertArrayEquals(a.gates, gates);
    }
    

    
    @Test 
    public void TestConstructor2()
    {
    	
    	Gate[] gates = new Gate[0];

    	assertEquals(this.b.getClass().getSimpleName().toString(), "Gym");
        assertEquals(1000,this.b.capacity);
        assertArrayEquals(gates,this.b.gates);

    }
    
    @Test 
    public void TestConstructor3()
    {
    	
    	Gate[] gates = new Gate[5];
    	gates[0]= new Gate("gate_0");
    	gates[1]= new Gate("gate_1");
    	gates[2]= new Gate("gate_2");
    	gates[3]= new Gate("gate_3");
    	gates[4]= new Gate("gate_4");

    	assertEquals(this.c.getClass().getSimpleName().toString(), "Gym");
        assertEquals(100,this.c.capacity);
        assertArrayEquals(gates,this.c.gates);

    }
    
    @Test 
    public void TestOpen1()
    {
    	for(Gate g: a.gates)
            assertTrue(g.isLocked());

    	a.open();
    	
    	for(Gate g: a.gates)
            assertFalse(g.isLocked());
    	
    	assertEquals(State.IN,a.gates[0].tripodState());
    	assertEquals(State.OUT,a.gates[1].tripodState());
    	assertEquals(State.OUT,a.gates[2].tripodState());
		
    }
    @Test 
    public void TestOpen2()
    {
    	for(Gate g: a.gates)
            assertTrue(g.isLocked());

    	a.open(10);
    	
    	for(Gate g: a.gates)
            assertFalse(g.isLocked());
    	
    	assertEquals( State.IN,a.gates[0].tripodState());
    	assertEquals( State.IN,a.gates[1].tripodState());
    	assertEquals( State.OUT,a.gates[2].tripodState());
		
    }
    
    @Test 
    public void TestOpen3()
    {
    	for(Gate g: a.gates)
            assertTrue(g.isLocked());

    	a.open(-5);
    	
    	for(Gate g: a.gates)
            assertFalse(g.isLocked());
    	
    	assertEquals( State.IN,a.gates[0].tripodState());
    	assertEquals( State.OUT,a.gates[1].tripodState());
    	assertEquals( State.OUT,a.gates[2].tripodState());
		
    }
    
    @Test 
    public void TestLockDown()
    {
    	for(Gate g: a.gates)
            g.open(State.IN);

     	for(Gate g: a.gates)
            assertFalse(g.isLocked());
     	
    	a.lockDown();
    	
    	for(Gate g: a.gates)
            assertTrue(g.isLocked());
	
    }
    
    @Test 
    public void TestCanGoIn()
    {
        assertTrue(a.canGoIn());
        a.gates[0].getIn(10000000);
        assertTrue(a.canGoIn());//Since the gate is not opened
        		
        a.open();
        a.gates[0].getIn(999);
        assertTrue(a.canGoIn());
        a.gates[0].getIn(2);
        assertFalse(a.canGoIn());

    }
    
    @Test 
    public void TestCanGoOut()
    {
        assertFalse(a.canGoOut());
 
        a.open();
        a.gates[0].getIn(999);
        assertTrue(a.canGoIn());
        a.gates[2].getOut(999);
        assertFalse(a.canGoOut());

    }
    @Test 
    public void TestpersonsInside()
    {
           	   
        c.open(4);
        c.gates[0].getIn(20);
        c.gates[1].getIn(20);
        c.gates[2].getIn(20);
        c.gates[3].getIn(20);
        assertEquals(80, c.personsInside());
        c.gates[4].getIn(20);
        assertEquals(80, c.personsInside());
        c.gates[4].getOut(50);
        assertEquals(30, c.personsInside());

    }
    @Test 
    public void TestgetIn()
    {
           	   
        c.open(3);
        assertEquals(0, c.personsInside());
        c.getIn(0);
        assertEquals(1, c.personsInside());
        c.getIn(-1);
        assertEquals(1, c.personsInside());
        c.getIn(2);
        c.getIn(2);
        assertEquals(3, c.personsInside());
        c.getIn(4);
        assertEquals(3, c.personsInside());
        c.getIn(10);
        assertEquals(3, c.personsInside());
        c.getIn(2);
        c.getIn(2);
        c.getIn(2);
        c.getIn(2);
        assertEquals(7, c.personsInside());

    }
     
    @Test 
    public void TestgetOut()
    {
           	   
        c.open(1);
        assertEquals(0, c.personsInside());
        c.gates[0].getIn(50);
        c.getOut(0);
        assertEquals(50, c.personsInside());
        c.getOut(-6);
        assertEquals(50, c.personsInside());
        c.getOut(1);
        assertEquals(49, c.personsInside());
        c.getOut(4);
        assertEquals(48, c.personsInside());
        c.getOut(3);
        assertEquals(47, c.personsInside());
        		
        for(int index=0; index<47;index++)
            c.getOut(2);
        assertEquals(0, c.personsInside());
    }
    
    @Test 
    public void TestToString()
    {
         		
        assertEquals("Gate gate_0 is locked.\n0 persons went out through this gate\n\nGate gate_1 is locked.\n0 persons went out through this gate\n\nGate gate_2 is locked.\n0 persons went out through this gate\n\nGate gate_3 is locked.\n0 persons went out through this gate\n\nGate gate_4 is locked.\n0 persons went out through this gate\n\n", c.toString());
        c.open(2);
        assertEquals("Gate gate_0 is open to get in.\n0 persons went out through this gate\n\nGate gate_1 is open to get in.\n0 persons went out through this gate\n\nGate gate_2 is open to get out.\n0 persons went out through this gate\n\nGate gate_3 is open to get out.\n0 persons went out through this gate\n\nGate gate_4 is open to get out.\n0 persons went out through this gate\n\n", c.toString());
        c.getIn(0);
        c.getIn(0);
        c.getIn(1);
        c.getIn(1);
        c.getOut(4);;
        assertEquals("Gate gate_0 is open to get in.\n2 persons came in through this gate\n\nGate gate_1 is open to get in.\n2 persons came in through this gate\n\nGate gate_2 is open to get out.\n0 persons went out through this gate\n\nGate gate_3 is open to get out.\n0 persons went out through this gate\n\nGate gate_4 is open to get out.\n1 persons went out through this gate\n\n", c.toString());

    }
    
    
}
