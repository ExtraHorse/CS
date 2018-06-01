package Battleship;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class BattleShip extends JPanel {
	private boardSpace[][] board;
	private int hits, torpedoes;
	private JLabel label;
	private JButton reset;
	static Ship[] fleet = { new Ship(4, "Battleship"), new Ship(3, "Destroyer"), new Ship(3, "Submarine"),
			new Ship(2, "Patrol Boat") };
	private PlayerPanel playerBoard;
	
	public BattleShip(PlayerPanel p) {
		playerBoard = p;
		setLayout(new BorderLayout());
		hits = 0;
		torpedoes = 60;
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		add(north, BorderLayout.NORTH);
		label = new JLabel("You have " + torpedoes + " torpedoes.");
		north.add(label);

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(10, 10));
		add(center, BorderLayout.CENTER);
		board = new boardSpace[10][10];
		for (int r = 0; r < 10; r++)
			for (int c = 0; c < 10; c++) {
				board[r][c] = new boardSpace();
				board[r][c].addActionListener(new Handler1(r, c));
				center.add(board[r][c]);
			}
		reset = new JButton("Reset");
		reset.addActionListener(new Handler2());
		reset.setEnabled(true);
		add(reset, BorderLayout.SOUTH);
		placeAll();
	}

	
	
	private void placeAll() {
		for (Ship s : fleet)
			placeShip(s);
	}

	private void placeShip(Ship s) {
		boolean added = false;
		int orientation; // 1 = down, 2 = right, 3 = up, 4 = left
		int r, c;
		while (!added) { //Find and Test possible placements
			r = (int)(Math.random() * (board.length - 2));
			c = (int)(Math.random() * (board[0].length - 2));
			System.out.println("Attempted Placement for enemy Ship: " + r + " " + c);
			ArrayList<Integer> posOrientations = new ArrayList<Integer>();
			if (board.length - r > s.getLength())
				posOrientations.add(1);
			if (r > s.getLength() - 1)
				posOrientations.add(3);
			if (board[0].length - c > s.getLength())
				posOrientations.add(2);
			if (c > s.getLength() - 1)
				posOrientations.add(4);
			orientation = posOrientations.get((int) (Math.random() * posOrientations.size()));
			System.out.println(orientation);
			int occupied = 0;
			if (orientation == 1) {
				for (int x = r; x < r + s.getLength(); x++)
					if (board[x][c].shipPresent() || board[x][Math.min(board[0].length - 1, c + 1)].shipPresent() || board[x][Math.max(c - 1, 0)].shipPresent() || board[Math.max(0, r - 1)][c].shipPresent() || board[Math.min(board.length - 1, r + s.getLength())][c].shipPresent())
						occupied++;
			} else if (orientation == 2) {
				for (int x = c; x < c + s.getLength(); x++)
					if (board[r][x].shipPresent() || board[Math.min(board.length - 1, r + 1)][x].shipPresent() || board[Math.max(r - 1, 0)][x].shipPresent() || board[r][Math.max(0, c - 1)].shipPresent() || board[r][Math.min(board[0].length - 1, c + s.getLength())].shipPresent())
						occupied++;
			} else if (orientation == 3) {
				for (int x = r; x > r - s.getLength(); x--)
					if (board[x][c].shipPresent() || board[x][Math.min(board[0].length - 1, c + 1)].shipPresent() || board[x][Math.max(c - 1, 0)].shipPresent() || board[r + 1][c].shipPresent() || board[Math.max(0,r - s.getLength())][c].shipPresent())
						occupied++;
			} else if (orientation == 4) {
				for (int x = c; x > c - s.getLength(); x--)
					if (board[r][x].shipPresent() || board[Math.min(board.length - 1, r + 1)][x].shipPresent() || board[Math.max(r - 1, 0)][x].shipPresent() || board[r][c + 1].shipPresent() || board[r][Math.max(0, c - s.getLength())].shipPresent())
						occupied++;
			}
			if (occupied == 0) {//place the ship if possible
				System.out.println(s.getName() + " placed at: ");
				if (orientation == 1)
					for (int i = r; i < r + s.getLength(); i++) {
						board[i][c].setShip(s);
						System.out.println("R: " + i + " C: " + c);
						s.addCoord(i, c);
					}
				else if (orientation == 2)
					for (int i = c; i < c + s.getLength(); i++) {
						board[r][i].setShip(s);
						System.out.println("R: " + r + " C: " + i);
						s.addCoord(r, i);
					}
				else if (orientation == 3)
					for (int i = r; i > r - s.getLength(); i--) {
						board[i][c].setShip(s);
						System.out.println("R: " + i + " C: " + c);
						s.addCoord(i, c);
					}
				else if (orientation == 4)
					for (int i = c; i > c - s.getLength(); i--) {
						board[r][i].setShip(s);
						System.out.println("R: " + r + " C: " + i);
						s.addCoord(r, i);
					}
				added = true;
			}
		}
	}

	private void endGame() {
		if (hits == 12)
			label.setText("You Win!");
		else if (playerBoard.getHits() == 12)
			label.setText("You lose. Try again?");
		else
			label.setText("Draw. Try again?");
		reset.setEnabled(true);
		for (boardSpace[] row : board)
			for (boardSpace space : row)
				space.end();
	}

	public class Handler1 implements ActionListener {
		private int myRow, myCol;

		public Handler1(int r, int c) {
			myRow = r;
			myCol = c;
		}

		public void actionPerformed(ActionEvent e) {
			// The following two statements are for debugging purpose
			boolean sank = false;
			System.out.println(myRow);
			System.out.println(myCol);
			board[myRow][myCol].hit();
			if (board[myRow][myCol].shipPresent()) {
				hits++;
				torpedoes--;
				for (Coord c : board[myRow][myCol].getShip().getCoordinates()) {
					if (c.x == myRow && c.y == myCol) {
						board[myRow][myCol].getShip().hit();
						if (board[myRow][myCol].getShip().getHits() == board[myRow][myCol].getShip().getLength())
							for (int i = 0; i < board[myRow][myCol].getShip().getLength(); i++) {//if every part of the ship was hit, sink it
								board[board[myRow][myCol].getShip().getCoordinates().get(i).x][board[myRow][myCol]
										.getShip().getCoordinates().get(i).y].sinkShip();
								sank = true;
							}
					}
				}
			} else {
				torpedoes--;
			}
			label.setText("You have " + torpedoes + " torpedoes.");
			if(sank)
				label.setText("You sank the " + board[myRow][myCol].getShip().getName());
			if (hits == 12 || torpedoes == 0 || playerBoard.gameEnd) {
				endGame();
				return;
			}
			playerBoard.computerAction();
			if (playerBoard.gameEnd) {
				endGame();
				return;
			}
		} // actionPerformed of Handler
	}

	// Handling the Reset button
	public class Handler2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (boardSpace[] row : board)
				for (boardSpace space : row)
					space.reset();
			torpedoes = 60;
			hits = 0;
			label.setText("You have " + torpedoes+ " torpedoes");
			reset.setEnabled(true);	
			for(Ship s: fleet) {
				s.reset();
			}
			placeAll();
			playerBoard.reset();
			System.out.println("--------RESET---------");
		} // actionPerformed of Handler2
	}
}