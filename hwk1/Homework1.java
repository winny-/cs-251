// Name:       Winston Weinert
// Class:      COMPSCI 251
// Assignment: Homework1
// Date:       12-Sept-2016
//
// -- Comments --
//
// Should meet the requirements. Also handles EOF & bad input (such as a
// non-numeric when reading a double) using try-catch.

package Homework1;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

class Homework1 {
    // Prompt and read for the birthday simulation's parameters.
    // Included as per the requirements.
    public static int[] promptAndRead(Scanner stdIn) {
        System.out.print("Please Enter the number of simulations to do: ");
        int numOfSimulations = stdIn.nextInt();
        System.out.print("Please Enter the size of the group of people: ");
        int numOfPeople = stdIn.nextInt();

        return new int[]{numOfSimulations, numOfPeople};
    }

    // Compute numOfSimulations with numOfPeople.
    // Returns the simulated probability two birthdays on the same day,
    // given all the simulations.
    // Included as per the requirements.
    public static double compute(int numOfSimulations, int numOfPeople) {
        // Use double to avoid explicit cast.
        double simulationsWithSameBirthdays = 0;

        for (int i = 0; i < numOfSimulations; i++) {
            if (simulate(numOfPeople, new Random(i+1))) {
                simulationsWithSameBirthdays++;
            }
        }

        return simulationsWithSameBirthdays / numOfSimulations;
    }

    // Simulate the birthday problem, given numOfPeople and rand.
    // Returns a boolean indicating whether the simulation had
    // AT LEAST two birthdays on the same day.
    public static boolean simulate(int numOfPeople, Random rand) {
        // Use a hash table to map birthdays to used/unused.
        // Our hash function is identity.
        boolean[] birthdays = new boolean[365];
        for (int i = 0; i < numOfPeople; i++) {
            int bday = rand.nextInt(365);
            if (birthdays[bday]) {
                return true;
            }
            birthdays[bday] = true;
        }
        return false;
    }

    // Main function
    public static void main(String[] args) {
        int[] ret;
        boolean lastInputSuccessful = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the birthday problem Simulator");
        for (;;) {
            System.out.println();

            if (lastInputSuccessful) {
                System.out.print("Do you want to run another set of" +
                                 " simulations? (y/n): ");
                String yn;
                try {
                    yn = scanner.next();
                } catch (NoSuchElementException e) { // EOF
                    System.out.println();
                    break;
                }
                if (yn.equalsIgnoreCase("n")) {
                    break;
                }
            }

            try {
                ret = promptAndRead(scanner);
                assert ret.length == 2;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Consume bad input.
                System.out.println("Unexpected input, please try again.");
                lastInputSuccessful = false;
                continue;
            } catch (NoSuchElementException e) { // EOF
                System.out.println();
                break;
            }

            int numOfSimulations = ret[0];
            int numOfPeople = ret[1];

            if (numOfSimulations < 1) {
                System.out.println("The number of simulations must be " +
                                   "postitive. Please try again.");
                lastInputSuccessful = false;
                continue;
            }
            if (numOfPeople < 2 || numOfPeople > 365) {
                System.out.println("The size of the group of people must " +
                                   "be within the range of [2,365] " +
                                   " inclusive. Please try again.");
                lastInputSuccessful = false;
                continue;
            }

            double simulatedProbability = compute(numOfSimulations,
                                                  numOfPeople);

            System.out.format("For a group of %d people, the probability" +
                              " that\n" + 
                              "two people have the same birthday is %f\n",
                              numOfPeople,
                              simulatedProbability);

            lastInputSuccessful = true;
        }
        System.out.println("Goodbye!");
    }
}
