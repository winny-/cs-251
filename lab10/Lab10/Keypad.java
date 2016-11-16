package Lab10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

interface KeypadListener {
    public void keyPressed(Keypad.Key key);
}

class Keypad extends JPanel {
    private final List<KeypadListener> listeners = new ArrayList<>();

    public enum Key {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        DECIMAL,
        CLEAR;

        public String toString() {
            switch (this) {
            case DECIMAL:
                return ".";
            case CLEAR:
                return "C";
            default:
                return Integer.toString(this.ordinal());
            }
        }
    };


    private final Key[] keypadLayout = {
        Key.SEVEN, Key.EIGHT, Key.NINE,
        Key.FOUR,  Key.FIVE,  Key.SIX,
        Key.ONE,   Key.TWO,   Key.THREE,
        Key.CLEAR, Key.ZERO,  Key.DECIMAL,
    };
    
    Keypad() {
        this.setLayout(new GridLayout(4, 3, 4, 4));
        for (Key key : this.keypadLayout) {
            this.addButton(key);
        }
    }

    private void addButton(Key key) {
        JButton button = new JButton(key.toString());
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (KeypadListener listener : Keypad.this.listeners) {
                    listener.keyPressed(key);
                }
            }
        });
        this.add(button);
    }

    public void addListener(KeypadListener listener) {
        if (this.listeners.contains(listener)) {
            return;
        }
        this.listeners.add(listener);
    }

    public void removeListener(KeypadListener listener) {
        this.listeners.remove(listener);
    }
}
