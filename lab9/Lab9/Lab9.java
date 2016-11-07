package Lab9;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Lab9 extends JFrame {

    Lab9() {
        // set frame title
        this.setTitle("temperature converter");
        // set frame bounds
        // ww: set size
        this.setPreferredSize(new Dimension(1000, 200));
        // ww: center the window
        this.setLocationRelativeTo(null);
        // set frame layout
        //
        // TODO
        this.setLayout(new FlowLayout());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame frame = new Lab9();

        JTextField fahrenheitInput = new JTextField("", 10);
        JButton convertFromFahrenheitButton = new JButton("convert");
        JTextField celsiusInput = new JTextField("", 10);
        JButton convertFromCelsiusButton = new JButton("convert");

        frame.add(new JLabel("Fahrenheit"));
        frame.add(fahrenheitInput);
        frame.add(convertFromFahrenheitButton);
        frame.add(new JLabel("Celsius"));
        frame.add(celsiusInput);
        frame.add(convertFromCelsiusButton);
        // create labels, textfields, and buttons
        // TODO
        //
        // add them to the frame
        // TODO
		
        // create listener for fahrenheit to celsius conversion
        ActionListener f2c = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    BigDecimal x;
                    try {
                        x = new BigDecimal(fahrenheitInput.getText().trim());
                    } catch(NumberFormatException exn) {
                        celsiusInput.setText("undefined");
                        return;
                    }
                    BigDecimal y = x.subtract(new BigDecimal(32))
                        .multiply(new BigDecimal(5))
                        .divide(new BigDecimal(9), RoundingMode.HALF_UP);
                    celsiusInput.setText(y.toPlainString());
                }
            };
        convertFromFahrenheitButton.addActionListener(f2c);
        fahrenheitInput.addActionListener(f2c);


        ActionListener c2f = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    BigDecimal x;
                    try {
                        x= new BigDecimal(celsiusInput.getText().trim());
                    } catch(NumberFormatException exn) {
                        fahrenheitInput.setText("undefined");
                        return;
                    }
                    BigDecimal y = x.multiply(new BigDecimal(9))
                        .divide(new BigDecimal(5), RoundingMode.HALF_UP)
                        .add(new BigDecimal(32));
                    fahrenheitInput.setText(y.toPlainString());
                }
            };
        convertFromCelsiusButton.addActionListener(c2f);
        celsiusInput.addActionListener(c2f);
        
        // add listener f2c to the right button
        // TODO
		
        // create listener for celsius to fahrenheit conversion
        // add listener c2f to the right button
        // TODO
		
        // make frame visible
        // TODO
        frame.pack();
        frame.setVisible(true);
    }
}
