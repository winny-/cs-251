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
 
public class Building {
    Apartment[] apartments;
	
    public Building(Apartment[] units) {
        this.apartments = units;
    }
	
    // Return the total order all apartments in this building
    TotalOrder order() {
        TotalOrder order = new TotalOrder();
        for (Apartment apt : this.apartments) {
            order.add(apt.totalOrder());
        }
        return order;
    }
	
    public String toString() {
        String strings[] = new String[this.apartments.length+1];
        for (int i = 0; i < this.apartments.length; i++) {
            strings[i] = this.apartments[i].toString();
        }
        strings[this.apartments.length] = "";
        return String.join("\n", strings);
    }
}
