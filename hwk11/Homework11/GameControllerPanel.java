package Homework11;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class GameControllerPanel extends JPanel {

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

        this.grid.addListener(new GridListener() {
            GameControllerPanel self = GameControllerPanel.this;
            public void gameFinished(Grid grid, State winner) {
                self.won();
            }

            public State gameStarted(Grid grid) {
                return self.playedFirstMove();
            }
        });
        
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
