// Name:       Winston Weinert
// Class:      COMPSCI 251
// Assignment: Homework5
// Date:       16-Oct-2016
//
// -- Comments --
//
// I changed the package name to Homework5.
//
// All unit tests pass. I don't really understand the requirements,
// so this is my best guess of what was asked for.

package Homework5;

public class GymController {
    final double toleranceIncrement = 0.01;

    double outTolerance = toleranceIncrement, inTolerance = toleranceIncrement;

    final double occupancy; // target ratio of occupancy
    final Gym gym; // gym object under control

    // Save occupancy ratio and gym object
    // Make sure the occupancy parameter between 0 and 1; if not, set it to 0.5
    // Check tolerances to ensure they are legal with respect to the target occupancy ratio
    GymController(double occupancy, Gym gym) {
        if (occupancy >= 1 || occupancy <= 0) {
            occupancy = 0.5;
        }
        this.occupancy = occupancy;
        this.gym = gym;
        this.checkTolerance();
    }

    // Make sure tolerances are legal and
    // change tolerances if necessary so that occupancy + outTolerance <= 1 and 
    //                                        occupancy - inTolerance >= 0
    private void checkTolerance() {
        //        this.inTolerance = this.clamp(this.inTolerance);
        //        this.outTolerance = this.clamp(this.outTolerance);
        this.outTolerance = Math.max(Math.min(this.outTolerance,
                                              1 - this.occupancy),
                                     this.toleranceIncrement);
        this.inTolerance = Math.max(Math.min(this.inTolerance,
                                             this.occupancy),
                                    this.toleranceIncrement);
    }

    // Increment out tolerance and 
    // decrement in tolerance (if it is > tolerance increment)
    // and also ensure they are legal
    private void adjustToleranceForOut() {
        this.outTolerance += this.toleranceIncrement;
        // Not necessary to check if inTolerance > toleranceIncrement
        // because checkTolerance() checks the lower bound.
        this.inTolerance -= this.toleranceIncrement;

        this.checkTolerance();
    }

    // Increment in tolerance and 
    // decrement out tolerance (if it is > tolerance increment) 
    // and also ensure they are legal
    private void adjustToleranceForIn() {
        this.inTolerance += this.toleranceIncrement;
        // Not necessary to check if outTolerance > toleranceIncrement
        // because checkTolerance() checks the lower bound.
        this.outTolerance -= this.toleranceIncrement;

        this.checkTolerance();
    }

    // This is called by the personsInside method of the gym object  
    // If the current occupancy ratio is great than the target occupancy + out tolerance 
    //  and there are more than one gate that are not in OUT state
    // then switch one gate to OUT state
    //
    // If the current occupancy ratio is less than the target occupancy - in tolerance
    //  and there are more than one gate that are not in IN state
    // then switch one gate to IN state
    //	
    // When a gate is switched to IN or OUT, adjust corresponding tolerance
    public void onChange(int personsInside) {
        double currentOccupancy = personsInside / (double)this.gym.capacity;

        int numOfOUT = this.gym.numberOfGatesInState(State.OUT);
        int numOfIN = this.gym.numberOfGatesInState(State.IN);

        // This passes the unit tests... I don't really understand why this works however.
        // Clearer requirements would be fantastic, thanks. (And unit tests that achieve
        // complete coverage of the behaviours would be really helpful.)
        if (this.occupancy + this.outTolerance < currentOccupancy && numOfIN > 1) {
            this.gym.switchOneGate(State.OUT);
            this.adjustToleranceForOut();
        } else if (this.occupancy - this.inTolerance > currentOccupancy && numOfOUT > 1) {
            this.gym.switchOneGate(State.IN);
            this.adjustToleranceForIn();
        }
    }
}
