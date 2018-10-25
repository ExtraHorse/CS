package advCSQ1;

/***********************************************************************************************************************************************
 * Name: Zachary Wang
 * Period: 5
 * Name of the Lab: Maze
 * Purpose of the Program: solve a maze recursively
 * Due Date: 10/24/18
 * Date Submitted:10/24/18 
 * What I learned: Setting a recursive method as a boolean and using large boolean expressions
 * can help solve the problem of exiting the recursion when a solution is found
 * What I wonder: Are the mazes generated by the algorithims we saw in class really mazes? Well, it's more like I wonder
 * how you actually properly define a maze, if such a definition can exist.
 * Student(s) who helped me (to what extent): 
 * Student(s) whom I helped (to what extent):
 *************************************************************************************************************************************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Pd5ZacharyWangMaze extends JPanel {
	JButton[][] GUIGrid;
	JButton start;
	int StartX, StartY;
	private int[][] grid = { { 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1 },
			{ 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1 },
			{ 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public Pd5ZacharyWangMaze() {
		setLayout(new BorderLayout());
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(grid.length, grid[0].length));
		GUIGrid = new JButton[grid.length][grid[0].length];
		for (int r = 0; r < grid.length; r++) {// create the board
			for (int c = 0; c < grid[0].length; c++) {
				JButton b = new JButton();
				if (grid[r][c] == 1)
					b.setBackground(Color.white);
				else {
					b.setBackground(Color.black);
					b.setEnabled(false);
				}
				b.addActionListener(new locationListener());
				GUIGrid[r][c] = b;
				center.add(b);
			}
		}
		add(BorderLayout.CENTER, center);
		start = new JButton("Choose a start");
		start.setEnabled(false);
		start.addActionListener(new startListener());
		add(BorderLayout.SOUTH, start);
	}

	// Assumption: the exit is at the bottom right corner of the grid
	//
	public boolean findAnExit(int x, int y) {
		return findAnExitHelper(x, y);
	} // FindAnExit

	private class startListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			start.setEnabled(false);
			findAnExit(StartX, StartY);
		}
	}

	private class locationListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int row = 0; row < GUIGrid.length; row++)
				for (int col = 0; col < GUIGrid[0].length; col++) {
					GUIGrid[row][col].setEnabled(false);
					if (e.getSource() == GUIGrid[row][col]) {
						StartX = col;// determine the start button
						StartY = row;
						GUIGrid[row][col].setBackground(Color.GREEN);
					}
				}
			start.setEnabled(true);
			start.setText("START");
		}
	}

	public boolean findAnExitHelper(int x, int y) {

		int location;
		try {// Check if the requested space is within the board
			location = grid[y][x];
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		if (location == 0 || location == 3)// not passable if space is a wall or has already been travelled
			return false;
		GUIGrid[y][x].setBackground(Color.BLUE);
		this.paintImmediately(0, 0, 1920, 1080);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		grid[y][x] = 3;// marks as passed
		return (x == grid[0].length - 1 && y == grid.length - 1 || findAnExitHelper(x + 1, y)// expands search in all
																								// directions
				|| findAnExitHelper(x - 1, y) || findAnExitHelper(x, y + 1) || findAnExitHelper(x, y - 1));
	}

	// findAnExitHelper

	public static void main(String[] args) {
		JFrame frame = new JFrame("Maze");
		frame.setSize(500, 500);
		frame.setLocation(200, 0);
		frame.setContentPane(new Pd5ZacharyWangMaze());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}