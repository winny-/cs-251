package Lab3;

import java.io.PrintStream;
import java.util.Scanner;
 

public class Lab3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        BasedNumber b1 = promptForNumber(in), b2 = promptForNumber(in);

        System.out.printf("These numbers represent %s!\n", b1.equals(b2)? "the same value" : "the different values");

        in.close();
    }
	
    private static BasedNumber promptForNumber(Scanner in) {
        PrintStream out = System.out;
		
        out.print("Enter a base: ");
		
        int base = Integer.parseInt(in.nextLine());
		
        out.printf("Enter base-%d digits (separated by space): ", base);
		
        String[] digitStrings = in.nextLine().split(" ");
		
        return new BasedNumber(base, parse(digitStrings));
    }

    private static int[] parse(String[] digitStrings) {
        int[] ary = new int[digitStrings.length];
        for (int i = 0; i < digitStrings.length; i++) {
            ary[i] = Integer.parseInt(digitStrings[i]);
        }
        return ary;
    }
}

class BasedNumber {
    private int base;
    private int[] digits;

    public BasedNumber(int base, int[] digits) {
        this.base = base;
        this.digits = digits;
    }

    public int getBase() {
        return this.base;
    }

    public int getDigit(int n) {
        return this.digits[n];
    }

    public int getValue() {
        int n = 0;
        for (int i = 0; i < digits.length; i++) {
            n += this.digits[this.digits.length - i - 1] *
                Math.pow(this.base, i);
        }
        return n;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BasedNumber)) {
            return false;
        }
        BasedNumber otherBN = (BasedNumber)other;
        return this.getValue() == otherBN.getValue();
    }
}
