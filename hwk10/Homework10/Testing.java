package Homework10;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;

public class Testing {
    public static void test(JFrame frame, JButton[] buttons, JButton newGame, TicTacToe game) {
        String comments = "";
        int defaultClosing = frame.getDefaultCloseOperation();
        comments += defaultClosing == 3 ? "" : "close button is not set\n";
        int width = frame.getWidth();
        comments += width == 600 ? "" : "Width is not correct, expected 600\n";

        int height = frame.getHeight();
        comments += height == 600 ? "" : "Height is not correct, expected 600\n";

        String dLayout = frame.getLayout().getClass().getSimpleName().toString();

        comments += dLayout.equals("BorderLayout") ? "" : "Layout of the Jframe/main container is not correct, expected BorderLayout\n";

        String ButtonsContainerLayout = buttons[0].getParent().getLayout().getClass().getSimpleName().toString();
        comments += ButtonsContainerLayout.equals("GridLayout") ? ""
            : "Layout of the container of the buttons is not correct, expected GridLayout\n";

        String newGameContainerLayout = newGame.getParent().getLayout().getClass().getSimpleName().toString();
        comments += newGameContainerLayout.equals("BoxLayout") ? ""
            : "Layout of the container of the new Game button is not correct, expected BoxLayout\n";

        LayoutManager nL = ((JPanel) newGame.getParent().getParent()).getLayout();
        Container g = (Container) ((BorderLayout) nL).getLayoutComponent(BorderLayout.NORTH);
        String north = g.getLayout().getClass().getSimpleName();

        comments += north.equals("BoxLayout") ? "" : "The North region of the main container must contain the new Game button component\n";

        LayoutManager cL = ((JPanel) newGame.getParent().getParent()).getLayout();
        Container gg = (Container) ((BorderLayout) cL).getLayoutComponent(BorderLayout.CENTER);
        String center = gg.getLayout().getClass().getSimpleName();

        comments += center.equals("GridLayout") ? "" : "The Center of the main container must contain the component of the cells\n";

        boolean iData = Arrays.equals(game.data, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        comments += iData ? "" : "The data array initially must be all zeros\n";

        newGame.doClick();
        delay();
        iData = Arrays.equals(game.data, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        comments += iData ? "" : "The data array after clearing must be all zeros\n";
		
        buttons[1].doClick();
        delay();
        buttons[4].doClick();
        delay();
        buttons[3].doClick();
        delay();
        int[] expected1 = { 0, 1, 0, 1, 2, 0, 0, 0, 0 };
        iData = Arrays.equals(game.data, expected1);
        comments += iData ? "" : String.format("The data array contents are not set properly, expected: %s (got %s)\n", intArrayToString(expected1), intArrayToString(game.data));;
		
        buttons[7].doClick();
        delay();
        buttons[5].doClick();
        delay();
        buttons[0].doClick();
        delay();
        buttons[8].doClick();
        delay();
        buttons[2].doClick();
        delay();
        buttons[6].doClick();
		
        Random r = null;
        for (int i = 0; i < buttons.length; i++) {
            r = new Random(i);
            buttons[r.nextInt(buttons.length)].doClick();
            delay();
        }
		
        JOptionPane.showMessageDialog(null, "Check the output expected \n"+ "O  X  O \n"+ "X  O  X \n"+ "X  O  X \n, press OK to resume testing", "Comments", JOptionPane.WARNING_MESSAGE);

        int[] expected2 = { 2, 1, 2, 1, 2, 1, 1, 2, 1 };
        iData = Arrays.equals(game.data, expected2);
        comments += iData ? "" : String.format("The data array contents are not set properly, expected: %s (got %s)\n", intArrayToString(expected2), intArrayToString(game.data));

        newGame.doClick();
        delay();

        int[] expected3 = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        iData = Arrays.equals(game.data, expected3);
        comments += iData ? "" : String.format("The data array contents are not set properly after clearing, expected: %s (got %s)\n", intArrayToString(expected3), intArrayToString(game.data));

        buttons[0].doClick();
        delay();
        buttons[2].doClick();
        delay();
        buttons[2].doClick();
        delay();
        buttons[4].doClick();
        delay();
        buttons[5].doClick();
        delay();
        buttons[8].doClick();
        delay();
        JOptionPane.showMessageDialog(null, "Check the output expected \n"+ "O  -  X \n"+ "-  O  X \n"+ "-  -  O \n, press OK to resume testing", "Comments", JOptionPane.WARNING_MESSAGE);
		
        for (int j = 0; j < buttons.length; j++){
            buttons[j].doClick();
            delay();
        }
        int[] expected4 = { 2, 0, 1, 0, 2, 1, 0, 0, 2 };
        iData = Arrays.equals(game.data, expected4);
        comments += iData ? "" : String.format("actionListeners were not removed properly, expected: %s (got %s)\n", intArrayToString(expected4), intArrayToString(game.data));;
		
        comments += (buttons[0].getBackground().equals(Color.BLUE) && buttons[4].getBackground().equals(Color.BLUE) && buttons[8].getBackground().equals(Color.BLUE)) ? "" : "Button backgrounds not properly set for win condition\n";
		
        comments += (buttons[1].getBackground().equals(Color.WHITE) && buttons[2].getBackground().equals(Color.WHITE)
                     && buttons[3].getBackground().equals(Color.WHITE) && buttons[5].getBackground().equals(Color.WHITE)
                     && buttons[6].getBackground().equals(Color.WHITE) && buttons[7].getBackground().equals(Color.WHITE)) ? "" : "Some other buttons were incorrectly highlighted\n";
		
        newGame.doClick();
        delay();
        int[] expected5 = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        iData = Arrays.equals(game.data, expected5);
        comments += iData ? "" : String.format("The data array contents are not set properly, expected: %s (got %s)\n", expected5, game.data);;

        boolean backCheck = true;
        for (int j = 0; j < buttons.length; j++){
            if (!(buttons[j].getBackground().equals(Color.WHITE))){
                backCheck = false;
                break;
            }
        }
		
        comments += backCheck ? "" : "One or more buttons' backgrounds were not properly reset\n";
		
        buttons[6].doClick();
        delay();
        buttons[0].doClick();
        delay();
        buttons[7].doClick();
        delay();
        buttons[5].doClick();
        delay();
        buttons[2].doClick();
        delay();
        buttons[1].doClick();
        delay();
        buttons[8].doClick();
        delay();
        JOptionPane.showMessageDialog(null, "Check the output expected \n"+ "O  O  X \n"+ "-  -  O \n"+ "X  X  X \n, press OK to resume testing", "Comments", JOptionPane.WARNING_MESSAGE);
				
        for (int j = 0; j < buttons.length; j++){
            buttons[j].doClick();
            delay();
        }

        int[] expected6 = { 2, 2, 1, 0, 0, 2, 1, 1, 1 };
        iData = Arrays.equals(game.data, expected6);
        comments += iData ? "" : String.format("actionListeners were not removed properly, expected: %s (got %s)\n", intArrayToString(expected6), intArrayToString(game.data));
				
        comments += (buttons[6].getBackground().equals(Color.BLUE) && buttons[7].getBackground().equals(Color.BLUE) && buttons[8].getBackground().equals(Color.BLUE)) ? "" : "Button backgrounds not properly set for win condition\n";
				
        comments += (buttons[0].getBackground().equals(Color.WHITE) && buttons[1].getBackground().equals(Color.WHITE)
                     && buttons[2].getBackground().equals(Color.WHITE) && buttons[3].getBackground().equals(Color.WHITE)
                     && buttons[4].getBackground().equals(Color.WHITE) && buttons[5].getBackground().equals(Color.WHITE)) ? "" : "Some other buttons were incorrectly highlighted\n";
				
		
        boolean newGameA = newGame.getAlignmentX() == Component.CENTER_ALIGNMENT;
        comments += newGameA ? "" : "The Alignment of the new Game button is not correct, expected to be center\n";

        newGame.doClick();
        delay();
        iData = Arrays.equals(game.data, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        comments += iData ? "" : "After hitting the new game button ,The data array must contain all zeros\n";

        if(comments.trim().equals(""))
            comments="Good job, but still we do not Guarantee full grade till we look at the code";
        JOptionPane.showMessageDialog(null, comments, "Comments", JOptionPane.WARNING_MESSAGE);

    }

    public static void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static String intArrayToString(int[] ary) {
        String[] parts = new String[ary.length];
        for (int i = 0; i < ary.length; i++) {
            parts[i] = Integer.toString(ary[i]);
        }
        return "[" +  String.join(", ", parts) + "]";
    }
}
