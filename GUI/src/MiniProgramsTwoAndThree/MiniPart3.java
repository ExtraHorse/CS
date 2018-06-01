package MiniProgramsTwoAndThree;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MiniPart3 extends JPanel{
	JPanel eastPanel;
	JPanel northPanel;
	JPanel southPanel;
	JButton[] east;
	JButton[] north;
	JLabel southLabel;
	
	public MiniPart3() {
		setLayout(new BorderLayout());
		east = new JButton[3];
		north = new JButton[3];
		eastPanel = new JPanel(new GridLayout(3,1));
		northPanel = new JPanel(new FlowLayout());
		southPanel = new JPanel(new FlowLayout());
		for(int i = 0; i < east.length; i++) {
			east[i] = new JButton(i + 1 + "");
			eastPanel.add(east[i]);
			north[i] = new JButton(i + 4 + "");
			northPanel.add(north[i]);
		}
		east[0].addActionListener(new Listener1());
		east[1].addActionListener(new Listener2());
		east[2].addActionListener(new Listener3());
		north[0].addActionListener(new Listener4());
		north[1].addActionListener(new Listener5());
		north[2].addActionListener(new Listener6());
		southLabel = new JLabel("What is 10 - 6?");
		southLabel.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		southLabel.setForeground(Color.BLUE);
		southPanel.add(southLabel);
		add(southPanel, BorderLayout.SOUTH);
		add(northPanel, BorderLayout.NORTH);
		add(eastPanel, BorderLayout.EAST);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("main");
		frame.setSize(400, 400);
		frame.setContentPane(new MiniPart3());
		frame.setVisible(true);
	}
	
	private class Listener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			southLabel.setText("Incorrect: 1 + 6 < 10");
		}
	}
	private class Listener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			southLabel.setText("Incorrect: 2 + 6 < 10");
		}
	}
	private class Listener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			southLabel.setText("Incorrect: 3 + 6 < 10");
		}
	}
	private class Listener4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			southLabel.setText("Correct!");
		}
	}
	private class Listener5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			southLabel.setText("Incorrect: 5 + 6 > 10");
		}
	}
	private class Listener6 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			southLabel.setText("Incorrect: 6 + 6 > 10");
		}
	}
}
