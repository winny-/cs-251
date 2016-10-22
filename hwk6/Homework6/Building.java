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
//
// I provided ArrayList and array based implementations for order().

package Homework6;

import java.util.ArrayList;

public class Building {
    Apartment[] apartments;
	
    public Building(Apartment[] apartments) {
        this.apartments = apartments;
        assert(this.apartments.length > 0);
    }

    WindowOrder[] orderA() {
        ArrayList<WindowOrder> accumulator = new ArrayList<WindowOrder>();
        for (Apartment apt : this.apartments) {
            for (WindowOrder order : apt.totalOrder()) {
                boolean inAccumulator = false;
                for (WindowOrder accumulatedOrder : accumulator) {
                    if (accumulatedOrder.window.equals(order.window)) {
                        accumulatedOrder.add(order);
                        inAccumulator = true;
                        break;
                    }
                }
                if (!inAccumulator) {
                    accumulator.add(order);
                }
            }
        }
        return accumulator.toArray(new WindowOrder[accumulator.size()]);
    }
    
    // Return an array of window orders for all apartments in the building
    // Ensure that the orders for windows of the same sizes are merged.
    WindowOrder[] orderB() {
        WindowOrder[] accumulator = this.apartments[0].totalOrder();
        for (int i = 1; i < this.apartments.length; i++) {
            WindowOrder[] current = this.apartments[i].totalOrder();
            for (int j = 0; j < current.length; j++) {
                boolean inAccumulator = false;
                for (int k = 0; k < accumulator.length; k++) {
                    if (accumulator[k].window.equals(current[j].window)) {
                        accumulator[k].add(current[j]);
                        inAccumulator = true;
                        break;
                    }
                }
                if (!inAccumulator) {
                    WindowOrder[] newAccumulator = new WindowOrder[accumulator.length+1];
                    for (int k = 0; k < accumulator.length; k++) {
                        newAccumulator[k] = accumulator[k];
                    }
                    newAccumulator[accumulator.length] = current[j];
                    accumulator = newAccumulator;
                }
            }
        }
        return accumulator;
    }

    WindowOrder[] order() {
        return orderA();
    }
	
    // return a string to represent all types of apartments in the building such as:
    // 20 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))
    // 15 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))(Guest room: 2 (5 X 6 window))
    // 10 apartments with (Living room: 5 (6 X 8 window))(Master bedroom: 3 (4 X 6 window))(Guest room: 2 (5 X 6 window))(Guest room: 2 (5 X 6 window))
    // 
    public String toString() {
        String strings[] = new String[this.apartments.length+1];
        for (int i = 0; i < this.apartments.length; i++) {
            strings[i] = this.apartments[i].toString();
        }
        strings[this.apartments.length] = "";
        return String.join("\n", strings);
    }
}
