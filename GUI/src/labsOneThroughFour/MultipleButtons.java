package labsOneThroughFour;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MultipleButtons extends JPanel {
	private JLabel label;
	private JButton rand, cbrt, recip, quit;
	private JPanel buttons;
	
	public MultipleButtons() {
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 4));
		setLayout(new GridLayout(2, 1));
		label = new JLabel("0.0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		rand = new JButton("Random");
		cbrt = new JButton("Cube Root");
		recip = new JButton("Reciprocal");
		quit = new JButton("Quit");
		JButton[] buttonArray = {rand, cbrt, recip, quit};
		for(JButton b : buttonArray) {
			b.setSize(100, 50);
			b.addActionListener(new Listener());
			buttons.add(b);
		}
		add(label);
		add(buttons);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("MultipleButtons");
		frame.setSize(500, 150);
		frame.setContentPane(new MultipleButtons());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double message = Double.parseDouble(label.getText());
			if(e.getSource() == rand)
				label.setText(Math.random() + "");
			if(e.getSource() == cbrt)
				label.setText(Math.cbrt(message) + "");
			if(e.getSource() == recip)
				label.setText((1 / message) + "");
			if(e.getSource() == quit)
				System.exit(0);
		}
	}
}