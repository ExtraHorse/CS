package advCSQ1;

import javax.swing.*;

public class Pd5ZacharyWangMazeMain {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Maze");
		frame.setSize(500, 500);
		frame.setLocation(200, 0);
		frame.setContentPane(new Pd5ZacharyWangMaze());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
