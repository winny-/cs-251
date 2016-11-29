package Homework11;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import Homework11.Cell;
import Homework11.State;
import Homework11.GameStatsPanel;

public class Grid extends JPanel {
    //////////////////////////////////////////////////
    // Private Fields
    //////////////////////////////////////////////////
    
    private final static int[][] winConditions = {
        // Across
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
        // Down
        {0, 3, 6},
        {1, 4, 7},
        {2, 5, 8},
        // Diagonal
        {0, 4, 8},
        {6, 4, 2}
    };
    private final static int NUMCELLS = 9;
    private final Cell cells[] = new Cell[Grid.NUMCELLS];
    private int[] winConditionCache;
    private final GameStatsPanel gameStatsPanel;
    private final ActionListener listener = new ActionListener() {
        Grid self = Grid.this;
        public void actionPerformed(ActionEvent evt) {
            if (self.turn == State.EMPTY) {
                self.turn = self.gameControllerDelegate.playedFirstMove();
            }
            Cell source = (Cell)evt.getSource();
            int i;
            for (i = 0; i < Grid.NUMCELLS && self.cells[i] != source; i++)
                ;
            if (!self.canPlay(i)) {
                throw new IllegalStateException("Was unable to play cell " + i);
            }
            self.cells[i].setState(self.turn);
            self.turn = self.turn == State.X ? State.O : State.X;
            if (self.isGameFinished()) {
                for (Cell c : self.cells) {
                    c.setEnabled(false);
                }
                State winner = State.EMPTY;
                if (!self.isTie()) {
                    int[] condition = self.findWin();
                    for (int cell : condition) {
                        self.cells[cell].setBackground(Color.BLUE);
                    }
                    winner = self.cells[condition[0]].getState();
                }
                if (self.gameControllerDelegate != null) {
                    self.gameControllerDelegate.won();
                }
                self.gameStatsPanel.updateStats(winner);
            }
        }
    };

    //////////////////////////////////////////////////
    // Properties
    //////////////////////////////////////////////////
    
    private State turn;
    public State getTurn() {
        return this.turn;
    }

    private GameControllerPanel gameControllerDelegate;
    public void setGameControllerDelegate(GameControllerPanel delegate) {
        this.gameControllerDelegate = delegate;
    }

    //////////////////////////////////////////////////
    // Methods
    //////////////////////////////////////////////////

    private boolean isTie() {
        if (this.isWin()) {
            return false;
        }
        for (Cell c : this.cells) {
            if (c.getState() == State.EMPTY) {
                return false;
            }
        }
        return true;
    }

    private boolean isGameFinished() {
        return this.isTie() || this.isWin();
    }

    private boolean isWin() {
        return this.findWin() != null;
    }

    private int[] findWin() {
        if (this.winConditionCache != null) {
            return this.winConditionCache;
        }
        for (int[] condition : Grid.winConditions) {
            State a = this.cells[condition[0]].getState();
            if (a == State.EMPTY) {
                continue;
            }
            State b = this.cells[condition[1]].getState();
            State c = this.cells[condition[2]].getState();
            if (a == b && b == c) {
                this.winConditionCache = condition;
                return condition;
            }
        }
        return null;
    }

    private boolean canPlay(int cell) {
        return !this.isGameFinished() &&
            this.cells[cell].getState() == State.EMPTY;
    }
    
    public void newGame() {
        this.winConditionCache = null;
        this.turn = State.EMPTY;
        for (Cell c : this.cells) {
            c.setEnabled(true);
            c.setState(State.EMPTY);
            c.setBackground(Color.WHITE);
        }
    }

    //////////////////////////////////////////////////
    // Constructors
    //////////////////////////////////////////////////
    
    Grid(GameStatsPanel gameStatsPanel) {
        super();
        this.gameStatsPanel = gameStatsPanel;
        this.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < Grid.NUMCELLS; i++) {
            Cell c =  new Cell();
            c.addActionListener(this.listener);
            this.add(c);
            this.cells[i] = c;
        }
    }
}
