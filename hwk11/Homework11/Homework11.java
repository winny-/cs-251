package Homework11;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import Homework11.GameStatsPanel;
import Homework11.GameControllerPanel;
import Homework11.Grid;

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
