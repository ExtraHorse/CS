import java.util.ArrayList;

public class Ship {
	private int length;
	private String name;
	private int numHits;
	private ArrayList<Coord> coordinates;
	public Ship(int l, String s) {
		length = l;
		name = s;
		numHits = 0;
		coordinates = new ArrayList<Coord>();
	}
	public int getLength() {
		return length;
	}
	public void hit() {
		numHits++;
	}
	public int getHits() {
		return numHits;
	}
	public String getName() {
		return name;
	}
	public void addCoord(int x, int y) {
		coordinates.add(new Coord(x,y));
	}
	public ArrayList<Coord> getCoordinates(){
		return coordinates;
	}
	public void reset() {
		for(int i = coordinates.size() - 1; i >= 0; i--)
			coordinates.remove(i);
		numHits = 0;
	}
}
