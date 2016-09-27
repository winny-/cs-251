// Name:       Winston Weinert
// Class:      COMPSCI 251
// Assignment: Homework3
// Date:       27-Sept-2016
//
// -- Comments --
//
// I changed the package and class name.

package Homework3;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.Test;
import Homework3.Gate;

public class Homework3 {
	private static final int CAPACITY = 1000; 

	public static void main(String[] args) {
		Gate left = new Gate("left", State.IN), 
			middle = new Gate("middle", State.OUT), 
			right = new Gate("right", State.IN);

		int numOfSimulations = 5000;

		Random random = new Random();
		for(int i=0; i<numOfSimulations; i++) {
			
			int choice = random.nextInt(6);

			int personInGym = numberPersonInGym(left, middle, right);

			if (personInGym < CAPACITY) {
				switch(choice) {
				case 0: left.getIn(1); break;
				case 1: middle.getIn(1); break;
				case 2: right.getIn(1); 
				}
			}
			if (personInGym > 0) {
				switch(choice) { 
				case 3: left.getOut(1); break;
				case 4: middle.getOut(1); break;
				case 5: right.getOut(1);
				}
			}
		}
		String output = String.format("After %d simulations: %d persons are in the gym\n", 
				numOfSimulations,
				numberPersonInGym(left, middle, right));
		
		System.out.printf("%s\n%s\n%s\n%s\n", output, left, middle, right);
	}

	private static int numberPersonInGym(Gate left, Gate middle, Gate right) {
		return left.numPersonGoneInside() + middle.numPersonGoneInside() + right.numPersonGoneInside();
	}
	

	@Test
	public void testGateConstructor() {
		Gate testGate = new Gate("left");
		assertEquals(testGate.getClass().getSimpleName().toString(), "Gate");
		assertEquals(testGate.isLocked(), true);
	}

	public void testGateConstructor2() {
		Gate testGate = new Gate("middle", State.IN);
		assertEquals(testGate.getClass().getSimpleName().toString(), "Gate");
		assertEquals(testGate.isLocked(), false);
	}

	@Test
	public void testOpen() {
		Gate testGate = new Gate("left");
		assertFalse(testGate.open(State.LOCKED));
		assertTrue(testGate.open(State.IN));

		assertFalse(testGate.open(State.IN));
		assertFalse(testGate.open(State.OUT));
		testGate.close();
		assertTrue(testGate.open(State.IN));

	}

	@Test
	public void testGateLocked() {
		Gate testGate = new Gate("left");
		assertTrue(testGate.isLocked());
		testGate.open(State.LOCKED);
		assertFalse(testGate.open(State.LOCKED));
	}

	@Test
	public void testOpenIn() {
		Gate testGate = new Gate("left");
		assertTrue(testGate.open(State.IN));
		assertFalse(testGate.isLocked());
		assertEquals(testGate.tripodState(), State.IN);
	}

	@Test
	public void testOpenInThroughCons() {
		Gate testGate = new Gate("left", State.IN);
		assertFalse(testGate.isLocked());
		assertEquals(testGate.tripodState(), State.IN);
	}

	@Test
	public void testOpenOut() {
		Gate testGate = new Gate("left", State.OUT);
		assertFalse(testGate.isLocked());
		assertEquals(testGate.tripodState(), State.OUT);
	}

	@Test
	public void testCloseGate() {
		Gate testGate = new Gate("left", State.OUT);
		testGate.close();
		assertTrue(testGate.isLocked());
	}

	@Test
	public void testGetIntWithStateOut() {
		Gate testGate = new Gate("right", State.LOCKED);
		assertTrue(testGate.open(State.OUT));
		testGate.getIn(100);
		assertEquals(testGate.numPersonGoneInside(), 0);
	}

	@Test
	public void testGetIntWithStateIn() {
		Gate testGate = new Gate("right", State.LOCKED);
		assertTrue(testGate.open(State.IN));
		testGate.getIn(100);
		assertEquals(testGate.numPersonGoneInside(), 100);
	}

	@Test
	public void testGetOuttWithStateIn() {
		Gate testGate = new Gate("right", State.LOCKED);
		assertTrue(testGate.open(State.IN));
		testGate.getOut(100);
		assertEquals(testGate.numPersonGoneInside(), 0);
	}

	@Test
	public void testGetOuttWithStateOut() {
		Gate testGate = new Gate("right", State.LOCKED);
		assertTrue(testGate.open(State.OUT));
		testGate.getOut(100);
		assertEquals(testGate.numPersonGoneInside(), -100);
	}

	@Test
	public void testInAndOut() {
		Gate testGate = new Gate("right", State.LOCKED);
		assertTrue(testGate.open(State.IN));
		testGate.getIn(100);
		testGate.setTripod(State.OUT);
		testGate.getOut(500);
		assertEquals(testGate.numPersonGoneInside(), -400);

	}

	@Test
	public void testInWhenClosed() {
		Gate testGate = new Gate("right", State.LOCKED);
		testGate.getIn(100);
		assertEquals(testGate.numPersonGoneInside(), 0);
		assertTrue(testGate.open(State.IN));
		testGate.getIn(100);
		testGate.close();
		testGate.getIn(1000);
		assertEquals(testGate.numPersonGoneInside(), 100);
		testGate.getOut(300);
		assertEquals(testGate.numPersonGoneInside(), 100);

	}

	@Test
	public void testGateToString1() {
		Gate testGate = new Gate("left");
		assertEquals(testGate.toString(), "Gate left is locked.\n0 persons went out through this gate\n");
		testGate.open(State.IN);
		assertEquals(testGate.toString(), "Gate left is open to get in.\n0 persons went out through this gate\n");
		testGate.setTripod(State.OUT);
		assertEquals(testGate.toString(), "Gate left is open to get out.\n0 persons went out through this gate\n");

	}

	@Test
	public void testGateToString2() {

		Gate testGate = new Gate("middle", State.IN);
		testGate.getIn(1000);
		assertEquals(testGate.toString(), "Gate middle is open to get in.\n1000 persons came in through this gate\n");
		testGate.setTripod(State.OUT);
		testGate.getOut(1000);
		assertEquals(testGate.toString(), "Gate middle is open to get out.\n0 persons went out through this gate\n");
		testGate.getOut(10);
		
		assertEquals(testGate.toString(), "Gate middle is open to get out.\n10 persons went out through this gate\n");

	}

}
