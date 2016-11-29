package Homework11;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import javax.imageio.ImageIO;

import Homework11.State;

public class Cell extends JButton {
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
