package MiniProgramsTwoAndThree;

import javax.swing.*;
public class driver02 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("panel02");
		frame.setSize(400, 350);
		frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new panel02());
		frame.setVisible(true);
	}
}
