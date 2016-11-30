package Homework11;

public interface GridListener {
    public void gameFinished(Grid grid, State winner);
    public State gameStarted(Grid grid);
}
