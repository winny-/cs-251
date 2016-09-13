// Name:       Winston Weinert
// Class:      COMPSCI 251
// Assignment: Lab1
// Date:       12-Sept-2016
//
// -- Comments --
//
// I wasn't certain there is a way to use
// PrintStream.format() to print out the default precision that
// Double.toString() utilizes: %f limits to only a handful of decimal points,
// so I'm just using %s in conjuction with Double.toString().

package Lab1;

import java.util.Scanner;

public class Lab1 {
    public static double compute(int numOfPeople) {
        double acc = 1;
        for (int i = 0; i < numOfPeople; i++) {
            acc *= (365 - i) / 365.0;
        }
        return 1 - acc;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the number of people: ");
        int n = scanner.nextInt();
        double m = compute(n);
        System.out.format("Probablitiy of birthday collision among %d " +
                          "people is %s\n",
                          n,
                          Double.toString(m));
    }
}
