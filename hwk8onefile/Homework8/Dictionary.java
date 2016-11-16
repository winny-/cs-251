package Homework8;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

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
