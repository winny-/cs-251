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

import java.util.ArrayList;
import java.util.List;

// This class represents a collection of window orders using an ArrayList
public class TotalOrder {
    List<WindowOrder> orders = new ArrayList<>();
	
    // Add a new window order to this list
    // Make sure to merge the orders for window of the same size
    // Return the current object
    TotalOrder add(WindowOrder newOrder) {
        for (WindowOrder order : this.orders) {
            if (order.window.equals(newOrder.window)) {
                order.add(newOrder);
                return this;
            }
        }
        this.orders.add(newOrder);
        return this;
    }
	
    // Add another collection of window order
    // Also make sure that the orders for windows of the same size are merged
    // Return the current object
    TotalOrder add(TotalOrder that) {
        for (WindowOrder order : that.orders) {
            this.add(order);
        }
        return this;
    }
	
    // Multiple all window orders by "num"
    TotalOrder times(int num) {
        for (WindowOrder order : this.orders) {
            order.times(num);
        }
        return this;
    }
	
    @Override
    public String toString() {
        return "not implemented";
    }
}
