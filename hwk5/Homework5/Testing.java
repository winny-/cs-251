package Homework5;
import static org.junit.Assert.*;

import org.junit.*;

public class Testing {

	Gym a;
	Gym b;
	Gym c;
	Gym d;
	GymController e;
	GymController f;

	@Before
	public void setUp()
	{
		this.a = new Gym();
		this.b = new Gym(0);
		this.c = new Gym(5,1000);
		this.d = new Gym(6,2000,0.6);
		this.e = new GymController(-0.1,d);
		this.f = new GymController(1.1,c);
	}

	@After
	public void tearDown()
	{    	
		this.a = null;
		this.b = null;
		this.c = null;
	}
	
	@Test 
	public void TestControllerConstructor1()
	{
		assertEquals(e.occupancy,0.5,0);
		assertEquals(e.gym,d);
		assertEquals(f.occupancy,0.5,0);
		assertEquals(f.gym,c);
		assertTrue(e.inTolerance<=e.occupancy);
		assertTrue(e.outTolerance<=1-e.occupancy);
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
		assertArrayEquals(b.gates, gates);



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
		assertEquals(1000,this.c.capacity);
		assertArrayEquals(c.gates, gates);


	}

	@Test
	public void TestConstructor4()
	{
		Gate[] gates = new Gate[6];
		gates[0]= new Gate("gate_0");
		gates[1]= new Gate("gate_1");
		gates[2]= new Gate("gate_2");
		gates[3]= new Gate("gate_3");
		gates[4]= new Gate("gate_4");
		gates[5]= new Gate("gate_5");

		assertEquals(this.d.getClass().getSimpleName().toString(), "Gym");
		assertEquals(2000,this.d.capacity);
		assertArrayEquals(d.gates, gates);
	}

	@Test
	public void TestnumberOfGates()
	{
		assertEquals(6,this.d.numberOfGates());
	}

	@Test
	public void TestnumberOfGatesInState()
	{
		d.gates[0].setTripod(State.IN);
		d.gates[1].setTripod(State.IN);
		d.gates[2].setTripod(State.OUT);
		d.gates[3].setTripod(State.IN);
		d.gates[4].setTripod(State.OUT);
		d.gates[5].setTripod(State.LOCKED);
		assertEquals(d.numberOfGatesInState(State.IN),3);
		assertEquals(d.numberOfGatesInState(State.OUT),2);
		assertEquals(d.numberOfGatesInState(State.LOCKED),1);
	}

	@Test
	public void TestswitchOneGate()
	{
		d.gates[0].setTripod(State.IN);
		d.gates[1].setTripod(State.IN);
		d.gates[2].setTripod(State.OUT);
		d.gates[3].setTripod(State.IN);
		d.gates[4].setTripod(State.OUT);
		d.gates[5].setTripod(State.LOCKED);
		d.switchOneGate(State.OUT);
		assertEquals(d.numberOfGatesInState(State.IN),2);
		assertEquals(d.numberOfGatesInState(State.OUT),3);
		assertEquals(d.numberOfGatesInState(State.LOCKED),1);

		d.gates[0].setTripod(State.OUT);
		d.gates[1].setTripod(State.OUT);
		d.gates[2].setTripod(State.OUT);
		d.gates[3].setTripod(State.OUT);
		d.gates[4].setTripod(State.OUT);
		d.gates[5].setTripod(State.OUT);
		d.switchOneGate(State.OUT);

		assertEquals(d.numberOfGatesInState(State.OUT),6);		
	}

	@Test
	public void TestpersonsInside1()
	{
		d.gates[0].setTripod(State.IN);
		d.gates[1].setTripod(State.IN);
		d.gates[2].setTripod(State.OUT);
		d.gates[3].setTripod(State.IN);
		d.gates[4].setTripod(State.OUT);

		d.gates[0].getIn(650);
		d.gates[1].getIn(150);
		d.gates[2].getOut(200);
		d.gates[3].getIn(550);
		d.gates[4].getOut(150);//no switch

		d.personsInside();
		assertEquals(d.numberOfGatesInState(State.IN),4);
		assertEquals(d.numberOfGatesInState(State.OUT),1);
		assertEquals(d.controller.inTolerance,0.02,0.0);
		assertEquals(d.controller.outTolerance,0.01,0.0);
		assertEquals(d.personsInside(),1000);
	}

	@Test
	public void TestpersonsInside2()
	{	
		d.gates[0].setTripod(State.IN);
		d.gates[1].setTripod(State.IN);
		d.gates[2].setTripod(State.OUT);
		d.gates[3].setTripod(State.IN);
		d.gates[4].setTripod(State.OUT);

		d.gates[0].getIn(1050);
		d.gates[1].getIn(150);
		d.gates[2].getOut(200);
		d.gates[3].getIn(550);
		d.gates[4].getOut(150);//no switch
	

		d.personsInside();
		assertEquals(d.numberOfGatesInState(State.IN),2);
		assertEquals(d.numberOfGatesInState(State.OUT),3);
		assertEquals(d.controller.inTolerance,0.01,0.0);
		assertEquals(d.controller.outTolerance,0.02,0.0);
		assertEquals(d.personsInside(),1400);
	}
	@Test
	public void TestpersonsInside3()
	{
		d.gates[0].setTripod(State.IN);
		d.gates[1].setTripod(State.IN);
		d.gates[2].setTripod(State.IN);
		d.gates[3].setTripod(State.IN);
		d.gates[4].setTripod(State.IN);

		d.getIn(3000);

		d.personsInside();
		assertEquals(d.numberOfGatesInState(State.IN),5);
		assertEquals(d.numberOfGatesInState(State.OUT),0);
		assertEquals(d.controller.inTolerance,0.01,0.0);
		assertEquals(d.controller.outTolerance,0.01,0.0);
		assertEquals(d.personsInside(),0);
	}
}
