package Homework11;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import Homework11.Grid;
import Homework11.State;

public class GameStatsPanel extends JPanel {

    private int xWins = 0;
    private JLabel xWinsLabel = new JLabel();
    private int ties = 0;
    private JLabel tiesLabel = new JLabel();
    private int oWins = 0;
    private JLabel oWinsLabel = new JLabel();
    
    GameStatsPanel(Grid grid) {
        super();
        this.setLayout(new GridLayout(1, 3));
        this.xWinsLabel.setHorizontalAlignment(JLabel.CENTER);
        this.tiesLabel.setHorizontalAlignment(JLabel.CENTER);
        this.oWinsLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(this.xWinsLabel);
        this.add(this.tiesLabel);
        this.add(this.oWinsLabel);
        this.updateStats(null);
        grid.addListener(new GridListener() {
            public void gameFinished(Grid grid, State winner) {
                GameStatsPanel.this.updateStats(winner);
            }

            public State gameStarted(Grid grid) {
                return null;
            }
        });
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
