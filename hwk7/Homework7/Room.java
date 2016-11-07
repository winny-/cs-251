// Name:       Winston Weinert
// Class:      COMPSCI 251
// Assignment: Homework7
// Date:       31-Oct-2016
//
// -- Comments --
//
// I changed the package name to Homework7.
//
// All unit tests pass.

package Homework7;

public class Room { 
    Window window;
    int numOfWindows;
	
    Room(Window window, int numOfWindows) { 
        this.window = window;
        this.numOfWindows = numOfWindows;
    }
	 
    WindowOrder order() {
        return new WindowOrder(window, numOfWindows);
    }
    @Override
    public String toString() {
        return String.format("%d (%s)", this.numOfWindows, this.window);
    }
    
    @Override
    public boolean equals(Object that){
        if (this == that) {
            return true;
        }
        if (that instanceof Room) {
            Room r = (Room)that;
            return this.window.equals(r.window) &&
                this.numOfWindows == r.numOfWindows;
        }
        return false;
    }
}

class MasterBedroom extends Room {
    MasterBedroom() {
        super(new Window(4, 6), 3);
    }
    @Override
    public String toString() {
        return String.format("Master bedroom: %s", super.toString());
    }
}

class GuestRoom extends Room {
    GuestRoom() {
        super(new Window(5, 6), 2);
    }
    @Override
    public String toString() {
        return String.format("Guest room: %s", super.toString());
    }
}

class LivingRoom extends Room {
    LivingRoom() {
        super(new Window(6, 8), 5);
    }
    @Override
    public String toString() {
        return String.format("Living room: %s", super.toString());
    }
}
