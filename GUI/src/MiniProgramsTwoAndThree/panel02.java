package MiniProgramsTwoAndThree;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class panel02 extends JPanel{
	private JButton top, left, center, right, bottom;
	private ImageIcon pic;
	private JTextField bottomText;
	public panel02() {
		setLayout(new BorderLayout());
		//top button
		top = new JButton("north");
		add(top, BorderLayout.NORTH);
		top.setHorizontalAlignment(SwingConstants.CENTER);
		top.setBackground(Color.blue);
		top.setForeground(Color.yellow);
		top.setFont(new Font("Comic Sans MS", Font.ITALIC, 24));
		top.addActionListener(new Listener());
		//left button
		left = new JButton("west");
		add(left, BorderLayout.WEST);
		left.setHorizontalAlignment(SwingConstants.CENTER);
		left.setBackground(Color.orange);
		left.setForeground(Color.cyan);
		left.addActionListener(new Listener());
		//center button
		pic = new ImageIcon("C:/Users/Zachary Wang/git/CS/GUI/src/image1.jpg");
		center = new JButton(pic);
		add(center, BorderLayout.CENTER);
		center.addActionListener(new Listener());
		//right button
		right = new JButton("east");
		add(right, BorderLayout.EAST);
		right.setHorizontalAlignment(SwingConstants.CENTER);
		right.setBackground(Color.red);
		right.setForeground(Color.cyan);
		right.addActionListener(new Listener());
		//bottom button
		bottom = new JButton("south");
		add(bottom, BorderLayout.SOUTH);
		bottom.setHorizontalAlignment(SwingConstants.CENTER);
		bottom.setForeground(Color.MAGENTA);
		bottom.setBackground(Color.green);
		bottom.setFont(new Font("Times New Roman", Font.BOLD, 24));
		bottom.addActionListener(new Listener());
		//south text
		bottomText = new JTextField(240);
		bottomText.setText("Before Enter Pressed");
		bottom.add(bottomText);
		bottomText.addActionListener(new Listener2());
	}
	private class Listener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			top.setBackground(Color.WHITE);
			left.setBackground(Color.WHITE);
			right.setBackground(Color.WHITE);
			bottom.setBackground(Color.WHITE);
			bottomText.setText("Enter Pressed");
			top.setText("top");
			left.setText("left");
			center.setIcon(new ImageIcon("C:/Users/Zachary Wang/git/CS/GUI/src/tragedy.jpg"));
			right.setText("right");
			bottom.setText("south");
		}
	}
	
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == top)
				top.setText("top");
			if(e.getSource() == left)
				left.setText("left");
			if(e.getSource() == center)
				center.setIcon(new ImageIcon("C:/Users/Zachary Wang/git/CS/GUI/src/tragedy.jpg"));
			if(e.getSource() == right)
				right.setText("right");
			if(e.getSource() == bottom)
				bottom.setText("bottom");
		}
	}
}
