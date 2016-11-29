// Name:       Winston Weinert
// Class:      COMPSCI 251
// Assignment: Homework 11
// Date:       28-Nov-2016
//
// -- Comments --
//
// Not very thrilled about this one-file nonsense :)
//
// Sorry for the inconsistent use of design patterns, I
// didn't spend as much time on this assignment as I wanted to.

package Homework11;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Homework11 extends JFrame {
    Homework11() {
        super();
        this.setTitle("Tic Tac Toe");
        this.setPreferredSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        GameStatsPanel gsp = new GameStatsPanel();
        Grid grid = new Grid(gsp);
        GameControllerPanel gcp = new GameControllerPanel(grid);
        grid.setGameControllerDelegate(gcp);
        this.add(gcp, BorderLayout.NORTH);
        this.add(grid, BorderLayout.CENTER);
        this.add(gsp, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Homework11();
    }
}

enum State {
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

class Cell extends JButton {
    State state;
    private static ImageIcon xIcon;
    private static ImageIcon oIcon;

    public void setState(State state) {
        this.state = state;
        this.setEnabled(this.state == State.EMPTY);
        if (this.haveIcons()) {
            this.setText("");
            this.setIcon(this.state == State.EMPTY ? null :
                         this.state == State.X ? Cell.xIcon :
                         Cell.oIcon);
        } else {
            this.setIcon(null);
            this.setText(this.state.toString());
        }
    }

    private boolean haveIcons() {
        return Cell.xIcon != null && Cell.oIcon != null;
    }

    private void loadIcons() {
        try {
            Cell.xIcon = new ImageIcon(ImageIO.read(getClass().getResource("X.png")));
            Cell.oIcon = new ImageIcon(ImageIO.read(getClass().getResource("O.png")));
        } catch (Exception e) {
            System.err.println("Could not load images. Falling back on text.");
        }
    }
    
    public State getState() {
        return this.state;
    }

    Cell() {
        this(State.EMPTY);
    }
    
    Cell(State state) {
        super();
        this.loadIcons();
        this.setState(state);
    }
}

class GameControllerPanel extends JPanel {

    private final JRadioButton xStarts = new JRadioButton("X Starts");
    private final JRadioButton oStarts = new JRadioButton("O Starts");
    private final JButton newGame = new JButton("New Game");
    private final Grid grid;

    GameControllerPanel(Grid grid) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.newGame.setAlignmentX(JButton.CENTER_ALIGNMENT);
        this.grid = grid;
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.xStarts);
        buttonGroup.add(this.oStarts);
        
        this.xStarts.setSelected(true);
        
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(this.xStarts);
        p.add(this.oStarts);

        this.add(newGame);
        this.add(p);
        
        newGame.addActionListener(new ActionListener() {
            GameControllerPanel self = GameControllerPanel.this;
            public void actionPerformed(ActionEvent evt) {
                self.grid.newGame();
            }
        });
        
        // Start a new game.
        newGame.doClick();
    }

    public State getStartingPlayer() {
        return this.xStarts.isSelected() ? State.X : State.O;
    }

    public void won() {
        this.xStarts.setEnabled(true);
        this.oStarts.setEnabled(true);
    }

    public State playedFirstMove() {
        this.xStarts.setEnabled(false);
        this.oStarts.setEnabled(false);
        return this.getStartingPlayer();
    }
}

class GameStatsPanel extends JPanel {

    private int xWins = 0;
    private JLabel xWinsLabel = new JLabel();
    private int ties = 0;
    private JLabel tiesLabel = new JLabel();
    private int oWins = 0;
    private JLabel oWinsLabel = new JLabel();
    
    GameStatsPanel() {
        super();
        this.setLayout(new GridLayout(1, 3));
        this.xWinsLabel.setHorizontalAlignment(JLabel.CENTER);
        this.tiesLabel.setHorizontalAlignment(JLabel.CENTER);
        this.oWinsLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(this.xWinsLabel);
        this.add(this.tiesLabel);
        this.add(this.oWinsLabel);
        this.updateStats(null);
    }

    public void updateStats(State state) {
        if (state != null) {
            switch (state) {
            case EMPTY:
                this.ties++;
                break;
            case X:
                this.xWins++;
                break;
            case O:
                this.oWins++;
                break;
            default:
                break;
            }
        }
        this.xWinsLabel.setText("X Wins: " + this.xWins);
        this.tiesLabel.setText("Ties: " + this.ties);
        this.oWinsLabel.setText("O Wins: " + this.oWins);
    }
}

class Grid extends JPanel {
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
