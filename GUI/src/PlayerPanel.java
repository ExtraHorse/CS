import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
	private PlayerSpace[][] board;
	private int hits, torpedoes;
	private JLabel label;
	static Ship[] fleet = { new Ship(4, "Battleship"), new Ship(3, "Destroyer"), new Ship(3, "Submarine"),
			new Ship(2, "Patrol Boat") };
	public boolean gameEnd;
	// variables for the computer to play
	private boolean previousHit;
	private int myRow, myCol;
	private ArrayList<Coord> guess;
	private ArrayList<Coord> possibleGuess;

	public PlayerPanel() {
		setLayout(new BorderLayout());
		guess = new ArrayList<Coord>();
		possibleGuess = new ArrayList<Coord>();
		hits = 0;
		torpedoes = 60;
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		add(north, BorderLayout.NORTH);
		label = new JLabel("Your opponent has " + torpedoes + " torpedoes.");
		north.add(label);
		previousHit = false;
		gameEnd = false;
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(10, 10));
		add(center, BorderLayout.CENTER);
		board = new PlayerSpace[10][10];
		for (int r = 0; r < 10; r++)
			for (int c = 0; c < 10; c++) {
				board[r][c] = new PlayerSpace();
				center.add(board[r][c]);
			}
		placeAll();
	}

	private void placeAll() {
		for (Ship s : fleet)
			placeShip(s);
	}

	public int getHits() { return hits;}
	public int getTorpedoes() {return torpedoes;}
	
	private void placeShip(Ship s) {
		boolean added = false;
		int orientation; // 1 = down, 2 = right, 3 = up, 4 = left
		int r, c;
		while (!added) { // Find and Test possible placements
			r = (int)(Math.random() * (board.length - 2));
			c = (int)(Math.random() * (board[0].length - 2));
			System.out.println("Attempted Placement for Player Ship: " + r + " " + c);
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
			if (occupied == 0) {// place the ship if possible
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
		label.setText("");
		gameEnd = true;
	}

	public void removeAll(ArrayList<Coord> a) {
		for (int i = a.size() - 1; i >= 0; i--)
			a.remove(i);
	}
	
	public void computerAction() {
		boolean sank = false;
		boolean eligible = false;
		if (guess.size() > 0) {
			if(!previousHit)
				possibleGuess.remove(0);
			else if(guess.size() == 1) {
				if (guess.get(guess.size() - 1).x > 0
						&& !board[guess.get(guess.size() - 1).y][guess.get(guess.size() - 1).x - 1].hitOnce())
					possibleGuess.add(new Coord(guess.get(guess.size() - 1).x - 1, guess.get(guess.size() - 1).y));
				if (guess.get(guess.size() - 1).x < board[0].length - 1
						&& !board[guess.get(guess.size() - 1).y][guess.get(guess.size() - 1).x + 1].hitOnce())
					possibleGuess.add(new Coord(guess.get(guess.size() - 1).x + 1, guess.get(guess.size() - 1).y));
				if (guess.get(guess.size() - 1).y > 0
						&& !board[guess.get(guess.size() - 1).y - 1][guess.get(guess.size() - 1).x].hitOnce())
					possibleGuess.add(new Coord(guess.get(guess.size() - 1).x, guess.get(guess.size() - 1).y - 1));
				if (guess.get(guess.size() - 1).y < board.length - 1
						&& !board[guess.get(guess.size() - 1).y + 1][guess.get(guess.size() - 1).x].hitOnce())
					possibleGuess.add(new Coord(guess.get(guess.size() - 1).x, guess.get(guess.size() - 1).y + 1));
			} else if (previousHit) {
				removeAll(possibleGuess);
				int firstX = guess.get(0).x;
				int firstY = guess.get(0).y;
				int lastX = guess.get(guess.size() - 1).x;
				int lastY = guess.get(guess.size() - 1).y;
				if(firstX == lastX) {		
					if(firstY < lastY) {
						if(firstY > 0 && !board[firstY - 1][firstX].hitOnce())
							possibleGuess.add(new Coord(firstX, firstY - 1));
						if(lastY < board.length - 1 && !board[lastY + 1][firstX].hitOnce())
							possibleGuess.add(new Coord(firstX, lastY + 1));
					} else {
						if(lastY > 0 && !board[lastY - 1][firstX].hitOnce())
							possibleGuess.add(new Coord(firstX, lastY - 1));
						if(firstY < board.length - 1 && !board[firstY + 1][firstX].hitOnce())
							possibleGuess.add(new Coord(firstX, firstY + 1));
					}
				} else {
					if(firstX < lastX) {
						if(firstX > 0 && !board[firstY][firstX - 1].hitOnce())
							possibleGuess.add(new Coord(firstX - 1, firstY));
						if(lastX < board.length - 1 && !board[firstY][lastX + 1].hitOnce())
							possibleGuess.add(new Coord(lastX + 1, firstY));
					} else {
						if(lastX > 0 && !board[firstY][lastX - 1].hitOnce())
							possibleGuess.add(new Coord(lastX - 1, firstY));
						if(firstX < board.length - 1 && !board[firstY][firstX + 1].hitOnce())
							possibleGuess.add(new Coord(firstX + 1, firstY));
					}
				}		
			}
			for (Coord c : possibleGuess)
				System.out.println("Possible Guesses: " + c.x + "," + c.y);
			Coord selected = possibleGuess.get(0);
			System.out.println(selected);
			myRow = selected.y;
			myCol = selected.x;
		} else {
			while (!eligible) {
				myRow = (int) (Math.random() * board.length);
				myCol = (int) (Math.random() * board[0].length);
				if (!board[myRow][myCol].hitOnce())
					eligible = true;
			}
		}
		board[myRow][myCol].hit();//Handle the hit
		if (board[myRow][myCol].shipPresent()) {
			hits++;
			torpedoes--;
			guess.add(new Coord(myCol, myRow));
			previousHit = true;
			for (Coord c : board[myRow][myCol].getShip().getCoordinates()) {
				if (c.x == myRow && c.y == myCol) {
					board[myRow][myCol].getShip().hit();
					if (board[myRow][myCol].getShip().getHits() == board[myRow][myCol].getShip().getLength()) {
						for (int i = 0; i < board[myRow][myCol].getShip().getLength(); i++) {// if every part of the ship was hit, sink it
							board[board[myRow][myCol].getShip().getCoordinates().get(i).x][board[myRow][myCol].getShip()
									.getCoordinates().get(i).y].sinkShip();
						}
						sank = true;
						previousHit = false;
						removeAll(guess);
						removeAll(possibleGuess);
					}
				}
			}
		} else {
			torpedoes--;
			previousHit = false;
		}
		label.setText("Your opponent has " + torpedoes + " torpedoes.");
		if (sank)
			label.setText("They sank your " + board[myRow][myCol].getShip().getName());
		if (hits == 12 || torpedoes == 0)
			endGame();
		// actionPerformed of Handler
	}

	// Handling the Reset button
	public void reset() {
		for (PlayerSpace[] row : board)
			for (PlayerSpace space : row)
				space.reset();
		removeAll(guess);
		removeAll(possibleGuess);
		torpedoes = 60;
		hits = 0;
		label.setText("You have " + torpedoes + " torpedoes.");
		for (Ship s : fleet) {
			s.reset();
		}
		gameEnd = false;
		placeAll();
		// actionPerformed of Handler2
	}
}
