package Homework9;

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

public class Testing {
	public static void test(JFrame frame, JButton[] buttons, JButton b, TicTacToe game) {
		String comments = "";
		int defaultClosing = frame.getDefaultCloseOperation();
		comments += defaultClosing == 3 ? "" : "close button is not set\n";
		int width = frame.getWidth();
		comments += width == 600 ? "" : "Width is not correct, expected 300\n";

		int height = frame.getHeight();
		comments += height == 600 ? "" : "Hieght is not correct, expected 600\n";

		String dLayout = frame.getLayout().getClass().getSimpleName().toString();

		comments += dLayout.equals("BorderLayout") ? "" : "Layout of the Jframe/main container is not correct, expected BorderLayout\n";

		String ButtonsContainerLayout = buttons[0].getParent().getLayout().getClass().getSimpleName().toString();
		comments += ButtonsContainerLayout.equals("GridLayout") ? ""
				: "Layout of the container of the buttons is not correct, expected GridLayout\n";

		String newGameContainerLayout = b.getParent().getLayout().getClass().getSimpleName().toString();
		comments += newGameContainerLayout.equals("BoxLayout") ? ""
				: "Layout of the container of the new Game button is not correct, expected BoxLayout\n";

		LayoutManager nL = ((JPanel) b.getParent().getParent()).getLayout();
		Container g = (Container) ((BorderLayout) nL).getLayoutComponent(BorderLayout.NORTH);
		String north = g.getLayout().getClass().getSimpleName();

		comments += north.equals("BoxLayout") ? "" : "The North region of the main container must contains the new Game button componenet\n";

		LayoutManager cL = ((JPanel) b.getParent().getParent()).getLayout();
		Container gg = (Container) ((BorderLayout) cL).getLayoutComponent(BorderLayout.CENTER);
		String center = gg.getLayout().getClass().getSimpleName();

		comments += center.equals("GridLayout") ? "" : "The Cneter of the main container must contains the componenet of the cells\n";

		boolean iData = Arrays.equals(game.data, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 });
		comments += iData ? "" : "The data array initially must be all zeros\n";

		buttons[0].doClick();
		delay();
		buttons[3].doClick();
		delay();
		buttons[6].doClick();
		delay();
		buttons[1].doClick();
		delay();
		buttons[4].doClick();
		delay();
		buttons[7].doClick();

		iData = Arrays.equals(game.data, new int[] { 1, 2, 0, 2, 1, 0, 1, 2, 0 });
		comments += iData ? "" : "The data array contents are not set properly, expected[1, 2, 0, 2, 1, 0, 1, 2, 0]\n";

		buttons[2].doClick();
		delay();
		buttons[5].doClick();
		delay();
		buttons[8].doClick();
		delay();

		iData = Arrays.equals(game.data, new int[] { 1, 2, 1, 2, 1, 2, 1, 2, 1 });
		comments += iData ? "" : "The data array contents are not set properly, expected [1, 2, 1, 2, 1, 2, 1, 2, 1]\n";

		// nothing should be changed
		Random r = null;
		for (int i = 0; i < buttons.length; i++) {
			r = new Random(i);
			buttons[r.nextInt(buttons.length)].doClick();
			delay();
		}
		
		JOptionPane.showMessageDialog(null, "Check the output expected \n"+ "X  O  X \n"+ "O  X  O \n"+ "X  O  X \n, press OK to resume testing", "Comments", JOptionPane.WARNING_MESSAGE);

		iData = Arrays.equals(game.data, new int[] { 1, 2, 1, 2, 1, 2, 1, 2, 1 });
		comments += iData ? "" : "The data array contents are not set properly, expected [1, 2, 1, 2, 1, 2, 1, 2, 1]\n";

		boolean newGameA = b.getAlignmentX() == Component.CENTER_ALIGNMENT;
		comments += newGameA ? "" : "The Alignment of the new Game button is not correct, expected to be center\n";

		b.doClick();
		iData = Arrays.equals(game.data, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 });
		comments += iData ? "" : "After hitting the new game button ,The data array must contain all zeros\n";

		for (JButton bu : buttons)
			bu.doClick();

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
}
