package Homework9;

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
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Homework9  extends JFrame {
    Homework9() {
        this.setTitle("Tic Tac Toe");
        this.setPreferredSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
    public static void main(String[] args) throws IOException {
        // create a frame with border layout
        JFrame frame = new Homework9();
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

        // add game panel (north) and box panel (center) to the frame and set it visible
        frame.add(gamePanel, BorderLayout.NORTH);
        frame.add(boxPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

        // TODO: Uncomment the line below for testing
        Testing.test(frame, buttons, newGame, game);
    }
}

class TicTacToe {
    // this array holds flags for buttons
    //   0 means not clicked
    //   1 means X
    //   2 means O
    int[] data = new int[9];

    // array of buttons representing the grid cells of the game
    JButton[] buttons;

    // this means next move will be X if true and O if false
    boolean isX = true; 
	
    // use this fields to hold image icons loaded from files
    ImageIcon icon_x, icon_o;

    TicTacToe(JButton[] buttons) throws IOException {
        if (buttons.length != this.data.length) {
            throw new IllegalArgumentException("buttons must be of length 9");
        }
        this.buttons = buttons;
        this.icon_x = new ImageIcon(ImageIO.read(this.getClass().getResource("X.png")));
        this.icon_o = new ImageIcon(ImageIO.read(this.getClass().getResource("O.png")));
        this.addListener();
        this.clear();
    }
	 
    // add action the same button listner to all buttons
    private void addListener() {
        for (JButton b : this.buttons) {
            b.addActionListener(this.buttonListener);
        }
    }

    private int findButtonIndex(JButton button) {
        for (int i = 0; i < this.buttons.length; i++) {
            if (this.buttons[i] == button) {
                return i;
            }
        }
        throw new RuntimeException(String.format("No such button %s", button));
    }

    // create a button listener object directly from ActionListener interface
    // by overridding its actionPerformed method
    //
    // the actionPerformed method will find out 
    //              which button is clicked and 
    //              whether the click is successful and switch the player if so
    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToe self = TicTacToe.this;
            JButton theButton = (JButton)e.getSource();

            // find the button's index.
            JButton[] buttons = self.buttons;
            int i;
            for (i = 0; i < buttons.length && buttons[i] != theButton; i++)
                ;
            if (self.play(i, self.isX)) {
                theButton.setIcon(self.isX ? self.icon_x : self.icon_o);
                self.isX = !self.isX;
            }
        }
    };
	
    // set the background color to each button to white 
    // remove icons by calling setIcon(null)
    // clear the data array to 0
    // reset isX to true
    void clear() {
        this.isX = true;
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = 0;
        }
        for (JButton b : this.buttons) {
            b.setIcon(null);
        }
    }

    // put X or O at the i'th button
    // set the data array to 1 for X and 2 for O
    //
    // returns true if i'th button is not already clicked
    private boolean play(int i, boolean isX) {
        if (this.data[i] != 0) {
            return false;
        }
        this.data[i] = isX ? 1 : 2;
        return true;
    }
}

