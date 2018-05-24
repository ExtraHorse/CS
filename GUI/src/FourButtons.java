import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FourButtons extends JPanel {
	JButton redButton, greenButton, blueButton, grayButton;
	JPanel buttonPanel;
	public FourButtons() {
		setLayout(new BorderLayout());
		buttonPanel = new JPanel(new GridLayout(1,4));
		redButton = new JButton("Red");
		greenButton = new JButton("Green");
		blueButton = new JButton("Blue");
		grayButton = new JButton("Gray");
		buttonPanel.add(redButton);
		buttonPanel.add(greenButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(grayButton);
		redButton.addActionListener(new Listener());
		greenButton.addActionListener(new Listener());
		blueButton.addActionListener(new Listener());
		grayButton.addActionListener(new Listener());
		add(buttonPanel, BorderLayout.NORTH);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Four Buttons");
		frame.setSize(400,400);
		frame.setContentPane(new FourButtons());
		frame.setVisible(true);
	}
	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == redButton)
				setBackground(Color.RED);
			if(e.getSource() == greenButton)
				setBackground(Color.green);
			if(e.getSource() == blueButton)
				setBackground(Color.BLUE);
			if(e.getSource() == grayButton)
				setBackground(Color.GRAY);
		}
	}
}
