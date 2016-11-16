package Lab10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class Lab10 extends JFrame {
    private final JTextField accumulator;
    private final Keypad keypad;

    private String model;
    public String getModel() {
        return this.model;
    }
    private void setModel(String theModel) {
        this.accumulator.setText(theModel);
        this.model = theModel;
    }
    
    Lab10() {
        this.setTitle("Keypad");
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300, 300));
        this.setDefaultCloseOperation(Lab10.EXIT_ON_CLOSE);
        

        this.accumulator = new JTextField();
        this.accumulator.setFont(new Font("Arial", Font.PLAIN, 40));
        this.accumulator.setHorizontalAlignment(SwingConstants.RIGHT);
        this.accumulator.setEditable(false);
        this.accumulator.setForeground(Color.BLACK);
        
        this.keypad = new Keypad();
        this.keypad.addListener(new KeypadListener() {
            public void keyPressed(Keypad.Key key) {
                Lab10 self = Lab10.this;
                switch (key) {
                case CLEAR:
                    self.setModel("0");
                    return;
                case DECIMAL:
                    if (!self.getModel().contains(".")) {
                        self.setModel(self.getModel() + ".");
                    }
                    return;
                default:
                    String s = key.toString();
                    self.setModel(self.getModel().equals("0") ?
                                  s
                                  : self.getModel() + s);
                    return;
                }
                
            }
        });

        this.add(this.accumulator, BorderLayout.NORTH);
        this.add(this.keypad, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);

        this.setModel("0");
    }

    public static void main(String[] args) {
        new Lab10();
    }
}
