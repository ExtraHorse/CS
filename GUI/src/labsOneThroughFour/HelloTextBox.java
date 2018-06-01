package labsOneThroughFour;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class HelloTextBox extends JPanel {
	private JTextField box;
	private JLabel label;
	private JButton button;
	private JPanel top;
	
	public HelloTextBox() {
		top = new JPanel();
		top.setLayout(new FlowLayout());
		setLayout(new GridLayout(2, 1));
		box = new JTextField("0.0", 10);
		box.setHorizontalAlignment(SwingConstants.RIGHT);
		label = new JLabel("0.0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		button = new JButton("SQRT");
		button.addActionListener(new Listener());
		box.setText("Enter a Number.");
		label.setText("see the square root");
		button.setText("Square Root");
		top.add(box);
		top.add(button);
		add(top);
		add(label);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("HelloTextBox");
		frame.setSize(400, 150);
		frame.setContentPane(new HelloTextBox());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double d = Double.parseDouble(box.getText());
			if(d < 0) {
				d *= -1;
				label.setText("" + Math.sqrt(d) + "i");
			} else {
				label.setText("" + Math.sqrt(d));
			}
		}
	}
}
