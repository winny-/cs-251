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

public class Homework6 {
    public static void main(String[] args) {
        Apartment[] apartments = { new OneBedroom(20),
                                   new TwoBedroom(15),
                                   new ThreeBedroom(10) };
		
        Building building = new Building(apartments);
		
        WindowOrder[] orders = building.order();
		
        System.out.println(building);
		
        System.out.println("Window orders are: ");
        for(WindowOrder order : orders) {
            System.out.println(order);
        }
    }
}
