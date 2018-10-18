package advCSQ1;

import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Pd5ZacharyWangMaze extends JPanel {
	Timer timer;
	JButton[][] GUIGrid;
	JButton start;
	int StartX, StartY;
	Scanner input = new Scanner(System.in);
	ActionListener taskPerformer;
	private int[][] grid = { { 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1 },
			{ 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1 },
			{ 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	// int [] [] grid = { {1,1,1}, {1,0,1},
	// {0,0,1} };

	public Pd5ZacharyWangMaze() {
		setLayout(new BorderLayout());
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(grid.length, grid[0].length));
		GUIGrid = new JButton[grid.length][grid[0].length];
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				JButton b = new JButton();
				b.setEnabled(false);
				if (grid[r][c] == 1)
					b.setBackground(Color.white);
				else
					b.setBackground(Color.black);
				GUIGrid[r][c] = b;
				center.add(b);
			}
		}
		add(BorderLayout.CENTER, center);
		start = new JButton("START");
		start.addActionListener(new Listener());
		add(BorderLayout.SOUTH, start);
		System.out.println("Enter start x and y");
		StartX = input.nextInt();
		StartY = input.nextInt();
	}

	// Assumption: the exit is at the bottom right corner of the grid
	//
	public boolean findAnExit(int x, int y) {
		String path = "START: ";
		return findAnExitHelper(x, y, path);
	} // FindAnExit

	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			findAnExit(StartX, StartY);
		}
	}

	public int[][] getMaze() {
		return grid;
	}

	public void setButton(int x, int y) {
		GUIGrid[y][x].setBackground(Color.blue);
	}

	public boolean findAnExitHelper(int x, int y, String path) {
		setButton(x, y);
		int ONE_SECOND = 1000;
		timer = new Timer(ONE_SECOND, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setButton(x, y);
			}
		});
		timer.start();
		if (x == grid[0].length - 1 && y == grid.length - 1) {
			System.out.println("true");
			return true;
		}
		if (x > 0 && grid[y][x - 1] == 1 && path.charAt(path.length() - 1) != 'r')
			if (findAnExitHelper(x - 1, y, path + "l"))
				return true;
		if (x < grid[0].length - 1 && grid[y][x + 1] == 1 && path.charAt(path.length() - 1) != 'l')
			if (findAnExitHelper(x + 1, y, path + "r"))
				return true;
		if (y > 0 && grid[y - 1][x] == 1 && path.charAt(path.length() - 1) != 'd')
			if (findAnExitHelper(x, y - 1, path + "u"))
				return true;
		if (y < grid.length - 1 && grid[y + 1][x] == 1 && path.charAt(path.length() - 1) != 'u')
			if (findAnExitHelper(x, y + 1, path + "d"))
				return true;
		return false;
	}

	// findAnExitHelper

	public String toString() {
		String s = "";
		for (int[] row : grid) {
			for (int v : row)
				s += "[" + v + "]";
			s += "\n";
		}
		return s;
	} // toString
} // Maze

/********************************
 * Sample Runs Run #1 ----jGRASP exec: java Maze
 * 
 * 1 1 1 0 1 1 0 0 0 1 1 1 1 1 0 1 1 1 0 1 1 1 1 0 0 1 0 0 0 0 1 0 1 0 1 0 1 0 0
 * 1 1 1 0 1 1 1 0 1 0 0 1 1 1 0 1 0 0 0 0 1 1 1 0 0 1 1 0 1 1 1 1 1 1 0 1 1 1 0
 * 1 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 
 * Start location coordinates (separated by a space): 0 0
 * [0,0][0,1][0,2][1,2][1,3][1,4][2,4][3,4][3,5][3,6][2,6][1,6][1,7][1,8][2,8][3,8][4,8][4,7][5,7][5,6][5,5][5,4][5,3][5,2][4,2][3,2][3,1][3,0][4,0][5,0][6,0][7,0][7,1][7,2][7,3][7,4][7,5][7,6][7,7][7,8][7,9][7,10][7,11][7,12]
 * Successfully exit the maze!!!
 * 
 * 7 7 7 0 1 1 0 0 0 1 1 1 1 3 0 7 7 7 0 7 7 7 1 0 0 1 0 0 0 0 7 0 7 0 7 0 1 0 0
 * 7 7 7 0 7 7 7 0 7 0 0 1 1 7 0 7 0 0 0 0 7 7 1 0 0 1 7 0 7 7 7 7 7 7 0 1 1 1 0
 * 7 0 0 0 0 0 0 0 0 0 0 0 0 7 7 7 7 7 7 7 7 7 7 7 7 7
 * 
 * 
 * ----jGRASP: operation complete. Run #2 ----jGRASP exec: java Maze
 * 
 * 1 1 1 0 1 1 0 0 0 1 1 1 1 1 0 1 1 1 0 1 1 1 1 0 0 1 0 0 0 0 1 0 1 0 1 0 1 0 0
 * 1 1 1 0 1 1 1 0 1 0 0 1 1 1 0 1 0 0 0 0 1 1 1 0 0 1 1 0 1 1 1 1 1 1 0 1 1 1 0
 * 1 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 
 * Start location coordinates (separated by a space): 3 10 Still trapped inside!
 * 
 * 1 1 1 0 1 1 0 0 0 1 1 1 1 1 0 1 1 1 0 1 1 1 1 0 0 1 0 0 0 0 1 0 1 0 1 0 1 0 0
 * 1 1 1 0 1 1 1 0 1 0 0 1 1 1 0 1 0 0 0 0 1 1 1 0 0 1 1 0 1 1 1 1 1 1 0 1 1 1 0
 * 1 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 
 * Start location coordinates (separated by a space): 7 0
 * [7,0][7,1][7,2][7,3][7,4][7,5][7,6][7,7][7,8][7,9][7,10][7,11][7,12]
 * Successfully exit the maze!!!
 * 
 * 3 3 3 0 3 3 0 0 0 3 3 3 3 3 0 3 3 3 0 3 3 3 3 0 0 3 0 0 0 0 3 0 3 0 3 0 1 0 0
 * 3 3 3 0 3 3 3 0 3 0 0 1 1 3 0 3 0 0 0 0 3 3 3 0 0 1 3 0 3 3 3 3 3 3 0 3 3 3 0
 * 3 0 0 0 0 0 0 0 0 0 0 0 0 7 7 7 7 7 7 7 7 7 7 7 7 7
 * 
 * 
 * ----jGRASP: operation complete. Run #3 ----jGRASP exec: java Maze
 * 
 * 1 1 1 0 1 1 0 0 0 1 1 1 1 1 0 1 1 1 0 1 1 1 1 0 0 1 0 0 0 0 1 0 1 0 1 0 1 0 0
 * 1 1 1 0 1 1 1 0 1 0 0 1 1 1 0 1 0 0 0 0 1 1 1 0 0 1 1 0 1 1 1 1 1 1 0 1 1 1 0
 * 1 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 
 * Start location coordinates (separated by a space): 2 10 Still trapped inside!
 * 
 * 1 1 1 0 1 1 0 0 0 1 1 1 1 1 0 1 1 1 0 1 1 1 1 0 0 1 0 0 0 0 1 0 1 0 1 0 3 0 0
 * 1 1 1 0 1 1 1 0 1 0 0 1 1 1 0 1 0 0 0 0 1 1 1 0 0 1 1 0 1 1 1 1 1 1 0 1 1 1 0
 * 1 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 
 * Start location coordinates (separated by a space): 0 12
 * [0,12][0,11][0,10][0,9][1,9][1,8][2,8][3,8][4,8][4,7][5,7][5,6][5,5][5,4][5,3][5,2][4,2][3,2][3,1][3,0][4,0][5,0][6,0][7,0][7,1][7,2][7,3][7,4][7,5][7,6][7,7][7,8][7,9][7,10][7,11][7,12]
 * Successfully exit the maze!!!
 * 
 * 1 1 1 0 1 1 0 0 0 7 7 7 7 1 0 1 1 1 0 1 1 7 7 0 0 3 0 0 0 0 1 0 1 0 7 0 3 0 0
 * 7 7 7 0 1 1 1 0 7 0 0 1 1 7 0 7 0 0 0 0 7 7 1 0 0 1 7 0 7 7 7 7 7 7 0 1 1 1 0
 * 7 0 0 0 0 0 0 0 0 0 0 0 0 7 7 7 7 7 7 7 7 7 7 7 7 7
 * 
 * 
 * ----jGRASP: operation complete.
 * 
 * 
 *********************************/