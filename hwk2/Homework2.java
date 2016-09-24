package Homework2;

import java.util.Scanner;

class Homework2 {

    public static String format(String s) {
        String[] parts = s.split("[{}]");

        assert parts.length == 2 || parts.length == 4;
        
        // Trim the parts.
        for (int index = 0; index < parts.length; index++) {
            parts[index] = parts[index].trim();
        }
        
        String ifExp = parts[0].replace("if(", "if (");
        String res = ifExp + " {\n" +
            "\t" + parts[1] + "\n" +
            "}";
        if (parts.length == 4) {
            res += "\nelse {\n" +
                "\t" + parts[3] + "\n}";
        }
        
        return res;
    }

    public static void main(String[] args) {
        System.out.print("Input a Java snippet as ONE line: ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println("------------------------");
        System.out.println("The formatted output is:");
        System.out.println("------------------------");
        System.out.println(format(s));
    }
}
