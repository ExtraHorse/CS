package FourFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FourFunction extends JPanel {
	private NumberButton[][] numbers;
	private NumberButton zero;
	private JButton dec, equal;
	private JTextField disp;
	private String nextOperation = "";
	private double previous;

	public FourFunction() {
		GridBagLayout all = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(all);
		disp = new JTextField();
		disp.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		disp.setHorizontalAlignment(SwingConstants.RIGHT);
		;
		constraints.gridy = 0;
		constraints.gridx = 0;
		constraints.gridwidth = 5;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(disp, constraints);
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		numbers = new NumberButton[3][3];
		int count = 1;
		for (int r = 0; r < numbers.length; r++) {
			for (int c = 0; c < numbers.length; c++) {
				numbers[r][c] = new NumberButton(count);
				constraints.ipady = 50;
				constraints.ipadx = 35;
				constraints.gridy = r + 1;
				constraints.gridx = c;
				count++;
				add(numbers[r][c], constraints);
				numbers[r][c].addActionListener(new Listener());
			}
		}
		zero = new NumberButton(0);
		zero.addActionListener(new Listener());
		constraints.ipadx = 111;
		constraints.ipady = 50;
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		add(zero, constraints);
		dec = new JButton(".");
		constraints.ipadx = 39;
		constraints.ipady = 50;
		constraints.gridy = 4;
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		add(dec, constraints);
		equal = new JButton("=");
		equal.addActionListener(new EqualsListener());
		constraints.ipadx = 35;
		constraints.ipady = 50;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridy = 5;
		constraints.gridx = 0;
		constraints.gridwidth = 5;
		add(equal, constraints);
		// constraints.weighty = 0;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridwidth = 1;
		JButton[] operators = { new JButton("+"), new JButton("-"), new JButton("*"), new JButton("/") };
		for (int y = 0; y < operators.length; y++) {
			constraints.gridy = y + 1;
			constraints.gridx = 3;
			constraints.ipadx = 15;
			constraints.ipady = 25;
			constraints.weightx = .1;
			operators[y].addActionListener(new OperatorListener());
			add(operators[y], constraints);
		}
		disp.setSize(400, 100);
	}

	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String current = disp.getText();
			if (!nextOperation.equals(""))
				current = "";
			if(current.equals("die"))
				current = "";
			for (NumberButton[] arr : numbers)
				for (NumberButton b : arr) {
					if (e.getSource() == b)
						current += "" + b.getVal();
				}
			if (e.getSource() == zero)
				current += "0";
			if (e.getSource() == dec)
				current += ".";
			disp.setText(current);
		}
	}
	
	private class EqualsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!disp.getText().equals("")) {
				double current = Double.parseDouble(disp.getText());
				if (!nextOperation.equals("")) {
					if (nextOperation.equals("+"))
						disp.setText("" + (previous + current));
					if (nextOperation.equals("-"))
						disp.setText("" + (previous - current));
					if (nextOperation.equals("/")) {
						if(current == 0.0) 
							disp.setText("die");
						else disp.setText("" + (previous / current));
					}
					if (nextOperation.equals("*"))
						disp.setText("" + (previous * current));
					previous = 0;
				}
				nextOperation = "";
			}
		}
	}

	private class OperatorListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!disp.getText().equals("")) {
				double current = Double.parseDouble(disp.getText());
				if (nextOperation.equals("")) {
					previous = Double.parseDouble(disp.getText());
				} else {
					if (nextOperation.equals("+"))
						disp.setText("" + (previous + current));
					if (nextOperation.equals("-"))
						disp.setText("" + (previous - current));
					if (nextOperation.equals("/")) {
						if(current == 0.0) 
							disp.setText("die");
						else disp.setText("" + (previous / current));
					}
					if (nextOperation.equals("*"))
						disp.setText("" + (previous * current));
					previous = Double.parseDouble(disp.getText());
				}
				nextOperation = ((JButton) e.getSource()).getText();
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("panel02");
		frame.setSize(400, 480);
		frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new FourFunction());
		frame.setVisible(true);
	}

}
