package Lab8;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import java.util.Arrays;
import java.util.Scanner;

public class Lab8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); 
        while(true) {
            // prompt user to supply a base
            //   parse the base as an integer
            //
            // prompt user to supply a list of digits separated by space
            //   parse the input string (for digits) to an array of digits
            //
            // create a based number object of the base and the digit array and print its value
            //
            // catch exceptions as required (number format, illegal base, illegal digits)
            try {
                System.out.print("Input a base: ");
                int base = in.nextInt();
                in.nextLine(); // Ignore rest of the line.
                
                System.out.print("Input spaced-delimited digits: ");
                String line = in.nextLine();
                String[] parts = line.split("[^0-9-]+");
                int[] digits = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    digits[i] = Integer.parseInt(parts[i]);
                }
                
                BasedNumber bn = new BasedNumber(base, digits);
                System.out.println(String.format("Your number is %d in decimal (base 10).", bn.getValue()));
                
                System.out.print("Parse another number? (y/n): ");
                String yn = in.nextLine();
                if (yn.toLowerCase().startsWith("n")) {
                    break;
                }
            } catch (InputMismatchException e) {
                in.nextLine();
                //                System.out.println("Unexpected input, please try again");
                System.out.println(e.getMessage());
                continue;
            } catch (NoSuchElementException e) {
                break;
            } catch (IllegalBaseException|IllegalDigitException e) {
                System.out.println(String.format("Base/digit mismatch: %s",
                                                 e.getMessage()));
            } catch (Exception e) {
                System.out.println(String.format("Miscellaneous error: %s", e));
            }
        }
        System.out.println("Exiting...");
        in.close();
    }
}

// define illegal base exception class
//
// define illegal digit exception class

class IllegalBaseException extends IllegalArgumentException {
    IllegalBaseException(String message) {
        super(message);
    }
}

class IllegalDigitException extends IllegalArgumentException {
    IllegalDigitException(String message) {
        super(message);
    }
}

class BasedNumber {
    private int base;
    private int[] digits;

    BasedNumber(int base, int[] digits) {
        // check base and throw exception if it is not great than 1
        //                                if the digit array is empty
        //                                if the digits is not within legal range (specified by the base)
        if (base < 2) {
            throw new IllegalBaseException(String.format("Invalid base %d (must be > 1)", base));
        }
        if (digits.length == 0) {
            throw new IllegalDigitException("No digits specified");
        }
        for (int i = 0; i < digits.length; i++) {
            int d = digits[i];
            if (d < 0) {
                throw new IllegalDigitException(String.format("Digit %d at digits[%d] is < 0",
                                                               d, i));
            }
            if (d >= base) {
                throw new IllegalDigitException(String.format("Digit %d at digits[%d] is >= base (%d)",
                                                              d, i, base));
            }
        }
        this.base = base;
        this.digits = digits;

    }

    int getBase() {
        return base;
    }
    int getDigit(int n) {
        return digits[n];
    }

    int getValue() {
        int ret = 0;

        for(int i=0; i<digits.length; i++) {
            ret = ret * base + digits[i];
        }
        return ret;
    }

    boolean equals(BasedNumber that) {
        return getValue() == that.getValue();
    }
}
