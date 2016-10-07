package Homework4;

import java.util.Random;
import Homework4.Gym;

public class Homework4 { 

    public static void main(String[] args) {
        int numOfGates = 5;
		
        Gym gym = new Gym(numOfGates);
        gym.open(3);
		
        int numOfSimulations = 5000;

        Random random = new Random();
        for(int i=0; i<numOfSimulations; i++) {
			
            int gateNumber = random.nextInt(numOfGates);
            int inOrOut = random.nextInt(2);
			
            if(inOrOut == 1) {
                gym.getIn(gateNumber);
            }
            else {
                gym.getOut(gateNumber);
            }
        }
        String output = String.format("After %d simulations: %d persons are in the gym\n", 
                                      numOfSimulations,
                                      gym.personsInside());
		
        System.out.printf("%s\n%s", output, gym);
    }

}
