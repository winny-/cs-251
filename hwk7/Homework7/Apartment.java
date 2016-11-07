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

public class Apartment {
    int numOfApartments; // the number of apartments of this type
    Room[] rooms; // rooms in this type of apartment
	
    Apartment(int numOfApartments, Room[] rooms) {
        this.numOfApartments = numOfApartments;
        this.rooms = rooms;
    }
	
    // Return the window orders for one apartment of this type as TotalOrder object
    TotalOrder orderForOneUnit() {
        TotalOrder order = new TotalOrder();
        for (Room room : this.rooms) {
            order.add(room.order());
        }
        return order;
    }
	
    // Return the window orders for all apartments of this type
    TotalOrder totalOrder() {
        return this.orderForOneUnit().times(this.numOfApartments);
    }
	
    public String toString() {
        String[] parts = new String[this.rooms.length];
        for (int i = 0; i < parts.length; i++) {
            parts[i] = this.rooms[i].toString();
        }
        return String.format("%d apartments with (%s)",
                             this.numOfApartments,
                             String.join(")(", parts));
    }
}

class OneBedroom extends Apartment {
    OneBedroom(int numOfUnits) {
        super(numOfUnits, new Room[] { new LivingRoom(), new MasterBedroom() });
    }
}

class TwoBedroom extends Apartment {
    TwoBedroom(int numOfUnits) {
        super(numOfUnits, new Room[] { new LivingRoom(), new MasterBedroom(), new GuestRoom() });
    }
}

class ThreeBedroom extends Apartment {
    ThreeBedroom(int numOfUnits) {
        super(numOfUnits, new Room[] { new LivingRoom(), new MasterBedroom(), new GuestRoom(), new GuestRoom() });
    }
}
