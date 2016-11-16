package Homework8;

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
