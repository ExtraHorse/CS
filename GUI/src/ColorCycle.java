import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ColorCycle extends JPanel {
	private JButton button;
	public static void main(String[] args) {
		JFrame frame = new JFrame("Color Cycle");
		frame.setSize(400,400);
		frame.setContentPane(new ColorCycle());
		frame.setVisible(true);
	}
	public ColorCycle() {
		button = new JButton("Change Color");
		add(button);
		button.addActionListener(new Listener());
		setBackground(Color.white);
	}
	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(getBackground().equals(Color.white))
				setBackground(Color.red);
			else if(getBackground().equals(Color.red))
				setBackground(Color.green);
			else if(getBackground().equals(Color.green))
				setBackground(Color.blue);
			else if(getBackground().equals(Color.blue))
				setBackground(Color.gray);
			else if(getBackground().equals(Color.gray))
				setBackground(Color.red);
		}
	}
}
