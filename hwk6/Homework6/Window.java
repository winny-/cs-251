// Name:       Winston Weinert
// Class:      COMPSCI 251
// Assignment: Homework5
// Date:       22-Oct-2016
//
// -- Comments --
//
// I changed the package name to Homework6.
//
// All unit tests pass.

package Homework6;

public class Window {
    private final int width, height;
	
    public Window(int width, int height) {
        this.width = width;
        this.height = height;
    }
	
    // print text like: 4 X 6 window
    public String toString() {
        return String.format("%d X %d window", this.width, this.height);
    }
	
    // compare window objects by their dimensions
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Window) {
            Window w = (Window)that;
            return this.width == w.width &&
                this.height == w.height;
        }
        return false;
    }
}

class WindowOrder {
    final Window window; // window description (its width and height)
    int num;             // number of windows for this order
	
    WindowOrder(Window window, int num) {
        this.window = window;
        this.num = num;
    }

    // add the num field of the parameter to the num field of this object
    //
    // BUT
    //
    //   do the merging only of two windows have the same size
    //   do nothing if the size does not match
    // 
    // return the current object
    WindowOrder add(WindowOrder order) {
        if (this.window.equals(order.window)) {
            this.num += order.num;
        }
        return this;
    }

    // update the num field of this object by multiplying it with the parameter
    // and then return the current object
    WindowOrder times(int number) {
        this.num *= number;
        return this;
    }
	
    // print text like: 20 4 X 6 window
    @Override
    public String toString() {
        return String.format("%d %s", this.num, this.window);
    }

    // Two orders are equal if they contain the same number of windows of the same size.
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof WindowOrder) {
            WindowOrder wo = (WindowOrder)that;
            return this.window.equals(wo.window) &&
                this.num == wo.num;
        }
        return false;
    }
}
