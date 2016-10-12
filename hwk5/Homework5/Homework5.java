package Homework5; 

import java.util.Random;


public class Homework5 { 

	public static void main(String[] args) {
		int numOfGates = 5;

		Gym gym = new Gym(numOfGates, 1000, 0.6);
		gym.open(2);

		int numOfSimulations = 10000;

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
			if (i > 0 && i % 1000 == 0) {
				String output = String.format("After %d simulations: %d persons are in the gym\n", 
						i,
						gym.personsInside());

				System.out.printf("%s\n%s\n", output, gym);
			}
		}
	}

}
