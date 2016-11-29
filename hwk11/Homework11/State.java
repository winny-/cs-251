package Homework11;

public enum State {
    EMPTY,
    X,
    O;

    public boolean isPlayer() {
        return this != State.EMPTY;
    }

    public String toString() {
        return this == State.EMPTY ? "" :
            this == State.X ? "X" :
            "O";
    }
}
