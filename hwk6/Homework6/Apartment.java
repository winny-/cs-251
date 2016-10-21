package Homework6;

// This class contains the configuration of a type of apartment 
public class Apartment {
    int numOfUnits; // the number of apartments of this type
    Room[] rooms; // rooms in this type of apartment
	
    Apartment(int numOfUnits, Room[] rooms) {
        this.numOfUnits = numOfUnits;
        this.rooms = rooms;
    }
	
    // return an array of window orders for one unit of this type of apartment
    WindowOrder[] orderForOneUnit() {
        WindowOrder[] order = new WindowOrder[this.rooms.length];
        for (int i = 0; i < this.rooms.length; i++) {
            order[i] = this.rooms[i].order();
        }
        return order;
    }
	
    // return an array of window orders for all units of this type of apartment
    WindowOrder[] totalOrder() {
        WindowOrder[] order = this.orderForOneUnit();
        for (WindowOrder i : order) {
            i.times(numOfUnits);
        }
        return order;
    }
	
    // return text like:
    //
    // 15 apartments with (Living room: 5 (6 X 8 window)) (Master bedroom: 3 (4 X 6 window)) (Guest room: 2 (5 X 6 window))
    public String toString() {
        String[] parts = new String[this.rooms.length];
        for (int i = 0; i < parts.length; i++) {
            parts[i] = this.rooms[i].toString();
        }
        return String.format("%d apartments with (%s)",
                             this.numOfUnits,
                             String.join(")(", parts));
    }
}

class OneBedroom extends Apartment {
    OneBedroom(int numOfUnits) {
        super(numOfUnits, new Room[] { new LivingRoom(),
                                       new MasterBedroom() });
    }
}

class TwoBedroom extends Apartment {
    TwoBedroom(int numOfUnits) {
        super(numOfUnits, new Room[] { new LivingRoom(),
                                       new MasterBedroom(),
                                       new GuestRoom() });
    }
}

class ThreeBedroom extends Apartment {
    ThreeBedroom(int numOfUnits) {
        super(numOfUnits, new Room[] { new LivingRoom(),
                                       new MasterBedroom(),
                                       new GuestRoom(),
                                       new GuestRoom() });
    }
	
    // return an array of window orders for all units of this type of apartment
    //
    // Notice we have two guest rooms and they have the same size of windows.
    // override the inherited method to merge the order for the two guest rooms since their windows have the same size
    @Override
    WindowOrder[] orderForOneUnit() {
        Room[] r = this.rooms;
        return new WindowOrder[] {
            r[0].order(),
            r[1].order(),
            r[2].order().add(r[3].order())
        };
    }
}
