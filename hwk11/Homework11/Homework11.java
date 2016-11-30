package Homework11;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Homework11 extends JFrame {
    Homework11() {
        super();
        this.setTitle("Tic Tac Toe");
        this.setPreferredSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        Grid grid = new Grid();
        GameStatsPanel gsp = new GameStatsPanel(grid);
        GameControllerPanel gcp = new GameControllerPanel(grid);
        this.add(gcp, BorderLayout.NORTH);
        this.add(grid, BorderLayout.CENTER);
        this.add(gsp, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Homework11();
    }
}
