package Homework5;

enum State {IN, OUT, LOCKED}

public class Gate { 
	private State tripod;
	// If negative, then it means the number of persons went outside through this gate
	private int personGoneInside = 0; 
	private String name;
	
	// Gate Constructor
	public Gate(String name) {  this(name, State.LOCKED); }
	
	public Gate(String name, State state) {
		this.name = name;
		this.tripod = state;
	}

	public int numPersonGoneInside() { return personGoneInside; }
	
	// To set the tripod direction
	public void setTripod(State dir) { tripod = dir; }

	// return true if and only if the gate is currently locked and will be unlocked after the call
	public boolean open(State dir) {
		boolean ret = tripod == State.LOCKED && dir != State.LOCKED; 
		setTripod(dir); 
		return ret;
	}

	// to close the gate
	public void close() { tripod = State.LOCKED; }

	// to check if the gate is locked or not
	public boolean isLocked() { return tripod == State.LOCKED; }

	// to return the tripod direction
	public State tripodState() { return tripod ; }

	public void getIn(int x) {
		if (tripod == State.IN) {
			personGoneInside += x;
		} 
	}
	public void getOut(int x) {
		if (tripod == State.OUT) {
			personGoneInside -= x;
		} 
	}

	// override of the toString method
	public String toString() {
		String state = "";
		switch(tripod) {
		case LOCKED: state = "locked"; break;
		case IN: state = "open to get in"; break;
		case OUT: state = "open to get out"; break;
		}
		String out;
		if(personGoneInside > 0) {
			out = "came in through this gate";
		}
		else {
			out = "went out through this gate";
		}
		return String.format("Gate %s is %s.\n%d persons %s\n", name, state, Math.abs(personGoneInside), out);
	}

	@Override
	public boolean equals(Object that) {
		boolean ret;
		if(that instanceof Gate) {
			Gate thatGate = (Gate) that;
			ret = name.equals(thatGate.name) &&
					tripod == thatGate.tripod &&
					personGoneInside == thatGate.personGoneInside;
		}
		else {
			ret = false;
		}
		return ret;
	}
}
