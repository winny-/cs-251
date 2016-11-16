package Homework8;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Template {
    private final String template;

    Template(String template) {
        this.template = template;
    }

    public static Template fromInputStream(InputStream is) {
        try (Scanner scanner = new Scanner(is)) {
            return new Template(scanner.nextLine());
        }
    }

    public String fill(Dictionary dictionary) {
        Scanner scanner = new Scanner(this.template);
        StringBuilder sb = new StringBuilder();
        boolean wasSlash = false;
        for (;;) {
            String token;
            try {
                token = scanner.next();
            } catch (NoSuchElementException e) {
                break;
            }
            if (token.equals("/")) {
                wasSlash = true;
            } else if (!wasSlash) {
                sb.append(token)
                    .append(" ");
            } else {
                try {
                    sb.append(dictionary.getWord(token))
                        .append(" ");
                } catch (UnsupportedCategoryException|EmptyWordListException e) {
                    String format = "[Error]: Unsupported category: '%s'";
                    if (e instanceof EmptyWordListException) {
                        format = "[Error]: No remaining words of category: '%s'";
                    }
                    System.out.println(String.format(format, token));
                    sb.append("/ ")
                        .append(token)
                        .append(" ");
                }
                wasSlash = false;
            }
        }
        scanner.close();
        return sb.toString();
    }
}
