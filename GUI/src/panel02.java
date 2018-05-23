
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class panel02 extends JPanel{
	private JButton top, left, center, right, bottom;
	private ImageIcon pic;
	public panel02() {
		setLayout(new BorderLayout());
		//top button
		top = new JButton("oh no");
		add(top, BorderLayout.NORTH);
		top.setHorizontalAlignment(SwingConstants.CENTER);
		top.setBackground(Color.blue);
		top.setForeground(Color.yellow);
		top.setFont(new Font("Comic Sans MS", Font.ITALIC, 24));
		top.addActionListener(new Listener());
		//left button
		left = new JButton("this bad");
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
		right = new JButton("how did this happen");
		add(right, BorderLayout.EAST);
		right.setHorizontalAlignment(SwingConstants.CENTER);
		right.setBackground(Color.red);
		right.setForeground(Color.cyan);
		right.addActionListener(new Listener());
		//bottom button
		bottom = new JButton("wow");
		add(bottom, BorderLayout.SOUTH);
		bottom.setHorizontalAlignment(SwingConstants.CENTER);
		bottom.setForeground(Color.MAGENTA);
		bottom.setBackground(Color.green);
		bottom.setFont(new Font("Times New Roman", Font.BOLD, 24));
		bottom.addActionListener(new Listener());
	}
	
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == top)
				top.setText("OH NO");
			if(e.getSource() == left)
				left.setText("this is very bad");
			if(e.getSource() == center)
				center.setIcon(new ImageIcon("C:/Users/Zachary Wang/git/CS/GUI/src/tragedy.jpg"));
			if(e.getSource() == right)
				right.setText("abosolute disaster");
			if(e.getSource() == bottom)
				bottom.setText("can do better");
		}
	}
}
