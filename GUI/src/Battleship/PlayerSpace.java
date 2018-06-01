package Battleship;
import java.awt.*;
import javax.swing.*;

public class PlayerSpace extends JButton{
	private boolean shipPresent, shipSunk, hitOnce;
	private Ship ship;

	public PlayerSpace() {
			super();
			shipPresent = false;
			setBackground(Color.WHITE);
			shipSunk = false;
			setEnabled(false);
			hitOnce = false;
		}

	public void setShip(Ship s) {
		shipPresent = true;
		ship = s;
		setBackground(Color.gray);
	}

	public boolean shipPresent() {return shipPresent;}

	public boolean shipSunk() {return shipSunk;}

	public Ship getShip() {return ship;}
	
	public boolean hitOnce() {return hitOnce;}

	public void hit() {
		if (shipPresent)
			setBackground(Color.red);
		else
			setBackground(Color.yellow);
		hitOnce = true;
	}

	public void sinkShip() {
		shipSunk = true;
		setBackground(Color.black);
	}

	public void end() {
		if (shipPresent)
			setBackground(Color.red);
		if (shipSunk)
			setBackground(Color.black);
		setEnabled(false);
	}

	public void reset() {
		setBackground(Color.WHITE);
		setEnabled(false);
		shipPresent = false;
		shipSunk = false;
		hitOnce = false;
	}

}
