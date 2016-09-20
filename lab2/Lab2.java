package Lab2;

import java.util.Random;
import java.util.Scanner;

public class Lab2 {
    private static Random rand = new Random();
    
    public static int countWords(String s) {
        String[] parts = s.split("\\s+");
        if (parts.length == 1 && parts[0].equals("")) {
            return 0;
        }
        return parts.length;
    }
    
    public static String spoonerizeAt(String s, int pos) {
        String[] parts = s.split(" ");
        String tmp = parts[pos].substring(0, 1);
        parts[pos] = parts[pos+1].substring(0, 1) + parts[pos].substring(1);
        parts[pos+1] = tmp + parts[pos+1].substring(1);
        return String.join(" ", parts);
    }

    public static String spoonerize(String s) {
        int n = rand.nextInt(s.split(" ").length - 1);
        return spoonerizeAt(s, n);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            System.out.print("Enter a sentence to muck up: ");
            String s = scanner.nextLine();
            System.out.print("Spoonerized! = ");
            System.out.println(spoonerize(s));
        }
    }
}
