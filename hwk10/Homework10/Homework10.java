package Homework10;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout; 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JPanel; 
 

@SuppressWarnings("serial")
public class Homework10 extends JFrame {
    Homework10() {
        this.setTitle("Tic Tac Toe");
        this.setPreferredSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
    public static void main(String[] args) throws IOException { 
        JFrame frame = new Homework10();

        // copy from your homework 9
        // create a frame with border layout
        frame.setLayout(new BorderLayout());
		
        // create a panel with box layer aligned with y axis
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        
		
        // create a new game button that is centered on x axis
        JButton newGame = new JButton("New Game");
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add the new game button to the game panel
        gamePanel.add(newGame);
		
        // create a box panel with 3 x 3 grid layout
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new GridLayout(3, 3));
		
        // create an array of 9 buttons and add them to the box panel
        JButton[] buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            boxPanel.add(buttons[i]);
        }
		
        // instantiate a tic-tac-toe game object 
        final TicTacToe game = new TicTacToe(buttons);
		
        // add an action listener to the new-game buttom so that it clears the game on click.
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.clear();
            }
        });

        frame.add(gamePanel, BorderLayout.NORTH);
        frame.add(boxPanel, BorderLayout.CENTER);
        // add game panel (north) and box panel (center) to the frame and set it visible

        frame.pack();
        frame.setVisible(true);

        // TODO: Uncomment the line below for testing
        //Testing.test(frame, buttons, newGame, game);
		
        frame.setVisible(true);
    }
}

class TicTacToe {
    int[] data = new int[9];
    JButton[] buttons;
    boolean isX = false; 

    // this variable keeps track of how many steps the game has played (it cannot be >= 9)
    int steps = 0;

    // use the flag to indicate whether the game has ended due to that X wins or O wins but not due to a draw
    boolean gameEnded = true;
	
    ImageIcon icon_x, icon_o; 
	
    // save buttons, load icon images, and clear the game (by calling "clear" method)
    TicTacToe(JButton[] buttons) throws IOException {
        this.buttons = buttons; 
        icon_x = new ImageIcon(ImageIO.read(getClass().getResource("O.png")));
        icon_o = new ImageIcon(ImageIO.read(getClass().getResource("X.png"))); 

        clear();
    } 
	
    // add the same listener to all buttons
    void addListener() {
        for (JButton b : this.buttons) {
            if (b.getActionListeners().length == 0) {
                b.addActionListener(this.buttonListener);
            }
        }
    }
    // create an action listener
    ActionListener buttonListener = new ActionListener() {
        // 1. Use the event source to find out which button is clicked
        // 2. Find out if anyone wins and if so, has it X or O player won
        // 3. In the console, 
        //      if X wins, print "X wins", 
        //      if O wins, print "O wins", 
        //      if it is a draw (max number of steps has reached), print "Draw" 
        //      if nobody wins, do nothing and continue the game
        // 4. Don't forget to increment the "steps" variable to find out whether max number of steps has reached.
        // 5. Switch player in this method as well if it is a legal move.
        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToe self = TicTacToe.this;
            JButton button = (JButton)e.getSource();

            int i;
            for (i = 0; i < self.buttons.length && self.buttons[i] != button; i++)
                ;

            if (self.play(i, self.isX)) {
                int won = self.win();
                if (won != 0 || steps >= 9) {
                    String msg;
                    if (won == 0) {
                        msg = "Draw";
                    } else  {
                        msg = self.isX ? "X wins" : "O wins";
                    }
                    self.removeListener();
                    System.out.println(msg);
                }
            }
        }
    };
	
    // reset "steps" to 0
    // for each button, set the background color to "white" and remove icons from buttons "setIcon(null)"
    // reset "data" array to 0
    // if game has ended (due to someone winnning), add listener back to buttons and reset the flag to false
    void clear() {
        this.addListener();
        this.steps = 0;
        for (int index = 0; index < this.data.length; index++) {
            this.data[index] = 0;
        }
        for (JButton b : this.buttons) {
            b.setBackground(Color.WHITE);
            b.setIcon(null);
        }
    }

    // remove listener from all buttons and set "gameEnded" flag to true
    void removeListener() {
        for (JButton b : this.buttons) {
            for (ActionListener al : b.getActionListeners()) {
                b.removeActionListener(al);
            }
        }
    }
	
    // put X or O at i
    // if a button click is legal (i.e. the button was no clicked before), set icons to the button and update "data" array accordingly
    // return true if and only if the button click is legal
    boolean play(int i, boolean isX) {
        assert this.steps < 9;
        if (this.data[i] != 0) {
            return false;
        }
        steps++;
        this.buttons[i].setIcon(isX ? this.icon_x : this.icon_o);
        this.data[i] = isX ? 1 : 2;
        this.isX = !isX;
        return true;
    }
    
    // if X wins, return 1, 
    // if O wins, return 2, 
    // if draws or no result, return 0
    //
    // if someone wins, the remove listeners so that subsequent clicks won't change the remaining unclicked buttons
    int win() {
        for (int row = 0; row < TicTacToe.rowConditions.length; row++) {
            int won = checkRow(row);
            if (won != 0) {
                return won;
            }
        }
        for (int column = 0; column < TicTacToe.columnConditions.length; column++) {
            int won = checkColumn(column);
            if (won != 0) {
                return won;
            }
        }
        return checkDiagonal();
    }

    static private final int[][] rowConditions = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8}
    };
    
    // check whether the ith row matches
    //       if so, set the background for that row of buttons to blue color
    //       and also return 1 if X wins, 2 if O wins, 0 if nobody wins
    int checkRow(int i) {
        int[] condition = TicTacToe.rowConditions[i];
        int won = this.checkCondition(condition);
        if (won != 0) {
            this.setBackground(condition);
            return won;
        }
        return 0;
    }

    static private final int[][] columnConditions = {
        {0, 3, 6},
        {1, 4, 7},
        {2, 5, 8}
    };

    // check whether the ith column matches
    //       if so, set the background for that column of buttons to blue color
    //       and also return 1 if X wins, 2 if O wins, 0 if nobody wins
    int checkColumn(int i) {
        int[] condition = TicTacToe.columnConditions[i];
        int won = checkCondition(condition);
        if (won != 0) {
            this.setBackground(condition);
            return won;
        }
        return 0;
    }

    static private final int[][] diagonalConditions = {
        {0, 4, 8},
        {6, 4, 2}
    };

    // check whether a diagonal matches
    //       if so, set the background for that diagonal of buttons to blue color
    //       and also return 1 if X wins, 2 if O wins, 0 if nobody wins
    // note that there are two diagonals to check
    int checkDiagonal() {
        for (int[] c : TicTacToe.diagonalConditions) {
            int won = checkCondition(c);
            if (won != 0) {
                this.setBackground(c);
                return won;
            }
        }
        return 0;
    }

    int checkCondition(int[] condition) {
        int a = this.data[condition[0]];
        int b = this.data[condition[1]];
        int c = this.data[condition[2]];
        if (a != 0 && a == b && b == c) {
            return a;
        }
        return 0;
    }

    // set buttons at the specified indices to blue
    void setBackground(int[] indices) {
        for (int index : indices) {
            this.buttons[index].setBackground(Color.BLUE);
        }
    }
}
