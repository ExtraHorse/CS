import java.awt.Color;
import javax.swing.*;
public class boardSpace extends JButton {
	private boolean shipPresent;
	private boolean shipSunk;
	private Ship ship;
	public boardSpace() {
		super();
		shipPresent = false;
		setBackground(Color.blue);
		shipSunk = false;
	}
	
	public void setShip(Ship s) {
		shipPresent = true;
		ship = s;
	}
	public boolean shipPresent() { return shipPresent;}
	public boolean shipSunk() { return shipSunk;}
	public Ship getShip() { return ship;}
	public void hit() {
		if(shipPresent)
			setBackground(Color.red);
		else
			setBackground(Color.WHITE);
		setEnabled(false);
	}
	public void sinkShip() {
		shipSunk = true;
		setBackground(Color.black);
	}
	public void end() {
		if(shipPresent)
			setBackground(Color.red);
		if(shipSunk)
			setBackground(Color.black);
		setEnabled(false);
	}
	public void reset() {
		setBackground(Color.blue);
		setEnabled(true);
		shipPresent = false;
		shipSunk = false;
	}
}
