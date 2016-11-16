// Name:       Winston Weinert
// Class:      COMPSCI 251
// Assignment: Homework5
// Date:       07-Nov-2016
//
// -- Comments --
//
// The error handling messages might be wrong, but otherwise appears to work.

package Homework8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import static java.lang.System.out;

class Homework8Exception extends RuntimeException {
    Homework8Exception(String message) {
        super(message);
    }
}

class DictionaryFormatException extends Homework8Exception {
    DictionaryFormatException(String message) {
        super(message);
    }
}

class EmptyWordListException extends Homework8Exception {
    EmptyWordListException(String message) {
        super(message);
    }
}

class UnsupportedCategoryException extends Homework8Exception {
    UnsupportedCategoryException(String message) {
        super(message);
    }
}

class Dictionary {
    private Random rand = new Random();
    private final List<String> nouns = new ArrayList<>();
    private final List<String> verbs = new ArrayList<>();
    private final List<String> adjectives = new ArrayList<>();
    private final List<String> adverbs = new ArrayList<>();
    private final List<String> pronouns = new ArrayList<>();
    private final List<String> interjections = new ArrayList<>();

    public Dictionary() {
    }

    public static Dictionary fromInputStream(InputStream is) {
        Dictionary dict = new Dictionary();
        String line;
        try (Scanner scanner = new Scanner(is)) {
            for (;;) {
                line = scanner.nextLine();
                dict.addWord(line);
            }
        } catch (NoSuchElementException e) {
            return dict;
        }/* catch (UnsupportedCategoryException e) {
            System.out.printf("[Error]: Unsupported category: '%s'.%n", line);
            }*/
    }
    
    public void addWord(String line) {
        String[] parts = line.split(":+");
        if (parts.length != 2) {
            throw new DictionaryFormatException(String.format("Too few/many colons in \"%s\"", line));
        }
        String category = parts[0].trim();
        String word = parts[1].trim();
        if (category.equals("")) {
            throw new DictionaryFormatException(String.format("No category provided in \"%s\"", line));
        }
        if (word.equals("")) {
            throw new DictionaryFormatException(String.format("No word provided in \"%s\"", line));
        }
        switch (category) {
        case "noun":
            this.nouns.add(word);
            break;
        case "verb":
            this.verbs.add(word);
            break;
        case "adjective":
            this.adjectives.add(word);
            break;
        case "adverb":
            this.adverbs.add(word);
            break;
        case "pronoun":
            this.pronouns.add(word);
            break;
        case "interjection":
            this.interjections.add(word);
            break;
        default:
            throw new UnsupportedCategoryException(String.format("Unsupported category \"%s\" in \"%s\"", category, line));
        }
    }

    public String getWord(String partOfSpeech) throws EmptyWordListException, UnsupportedCategoryException {
        String category = partOfSpeech.trim();
        switch (category) {
        case "noun":
            if (this.nouns.size() == 0) {
                throw new EmptyWordListException("Nouns wordlist is empty.");
            }
            return this.getRandom(this.nouns);
        case "verb":
            if (this.verbs.size() == 0) {
                throw new EmptyWordListException("Verbs wordlist is empty.");
            }
            return this.getRandom(this.verbs);
        case "adjective":
            if (this.adjectives.size() == 0) {
                throw new EmptyWordListException("Adjectives wordlist is empty.");
            }
            return this.getRandom(this.adjectives);
        case "adverb":
            if (this.adverbs.size() == 0) {
                throw new EmptyWordListException("Adverbs wordlist is empty.");
            }
            return this.getRandom(this.adverbs);
        case "pronoun":
            if (this.pronouns.size() == 0) {
                throw new EmptyWordListException("Pronouns wordlist is empty.");
            }
            return this.getRandom(this.pronouns);
        case "interjection":
            if (this.interjections.size() == 0) {
                throw new EmptyWordListException("Interjections wordlist is empty.");
            }
            return this.getRandom(this.interjections);
        default:
            throw new UnsupportedCategoryException(String.format("Unsupported category \"%s\"", category));
        }
    }

    private String getRandom(List<String> ls) {
        return ls.remove(this.rand.nextInt(ls.size()));
    }
}

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

class Driver {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        out.print("Enter template filename: ");
        Template t = loadTemplate(scanner.nextLine());
        out.print("Enter dictionary filename: ");
        Dictionary d = loadDictionary(scanner.nextLine());
        out.print("Enter output filename: ");
        try (OutputStream os = new FileOutputStream(new File(scanner.nextLine()))) {
            os.write(t.fill(d).getBytes());
        } catch (Homework8Exception e) {
            out.println("lmao");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static Template loadTemplate(String filename) {
        try (InputStream is = new FileInputStream(new File(filename))) {
            return Template.fromInputStream(is);
        } catch (Homework8Exception e) {
            // do nothing
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new Template("");
    }

    private static Dictionary loadDictionary(String filename) {
        try (InputStream is = new FileInputStream(new File(filename))) {
            return Dictionary.fromInputStream(is);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new Dictionary();
    }
}
