package Battleship;

public class Coord {
	public int x;
	public int y;
	public int paths;
	public Coord(int x, int y, int paths) {
		this.x = x;
		this.y = y;
		this.paths = paths;
	}
	public String toString() {
		return "X: " + x + " Y: " + y;
	}
}
