// Name:       Winston Weinert
// Class:      COMPSCI 251
// Assignment: Homework4
// Date:       7-Oct-2016
//
// -- Comments --
//
// I found the template very contrived; however, all test pass, so I think this is what was expected of me.
// Added a handrolled equals() for Gym to work.

package Homework4;

enum State {IN, OUT, LOCKED}

public class Gate { 
	private State tripod;
	// If negative, then it means the number of persons gone outside through this gate
	private int personGoneInside = 0; 
	private String name;
	
	// Gate Constructor
	public Gate(String name) {
            this(name, State.LOCKED); // Call other constructor.
	}
	
	public Gate(String name, State state) {
            this.name = name;
            this.tripod = state;
	}

	public int numPersonGoneInside() {
            return personGoneInside;
	}
	
	// To set the tripod state
	public void setTripod(State dir) {
            this.tripod = dir;
	}

	// return true if and only if the gate is currently locked
	public boolean open(State dir) {
            boolean opening = this.isLocked() && dir != State.LOCKED;
            this.tripod = dir;
            return opening;
	}

	// to close the gate
	public void close() {
            this.tripod = State.LOCKED;
	}

	// to check if the gate is locked or not
	public boolean isLocked() {
            return this.tripod == State.LOCKED;
	}

	// to return the tripod state
	public State tripodState() {
            return this.tripod;
	}

	// x number of person wants to get in through this gate
	public void getIn(int x) {
            if (this.tripodState() == State.IN) {
                this.personGoneInside += x;
            }
	}

	// x number ofabs person wants to get out through this gate
	public void getOut(int x) {
            if (this.tripodState() == State.OUT) {
                this.personGoneInside -= x;
            }
	}

	@Override
	public String toString() {
            String stateFragment = "";
            switch (this.tripodState()) {
            case LOCKED:
                stateFragment = "locked";
                break;
            case IN:
                stateFragment = "open to get in";
                break;
            case OUT:
                stateFragment = "open to get out";
                break;
            }
            String verbFragment = "went out";
            if (this.tripodState() == State.IN && this.numPersonGoneInside() != 0) {
                verbFragment = "came in";
            }
            int numPersonAbsolute = Math.abs(this.numPersonGoneInside());
            return String.format("Gate %s is %s.\n%d persons %s through this gate\n",
                                 this.name,
                                 stateFragment,
                                 numPersonAbsolute,
                                 verbFragment);

        }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Gate) {
            Gate otherGate = (Gate)other;
            return this.tripod == otherGate.tripod &&
                this.personGoneInside == otherGate.personGoneInside &&
                this.name.equals(otherGate.name);
        }
        return false;
    }
}
