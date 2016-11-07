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

public class Window {
    private final int width, height;
	
    public Window(int width, int height) {
        this.width = width;
        this.height = height;
    }
	
    public String toString() {
        return String.format("%d X %d window", this.width, this.height);
    }
	
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
    final Window window;
    int num;
	
    WindowOrder(Window window, int num) {
        this.window = window;
        this.num = num;
    }
    WindowOrder add (WindowOrder order) {
        if (this.window.equals(order.window)) {
            this.num += order.num;
        }
        return this;
    }
    WindowOrder times(int number) {
        this.num *= number;
        return this;
    }
	
    public String toString() {
        return String.format("%d %s", this.num, this.window);
    }
	
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
