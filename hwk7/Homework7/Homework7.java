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

public class Homework7 {
    public static void main(String[] args) {
        Apartment[] apartments = { new OneBedroom(20), new TwoBedroom(15), new ThreeBedroom(10) };
		
        Building building = new Building(apartments);
		
        TotalOrder orders = building.order();
		
        System.out.println(building);
		
        System.out.println("Window orders are:\n" + orders); 
    }
}
