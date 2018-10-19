package advCSQ1;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Battleship.Coord;

public class Pd5ZacharyWangMaze extends JPanel {
	ListNode<Coord> travelled = new ListNode<Coord>(null, null), temp = travelled;
	Timer timer;
	boolean controller = false;
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
		timer = new Timer(200, new timeListener());
		timer.setRepeats(true);
	}

	// Assumption: the exit is at the bottom right corner of the grid
	//
	public boolean findAnExit(int x, int y) {
		boolean solved = findAnExitHelper(x, y);
		timer.start();
		return solved;
	} // FindAnExit

	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			findAnExit(StartX, StartY);
		}
	}

	public int[][] getMaze() {
		return grid;
	}

	public boolean findAnExitHelper(int x, int y) {
		int location;
		try {
			location = grid[y][x];
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
		if (location == 0 || location == 3)
			return false;
		grid[y][x] = 3;
		temp.setNext(new ListNode<Coord>(new Coord(x, y, 0), null));
		temp = temp.getNext();
		return(x == grid[0].length - 1 && y == grid.length - 1 
				|| findAnExitHelper(x + 1, y) 
				|| findAnExitHelper(x - 1, y)
				|| findAnExitHelper(x, y + 1)
				|| findAnExitHelper(x, y - 1));
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

	private class timeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			GUIGrid[travelled.getNext().getValue().y][travelled.getNext().getValue().x].setBackground(Color.blue);
			travelled = travelled.getNext();
			if(travelled.getNext() == null)
				timer.stop();
		}
	}
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