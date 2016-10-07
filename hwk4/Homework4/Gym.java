// Name:       Winston Weinert
// Class:      COMPSCI 251
// Assignment: Homework4
// Date:       7-Oct-2016
//
// -- Comments --
//
// I changed the package name to Homework4, and therefore Gate is imported from Homework4.
// I'm using String.join() so this should work on Java 8 or later, only.
// My TA said this is fine to target Java 8, so please let me know if that isn't the case.
//
// All unit tests pass.

package Homework4;

import Homework4.Gate;

public class Gym {
    final Gate[] gates; // array of gates
    final int capacity; // capacity of the gym
	
    // initialize with number of gates set to 3 and capacity set to 1000
    public Gym() {
        this(3);
    }
    // initialize with specified number of gates and capacity set to 1000
    public Gym(int numOfGates) {
        this(numOfGates, 1000);
    }
    // initialize with specified number of gates and with specified capacity
    public Gym(int numOfGates, int capacity) {
        assert(numOfGates > 1);
        assert(capacity > 0);
        this.gates = new Gate[numOfGates];
        for (int idx = 0; idx < this.gates.length; idx++) {
            this.gates[idx] = new Gate("gate_" + Integer.toString(idx));
        }
        this.capacity = capacity;
    }
	
    // lock all gates
    public void lockDown() {
        for (Gate g : this.gates) {
            g.close();
        }
    }
	
    // open (roughly) half of the gates for in and the other half for out
    public void open() {
        this.open(this.gates.length / 2);
    }

    // open specific number of gate for in and leave the rest for out
    // if numOfInGate >= gates.length, then open (gates.length-1) gates for in and 1 for out
    // if numOfInGate <= 0, then open 1 gate for in and rest for out
    public void open(int numOfInGate) {
        this.lockDown();
        if (numOfInGate >= this.gates.length) {
            numOfInGate = this.gates.length - 1;
        } else if (numOfInGate < 1) {
            numOfInGate = 1;
        }
        for (int idx = 0; idx < this.gates.length; idx++) {
            State state = (idx+1 > numOfInGate) ? State.OUT : State.IN;
            this.gates[idx].open(state);
        }
    }
	
    // enters 1 person at gates[gateNumber] if the persons inside do not exceed capacity 
    public void getIn(int gateNumber) {
        if (!this.validGateNumber(gateNumber)) {
            return;
        }
        if (this.canGoIn()) {
            this.gates[gateNumber].getIn(1);
        }
    }
	
    // exits 1 person at gates[gateNumber] if there is at least 1 person inside
    public void getOut(int gateNumber) {
        if (!this.validGateNumber(gateNumber)) {
            return;
        }
        Gate gate = this.gates[gateNumber];
        if (this.canGoOut()) {
            gate.getOut(1);
        }
    }
    // returns true if and only if capacity is not reached
    public boolean canGoIn() {
        return this.personsInside() < this.capacity;
    }
    // returns true if and only if it is not empty
    public boolean canGoOut() {
        return this.personsInside() > 0;
    }
    // return the number of persons inside the gym
    public int personsInside() {
        int n = 0;
        for (Gate g : this.gates) {
            n += g.numPersonGoneInside();
        }
        return n;
    }

    private boolean validGateNumber(int gateNumber) {
        return gateNumber >= 0 && gateNumber < this.gates.length;
    }

    // print each gate of the gym
    @Override
    public String toString() {
        // Initialize an array of strings to String.join()
        // The +1 is for the extra newline.
        String[] gateStrings = new String[this.gates.length+1];
        for (int idx = 0; idx < this.gates.length; idx++) {
            gateStrings[idx] = this.gates[idx].toString();
        }
        // Set the last string to "" so String.join() adds another newline at the end.
        gateStrings[this.gates.length] = ""; // Hack
        return String.join("\n", gateStrings);
    }
}
